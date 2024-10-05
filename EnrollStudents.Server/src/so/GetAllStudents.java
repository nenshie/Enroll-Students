/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Student;
import java.util.List;

/**
 *
 * @author neven
 */
public class GetAllStudents extends AbstractGenericOperation{
    List<Student> allStud;
    @Override
    protected void validate(Object object) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        allStud = broker.get((Student)object, key);
        System.out.println("Klasa GetAllStudents SO: "+allStud);
    }

    public List<Student> getAllStud() {
        return allStud;
    }
    
}
