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
public class ResearchLab implements IRoomType {

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
        Role[] roles = keycard.GetRoles();
        
        for (Role role : roles) {
            switch (role) {
                case STAFFMEMBER:
                case CLEANER:
                case MANAGER:
                case SECURITY:
                case EMERGENCYRESPONDER:
                        return true;
                default:
                    break;
            }
        }
        return false;
    }
}
