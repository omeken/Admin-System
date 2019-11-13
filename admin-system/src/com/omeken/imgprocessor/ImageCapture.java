/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omeken.imgprocessor;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Ezenna Charles
 */
public class ImageCapture extends Thread {
    private JLabel jlabel;
    JFrame window = new JFrame("Datatruck webcam panel");
    public ImageCapture(JLabel jLabel){
        this.jlabel =jLabel;
    }
    
    public void run(){
        
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.setViewSize(new Dimension(320, 240));

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);
        
            
        
        
        window.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int count = mouseEvent.getClickCount();
                if (count == 1) {
                    try {
                        File f = new File("cam.png");
                        if(f.exists() && !f.isDirectory()) { 
                            // do something
                            f.delete();
                        }
                        
                        
                        if (webcam != null) {
                            System.out.println("Webcam: " + webcam.getName());
                        } else {
                            System.out.println("No webcam detected");
                        }
                        BufferedImage image = webcam.getImage();
                        
                        // save image to PNG file
                        ImageIO.write(image, "PNG", new File("cam.png"));
                        //Image captured. Now display on jLabel
                        jlabel.repaint();
                        jlabel.setText("");
                        ImageIcon icon = new ImageIcon("cam.png");
                        jlabel.setIcon(icon);
                    } catch (IOException ex) {
                        
                        Logger.getLogger(ImageCapture.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        window.add(panel);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    public void loadFrame() {
        window.setVisible(true);
    }
    
}
