/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.States;

import People.Keycard;
import People.Role;

/**
 * The default state for a room. Designed to allow the normal use of a room and
 * deny access to emergency responders.
 * @author Student
 */
public class NormalState implements ILocationState {
    
    /**
     * Tests a <code>Keycard</code> object to see if it qualifies for entry to
     * the <code>Room</code> under the <code>Room</code>s current state.
     * @param keycard The <code>Keycard</code> trying to gain access to the
     * <code>Room</code>
     * @return If the <code>Keycard</code> is successfully given access to this
     * object or not
     */
    @Override
    public boolean AccessRequest(Keycard keycard) {
        for (Role role : keycard.GetRoles()) {
            switch (role){
                case VISITOR:
                case STAFFMEMBER:
                case STUDENT:
                case CLEANER:
                case MANAGER:
                case SECURITY:
                        return true;
                default:
                    break;
            }
        }
        return false;
    }
}
