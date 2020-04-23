package msc.neuroscience.neuroBench.core.activationfunction;

/**
 *
 * @author Keshan De Silva
 */
public abstract class ThresholdActivationFunction implements ActivationFunction
{
    private double thresholdValue = 0;

    public double getThresholdValue()
    {
        return thresholdValue;
    }

    public void setThresholdValue(double thresholdValue)
    {
        this.thresholdValue = thresholdValue;
    }
}
