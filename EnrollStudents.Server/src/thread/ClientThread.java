/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Operation;
import static communication.Operation.UPDATE_STUDENT;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Administrator;
import domain.City;
import domain.School;
import domain.Class;
import domain.Enrollment;
import domain.Student;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neven
 */
public class ClientThread extends Thread{
    
    Socket clientSocket;
    private Sender sender;
    private Receiver reciever;
    boolean end = false;
    Administrator admin;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        sender = new Sender(clientSocket);
        reciever = new Receiver(clientSocket);  
    }

    
    @Override
    public void run() {
        while (!end) {            
            try {
            Request request = (Request) reciever.receive();
                Response response = new Response();
                switch (request.getOperation()) {
                    case LOGIN:
                      try{
                        admin = (Administrator) request.getArgument();
                        admin = Controller.getInstance().login(admin);
                        Controller.getInstance().getLoggedIn().add(admin);
                        Controller.getInstance().setCurrentlyLogged(admin);
                        response.setResult(admin);
                      
                      }catch(Exception e){
                          response.setResult(e);
                      }  
                        break;
                        
                    case GET_ALL_SCHOOLS:
                        List<School> allSchools = Controller.getInstance().getAllSchools();
                        response.setResult(allSchools);
                        break;
                        
                    case GET_ALL_CITIES:   
                        List<City> allCities = Controller.getInstance().getAllCities();
                        response.setResult(allCities);
                        break;
                        
                    case GET_SELECTED_SCHOOLS:
                        int cityID = (int) request.getArgument();
                        List<School> selectedSchools = Controller.getInstance().getSeelctedSchools(cityID);
                        response.setResult(selectedSchools);
                        break;
                        
                    case DELETE_SCHOOL:
                        try {
                        School s = (School) request.getArgument();
                        Controller.getInstance().deleteSchool(s);
                        response.setResult(null);
                            } catch (Exception e) {
                                response.setResult(e);
                            }
                        
                        break;
                    case GET_CLASSES:
                        School s = (School) request.getArgument();
                        List<Class> allClasses = Controller.getInstance().getAllClasses(s);
                        response.setResult(allClasses);
                        break;
                        
                    case ADD_SCHOOL:
                      try {
                       School sch = (School) request.getArgument();
                        Controller.getInstance().addSchool(sch);
                        response.setResult(null);
                            } catch (Exception e) {
                                response.setResult(e);
                            }
                        
                        break;
                        
                    case UPDATE_SCHOOL:
                         try {
                       School sc = (School) request.getArgument();
                        Controller.getInstance().updateSchool(sc);
                        response.setResult(null);
                            } catch (Exception e) {
                                response.setResult(e);
                            }
                        break;
                        
                    case FIND_STUDENTS:
                        Class cl = (Class) request.getArgument();
                        List<Student> selectedStudnets = Controller.getInstance().findStudnets(cl);
                        response.setResult(selectedStudnets);
                        break;
                        
                    case GET_ALL_STUDENTS:
                        List<Student> allStud = Controller.getInstance().getAllStud();
                        response.setResult(allStud);
                        break;
                    case GET_ALL_ENROLLS:
                        List<Enrollment> allEnrolls = Controller.getInstance().getAllEnrolls();
                        response.setResult(allEnrolls);
                        break;
                   
                    case ADD_CLASS:
                        try {
                        HashMap map = (HashMap) request.getArgument();
                        Controller.getInstance().addClass(map);
                        response.setResult(null);
                            } catch (Exception e) {
                                response.setResult(e);
                            }
                        break;
                        
                    case UPDATE_STUDENT:
                        try {
                            Student stud = (Student) request.getArgument();
                            Controller.getInstance().updateStudent(stud);
                             response.setResult(null);
                            } catch (Exception e) {
                                response.setResult(e);
                            }
                        break;
                        
                        case UPDATE_CLASS:
                        try {
                            HashMap map = (HashMap) request.getArgument();
                            Controller.getInstance().updateClass(map);
                             response.setResult(null);
                            } catch (Exception e) {
                                response.setResult(e);
                            }
                        break;
                        
                    case ADD_STUDENT:
                        try {
                            Student stud = (Student) request.getArgument();
                            Controller.getInstance().addStudent(stud);
                             response.setResult(null);
                            } catch (Exception e) {
                                response.setResult(e);
                            }
                        break;
                        
                    case DELETE_STUDENT:
                         try {
                            Student std = (Student) request.getArgument();
                            Controller.getInstance().deleteStudent(std);
                            response.setResult(null);
                                } catch (Exception e) {
                                    response.setResult(e);
                                }
                        
                        break;
                        
                   
                    default:
                      System.out.println("Error, operaiton is not valid");
                }
                sender.send(response);
        } catch (Exception ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    public Administrator getAdmin() {
        return admin;
    }
    
    
    
    
    public void stopT() throws Exception{
        try {
                
                end = true;
                clientSocket.close();
                interrupt();
                
            
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
