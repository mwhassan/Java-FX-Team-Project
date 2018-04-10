package application;

/**
 * Instances of this class represent a single Team or Challenger.
 * This class keeps track of the team's name and seed in a Bracket.
 */
public class Team {
	
	/**********************
	 * Private Constants
	 **********************/
	private String name;
	private int seed;
	
	/**
	 * Constructor
	 */
	public Team(String name, int seed) {
	    this.seed = seed;
		this.name = name;
	}
	
	/**********************
	 * Setters and Getters
	 **********************/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getSeed() {
	    return seed;
	}
}