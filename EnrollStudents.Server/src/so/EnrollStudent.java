/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import controller.Controller;
import domain.Enrollment;
import domain.School;
import java.util.List;

/**
 *
 * @author neven
 */
public class EnrollStudent extends AbstractGenericOperation{

    @Override
    protected void validate(Object object) throws Exception {
        if(object == null || !(object instanceof Enrollment)){
            throw new Exception("Sistem nije mogao da upise ucenika u odeljenje");
        }
       List<Enrollment> enrolls = Controller.getInstance().getAllEnrolls();
          for(Enrollment en: enrolls){
              if(((Enrollment)object).getStudent().getStudentID() == en.getStudent().getStudentID()){
                   throw new Exception("Ucenik je ved upisan u odeljenje!");
                       }
                   }
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        broker.add((Enrollment)object);
    }
    
}
