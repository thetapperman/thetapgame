/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kraugen
 */
public class JokerNordControl extends JPanel{
    private String text;
   private String message;
   
   public JokerNordControl(String text){
       this.message = "";
       this.text = text;
       
   }
   
   public String getMsg(){
       return this.message;
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
        JButton btn = new JButton("Down");
        JButton btn2 = new JButton("Up");
        
        int BUTTON_LOCATION_X = 120; 
        int BUTTON_LOCATION_Y = 200;   
        int BUTTON_SIZE_X = 200;      
        int BUTTON_SIZE_Y = 100; 
        
        int x_2 = 120;
        int y_2 = 100;
        int size_x_2 = 200;
        int size_y_2 = 100;
                
        
        btn.setBounds(BUTTON_LOCATION_X,
                      BUTTON_LOCATION_Y,
                      BUTTON_SIZE_X, 
                      BUTTON_SIZE_Y
                     );
        btn2.setBounds(x_2, y_2, size_x_2, size_y_2);
        
        btn.addActionListener(new ActionListener() { 
        
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                JokerNordControl.this.message = "down";
                Window window = SwingUtilities.windowForComponent(button);
                window.setVisible(false);
                } 
            });
        
        btn2.addActionListener(new ActionListener() { 
        
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                JokerNordControl.this.message = "up";
                Window window = SwingUtilities.windowForComponent(button);
                window.setVisible(false);
                } 
            });
        
        textArea.add(btn);
        textArea.add(btn2);
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
   
   public static void main(String[] args) throws IOException {
        JokerNordControl joker = new JokerNordControl("jira");
    }
}
