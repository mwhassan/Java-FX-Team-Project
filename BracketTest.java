// MatchPane.java
// Team.java
// application.css
//
// Testing Files:
// BracketTest.java
// MatchTest.java
// ZeroTeam.txt
// OneTeam.txt
// TwoTeam.txt
// FourTeam.txt
// EightTeam.txt
// SixteenTeam.txt
// ThirtyTwoTeam.txt
//
// Course: CS 400, Spring, 2018
//
// Author - A-Team 9:
// Mostafa Wail Hassan
// Christopher Todd Hayes-Birchler - hayesbirchle@wisc.edu
// Emma He
// Bryan Jin
//
// Lecturer's Name: Deb Deppeler
// Due Date : 4/23/2018 by 10PM
//
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.MatchADT.TeamSpot;

/**
 * Tests the Bracket implementation of BracketADT.
 */
public class BracketTest {
    
    public static Bracket<Integer> bracket;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    @Before
    public void setUp() throws Exception {
        bracket = new Bracket<Integer>();
    }

    @After
    public void tearDown() throws Exception {
        bracket = null;
    }
    
    // Test List
    
    /**************************
     * Testing SeededBrackets
     * 8 input fileNames
     * IOExceptions or seeded order
     **************************/
    @Test
    public final void test01_seededBracket_readInFile() {
        String expected = "IOException";
        String actual = "";
        try {
            bracket.seedBracket("lalala.txt");
            System.out.println("Expected: " + expected + " actual: " + actual);
        } catch(IOException e) {
            actual = "IOException";
            assertEquals(expected, actual);
        }
    }
    
    @Test
    public final void test02_seedeBracket_zeroTeam() throws IOException {
        String expected = "";
        try {
            bracket.seedBracket("ZeroTeam.txt");
            String actual = bracket.toString();
            System.out.println("Expected: " + expected + " actual: " + actual);
        } catch(NullPointerException e) {
            assertEquals(expected, "");
        }
    }
    
    @Test
    public final void test03_seedeBracket_oneTeam() throws IOException {
        bracket.seedBracket("OneTeam.txt");
        String actual = bracket.toString();
        String expected = "";
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test04_seedeBracket_twoTeam() throws IOException {
        bracket.seedBracket("TwoTeam.txt");
        String actual = bracket.toString();
        String expected = "Team1Team2";
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test05_seedeBracket_fourTeam() throws IOException {
        bracket.seedBracket("FourTeam.txt");
        String actual = bracket.toString();
        String expected = "Team1Team4Team2Team3";
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test06_seedeBracket_eightTeam() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String actual = bracket.toString();
        String expected = "Team1Team8Team4Team5Team2Team7Team3Team6";
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test07_seedeBracket_sixTeenTeam() throws IOException {
        bracket.seedBracket("SixteenTeam.txt");
        String actual = bracket.toString();
        String expected = "Team1Team16Team8Team9Team4Team13Team5Team12Team2Team15Team7Team10Team3Team14Team6Team11";
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test08_seedeBracket_ThirttTwoTeam() throws IOException {
        bracket.seedBracket("ThirtyTwoTeam.txt");
        String actual = bracket.toString();
        String expected =  "Team1Team32Team16Team17Team8Team25Team9Team24Team4Team29Team13Team20Team5Team28Team12Team21"
                                        + "Team2Team31Team15Team18Team7Team26Team10Team23Team3Team30Team14Team19Team6Team27Team11Team22";
        assertEquals(expected, actual);
    }
    
    /*************************************
     * Testing size(), matches(), rounds()
     *************************************/
    @Test
    public final void test09_size_zero() throws IOException {
        bracket.seedBracket("ZeroTeam.txt");
        String actual = "" + bracket.size();
        String expected = "" + 0;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test10_size_one() throws IOException {
        bracket.seedBracket("OneTeam.txt");
        String actual = "" + bracket.size();
        String expected = "" + 1;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test11_size_two() throws IOException {
        bracket.seedBracket("TwoTeam.txt");
        String actual = "" + bracket.size();
        String expected = "" + 2;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test12_size_eight() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String actual = "" + bracket.size();
        String expected = "" + 8;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test13_size_sixteen() throws IOException {
        bracket.seedBracket("SixteenTeam.txt");
        String actual = "" + bracket.size();
        String expected = "" + 16;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test14_matches_zero() throws IOException {
        bracket.seedBracket("ZeroTeam.txt");
        String actual = "" + bracket.matches();
        String expected = "" + 0;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test15_matches_zero() throws IOException {
        bracket.seedBracket("OneTeam.txt");
        String actual = "" + bracket.matches();
        String expected = "" + 0;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test16_matches_one() throws IOException {
        bracket.seedBracket("TwoTeam.txt");
        String actual = "" + bracket.matches();
        String expected = "" + 1;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test17_matches_seven() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String actual = "" + bracket.matches();
        String expected = "" + 7;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test18_matches_fifteen() throws IOException {
        bracket.seedBracket("SixteenTeam.txt");
        String actual = "" + bracket.matches();
        String expected = "" + 15;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test19_rounds_zero() throws IOException {
        bracket.seedBracket("ZeroTeam.txt");
        String actual = "" + bracket.rounds();
        String expected = "" + 0;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test20_rounds_one() throws IOException {
        bracket.seedBracket("OneTeam.txt");
        String actual = "" + bracket.rounds();
        String expected = "" + 0;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test21_rounds_two() throws IOException {
        bracket.seedBracket("TwoTeam.txt");
        String actual = "" + bracket.rounds();
        String expected = "" + 1;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test22_rounds_three() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String actual = "" + bracket.rounds();
        String expected = "" + 3;
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test23_rounds_four() throws IOException {
        bracket.seedBracket("SixteenTeam.txt");
        String actual = "" + bracket.rounds();
        String expected = "" + 4;
        assertEquals(expected, actual);
    }
    
    /***********************************
     * public int getMatchIndex(int round, int slot); 
     * public int getMatchRound(int matchIndex);
     * public int getMatchSlot(int matchIndex);
     ***********************************/
    
    @Test
    public final void test24_getMatchIndex_Exception() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String expected = "";
        String actual = "";
        try {
            actual = "" + bracket.getMatchIndex(0, 1);
            assertEquals(expected, actual);
        } catch(IllegalArgumentException e) {
            assertEquals(expected, actual);
        }
    }
    
    @Test
    public final void test25_getMatchIndex_five() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String expected = "" + 5;
        String actual = "" + bracket.getMatchIndex(2, 1);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test26_getMatchIndex_fourteen() throws IOException {
        bracket.seedBracket("SixteenTeam.txt");
        String expected = "" + 14;
        String actual = "" + bracket.getMatchIndex(3, 2);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test27_getMatchIndex_thirty() throws IOException {
        bracket.seedBracket("ThirtyTwoTeam.txt");
        String expected = "" + 30;
        String actual = "" + bracket.getMatchIndex(4, 2);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test28_getMatchRound_two() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String expected = "" + 2;
        String actual = "" + bracket.getMatchRound(5);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test29_getMatchRound_three() throws IOException {
        bracket.seedBracket("SixteenTeam.txt");
        String expected = "" + 3;
        String actual = "" + bracket.getMatchRound(13);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test30_getMatchRound_four() throws IOException {
        bracket.seedBracket("ThirtyTwoTeam.txt");
        String expected = "" + 4;
        String actual = "" + bracket.getMatchRound(29);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test31_getMatchSlot_one() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String expected = "" + 1;
        String actual = "" + bracket.getMatchSlot(5);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test32_getMatchSlot_two() throws IOException {
        bracket.seedBracket("SixteenTeam.txt");
        String expected = "" + 2;
        String actual = "" + bracket.getMatchSlot(10);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test33_getMatchSlot_three() throws IOException {
        bracket.seedBracket("ThirtyTwoTeam.txt");
        String expected = "" + 3;
        String actual = "" + bracket.getMatchSlot(27);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test34_getNextMatch_champion() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String expected = "" + -1;
        String actual = "" + bracket.getNextMatch(7);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test35_getNextMatch_five() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String expected = "" + 5;
        String actual = "" + bracket.getNextMatch(1);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test36_getNextMatch_two() throws IOException {
        bracket.seedBracket("SixteenTeam.txt");
        String expected = "" + 14;
        String actual = "" + bracket.getNextMatch(11);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test37_getNextMatch_three() throws IOException {
        bracket.seedBracket("ThirtyTwoTeam.txt");
        String expected = "" + 27;
        String actual = "" + bracket.getNextMatch(22);
        assertEquals(expected, actual);
    }
    
    /******************************
     * getMatchTeam()
     ******************************/
    
    @Test
    public final void test38_getMatchTeam_null() {
        String expected = null;
        String actual = bracket.getMatchTeam(0, TeamSpot.TeamOne);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test39_getMatchTeam_null() throws IOException {
        bracket.seedBracket("ZeroTeam.txt");
        String expected = null;
        String actual = bracket.getMatchTeam(0, TeamSpot.TeamOne);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test40_getMatchTeam_Team2() throws IOException {
        bracket.seedBracket("TwoTeam.txt");
        String expected = "Team2";
        String actual = bracket.getMatchTeam(1, TeamSpot.TeamTwo);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test41_getMatchTeam_Team2() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String expected = "Team7";
        String actual = bracket.getMatchTeam(3, TeamSpot.TeamTwo);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test42_getMatchTeam_Team2() throws IOException {
        bracket.seedBracket("SixteenTeam.txt");
        String expected = "Team10";
        String actual = bracket.getMatchTeam(6, TeamSpot.TeamTwo);
        assertEquals(expected, actual);
    }
    
    /*********************************
     * getMatchWinner()
     * setMatchScore()
     * getChampion()
     *********************************/
    
    @Test
    public final void test43_setMatchScore_Exception_argu() throws IOException {
        String expected = "";
        String actual = "";
        try {
            bracket.seedBracket("EightTeam.txt");
            bracket.setMatchScore(2, null, 3);
        } catch(IllegalArgumentException e) {
            assertEquals(expected, actual);
        }
    }
    
    @Test
    public final void test44_setMatchScore_Exception_state() throws IOException {
        String expected = "";
        String actual = "";
        try {
            bracket.seedBracket("EightTeam.txt");
            bracket.setMatchScore(2, 6, 3);
            bracket.setMatchScore(2, 4, 2);
        } catch(IllegalStateException e) {
            assertEquals(expected, actual);
        }
    }
    
    @Test
    public final void test45_getMatchWinner_wihout_set_null() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        String expected = null;
        String actual = bracket.getMatchWinner(2);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test46_getMatchWinner_team5() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        bracket.setMatchScore(2, 3, 6);
        String expected = "Team5";
        String actual = bracket.getMatchWinner(2);
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test47_getMatchScore_team5() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        bracket.setMatchScore(2, 3, 6);
        String expected = "Team1Team8Team4Team5Team2Team7Team3Team6Team5";
        String actual = bracket.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public final void test48_getChampion_Exception() throws IOException {
        String expected = "";
        String actual = "";
        try {
            bracket.seedBracket("EightTeam.txt");
            bracket.getChampion();
        } catch(IllegalStateException e) {
            assertEquals(expected, actual);
        }
    }
    
    @Test
    public final void test46_getChampion_team5() throws IOException {
        bracket.seedBracket("EightTeam.txt");
        bracket.setMatchScore(1, 4, 5);
        bracket.setMatchScore(2, 3, 6);
        bracket.setMatchScore(3, 1, 2);
        bracket.setMatchScore(4, 3, 0);
        bracket.setMatchScore(5, 2, 3);
        bracket.setMatchScore(6, 3, 1);
        bracket.setMatchScore(7, 2, 1);
        String expected = "Team5";
        String actual = bracket.getChampion().getName();
        assertEquals(expected, actual);
    }
}