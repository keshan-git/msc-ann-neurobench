package msc.neuroscience.neuroBench.core.model.primitive;

/**
 *
 * @author Keshan De Silva
 */
public class DataSet
{
    private double [][] dataSet;
    private int rowCount;
    private int columnCount;

    public DataSet(int rowCount, int columnCount)
    {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.dataSet = new double[columnCount][rowCount];
    }
    
    public double getDataAt(int row, int column)
    {
        return dataSet[column][row];
    }
    
    public DataSet getDataSetAt(int row)
    {
        DataSet rowDataSet = new DataSet(1, columnCount);
        for (int i = 0; i < columnCount; i++)
        {
            rowDataSet.setDataAt(0, i, getDataAt(row, i));
        }
        
        return rowDataSet;
    }
        
    public void setDataAt(int row, int column, double data)
    {
        dataSet[column][row] = data;
    }

    public int getRowCount()
    {
        return rowCount;
    }

    public int getColumnCount()
    {
        return columnCount;
    }

    public DataSet different(DataSet dataSet)
    {
        DataSet result = new DataSet(rowCount, columnCount);
        
        for (int i = 0; i < rowCount; i++)
        {
            for (int j = 0; j < columnCount; j++)
            {
                result.setDataAt(i, j, this.getDataAt(i, j) - dataSet.getDataAt(i, j));
            }
        }
        
        return result;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < rowCount; i++)
        {
            stringBuilder.append("[ ");
            for (int j = 0; j < columnCount; j++)
            {
                stringBuilder.append(getDataAt(i, j)).append(" ");
            }
            stringBuilder.append("]").append("\n");
        }
        
        return stringBuilder.toString();
    }
    
    
}
