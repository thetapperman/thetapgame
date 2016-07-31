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
public class PlayerRegistrationGUI extends JFrame{
    private ArrayList<String> players;
    private String regMsg;
    private int numPlayers;
    private String onClose;
    
    JTextField jtfText1, jtfUneditableText;
    JButton okButton;
    String disp = "";
    TextHandler handler = null;
    
    public void setNumPlayers(int num){
        this.numPlayers = num;
    }
    
    public String getRegMsg(){
        return this.regMsg;
    }
    
    public int getNumPlayers(){
        return this.numPlayers;
    }
    
    public ArrayList<String> getPlayers(){
        return this.players;
    }
    private class TextHandler implements ActionListener {
        private String num;
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jtfText1) {
				disp = (e.getActionCommand().substring(0, 1).toUpperCase()+e.getActionCommand().substring(1)) +" registered.";
                                this.num = e.getActionCommand();
                                
                                if(!PlayerRegistrationGUI.this.players.contains(this.num)){
                                    //PlayerRegistrationGUI.this.players.add(this.num);
                                    //Converts first letter to capital letter:
                                    PlayerRegistrationGUI.this.players.add(this.num.substring(0, 1).toUpperCase() + this.num.substring(1));
                                    
                                    System.out.println(this.num);
                                    
                                }
                                
			} else if (e.getSource() == jtfUneditableText) {
				disp = "text3 : " + e.getActionCommand();
			}else if(e.getSource() == okButton){
                            disp = "Registration successful";
                            PlayerRegistrationGUI.this.onClose = "DERJA";
                            setVisible(false);
                        }
                     
			JOptionPane.showMessageDialog(null, disp);
		}
	}
    
    public PlayerRegistrationGUI() {
                
		super("PLAYER REGISTRATION");
                this.onClose="";
                this.players = new ArrayList<String>();
                this.numPlayers=0;
                this.regMsg = "";
                okButton = new JButton("OK");
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
                   
                
		jtfText1 = new JTextField(10);
		jtfUneditableText = new JTextField("welcome to player registration", 17);
		jtfUneditableText.setEditable(false);
                System.out.println(jtfText1.getText());
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
		PlayerRegistrationGUI test = new PlayerRegistrationGUI();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ArrayList<String> p = test.getPlayers();
                test.whileConnected();
                System.out.println("length: "+p.size());
                
	}
}
