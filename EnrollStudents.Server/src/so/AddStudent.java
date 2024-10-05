/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;


import controller.Controller;
import domain.Student;
import java.util.List;

/**
 *
 * @author neven
 */
public class AddStudent extends AbstractGenericOperation {

    @Override
    protected void validate(Object object) throws Exception {
        if(object == null || !(object instanceof Student)){
            throw new Exception("Sistem nije mogao da doda studenta");
        }
        
        List<Student> allStud = Controller.getInstance().getAllStud();
        for (Student student : allStud) {
            if(student.equals(object)){
                throw new Exception("Sistem nije mogao da doda studenta");
            }
            
        }
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        broker.add((Student)object);
    }
    
}
