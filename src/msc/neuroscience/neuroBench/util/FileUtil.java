package msc.neuroscience.neuroBench.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import msc.neuroscience.neuroBench.core.ANNEngine;
import msc.neuroscience.neuroBench.core.model.HiddenNode;
import msc.neuroscience.neuroBench.core.model.InputNode;
import msc.neuroscience.neuroBench.core.model.Link;
import msc.neuroscience.neuroBench.core.model.NeuralNetwork;
import msc.neuroscience.neuroBench.core.model.OutputNode;
import msc.neuroscience.neuroBench.core.model.TrainingDataSet;
import msc.neuroscience.neuroBench.core.model.primitive.DataSet;

/**
 *
 * @author Keshan De Silva
 */
public class FileUtil
{
    private static FileUtil instance;
    
    private static final String FLAG = "#";
    private static final String INPUT = "INPUT";
    private static final String HIDDEN = "HIDDEN";
    private static final String OUTPUT = "OUTPUT";
    private static final String WEIGHTS_L1 = "WEIGHTS-L1";
    private static final String WEIGHTS_L2 = "WEIGHTS-L2";
    private static final String DATA = "DATA";
    
    private FileUtil()
    {}
    
    public static synchronized FileUtil getInstance()
    {
        if (instance == null)
        {
            instance = new FileUtil();
        }
        
        return instance;
    }
    
    private ArrayList<String> readFile(File file) throws FileNotFoundException, IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        ArrayList<String> data = new ArrayList<>();
        
	String line;
	while ((line = bufferedReader.readLine()) != null)
        {
            if (!line.trim().isEmpty())
            {
                data.add(line);
            }
	}
 
	bufferedReader.close();
        
        return data;
    }
    
    public void generateNeuralNetwork(ANNEngine engine, File file) 
            throws FileNotFoundException, IOException, InvalidFileFormatException
    {
        ArrayList<String> data = readFile(file);
        NeuralNetwork neuralNetwork = new NeuralNetwork();
        TrainingDataSet trainingDataSet = null;
        DataSet inputDataSet = null;
        
        int inputLayerNodes = 0;
        int hiddenLayerNodes = 0;
        int outputLayerNodes = 0;
        
        try
        {
            for (int i = 0; i < data.size(); i++)
            {
                String currentLine = data.get(i);
                if (currentLine.startsWith(FLAG))
                {
                    if (currentLine.startsWith(FLAG + INPUT))
                    {
                        inputLayerNodes = Integer.parseInt(currentLine.replaceAll(FLAG + INPUT, "").trim());
                    }    
                    else if (currentLine.startsWith(FLAG + HIDDEN))
                    {
                        hiddenLayerNodes = Integer.parseInt(currentLine.replaceAll(FLAG + HIDDEN, "").trim());
                    } 
                    else if (currentLine.startsWith(FLAG + OUTPUT))
                    {
                        outputLayerNodes = Integer.parseInt(currentLine.replaceAll(FLAG + OUTPUT, "").trim());
                    }
                    else if (currentLine.startsWith(FLAG + WEIGHTS_L1))
                    {
                        for (int j = 0; j < inputLayerNodes; j++)
                        {
                            neuralNetwork.addInputNode(new InputNode());
                        }

                        for (int j = 0; j < hiddenLayerNodes; j++)
                        {
                            neuralNetwork.addHiddenNode(new HiddenNode());
                        }

                        for (int j = 0; j < outputLayerNodes; j++)
                        {
                            neuralNetwork.addOutputNode(new OutputNode());
                        }


                        try
                        {
                        i++;
                        for (int j = 0; j < inputLayerNodes; j ++)
                        {
                            currentLine = data.get(i + j).replaceAll("\\[", "").replaceAll("]", "");
                            int k = 0;
                            for (String weightValue : currentLine.split(" "))
                            {
                                double weight = Double.parseDouble(weightValue);
                                Link link = new Link(weight);
                                link.setFromNode(neuralNetwork.getInputLayerNodes().get(j));

                                if (neuralNetwork.isMultiLayer())
                                {
                                    link.setToNode(neuralNetwork.getHiddenLayerNodes().get(k++));
                                }
                                else
                                {
                                    link.setToNode(neuralNetwork.getOutputLayerNodes().get(k++));
                                }

                                neuralNetwork.getLayerOneConnections().add(link);
                            }
                        }
                        }
                        catch (Exception ex)
                        {
                            throw new InvalidFileFormatException("Invalid weight set (Input - Hidden)");
                        }
                    }
                    else if (currentLine.startsWith(FLAG + WEIGHTS_L2))
                    {
                        try
                        {
                            i++;
                            for (int j = 0; j < hiddenLayerNodes; j ++)
                            {
                                currentLine = data.get(i + j).replaceAll("\\[", "").replaceAll("]", "");
                                int k = 0;
                                for (String weightValue : currentLine.split(" "))
                                {
                                    double weight = Double.parseDouble(weightValue);
                                    Link link = new Link(weight);
                                    link.setFromNode(neuralNetwork.getHiddenLayerNodes().get(j));
                                    link.setToNode(neuralNetwork.getOutputLayerNodes().get(k++));
                                    neuralNetwork.getLayerTwoConnections().add(link);
                                }    
                            }

                            i = i + hiddenLayerNodes - 1;
                        }
                        catch (Exception ex)
                        {
                            throw new InvalidFileFormatException("Invalid weight set (Hidden - Output)");
                        }
                    }
                    else if (currentLine.startsWith(FLAG + DATA))
                    {
                        try
                        {
                            int dataSetSize = Integer.parseInt(currentLine.replaceAll(FLAG + DATA, "").trim());
                            trainingDataSet = new TrainingDataSet(new DataSet(dataSetSize, inputLayerNodes),
                                        new DataSet(dataSetSize, outputLayerNodes));
                            inputDataSet = new DataSet(1, inputLayerNodes);

                            int row = 0;
                            for (int j = i + 1; j < data.size(); j++)
                            {
                                currentLine = data.get(j);
                                String[] dataSet = currentLine.split("\\]\\[");
                                String[] inputData = dataSet[0].replaceAll("\\[", "").split(" ");
                                String[] outputData = dataSet[1].replaceAll("\\]", "").split(" ");

                                int k = 0;
                                for (String input : inputData)
                                {
                                    if (j == (i + 1))
                                    {
                                        inputDataSet.setDataAt(0, k, Double.parseDouble(input));
                                    }

                                    trainingDataSet.getInputDataSet().setDataAt(row, k++, Double.parseDouble(input));

                                }

                                k = 0;
                                for (String output : outputData)
                                {
                                    trainingDataSet.getOutputDataSet().setDataAt(row, k++, Double.parseDouble(output));
                                }

                                row++;
                            }
                        }
                        catch (Exception ex)
                        {
                            throw new InvalidFileFormatException("Invalid training data set");
                        }
                    }
                }
            }

            if ((trainingDataSet != null) && (inputDataSet != null))
            {
                engine.setNeuralNetwork(neuralNetwork);
                engine.setTrainingDataSet(trainingDataSet);
                engine.setInput(inputDataSet);
            }
            else
            {
                throw new InvalidFileFormatException("Invalid data file");
            }
        }
        catch (NumberFormatException ex)
        {
            throw new InvalidFileFormatException("Invalid digits - Layer count");
        }
    }
}
