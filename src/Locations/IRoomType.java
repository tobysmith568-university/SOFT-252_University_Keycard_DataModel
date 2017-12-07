/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import People.Keycard;

/**
 *
 * @author tsmith10
 */
public interface IRoomType {
    public boolean AccessRequest(Keycard keycard);
}
