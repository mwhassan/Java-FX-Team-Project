package application;

import java.io.*;
import java.util.ArrayList;

import application.MatchADT.teamSpot;

/**
 * This class handles seeding and keeping track of matches, teams, and scores.
 */
public class Bracket<S extends Comparable<S>> implements BracketADT<S> {
	
	/**********************
	 * Private Constants
	 **********************/
	private Match<S>[] matches;												// array of matches
	private ArrayList<Team> teams = new ArrayList<Team>();					// used as temporary storage
	private int round = 0;													// round number

	/**
	 * Constructor
	 */
	public Bracket(String fileName) throws IOException {
		seedBracket(fileName);
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Seeds the bracket.
	 * Favors good teams by matching them with weaker teams in earlier rounds.
	 */
	public void seedBracket(String fileName) throws IOException {
		teams.add(null);  // set the index 0 to be null, the team array starts from index 1

		File file = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		// get teams from file
		String temp = reader.readLine();
		int counter = 0;
		while(temp != null) {
			temp = temp.replaceAll(" ", "");
			if(temp.length() != 0) {
				teams.add(new Team(temp, ++counter));
			}
			temp = reader.readLine();
		}
		reader.close();

		if(size() < 1) {
			System.out.println("No challengers, no games, and no champion!!!");
			return;
		}
		else if(size() == 1) {
			round++; // there is one round
			System.out.println(teams.get(size()).getName() + " is the only challenger, hence the only champion.");
			return;
		}
		else {
			round++; // entering first round
			matches =  (Match<S>[]) new Match[size()];
		}
		for(int i = 1; i < matches.length; i++) {
			matches[i] = new Match<S>();
		}
		matches[1].setTeams(teams.get(1), teams.get(size())); 
		split(1, (size()) / 2, 1);
		print();
	}

	private void split(int start, int end, int factor) {
		// stop condition: when the factor is larger than 
		if((int)Math.pow(2, factor) > size() / 2) return;

		// index: calculate the mid position to place the less priority team using factor and start index
		int index = (int)Math.pow(2.0, factor) - matches[start].getTeams()[0].getSeed() + 1;
		// use the index to calculate and access to the less priority team from teams arrayList
		matches[(start + end) / 2 + 1].setTeams(teams.get(index), teams.get(size() - index + 1));
		// split the interval(start, end) into half and recursively call itself
		split(start, (start + end)/2, factor + 1);
		split((start + end)/2 + 1, end, factor + 1);
	}

	// used for testing: printing out the sets of matches TODO remove before submission
	private void print() {
		System.out.println("the length of the matches array is: " + matches.length);
		for(int i = 1; i <= size()/2; i++) { 
			System.out.println("(" + matches[i].getTeams()[0].getName() + ", " + matches[i].getTeams()[1].getName() + ") ");
		}
	}

	@Override
	/**
	 * return the number of teams stored in the arrayList Teams
	 */
	public int size() {
		return teams.size() - 1;
	}

	@Override
	/**
	 * return the number of rounds in the tournament
	 */
	public int rounds() {
		int size = size();
		
		// if no teams, return 0
		if(size == 0) return 0;
		
		// otherwise, return log_2(size) + 1
		double log = Math.log((double)size)/Math.log(2.0);
		return (int) log + 1;
	}

	@Override
	/**
	 * return number of matches
	 */
	public int matches() {
		return size() - 1; // one team loses every match, until there is one left
	}

	@Override
	/**
	 * return winner of a match if set
	 * return null if no winner has yet been decided
	 */
	public String getMatchWinner(int matchIndex) {
		try {
			return matches[matchIndex].getWinner().getName();
		}catch(IllegalStateException e) {
			return null;
		}
	}
	
	@Override
	/**
	 * return a team name in a given spot of a match
	 */
	public String getMatchTeam(int matchIndex, teamSpot team) {
		return matches[matchIndex].getTeams()[team.ordinal()].getName();
	}
	
	@Override
	public void setMatchScore(int matchIndex, S teamOneScore, S teamTwoScore) {
		matches[matchIndex].setFinalScore(teamOneScore, teamTwoScore);
	}

	@Override
	public int getMatchIndex(int round, int slot) {
		int m = matches(); // get number of matches
		return 2*m - (int)(m/Math.pow(0.5, round)) + slot; // account for previous rounds
	}

	@Override
	public int[] getPrevMatches(int matchIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNextMatch(int matchIndex) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}