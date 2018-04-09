package application;

public class Team {
	private String name;
	private int seed;
	
	public Team(String name, int seed) {
	    this.seed = seed;
		this.name = name;
	}

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