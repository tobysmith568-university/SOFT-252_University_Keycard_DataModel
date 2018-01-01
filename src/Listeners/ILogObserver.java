/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

/**
 * The observer in an observer pattern which keeps track of the logged messages
 * in the logger and reports them to any observing objects.
 * @author Student
 */
public interface ILogObserver {

    /**
     * Called by the subject of the observer pattern when it has a newly logged
     * message
     * @param message The message which has been logged
     */
    public void ObservedLogUpdate(String message);
}
