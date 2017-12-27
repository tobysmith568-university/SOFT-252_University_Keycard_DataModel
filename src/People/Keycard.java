/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

import java.io.Serializable;
import java.util.Calendar;

/**
 * This object stores all the needed information about a person within the
 * university. This is the information used by <code>Room</code>s to determine
 * if it will grant access or not.
 * @author Student
 */
public class Keycard implements Serializable{
    private Role role;
    private String name;
    private String cardID;

    public Keycard() {
        this.role = null;
        this.name = "";
        this.cardID = "";
    }    

    /**
     * Creates a new <code>Keycard</code>.
     * @param role The type of permissions the <code>Keycard</code> should have
     * @param name The name of the owner of the <code>Keycard</code>
     * @param cardID A unique ID for the card
     */
    public Keycard(Role role, String name, String cardID) {
        this.role = role;
        this.name = name;
        this.cardID = CreateUniqueID();
    }

    /**
     * Returns the <code>Role</code> of this <code>Keycard</code>.
     * @return The <code>Role</code>
     */
    public Role GetRole() {
        return role;
    }

    /**
     * Returns the name of this owner of this <code>Keycard</code>.
     * @return The owner's name
     */
    public String GetName() {
        return name;
    }

    /**
     * Returns the unique ID of this <code>Keycard</code>.
     * @return The unique ID
     */
    public String GetCardID() {
        return cardID;
    }
    
    private String CreateUniqueID(){
        /*
          getTimeInMillis() returns the number of miliseconds since Epoch (1/1/1970).
          The hardcoded number is the number of mileseconds between then and 1/1/2017.
          I minus this off so that the unique IDs are slightly shorter
        */
        
        return "" + (Calendar.getInstance().getTimeInMillis() - 1483176744000L);
        //1483176744000
        //1514396339701
        //31219931913
    }
}
