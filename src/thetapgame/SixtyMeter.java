/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Kraugen
 */
public class SixtyMeter {
    private ImageIcon img;
    private JFrame f;
    private JLabel lbl;
    private int x;
    private int y;
    
    public SixtyMeter() throws IOException{
        this.f = new JFrame();
        this.img = new ImageIcon(ImageIO.read(new File("60m_background.jpg")));
        this.x = -210;
        this.y = 160;
        createBoard();
        this.lbl = initRunners();
        startSixtyMeter();
    }
    
     public void createBoard() {
        this.f.setContentPane(new JLabel(this.img));
    	this.f.pack();
        this.f.setLayout(null);
        this.f.setResizable(false);
        this.f.setDefaultCloseOperation(0);
        this.f.setVisible(true);
    }
    
    public JLabel initRunners(){
        String imgPath = "runner.gif";
        JLabel runner = new JLabel(new ImageIcon(imgPath));
        runner.setBounds(this.x, this.y, this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(runner);
        runner.setVisible(true);
        this.f.repaint();
        return runner;
    }
    
    
    public void run(){
        this.lbl.setBounds(this.x + 2, this.y, this.img.getIconWidth(), this.img.getIconHeight());
        this.x = this.x + 2;
    }
    
    public void startSixtyMeter() throws IOException{
        JTextField textField = new JTextField();

        textField.addKeyListener((KeyListener) new MKeyListener());

        JFrame jframe = new JFrame();

        jframe.add(textField);

        jframe.setSize(400, 350);

        jframe.setVisible(true);
    }
    
    /*public static void main(String[] args) throws IOException {
        SixtyMeter sixty = new SixtyMeter();
    }*/
    public static void main(String[] args) throws IOException {
        MKeyListener l = new MKeyListener();
    }
}

class MKeyListener extends KeyAdapter {
    private int counter = 0;
    private SixtyMeter sixty;

    MKeyListener() throws IOException {
        this.sixty = new SixtyMeter();
    }
    @Override
    public void keyPressed(KeyEvent event) {

    char ch = event.getKeyChar();

    if (ch == 'r') {
        this.counter++;
        this.sixty.run();
        System.out.println("r");
    }

    if (event.getKeyCode() == KeyEvent.VK_HOME) {
        System.out.println("Key codes: " + event.getKeyCode());
        }
    }
    
}