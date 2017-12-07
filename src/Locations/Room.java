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
import java.util.ArrayList;

/**
 *
 * @author tsmith10
 */
public abstract class Room extends Location implements IRoomState, ISubject{
    protected Floor floor;
    protected String number;
    private EmergencyStatus status;
    public IRoomState currentState;
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    
    @Override
    public void SetRoomState(EmergencyStatus newState){
        status = newState;
        currentState = status.getEmergencyState();
    }
    
    public Room(Floor floor, String number){
        this.floor = floor;
        this.number = number;
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
