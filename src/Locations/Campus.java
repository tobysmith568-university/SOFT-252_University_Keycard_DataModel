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
 * The datatype of a campus. This extends from <code>Location</code> and shares
 * most of the same functionality as the other <code>Location</code> types.
 * @author Student
 */
public class Campus extends ParentLocation{
    private final String name;
    private final HashMap<String, Building> buildings;
    
    /**
     * Creates a new <code>Campus</code>. Also:<ul><li>Adds the <code>Logger</code> as
     * an observer of it's state</li><li>Sets it's <code>fullName</code> to the
     * exact same string as the <code>name</code></li></ul>
     * @param name The name of the new <code>Campus</code>
     */
    public Campus(String name) {
        super();
        this.buildings = new HashMap<>();
        this.name = this.fullName = name;
    }

    /**
     * Returns the name of the <code>Campus</code>
     * @return The name
     */
    public String GetName() {
        return name;
    }
    
    /**
     * Finds and returns a specific child <code>Building</code> of this object.
     * @param name The name of the child object to find
     * @return The child object, if it is found
     */
    @Override
    public Building GetChild(String name) {
        return buildings.get(name);
    }
    
    /**
     * Finds and returns all the child <code>Buiding</code> objects of this
     * object.
     * @return The child objects
     */
    @Override
    public Building[] GetAllChildren() {
        return buildings.values().toArray(new Building[0]);
    }
    
    /**
     * Called by the <code>SetRoomState()</code>. This contains the actual
     * functionality for Changing the <code>Campus</code>es state.<p>The reason
     * this is separated out into a separate <code>protected</code> method
     * rather than doing in in the <code>public</code> one is so that parent and
     * child <code>Location</code>s can call the protected one without it going
     * to the <code>Logger</code>.
     * @param newState The new state the <code>Location</code> is set to
     */
    @Override
    protected void ActualSetRoomState(LocationState newState, String reason) {
        super.ActualSetRoomState(newState, reason);
        buildings.values().forEach(building ->
            ((Building)building).ActualSetRoomState(newState, reason));
    }
    
    /**
     * Adds a new child <code>Building</code> to this <code>Campus</code>.
     * This method returns the new <code>Building</code> but it also
     * automatically assigns it to this object so you do not need to do that
     * yourself. This method will fail and return <code>null</code> if a
     * <code>Building</code> already exists as a child of this object with the
     * same name.
     * @param name The name of the <code>Building</code> to add
     * @param shortCode A short code of the <code>Building</code> to add
     * @return The new <code>Building</code> or <code>null</code> if it already
     * existed
     */
    public Building AddBuilding(String name, String shortCode) {
        //If the building already exists, return null
        if (buildings.containsKey(name))
            return null;
        
        //Create the building
        Building building = new Building(name, shortCode);
        building.SetFullName(this.name + " " + shortCode);
        buildings.put(building.GetName(), building);
        building.SetCampus(this);
        
        //Tell the logger
        Log.Log("Added new building \"" + name + "\" (" + shortCode + ") to " + this.fullName);
        
        return building;
    }
    
    /**
     * Removes a <code>Building</code> object from this <code>Campus</code>.
     * @param building The <code>Building</code> to remove
     * @return The removed <code>Building</code> or null if it does not exist
     */
    public Building RemoveBuilding(Building building) {
        Building removed = buildings.remove(building.GetName());
        
        //If removal was successful, tell the logger
        if (removed != null)
            Log.Log("Removed building \"" + removed.GetName() + "\" (" + removed.GetShortcode() + ") from " + this.fullName);
        
        return removed;
    }

    @Override
    public void ObservedStateUpdate(Location location, LocationState locationState, String reason) {
        SetIsMixedState(false);
        for (Building building : buildings.values()) {
            if (building.GetState() != locationState)
                SetIsMixedState(true);
        }
    }
}
