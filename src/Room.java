import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Room {
    private String description;
    private ArrayList<String> signals;
    private Wumpus wumpus = null;
    private Item item = null;
    private Pit pit = null;
    private boolean hasObject = false;
    private int x, y;

    /**
     * This/each room has a hash map of exits
     * String direction, Room direction destination
     */
    private HashMap<String, Room> exits;        // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    public Room(String description, int x, int y) {
        this.x = x;
        this.y = y;
        signals = new ArrayList<String>();
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Define an exit from this room.
     *
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Return a description of the room in the form:
     * You are in the kitchen.
     * Exits: north west
     *
     * @return A long description of this room
     */
    public String getLongDescription() {
        return "You are at " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     *
     * @return Details of the room's exits.
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     *
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void setWumpus(Wumpus w) {
        wumpus = w;
        hasObject = true;
    }

    public ArrayList<String> getSignals() {
        return new ArrayList<String>(signals);
    }

    public void killWumpus() {

        wumpus = null;
    }

    public Item getItem() {
        Item copy = item.clone();
        item = null;
        return copy;
    }

    public void addItem(Item i) {
        item = i;
        hasObject = true;
    }

    public void removeItem()
    {
        item = null;
    }

    public void setSignals(String signal) {
        signals.add(signal);
    }

    public void removeSignal(String signal) {
        signals.remove(signal);
    }

    public void setPit(Pit pit) {
        hasObject = true;
        this.pit = pit;
    }

    public Pit hasPit()
    {
        return pit;
    }


    public boolean thisHasObject() {
        return hasObject;
    }

    public Wumpus hasWumpus() {
        return wumpus;
    }

    public int getX() {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String getItemDescription()
    {
        if(item != null)
            return item.getDescription();
        return "None";
    }

}

