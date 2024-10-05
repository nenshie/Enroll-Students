/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import coordinator.Coordinator;
import domain.Administrator;
import form.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author neven
 */
public class LoginController {
    private final LoginForm lf;

    public LoginController() {
        this.lf = null;
    }
    

    public LoginController(LoginForm lf) {
        this.lf = lf;
        addActionListener();
    }

    private void addActionListener() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login(e);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void login(ActionEvent e) throws Exception{
                String username = lf.getjTextFieldUsername().getText().trim();
                String password = String.valueOf(lf.getjPasswordField1().getPassword()).trim();
                
                try {
                    Communication.getInstance().connect();
                    Administrator loggedIn = Communication.getInstance().login(username, password);


                    if(loggedIn == null){
                        JOptionPane.showMessageDialog(lf, "Prijava na sistem je neuspešna", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }else{
                        Coordinator.getInstance().setLoggedIn(loggedIn);
                         JOptionPane.showMessageDialog(lf, "Prijava na sistem je uspešna", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                         Coordinator.getInstance().openMainForm();
                        lf.dispose();
                    }
                } catch (Exception ecp) {
                    JOptionPane.showMessageDialog(lf, "Prijava na sistem je neuspešna", "GREŠKA", JOptionPane.ERROR_MESSAGE); 
                }
                
            }
        });
    }

    public void openForm() {
        lf.setVisible(true);
        
    }

 
    
    
}
