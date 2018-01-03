/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This object stores all the needed information about a person within the
 * university. This is the information used by <code>Room</code>s to determine
 * if it will grant access or not.
 * @author Student
 */
public class Keycard implements Serializable {
    private final ArrayList<Role> roles;
    private String name;
    private final String cardID;

    /**
     * Creates a new <code>Keycard</code>.
     * @param roles The types of permissions the <code>Keycard</code> should have
     * @param name The name of the owner of the <code>Keycard</code>
     * @param cardID A unique ID for the card
     */
    public Keycard(Role roles[], String name, String cardID) {
        this.roles = new ArrayList<>();
        this.roles.addAll(Arrays.asList(roles));
        this.name = name;
        this.cardID = cardID;
    }

    /**
     * Creates a new <code>Keycard</code>.
     * @param role The type of permissions the <code>Keycard</code> should have
     * @param name The name of the owner of the <code>Keycard</code>
     * @param cardID A unique ID for the card
     */
    public Keycard(Role role, String name, String cardID) {
        this.roles = new ArrayList<>();
        this.roles.add(role);
        this.name = name;
        this.cardID = cardID;
    }

    /**
     * Returns the <code>Role</code> of this <code>Keycard</code>.
     * @return The <code>Role</code>
     */
    public Role[] GetRoles() {
        return roles.toArray(new Role[0]);
    }
    
    /**
     * Returns a <code>String</code> of all this <code>Keycard</code>s
     * <code>Role</code>s names separated by a given delimiting <code>String</code>.
     * @param delimiter What will separate the roles
     * @return The combined <code>String</code>
     */
    public String GetRolesString(String delimiter) {
        String output = "";
        output = roles.stream().map(role -> delimiter + role.GetName()).reduce(output, String::concat);
        return output.substring(delimiter.length());
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

    /**
     * Adds a new <code>Role</code> to this <code>Keycard</code>.
     * @param role The new <code>Role</code>
     */
    public void AddRole(Role role) {
        if (!this.roles.contains(role))
            this.roles.add(role);
    }

    /**
     * Removes a <code>Role</code> from this <code>Keycard</code>.
     * @param role The <code>Role</code> to remove
     * @return <code>True</code> if the <code>Keycode</code> does not have the
     * given <code>Role</code>. This could be due to it being removed or it may
     * never have had it.
     */
    public boolean RemoveRole(Role role) {
        if (!this.roles.contains(role))
            return true;
        return this.roles.remove(role);
    }

    /**
     * Sets the current name of the owner of this <code>Keycard</code>.
     * @param name The new name of the owner
     */
    public void SetName(String name) {
        this.name = name;
    }
}
