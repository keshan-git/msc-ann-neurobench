package msc.neuroscience.neuroBench.core.activationfunction;

/**
 *
 * @author Keshan De Silva
 */
public abstract class SigmoidActivationFunction extends ThresholdActivationFunction
{
    private double alpha = 1;

    public double getAlpha()
    {
        return alpha;
    }

    public void setAlpha(double alpha)
    {
        this.alpha = alpha;
    }
    
    
}
