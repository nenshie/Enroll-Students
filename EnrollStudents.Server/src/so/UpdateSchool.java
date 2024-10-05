/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.School;

/**
 *
 * @author neven
 */
public class UpdateSchool extends AbstractGenericOperation{

    @Override
    protected void validate(Object object) throws Exception {
        if(object == null || !(object instanceof School)){
            throw new Exception("Sistem nije mogao da azurira skolu");
        }
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        broker.update((School)object, key);
    }
    
}
