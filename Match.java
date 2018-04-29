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

/**
 * Match object used for bracket class.  Contains information about a specific match
 * 
 * @author hayesbirchle
 *
 * @param <S> - Generic score type
 */
public class Match<S extends Comparable<S>> implements MatchADT <S>  {

	/**********************
	 * Private Constants
	 **********************/
	private static int NUM_TEAMS = 2;
	
	/**********************
	 * Private Class Fields
	 **********************/
	private Team teamOne;
	private Team teamTwo;
	
	private S teamOneScore;
	private S teamTwoScore;
	
	/**********************
	 * Public Interface
	 **********************/
	
	/**
     * void setTeams(Team,Team)
     * 
     * Allows utilizer to set two teams of type Team
     *
     * @param teamOne - team in socket one of match
     * @param teamTwo - team in socket two of match
     * @throws IllegalArgumentException if either team is null
     */
	@Override
	public void setTeams(Team teamOne, Team teamTwo) throws IllegalArgumentException{
	    if (teamOne == null || teamTwo == null) 
	        throw new IllegalArgumentException("You can not set a null team");
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
	}
	
	/**
	 * void addTeam(Team)
	 * 
	 * Adds a team to the match.
	 * @throws IllegalStateException if match is already full
	 * @throws IllegalArgumentException if team is null
	 * @param Team team
	 */
	@Override
	public void addTeam(Team team) throws IllegalArgumentException, IllegalStateException{
	    if (team == null) 
            throw new IllegalArgumentException("You can not add a null team");
	    if(teamOne == null) teamOne = team;
		else if(teamTwo == null) teamTwo = team;
		else throw new IllegalStateException("Match is already full.");
	}

	/**
	 * Adds teams to the match at the given spot. 
	 * 
	 * @param team - team you want to add
	 * @param spot - spot you want to add team to
	 * @throws IllegalStateException if spot is already full
	 * @throws IllegalArgumentException if team is null 
	 */
	@Override
	public void addTeam(Team team, TeamSpot spot) 
							throws IllegalArgumentException, IllegalStateException {
		if (team == null || spot == null) 
            throw new IllegalArgumentException("You can not add a null team");
		if (spot == TeamSpot.TeamOne) {
			//if team is not null and name is not "" then throw exception
			System.out.println(teamOne);
			if (teamOne != null && !teamOne.getName().trim().equals("")) {
				System.out.println("team one exception");
				throw new IllegalStateException("Spot is already full.");
			}
			System.out.println("Setting teamOne");
			teamOne = team;
		}
		else {
			if (teamTwo != null && !teamTwo.getName().trim().equals("")) { 
				System.out.println("team two exception");
				throw new IllegalStateException("Spot is already full.");
			}
			System.out.println("Setting teamTwo");
			teamTwo = team;
		}
	}
	
	/**
	 * Return both teams as an array with teamOne in spot 0 and teamTwo in spot 1
	 * @return both teams as an array
	 */
	@Override
	public Team[] getTeams(){
		Team[] teams = new Team[NUM_TEAMS];
		teams[TeamSpot.TeamOne.ordinal()] = this.teamOne;
		teams[TeamSpot.TeamTwo.ordinal()] = this.teamTwo;
		return teams;
	}
	
	
	/**
	 * Returns true if match contains the team sent in
	 */
	@Override
	public boolean containsTeam(Team checkTeam) {
		if (checkTeam == null)
				throw new IllegalArgumentException("You can check a null team");
		return (teamOne == checkTeam || teamTwo == checkTeam);
	}

	
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
	@Override
	public Team setFinalScore(S teamOneScore, S teamTwoScore) 
	        throws IllegalArgumentException, IllegalStateException {
		if (teamOneScore == null || teamTwoScore == null) throw new 
							IllegalArgumentException ("Can not have null scores");
		if (teamOneScore.equals(teamTwoScore)) 
		    throw new IllegalArgumentException ("Can not have ties in bracket");
		if (this.teamOneScore != null || this.teamTwoScore != null) {
			System.out.println("TeamOneScore = " + this.teamOneScore);
		    throw new IllegalStateException("Cannot override existing score.");
		}
		this.teamOneScore = teamOneScore;
		this.teamTwoScore = teamTwoScore;
		return getWinner();
	}

	/**
     * Return both teams as an array with teamOne in spot 0 and teamTwo in spot 1
     * @return both teams as an array
     */
	@Override
    public ArrayList<S> getFinalScores(){
        ArrayList<S> scores = new ArrayList<S>();
        scores.add(this.teamOneScore);
        scores.add(this.teamTwoScore);
        return scores;
    }
    
	/**
     * Team getWinner()
     * returns the winner of the match
     *
     * @return returns the team of type T that won the match (highest 'score')
     * @throws IllegalStateException if no scores have been set
     */
	@Override
	public Team getWinner() throws IllegalStateException {
		if (teamOneScore == null || teamTwoScore == null) 
			throw new IllegalStateException("no scores set");
		if (teamOneScore.compareTo(teamTwoScore) > 0) {
			return teamOne;
		}else {
			return teamTwo;
		}
	}
	
	/**
	 * returns the team that lost the match
	 * 
	 * @return - losing team
	 * @throws IllegalStateException - thrown if scores are not set
	 */
	@Override
	public Team getLoser() throws IllegalStateException {
		if (teamOneScore == null || teamTwoScore == null) throw new 
									IllegalStateException ("Scores not set");
		if (teamOneScore.compareTo(teamTwoScore) < 0) {
			return teamOne;
		}else {
			return teamTwo;
		}
	}
	
	/**
	 * Overriding to string to show teams
	 */
	@Override
	public String toString(){
	    String mReturn = "";
	    mReturn += "Match\n";
	    if (this.teamOne != null)
	    	mReturn += "   Team One : " + this.teamOne.getName() + this.teamOne.getSeed() + "\n";
	    else 
	    	mReturn += "    <team one not set>\n";
	    
	    if (this.teamTwo != null) 
	    	mReturn += "   Team Two : " + this.teamTwo.getName() + this.teamTwo.getSeed() + "\n";
	    else 
	    	mReturn += "    <team two not set>\n";
	    
	    if (this.teamOneScore != null && this.teamTwoScore != null)
	        mReturn += "   Final Score : (T1) " + this.teamOneScore + " to (T2) " + this.teamTwoScore;
	    else
	        mReturn += "   No score set";
	                    
	    return mReturn;
	}
	
	/*******************
     * Getter for Testing
     *******************/
    public String teamOne() {
        if(teamOne != null)
            return teamOne.getName();
        else
            return "";
    }
    public String teamTwo() {
        if(teamTwo != null)
            return teamTwo.getName();
        else
            return "";
    }

}