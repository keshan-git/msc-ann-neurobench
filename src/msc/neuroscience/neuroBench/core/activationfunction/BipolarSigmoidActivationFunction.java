package msc.neuroscience.neuroBench.core.activationfunction;

/**
 *
 * @author Keshan De Silva
 */
public class BipolarSigmoidActivationFunction extends SigmoidActivationFunction
{

    @Override
    public double activation(double amount)
    {
        return (2 / ( 1 + Math.exp(-1 * getAlpha() * (amount + getThresholdValue())))) - 1;
    }
    
}
