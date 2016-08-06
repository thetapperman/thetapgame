/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author larstoc
 */
public class Teleporter {
    
    private String teleporterBackground;
    private String brokenTeleporterBackground;
    private String teleportationDisplay;
    private Field teleportationCoordinate;
    private boolean teleporterIsBroken;
    private ImageIcon icon;
    private JFrame f;
    
    public Teleporter(){
        Random randomXCoord = new Random();
        Random randomYCoord = new Random();
        randomXCoord = randomXCoord.nextInt(9) + 1;
    }
}
