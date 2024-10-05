/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

;


import configuration.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import configuration.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author neven
 */
public class DbConnectionFactory {
    private static DbConnectionFactory instance;
    private Connection connection;
    
    private DbConnectionFactory() {
        
        try {
            if(connection == null || connection.isClosed()){
                String url = Configuration.getInstace().getProperty("url");
                String username = Configuration.getInstace().getProperty("username");
                String password = Configuration.getInstace().getProperty("password");

                connection = DriverManager.getConnection(url,username,password);
                connection.setAutoCommit(false);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    public static DbConnectionFactory getInstance() {
        if(instance == null){
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    
    
}
