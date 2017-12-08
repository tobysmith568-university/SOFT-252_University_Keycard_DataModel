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
    
    public Floor(String floorNumber){
        this.floorNumber = floorNumber;
    }

    public Building GetBuilding() {
        return this.building;
    }

    public String GetFloorNumber() {
        return floorNumber;
    }   

    @Override
    public void SetRoomState(EmergencyStatus newState) {
        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).SetRoomState(newState);
        }
    }
    
    public Room AddRoom(RoomType type) {
        Room room = new Room(Integer.toString(rooms.size()), type);
        rooms.add(room);
        return room;
    } 
}
