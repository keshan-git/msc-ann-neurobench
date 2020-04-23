package msc.neuroscience.neuroBench.core;

import msc.neuroscience.neuroBench.core.model.primitive.DataSet;

/**
 *
 * @author Keshan De Silva
 */
public class LearningStatusEvent
{
    private DataSet input;
    private DataSet desiredOutput;
    private DataSet actualOutput;
    private DataSet errorOutput;
    private double totalError;

    public LearningStatusEvent()
    {
    }

    public LearningStatusEvent(DataSet input, DataSet desiredOutput, DataSet actualOutput, DataSet errorOutput, double totalError)
    {
        this.input = input;
        this.desiredOutput = desiredOutput;
        this.actualOutput = actualOutput;
        this.errorOutput = errorOutput;
        this.totalError = totalError;
    }

    public DataSet getInput()
    {
        return input;
    }

    public DataSet getDesiredOutput()
    {
        return desiredOutput;
    }

    public DataSet getActualOutput()
    {
        return actualOutput;
    }

    public DataSet getErrorOutput()
    {
        return errorOutput;
    }

    public double getTotalError()
    {
        return totalError;
    }
    
    
}
