/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Administrator;
import domain.School;
import java.util.List;

/**
 *
 * @author neven
 */
public class Login extends AbstractGenericOperation{

    Administrator admin;
    
    @Override
    protected void validate(Object object) throws Exception {
         if(controller.Controller.getInstance().getLoggedIn().contains((Administrator)object)){
           throw new Exception("Korisnik je vec ulogovan");
       }
        
        if(object == null || !(object instanceof Administrator)){
            throw new Exception("Sistem nije mogao da pronadje korisnika");
        }
        
      
        
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        List<Administrator> allAdmin = broker.getAll((Administrator)object);
        System.out.println("Klasa Login SO: "+allAdmin);
        
       if(allAdmin.contains((Administrator)object)){
         for(Administrator a: allAdmin){
            if(a.equals((Administrator)object)){
                admin = a;
                System.out.println(a);
                return;
             }
        else{
             admin = null;
            }
          }
         }
    }    

    public Administrator getAdmin() {
        return admin;
    }

    
    
    
}
