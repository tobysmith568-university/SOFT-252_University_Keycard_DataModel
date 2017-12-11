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
 *
 * @author Toby
 */
public class Log implements IStateObserver, IAccessObserver, Serializable{
    
    private static Log singleton;
    private DateTimeFormatter messageFormat = DateTimeFormatter.ofPattern("'['dd/MM/yy'] ['HH:mm:ss']'");
    private DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd-MM-yy");
    
    private Log(){
    }
    
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
    
    public static void Log(String message){
        String output = singleton.LogPrefix() + " " + message;
        
        singleton.LogToFile(output);
        System.out.println(output);
    }
    
    private boolean LogToFile(String message){
        try {
            String fileName = "Log for " + LocalDateTime.now().format(fileFormat);
            Path path = Paths.get(fileName);
                        
            Files.write(path, Arrays.asList(message), Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void ObservedStateUpdate(Location location, LocationState locationState) {
        LogEmergency(location, locationState);
    }

    @Override
    public void ObservedAccessUpdate(Keycard keycard, Room room, boolean wasSuccessful) {
        LogAccess(keycard, room, wasSuccessful);
    }
}
