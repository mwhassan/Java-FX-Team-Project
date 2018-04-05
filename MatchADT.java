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


public interface MatchADT <T, S extends Comparable<S>> {
    
    public enum teamSpot {TeamOne, TeamTwo};//ID Team, might take out or turn to constants w\index
    
    
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
    public void setTeams(T teamOne, T teamTwo);
    
    
    /**
     * Return an array containing team 1 and team 2
     * @return
     */
    public T[] getTeams(); 
    
    
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
     */
    public T setFinalScore(S scoreTeamOne, S scoreTeamTwo); //Set score, allow override?
    
    /**
     * T getWinner()
     * returns the winner of the match
     * NOTES:  Do we want to allow override of scores, i.e if they are already set can they 
     *         be adjusted?
     *
     * @return eturns the team of type T that won the match (highest 'score')
     * @throws IllegalStateException if no scores have been set
     * @throws IllegalArgumentException if the score is tied
     */
    public T getWinner() throws IllegalStateException, IllegalArgumentException; 
    
}
