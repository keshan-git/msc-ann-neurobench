package msc.neuroscience.neuroBench.core.model;

import java.util.ArrayList;

/**
 *
 * @author Keshan De Silva
 */
public class NeuralNetwork
{
    private final ArrayList<InputNode> inputLayerNodes;
    private final ArrayList<HiddenNode> hiddenLayerNodes;
    private final ArrayList<OutputNode> outputLayerNodes;
    
    private final ArrayList<Link> layerOneConnections;
    private final ArrayList<Link> layerTwoConnections;

    public NeuralNetwork()
    {
        inputLayerNodes = new ArrayList<>();
        hiddenLayerNodes = new ArrayList<>();
        outputLayerNodes = new ArrayList<>();
    
        layerOneConnections = new ArrayList<>();
        layerTwoConnections = new ArrayList<>();
    }

    public ArrayList<InputNode> getInputLayerNodes()
    {
        return inputLayerNodes;
    }
    
    public void addInputNode(InputNode inputNode)
    {
        inputLayerNodes.add(inputNode);
    }
    
    public ArrayList<HiddenNode> getHiddenLayerNodes()
    {
        return hiddenLayerNodes;
    }
    
    public void addHiddenNode(HiddenNode hiddenNode)
    {
        hiddenLayerNodes.add(hiddenNode);
    }

    public ArrayList<OutputNode> getOutputLayerNodes()
    {
        return outputLayerNodes;
    }

    public void addOutputNode(OutputNode outputNode)
    {
        outputLayerNodes.add(outputNode);
    }
        
    public ArrayList<Link> getLayerOneConnections()
    {
        return layerOneConnections;
    }

    public ArrayList<Link> getLayerTwoConnections()
    {
        return layerTwoConnections;
    }  
    
    public boolean isMultiLayer()
    {
        return !hiddenLayerNodes.isEmpty();
    }
    
    private void generateNetworkLinks()
    {
        layerOneConnections.clear();
        layerTwoConnections.clear();
        
        for (InputNode inputNode : inputLayerNodes)
        {
            Link layerOneLink = null;
            if (!hiddenLayerNodes.isEmpty())
            {
                for (HiddenNode hiddenNode : hiddenLayerNodes)
                {
                    layerOneLink = new Link(inputNode, hiddenNode);
                }       
            }
            else
            {
                for (OutputNode outputNode : outputLayerNodes)
                {
                    layerOneLink = new Link(inputNode, outputNode);
                }  
            }
            layerOneConnections.add(layerOneLink);
        }
        
        if (hiddenLayerNodes.isEmpty())
        {
            for (HiddenNode hiddenNode : hiddenLayerNodes)
            {
                for (OutputNode outputNode : outputLayerNodes)
                {
                    Link link = new Link(hiddenNode, outputNode);
                    layerTwoConnections.add(link);
                }
            }
        }  
    }
    
    public void evaluateNeuralNetwork()
    {        
        for (OutputNode outputNode : outputLayerNodes)
        {
            outputNode.reset();
        }
        
        for (HiddenNode hiddenNode : hiddenLayerNodes)
        {
            hiddenNode.reset();
        }
                    
        for (Link link : layerOneConnections)
        {
            double input = link.getFromNode().getValue();
            double weight = link.getWeight();
            double weightedInput = input * weight;

            link.getToNode().addInputValue(weightedInput);
            link.getToNode().processActivation();
        }
        
        if (!hiddenLayerNodes.isEmpty())
        {                    
            for (Link link : layerTwoConnections)
            {
                double input = link.getFromNode().getValue();
                double weight = link.getWeight();
                double weightedInput = input * weight;
                
                link.getToNode().addInputValue(weightedInput);
                link.getToNode().processActivation();
            }
        }
    }   
}
