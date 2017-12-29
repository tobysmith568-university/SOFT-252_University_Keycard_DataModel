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
public interface ILogSubject {
    public boolean AddLogObserver(ILogObserver observer);
    public boolean RemoveLogObserver(ILogObserver observer);
    public void UpdateLogObservers(String message);
}
