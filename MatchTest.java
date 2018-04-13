package application;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MatchTest {

    private static Match<Integer> match;
    private static String teamOneName = "teamOne";
    private static String teamTwoName = "teamTwo";
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
        mOut += "\n" + (match.getTeams()[TEAM_ONE]);
        mOut += "\n" + (match.getTeams()[TEAM_TWO]);
        mOut += "\n" + ("****************\n");
        
        
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        
        
        assertEquals(String.format("Team One returned as %s instead of %s", 
                                   match.getTeams()[TEAM_ONE], teamOneSeeded),
                        teamOneSeeded, match.getTeams()[TEAM_ONE].toString());
        
        assertEquals(String.format("Team Two returned as %s", match.getTeams()[TEAM_TWO]),
                        teamTwoSeeded, match.getTeams()[TEAM_TWO].toString());
        
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
        mOut += "\n Starting " + (match);
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        
        
        
        
                
        match.addTeam(new Team("teamOne",1));
        mOut += "\n team one set to : " + (match.getTeams()[TEAM_ONE]);
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        assertEquals(String.format("Team One returned as %s instead of %s", 
                        match.getTeams()[TEAM_ONE], teamOneSeeded),
             teamOneSeeded, match.getTeams()[TEAM_ONE].toString());
        

        
        
        match.addTeam(new Team("teamTwo",2));
        mOut += "\n team two set to : " + (match.getTeams()[TEAM_TWO]);
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        assertEquals(String.format("Team Two returned as %s", match.getTeams()[TEAM_TWO]),
                     teamTwoSeeded, match.getTeams()[TEAM_TWO].toString());
        
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
                                    match.getTeams()[TEAM_ONE], teamOneSeeded),
                     teamOneSeeded, match.getTeams()[TEAM_ONE].toString());

        assertEquals(String.format("Team Two returned as %s", 
                                    match.getTeams()[TEAM_TWO]),
                     teamTwoSeeded, 
                     match.getTeams()[TEAM_TWO].toString());

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

        
        mOut += ("****************");
        mOut += "\n" + ("Test 07");
        mOut += "\n" + (" - Test that scores are added and removed correctly");
        mOut += "\n" + ("----------------");
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
        mOut = "";
        
        match.addTeam(new Team("teamOne",1));
        match.addTeam(new Team("teamTwo",2));
        
        match.setFinalScore(27, 15);
        

        mOut += match;
        if (SHOW_ALL_RESULTS) System.out.println(mOut);
    }
    
    //  8) Check that setFinalScore throws illArgExc if a score is null
    //  9) Check that setFinalScore throws illArgExc if score is tied
    //  10) Check that setFinalScore throws IllStatException if score is already set
    //  11) Check that getFinalScore correctly returns team scores
    //  12) Check that getWinner returns the winning team
    //  13) Check that getWinner throws illegalStateException if no score set
    
}
