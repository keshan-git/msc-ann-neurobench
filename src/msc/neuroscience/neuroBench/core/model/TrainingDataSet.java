package msc.neuroscience.neuroBench.core.model;

import msc.neuroscience.neuroBench.core.model.primitive.DataSet;

/**
 *
 * @author Keshan De Silva
 */
public class TrainingDataSet
{
    private DataSet inputDataSet;
    private DataSet outputDataSet;

    public TrainingDataSet(DataSet inputDataSet, DataSet outputDataSet)
    {
        this.inputDataSet = inputDataSet;
        this.outputDataSet = outputDataSet;
    }
    
    public DataSet getInputDataSet()
    {
        return inputDataSet;
    }

    public void setInputDataSet(DataSet inputDataSet)
    {
        this.inputDataSet = inputDataSet;
    }

    public DataSet getOutputDataSet()
    {
        return outputDataSet;
    }

    public void setOutputDataSet(DataSet outputDataSet)
    {
        this.outputDataSet = outputDataSet;
    }
    
    
}
