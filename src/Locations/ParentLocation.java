/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IStateObserver;

/**
 * A place in the hierarchy of locations <u>apart from the lowest</u>. This contains
 * the same functionality as <code>Location</code> but also contains methods
 * for finding child objects in the <code>Location</code> hierarchy.
 * @author Student
 */
public abstract class ParentLocation extends Location implements IStateObserver {   
    
    
    
    /**
     * Finds and returns a specific child <code>Location</code> of this object.
     * @param name The name of the child object to find
     * @return The child object, if it is found
     */
    public abstract Location GetChild(String name);

    /**
     * Finds and returns all the child <code>Location</code> objects of this
     * object.
     * @return The child objects
     */
    public abstract Location[] GetAllChildren();
}
