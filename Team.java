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
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

/**
 * Instances of this class represent a single Team or Challenger. This class keeps track of the
 * team's name and seed in a Bracket.
 */
public class Team {

    /**********************
     * Private Constants
     **********************/
    private String name;		// Team name
    private final int seed;		// Team seed number

    /**
     * Constructor
     */
    public Team(String name, int seed) {
        this.seed = seed;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
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
