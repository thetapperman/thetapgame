/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Kraugen
 */
public class TheTapGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       ArrayList<String> playerCredentials = new ArrayList<String>();
        //String[] players = ;
        /*Scanner sc = new Scanner(System.in);
        System.out.println("Antall spillere: ");
        int numPlayers = sc.nextInt();
        
        for(int playerNr = 0;playerNr<numPlayers;playerNr++){
            System.out.println("Navn på spiller nr"+(playerNr+1)+":");
            String name = sc.next();
            playerCredentials.add(name);
        }*/
        
        /*for(int name = 0;name<playerCredentials.size();name++){
            System.out.println("Credentials: "+playerCredentials.get(name));
        }*/
        PlayerRegistrationGUI test = new PlayerRegistrationGUI();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerCredentials = test.getPlayers();
        test.whileConnected();
        String gameName = "knowitGame";
        Board board = new Board(playerCredentials,gameName);
        
        //board.printBoard();
        //board.printBoard();
        /*Field ladderField = new Field(5,3,false,false);
        Ladder ladder = new Ladder("small_ladder", new Field(0,0,false,false),new Field(0,1,false,false), ladderField);
        
        Field field_player = new Field(8,9,false,false);
        Field ex = new Field(4,9,false,false);
        int diceValue = 6;
        
        Field result = board.calculateGoal(diceValue, field_player);
        
        field_player.setMoveDir("right");
        String challenge_1 = "If you have less Tinder matches than 100, move three fields back. If you are in a relationship, move two fields forward. ";
        
        int field_advantage_1 = 0;
        int field_punishment_1 = 1;
        Field taskField = new Field(1,2,true,false);
        Task task_1 = new Task(challenge_1,field_advantage_1,field_punishment_1,taskField);
        
        System.out.println("____________FIELD CALCUATED___________");
        //Field result = board.SubtractFieldsInTask(field_player, task_1);
        //Field result = board.movePlayer(diceValue,field_player);
        //Field result = board.moveLadder(ladderField, ladder);
        
        System.out.println("___________________________");
        if(result.getXValue() == ex.getXValue() && result.getYValue()==ex.getYValue()){
            System.out.println("Correct");
        }else{
            System.out.println("Wrong.");
            result.printField();
        }
        result.printField();*/
        
        
        
        
    }
    
}
