/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

/**
 *
 * @author Kraugen
 */
public class Util {
    public static void showDialog(String msg){
        AppearWindow maink = new AppearWindow(msg);
        maink.ShowFrame(true);
        maink.whileConnected();
    }
}
