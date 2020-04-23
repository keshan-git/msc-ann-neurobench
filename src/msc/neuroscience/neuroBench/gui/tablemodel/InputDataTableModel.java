package msc.neuroscience.neuroBench.gui.tablemodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import msc.neuroscience.neuroBench.core.LearningStatusEvent;
import msc.neuroscience.neuroBench.core.model.TrainingDataSet;
import msc.neuroscience.neuroBench.core.model.primitive.DataSet;

/**
 *
 * @author Keshan De Silva
 */
public class InputDataTableModel extends AbstractTableModel
{
    private TrainingDataSet trainingDataSet;
    private ArrayList<String> columnNames;
    
    public InputDataTableModel(TrainingDataSet trainingDataSet)
    {
        this.trainingDataSet = trainingDataSet;
        this.columnNames = new ArrayList<>();
        generateColumnNames();
    }

    @Override
    public String getColumnName(int i)
    {
        if (columnNames != null)
        {
            return columnNames.get(i);
        }
        
        return "";
        
    }

    @Override
    public int getRowCount()
    {
        return trainingDataSet.getInputDataSet().getRowCount();
    }

    @Override
    public int getColumnCount()
    {
        if (columnNames != null)
        {
            return columnNames.size();
        }
        
        return 0;
    }

    @Override
    public Class<?> getColumnClass(int i)
    {
        return Double.class;
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        DataSet inputDataSet = trainingDataSet.getInputDataSet();
        DataSet outputDataSet = trainingDataSet.getOutputDataSet();

        if (column < inputDataSet.getColumnCount())
        {
            return inputDataSet.getDataAt(row, column);
        }
        else if (column < (inputDataSet.getColumnCount() + outputDataSet.getColumnCount()))
        {
            return outputDataSet.getDataAt(row, column - inputDataSet.getColumnCount());
        }
        else
        {
            return "";
        }
    }

    private void generateColumnNames()
    {
        columnNames.clear();
        
        DataSet inputDataSet = trainingDataSet.getInputDataSet();
        for (int i = 0; i < inputDataSet.getColumnCount(); i++)
        {
            columnNames.add("X" + (i + 1));
        }

        DataSet outputDataSet = trainingDataSet.getOutputDataSet();
        for (int i = 0; i < outputDataSet.getColumnCount(); i++)
        {
            columnNames.add("Y" + (i + 1));
        }

        fireTableStructureChanged();
    }
  
}
