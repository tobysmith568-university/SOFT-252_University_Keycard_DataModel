/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.RoomTypes;

import Locations.IRoomType;
import People.Keycard;

/**
 * This room is used to deliver lectures to students.
 * @author Student
 */
public class LectureHall implements IRoomType {

    /**
     * Tests a <code>Keycard</code> object to see if it qualifies for entry to
     * the <code>Room</code>.
     * @param keycard The <code>Keycard</code> trying to gain access to the
     * <code>Room</code>
     * @return If the <code>Keycard</code> is successfully given access to this
     * object or not
     */
    @Override
    public boolean AccessRequest(Keycard keycard) {
        return true;
    }
    
}
