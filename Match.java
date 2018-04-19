package application;

import java.util.ArrayList;

/**
 * Match object used for bracket class.  Contains information about a specific match
 * 
 * @author hayesbirchle
 *
 * @param <Team> - Generic team type
 * @param <S> - Generic score type
 */
public class Match<S extends Comparable<S> > implements MatchADT <S>  {

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
     * NOTES:  Do we want to allow override of teams, i.e if they are already set can they 
     *         be adjusted?
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
	public void addTeam(Team team) throws IllegalArgumentException, IllegalStateException{
	    if (team == null) 
            throw new IllegalArgumentException("You can not add a null team");
	    
	    if(teamOne == null) teamOne = team;
		else if(teamTwo == null) teamTwo = team;
		else throw new IllegalStateException("Match is already full.");
	}

	/**
	 * Return both teams as an array with teamOne in spot 0 and teamTwo in spot 1
	 * @return both teams as an array
	 */
	@Override
	public Team[] getTeams(){
		Team[] teams = new Team[NUM_TEAMS];
		
		teams[teamSpot.TeamOne.ordinal()] = this.teamOne;
		teams[teamSpot.TeamTwo.ordinal()] = this.teamTwo;
		
		return teams;
	}

	
	/**
     * Team setFinalScore(S, S)
     * 
     * Allows user to set final score, returns the winner.
     * I think it makes sense to keep score generic but make it extend Comparable.  It is fairly
     * unnecessary for this specific project, but I think we should work on showing modular 
     * design approaches.  I could imagine 'results' from 'tournements' in many forms including
     * grades, ranks where 1 is best, scores were higher is better.  So we can just use
     * scoreOne.compareTo(scoreTwo)
     * 
     * @param scoreTeamOne - score of team one.  
     * @param scoreTeamTwo - score of team two
     * @return - returns the winner of the match (type Team)
     * @throws IllegalArgumentException if the score is tied
     */
	@Override
	public Team setFinalScore(S teamOneScore, S teamTwoScore) throws IllegalArgumentException {
		if (teamOneScore == null || teamTwoScore == null) throw new 
							IllegalArgumentException ("Can not have null scores");
			
		if (teamOneScore.equals(teamTwoScore)) 
		    throw new IllegalArgumentException ("Can not have ties in bracket");
		if (this.teamOneScore != null || this.teamTwoScore != null) 
		    throw new IllegalStateException("Cannot override existing score.");
		
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
        @SuppressWarnings("unchecked")
        //S[] scores = (S[])new Comparable[NUM_TEAMS];
        ArrayList<S> scores = new ArrayList<S>();
        
        scores.add(this.teamOneScore);
        scores.add(this.teamTwoScore);
        
        return scores;
    }
    
	/**
     * Team getWinner()
     * returns the winner of the match
     * NOTES:  Do we want to allow override of scores, i.e if they are already set can they 
     *         be adjusted?
     *
     * @return returns the team of type T that won the match (highest 'score')
     * @throws IllegalStateException if no scores have been set
     */
	@Override
	public Team getWinner() throws IllegalStateException {
		if (teamOneScore == null || teamTwoScore == null) throw new 
									IllegalStateException ("Scores not set");
		if (teamOneScore.compareTo(teamTwoScore) > 0) {
			return teamOne;
		}else { 
			return teamTwo;
		}
	}
	
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

}