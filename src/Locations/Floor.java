/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IObserver;
import Locations.States.EmergencyStatus;
import Locations.States.IRoomState;
import java.util.ArrayList;

/**
 *
 * @author tsmith10
 */
public class Floor extends Location {
    private String floorNumber;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    
    public Floor(String floorNumber){
        this.floorNumber = floorNumber;
    }
    
    public void CreateRoom(String roomNumber) {
        throw new UnsupportedOperationException();
    }    

    @Override
    public void SetRoomState(EmergencyStatus newState) {
        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).SetRoomState(newState);
        }
    }

    @Override
    public void UpdateObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void RegisterObserver(IObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void RemoveObserver(IObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ObservedUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
