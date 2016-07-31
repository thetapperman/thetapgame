/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.util.ArrayList;
import java.util.HashMap;
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
public class GameBoardIT {
    
    public GameBoardIT() {
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
     * Test of paintDice method, of class GameBoard.
     */
    @Test
    public void testPaintDice() {
        System.out.println("paintDice");
        int diceNum = 0;
        GameBoard instance = null;
        instance.paintDice(diceNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paintChallenges method, of class GameBoard.
     */
    @Test
    public void testPaintChallenges() {
        System.out.println("paintChallenges");
        HashMap<Field, String> challengeMap = null;
        GameBoard instance = null;
        instance.paintChallenges(challengeMap);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initCoordinatesHashMap method, of class GameBoard.
     */
    @Test
    public void testInitCoordinatesHashMap() {
        System.out.println("initCoordinatesHashMap");
        GameBoard instance = null;
        instance.initCoordinatesHashMap();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CreateBoard method, of class GameBoard.
     */
    @Test
    public void testCreateBoard() {
        System.out.println("CreateBoard");
        GameBoard instance = null;
        instance.CreateBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBricks method, of class GameBoard.
     */
    @Test
    public void testGetBricks() {
        System.out.println("getBricks");
        GameBoard instance = null;
        ArrayList<Brick> expResult = null;
        ArrayList<Brick> result = instance.getBricks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPieceOnField method, of class GameBoard.
     */
    @Test
    public void testSetPieceOnField() {
        System.out.println("setPieceOnField");
        ArrayList<Integer> c = null;
        String player = "";
        GameBoard instance = null;
        instance.setPieceOnField(c, player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of movePiece method, of class GameBoard.
     */
    @Test
    public void testMovePiece() {
        System.out.println("movePiece");
        String player = "";
        Field toField = null;
        GameBoard instance = null;
        instance.movePiece(player, toField);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUpdateMoveDir method, of class GameBoard.
     */
    @Test
    public void testGetUpdateMoveDir() {
        System.out.println("getUpdateMoveDir");
        Field field = null;
        GameBoard instance = null;
        String expResult = "";
        String result = instance.getUpdateMoveDir(field);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveSameRow method, of class GameBoard.
     */
    @Test
    public void testMoveSameRow() {
        System.out.println("moveSameRow");
        Field fromField = null;
        int brickNr = 0;
        Field toField = null;
        JLabel l = null;
        GameBoard instance = null;
        instance.moveSameRow(fromField, brickNr, toField, l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateNumFieldsToMove method, of class GameBoard.
     */
    @Test
    public void testCalculateNumFieldsToMove() {
        System.out.println("calculateNumFieldsToMove");
        Field fromField = null;
        Field toField = null;
        GameBoard instance = null;
        int expResult = 0;
        int result = instance.calculateNumFieldsToMove(fromField, toField);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveNotSameRow method, of class GameBoard.
     */
    @Test
    public void testMoveNotSameRow() {
        System.out.println("moveNotSameRow");
        Field fromField = null;
        int brickNr = 0;
        Field toField = null;
        JLabel l = null;
        GameBoard instance = null;
        instance.moveNotSameRow(fromField, brickNr, toField, l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveGoal method, of class GameBoard.
     */
    @Test
    public void testMoveGoal() {
        System.out.println("moveGoal");
        Field fromField = null;
        String player = "";
        Field toField = null;
        int dice = 0;
        GameBoard instance = null;
        instance.moveGoal(fromField, player, toField, dice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPiecesStart method, of class GameBoard.
     */
    @Test
    public void testSetPiecesStart() {
        System.out.println("setPiecesStart");
        GameBoard instance = null;
        instance.setPiecesStart();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initPieces method, of class GameBoard.
     */
    @Test
    public void testInitPieces() throws Exception {
        System.out.println("initPieces");
        GameBoard instance = null;
        ArrayList<Brick> expResult = null;
        ArrayList<Brick> result = instance.initPieces();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveLadder method, of class GameBoard.
     */
    @Test
    public void testMoveLadder() {
        System.out.println("moveLadder");
        Field fromField = null;
        String player = "";
        Field toField = null;
        GameBoard instance = null;
        instance.moveLadder(fromField, player, toField);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printBricks method, of class GameBoard.
     */
    @Test
    public void testPrintBricks() {
        System.out.println("printBricks");
        GameBoard instance = null;
        instance.printBricks();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printHashMapCoordinates method, of class GameBoard.
     */
    @Test
    public void testPrintHashMapCoordinates() {
        System.out.println("printHashMapCoordinates");
        GameBoard instance = null;
        instance.printHashMapCoordinates();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHashMap method, of class GameBoard.
     */
    @Test
    public void testGetHashMap() {
        System.out.println("getHashMap");
        GameBoard instance = null;
        HashMap<ArrayList<Integer>, ArrayList<Integer>> expResult = null;
        HashMap<ArrayList<Integer>, ArrayList<Integer>> result = instance.getHashMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
