/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IStateSubject;
import Locations.States.ILocationState;
import Locations.States.LocationState;
import java.util.ArrayList;
import Listeners.IStateObserver;


/**
 *
 * @author tsmith10
 */
public abstract class Location implements IStateSubject{
    protected ArrayList<IStateObserver> stateObservers = new ArrayList<>();    
    protected String fullName;    
    protected LocationState state;
    protected ILocationState iState;
    
    public void SetRoomState(LocationState newState){
        state = newState;
        iState = state.GetLocationState();
        UpdateStateObservers(this, state);
    }  
    
    public String GetFullName(){
        return fullName;
    }
    
    public LocationState GetState(){
        return state;
    }
    
    public void SetFullName(String name){
        this.fullName = name;
    }

    @Override
    public boolean AddStateObserver(IStateObserver observer) {
        if (observer != null && !stateObservers.contains(observer)) {
            stateObservers.add(observer);
            return true;
        }
        return false;
    }    

    @Override
    public boolean RemoveStateObserver(IStateObserver observer) {
        return stateObservers.remove(observer);
    }

    @Override
    public void UpdateStateObservers(Location location, LocationState locationState) {
        stateObservers.forEach((stateObserver) -> {
            stateObserver.ObservedStateUpdate(location, locationState);
        });
    }
}
