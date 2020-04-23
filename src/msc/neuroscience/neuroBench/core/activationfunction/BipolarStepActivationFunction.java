package msc.neuroscience.neuroBench.core.activationfunction;

/**
 *
 * @author Keshan De Silva
 */
public class BipolarStepActivationFunction extends ThresholdActivationFunction
{
    @Override
    public double activation(double amount)
    {
        if (amount < getThresholdValue())
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
    
}
