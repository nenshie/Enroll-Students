/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;

/**
 *
 * @author neven
 */
public interface Repository<T> {
    
    
    void add(T param) throws Exception;
    void update(T param, String condition) throws Exception; 
    void delete(T param, String condition) throws Exception; // brise objekte iz liste
    List<T> get(T param , String condition);
    List<T> getAll(T param); // vraca sve objekte iz lise
    List<T> find(T param,String join, String condition) throws Exception;
    int addClass(T param) throws Exception;
}
