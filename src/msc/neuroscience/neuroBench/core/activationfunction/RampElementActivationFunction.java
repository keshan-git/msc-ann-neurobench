package msc.neuroscience.neuroBench.core.activationfunction;

/**
 *
 * @author Keshan De Silva
 */
public class RampElementActivationFunction extends ThresholdActivationFunction
{
    @Override
    public double activation(double amount)
    {
        if (amount >= getThresholdValue())
        {
            return getThresholdValue();
        }
        else if (amount <= getThresholdValue() * -1)
        {
            return getThresholdValue() * (-1);
        }
        else
        {
            return amount;
        }
    }   
}
