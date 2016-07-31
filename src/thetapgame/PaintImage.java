/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kraugen
 */
public class PaintImage extends JPanel{
    private BufferedImage image; 
    
    public PaintImage (){ 
        super(); 
        try 
        {                
          this.image = ImageIO.read(new File("bikke_blaa.gif")); 
        } 
        catch (IOException e) 
        { 
          //Not handled. 
        } 
  } 

  public void paintComponent(Graphics g) 
  { 
    g.drawImage(image, 0, 0, null); 
    repaint(); 
  } 
  
  public static void main(String [] args) throws MalformedURLException 
  { 
    JFrame fr = new JFrame("Window"); 
    fr.add(new JLabel(new ImageIcon("brikke_blaa.gif")));
    fr.setSize(500, 500); 
    fr.setVisible(true); 
    
  } 
}
