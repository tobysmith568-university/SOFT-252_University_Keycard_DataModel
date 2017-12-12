/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Control.Log;
import Listeners.IStateSubject;
import Locations.States.ILocationState;
import Locations.States.LocationState;
import java.util.ArrayList;
import Listeners.IStateObserver;
import java.io.Serializable;


/**
 *
 * @author tsmith10
 */
public abstract class Location implements IStateSubject, Serializable{

    /**
     *
     */
    protected transient ArrayList<IStateObserver> stateObservers;    

    /**
     *
     */
    protected String fullName;    

    /**
     *
     */
    protected LocationState state;

    /**
     *
     */
    protected ILocationState iState;
    
    private static IStateObserver log = Log.Logger();
    
    /**
     *
     */
    protected Location(){
        stateObservers = new ArrayList<>();
        AddStateObserver(log);
    }
    
    /**
     *
     * @param newState
     */
    public void SetRoomState(LocationState newState){
        ActualSetRoomState(newState);
        UpdateStateObservers(this, state);
    }
    
    /**
     *
     * @param newState
     */
    protected void ActualSetRoomState(LocationState newState){
        state = newState;
        iState = state.GetLocationState();
    }
    
    /**
     *
     * @return
     */
    public String GetFullName(){
        return fullName;
    }
    
    /**
     *
     * @return
     */
    public LocationState GetState(){
        return state;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public abstract Location GetChild(String name);

    /**
     *
     * @return
     */
    public abstract Location[] GetAllChildren();
    
    /**
     *
     * @param name
     */
    public void SetFullName(String name){
        this.fullName = name;
    }

    /**
     *
     * @param observer
     * @return
     */
    @Override
    public final boolean AddStateObserver(IStateObserver observer) {
        if (stateObservers == null) stateObservers = new ArrayList<>();
        if (stateObservers.contains(observer))
            return false;
        else {
            stateObservers.add(observer);
            return stateObservers.contains(observer);
        }
    }    

    /**
     *
     * @param observer
     * @return
     */
    @Override
    public final boolean RemoveStateObserver(IStateObserver observer) {
        return stateObservers.remove(observer);
    }

    /**
     *
     * @param location
     * @param locationState
     */
    @Override
    public final void UpdateStateObservers(Location location, LocationState locationState) {
        stateObservers.forEach((observer) -> {
            observer.ObservedStateUpdate(location, locationState);
        });
    }
}
