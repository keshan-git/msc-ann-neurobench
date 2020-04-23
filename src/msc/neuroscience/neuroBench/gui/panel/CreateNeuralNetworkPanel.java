package msc.neuroscience.neuroBench.gui.panel;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import msc.neuroscience.neuroBench.gui.data.CreateNetworkData;

/**
 *
 * @author Keshan De Silva
 */
public class CreateNeuralNetworkPanel extends JPanel
{

    public CreateNeuralNetworkPanel()
    {
        initComponents();
    }

    public CreateNetworkData getNetworkData()
    {
        CreateNetworkData data = new CreateNetworkData((Integer)spinnerInputNode.getValue(),
                (Integer)spinnerHidden.getValue(), (Integer)spinnerOutput.getValue());
        
        return data;
    }
    
    public void addCreateButtonActionListener(ActionListener listener)
    {
        btnCreate.addActionListener(listener);
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

        lblInput = new javax.swing.JLabel();
        spinnerInputNode = new javax.swing.JSpinner();
        spinnerHidden = new javax.swing.JSpinner();
        lblHidden = new javax.swing.JLabel();
        spinnerOutput = new javax.swing.JSpinner();
        lblOutput = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();

        lblInput.setText("Number of Input Nodes : ");

        spinnerInputNode.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        spinnerHidden.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        lblHidden.setText("Number of Hidden Nodes : ");
        lblHidden.setToolTipText("");

        spinnerOutput.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        lblOutput.setText("Number of Output Nodes : ");
        lblOutput.setToolTipText("");

        btnCreate.setText("Create");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblInput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerInputNode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHidden)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerHidden, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblOutput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblHidden, lblInput, lblOutput});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInput)
                    .addComponent(spinnerInputNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHidden)
                    .addComponent(spinnerHidden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOutput)
                    .addComponent(spinnerOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblInput, spinnerInputNode});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblHidden, spinnerHidden});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblOutput, spinnerOutput});

    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JLabel lblHidden;
    private javax.swing.JLabel lblInput;
    private javax.swing.JLabel lblOutput;
    private javax.swing.JSpinner spinnerHidden;
    private javax.swing.JSpinner spinnerInputNode;
    private javax.swing.JSpinner spinnerOutput;
    // End of variables declaration//GEN-END:variables
}
