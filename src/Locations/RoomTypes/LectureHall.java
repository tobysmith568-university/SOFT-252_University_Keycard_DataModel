/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.RoomTypes;

import Locations.IRoomType;
import People.Keycard;
import People.Role;

/**
 *
 * @author tsmith10
 */
public class LectureHall implements IRoomType {

    @Override
    public boolean AccessRequest(Keycard keycard) {
        return true;
    }
    
}
