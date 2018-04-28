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

import java.util.ArrayList;

/********************************************
 * Model
 *      n/a only data fed in from user
 * Controlor
 *      Match - information about specific 
 *      Bracket - list of matches
 * View
 *      viewBracket.fxml
 ********************************************/


/**
 * Match class represents one match in the bracket.  It is used to track who played who in which 
 * match, allow the utilizer to set the final score, and then later return the winner
 * @author devbox
 *
 */


public interface MatchADT <S extends Comparable<S>> {
    
    public enum TeamSpot {TeamOne, TeamTwo};//ID Team, might take out or turn to constants w\index
    
    
    /**
     * void setTeams(Team,Team)
     * 
     * Allows utilizer to set two teams of type Team
     * NOTES:  Do we want to allow override of teams, i.e if they are already set can they 
     *         be adjusted?
     *
     * @param teamOne - team in socket one of match
     * @param teamTwo - team in socket two of match
     */
    public void setTeams(Team teamOne, Team teamTwo);
    
    /**
     * void addTeam(Team)
     * 
     * Adds a single team to the match.
     * 
     * @throws IllegalStateException() if match is already full.
     * @param Team team
     */
    public void addTeam(Team team);
    
    
    /**
     * Return an array containing team 1 and team 2
     * @return
     */
    public Team[] getTeams(); 
    
    
    /**
     * Team setFinalScore(S, S)
     * 
     * Allows user to set final score, returns the winner.
     * 
     * @param scoreTeamOne - score of team one.  
     * @param scoreTeamTwo - score of team two
     * @return - returns the winner of the match (type Team)
     * @throws IllegalArgumentException if the score is tied
     */
    public Team setFinalScore(S scoreTeamOne, S scoreTeamTwo) throws IllegalArgumentException; //Set score, allow override?
    
    /**
     * Team getWinner()
     * returns the winner of the match
     * NOTES:  Do we want to allow override of scores, i.e if they are already set can they 
     *         be adjusted?
     *
     * @return returns the team of type Team that won the match (highest 'score')
     * @throws IllegalStateException if no scores have been set
     */
    public Team getWinner() throws IllegalStateException;
    
    public ArrayList<S>  getFinalScores(); 
    
}
