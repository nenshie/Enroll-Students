/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neven
 */
public class Receiver { // prima poruku
    private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }
    
    public Object receive(){
         try {
             ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
             return in.readObject(); 
        } catch (Exception ex) {
             ex.printStackTrace();
        }
         return null;
    
    }
    
}
