/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.School;
import java.util.List;

/**
 *
 * @author neven
 */
public class GetAllSchools extends AbstractGenericOperation{
    List<School> allSchools;
    @Override
    protected void validate(Object object) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
       allSchools = (List<School>) broker.get((School)object, key);
       System.out.println("Klasa GetAllSchools SO: "+allSchools);
       
    }

    public List<School> getAllSchools() {
        return allSchools;
    }
    
    
    
}
