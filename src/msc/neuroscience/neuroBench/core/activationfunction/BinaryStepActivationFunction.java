package msc.neuroscience.neuroBench.core.activationfunction;

/**
 *
 * @author Keshan De Silva
 */
public class BinaryStepActivationFunction extends ThresholdActivationFunction
{
    @Override
    public double activation(double amount)
    {
        if (amount < getThresholdValue())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}
