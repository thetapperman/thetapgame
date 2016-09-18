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

    public static void main(String[] args) throws IOException {
       ArrayList<String> playerCredentials = new ArrayList<String>();
        System.out.println("here");
        PlayerRegistrationGUI test = new PlayerRegistrationGUI();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerCredentials = test.getPlayers();
        test.whileConnected();
        String gameName = "knowitGame";
        Board board = new Board(playerCredentials,gameName);
    }
}
