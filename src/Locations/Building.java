/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

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
    
    public Building(String name, String shortCode){
        this.name = name;
        this.shortCode = shortCode;
    }

    public String GetName() {
        return name;
    }

    public Campus GetCampus() {
        return campus;
    }

    @Override
    public void SetRoomState(EmergencyStatus newState) {
        for (int i = 0; i < floors.size(); i++) {
            floors.get(i).SetRoomState(newState);
        }
    }
    
    public Floor AddFloor(){
        Floor floor = new Floor(Integer.toString(floors.size()));
        floors.add(floor);
        return floor;
    }
}
