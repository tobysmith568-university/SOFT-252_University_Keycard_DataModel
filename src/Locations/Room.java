/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IAccessSubject;
import Listeners.IObserver;
import Locations.States.LocationState;
import People.Keycard;
import java.util.ArrayList;
import Locations.States.ILocationState;

/**
 *
 * @author tsmith10
 */
public class Room extends Location implements ILocationState, IAccessSubject {
    protected Floor floor;
    protected String number;
    
    protected RoomType type;
    private IRoomType iType;
    
    protected LocationState state;
    private ILocationState iState;
    
    private ArrayList<IObserver> observers = new ArrayList<>();
    
    public Room(String number){
        if (number.length() == 1)
            this.number = "0" + number;
        else
            this.number = number;
    }

    public String GetNumber() {
        return number;
    }
    
    public LocationState GetState(){
        return state;
    }
    
    public void SetFloor(Floor floor){
        this.floor = floor;
    }
    
    public void SetRoomType(RoomType type) {
        this.type = type;
        this.iType = this.type.GetRoomType();
    }

    @Override
    public String GetFullName() {
        return fullName;
    }
    
    @Override
    public void SetRoomState(LocationState newState){
        state = newState;
        iState = state.GetLocationState();
    }

    @Override
    public boolean AccessRequest(Keycard keycard) {
        Building building = floor.GetBuilding();
        Campus campus = building.GetCampus();
        
        boolean roomAccess = iType.AccessRequest(keycard);
        boolean stateAccess = iState.AccessRequest(keycard);
        boolean timeAccess = keycard.GetRole().HasTimeAccess();
        
        UpdateObservers(keycard, this, roomAccess && stateAccess && timeAccess);
        return roomAccess && stateAccess && timeAccess;
    }

    @Override
    public void UpdateObservers(Keycard keycard, Room room, boolean wasSuccessful) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
