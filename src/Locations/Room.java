/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IObserver;
import Listeners.ISubject;
import Locations.States.EmergencyStatus;
import Locations.States.IRoomState;
import People.Keycard;
import java.util.ArrayList;

/**
 *
 * @author tsmith10
 */
public class Room extends Location implements IRoomState, ISubject {
    protected Floor floor;
    protected String number;
    
    protected RoomType type;
    private IRoomType roomType;
    
    protected EmergencyStatus status;
    private IRoomState currentState;
    
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    
    public Room(Floor floor, String number, RoomType roomType){
        this.floor = floor;
        this.number = number;
        this.type = roomType;
        this.roomType = roomType.GetRoomType();
    }
    
    @Override
    public void SetRoomState(EmergencyStatus newState){
        status = newState;
        currentState = status.getEmergencyState();
    }

    @Override
    public boolean AccessRequest(Keycard keycard) {   
        Building building = floor.GetBuilding();
        Campus campus = building.GetCampus();
        
        boolean roomAccess = roomType.AccessRequest(keycard);
        boolean stateAccess = currentState.AccessRequest(keycard);
        boolean timeAccess = keycard.GetRole().HasTimeAccess();
        
        String message = "Access request in " + campus.GetName() + 
                " " + building.GetName() + " " + floor.GetFloorNumber() + 
                number + ": " + (roomAccess && stateAccess && timeAccess);
        
        UpdateObservers(message);
        return roomAccess && stateAccess && timeAccess;
    }

    @Override
    public boolean RemoveObserver(IObserver observer){
        return observers.remove(observer);
    }

    @Override
    public boolean RegisterObserver(IObserver observer){
        if (!observers.contains(observer))
            return observers.add(observer);
        return false;
    }

    @Override
    public <T> void UpdateObservers(T updateInformation){
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).ObservedUpdate(updateInformation);
        }
    }
}
