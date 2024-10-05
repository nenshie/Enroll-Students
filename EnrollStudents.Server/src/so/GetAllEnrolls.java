/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Enrollment;
import java.util.List;

/**
 *
 * @author neven
 */
public class GetAllEnrolls extends AbstractGenericOperation{
    List<Enrollment> allEnrolls;
    
    @Override
    protected void validate(Object object) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        allEnrolls = broker.get((Enrollment)object, key);
        System.out.println("Klasa GetAllEnrolls SO: "+allEnrolls);
    }

    public List<Enrollment> getAllEnrolls() {
        return allEnrolls;
    }
    
}
