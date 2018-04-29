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

import java.io.*;
import java.util.ArrayList;
import application.MatchADT.TeamSpot;

/**
 * This class handles seeding and keeping track of matches, teams, and scores.
 */
public class Bracket<S extends Comparable<S>> implements BracketADT<S> {

	/**********************
	 * Private Constants
	 **********************/
	private Match<S>[] matches; // array of matches
	private ArrayList<Team> teams = new ArrayList<Team>(); // used as temporary storage
	private Team champion; // winner of the Bracket

	/**********************
	 * Constructors
	 **********************/

	/**
	 * Constructor
	 * 
	 * @param fileName
	 *            - file we will load teams from
	 */
	public Bracket(String fileName) throws IOException {
		seedBracket(fileName);
	}

	/**
	 * Default Constructor used for testing
	 */
	public Bracket() {
	}

	/**********************
	 * Public interface
	 **********************/

	@Override
	/**
	 * Return a String containing all matches generated so for following the seeding
	 * order
	 */
	public String toString() throws NullPointerException {
		String s = "";
		for (int i = 1; i < matches.length; i++) {
			s += matches[i].teamOne();
			s += matches[i].teamTwo();
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Seeds the bracket. Favors good teams by matching them with weaker teams in
	 * earlier rounds.
	 */
	public void seedBracket(String fileName) throws IOException {
		teams.add(null); // set the index 0 to be null, the team array starts from index 1

		File file = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		// get teams from file
		String temp = reader.readLine();
		int counter = 0;
		while (temp != null) {
			temp = temp.replaceAll(" ", "");
			if (temp.length() != 0) {
				teams.add(new Team(temp, ++counter));
			}
			temp = reader.readLine();
		}
		reader.close();
		if (size() < 1) {
			return;
		} else if (size() == 1) {
			// set both teams to be the same. no winner or scores
			matches = (Match<S>[]) new Match[2];
			matches[1] = new Match<S>();
			matches[1].setTeams(teams.get(1), teams.get(1));
			return;
		} else {
			matches = (Match<S>[]) new Match[matches() + 1];
		}
		for (int i = 1; i < matches.length; i++) {
			matches[i] = new Match<S>();
		}
		matches[1].setTeams(teams.get(1), teams.get(size()));
		split(1, (size()) / 2, 1);
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
		if (size == 0)
			return 0;
		// otherwise, return log_2(size)
		return (int) (Math.log((double) size) / Math.log(2.0));
	}

	@Override
	/**
	 * return number of matches
	 */
	public int matches() {
		if (size() == 0)
			return 0;
		else
			return size() - 1; // one team loses every match, until there is one left
	}

	@Override
	/**
	 * return winner of a match if set return null if no winner has yet been decided
	 */
	public String getMatchWinner(int matchIndex) {
		try {
			return matches[matchIndex].getWinner().getName();
		} catch (IllegalStateException e) {
			return null;
		}
	}

	@Override
	/**
	 * return a team name in a given spot of a match returns null if no team yet
	 */
	public String getMatchTeam(int matchIndex, TeamSpot team) {
		try {
			return matches[matchIndex].getTeams()[team.ordinal()].getName();
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	/**
	 * Set the match score and add winner to next match.
	 * 
	 * @throws IllegalArgumentException
	 *             if null score passed or if tie is entered
	 * @throws IllegalStateException
	 *             if overriding a previously determined score
	 * 
	 *             Note: I don't think we are using this anywhere
	 */
	public void setMatchScore(int matchIndex, S teamOneScore, S teamTwoScore) {
		Team winner = matches[matchIndex].setFinalScore(teamOneScore, teamTwoScore);
		int nextIndex = getNextMatch(matchIndex);
		if (nextIndex == -1)
			champion = winner;
		else
			matches[nextIndex].addTeam(winner);
	}

	/**
	 * returns the match found at a given matchIndex
	 * 
	 * @param matchIndex
	 *            - index of match we want to return
	 * @return the match found at the given index
	 */
	@Override
	public Match<S> getMatch(int matchIndex) {
		return matches[matchIndex];
	}

	@Override
	/**
	 * Returns the index of a match
	 * 
	 * @param round
	 *            - round that contains the slot we want to get the match index for
	 * @param slot
	 *            - that contains the match we want the match index for
	 * @return index of the match found at a given round and slot
	 */
	public int getMatchIndex(int round, int slot) {
		int rounds = rounds();
		if (round <= 0 || round > rounds)
			throw new IllegalArgumentException("No such round in Bracket");
		return (int) (Math.pow(2, rounds) - Math.pow(2, rounds - round + 1) + slot);
	}

	@Override
	/**
	 * Return the round of a match
	 * 
	 * @param matchIndex
	 *            - index of current match
	 * @return round the match is in
	 */
	public int getMatchRound(int matchIndex) {
		return rounds() - (int) (Math.log(size() - matchIndex) / Math.log(2)); // rounds() - log_2(# of teams -
																				// matchIndex)
	}

	@Override
	/**
	 * Return the slot of a match in its round.
	 * 
	 * @param matchIndex
	 *            - index of current match
	 * @return slot of current round that the match is in
	 */
	public int getMatchSlot(int matchIndex) {
		return (int) (matchIndex - size() + Math.pow(2, rounds() - getMatchRound(matchIndex) + 1)); //
	}

	@Override
	/**
	 * Calculate the index of the match to which the winner will advance. Returns -1
	 * if this is the championship match.
	 */
	public int getNextMatch(int matchIndex) {
		int currRound = getMatchRound(matchIndex);
		int currSlot = getMatchSlot(matchIndex);
		if (currRound == rounds())
			return -1;
		int newRound = currRound + 1;
		int newSlot = (currSlot + 1) / 2;
		return getMatchIndex(newRound, newSlot);
	}

	/**
	 * Returns previous match containing the team sent in
	 * 
	 * @param currentIndex
	 *            - index of current match
	 * @param team
	 *            - team we want to find previous match for
	 * @return - index of match team sent in played prior to current match
	 */
	@Override
	public int getPrevMatch(int currentIndex, Team team) {
		int currRound = getMatchRound(currentIndex);
		int currSlot = getMatchSlot(currentIndex);
		if (currRound == 1)
			return -1;
		int newRound = currRound - 1;
		int optSlotOne = ((currSlot) / 2) + 1;
		int optSlotTwo = ((currSlot) / 2) + 2;
		if (matches[optSlotOne].containsTeam(team))
			return getMatchIndex(newRound, optSlotOne);
		else if (matches[optSlotTwo].containsTeam(team))
			return getMatchIndex(newRound, optSlotTwo);
		else
			return -1;
	}

	/**
	 * Returns the champion of the bracket
	 * 
	 * @return - champion
	 * @throws -
	 *             illegal state exception if no champ set
	 */
	@Override
	public Team getChampion() {
		try {
			champion = matches[matches()].getWinner();
		} catch (NullPointerException | IllegalStateException e) {
			throw new IllegalStateException("No champion set");
		}
		return champion;
	}

	/**********************
	 * Private Helper Methods
	 **********************/

	/**
	 * Recursion method splitting the sub-array into half and add match and team
	 * information
	 * 
	 * @param start:
	 *            start index of the sub-array
	 * @param end:
	 *            end index of the sub-array
	 * @param factor:
	 *            factor of 2 to calculate the seeded number
	 */
	private void split(int start, int end, int factor) {
		// stop condition: when the factor is larger than
		if ((int) Math.pow(2, factor) > size() / 2)
			return;
		// index: calculate the mid position to place the less priority team using
		// factor and start index
		int index = (int) Math.pow(2.0, factor) - matches[start].getTeams()[0].getSeed() + 1;
		// use the index to calculate and access to the less priority team from teams
		// arrayList
		matches[(start + end) / 2 + 1].setTeams(teams.get(index), teams.get(size() - index + 1));
		// split the interval(start, end) into half and recursively call itself
		split(start, (start + end) / 2, factor + 1);
		split((start + end) / 2 + 1, end, factor + 1);
	}

}