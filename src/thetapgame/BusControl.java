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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Kraugen
 */
public class BusControl extends JFrame{
    private int cardNumber;
    private String choiceMSG;
    private String onClose;
    private int rowCounter;
    
    
    JTextField jtfText1, jtfUneditableText;
    JButton okButton;
    String disp = "";
    TextHandler handler = null;
    
    public int getRowCounter(){
        return this.rowCounter;
    }
    
    public void setRowCounter(int rowCounter){
        this.rowCounter = rowCounter;
    }
    
    public String getRegMsg(){
        return this.choiceMSG;
    }
    
    public int getCardNumber(){
        return this.cardNumber;
    }
    
    public int cardNumber(){
        return this.cardNumber;
    }
    
    private class TextHandler implements ActionListener {
        private String num;
        
            public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == okButton && !jtfText1.getText().equals("")){
                        try{
                            if(Integer.parseInt(jtfText1.getText())<16 && Integer.parseInt(jtfText1.getText())>0 && validateRowCounter(Integer.parseInt(jtfText1.getText()))==true){
                                disp = "Lets see if you picked a royal card....";
                                BusControl.this.cardNumber = Integer.parseInt(jtfText1.getText());

                                BusControl.this.onClose = "DERJA";
                                setVisible(false);
                            }
                        }catch (NumberFormatException ex) {
                            System.out.println("exception converting to int");
                        }
                        
                        
                    }
            }
	}
    
    public BusControl(int rowCounter) {
                
		super("Choose Card Number!");
                this.onClose="";
                //this.numPlayers=0;
                this.choiceMSG = "";
                this.cardNumber = 0;
                this.rowCounter=rowCounter;
                okButton = new JButton("OK");
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
                  
		jtfText1 = new JTextField(10);
		jtfUneditableText = new JTextField("Welcome to BusRoute", 17);
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
    
    public boolean validateRowCounter(int cardChoice){
        if(this.rowCounter == 1){
            System.out.println("in");
            System.out.println(cardChoice);
            return (cardChoice >=1 && cardChoice <=5);
        }else if(this.rowCounter==2){
            return (cardChoice >=6 && cardChoice <=9);
        }else if(this.rowCounter == 3){
            return (cardChoice >=10 && cardChoice <=12);
        }else if(this.rowCounter == 4){
            return (cardChoice >=13 && cardChoice <=14);
        }else if(this.rowCounter==5){
            return cardChoice==15;
        }
        return false;
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
		BusControl test = new BusControl(1);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                test.whileConnected();
                
                //int cardNum = test.getCardNumber();
                //System.out.println("cardnum:: "+cardNum);
	}
}
