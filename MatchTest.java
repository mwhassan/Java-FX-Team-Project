package application;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MatchTest {

    private static Match<Integer> match;
    private static String teamOne = "teamOne";
    private static String teamTwo = "teamTwo";
    private static String teamOneSeeded = "teamOne (1)";
    private static String teamTwoSeeded = "teamTwo (2)";
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
    
    // Test List
    
    //  1) Check that setTeams adds teams in expected order
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
    
    //  2) Check that setTeams throws illegalArgExc if one of the teams is null
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
    //  3) Check that addTeam adds in expected order 
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
        assertEquals(String.format("Team Two returned as %s", match.getTeams()[TEAM_TWO].getName()),
                     teamTwo, match.getTeams()[TEAM_TWO].getName());
        
        mOut += "\n Final " + (match);
        mOut += "\n" + ("****************\n");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);

    }
    
    //  4) Check that addTeam throws IllegalArgExc
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
            assertEquals(String.format("Allowed null team to be added to team one"), true, false);
        } catch (IllegalArgumentException e) {
            mOut += "The following error was returned as expected (team 1) : " + e.getMessage();
        }
        
        try{
            match.addTeam(null);
            assertEquals(String.format("Allowed null team to be added to team one"), true, false);
        } catch (IllegalArgumentException e) {
            mOut += "The following error was returned as expected (team 2) : " + e.getMessage();
        }
        
        mOut += ("\n****************\n");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);

    }
    
//  4b) Check that addTeam throws IllStateExc
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
    
    //  5) Check that getTeams returns the correct teams in expected order
    //     - Taking this out because we already confirm it when we check addTeams
    
    //  6) Check that getTeams will return blank array even if no teams set
    @Test
    public final void test06_GetTeamsWithBlankArray() {
        String mOut = "";

        
        mOut += ("****************");
        mOut += "\n" + ("Test 06");
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
    
    //  7) Check that setFinalScore correctly sets teamScores
    @Test
    public final void test07_CheckScoresAddedAndRemovedCorrectly() {
        String mOut = "";
        final Integer TEAM_ONE_SCORE = 27;
        final Integer TEAM_TWO_SCORE = 15;
        
        mOut += ("****************");
        mOut += "\n" + ("Test 07");
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
    
    //  8) Check that setFinalScore throws illArgExc if a score is null
    @Test
    public final void test08_CheckIllArgExcOnSetFinalScoreNull() {
        String mOut = "";
        final Integer TEAM_ONE_SCORE = 27;
        final Integer TEAM_TWO_SCORE = null;
        
        mOut += ("****************");
        mOut += "\n" + ("Test 08");
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
    
    //  9) Check that setFinalScore throws illArgExc if score is tied
    @Test
    public final void test09_CheckIllArgExcOnSetFinalScoreTied() {
        String mOut = "";
        final Integer TEAM_ONE_SCORE = 27;
        final Integer TEAM_TWO_SCORE = 27;
        
        mOut += ("****************");
        mOut += "\n" + ("Test 09");
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
    
    //  10) Check that setFinalScore throws IllStatException if score is already set
    @Test
    public final void test10_CheckIllStateExcOnReSetFinalScoreTied() {
        String mOut = "";
        final Integer TEAM_ONE_SCORE = 27;
        final Integer TEAM_TWO_SCORE = 15;
        
        mOut += ("****************");
        mOut += "\n" + ("Test 10");
        mOut += "\n" + (" - Test thaat setFinalScore throws illegal state exc if score is already set");
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

    //  12) Check that getWinner returns the winning team
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
        
        
        assertEquals(String.format("Wrong Team Returned"), "teamOne", match.getWinner().getName());
        
        
    }
    
    //  13) Check that getWinner throws illegalStateException if no score set
    @Test
    public final void test13_CheckIllStateExcOnGetWinnerNoScore() {
        String mOut = "";
        final Integer TEAM_ONE_SCORE = 27;
        final Integer TEAM_TWO_SCORE = 15;
        
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
}
