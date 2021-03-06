package msc.neuroscience.neuroBench.gui.panel;

import java.awt.event.ItemEvent;
import msc.neuroscience.neuroBench.core.activationfunction.ActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.BinarySigmoidActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.BinaryStepActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.BipolarSigmoidActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.BipolarStepActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.HyperbolicTangentActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.LinearActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.RampElementActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.SigmoidActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.ThresholdActivationFunction;

/**
 *
 * @author Keshan De Silva
 */
public class EditActivationParametersPanel extends javax.swing.JPanel
{
    private ActivationFunction activationFunction;
    
    public EditActivationParametersPanel()
    {
        initComponents();
    }

    public ActivationFunction getActivationFunction()
    {
        switch (comboActivation.getSelectedItem().toString())
        {
            case "Linear" : activationFunction = new LinearActivationFunction(); break;
            case "Binary Step" : activationFunction = new BinaryStepActivationFunction(); break;
            case "Bipolar Step" : activationFunction = new BipolarStepActivationFunction(); break;
            case "Binary Sigmoid" : activationFunction = new BinarySigmoidActivationFunction(); break;
            case "Bipolar Sigmoid" : activationFunction = new BipolarSigmoidActivationFunction(); break;
            case "Ramp Element" : activationFunction = new RampElementActivationFunction(); break;
            case "Hyperbolic Tangent" : activationFunction = new HyperbolicTangentActivationFunction(); break;
        }
        
        if (activationFunction instanceof ThresholdActivationFunction)
        {
            ((ThresholdActivationFunction)activationFunction).setThresholdValue((Double)spinnerThreshold.getValue());
        }
        else if (activationFunction instanceof SigmoidActivationFunction)
        {
            ((SigmoidActivationFunction)activationFunction).setAlpha((Double)spinnerAlpha.getValue());
        }
                
        return activationFunction;
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
        lblActivationFunction = new javax.swing.JLabel();
        comboActivation = new javax.swing.JComboBox();

        lblThreashold.setText("Threshold : ");

        spinnerThreshold.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(0.1d)));

        lblAlpha.setText("Alpha : ");

        spinnerAlpha.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(0.1d)));

        lblActivationFunction.setText("Activation Function : ");

        comboActivation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Linear", "Binary Step", "Bipolar Step", "Binary Sigmoid", "Bipolar Sigmoid", "Ramp Element", "Hyperbolic Tangent" }));
        comboActivation.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                comboActivationItemStateChanged(evt);
            }
        });

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
                        .addComponent(spinnerAlpha, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblActivationFunction)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboActivation, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblActivationFunction, lblAlpha, lblThreashold});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActivationFunction)
                    .addComponent(comboActivation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThreashold)
                    .addComponent(spinnerThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlpha)
                    .addComponent(spinnerAlpha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblActivationFunction, lblThreashold, spinnerThreshold});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblAlpha, spinnerAlpha});

    }// </editor-fold>//GEN-END:initComponents

    private void comboActivationItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_comboActivationItemStateChanged
    {//GEN-HEADEREND:event_comboActivationItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            switch (comboActivation.getSelectedItem().toString())
            {
                case "Linear" : activationFunction = new LinearActivationFunction(); break;
                case "Binary Step" : activationFunction = new BinaryStepActivationFunction(); break;
                case "Bipolar Step" : activationFunction = new BipolarStepActivationFunction(); break;
                case "Binary Sigmoid" : activationFunction = new BinarySigmoidActivationFunction(); break;
                case "Bipolar Sigmoid" : activationFunction = new BipolarSigmoidActivationFunction(); break;
                case "Ramp Element" : activationFunction = new RampElementActivationFunction(); break;
                case "Hyperbolic Tangent" : activationFunction = new HyperbolicTangentActivationFunction(); break;
            }
        }
    }//GEN-LAST:event_comboActivationItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboActivation;
    private javax.swing.JLabel lblActivationFunction;
    private javax.swing.JLabel lblAlpha;
    private javax.swing.JLabel lblThreashold;
    private javax.swing.JSpinner spinnerAlpha;
    private javax.swing.JSpinner spinnerThreshold;
    // End of variables declaration//GEN-END:variables

    public void setActivationFunction(ActivationFunction activationFunction)
    {
        spinnerAlpha.setEnabled(false);
        spinnerThreshold.setEnabled(false);
                
        if (activationFunction instanceof LinearActivationFunction)
        {
            comboActivation.setSelectedItem("Linear");
        }
        else if (activationFunction instanceof BinaryStepActivationFunction)
        {
            comboActivation.setSelectedItem("Binary Step");
        }
        else if (activationFunction instanceof BipolarStepActivationFunction)
        {
            comboActivation.setSelectedItem("Bipolar Step");
        }
        else if (activationFunction instanceof BinarySigmoidActivationFunction)
        {
            comboActivation.setSelectedItem("Binary Sigmoid");
        }
        else if (activationFunction instanceof BipolarSigmoidActivationFunction)
        {
            comboActivation.setSelectedItem("Bipolar Sigmoid");
        }
        else if (activationFunction instanceof RampElementActivationFunction)
        {
            comboActivation.setSelectedItem("Ramp Element");
        }
        else if (activationFunction instanceof HyperbolicTangentActivationFunction)
        {
            comboActivation.setSelectedItem("Hyperbolic Tangent");
        }
        
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
