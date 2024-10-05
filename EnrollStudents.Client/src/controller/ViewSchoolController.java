/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import coordinator.Coordinator;
import domain.City;
import domain.School;
import form.FormMod;
import form.MainForm;
import form.ViewSchoolForm;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author neven
 */
public class ViewSchoolController {
    private final ViewSchoolForm vsf;

    public ViewSchoolController(ViewSchoolForm vsf) {
        this.vsf = vsf;
      
    }

    public void openForm(FormMod fm) throws Exception {
        editForm();
        prepareForm(fm);
        vsf.setVisible(true);
        int width = 500;
        int height = 500;
        vsf.setSize(width, height);
        vsf.setDefaultCloseOperation(vsf.DISPOSE_ON_CLOSE);
        vsf.setLocationRelativeTo(null);
        addActionListener();
    }

     private void editForm() {
         vsf.getjLabel1().setMinimumSize(new Dimension(120, 30));
         vsf.getjLabel1().setPreferredSize(new Dimension(120, 30));
         vsf.getjLabel1().setMaximumSize(new Dimension(120, 30));
         
         vsf.getjLabel2().setMinimumSize(new Dimension(100, 30));
         vsf.getjLabel2().setPreferredSize(new Dimension(100, 30));
         vsf.getjLabel2().setMaximumSize(new Dimension(100, 30));
         
         vsf.getjLabel3().setMinimumSize(new Dimension(100, 30));
         vsf.getjLabel3().setPreferredSize(new Dimension(100, 30));
         vsf.getjLabel3().setMaximumSize(new Dimension(100, 30));
         
         vsf.getjLabel4().setMinimumSize(new Dimension(100, 30));
         vsf.getjLabel4().setPreferredSize(new Dimension(100, 30));
         vsf.getjLabel4().setMaximumSize(new Dimension(100, 30));
         
         vsf.getjLabelCity1().setMinimumSize(new Dimension(100, 30));
         vsf.getjLabelCity1().setPreferredSize(new Dimension(100, 30));
         vsf.getjLabelCity1().setMaximumSize(new Dimension(100, 30));
         
         vsf.getjTextFieldName().setMinimumSize(new Dimension(200, 30));
         vsf.getjTextFieldName().setPreferredSize(new Dimension(200, 30));
         vsf.getjTextFieldName().setMaximumSize(new Dimension(200, 30));
         
         vsf.getjTextFieldAdress().setMinimumSize(new Dimension(200, 30));
         vsf.getjTextFieldAdress().setPreferredSize(new Dimension(200, 30));
         vsf.getjTextFieldAdress().setMaximumSize(new Dimension(200, 30));
         
         vsf.getjTextFieldPhone().setMinimumSize(new Dimension(200, 30));
         vsf.getjTextFieldPhone().setPreferredSize(new Dimension(200, 30));
         vsf.getjTextFieldPhone().setMaximumSize(new Dimension(200, 30));
         
         vsf.getjTextFieldSchoolID().setMinimumSize(new Dimension(200, 30));
         vsf.getjTextFieldSchoolID().setPreferredSize(new Dimension(200, 30));
         vsf.getjTextFieldSchoolID().setMaximumSize(new Dimension(200, 30));
         
         vsf.getjComboBoxCity().setMinimumSize(new Dimension(200, 30));
         vsf.getjComboBoxCity().setPreferredSize(new Dimension(200, 30));
         vsf.getjComboBoxCity().setMaximumSize(new Dimension(200, 30));
         
          vsf.getjComboBoxCity().setFont(new Font("Arial", Font.BOLD, 16));
     }
    
    
    private void prepareForm(FormMod fm) throws Exception {
        if(fm.equals(FormMod.UPDATE)){
            School s = Coordinator.getInstance().getSelectedSchool();
            vsf.getjTextFieldSchoolID().setText(String.valueOf(s.getSchoolID()));
            vsf.getjTextFieldSchoolID().setEditable(false);
            vsf.getjTextFieldName().setText(s.getName());
            vsf.getjTextFieldAdress().setText(s.getAddress());
            vsf.getjTextFieldPhone().setText(s.getPhone());
            
            vsf.getjButtonAdd().setVisible(false);
            vsf.getjButtonUpdate().setVisible(true);
            
            List<City> city =  Communication.getInstance().getAllCities();
            for(int i = 0; i < city.size(); i++){
                vsf.getjComboBoxCity().addItem(city.get(i));
            }
            vsf.getjComboBoxCity().setVisible(true);
            vsf.getjComboBoxCity().setSelectedItem(s.getCity());
        }else if(fm.equals(FormMod.ADD)){
            vsf.getjTextFieldSchoolID().setText("");
            vsf.getjTextFieldSchoolID().setEditable(false);
            vsf.getjTextFieldName().setText("");
            vsf.getjTextFieldAdress().setText("");
            vsf.getjTextFieldPhone().setText("");
            
            vsf.getjButtonUpdate().setVisible(false);
            vsf.getjButtonAdd().setVisible(true);
            vsf.getjComboBoxCity().setVisible(true);
            List<City> city =  Communication.getInstance().getAllCities();
            for(int i = 0; i < city.size(); i++){
                vsf.getjComboBoxCity().addItem(city.get(i));
            }
        }
       
    }

    private void addActionListener() {
      vsf.backAddActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int result = JOptionPane.showConfirmDialog(vsf, "Da li ste sigurni?", "?", JOptionPane.YES_NO_OPTION);
              if (result == JOptionPane.YES_OPTION) {
                    vsf.dispose();
                } else if (result == JOptionPane.NO_OPTION) {
                    return;
                }
          }
      });
      
      vsf.addBtnAddActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String name =  vsf.getjTextFieldName().getText();
              String adress = vsf.getjTextFieldAdress().getText();
              String phone = vsf.getjTextFieldPhone().getText();
              City city = (City) vsf.getjComboBoxCity().getSelectedItem();
              
              if(name == "" || adress == "" || phone == "" || name.isEmpty() || adress.isEmpty() || phone.isEmpty() ||
                      name.isBlank() || adress.isBlank() || phone.isBlank() || city == null){
                  JOptionPane.showMessageDialog(vsf, "Sistem ne moze da doda školu, nisu uneti svi parametri", "Greška", JOptionPane.ERROR_MESSAGE);
                  return;
              }
              
              School sch = new School(0, name, adress, phone, city);
              try {
                  Communication.getInstance().addSchool(sch);
                  JOptionPane.showMessageDialog(vsf, "Sistem je uspešno dodao školu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                  Coordinator.getInstance().updateTableSchool();
                  vsf.dispose();
                  
                   } catch (Exception ex) {
                       JOptionPane.showMessageDialog(vsf, "Sistem ne može da doda školu", "Greška", JOptionPane.ERROR_MESSAGE);
                       return;
                  }
              
          }
      });
      
      vsf.updateBtnAddActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int id = Integer.parseInt(vsf.getjTextFieldSchoolID().getText());
              String name =  vsf.getjTextFieldName().getText();
              String adress = vsf.getjTextFieldAdress().getText();
              String phone = vsf.getjTextFieldPhone().getText();
              City city = (City) vsf.getjComboBoxCity().getSelectedItem();
              
              School sch = new School(id, name, adress, phone, city);
              try {
                  Communication.getInstance().updateSchool(sch);
                  JOptionPane.showMessageDialog(vsf, "Sistem je uspešno ažurirao školu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                  Coordinator.getInstance().updateTableSchool();
                  vsf.dispose();
                  
                   } catch (Exception ex) {
                       JOptionPane.showMessageDialog(vsf, "Sistem ne može da ažurira školu", "Greška", JOptionPane.ERROR_MESSAGE);
                       return;
                  }
          }
      });
    }

   
    
}
