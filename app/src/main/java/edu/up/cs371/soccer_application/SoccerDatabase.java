package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    public Hashtable<String, SoccerPlayer> hashPlayer = new Hashtable<>();
    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {

        if(hashPlayer.containsKey(""+ firstName + "##" + lastName))
        {
            return false;
        }
        SoccerPlayer sp = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
        String name = firstName + "##" + lastName;
        hashPlayer.put(name, sp);
        return true;
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName)
    {
        if(hashPlayer.get(firstName + "##" + lastName) != null)
        {
            hashPlayer.remove(firstName + "##" + lastName);
            return true;
        }
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {
        SoccerPlayer foundPlayer = null;
        for(String name : hashPlayer.keySet()) {
            if(name.equals(firstName + "##" + lastName)) {
                foundPlayer = hashPlayer.get(name);
                return foundPlayer;
            }
        }
        return null;
    }


    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        SoccerPlayer sp = getPlayer(firstName, lastName);
        if(sp != null) {
            sp.bumpGoals();
            return true;
        }
        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        SoccerPlayer sp = getPlayer(firstName, lastName);
        if(sp != null) {
            sp.bumpAssists();
            return true;
        }
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        SoccerPlayer sp = getPlayer(firstName, lastName);
        if(sp != null) {
            sp.bumpShots();
            return true;
        }
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        SoccerPlayer sp = getPlayer(firstName, lastName);
        if(sp != null) {
            sp.bumpSaves();
            return true;
        }
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        SoccerPlayer sp = getPlayer(firstName, lastName);
        if(sp != null) {
            sp.bumpFouls();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        SoccerPlayer sp = getPlayer(firstName, lastName);
        if(sp != null) {
            sp.bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        SoccerPlayer sp = getPlayer(firstName, lastName);
        if(sp != null) {
            sp.bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {

        int playerNum = 0;
        if(teamName == null)
        {
            return hashPlayer.size();
        }
        for( String name : hashPlayer.keySet())
        {
            SoccerPlayer currPlayer = hashPlayer.get(name);
            if(currPlayer.getTeamName().equalsIgnoreCase(teamName))
            {
                playerNum++;
            }
        }
        return playerNum;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName)
    {
        ArrayList<SoccerPlayer> currTeam = new ArrayList<SoccerPlayer>();
        for(String name : hashPlayer.keySet()) {
            if(hashPlayer.get(name).getTeamName().equals(teamName) || teamName == null) {
                currTeam.add(hashPlayer.get(name));
            }
        }
        return currTeam.get(idx % currTeam.size());
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        File f = new File(file.getPath());
        PrintWriter output = null;
        try {
            output = new PrintWriter(f);
        } catch(FileNotFoundException fnfe) {
            //swag
        }
        try {
            for (String playerName : hashPlayer.keySet()) {
                output.println(logString("First Name: " + hashPlayer.get(playerName).getFirstName()));
                output.println(logString("Last Name: " + hashPlayer.get(playerName).getLastName()));
                output.println(logString("Team Name: " + hashPlayer.get(playerName).getTeamName()));
                output.println(logString("Uniform Number: "+hashPlayer.get(playerName).getUniform()));
                output.println(logString("Goals: "+hashPlayer.get(playerName).getGoals()));
                output.println(logString("Assists: "+hashPlayer.get(playerName).getAssists()));
                output.println(logString("Shots: "+hashPlayer.get(playerName).getShots()));
                output.println(logString("Fouls: "+hashPlayer.get(playerName).getFouls()));
                output.println(logString("Saves: "+hashPlayer.get(playerName).getSaves()));
                output.println(logString("Yellow Cards: "+hashPlayer.get(playerName).getYellowCards()));
                output.println(logString("Red Cards: "+hashPlayer.get(playerName).getRedCards()));
            }
            output.close();
        } catch(NullPointerException npe) {
            //swag2
        }
        return true;
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
