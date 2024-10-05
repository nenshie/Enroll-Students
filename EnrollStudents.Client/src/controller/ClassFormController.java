/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import coordinator.Coordinator;
import domain.School;
import domain.Class;
import form.ClassForm;
import form.FormMod;
import form.TableModelClass;
import form.TableModelSchool;
import form.edit.CustomTableEditor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author neven
 */
public class ClassFormController {
    private final ClassForm cf;
    ResourceBundle bundle = ResourceBundle.getBundle("resources.messages", Coordinator.getInstance().getCurrentLocale());
    
    public ClassFormController(ClassForm cf) {
        this.cf = cf;
        cf.setLocationRelativeTo(null);
    }

    public void openForm() throws Exception {
        cf.setVisible(true);
        int width = 800;
        int height = 600;
        cf.setSize(width, height);
        cf.setDefaultCloseOperation(cf.DISPOSE_ON_CLOSE);
        cf.setLocationRelativeTo(null);
        prepareTable();
        addActionListener();
        setLanguage();
         
    }

    private void prepareTable() throws Exception {
        List<domain.Class> allClasses = Communication.getInstance().getAllClasses(Coordinator.getInstance().getSelectedSchool());
        TableModelClass tmc = new TableModelClass(allClasses);
        cf.getjTableClass().setModel(tmc);
        
        CustomTableEditor editTable = new CustomTableEditor(cf.getjTableClass()); 
         editTable.setTableSize(680, 400);
         int[] widths = {80,150,150,150,150};
         editTable.setColumnWidths(widths);
         cf.getjScrollPane1().setBackground(new Color(207,139,240));
         cf.getjScrollPane1().getVerticalScrollBar().setBackground(new Color(207,139,240));
    }

    private void addActionListener() {
        cf.backAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string1 = "confirm_message";
                 int result = JOptionPane.showConfirmDialog(cf, bundle.getString(string1), "?", JOptionPane.YES_NO_OPTION);
              if (result == JOptionPane.YES_OPTION) {
                  try {
                      Coordinator.getInstance().openMainForm();
                  } catch (Exception ex) {
                      Logger.getLogger(ViewSchoolController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                    cf.dispose();
                } else if (result == JOptionPane.NO_OPTION) {
                    return;
                }
          }
        });
        
        cf.viewBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = cf.getjTableClass().getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(cf, bundle.getString("select_row"), bundle.getString("error"), JOptionPane.ERROR_MESSAGE);
                }else{
                    TableModelClass tmc = (TableModelClass) cf.getjTableClass().getModel();
                    Class c = tmc.getList().get(row);
                    Coordinator.getInstance().setSelectedClass(c);
                    try {
                        Coordinator.getInstance().openViewClassForm();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(cf, "Sistem ne može da prikaže odeljenje", "Greska", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(ClassFormController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cf.dispose();
                    
                }
            }
        });
        
        cf.addClassBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Coordinator.getInstance().openAddClassForm(FormMod.NOT_MAIN);
                } catch (Exception ex) {
                    Logger.getLogger(ClassFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setLanguage() {
        Locale localeRS = new Locale("sr", "RS");
        Locale localeENG = new Locale("en", "US");
        if(Coordinator.getInstance().getCurrentLocale().equals(localeENG)){
            bundle = ResourceBundle.getBundle("resources.messages", Coordinator.getInstance().getCurrentLocale());
            localizeComponents(bundle);
            updateTableHeaders(bundle);
        }else if(Coordinator.getInstance().getCurrentLocale().equals(localeRS)){
            bundle = ResourceBundle.getBundle("resources.messages", Coordinator.getInstance().getCurrentLocale());
            localizeComponents(bundle);
            updateTableHeaders(bundle);
        }
      
    }

    private void localizeComponents(ResourceBundle bundle) {
        try {
        cf.getjButtonAddClass().setText(bundle.getString("btn.add_class"));
        cf.getjButtonBack().setText(bundle.getString("btn.back"));
        cf.getjButtonView().setText(bundle.getString("view"));
        } catch (MissingResourceException e) {
             System.err.println("Key '' not found in resource bundle.");
        }
    }

    private void updateTableHeaders(ResourceBundle bundle) {
        try {
            String[] newColumnNames = {
            bundle.getString("classID"),
            bundle.getString("name"),
            bundle.getString("num_of_stud"),
            bundle.getString("grade"),
            bundle.getString("school")
        };

        ((TableModelClass) cf.getjTableClass().getModel()).updateColumnNames(newColumnNames);
        cf.getjTableClass().revalidate();
        cf.getjTableClass().repaint();
            
        } catch (MissingResourceException e) {
             System.err.println("Key '' not found in resource bundle.");
        }
        
    }

    public void refreshTable() throws Exception {
        prepareTable();
    }
    
     
}
