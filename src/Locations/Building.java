/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IObserver;
import Locations.States.EmergencyStatus;
import java.util.ArrayList;

/**
 *
 * @author tsmith10
 */
public class Building extends Location{
    private Campus campus;
    private String name;
    private String shortCode;
    private ArrayList<Floor> floors = new ArrayList<Floor>();
    
    public Building(Campus campus, String name, String shortCode){
        this.campus = campus;
        this.name = name;
        this.shortCode = shortCode;
    }
    
    public void CreateFloor(Building building, String floorNumber){
        floors.add(new Floor(building, floorNumber));
    }

    @Override
    public void SetRoomState(EmergencyStatus newState) {
        for (int i = 0; i < floors.size(); i++) {
            floors.get(i).SetRoomState(newState);
        }
    }

    public String GetName() {
        return name;
    }

    public Campus GetCampus() {
        return campus;
    }
}
