/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Locations.Campus;
import People.Keycard;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An object containing an <code>ArrayList</code> of <code>Campus</code>s and a
 * <code>HashMap</code> of <code>Keycard</code>s. This is used so that both
 * can be serialised and saved together in the same file.
 * @author Student
 */
public class SaveObject implements Serializable {
    
    /**
     *
     */
    public ArrayList<Campus> campuses;

    /**
     *
     */
    public HashMap<String, Keycard> keycards;
    
    /**
     *
     * @param campuses
     * @param keycards
     */
    public SaveObject(ArrayList<Campus> campuses, HashMap<String, Keycard> keycards){        
        this.campuses = campuses;
        this.keycards = keycards;
    }    
}
