/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.States;

import People.Keycard;
import People.Role;

/**
 * A non-default state for a room. Designed to allow the emergency access use of a
 * room only. This means denying access to a majority of people but allows
 * access to emergency responders and other specific university <code>Roles</code>s
 * @author Student
 */
public class EmergencyState implements ILocationState {

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
            switch (role) {
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
