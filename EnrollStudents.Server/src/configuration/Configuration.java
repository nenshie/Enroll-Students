/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neven
 */
public class Configuration {
    private static Configuration instance;
    private Properties config;

    public Configuration() {
        try {
            config = new Properties();
            config.load(new FileInputStream("C:\\Users\\neven\\Documents\\NetBeansProjects\\EnrollStudents.Server\\config\\config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Configuration getInstace() {
        if(instance == null){
            instance = new Configuration();
        }
        return instance;
    }

    public String getProperty(String key){
        return config.getProperty(key, "n/a");
    }
    
    public void setPropery(String key, String value){
        config.setProperty(key, value);
    }
    
    public void saveChanges(){
        try {
            try {
                config.store(new FileOutputStream("C:\\Users\\neven\\Documents\\NetBeansProjects\\EnrollStudents.Server\\config\\config.properties"), null);
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } } catch (IOException ex) {
            ex.printStackTrace();
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
}
