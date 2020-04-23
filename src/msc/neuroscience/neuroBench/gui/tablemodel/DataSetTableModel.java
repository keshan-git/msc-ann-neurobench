package msc.neuroscience.neuroBench.gui.tablemodel;

import javax.swing.table.AbstractTableModel;
import msc.neuroscience.neuroBench.core.model.primitive.DataSet;

/**
 *
 * @author Keshan De Silva
 */
public class DataSetTableModel extends AbstractTableModel
{
    private DataSet dataSet;

    public DataSetTableModel(DataSet dataSet)
    {
        this.dataSet = dataSet;
    }
    
    @Override
    public int getRowCount()
    {
        return dataSet.getRowCount();
    }

    @Override
    public int getColumnCount()
    {
        return dataSet.getColumnCount() + 1;
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        if (column == 0)
        {
            return row;
        }
        else
        {
            return dataSet.getDataAt(row, column + 1);
        }
    }

    @Override
    public void setValueAt(Object value, int row, int column)
    {
        if (column != 0)
        {
            dataSet.setDataAt(row, column, (Double)value);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return column != 0;
    }

    @Override
    public Class<?> getColumnClass(int column)
    {
        return double.class;
    }

    @Override
    public String getColumnName(int column)
    {
        if (column != 0)
        {
            return "Input " + column;
        }
        return "";
    }  
}
