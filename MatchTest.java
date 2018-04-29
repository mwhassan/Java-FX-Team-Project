////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: CS 400 Team GUI Tournament Bracket Project
//
// Program Files:
// Bracket.java
// BracketADT.java
// Main.java
// Match.java
// MatchADT.java
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
///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.MatchADT.TeamSpot;


/**
 * This class performs a unit test on the Match implementation of MatchADT
 */
public class MatchTest {
	
    /**********************
     * Private Constants
     **********************/
    private static Match<Integer> match;
    private static String teamOne = "teamOne";
    private static String teamTwo = "teamTwo";
    private static int TEAM_ONE = 0;
    private static int TEAM_TWO = 1;
    private static boolean SHOW_ALL_RESULTS = true;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        match = new Match<Integer>();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        match = null;
    }

    @Before
    public void setUp() throws Exception {
        match = new Match<Integer>();
    }

    @After
    public void tearDown() throws Exception {
        
    }
    
    /**
     * Check that setTeams adds teams in expected order
     */
    @Test
    public final void test01_TeamsSetAsExpected() {
        String mOut = "";
        
        match.setTeams(new Team("teamOne",1), new Team("teamTwo", 2));
        
        mOut += ("****************");
        mOut += "\n" + ("Test 01");
        mOut += "\n" + (" - Test team names set as expected");
        mOut += "\n" + ("----------------\n");
        mOut += "\n" + (match);
        mOut += "\n" + ("team 1 = " + match.getTeams()[TEAM_ONE].getName());
        mOut += "\n" + ("team 2 = " + match.getTeams()[TEAM_TWO].getName());
        mOut += "\n" + ("****************\n");
        
        
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        
        
        assertEquals(String.format("Team One returned as %s instead of %s", 
                                   match.getTeams()[TEAM_ONE], teamOne),
        			teamOne, match.getTeams()[TEAM_ONE].getName());
        
        assertEquals(String.format("Team Two returned as %s", match.getTeams()[TEAM_TWO]),
                        teamTwo, match.getTeams()[TEAM_TWO].getName());
        
    }
    
    /**
     * Check that setTeams throws illegalArgExc if one of the teams is null
     */
    @Test
    public final void test02_setTeams_NoNullTeams() {
        String mOut = "";

        
        mOut += ("****************");
        mOut += "\n" + ("Test 02");
        mOut += "\n" + (" - Test team names can not be sent in as null");
        mOut += "\n" + ("----------------");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        
        try{
            match.setTeams(new Team("teamOne",1), null);
            assertEquals(String.format("Allowed null team to be added"), true, false);
        } catch (IllegalArgumentException e) {
            mOut += "The following error was returned as expected : " + e.getMessage();
            mOut += ("\n****************\n");
            if (SHOW_ALL_RESULTS) System.out.println(mOut);
        }

    }
    
    /**
     *  Check that addTeam adds in expected order 
     */
    @Test
    public final void test03_addTeam_teamsAddedInOrder(){
        String mOut = "";

        
        mOut += ("****************");
        mOut += "\n" + ("Test 03");
        mOut += "\n" + (" - test addTeam adds as expected");
        mOut += "\n" + ("----------------");
        mOut += "\n Starting " + (match.toString());
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        
        
        
        
                
        match.addTeam(new Team("teamOne",1));
        mOut += "\n team one set to : " + (match.getTeams()[TEAM_ONE].getName());
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        assertEquals(String.format("Team One returned as %s instead of %s", 
                        match.getTeams()[TEAM_ONE].getName(), teamOne),
             teamOne, match.getTeams()[TEAM_ONE].getName());
        

        
        
        match.addTeam(new Team("teamTwo",2));
        mOut += "\n team two set to : " + (match.getTeams()[TEAM_TWO].getName());
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        assertEquals(String.format("Team Two returned as %s", 
                match.getTeams()[TEAM_TWO].getName()),
                     teamTwo, match.getTeams()[TEAM_TWO].getName());
        
        mOut += "\n Final " + (match);
        mOut += "\n" + ("****************\n");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);

    }
    
    /**
     * Check that addTeam throws IllegalArgExc
     */
    @Test
    public final void test04a_addTeam_NoNullTeamsAllowed() {
        String mOut = "";

        
        mOut += ("****************");
        mOut += "\n" + ("Test 04a");
        mOut += "\n" + (" - Test team names can not be sent in as null");
        mOut += "\n" + ("----------------");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        
        try{
            match.addTeam(null);
            assertEquals(String.format("Allowed null team to be added to team one"), 
                    true, false);
        } catch (IllegalArgumentException e) {
            mOut += "The following error was returned as expected (team 1) : " +
                           e.getMessage();
        }
        
        try{
            match.addTeam(null);
            assertEquals(String.format("Allowed null team to be added to team one"), 
                    true, false);
        } catch (IllegalArgumentException e) {
            mOut += "The following error was returned as expected (team 2) : " 
                         + e.getMessage();
        }
        
        mOut += ("\n****************\n");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);

    }
    
	/**
	 * Check that addTeam throws IllStateExc
	 */
    @Test
    public final void test04b_addTeam_OnlyTwoTeamsCanBeAdded() {
        String mOut = "";

        
        mOut += ("****************");
        mOut += "\n" + ("Test 04b");
        mOut += "\n" + (" - Test that add does not allow more than two teams to be added");
        mOut += "\n" + ("----------------");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        
        match.addTeam(new Team("teamOne",1));
        match.addTeam(new Team("teamTwo",2));
        
        try{
            match.addTeam(new Team("teamThree",3));
            assertEquals(String.format("Allowed third team to be added"), true, false);
        } catch (IllegalStateException e) {
            mOut += "The following error was returned as expected : " + e.getMessage();
        }
        
        mOut += "\n Final " + (match);
        mOut += "\n" + ("****************\n");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        
        assertEquals(String.format("Team One returned as %s instead of %s", 
                                    match.getTeams()[TEAM_ONE].getName(), teamOne),
                     teamOne, match.getTeams()[TEAM_ONE].getName());

        assertEquals(String.format("Team Two returned as %s", 
                                    match.getTeams()[TEAM_TWO].getName()),
                     teamTwo, 
                     match.getTeams()[TEAM_TWO].getName());

    }
    
    /**
     * Check that getTeams will return an array with length 2 and one team 
     * at index 1 when only one team is set at spot teamTwo
     */
    @Test
    public final void test05_getTeamsWithOneTeam() {
        String mOut = "";
        
        mOut += ("****************");
        mOut += "\n" + ("Test 05");
        mOut += "\n" + (" - Test that array's length is 2 and contains one team at index 1");
        mOut += "\n" + ("----------------");
        mOut += "\n" + ("    match.addTeam(): add TeamTwo with seed 2 to TeamSpot.TeamTwo");
        match.addTeam(new Team("TeamTwo", 2), TeamSpot.TeamTwo);
        mOut += "\n" + ("    match.getTeams() return array: ");
        Team[] testTeam = match.getTeams();
        mOut += "\n" + ("    length: " + testTeam.length);
        mOut += "\n" + ("    first team is: " + testTeam[0]);
        mOut += "\n" + ("    second team is: " + testTeam[1].getName() +
                " with seed " + testTeam[1].getSeed());
        mOut += "\n" + ("****************");
        
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        
        assertEquals("The length is " + testTeam.length + " The first element is " + testTeam[0]
                + " The second element is " + testTeam[1].getName() + testTeam[1].getSeed(),
                "The length is 2 The first element is null The second element is TeamTwo2");
    }
    
    /**
     * Check that getTeams will return an array with length 2 and two teams
     * when two teams are set
     */
    @Test
    public final void test06_GetTeamsWithTwoTeams() {
        String mOut = "";
        
        mOut += ("****************");
        mOut += "\n" + ("Test 06");
        mOut += "\n" + (" - Test that array's length is 2 and contains two teams");
        mOut += "\n" + ("----------------");
        mOut += "\n" + ("    add two teams to this match: TeamOne seed 1, TeamTwo seed 2");
        match.addTeam(new Team("TeamOne", 1));
        match.addTeam(new Team("TeamTwo", 2));
        mOut += "\n" + ("    getTeams() returns an array: ");
        Team[] test = match.getTeams();
        mOut += "\n" + ("    array length is " + test.length);
        mOut += "\n" + ("    the first team is " + test[0].getName() + 
                " with seed " + test[0].getSeed());
        mOut += "\n" + ("    the second team is " + test[1].getName() +
                " with seed " + test[1].getSeed());
        mOut += "\n" + ("****************");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        
        assertEquals("array length is " + test.length + " the first team is " + test[0].getName()
                + test[0].getSeed()
                + " the second team is " + test[1].getName() + test[1].getSeed(), "array length is 2 "
                        + "the first team is TeamOne1 the second team is TeamTwo2");
    }
    
    /**
     * Check that getTeams will return blank array even if no teams set
     */
    @Test
    public final void test07_GetTeamsWithBlankArray() {
        String mOut = "";

        
        mOut += ("****************");
        mOut += "\n" + ("Test 07");
        mOut += "\n" + (" - Test that array contains null values");
        mOut += "\n" + ("----------------");
        mOut += "\n" + ("    teamOne returns: " + match.getTeams()[TEAM_ONE]);
        mOut += "\n" + ("    teamTwo returns: " + match.getTeams()[TEAM_TWO]);
        mOut += "\n" + ("****************");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        
        //Check team 1 is blank
        assertEquals(String.format("Team one did not return null"),
                        null, 
                        match.getTeams()[TEAM_ONE]);

        //Check team 2 is blank
        assertEquals(String.format("Team two did not return null"),
                        null, 
                        match.getTeams()[TEAM_TWO]);
    }
    
    /**
     * Check that setFinalScore correctly sets teamScores
     */
    @Test
    public final void test08_CheckScoresAddedAndRemovedCorrectly() {
        String mOut = "";
        final Integer TEAM_ONE_SCORE = 27;
        final Integer TEAM_TWO_SCORE = 15;
        
        mOut += ("****************");
        mOut += "\n" + ("Test 08");
        mOut += "\n" + (" - Test that scores are added and removed correctly");
        mOut += "\n" + ("----------------");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        
        
        match.setFinalScore(TEAM_ONE_SCORE, TEAM_TWO_SCORE);
        

        mOut += match;
        mOut += "\nattempting to get final score";
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        
        
        mOut = "t1 score :" + match.getFinalScores().get(0); 
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        
        mOut = "Trying for score two";
        mOut = "t2 score :" + match.getFinalScores().get(1);
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        

        
        //Check team 1 has score of 27
        assertEquals(String.format("Team ones score was not " + TEAM_ONE_SCORE),
        		TEAM_ONE_SCORE, 
                (Integer) match.getFinalScores().get(TEAM_ONE));

        //Check team 2 is blank
        assertEquals(String.format("Team ones score was not " + TEAM_TWO_SCORE),
        		TEAM_TWO_SCORE, 
                (Integer) match.getFinalScores().get(TEAM_TWO));
        
    }
    
    /**
     * Check that setFinalScore throws illArgExc if a score is null
     */
    @Test
    public final void test09_CheckIllArgExcOnSetFinalScoreNull() {
        String mOut = "";
        final Integer TEAM_ONE_SCORE = 27;
        final Integer TEAM_TWO_SCORE = null;
        
        mOut += ("****************");
        mOut += "\n" + ("Test 09");
        mOut += "\n" + (" - Test thaat setFinalScore throws illegal arg exc if score is null");
        mOut += "\n" + ("----------------");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        		
        match.addTeam(new Team("teamOne",1));
        match.addTeam(new Team("teamTwo",2));
        
        
        
        try{
        	match.setFinalScore(TEAM_ONE_SCORE, TEAM_TWO_SCORE);
            assertEquals(String.format("Allowed null score to be added"), true, false);
        } catch (IllegalArgumentException e) {
            mOut += "The following error was returned as expected : " + e.getMessage();
        }
        
    }	
    
    /**
     * Check that setFinalScore throws illArgExc if score is tied
     */
    @Test
    public final void test10_CheckIllArgExcOnSetFinalScoreTied() {
        String mOut = "";
        final Integer TEAM_ONE_SCORE = 27;
        final Integer TEAM_TWO_SCORE = 27;
        
        mOut += ("****************");
        mOut += "\n" + ("Test 10");
        mOut += "\n" + (" - Test that setFinalScore throws illegal arg exc if score is tied");
        mOut += "\n" + ("----------------");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        		
        match.addTeam(new Team("teamOne",1));
        match.addTeam(new Team("teamTwo",2));
        
        
        
        try{
        	match.setFinalScore(TEAM_ONE_SCORE, TEAM_TWO_SCORE);
            assertEquals(String.format("Allowed tied score to be added"), true, false);
        } catch (IllegalArgumentException e) {
            mOut += "The following error was returned as expected : " + e.getMessage();
        }
        
    }
    
    /**
     * Check that setFinalScore throws IllStatException if score is already set
     */
    @Test
    public final void test11_CheckIllStateExcOnReSetFinalScoreTied() {
        String mOut = "";
        final Integer TEAM_ONE_SCORE = 27;
        final Integer TEAM_TWO_SCORE = 15;
        
        mOut += ("****************");
        mOut += "\n" + ("Test 11");
        mOut += "\n" + (" - Test thaat setFinalScore throws illegal state exc if "
                + "score is already set");
        mOut += "\n" + ("----------------");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        		
        match.addTeam(new Team("teamOne",1));
        match.addTeam(new Team("teamTwo",2));
        match.setFinalScore(TEAM_ONE_SCORE, TEAM_TWO_SCORE);
        
        
        try{
        	match.setFinalScore(TEAM_ONE_SCORE, TEAM_TWO_SCORE);
            assertEquals(String.format("Allowed second score to be added"), true, false);
        } catch (IllegalStateException e) {
            mOut += "The following error was returned as expected : " + e.getMessage();
        }
        
    }

    /**
     * Check that getWinner returns the winning team
     */
    @Test
    public final void test12_CheckGetWinnerReturnsCorrectTeam() {
        String mOut = "";
        final Integer TEAM_ONE_SCORE = 27;
        final Integer TEAM_TWO_SCORE = 15;
        
        mOut += ("****************");
        mOut += "\n" + ("Test 12");
        mOut += "\n" + (" - Test thaat getWinner returns correct team");
        mOut += "\n" + ("----------------");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        		
        match.addTeam(new Team("teamOne",1));
        match.addTeam(new Team("teamTwo",2));
        match.setFinalScore(TEAM_ONE_SCORE, TEAM_TWO_SCORE);
        
        
        assertEquals(String.format("Wrong Team Returned"), "teamOne", 
                match.getWinner().getName());
        
        
    }
    
    /**
     * Check that getWinner throws illegalStateException if no score set
     */
    @Test
    public final void test13_CheckIllStateExcOnGetWinnerNoScore() {
        String mOut = "";
        
        mOut += ("****************");
        mOut += "\n" + ("Test 13");
        mOut += "\n" + (" - Test that getWinner throws illegal state exc if no score set");
        mOut += "\n" + ("----------------");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        		
        match.addTeam(new Team("teamOne",1));
        match.addTeam(new Team("teamTwo",2));
        
        
        
        try{
        	System.out.println("Match winner was " + match.getWinner());
            assertEquals(String.format("Returned score despite no scores set"), true, false);
        } catch (IllegalStateException e) {
            mOut += "The following error was returned as expected : " + e.getMessage();
        }
    }
    
    /**
     * Check if getFinalScore return an empty list when no scores set
     */
    @Test
    public final void test14_GetFinalScoreReturnEmptyList() {
        String mOut = "";
        
        mOut += ("****************");
        mOut += "\n" + ("Test 14");
        mOut += "\n" + (" - Test that getFinalScore() returns empty list");
        mOut += "\n" + ("----------------");
        mOut += "\n" + ("getFinalScores return a list with length: "
                                  + match.getFinalScores().size());
        mOut += "\n" + ("The first score is " + match.getFinalScores().get(0));
        mOut += "\n" + ("The second score is " + match.getFinalScores().get(1));
        mOut += "\n" + ("****************");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";    
        
        assertEquals("The first score is " + match.getFinalScores().get(0) + 
                " The second score is " + match.getFinalScores().get(1), 
                          "The first score is null The second score is null");
    }
    
    /**
     * Check if getFinalScore return the correct score after setting
     */
    @Test
    public final void test15_GetFinalScoreReturnCorrectScore() {
        String mOut = "";
        
        mOut += ("****************");
        mOut += "\n" + ("Test 15");
        mOut += "\n" + (" - Test that getFinalScore() returns the correct scores");
        mOut += "\n" + ("----------------");
        match.addTeam(new Team("TeamOne", 1));
        match.addTeam(new Team("TeamTwo", 2));
        mOut += "\n" + ("add two teams to the match");
        match.setFinalScore(5, 3);
        mOut += "\n" + ("set scores in order: 5, 3");
        mOut += "\n" + ("getFinalScores return a list with length: " 
                   + match.getFinalScores().size());
        mOut += "\n" + ("The first score is " + match.getFinalScores().get(0));
        mOut += "\n" + ("The second score is " + match.getFinalScores().get(1));
        mOut += "\n" + ("****************");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";    
        
        assertEquals("The first score is " + match.getFinalScores().get(0) + 
                " The second score is " + match.getFinalScores().get(1), 
                          "The first score is 5 The second score is 3");   
    }
    
    /**
     * Check if getLoser throw IllegalStatementException when no scores set
     */
    @Test
    public final void test16_GetLoserThrowIllegalStatementExceptionNoScores() {
        String mOut = "";
        
        mOut += ("****************");
        mOut += "\n" + ("Test 16");
        mOut += "\n" + (" - Test that getLower() throw"
                + " IllegalStatementException when no scores set");
        mOut += "\n" + ("----------------");
        match.addTeam(new Team("TeamOne", 1));
        match.addTeam(new Team("TeamTwo", 2));
        mOut += "\n" + ("add two teams to the match");
        try {
            mOut += "\n" + ("Call getLoser() without setting scores" + match.getLoser());
            assertEquals(0, 1);
        } catch(IllegalStateException e) {
            assertEquals(1, 1);
            mOut += "\n" + e.getMessage();
        }

        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";    
    }
    
    /**
     * Check if check a null team throw illegalArgumentException, containsTeam()
     */
    @Test
    public final void test17_NullCheckTeamThrowIllegalArgumentException() {
        String mOut = "";
        
        mOut += ("****************");
        mOut += "\n" + ("Test 17");
        mOut += "\n" + (" - Test that check a null team throw"
                + " IllegalStatementException for containsTeam()");
        mOut += "\n" + ("----------------");
        match.addTeam(new Team("TeamOne", 1));
        match.addTeam(new Team("TeamTwo", 2));
        mOut += "\n" + ("add two teams to the match");
        try {
            mOut += "\n" + ("check a null team" + match.containsTeam(null));
            assertEquals(0, 1);
        } catch(IllegalArgumentException e) {
            assertEquals(1, 1);
            mOut += "\n" + e.getMessage();
        }

        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";       
    }
    
    /**
     * Check if containsTeam(TeamOne) return true after TeamOne inserted
     */
    @Test
    public final void test18_ContainsTeamOneReturnTrue() {
        String mOut = "";
        
        mOut += ("****************");
        mOut += "\n" + ("Test 18");
        mOut += "\n" + (" - Test that containsTeam(TeamOne) return the correct team");
        mOut += "\n" + ("----------------");
        match.addTeam(new Team("TeamOne", 1));
        mOut += "\n" + ("Add TeamOne to the match");
        String actual = "" + match.containsTeam(match.getTeams()[0]);
        mOut += "\n" + ("check containsTeam() pass in the object TeamOne returns " + actual);
        mOut += "\n" + ("****************");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";    
        
        assertEquals(actual, "true");
    }
    
    /**
     * Check if getLoser() return the correct Team
     */
    @Test
    public final void test19_GetLoserReturnCorrectTeam() {
        String mOut = "";
        
        mOut += ("****************");
        mOut += "\n" + ("Test 19");
        mOut += "\n" + (" - Test that getLoser() return the correct Team");
        mOut += "\n" + ("----------------");
        match.addTeam(new Team("TeamOne", 1));
        match.addTeam(new Team("TeamTwo", 2));
        mOut += "\n" + ("Add two teams to the match");
        match.setFinalScore(5, 3);
        mOut += "\n" + ("set the final scores in order: 5, 3");
        mOut += "\n" + ("getLoser() returns the team: " + match.getLoser().getName()
                + " with seed " + match.getLoser().getSeed());
        mOut += "\n" + ("****************");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = ""; 
        
        assertEquals("the loser is " + match.getLoser().getName() +
                match.getLoser().getSeed(), "the loser is TeamTwo2");
    }
}