/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import People.Keycard;
import java.io.Serializable;

/**
 * Forces objects to allow <code>Keycard</code>s to try and gain access to them.
 * @author Student
 */
public interface IRoomType extends Serializable{

    /**
     * Tests a <code>Keycard</code object to see if it qualifies for entry to
     * the <code>Room</code>
     * @param keycard
     * @return
     */
    public boolean AccessRequest(Keycard keycard);
}
