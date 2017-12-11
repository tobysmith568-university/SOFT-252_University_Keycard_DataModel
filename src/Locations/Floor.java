/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Control.RoomFactory;
import Locations.States.LocationState;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author tsmith10
 */
public class Floor extends Location {
    private Building building;
    private String floorNumber;
    private HashMap<String, Room> rooms = new HashMap<>();
    
    public Floor(String floorNumber){
        this.floorNumber = this.fullName = floorNumber;        
    }

    public Building GetBuilding() {
        return this.building;
    }

    public String GetFloorNumber() {
        return floorNumber;
    }  

    @Override
    protected void ActualSetRoomState(LocationState newState) {
        super.ActualSetRoomState(newState);
        Iterator iterator = rooms.values().iterator();
        while (iterator.hasNext()){
            ((Room)iterator.next()).ActualSetRoomState(newState);
        }
    }
    
    public Room AddRoom(RoomType type) {
        Room room = RoomFactory.Create(Integer.toString(rooms.size()), type, this);
        rooms.put(room.GetNumber(), room);
        room.SetFullName(this.fullName + room.GetNumber());
        return room;
    }
}
