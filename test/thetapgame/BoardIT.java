/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

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
public class BoardIT {
    
    public BoardIT() {
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
     * Test of initDice method, of class Board.
     */
    @Test
    public void testInitDice() {
        //System.out.println("initDice");
        //Board instance = null;
        
    }

    /**
     * Test of subtractFieldsInTask method, of class Board.
     */
    @Test
    public void testSubtractFieldsInTask() {
        
        //her trengs det testing!!!!!
        
        //System.out.println("subtractFieldsInTask");
        Field field_player = new Field(8,1,false,false);
        
        String challenge_1 = "If you have less Tinder matches than 100, move three fields back. If you are in a relationship, move two fields forward. ";
        
        int field_advantage_1 = 3;
        int field_punishment_1 = 0;
        Field taskField = new Field(7,7,true,false);
        Task task_1 = new Task(challenge_1,field_advantage_1,field_punishment_1,taskField);
       
        //String[] players_ = {"kreppen","kraugen"};
        //Board game_board = new Board(players_,"game_10");

        //result.printField();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of printBoard method, of class Board.
     */
    @Test
    public void testPrintBoard() {
        System.out.println("printBoard");
       
    }

    /**
     * Test of movePlayer method, of class Board.
     */
    @Test
    public void testMovePlayer() {
    }

    /**
     * Test of checkIfSix method, of class Board.
     */
    @Test
    public void testCheckIfSix() {
       
    }
    /**
     * Test of moveLadder method, of class Board.
     */
    @Test
    public void testMoveLadder() {
        
    }

    /**
     * Test of moveLadderAdvantage method, of class Board.
     */
    @Test
    public void testMoveLadderAdvantage() {
    }

    /**
     * Test of moveLadderDisAdvantage method, of class Board.
     */
    @Test
    public void testMoveLadderDisAdvantage() {
        
    }
    
}
