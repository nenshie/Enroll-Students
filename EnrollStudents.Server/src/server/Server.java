/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;


import communication.Operation;
import communication.Response;
import controller.Controller;
import domain.Administrator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import thread.ClientThread;

/**
 *
 * @author neven
 */
public class Server extends Thread{
    
    ServerSocket serverSocket;
    private boolean end = false;
    private List<ClientThread> clients;

    public Server() {
       clients = new ArrayList<>();    
    }

    
    
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (!end) {
            try {
                System.out.println("Waiting for conection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected!");
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
                clients.add(clientThread);
                
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void stopServer() throws Exception{
      
        for(ClientThread c: clients){
             c.stopT();
        }
        end = true;
        try {
            serverSocket.close();
            System.out.println("Server stopped!");
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
       Controller.getInstance().setLoggedIn(new ArrayList<>());
    }
    
}
