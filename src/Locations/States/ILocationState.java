/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.States;

import People.Keycard;
import java.io.Serializable;

/**
 * The state a <code>Room</code> is currently in. This defines how the <code>Room</code>
 * interacts with <code>Keycard</code>s.
 * @author Student
 */
public interface ILocationState extends Serializable {

    /**
     * Tests a <code>Keycard</code> object to see if it qualifies for entry to
     * the <code>Room</code> under the <code>Room</code>s current state.
     * @param keycard The <code>Keycard</code> trying to gain access to the
     * <code>Room</code>
     * @return If the <code>Keycard</code> is successfully given access to this
     * object or not
     */
    public boolean AccessRequest(Keycard keycard);
}
