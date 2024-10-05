/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Class;
import java.util.List;

/**
 *
 * @author neven
 */
public class GetAllClasses extends AbstractGenericOperation{
    List<domain.Class> allClasses;
    
    @Override
    protected void validate(Object object) throws Exception {
       
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        allClasses = broker.find((Class)object, " "+((Class)object).getJoinTable()+((Class)object).getJoinCondition(), "schoolID = "+Integer.parseInt(key));
         System.out.println("Klasa GetAllClasses SO: "+allClasses);
    }

    public List<Class> getAllClasses() {
        return allClasses;
    }
    
    
    
}
