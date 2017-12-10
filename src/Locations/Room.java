/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IAccessObserver;
import Listeners.IAccessSubject;
import Listeners.IStateObserver;
import People.Keycard;
import java.util.ArrayList;
import Locations.States.ILocationState;

/**
 *
 * @author tsmith10
 */
public class Room extends Location implements ILocationState, IAccessSubject {
    protected ArrayList<IAccessObserver> accessObservers = new ArrayList<>();
    protected Floor floor;
    protected String number;
    
    protected RoomType type;
    protected IRoomType iType;    
    
    public Room(String number){
        if (number.length() == 1)
            this.number = "0" + number;
        else
            this.number = number;
    }

    public String GetNumber() {
        return number;
    }
    
    public void SetFloor(Floor floor){
        this.floor = floor;
    }
    
    public void SetRoomType(RoomType type) {
        this.type = type;
        this.iType = this.type.GetRoomType();
    }

    @Override
    public boolean AccessRequest(Keycard keycard) {
        boolean roomAccess = iType.AccessRequest(keycard);
        boolean stateAccess = iState.AccessRequest(keycard);
        boolean timeAccess = keycard.GetRole().HasTimeAccess();
        
        UpdateAccessObservers(keycard, this, roomAccess && stateAccess && timeAccess);
        return roomAccess && stateAccess && timeAccess;
    }

    @Override
    public boolean AddAccessObserver(IAccessObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean RemoveAccessObserver(IAccessObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void UpdateAccessObservers(Keycard keycard, Room room, boolean wasSuccessful) {
        for (IAccessObserver iAccessObserver : IAccessObservers) {
            iAccessObserver.
        }
    }
}
