/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Control.Log;
import Locations.States.LocationState;
import java.util.HashMap;

/**
 * The datatype of a Building. This extends from <code>Location</code> and
 * shares most of the same functionality as the other <code>Location</code>
 * types.
 * @author Student
 */
public class Building extends ParentLocation {
    private Campus campus;
    private final String name;
    private final String shortCode;
    private final HashMap<String, Floor> floors;
    
    /**
     * Creates a new <code>Building</code>. Also:<ul><li>Adds the <code>Logger</code> as
     * an observer of it's state</li><li>Sets it's <code>fullName</code> to the
     * exact same string as the <code>name</code></li></ul>
     * @param name The name of the new <code>Building</code>
     * @param shortCode The shortcode to be assigned to the new <code>Building</code>
     */
    public Building(String name, String shortCode) {
        this.floors = new HashMap<>();
        this.name = this.fullName = name;
        this.shortCode = shortCode;
    }

    /**
     * Returns the name of the <code>Building</code>.
     * @return The name
     */
    public String GetName() {
        return name;
    }

    /**
     * Returns the shortcode of the <code>Building</code>.
     * @return The shortcode
     */
    public String GetShortcode() {
        return shortCode;
    }
    
    /**
     * Finds and returns a specific child <code>Floor</code> of this object.
     * @param name The name of the child object to find
     * @return The child object, if it is found
     */
    @Override
    public Floor GetChild(String name) {
        return floors.get(name);
    }
    
    /**
     * Finds and returns all the child <code>Floor</code> objects of this
     * object.
     * @return The child objects
     */
    @Override
    public Floor[] GetAllChildren() {
        return floors.values().toArray(new Floor[0]);
    }

    /**
     * Gets and returns the parent <code>Campus</code> of this object.
     * @return The <code>Campus</code>
     */
    public Campus GetCampus() {
        return campus;
    }

    /**
     * Sets the parent <code>Campus</code> of this object.
     * @param campus The parent <code>Campus</code>
     */
    public void SetCampus(Campus campus) {
        this.campus = campus;
        AddStateObserver(campus);
    }

    /**
     * Sets if this object is in a mixed state.
     * @param isMixedState <code>True</code> if this should be in a mixed state
     */
    @Override
    public void SetIsMixedState(boolean isMixedState) {
        super.SetIsMixedState(isMixedState);
        if (campus != null)
            campus.SetIsMixedState(isMixedState);
    }

    /**
     * Called by the <code>SetRoomState()</code>. This contains the actual
     * functionality for Changing the <code>Building</code>s state.<p>The reason
     * this is separated out into a separate <code>protected</code> method
     * rather than doing in in the <code>public</code> one is so that parent and
     * child <code>Location</code>s can call the protected one without it going
     * to the <code>Logger</code>.
     * @param newState The new state the <code>Location</code> is set to
     */
    @Override
    protected void ActualSetRoomState(LocationState newState, String reason) {
        super.ActualSetRoomState(newState, reason);
        floors.values().forEach(floor ->
            ((Floor)floor).ActualSetRoomState(newState, reason)); 
    }
    
    /**
     * Adds a new child <code>Floor</code> to this <code>Building</code>.
     * This method returns the new <code>Floor</code> but it also
     * automatically assigns it to this object so you do not need to do that
     * yourself. The new <code>Floor</code> has it's <code>Name</code>
     * automatically assigned to it based on the next number
     * following the previously made <code>Floor</code>.
     * @return The new <code>Floor</code>
     */
    public Floor AddFloor() {
        //Find all the current floors
        Floor[] allFloors = floors.values().toArray(new Floor[0]);
        
        //Find the next floor number after the highest alreay created
        String highestFloor = (allFloors.length > 0) ? allFloors[allFloors.length - 1].GetFloorNumber() : "-1";
        String newFloor = "" + (Integer.parseInt(highestFloor) + 1);
        
        //Create the new floor
        Floor floor = new Floor(newFloor);
        floor.SetFullName(this.fullName + " " + floor.GetFloorNumber());
        floors.put(floor.GetFloorNumber(), floor);
        floor.SetBuilding(this);
        
        //Tell the logger
        Log.Log("Added new floor \"" + newFloor + "\" to " + this.fullName);   

        return floor;
    }
    
    /**
     * Removes a <code>Floor</code> object from this <code>Building</code>.
     * @param floor The <code>Floor</code> to be removed
     * @return The previous <code>Floor</code> value before it was removed
     */
    public Floor RemoveFloor(Floor floor) {
        return floors.remove(floor.GetFloorNumber());
    }

    @Override
    public void ObservedStateUpdate(Location location, LocationState locationState, String reason) {
        SetIsMixedState(false);
        for (Floor floor : floors.values()) {
            if (floor.GetState() != locationState)
                SetIsMixedState(true);
        }
    }
}
