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
 *
 * @author tsmith10
 */
public class Room extends Location implements ILocationState, IAccessSubject {

    /**
     *
     */
    protected transient ArrayList<IAccessObserver> accessObservers;

    /**
     *
     */
    protected Floor floor;

    /**
     *
     */
    protected String number;
    
    /**
     *
     */
    protected RoomType type;

    /**
     *
     */
    protected IRoomType iType;
    
    /**
     *
     * @param number
     */
    public Room(String number){
        if (number.length() == 1)
            this.number = this.fullName = "0" + number;
        else
            this.number = this.fullName = number;
    }

    /**
     *
     * @return
     */
    public String GetNumber() {
        return number;
    }
    
    /**
     *
     * @param floor
     */
    public void SetFloor(Floor floor){
        this.floor = floor;
    }
    
    /**
     *
     * @param type
     */
    public void SetRoomType(RoomType type) {
        this.type = type;
        this.iType = this.type.GetRoomType();
    }

    /**
     *
     * @param keycard
     * @return
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
     *
     * @param observer
     * @return
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
     *
     * @param observer
     * @return
     */
    @Override
    public boolean RemoveAccessObserver(IAccessObserver observer) {
        return accessObservers.remove(observer);
    }

    /**
     *
     * @param keycard
     * @param room
     * @param wasSuccessful
     */
    @Override
    public void UpdateAccessObservers(Keycard keycard, Room room, boolean wasSuccessful) {
        accessObservers.forEach((observer) -> {
            observer.ObservedAccessUpdate(keycard, room, wasSuccessful);
        });
    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public Location GetChild(String name) {
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Building[] GetAllChildren(){
        return new Building[0];
    }
}
