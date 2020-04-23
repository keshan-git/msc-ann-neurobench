package msc.neuroscience.neuroBench.gui.tablemodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import msc.neuroscience.neuroBench.core.LearningStatusEvent;
import msc.neuroscience.neuroBench.core.model.primitive.DataSet;

/**
 *
 * @author Keshan De Silva
 */
public class TrainingDataTableModel extends AbstractTableModel
{
    private ArrayList<LearningStatusEvent> dataList;
    private ArrayList<String> columnNames;
    
    public TrainingDataTableModel(ArrayList<LearningStatusEvent> dataList)
    {
        this.dataList = dataList;
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
        return dataList.size();
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
        LearningStatusEvent event = dataList.get(row);
        DataSet inputDataSet = event.getInput();
        DataSet desiredOutputDataSet = event.getDesiredOutput();
        DataSet actualOutputDataSet = event.getActualOutput();
        DataSet errorOutputDataSet = event.getErrorOutput();
        
        if (columnNames.size() > 1)
        {
            if (column < inputDataSet.getColumnCount())
            {
                return inputDataSet.getDataAt(0, column);
            }
            else if (column < (inputDataSet.getColumnCount() + desiredOutputDataSet.getColumnCount()))
            {
                return desiredOutputDataSet.getDataAt(0, column - inputDataSet.getColumnCount());
            }
            else if (column < (inputDataSet.getColumnCount() + desiredOutputDataSet.getColumnCount()
                    + actualOutputDataSet.getColumnCount()))
            {
                return actualOutputDataSet.getDataAt(0, column - (inputDataSet.getColumnCount() 
                        + desiredOutputDataSet.getColumnCount()));
            }
            else if (column < (inputDataSet.getColumnCount() + desiredOutputDataSet.getColumnCount()
                    + actualOutputDataSet.getColumnCount() + errorOutputDataSet.getColumnCount()))
            {
                return errorOutputDataSet.getDataAt(0, column - (inputDataSet.getColumnCount() 
                        + desiredOutputDataSet.getColumnCount() + actualOutputDataSet.getColumnCount()));
            }
            else
            {
                return event.getTotalError();
            }
        }
        else
        {
            return "";
        }
    }

    @Override
    public void fireTableDataChanged()
    {
        generateColumnNames();
        super.fireTableDataChanged();
    }

    
    private void generateColumnNames()
    {
        if (dataList.isEmpty())
        {
            columnNames.add("No Data Found");
        }
        else if (columnNames.size() == 1)
        {
            columnNames.clear();
            LearningStatusEvent event = dataList.get(0);
            DataSet inputDataSet = event.getInput();
            for (int i = 0; i < inputDataSet.getColumnCount(); i++)
            {
                columnNames.add("X" + (i + 1));
            }
            
            DataSet desiredOutputDataSet = event.getDesiredOutput();
            for (int i = 0; i < desiredOutputDataSet.getColumnCount(); i++)
            {
                columnNames.add("D" + (i + 1));
            }
            
            DataSet actualOutputDataSet = event.getActualOutput();
            for (int i = 0; i < actualOutputDataSet.getColumnCount(); i++)
            {
                columnNames.add("Y" + (i + 1));
            }
            
            DataSet errorOutputDataSet = event.getErrorOutput();
            for (int i = 0; i < errorOutputDataSet.getColumnCount(); i++)
            {
                columnNames.add("E" + (i + 1));
            }
            
            columnNames.add("Error");
            fireTableStructureChanged();
        }
    }
    
}
