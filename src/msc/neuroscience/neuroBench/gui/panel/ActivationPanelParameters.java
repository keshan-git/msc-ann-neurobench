package msc.neuroscience.neuroBench.gui.panel;

import msc.neuroscience.neuroBench.core.activationfunction.ActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.SigmoidActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.ThresholdActivationFunction;

/**
 *
 * @author Keshan De Silva
 */
public class ActivationPanelParameters extends javax.swing.JPanel
{

    private double threshold;
    private double alpha;
    
    public ActivationPanelParameters()
    {
        initComponents();
    }

    public double getThreshold()
    {
        this.threshold = (Double)spinnerThreshold.getValue();
        return threshold;
    }

    public void setThreshold(double threshold)
    {
        this.threshold = threshold;
        spinnerThreshold.setValue(threshold);
    }

    public double getAlpha()
    {
        this.alpha = (Double)spinnerAlpha.getValue();
        return alpha;
    }

    public void setAlpha(double alpha)
    {
        this.alpha = alpha;
        spinnerAlpha.setValue(threshold);
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

        lblThreashold = new javax.swing.JLabel();
        spinnerThreshold = new javax.swing.JSpinner();
        lblAlpha = new javax.swing.JLabel();
        spinnerAlpha = new javax.swing.JSpinner();

        lblThreashold.setText("Threshold : ");

        spinnerThreshold.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(0.1d)));

        lblAlpha.setText("Alpha : ");

        spinnerAlpha.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(0.1d)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblThreashold)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAlpha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerAlpha, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblAlpha, lblThreashold});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThreashold)
                    .addComponent(spinnerThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlpha)
                    .addComponent(spinnerAlpha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblThreashold, spinnerThreshold});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblAlpha, spinnerAlpha});

    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAlpha;
    private javax.swing.JLabel lblThreashold;
    private javax.swing.JSpinner spinnerAlpha;
    private javax.swing.JSpinner spinnerThreshold;
    // End of variables declaration//GEN-END:variables

    public void setActivationFunction(ActivationFunction activationFunction)
    {
        spinnerAlpha.setEnabled(false);
        spinnerThreshold.setEnabled(false);
        
        if (activationFunction instanceof ThresholdActivationFunction)
        {
            spinnerThreshold.setEnabled(true);
            spinnerThreshold.setValue(((ThresholdActivationFunction)activationFunction).getThresholdValue());
        }
        
		if (activationFunction instanceof SigmoidActivationFunction)
        {
            spinnerAlpha.setEnabled(true);
            spinnerAlpha.setValue(((SigmoidActivationFunction)activationFunction).getAlpha());
        }
    }
}