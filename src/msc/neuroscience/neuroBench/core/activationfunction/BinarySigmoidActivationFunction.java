package msc.neuroscience.neuroBench.core.activationfunction;

/**
 *
 * @author Keshan De Silva
 */
public class BinarySigmoidActivationFunction extends SigmoidActivationFunction
{
    @Override
    public double activation(double amount)
    {
        return (1 / ( 1 + Math.exp(-1 * getAlpha() * (amount + getThresholdValue()))));
    }
    
}
