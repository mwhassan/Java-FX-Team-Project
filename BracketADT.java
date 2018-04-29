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
 * 2)  The bracket above could be split in half, but the numbering will remain with the idea that
 *     the bottom half will become the left symmetrical half when split up in such a manner
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
    public int getMatchRound(int matchIndex);
    public int getMatchSlot(int matchIndex);
    
    public String getMatchWinner(int matchIndex); //Return the winner of a match given an index
    public String getMatchTeam(int matchIndex, MatchADT.TeamSpot team); //Return the winner of a match given an index
    
    
    //Genealogy
    public int getNextMatch(int matchIndex); //Returns the index of the match that follows
    
    //Set match scores for each team
    public void setMatchScore(int matchIndex, S scoreTeamOne, S scoreTeamTwo);
    
    //Get champion
    public Team getChampion();
    
    /**
	 * Returns previous match containing the team sent in
	 * @param currentIndex - index of current match
	 * @param team - team we want to find previous match for
	 * @return - index of match team sent in played prior to current match
	 */
	public int getPrevMatch (int currentIndex, Team team);
	
	
    
}
