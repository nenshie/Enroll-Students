/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import controller.Controller;
import domain.School;
import domain.Class;
import java.util.List;

/**
 *
 * @author neven
 */
public class GetNumOfClasses extends AbstractGenericOperation{

    List<Class> list;
    
    @Override
    protected void validate(Object object) throws Exception {

    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        Class cl = new domain.Class();
        list = broker.get(cl, cl.getJoinTable() + cl.getJoinCondition()+ " WHERE school.schoolID = " + ((int)object));
       System.out.println("Klasa GetNumOfClass SO: "+list);
    }

    public List<Class> getList() {
        return list;
    }
    
    
    
}
