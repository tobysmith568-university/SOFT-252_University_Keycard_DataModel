/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Control.Log;
import Control.RoomFactory;
import Locations.States.LocationState;
import java.util.HashMap;

/**
 * The datatype of a Floor. This extends from <code>Location</code> and
 * shares most of the same functionality as the other <code>Location</code>
 * types.
 * @author Student
 */
public class Floor extends ParentLocation {
    private Building building;
    private final String floorNumber;
    private final HashMap<String, Room> rooms = new HashMap<>();
    
    /**
     * Creates a new <code>Floor</code>. Also:<ul><li>Adds the <code>Logger</code> as
     * an observer of it's state</li><li>Sets it's <code>fullName</code> to the
     * exact same string as the <code>name</code></li></ul>
     * @param floorNumber The number to be given to the new <code>Floor</code>
     */
    public Floor(String floorNumber){
        this.floorNumber = this.fullName = floorNumber;        
    }

    /**
     * Gets and returns the parent <code>Building</code> of this object.
     * @return The <code>Building</code>
     */
    public Building GetBuilding() {
        return this.building;
    }

    /**
     * Returns the floor number (name) of the <code>Floor</code>.
     * @return The name
     */
    public String GetFloorNumber() {
        return floorNumber;
    }  
    
    /**
     * Finds and returns a specific child <code>Room</code> of this object.
     * @param name The name of the child object to find
     * @return The child object, if it is found
     */
    @Override
    public Room GetChild(String name){
        if (!rooms.containsKey(name))
            return null;
        else
            return rooms.get(name);
    }
    
    /**
     * Finds and returns all the child <code>Room</code> objects of this
     * object.
     * @return The child objects
     */
    @Override
    public Room[] GetAllChildren(){
        return rooms.values().toArray(new Room[0]);
    }
    
    /**
     * Sets the parent <code>Building</code> object of this <code>Floor</code>
     * @param building The parent <code>Building</code>
     */
    public void SetBuilding(Building building){
        this.building = building;
        AddStateObserver(building);
    }

    /**
     * Sets if this object is in a mixed state.
     * @param isMixedState <code>True</code> if this should be in a mixed state
     */
    @Override
    public void SetIsMixedState(boolean isMixedState) {
        super.SetIsMixedState(isMixedState);
        building.SetIsMixedState(isMixedState);
    }

    /**
     * Called by the <code>SetRoomState()</code>. This contains the actual
     * functionality for Changing the <code>Floor</code>s state.<p>The reason
     * this is separated out into a separate <code>protected</code> method
     * rather than doing in in the <code>public</code> one is so that parent and
     * child <code>Location</code>s can call the protected one without it going
     * to the <code>Logger</code>.
     * @param newState The new state the <code>Location</code> is set to
     */
    @Override
    protected void ActualSetRoomState(LocationState newState, String reason) {
        super.ActualSetRoomState(newState, reason);
        rooms.values().forEach(room ->
            ((Room)room).ActualSetRoomState(newState, reason));
    }
    
    /**
     * Adds a new child <code>Room</code> to this <code>Floor</code>.
     * This method returns the new <code>Room</code> but it also
     * automatically assigns it to this object so you do not need to do that
     * yourself. The new <code>Room</code> has it's <code>Name</code>
     * automatically assigned to it based on the next number
     * following the previously made <code>Room</code>.
     * @param type The type of <code>Room</code> that should be created
     * @return The new <code>Floor</code>
     */
    public Room AddRoom(RoomType type) {
        Room[] allRooms = rooms.values().toArray(new Room[0]);
        String highestRoom = (allRooms.length > 0) ? allRooms[allRooms.length - 1].GetNumber() : "-1";
        String newRoom = Integer.toString(Integer.parseInt(highestRoom) + 1);
                
        Room room = RoomFactory.Create(newRoom, type);
        rooms.put(room.GetNumber(), room);
        room.SetFloor(this);
        room.SetFullName(this.fullName + room.GetNumber());
        
        Log.Log("Added new room \"" + newRoom + "\" (" + type.GetName() + ") to " + this.fullName);
        
        return room;
    }
    
    /**
     * Removes a <code>Room</code> object from this <code>Floor</code>.
     * @param room The <code>Room</code> to be removed
     * @return The previous <code>Room</code> value before it was removed
     */
    public Room RemoveRoom(Room room){        
        return rooms.remove(room.GetNumber());
    }

    @Override
    public void ObservedStateUpdate(Location location, LocationState locationState, String reason) {
        SetIsMixedState(false);
        for (Room room : rooms.values()) {
            if (room.GetState() != locationState)
                SetIsMixedState(true);
        }
    }
}
