package application;

import java.io.*;
import java.util.ArrayList;

import application.MatchADT.teamSpot;

public class Bracket<S extends Comparable<S>> implements BracketADT {
	
	private Match<Team, S>[] matches;					// array of matches
	private ArrayList<Team> teams = new ArrayList<Team>();					// used as temporary storage
	
	public Bracket(String fileName) throws IOException {
		seedBracket(fileName);
	}

	@Override
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
            System.out.println(teams.get(size()).getName() + " is the only challenger, hence the only champion.");
            return;
        }
        else {
            matches =  (Match<Team, S>[]) new Match[size()];
        }
        for(int i = 1; i < matches.length; i++) {
            matches[i] = new Match<Team, S>();
        }
        matches[1].setTeams(teams.get(1), teams.get(size())); 
        split(1, (size()) / 2, 1);
        print();
	}

	private void split(int start, int end, int factor) {
	        // stop condition: when the factor is larger than 
	        if((int)Math.pow(2, factor) > size() / 2) return;
	        
	        // index: calculate the mid position to place the less priority team using factor and start index
	        int index = (int)Math.pow(2.0, factor) - matches[start].teamOne.getSeed() + 1;
	        // use the index to calculate and access to the less priority team from teams arrayList
	        matches[(start + end) / 2 + 1].setTeams(teams.get(index), teams.get(size() - index + 1));
	        // split the interval(start, end) into half and recursively call itself
	        split(start, (start + end)/2, factor + 1);
	        split((start + end)/2 + 1, end, factor + 1);
	    }
	    
	    // used for testing: printing out the sets of matches
	    private void print() {
	        System.out.println("the length of the matches array is: " + matches.length);
	        for(int i = 1; i <= size()/2; i++) { 
	            System.out.println("(" + matches[i].teamOne.getName() + ", " + matches[i].teamTwo.getName() + ") ");
	        }
	    }

	    @Override
	    // return the number of teams stored in the arrayList Teams
	    public int size() {
	        // TODO Auto-generated method stub
	        return teams.size() - 1;
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