package msc.neuroscience.neuroBench.core.model;

import msc.neuroscience.neuroBench.core.model.primitive.Node;

/**
 *
 * @author Keshan De Silva
 */
public class Link
{
    private double weight;
    private double delta;
    private Node from;
    private ProcessingNode to;

    public Link(double weight)
    {
        this.weight = weight;
    }

    public Link(Node from, ProcessingNode to)
    {
        this.from = from;
        this.to = to;
    }
    
    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public Node getFromNode()
    {
        return from;
    }

    public void setFromNode(Node from)
    {
        this.from = from;
    }

    public ProcessingNode getToNode()
    {
        return to;
    }

    public void setToNode(ProcessingNode to)
    {
        this.to = to;
    }  

    public double getDelta()
    {
        return delta;
    }

    public void setDelta(double delta)
    {
        this.delta = delta;
    }
}
