/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.Rooms;

import Locations.Building;
import Locations.Campus;
import Locations.Floor;
import Locations.Room;
import People.Keycard;

/**
 *
 * @author Toby
 */
public class SecureRoom extends Room {
    
    public SecureRoom(Floor floor, String number) {
        super(floor, number);
    }

    @Override
    public boolean AccessRequest(Keycard keycard) {
        
        boolean isGivingAccess = currentState.AccessRequest(keycard);
        
        /* INSERT ROOM-SPECIFIC LOGIC */
        
        Building building = floor.GetBuilding();
        Campus campus = building.GetCampus();
        
        String message = "Access request in " + campus.GetName() + 
                " " + building.GetName() + " " + floor.GetFloorNumber() + 
                number + (isGivingAccess ? ": Access Granted" : ": Access Denied");
        
        super.UpdateObservers(message);
        return isGivingAccess;
    }    
}