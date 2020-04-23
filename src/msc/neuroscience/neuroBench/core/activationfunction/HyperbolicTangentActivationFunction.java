package msc.neuroscience.neuroBench.core.activationfunction;

/**
 *
 * @author Keshan De Silva
 */
public class HyperbolicTangentActivationFunction implements ActivationFunction
{

    @Override
    public double activation(double amount)
    {
        return (Math.exp(amount) - Math.exp(-1 * amount)) 
                / (Math.exp(amount) + Math.exp(-1 * amount));
    }
    
}
