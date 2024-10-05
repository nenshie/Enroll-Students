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
public class GetSelectedSchools extends AbstractGenericOperation{
    List<School> selectedSchools;
    @Override
    protected void validate(Object object) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        
       selectedSchools = (List<School>) broker.find((School)object," "+((School)object).getJoinTable()+((School)object).getJoinCondition() ,"cityID = "+Integer.parseInt(key));
       System.out.println("Klasa GetAllSchools SO: "+selectedSchools);
       
    }

    public List<School> getSelectedSchools() {
        return selectedSchools;
    }
    
}
