package msc.neuroscience.neuroBench.core.model.primitive;

/**
 *
 * @author Keshan De Silva
 */
public abstract class Node
{
    private double value;

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }
}
