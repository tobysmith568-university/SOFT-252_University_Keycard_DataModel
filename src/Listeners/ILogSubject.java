/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

/**
 * The subject in an observer pattern which keeps track of the logged messages
 * in the logger and reports them to any observing objects.
 * @author Student
 */
public interface ILogSubject {

    /**
     * Adds a new <code>ILogObserver</code> to the object.
     * @param observer The observer to be added
     * @return If the observer was successfully added
     */
    public boolean AddLogObserver(ILogObserver observer);

    /**
     * Removes a given <code>ILogObserver</code> from the object.
     * @param observer The observer to be removed
     * @return If the observer was successfully removed from the object
     */
    public boolean RemoveLogObserver(ILogObserver observer);

    /**
     * Updates all of <code>ILogObserver</code>s of this object with a newly
     * logged message
     * @param message The message which has been logged
     */
    public void UpdateLogObservers(String message);
}
