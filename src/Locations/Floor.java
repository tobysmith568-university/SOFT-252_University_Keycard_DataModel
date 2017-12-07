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
public class Floor extends Location {
    private Building building;
    private String floorNumber;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    
    public Floor(Building building, String floorNumber){
        this.building = building;
        this.floorNumber = floorNumber;
    }
    
    public void CreateRoom(Floor floor, String roomNumber) {
        throw new UnsupportedOperationException();
    }    

    @Override
    public void SetRoomState(EmergencyStatus newState) {
        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).SetRoomState(newState);
        }
    }

    public Building GetBuilding() {
        return this.building;
    }

    public String GetFloorNumber() {
        return floorNumber;
    }
}
