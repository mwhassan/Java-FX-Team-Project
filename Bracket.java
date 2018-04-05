package application;

import java.io.*;
import java.util.ArrayList;

import application.MatchADT.teamSpot;

public class Bracket<S extends Comparable<S>> implements BracketADT {
	
	private Match<Team, S>[] matches;					// array of matches
	private ArrayList<Team> teams;					// used as temporary storage
	
	public Bracket(String fileName) throws IOException {
		seedBracket(fileName);
	}

	@Override
	public void seedBracket(String fileName) throws IOException {
		File file = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		// get teams from file
		String temp;
		do {
			temp = reader.readLine();
			teams.add(new Team(temp));
		}while(temp != null);
		reader.close();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rounds() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int matches() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getMatchIndex(int round, int slot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getMatchWinner(int matchIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMatchTeam(int matchIndex, teamSpot team) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getPrevMatchs(int matchIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNextMatch(int matchIndex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMatchScore(int matchIndex, Object scoreTeamOne, Object scoreTeamTwo) {
		// TODO Auto-generated method stub
		
	}
	
}