/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import controller.LoginController;
import domain.Administrator;
import domain.City;
import domain.Class;
import domain.Enrollment;
import domain.School;
import domain.Student;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neven
 */
public class Communication {
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private static Communication instance;
    private Properties config;
    private boolean end = false;

    private Communication() {
        try {
            config = new Properties();
            config.load(new FileInputStream("C:\\Users\\neven\\Documents\\NetBeansProjects\\EnrollStudents.Client\\config\\config.properties"));
        } catch (Exception ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Communication getInstance() {
        if(instance == null){
            instance = new Communication();
        }
        return instance;
    }
    
    public String getProperty(String key){
        return config.getProperty(key, "n/a");
    }
    public void connect(){
        try {
            int port = Integer.parseInt(config.getProperty("port"));
            socket = new Socket("localhost", port);
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN");
        }
    }

   
    public Administrator login(String username, String password) throws Exception {
        Administrator admin = new Administrator();
        admin.setUsername(username);
        admin.setPassword(password);
        Request request = new Request(Operation.LOGIN, admin);
        
        sender.send(request);
        
        Response response = (Response) receiver.receive();
         if(response.getResult() != null){
           admin = (Administrator) response.getResult();
       }else{
           System.out.println("Nije ulogovan korisnik!");
           ((Exception)response.getResult()).printStackTrace();
           throw new Exception("GRESKA");
       }
        return admin;  
    }

    public List<School> getAllSchools() throws Exception {
       Request request = new Request(Operation.GET_ALL_SCHOOLS, null); 
       
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       
       List<School> allSchools = (List<School>) response.getResult();
       return allSchools;
    }

    public List<City> getAllCities() throws Exception {
       Request request= new Request(Operation.GET_ALL_CITIES, null); 
       
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       
       List<City> allCities = (List<City>) response.getResult();
       return allCities;
    }

    public List<School> getSelectedSchools(int cityID) throws Exception {
       Request request = new Request(Operation.GET_SELECTED_SCHOOLS, cityID); 
       
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       
       List<School> selectedSchools = (List<School>) response.getResult();
       return selectedSchools;
    }

    public void deleteSchool(School s) throws Exception {
       Request request = new Request(Operation.DELETE_SCHOOL,s); 
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       if(response.getResult() == null){
           System.out.println("USPESNO OBRISANA SKOLA");
       }else{
           System.out.println("NIJE USPESNO OBRISANA SKOLA");
           ((Exception)response.getResult()).printStackTrace();
           throw new Exception("GRESKA");
       }
       
    }


    public List<Class> getAllClasses(School selectedSchool) throws Exception {
       Request request = new Request(Operation.GET_CLASSES, selectedSchool); 
       
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       
       List<Class> allClasses = (List<Class>) response.getResult();
       return allClasses;
    }

    public void addSchool(School sch) throws Exception {
       Request request = new Request(Operation.ADD_SCHOOL,sch); 
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       if(response.getResult() == null){
           System.out.println("USPESNO DODATA SKOLA");
       }else{
           System.out.println("NIJE DODATA SKOLA");
           ((Exception)response.getResult()).printStackTrace();
           throw new Exception("GRESKA");
       }
       
    }

    public void updateSchool(School sch) throws Exception {
       Request request = new Request(Operation.UPDATE_SCHOOL,sch); 
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       if(response.getResult() == null){
           System.out.println("USPESNO AZURIRANA SKOLA");
       }else{
           System.out.println("NIJE AZURIRANA SKOLA");
           ((Exception)response.getResult()).printStackTrace();
           throw new Exception("GRESKA");
       }
    }

    public List<Student> getStudents(Class selectedClass) throws Exception {
       Request request = new Request(Operation.FIND_STUDENTS, selectedClass); 
       
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       
       List<Student> selectedStudents = (List<Student>) response.getResult();
       return selectedStudents;
    }

    public List<Student> getAllStudents() throws Exception {
       Request request = new Request(Operation.GET_ALL_STUDENTS, null); 
       
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       
       List<Student> allStud = (List<Student>) response.getResult();
       return allStud;
    }


    public void addClass(HashMap map) throws Exception {
       Request request = new Request(Operation.ADD_CLASS,map); 
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       if(response.getResult() == null){
           System.out.println("USPESNO DODATO ODELJENJE");
       }else{
           System.out.println("NIJE DODATO ODELJENJE");
           ((Exception)response.getResult()).printStackTrace();
           throw new Exception("GRESKA");
       }
    }

    public void updateStudent(Student newStud) throws Exception {
       Request request = new Request(Operation.UPDATE_STUDENT,newStud); 
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       if(response.getResult() == null){
           System.out.println("USPESNO AZURIRAN STUDENT");
       }else{
           System.out.println("NIJE AZURIRAN STUDENT");
           ((Exception)response.getResult()).printStackTrace();
           throw new Exception("GRESKA");
       }
    }

    public void addStudent(Student newStud) throws Exception {
       Request request = new Request(Operation.ADD_STUDENT,newStud); 
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       if(response.getResult() == null){
           System.out.println("USPESNO DODAT STUDENT");
       }else{
           System.out.println("NIJE DODAT STUDENT");
           ((Exception)response.getResult()).printStackTrace();
           throw new Exception("GRESKA");
       }
    }

    public List<Enrollment> getAllEnrolls() throws Exception {
       Request request = new Request(Operation.GET_ALL_ENROLLS, null); 
       
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       
       List<Enrollment> allEnrolls = (List<Enrollment>) response.getResult();
       return allEnrolls;
    }

    public void deleteStudent(Student s) throws Exception {
       Request request = new Request(Operation.DELETE_STUDENT,s); 
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       if(response.getResult() == null){
           System.out.println("USPESNO OBRISAN STUDENT");
       }else{
           System.out.println("NIJE USPESNO OBRISAN STUDENT");
           ((Exception)response.getResult()).printStackTrace();
           throw new Exception("GRESKA");
       }
    }
    
    public void updateClass(HashMap<Class, List<Student>> map) throws Exception {
        Request request = new Request(Operation.UPDATE_CLASS,map); 
       sender.send(request);
       
       Response response = (Response) receiver.receive();
       if(response.getResult() == null){
           System.out.println("USPESNO AZURIRANO ODELJENJE");
       }else{
           System.out.println("NIJE AZURIRANO ODELJENJE");
           ((Exception)response.getResult()).printStackTrace();
           throw new Exception("GRESKA");
       }
    }

    public void close() {
        if(socket != null && !socket.isClosed()){
            try {
                end = true;
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    

    

    
    
    
    
}

