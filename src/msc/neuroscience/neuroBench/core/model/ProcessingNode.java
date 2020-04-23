package msc.neuroscience.neuroBench.core.model;

import msc.neuroscience.neuroBench.core.model.primitive.Node;
import msc.neuroscience.neuroBench.core.activationfunction.ActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.BinaryStepActivationFunction;

/**
 *
 * @author Keshan De Silva
 */
public abstract class ProcessingNode extends Node
{   
    private double inputValue;
    private ActivationFunction activationFunction;

    public ProcessingNode()
    {
        activationFunction = new BinaryStepActivationFunction();
    }

    public double getInputValue()
    {
        return inputValue;
    }

    public void setInputValue(double inputValue)
    {
        this.inputValue = inputValue;
    }
    
    public void addInputValue(double inputValue)
    {
        this.inputValue += inputValue;
    }

    public ActivationFunction getActivationFunction()
    {
        return activationFunction;
    }

    public void setActivationFunction(ActivationFunction activationFunction)
    {
        this.activationFunction = activationFunction;
    }
    
    public void processActivation()
    {
        if (activationFunction != null)
        {
            setValue(activationFunction.activation(inputValue));
        }
    }
    
    public void reset()
    {
        setValue(0);
        setInputValue(0);
    }
}
