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

/**
 *
 * @author Toby
 */
public class Log implements IStateObserver, IAccessObserver{
    
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
    
    private String LogHeader(Location location){
        return LocalDateTime.now().format(messageFormat) + " " + location.GetFullName();
    }
    
    private void LogAccess(Keycard keycard, Room room, boolean wasSuccessful) {
        String message = LogHeader(room) + " (" + room.GetState().GetName() +
                (wasSuccessful ? ") granted access to " : ") denied access to ") +
                keycard.GetName() + " (" + keycard.GetCardID() + ")";
        
        FileLog(message);
        System.out.println(message);
    }
    
    private void LogEmergency(Location location, LocationState state) {
        String message = LogHeader(location) + " is now in the state: " + 
                state.GetName();
                
        FileLog(message);
        System.out.println(message);        
    }
    
    private boolean FileLog(String message){
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
