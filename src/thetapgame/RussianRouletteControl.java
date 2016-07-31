/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Kraugen
 */
public class RussianRouletteControl extends JFrame{
    private int chosenNumber;
    private int dangerousNumber;
    private String choiceMSG;
    private String onClose;
    
    
    JTextField jtfText1, jtfUneditableText;
    JButton okButton;
    String disp = "";
    TextHandler handler = null;
    
    public int getDangerousNumber(){
        return this.dangerousNumber;
    }
    
    public String getRegMsg(){
        return this.choiceMSG;
    }
    
    public int getChosenNumber(){
        return this.chosenNumber;
    }
    
    private class TextHandler implements ActionListener {
        private String num;
        
            public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == okButton && !jtfText1.getText().equals("")){
                        try{
                            if(Integer.parseInt(jtfText1.getText())<=5 && Integer.parseInt(jtfText1.getText())>=1){
                                disp = "Lets see if you picked a royal card....";
                                RussianRouletteControl.this.chosenNumber = Integer.parseInt(jtfText1.getText());

                                RussianRouletteControl.this.onClose = "DERJA";
                                setVisible(false);
                            }
                        }catch (NumberFormatException ex) {
                            System.out.println("exception converting to int");
                        }
                    }
            }
	}
    
    public RussianRouletteControl(int dangerousNumber) {
                
		super("Choose Card Number!");
                this.onClose="";
                //this.numPlayers=0;
                this.choiceMSG = "";
                this.chosenNumber = 0;
                this.dangerousNumber=dangerousNumber;
                okButton = new JButton("OK");
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
                  
		jtfText1 = new JTextField(10);
		jtfUneditableText = new JTextField("Welcome to Russian Roulette", 17);
		jtfUneditableText.setEditable(false);
                //System.out.println(jtfText1.getText());
                
		container.add(jtfText1);
		container.add(jtfUneditableText);
                container.add(okButton);
		handler = new TextHandler();
		jtfText1.addActionListener(handler);
		jtfUneditableText.addActionListener(handler);
                okButton.addActionListener(handler);
		setSize(325, 100);
		setVisible(true);
                
	}
    
    
    public void whileConnected(){
        System.out.println("message is at first empty: "+this.onClose);
        while (true){
            if(this.onClose==""){
                try {
                        Thread.sleep(1000);                 //1000 milliseconds is one second.
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }else{
                    break;
                }
            }

        }
    
    
    public static void main(String args[]) {
		RussianRouletteControl test = new RussianRouletteControl(1);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                test.whileConnected();
	}
}
