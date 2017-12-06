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
public class Building extends Location{
    private String name;
    private String shortCode;
    private ArrayList<Floor> floors = new ArrayList<Floor>();
    
    public Building(String name, String shortCode){
        this.name = name;
        this.shortCode = shortCode;
    }
    
    public void CreateFloor(String floorNumber){
        floors.add(new Floor(floorNumber));
    }

    @Override
    public void SetRoomState(EmergencyStatus newState) {
        for (int i = 0; i < floors.size(); i++) {
            floors.get(i).SetRoomState(newState);
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
