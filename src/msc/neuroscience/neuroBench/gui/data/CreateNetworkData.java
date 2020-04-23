package msc.neuroscience.neuroBench.gui.data;

/**
 *
 * @author Keshan De Silva
 */
public class CreateNetworkData
{
    private int inputNodeCount;
    private int hiddenNodeCount;
    private int outputNodeCount;

    public CreateNetworkData(int inputNodeCount, int hiddenNodeCount, int outputNodeCount)
    {
        this.inputNodeCount = inputNodeCount;
        this.hiddenNodeCount = hiddenNodeCount;
        this.outputNodeCount = outputNodeCount;
    }

    public int getInputNodeCount()
    {
        return inputNodeCount;
    }

    public void setInputNodeCount(int inputNodeCount)
    {
        this.inputNodeCount = inputNodeCount;
    }

    public int getHiddenNodeCount()
    {
        return hiddenNodeCount;
    }

    public void setHiddenNodeCount(int hiddenNodeCount)
    {
        this.hiddenNodeCount = hiddenNodeCount;
    }

    public int getOutputNodeCount()
    {
        return outputNodeCount;
    }

    public void setOutputNodeCount(int outputNodeCount)
    {
        this.outputNodeCount = outputNodeCount;
    }
    
    public boolean isSingleLayerNetwork()
    {
        return hiddenNodeCount == 0;
    }
}
