/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.School;
import domain.Student;

/**
 *
 * @author neven
 */
public class UpdateStudent extends AbstractGenericOperation{

    @Override
    protected void validate(Object object) throws Exception {
        if(object == null || !(object instanceof Student)){
            throw new Exception("Sistem nije mogao da azurira studenta");
        }
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
         broker.update((Student)object, key);
    }
    
}
