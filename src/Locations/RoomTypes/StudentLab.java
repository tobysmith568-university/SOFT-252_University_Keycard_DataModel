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
 * @author Student
 */
public class StudentLab implements IRoomType {

    /**
     *
     * @param keycard
     * @return
     */
    @Override
    public boolean AccessRequest(Keycard keycard) {
        Role role = keycard.GetRole();
        
        switch (role){
            case STAFFMEMBER:
            case STUDENT:
            case CLEANER:
            case MANAGER:
            case SECURITY:
            case EMERGENCYRESPONDER:
                    return true;
            default:
                return false;
        }
    }
    
}
