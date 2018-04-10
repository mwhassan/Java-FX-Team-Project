package application;


import java.io.IOException;

/**
 * Assumptions:
 * 
 * 1) Items are referred to as follows.  This shows for an 8 team bracket, but general principal
 *    holds that you work your way from top to bottom of round a then left to right on rounds 
 *    themselves when you number.  Matches continue to increment regardless of round, slots
 *    restart every round.  
 * Round 1  Round 2   Round 3
 * ________
 * slot 1  |
 * match 1 |-------
 * ________|       |
 *          slot 1 |_______
 * ________ Match 5|       |
 * slot 2  |       |       |
 * match 2 |-------        |
 * ________|        Slot 1 |
 *                         |
 *                      Winner
 * ________                |             
 * slot 3  |        Match 7|
 * match 3 |-------        |
 * ________|       |       |
 *          slot 2 |_______|
 * ________ Match 6|
 * slot 4  |       |
 * match 4 |-------
 * ________|       
 *                  
 * 2) For wishlist items, the assumption is rounds are played top to bottom, so current match
 *    is the first match numerically that has not been scored yet
 * 
 * 3)  The bracket above could be split in half, but the numbering will remain with the idea that
 *     the bottom half will become the left symetrical half when split up in such a manner
 *     
 *      Round 1    Round 2    Round 1
 *      ________               ________
 *      slot 1  |   slot 1    |slot 2
 *      match 1 |-----[W]-----|match 2
 *      ________|   Match 3   |________
 * 
 *  Total Rounds: Log N
 *  Total Matches: N - 1
 *  Total slots in a given round: 2^(LogN-round) 
 *  
 *  Generic S is the score type set up for the controller
 * @author devbox
 *
 */
public interface BracketADT <S> {

    
    
    //tournament specific
    public void seedBracket(String fileName) throws IOException; //Seed new bracket
    public int size(); //Return number of teams in the bracket 
    public int rounds(); //Return the total number of rounds in the bracket
    public int matches(); //Returns the total number of matches in the bracket
    
    
    //Match specific information
    
    //Find index given round and slot
    public int getMatchIndex(int round, int slot); 
    
    public String getMatchWinner(int matchIndex); //Return the winner of a match given an index
    public String getMatchTeam(int matchIndex, MatchADT.teamSpot team); //Return the winner of a match given an index
    
    
    //Genealogy
    public int[] getPrevMatches(int matchIndex); //Returns the index of the match that follows
    public int getNextMatch(int matchIndex); //Returns the index of the match that follows
    
    //Set match scores for each team
    public void setMatchScore(int matchIndex, S scoreTeamOne, S scoreTeamTwo);
    
    
//    //Wishlist - might be nice for fun design layout options on view
//    public String[] teams(); //Return a list of all teams
//    public String[] teamsOut(); //return a list of teams that are out
//    public String[] teamsIn(); //Return a list of teams that are in
//    public String[] teamsBeatBy(String teamName); //Returns an array of teams already beat by the
//                                                  //team sent in
//    public int getCurrentRound(); //Return the round number  (assuming all items in round scored
//                                  //before moving on to next round, returns next round without
//                                  //a score
//    public int getCurrentMatch(); //Return the index next match to be scored
//    public int getSlot(int matchIndex); //Return the slot given the match index
//    public int getRound(int matchIndex); //Return the round given the match index
//    public String getTournementWinner(); // return tourney winner if there is one, otherwise?
    
}
