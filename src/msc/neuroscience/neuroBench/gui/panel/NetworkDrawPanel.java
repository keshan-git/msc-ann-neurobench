package msc.neuroscience.neuroBench.gui.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JOptionPane;
import msc.neuroscience.neuroBench.core.ANNEngine;
import msc.neuroscience.neuroBench.core.activationfunction.ActivationFunction;
import msc.neuroscience.neuroBench.core.activationfunction.ThresholdActivationFunction;
import msc.neuroscience.neuroBench.core.model.HiddenNode;
import msc.neuroscience.neuroBench.core.model.InputNode;
import msc.neuroscience.neuroBench.core.model.Link;
import msc.neuroscience.neuroBench.core.model.NeuralNetwork;
import msc.neuroscience.neuroBench.core.model.OutputNode;
import msc.neuroscience.neuroBench.core.model.ProcessingNode;

/**
 *
 * @author Keshan De Silva
 */
public class NetworkDrawPanel extends javax.swing.JPanel
{
    private NeuralNetwork neuralNetwork;
    private ProcessingNode selectedNode;
    
    int nodeRadius;

    int[][] inputLayerCenter;
    int[][] hiddenLayerCenter;
    int[][] outputLayerCenter;
            
    public NetworkDrawPanel()
    {
        initComponents();
    }

    public void setANNEngine(ANNEngine engine)
    {
        this.neuralNetwork = engine.getNeuralNetwork();
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g)
    { 
        super.paintComponent(g); 
        if (neuralNetwork != null)
        {
            int height = getHeight();
            int width = getWidth();

            int inputLayerX = 50;
            int hiddenLayerX = 2 * width / 5;
            int outputLayerX = 4 * width / 5;

            int numberOfInputs = neuralNetwork.getInputLayerNodes().size();
            int numberOfHiddens = neuralNetwork.getHiddenLayerNodes().size();
            int numberOfOutputs = neuralNetwork.getOutputLayerNodes().size();
        
            int inputLayerYGap = height / (int)(1.5 * numberOfInputs + 1);
            int hiddenLayerYGap = height / (int)(1.5 * numberOfHiddens + 1);
            int outputLayerYGap = height / (int)(1.5 * numberOfOutputs + 1);
            nodeRadius = Math.min(Math.min(inputLayerYGap, hiddenLayerYGap),outputLayerYGap);

            inputLayerCenter = new int[numberOfInputs][2];
            hiddenLayerCenter = new int[numberOfHiddens][2];
            outputLayerCenter = new int[numberOfOutputs][2];
            
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            graphics2D.setStroke(new BasicStroke(3));   
            graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 12));
            
            for (int i = 0; i < neuralNetwork.getInputLayerNodes().size(); i ++)
            {
                InputNode inputNode = neuralNetwork.getInputLayerNodes().get(i);
                
                graphics2D.setColor(Color.GRAY);
                graphics2D.fillOval(inputLayerX, inputLayerYGap + (i + 1) * nodeRadius, nodeRadius/2, nodeRadius/2);
                
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawOval(inputLayerX, inputLayerYGap + (i + 1) * nodeRadius, nodeRadius/2, nodeRadius/2);
                
                graphics2D.setColor(Color.MAGENTA);
                graphics2D.drawString("X=" + inputNode.getValue(), inputLayerX, inputLayerYGap + (i + 1) * nodeRadius);
                
                inputLayerCenter[i][0] = inputLayerX + nodeRadius/4;
                inputLayerCenter[i][1] = inputLayerYGap + (i + 1) * nodeRadius + nodeRadius/4;   
            }
            
            for (int i = 0; i < neuralNetwork.getHiddenLayerNodes().size(); i ++)
            {
                HiddenNode hiddenNode = neuralNetwork.getHiddenLayerNodes().get(i);
              
                graphics2D.setColor(Color.GRAY);
                graphics2D.fillOval(hiddenLayerX, hiddenLayerYGap + (i + 1) * nodeRadius, nodeRadius/2, nodeRadius/2);
                
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawOval(hiddenLayerX, hiddenLayerYGap + (i + 1) * nodeRadius, nodeRadius/2, nodeRadius/2);
                
                graphics2D.setColor(Color.RED);
                graphics2D.drawString(String.format("S=%.2f", hiddenNode.getInputValue())
                        , hiddenLayerX, hiddenLayerYGap + (i + 1) * nodeRadius + nodeRadius / 2 + 10);
                
                ActivationFunction activationFunction = hiddenNode.getActivationFunction();
                if (activationFunction instanceof ThresholdActivationFunction)
                {
                    double theta = ((ThresholdActivationFunction)activationFunction).getThresholdValue();
                    graphics2D.drawString(String.format("θ = %.2f", theta), hiddenLayerX, hiddenLayerYGap + (i + 1) * nodeRadius);
                }
                               
                graphics2D.setColor(Color.MAGENTA);
                graphics2D.drawString(String.format("H=%.2f", hiddenNode.getValue()), hiddenLayerX + 2 * nodeRadius / 3,
                                                                    nodeRadius / 3 + hiddenLayerYGap + (i + 1) * nodeRadius);
                
                hiddenLayerCenter[i][0] = hiddenLayerX + nodeRadius/4;
                hiddenLayerCenter[i][1] = hiddenLayerYGap + (i + 1) * nodeRadius + nodeRadius/4; 
            }
                       
            for (int i = 0; i < neuralNetwork.getOutputLayerNodes().size(); i ++)
            {
                OutputNode outputNode = neuralNetwork.getOutputLayerNodes().get(i);
            
                graphics2D.setColor(Color.GRAY);
                graphics2D.fillOval(outputLayerX, outputLayerYGap + (i + 1) * nodeRadius, nodeRadius/2, nodeRadius/2);
                
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawOval(outputLayerX, outputLayerYGap + (i + 1) * nodeRadius, nodeRadius/2, nodeRadius/2);
                
                graphics2D.setColor(Color.RED);
                graphics2D.drawString(String.format("S=%.2f", outputNode.getInputValue())
                        , outputLayerX, outputLayerYGap + (i + 1) * nodeRadius + nodeRadius / 2 + 10);
                
                graphics2D.setColor(Color.RED);
                ActivationFunction activationFunction = outputNode.getActivationFunction();
                if (activationFunction instanceof ThresholdActivationFunction)
                {
                    double theta = ((ThresholdActivationFunction)activationFunction).getThresholdValue();
                    graphics2D.drawString(String.format("θ = %.2f", theta), outputLayerX, outputLayerYGap + (i + 1) * nodeRadius);
                }
                
                graphics2D.setColor(Color.MAGENTA);
                graphics2D.drawString(String.format("Y=%.2f", outputNode.getValue()), outputLayerX + 2 * nodeRadius / 3, outputLayerYGap + (i + 1) * nodeRadius);
                
                outputLayerCenter[i][0] = outputLayerX + nodeRadius/4;
                outputLayerCenter[i][1] = outputLayerYGap + (i + 1) * nodeRadius + nodeRadius/4; 
            }
            
            graphics2D.setStroke(new BasicStroke(2));

            
            if (numberOfHiddens != 0)
            {             
                for (int i = 0; i < numberOfInputs; i++)
                {
                    for (int j = 0; j < numberOfHiddens; j++)
                    {
                        graphics2D.setColor(Color.DARK_GRAY);
                        graphics2D.drawLine(inputLayerCenter[i][0], inputLayerCenter[i][1],
                                                    hiddenLayerCenter[j][0], hiddenLayerCenter[j][1]);
                        
                        graphics2D.setColor(Color.BLUE);
                        Link link = neuralNetwork.getLayerOneConnections().get(numberOfHiddens * i + j);
                        drawWeights(String.format("W=%.2f", link.getWeight()), inputLayerCenter[i][0], inputLayerCenter[i][1],
                                                    hiddenLayerCenter[j][0], hiddenLayerCenter[j][1], graphics2D);
                    }
                }
                
                
                for (int i = 0; i < numberOfHiddens; i++)
                {
                    for (int j = 0; j < numberOfOutputs; j++)
                    {
                        graphics2D.setColor(Color.DARK_GRAY);
                        graphics2D.drawLine(hiddenLayerCenter[i][0], hiddenLayerCenter[i][1],
                                                    outputLayerCenter[j][0], outputLayerCenter[j][1]);
                        
                        graphics2D.setColor(Color.BLUE);
                        Link link = neuralNetwork.getLayerTwoConnections().get(numberOfOutputs * i + j);
                        drawWeights(String.format("W=%.2f", link.getWeight()), hiddenLayerCenter[i][0], hiddenLayerCenter[i][1],
                                                    outputLayerCenter[j][0], outputLayerCenter[j][1], graphics2D);
                    }
                }
            }
            else
            {
                for (int i = 0; i < numberOfInputs; i++)
                {
                    for (int j = 0; j < numberOfOutputs; j++)
                    {
                        graphics2D.setColor(Color.DARK_GRAY);
                        graphics2D.drawLine(inputLayerCenter[i][0], inputLayerCenter[i][1],
                                                    outputLayerCenter[j][0], outputLayerCenter[j][1]);
                        
                        graphics2D.setColor(Color.BLUE);
                        Link link = neuralNetwork.getLayerOneConnections().get(numberOfOutputs * i + j);
                        drawWeights(String.format("W=%.2f", link.getWeight()), inputLayerCenter[i][0], inputLayerCenter[i][1],
                                                    outputLayerCenter[j][0], outputLayerCenter[j][1], graphics2D);
                    }
                }
            }
        }
    }
    
    private void drawWeights(String text, int x1, int y1, int x2, int y2, Graphics2D graphic2D)
    {
        int centerX = (int)(Math.round(x1 + (3.2 * ( x2 - x1 ) / 4)));
        int centerY = (int)(Math.round(y1 + (3.2 * ( y2 - y1 ) / 4)));

        double deg = Math.toDegrees(Math.atan2(centerY - y2, centerX - x2 ) + Math.PI);

        if ((deg > 90) && (deg < 270))
        {
            deg += 180;
        }

        double angle = Math.toRadians(deg);

        Font font = new Font("default", Font.PLAIN, 12);
        FontMetrics fontMetrics = graphic2D.getFontMetrics(font);

        int sw =  fontMetrics.stringWidth(text);

        graphic2D.setFont(font);
        graphic2D.rotate(angle, centerX, centerY);
        graphic2D.drawString(text, centerX - (sw / 2), centerY); 
        graphic2D.rotate(-angle, centerX, centerY);
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

        menuActivationPopup = new javax.swing.JPopupMenu();
        menuEdit = new javax.swing.JMenuItem();

        menuEdit.setText("Edit Activation");
        menuEdit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEditActionPerformed(evt);
            }
        });
        menuActivationPopup.add(menuEdit);

        addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMouseReleased
    {//GEN-HEADEREND:event_formMouseReleased
        if ((evt.isPopupTrigger()) && (neuralNetwork != null))
        {
            selectedNode = getSelectedNode(evt.getX(), evt.getY());
            if (selectedNode != null)
            {
                menuActivationPopup.show(this, evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_formMouseReleased

    private void menuEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuEditActionPerformed
    {//GEN-HEADEREND:event_menuEditActionPerformed
        EditActivationParametersPanel editActivationParametersPanel = new EditActivationParametersPanel();
        editActivationParametersPanel.setActivationFunction(selectedNode.getActivationFunction());
        
        JOptionPane.showMessageDialog(null, editActivationParametersPanel, "Edit Activation Parameters", JOptionPane.PLAIN_MESSAGE);
        
        selectedNode.setActivationFunction(editActivationParametersPanel.getActivationFunction());
        repaint();
    }//GEN-LAST:event_menuEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu menuActivationPopup;
    private javax.swing.JMenuItem menuEdit;
    // End of variables declaration//GEN-END:variables

    private ProcessingNode getSelectedNode(int posX, int posY)
    {
        double minDistance = Double.MAX_VALUE;
        ProcessingNode processingNode = null;
        
        for (int i = 0; i < hiddenLayerCenter.length; i++)
        {
            double distance = getDistance(posX, posY, hiddenLayerCenter[i][0], hiddenLayerCenter[i][1]);
            if ((distance <= nodeRadius / 2) && (distance < minDistance))
            {
                processingNode = neuralNetwork.getHiddenLayerNodes().get(i);
            }
        }

        for (int i = 0; i < outputLayerCenter.length; i++)
        {
            double distance = getDistance(posX, posY, outputLayerCenter[i][0], outputLayerCenter[i][1]);
            if ((distance <= nodeRadius / 2) && (distance < minDistance))
            {
                processingNode = neuralNetwork.getOutputLayerNodes().get(i);
            }
        }
        
        return processingNode;
    }
    
    private double getDistance(int fromPosX, int fromPosY, int toPosX, int toPosY)
    {
        return Math.sqrt(Math.pow(fromPosX - toPosX, 2) + Math.pow(fromPosY - toPosY, 2));
    }
}
