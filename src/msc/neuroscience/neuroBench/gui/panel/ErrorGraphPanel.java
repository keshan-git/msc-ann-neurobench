package msc.neuroscience.neuroBench.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import msc.neuroscience.neuroBench.core.ANNEngine;
import msc.neuroscience.neuroBench.core.LearningStatusEvent;
import msc.neuroscience.neuroBench.core.LearningStatusListener;
import msc.neuroscience.neuroBench.gui.tablemodel.TrainingDataTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Keshan De Silva
 */
public class ErrorGraphPanel extends javax.swing.JPanel
{
    private TrainingDataTableModel trainingDataTableModel;
    private ArrayList<LearningStatusEvent> dataList;
    private TimeSeries timeSeries;
    private int counter = 0;
    private double totalError;
    
    private ANNEngine engine;
    
    public ErrorGraphPanel()
    {
        initComponents();
        dataList = new ArrayList<>();
        trainingDataTableModel = new TrainingDataTableModel(dataList);
        tableValues.setModel(trainingDataTableModel);
        
        this.timeSeries = new TimeSeries("Error", Second.class);
        generateGraph();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        spliterErrorGraph = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableValues = new javax.swing.JTable();
        panelGraph = new javax.swing.JPanel();

        spliterErrorGraph.setDividerLocation(200);
        spliterErrorGraph.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        tableValues.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {},
                {},
                {},
                {}
            },
            new String []
            {

            }
        ));
        jScrollPane2.setViewportView(tableValues);

        spliterErrorGraph.setTopComponent(jScrollPane2);

        panelGraph.setLayout(new java.awt.BorderLayout());
        spliterErrorGraph.setRightComponent(panelGraph);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spliterErrorGraph, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spliterErrorGraph, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelGraph;
    private javax.swing.JSplitPane spliterErrorGraph;
    private javax.swing.JTable tableValues;
    // End of variables declaration//GEN-END:variables

    public void setANNEngine(ANNEngine engine)
    {
        int dataSetSize = engine.getTrainingDataSet().getInputDataSet().getRowCount();
        dataList.clear();
        timeSeries.clear();
        
        engine.setLearningStatusListener(new LearningStatusListener()
        {
            @Override
            public void onLearningStstusUpdate(LearningStatusEvent event)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (dataList.size() == 1000)
                        {
                            dataList.clear();
                        }
                        dataList.add(event);
                        trainingDataTableModel.fireTableDataChanged();
                    }
                });
                
                if (counter == dataSetSize)
                {

                    Second current = new Second();
                    timeSeries.addOrUpdate(current, totalError / dataSetSize); 
                    totalError = 0;
                    counter = 0;
                }
                else
                {
                    totalError += event.getTotalError();
                    counter++;
                } 
            }
        });
    }
    
    private void generateGraph()
    {
        TimeSeriesCollection dataset = new TimeSeriesCollection(this.timeSeries);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        panelGraph.add(chartPanel, BorderLayout.CENTER);
    }
  
    private JFreeChart createChart(final XYDataset dataset)
    {
        JFreeChart result = ChartFactory.createTimeSeriesChart("Error Level", "Iterations", 
            "Error", dataset, true, true, false);
        
        XYPlot plot = result.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setTickLabelsVisible(false);
        axis = plot.getRangeAxis();
        axis.setAutoRange(true);
       
        result.setBackgroundPaint(new Color(240, 240, 240));
        plot.setBackgroundPaint(new Color(240, 240, 240));
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);
        
        result.removeLegend();
        
        return result;
    }
}
