/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import People.Keycard;
import java.io.Serializable;

/**
 *
 * @author tsmith10
 */
public interface IRoomType extends Serializable{
    public boolean AccessRequest(Keycard keycard);
}
