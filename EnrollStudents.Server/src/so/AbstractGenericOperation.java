/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.sql.SQLException;
import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author neven
 */
public abstract class AbstractGenericOperation {
  protected final Repository broker;

    public AbstractGenericOperation() {
        this.broker = new DbRepositoryGeneric();
    }
public void execute(Object object, String key) throws Exception {
        try {
            validate(object);   
            startTransaction();
            executeOperation(object, key);  
            committ(); 
        } catch (Exception ex) {
            rollbackk(); 
            throw ex;
        }
    }

    private void committ() throws Exception {
        ((DbRepository) broker).commit();
    }

    private void rollbackk() throws Exception {
         ((DbRepository) broker).rollback();
    }
    private void startTransaction() throws Exception {
      ((DbRepository) broker).connect();
    }
    protected abstract void validate(Object object) throws Exception;
    protected abstract void executeOperation(Object object, String key) throws Exception;

    

  
}
