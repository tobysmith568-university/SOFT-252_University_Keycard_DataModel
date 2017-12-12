/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

import java.io.Serializable;

/**
 *
 * @author tsmith10
 */
public class Keycard implements Serializable{
    private Role role;
    private String name;
    private String cardID;

    /**
     *
     * @param role
     * @param name
     * @param cardID
     */
    public Keycard(Role role, String name, String cardID) {
        this.role = role;
        this.name = name;
        this.cardID = cardID;
    }

    /**
     *
     * @return
     */
    public Role GetRole() {
        return role;
    }

    /**
     *
     * @return
     */
    public String GetName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String GetCardID() {
        return cardID;
    }
}
