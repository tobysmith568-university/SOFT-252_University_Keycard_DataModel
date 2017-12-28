/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Listeners.IAccessObserver;
import Locations.Location;
import Locations.Room;
import Locations.States.LocationState;
import People.Keycard;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import Listeners.IStateObserver;
import java.io.Serializable;

/**
 * Logs messages to the console as well as to a file.
 * A new file is used for each day and is named in the format "Log for dd-MM-yy".
 * @author Student
 */
public class Log implements IStateObserver, IAccessObserver, Serializable{
    
    private static Log singleton;
    private DateTimeFormatter messageFormat = DateTimeFormatter.ofPattern("'['dd/MM/yy'] ['HH:mm:ss']'");
    private DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd-MM-yy");
    
    private Log(){
    }
    
    /**
     * Gets the Logger instance.
     * @return The Logger instance
     */
    public static Log Logger(){
        if (singleton == null)
            singleton = new Log();
        
        return singleton;
    }
    
    private String LogPrefix(){
        return LocalDateTime.now().format(messageFormat);
    }
    
    private void LogAccess(Keycard keycard, Room room, boolean wasSuccessful) {
        String output = LogPrefix() + " " + room.GetFullName() + " (" + room.GetState().GetName() +
                (wasSuccessful ? ") granted access to " : ") denied access to ") +
                keycard.GetName() + " (" + keycard.GetCardID() + ")";
        
        LogToFile(output);
        System.out.println(output);
    }
    
    private void LogEmergency(Location location, LocationState state) {
        String output = LogPrefix() + " " + location.GetFullName() + " is now in the state: " + 
                state.GetName();
                
        LogToFile(output);
        System.out.println(output);
    }
    
    /**
     * Prints a <code>String</code> to both the console and the current log file.
     * @param message The <code>String</code> to be printed
     */
    public static void Log(String message){
        String output = singleton.LogPrefix() + " " + message;
        
        singleton.LogToFile(output);
        System.out.println(output);
    }
    
    private boolean LogToFile(String message){
        Path logDirectory = Paths.get("Daily Logs");
        Path logFile = Paths.get(logDirectory.toString(), "Log for " + LocalDateTime.now().format(fileFormat) + ".log");
        try {
            if (!Files.exists(logDirectory))
                Files.createDirectories(logDirectory);           
                        
            Files.write(logFile, Arrays.asList(message), Files.exists(logFile) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Logs a change in a <code>Location</code>'s state. Prints the log to both
     * the console and the current log file.
     * @param location The <code>Location</code> which has changed state
     * @param locationState The new state of the <code>Location</code>
     */
    @Override
    public void ObservedStateUpdate(Location location, LocationState locationState) {
        LogEmergency(location, locationState);
    }

    /**
     * Logs an attempt to access a <code>Room</code>. Prints the log to both
     * the console and the current log file.
     * @param keycard The <code>Keycard</code> used to try and gain access
     * @param room The <code>room</code> the <code>Keycard</code> tried to
     * access
     * @param wasSuccessful If the attempt was successful or not in gaining
     * access
     */
    @Override
    public void ObservedAccessUpdate(Keycard keycard, Room room, boolean wasSuccessful) {
        LogAccess(keycard, room, wasSuccessful);
    }
}
