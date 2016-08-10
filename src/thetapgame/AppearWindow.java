/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Kraugen
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class AppearWindow extends JPanel {
   
   private String text;
   private String message;
   
   public AppearWindow(String text){
       this.message = "";
       this.text = text;
       
   }
    
   @Override
   public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
      
      g2.setFont(new Font("Serif", Font.PLAIN, 48));
      paintHorizontallyCenteredText(g2, "Task: ", 200, 75);
      g2.setFont(new Font("Serif", Font.PLAIN, 10));
      paintHorizontallyCenteredText(g2, this.text, 200, 175);
   }
   
   protected void paintHorizontallyCenteredText(Graphics2D g2,String s, float centerX, float baselineY) {
      FontRenderContext frc = g2.getFontRenderContext();
      Rectangle2D bounds = g2.getFont().getStringBounds(s, frc);
      float width = (float) bounds.getWidth();
      g2.drawString(s, centerX - width / 2, baselineY);
   }
   
   public void ShowFrame(boolean show) {
        JFrame f = new JFrame();
        f.getContentPane().add(new AppearWindow(this.text));
        f.setSize(450, 350);
      
        JTextArea textArea = new JTextArea(this.text,6,15);
        textArea.setFont(new Font("Serif", Font.BOLD, 25));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        f.add(textArea);
        JButton btn = new JButton("Done.");
        
        int BUTTON_LOCATION_X = 120; 
        int BUTTON_LOCATION_Y = 200;   
        int BUTTON_SIZE_X = 200;      
        int BUTTON_SIZE_Y = 100;   
        
        btn.setBounds(BUTTON_LOCATION_X,
                      BUTTON_LOCATION_Y,
                      BUTTON_SIZE_X, 
                      BUTTON_SIZE_Y
                     );
        
        btn.addActionListener(new ActionListener() { 
        
            public void actionPerformed(ActionEvent e) { 
                System.out.println("lukke");
                JButton button = (JButton)e.getSource();
                AppearWindow.this.message = "a";
                Window window = SwingUtilities.windowForComponent(button);
                window.setVisible(false);
                } 
            });
        
        textArea.add(btn);
        f.setVisible(show);
   }
   
   public void whileConnected(){
        System.out.println("message is at first empty: "+this.message);
        while (true){
            if(this.message==""){
                try {
                        Thread.sleep(1000);                
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }else{
                    break;
                }
        }

    }
   
   
}