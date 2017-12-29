/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IAccessObserver;
import Listeners.IAccessSubject;
import People.Keycard;
import java.util.ArrayList;
import Locations.States.ILocationState;

/**
 * The datatype of a Room. This extends from <code>Location</code> and
 * shares most of the same functionality as the other <code>Location</code>
 * types. This specific child class also has additional functionality as it is
 * the lowest place in the <code>Location</code> hierarchy including being part
 * of a second observer pattern used to report access requests
 * @author Student
 */
public class Room extends Location implements ILocationState, IAccessSubject {

    private transient ArrayList<IAccessObserver> accessObservers;
    /*private Floor floor;*/
    private final String number;
    private RoomType type;
    private IRoomType iType;
    
    /**
     * Creates a new <code>Room</code>. Also:<ul><li>Adds the <code>Logger</code> as
     * an observer of it's state</li><li>Sets it's <code>fullName</code> to the
     * exact same string as the <code>name</code></li></ul>
     * @param number The number to be given to the new <code>Room</code>. Note
     * that <code>Room</code> numbers should re-start at zero for each <code>Floor</code>
     */
    public Room(String number){
        if (number.length() == 1)
            this.number = this.fullName = "0" + number;
        else
            this.number = this.fullName = number;
    }

    /**
     * Returns the room number (name) of the <code>Room</code>.
     * @return The name
     */
    public String GetNumber() {
        return number;
    }
    
    public String GetRoomType() {
        return this.type.GetName();
    }
    
    /**
     *
     * @param floor
     */
    /*public void SetFloor(Floor floor){
        this.floor = floor;
    }*/
    
    /**
     * Called by the room factory. This contains functionality for setting the
     * <code>Rooms</code>s room type and assigning the correct state class.
     * @param type The type of room to set this <code>Room</code> to
     */
    public void SetRoomType(RoomType type) {
        this.type = type;
        this.iType = this.type.GetRoomType();
    }

    /**
     * Tests a <code>Keycard</code> to determine if it currently has access to
     * this <code>Room</code>. Access is based off of 3 different properties:
     * <ul><li>If the <code>Role</code> of the <code>Keycard</code> has
     * permission to access this <code>Room</code> type</li><li>If this
     * <code>Room</code> is in an emergency state or not</li><li>If the
     * <code>Role</code> of the <code>Keycard</code> has access to <code>Room</code>s
     * at the current time.</li></ul>
     * @param keycard The <code>keycard</code> to test for access permissions
     * @return If access was granted or not
     */
    @Override
    public boolean AccessRequest(Keycard keycard) {
        boolean roomAccess = iType.AccessRequest(keycard);
        boolean stateAccess = iState.AccessRequest(keycard);
        boolean timeAccess = keycard.GetRole().HasTimeAccess();
        
        UpdateAccessObservers(keycard, this, roomAccess && stateAccess && timeAccess);
        return roomAccess && stateAccess && timeAccess;
    }

    /**
     * Adds a new <code>IAccessObserver</code> to the object. This observes
     * access requests to the <code>Room</code> no matter if they are successful
     * or fail.
     * @param observer The observer to be added
     * @return If the observer was successfully added
     */
    @Override
    public boolean AddAccessObserver(IAccessObserver observer) {
        if (accessObservers == null) accessObservers = new ArrayList<>();
        if (accessObservers.contains(observer))
            return false;
        else{
            accessObservers.add(observer);
            return accessObservers.contains(observer);
        }
    }

    /**
     * Removes a given <code>IAccessObserver</code> from the object.
     * @param observer The observer to be removed
     * @return If the observer was successfully removed from the object
     */
    @Override
    public boolean RemoveAccessObserver(IAccessObserver observer) {
        return accessObservers.remove(observer);
    }

    /**
     * Alerts all of <code>IAccessObserver</code>s of this object that there has
     * been an access request and passes on the details of it.
     * @param keycard The <code>Keycard</code> which tried to gain access
     * @param room The <code>Room</code> the <code>Keycard</code> tried to gain
     * access to
     * @param wasSuccessful If the access request was successful or not
     */
    @Override
    public void UpdateAccessObservers(Keycard keycard, Room room, boolean wasSuccessful) {
        accessObservers.forEach((observer) -> {
            observer.ObservedAccessUpdate(keycard, room, wasSuccessful);
        });
    }
}
