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
public class FindStudents extends AbstractGenericOperation {

    List<Student> selectedStudnets;
    
    @Override
    protected void validate(Object object) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        selectedStudnets = broker.get(new Student(), 
                "JOIN city ON(student.city=city.cityID) JOIN enrollment ON(student.studentID = enrollment.student) WHERE enrollment.class= "+Integer.parseInt(key));
         System.out.println("Klasa FindStudents SO: "+selectedStudnets);
    }

    public List<Student> getSelectedStudnets() {
        return selectedStudnets;
    }
    
}
