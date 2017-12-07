/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

/**
 *
 * @author tsmith10
 */
public class Keycard {
    private Role role;
    private String cardID;

    public Keycard(Role role, String cardID) {
        this.role = role;
        this.cardID = cardID;
    }

    public Role GetRole() {
        return role;
    }
}
