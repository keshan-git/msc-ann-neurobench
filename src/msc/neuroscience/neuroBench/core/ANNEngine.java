package msc.neuroscience.neuroBench.core;

import java.util.ArrayList;
import msc.neuroscience.neuroBench.core.activationfunction.ActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.SigmoidActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.ThresholdActivationFunction;
import msc.neuroscience.neuroBench.core.model.HiddenNode;
import msc.neuroscience.neuroBench.core.model.Link;
import msc.neuroscience.neuroBench.core.model.NeuralNetwork;
import msc.neuroscience.neuroBench.core.model.OutputNode;
import msc.neuroscience.neuroBench.core.model.ProcessingNode;
import msc.neuroscience.neuroBench.core.model.TrainingDataSet;
import msc.neuroscience.neuroBench.core.model.primitive.DataSet;
import msc.neuroscience.neuroBench.core.model.primitive.Node;

/**
 *
 * @author Keshan De Silva
 */
public class ANNEngine
{
    private NeuralNetwork neuralNetwork;
    private TrainingDataSet trainingDataSet;
    private DataSet input;
    private boolean trainingMode;
    
    private LearningStatusListener learningStatusListener;
    
    public ANNEngine()
    {
    }
    
    public NeuralNetwork getNeuralNetwork()
    {
        return neuralNetwork;
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork)
    {
        this.neuralNetwork = neuralNetwork;
    }

    public void setTrainingMode(boolean trainingMode)
    {
        this.trainingMode = trainingMode;
    }

    public TrainingDataSet getTrainingDataSet()
    {
        return trainingDataSet;
    }

    public void setTrainingDataSet(TrainingDataSet trainingDataSet)
    {
        this.trainingDataSet = trainingDataSet;
    }

    public DataSet getInput()
    {
        return input;
    }

    public void setInput(DataSet input)
    {
        this.input = input;
        for(int i = 0; i < input.getColumnCount(); i++)
        {
            neuralNetwork.getInputLayerNodes().get(i).setValue(input.getDataAt(0, i));
        }
    }

    public void setLearningStatusListener(LearningStatusListener learningStatusListener)
    {
        this.learningStatusListener = learningStatusListener;
    }

    public void evaluateANN()
    {
        neuralNetwork.evaluateNeuralNetwork();
    }

    public void updateActivationFunction(ActivationFunction activationFunction)
    {
        for (HiddenNode hiddenNode : neuralNetwork.getHiddenLayerNodes())
        {
            hiddenNode.setActivationFunction(activationFunction);
        }
        
        for (OutputNode outputNode : neuralNetwork.getOutputLayerNodes())
        {
            outputNode.setActivationFunction(activationFunction);
        }
        
        neuralNetwork.evaluateNeuralNetwork();
    }

    public void updateActivationFunctionParameters(double threshold, double alpha)
    {
        for (HiddenNode hiddenNode : neuralNetwork.getHiddenLayerNodes())
        {
            ActivationFunction activationFunction = hiddenNode.getActivationFunction();
            if (activationFunction instanceof ThresholdActivationFunction)
            {
                ((ThresholdActivationFunction)activationFunction).setThresholdValue(threshold);
            }
            
            if (activationFunction instanceof SigmoidActivationFunction)
            {
                ((SigmoidActivationFunction)activationFunction).setAlpha(alpha);
            }
        }
        
        for (OutputNode outputNode : neuralNetwork.getOutputLayerNodes())
        {
            ActivationFunction activationFunction = outputNode.getActivationFunction();
            if (activationFunction instanceof ThresholdActivationFunction)
            {
                ((ThresholdActivationFunction)activationFunction).setThresholdValue(threshold);
            }
            
            if (activationFunction instanceof SigmoidActivationFunction)
            {
                ((SigmoidActivationFunction)activationFunction).setAlpha(alpha);
            }
        }
        
        neuralNetwork.evaluateNeuralNetwork();
    }

    public void startSuperviousTraining(double learningRate, double acceptanceRange)
    {
        int dataSetSize = trainingDataSet.getInputDataSet().getRowCount();

        DataSet input;
        DataSet desiredOutput;
        DataSet actualOutput;
        DataSet errorOutput;
        double totalError = 0;
        boolean acceptFlag = true;
        
        if (neuralNetwork.isMultiLayer())
        {
            do
            {
                acceptFlag = true;
                for (int i = 0; i < dataSetSize; i++)
                {
                    input = trainingDataSet.getInputDataSet().getDataSetAt(i);
                    for(int j = 0; j < trainingDataSet.getInputDataSet().getColumnCount(); j++)
                    {
                        // set input data
                        neuralNetwork.getInputLayerNodes().get(j).setValue(trainingDataSet.getInputDataSet().getDataAt(i, j));
                    }

                    // evaluate
                    evaluateANN();

                    desiredOutput = trainingDataSet.getOutputDataSet().getDataSetAt(i);
                    actualOutput = new DataSet(1, trainingDataSet.getOutputDataSet().getColumnCount());

                    // Error calculation - Hidden/Output Layer
                    for (int j = 0; j < neuralNetwork.getOutputLayerNodes().size(); j++)
                    {
                        OutputNode outputNode = neuralNetwork.getOutputLayerNodes().get(j);        
                        double currentValue = outputNode.getValue();
                        double desiredValue = trainingDataSet.getOutputDataSet().getDataAt(i, j);
                        double error = desiredValue - currentValue;
                        double deltaValue = currentValue * (1 - currentValue) * error;
                        actualOutput.setDataAt(0, j, currentValue);

                        ArrayList<Link> links = getLinksEndTO(outputNode);
                        for (Link link : links)
                        {
                            link.setDelta(deltaValue);
                        }
                    }

                    // Error calculation - Input/Hidden Layer
                    for (int j = 0; j < neuralNetwork.getHiddenLayerNodes().size(); j++)
                    {
                        HiddenNode hiddenNode = neuralNetwork.getHiddenLayerNodes().get(j);   
                        double currentValue = hiddenNode.getValue();
                        double deltaValue = currentValue * (1 - currentValue);
                        double summation = 0;

                        ArrayList<Link> hlinks = getLinksStratFrom(hiddenNode);
                        for (Link link : hlinks)
                        {
                            summation += link.getWeight() * link.getDelta();
                        }

                        deltaValue = deltaValue * summation;    
                        for (Link hlink : getLinksEndTO(hiddenNode))
                        {
                            hlink.setDelta(deltaValue);
                        }    
                    }

                    errorOutput = desiredOutput.different(actualOutput);

                    //Update weights : Input / Hidden
                    for (Link link : neuralNetwork.getLayerOneConnections())
                    {
                        double currentWeight = link.getWeight();
                        double adjustment = learningRate * link.getFromNode().getValue() * link.getDelta();

                        link.setWeight(currentWeight + adjustment);
                    }

                    //Update weights : Hidden / Output
                    for (Link link : neuralNetwork.getLayerTwoConnections())
                    {
                        double currentWeight = link.getWeight();
                        double adjustment = learningRate * link.getFromNode().getValue() * link.getDelta();

                        link.setWeight(currentWeight + adjustment);
                    }

                    // Error calculation
                    totalError = 0;
                    for (int j = 0; j < neuralNetwork.getOutputLayerNodes().size(); j++)
                    {
                        OutputNode outputNode = neuralNetwork.getOutputLayerNodes().get(j);        
                        double currentValue = outputNode.getValue();
                        double desiredValue = trainingDataSet.getOutputDataSet().getDataAt(i, j);
                        double error = desiredValue - currentValue;
                        totalError += Math.abs(error);
                    }

                    LearningStatusEvent evt = new LearningStatusEvent(input, desiredOutput, actualOutput, errorOutput, totalError);
                    learningStatusListener.onLearningStstusUpdate(evt);
                    
                    acceptFlag &= (totalError < acceptanceRange);
                }
            }
            while ((!acceptFlag) && (trainingMode)); 
        }
        else
        {
            do
            {
                acceptFlag = true;
                for (int i = 0; i < dataSetSize; i++)
                {
                    input = trainingDataSet.getInputDataSet().getDataSetAt(i);
                    for(int j = 0; j < trainingDataSet.getInputDataSet().getColumnCount(); j++)
                    {
                        // set input data
                        neuralNetwork.getInputLayerNodes().get(j).setValue(trainingDataSet.getInputDataSet().getDataAt(i, j));
                    }

                    // evaluate
                    evaluateANN();

                    desiredOutput = trainingDataSet.getOutputDataSet().getDataSetAt(i);
                    actualOutput = new DataSet(1, trainingDataSet.getOutputDataSet().getColumnCount());

                    // Error calculation - Input/Output Layer
                    for (int j = 0; j < neuralNetwork.getOutputLayerNodes().size(); j++)
                    {
                        OutputNode outputNode = neuralNetwork.getOutputLayerNodes().get(j);        
                        double currentValue = outputNode.getValue();
                        double desiredValue = trainingDataSet.getOutputDataSet().getDataAt(i, j);
                        double error = desiredValue - currentValue;
//                        double deltaValue = currentValue * (1 - currentValue) * error;
                        actualOutput.setDataAt(0, j, currentValue);

                        ArrayList<Link> links = getLinksEndTO(outputNode);
                        for (Link link : links)
                        {
                            link.setDelta(error);
                        }
                    }

                    errorOutput = desiredOutput.different(actualOutput);

                    //Update weights : Input / Output
                    for (Link link : neuralNetwork.getLayerOneConnections())
                    {
                        double currentWeight = link.getWeight();
                        double adjustment = learningRate * link.getFromNode().getValue() * link.getDelta();

                        link.setWeight(currentWeight + adjustment);
                    }

                    // Error calculation
                    totalError = 0;
                    for (int j = 0; j < neuralNetwork.getOutputLayerNodes().size(); j++)
                    {
                        OutputNode outputNode = neuralNetwork.getOutputLayerNodes().get(j);        
                        double currentValue = outputNode.getValue();
                        double desiredValue = trainingDataSet.getOutputDataSet().getDataAt(i, j);
                        double error = desiredValue - currentValue;
                        totalError += Math.abs(error);
                    }

                    LearningStatusEvent evt = new LearningStatusEvent(input, desiredOutput, actualOutput, errorOutput, totalError);
                    learningStatusListener.onLearningStstusUpdate(evt);
                    
                    acceptFlag &= (totalError < acceptanceRange);
                }
            }
            while ((!acceptFlag) && (trainingMode)); 
        }
    }
    
    public void startUnSuperviousTraining(double learningRate, double acceptanceLevel)
    {
        int dataSetSize = trainingDataSet.getInputDataSet().getRowCount();

        DataSet input;
        DataSet actualOutput;
        boolean acceptFlag = true;
        
        if (!neuralNetwork.isMultiLayer())
        {
            do
            {
                acceptFlag = false;
                for (int i = 0; i < dataSetSize; i++)
                {
                    input = trainingDataSet.getInputDataSet().getDataSetAt(i);
                    for(int j = 0; j < trainingDataSet.getInputDataSet().getColumnCount(); j++)
                    {
                        // set input data
                        neuralNetwork.getInputLayerNodes().get(j).setValue(trainingDataSet.getInputDataSet().getDataAt(i, j));
                    }

                    // evaluate
                    evaluateANN();

                    actualOutput = new DataSet(1, trainingDataSet.getOutputDataSet().getColumnCount());

                    // Delta calculation
                    for (int j = 0; j < neuralNetwork.getOutputLayerNodes().size(); j++)
                    {
                        OutputNode outputNode = neuralNetwork.getOutputLayerNodes().get(j);        
                        double currentValue = outputNode.getValue();
                        actualOutput.setDataAt(0, j, currentValue);

                        ArrayList<Link> links = getLinksEndTO(outputNode);
                        for (Link link : links)
                        {
                            link.setDelta(currentValue);
                        }
                    }
                    
                    //Update weights : Input / Output
                    for (Link link : neuralNetwork.getLayerOneConnections())
                    {
                        double currentWeight = link.getWeight();
                        double adjustment = learningRate * link.getFromNode().getValue() * link.getDelta();

                        link.setWeight(currentWeight + adjustment);
                    }


//                    // Error calculation
//                    totalError = 0;
//                    for (int j = 0; j < neuralNetwork.getOutputLayerNodes().size(); j++)
//                    {
//                        OutputNode outputNode = neuralNetwork.getOutputLayerNodes().get(j);        
//                        double currentValue = outputNode.getValue();
//                        double desiredValue = trainingDataSet.getOutputDataSet().getDataAt(i, j);
//                        double error = desiredValue - currentValue;
//                        totalError += Math.abs(error);
//                    }
//
//                    LearningStatusEvent evt = new LearningStatusEvent(input, desiredOutput, actualOutput, errorOutput, totalError);
//                    learningStatusListener.onLearningStstusUpdate(evt);
                    
//                    acceptFlag &= (totalError < acceptanceRange);
                }
            }
            while ((!acceptFlag) && (trainingMode)); 
        }
    }
        
    public void evaluateANN(int rowIndex)
    {
        setInput(getTrainingDataSet().getInputDataSet().getDataSetAt(rowIndex));

        // evaluate
        evaluateANN();
    }
    
    private ArrayList<Link> getLinksEndTO(ProcessingNode node)
    {
        ArrayList<Link> linkList = new ArrayList<>();
        
        if ((node instanceof HiddenNode) && (neuralNetwork.isMultiLayer()))
        {
            for (Link link : neuralNetwork.getLayerOneConnections())
            {
                if (link.getToNode() == node)
                {
                    linkList.add(link);
                }
            }
        }
        else if (node instanceof OutputNode)
        {
            if (neuralNetwork.isMultiLayer())
            {
                for (Link link : neuralNetwork.getLayerTwoConnections())
                {
                    if (link.getToNode() == node)
                    {
                        linkList.add(link);
                    }
                }
            }
            else
            {
                for (Link link : neuralNetwork.getLayerOneConnections())
                {
                    if (link.getToNode() == node)
                    {
                        linkList.add(link);
                    }
                }
            }       
        }
        
        return linkList;
    }
    
    private ArrayList<Link> getLinksEndTO(HiddenNode node)
    {
        ArrayList<Link> linkList = new ArrayList<>();
        
        for (Link link : neuralNetwork.getLayerOneConnections())
        {
            if (link.getToNode() == node)
            {
                linkList.add(link);
            }
        }
        
        return linkList;
    }
        
    private ArrayList<Link> getLinksStratFrom(Node node)
    {
        ArrayList<Link> linkList = new ArrayList<>();
        
        for (Link link : neuralNetwork.getLayerTwoConnections())
        {
            if (link.getFromNode()== node)
            {
                linkList.add(link);
            }
        }
        
        return linkList;
    }
}
