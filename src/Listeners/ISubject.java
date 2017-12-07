/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

/**
 *
 * @author Toby
 */
public interface ISubject {    
    public <T> void UpdateObservers(T updateInformation);
    public boolean RegisterObserver(IObserver observer);
    public boolean RemoveObserver(IObserver observer);
}
