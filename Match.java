package application;


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
     * void setTeams(T,T)
     * 
     * Allows utilizer to set two teams of type T
     * NOTES:  Do we want to allow override of teams, i.e if they are already set can they 
     *         be adjusted?
     *
     * @param teamOne - team in socket one of match
     * @param teamTwo - team in socket two of match
     */
	@Override
	public void setTeams(Team teamOne, Team teamTwo) {
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
	}

	/**
	 * Return both teams as an array with teamOne in spot 0 and teamTwo in spot 1
	 * @return both teams as an array
	 */
	@Override
	public Team[] getTeams() {
		Team[] teams = new Team[NUM_TEAMS];
		
		teams[teamSpot.TeamOne.ordinal()] = this.teamOne;
		teams[teamSpot.TeamTwo.ordinal()] = this.teamTwo;
		
		return teams;
	}

	
	/**
     * T setFinalScore(S, S)
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
     * @return - returns the winner of the match (type T)
     * @throws IllegalArgumentException if the score is tied
     */
	@Override
	public Team setFinalScore(S teamOneScore, S teamTwoScore) throws IllegalArgumentException {
		if (teamOneScore == null || teamTwoScore == null) throw new 
							IllegalArgumentException ("Can not have null scores");
			
		if (teamOneScore.equals(teamTwoScore)) throw new 
											IllegalArgumentException ("Can not have ties in bracket");
		
		this.teamOneScore = teamOneScore;
		this.teamTwoScore = teamTwoScore;
		return getWinner();
	}

	/**
     * T getWinner()
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

}