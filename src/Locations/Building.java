/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Locations.States.LocationState;
import java.util.HashMap;
import java.util.Iterator;

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
    private final HashMap<String, Floor> floors = new HashMap<>();
    
    /**
     * Creates a new <code>Building</code>. Also:<ul><li>Adds the <code>Logger</code> as
     * an observer of it's state</li><li>Sets it's <code>fullName</code> to the
     * exact same string as the <code>name</code></li></ul>
     * @param name The name of the new <code>Building</code>
     * @param shortCode The shortcode to be assigned to the new <code>Building</code>
     */
    public Building(String name, String shortCode){
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
     * Finds and returns a specific child <code>Floor</code> of this object.
     * @param name The name of the child object to find
     * @return The child object, if it is found
     */
    @Override
    public Floor GetChild(String name){
        if (!floors.containsKey(name))
            return null;
        else{
            return floors.get(name);
        }
    }
    
    /**
     * Finds and returns all the child <code>Floor</code> objects of this
     * object.
     * @return The child objects
     */
    @Override
    public Floor[] GetAllChildren(){
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

    @Override
    public void SetIsMixedState(boolean isMixedState) {
        super.SetIsMixedState(isMixedState);
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
    protected void ActualSetRoomState(LocationState newState) {
        super.ActualSetRoomState(newState);
        floors.values().forEach(floor ->
            ((Floor)floor).ActualSetRoomState(newState)); 
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
    public Floor AddFloor(){
        Floor[] allFloors = floors.values().toArray(new Floor[0]);
        String highestFloor = (allFloors.length > 0) ? allFloors[allFloors.length - 1].GetFloorNumber() : "-1";
        
        Floor floor = new Floor(Integer.toString(Integer.parseInt(highestFloor) + 1));
        floor.SetFullName(this.fullName + " " + floor.GetFloorNumber());
        floors.put(floor.GetFloorNumber(), floor);
        floor.SetBuilding(this);
        
        return floor;
    }
    
    public Floor RemoveFloor(Floor floor){
        if (!floors.containsValue(floor))
            return null;
        
        return floors.remove(floor.GetFloorNumber());
    }

    @Override
    public void ObservedStateUpdate(Location location, LocationState locationState) {
        SetIsMixedState(false);
        for (Floor floor : floors.values()) {
            if (floor.GetState() != locationState)
                SetIsMixedState(true);
        }
    }
}
