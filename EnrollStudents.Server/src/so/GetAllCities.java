/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.City;
import java.util.List;

/**
 *
 * @author neven
 */
public class GetAllCities extends AbstractGenericOperation {

    List<City> allCities;
    
    @Override
    protected void validate(Object object) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        allCities = broker.getAll((City)object);
        System.out.println("Klasa GetAllCities SO: "+allCities);
    }

    public List<City> getAllCities() {
        return allCities;
    }

    public void setAllCities(List<City> allCities) {
        this.allCities = allCities;
    }

   
    
}
