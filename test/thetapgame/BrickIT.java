/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kraugen
 */
public class BrickIT {
    
    public BrickIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of CreateBrick method, of class Brick.
     */
    @Test
    public void testCreateBrick() {
       
    }

    /**
     * Test of paint method, of class Brick.
     */
    @Test
    public void testPaint() throws IOException {
        System.out.println("paint");
        Graphics g = null;
        Brick brick = new Brick("Steinar","blaa");
        g = brick.getIMG().getGraphics();
        //Width = 62, height = 62;
        brick.paint(g);
        //gr.drawImage(brick.getIMG(), 20, 20,brick);
        Graphics2D g2d = brick.getIMG().createGraphics();
    
   // Draw on the buffered image
        //g2d.setColor(Color.red);
        //g2d.fill(new Ellipse2D.Float(0, 0, 200, 100));
       
        
   
    }
    
}
