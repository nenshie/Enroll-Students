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
import form.TableModelSchool;
import form.edit.CustomBtnEdit;
import form.edit.CustomScrollBarUI;
import form.edit.CustomTableEditor;
import form.edit.GradientPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 *
 * @author neven
 */
public class MainFormController {
 
    private final MainForm mf;
    private List<School> allSchools;
    public MainFormController(MainForm mf) throws Exception {
        this.mf = mf;
        prepareTable();
        prepareComboBox();
        addActionListener();
        
    }


    public void openForm() {
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int width = screenSize.width-50;
        int height = screenSize.height-90;
        mf.setSize(width, height);
        mf.setLocationRelativeTo(null);
        try {
            
            Image icon = ImageIO.read(new File("C:\\Users\\neven\\Documents\\NetBeansProjects1\\EnrollStudents.Client/iconEnroll.png"));
            mf.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = Coordinator.getInstance().getLoggedIn().getName()+ " "+Coordinator.getInstance().getLoggedIn().getSurname();
        mf.setVisible(true);
        mf.setLocationRelativeTo(null);
        mf.getjLabelLoggedIn().setText(name);
        editForm();
        
        
    }
   
    
     private void editForm() {
         
         mf.getjTextFieldSchoolName().setFont(new Font("Arial", Font.ITALIC, 18));
         mf.getjLabel1().setFont(new Font("Arial", Font.ITALIC, 18));
         mf.getjTextFieldSchoolName().setBackground(new Color(204, 204, 255));
         mf.getjLabel2().setFont(new Font("Arial", Font.ITALIC, 18));
         mf.getjLabel3().setFont(new Font("Arial", Font.ITALIC, 18));
         mf.getjLabelLoggedIn().setFont(new Font("Arial", Font.ITALIC, 18));
        
         
         mf.getjButtonSrbLat().setBackground(new Color(218,248,251));
         mf.getjButtonSrbLat().setForeground(Color.DARK_GRAY);
        
         mf.getjButtonEnglish().setBackground(new Color(218,248,251));
         mf.getjButtonEnglish().setForeground(Color.DARK_GRAY);
         
         mf.getjMenuBar1().setPreferredSize(new Dimension(MAXIMIZED_BOTH, 30));
         mf.getjMenuBar1().setBackground(new Color(207,139,240));
         mf.getjMenu1().setBackground(new Color(207,139,240));
         mf.getjMenu1().setFont(new Font("Arial", Font.PLAIN, 18));
         mf.getjMenu1().setForeground(Color.WHITE);
         
         mf.getjMenu2().setBackground(new Color(207,139,240));
         mf.getjMenu2().setFont(new Font("Arial", Font.PLAIN, 18));
         mf.getjMenu2().setForeground(Color.WHITE);
         
         mf.getjMenu3().setBackground(new Color(207,139,240));
         mf.getjMenu3().setFont(new Font("Arial", Font.PLAIN, 18));
         mf.getjMenu3().setForeground(Color.WHITE);
         
         mf.getjMenuItemAddCls().setBackground(new Color(207,139,240));
         mf.getjMenuItemAddCls().setFont(new Font("Arial", Font.PLAIN, 16));
         mf.getjMenuItemAddCls().setForeground(Color.WHITE);
         
         mf.getjMenuItemAddSch().setBackground(new Color(207,139,240));
         mf.getjMenuItemAddSch().setFont(new Font("Arial", Font.PLAIN, 16));
         mf.getjMenuItemAddSch().setForeground(Color.WHITE);
         
         mf.getjMenuItemAddStud().setBackground(new Color(207,139,240));
         mf.getjMenuItemAddStud().setFont(new Font("Arial", Font.PLAIN, 16));
         mf.getjMenuItemAddStud().setForeground(Color.WHITE);
      
         mf.getjMenuItemViewAllStud().setBackground(new Color(207,139,240));
         mf.getjMenuItemViewAllStud().setFont(new Font("Arial", Font.PLAIN, 16));
         mf.getjMenuItemViewAllStud().setForeground(Color.WHITE);
         
         mf.getjMenuItemAddStud().setBackground(new Color(207,139,240));
         mf.getjMenuItemAddStud().setFont(new Font("Arial", Font.PLAIN, 16));
         mf.getjMenuItemAddStud().setForeground(Color.WHITE);
         
         
         
         
     }
    private void prepareTable() throws Exception {
        
        allSchools =  Communication.getInstance().getAllSchools();
        TableModelSchool tms = new TableModelSchool(allSchools);
        mf.getjTableSchool().setModel(tms);  
        mf.getjTableSchool().revalidate();
        mf.getjTableSchool().repaint();
       CustomTableEditor editTable = new CustomTableEditor(mf.getjTableSchool()); 
       editTable.setTableSize(890, 445);
       int[] widths = {80,200,250,150,200};
       editTable.setColumnWidths(widths);
       mf.getjScrollPane2().setBackground(new Color(207,139,240));
       mf.getjScrollPane2().getVerticalScrollBar().setBackground(new Color(207,139,240));
      
       
      
    }
    private void prepareComboBox() throws Exception {
        List<City> city =  Communication.getInstance().getAllCities();
        for(int i = 0; i < city.size(); i++){
            mf.getjComboBoxCity().addItem(city.get(i));
        }
        Dimension dim = new Dimension(300, 30);
        mf.getjComboBoxCity().setFont(new Font("Arial", Font.ITALIC, 18)); 
    }

    
    private void addActionListener() {
        mf.comboBoxAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                City city = (City) mf.getjComboBoxCity().getSelectedItem();
                
                List<School> selctedSchools;
                try {
                    selctedSchools = Communication.getInstance().getSelectedSchools(city.getCityID());
                    TableModelSchool tms = new TableModelSchool(selctedSchools);
                    mf.getjTableSchool().setModel(tms);
                } catch (Exception ex) {
                    Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        mf.SearchSchoolAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = mf.getjTextFieldSchoolName().getText().trim();
                System.out.println(name);
                TableModelSchool tms = (TableModelSchool) mf.getjTableSchool().getModel();
                String searchText = mf.getjTextFieldSchoolName().getText().trim();
                TableModelSchool model = (TableModelSchool)  mf.getjTableSchool().getModel();
                model.search(searchText);
                
               if (model.getRowCount() == 1 && model.getValueAt(0, 0).equals("Not Found")) {
                   System.out.println("No schools found with the name: " + searchText);
                }else if(name == "" || name.isEmpty()){
                   tms.setList(allSchools);
                   tms.fireTableDataChanged();
                }else{
                  tms.search(name);
                }
           }
        });
        
        mf.getjTextFieldSchoolName().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
            private void search() {
                String name = mf.getjTextFieldSchoolName().getText().trim();
                System.out.println(name);
                TableModelSchool tms = (TableModelSchool) mf.getjTableSchool().getModel();
                if (name.isEmpty()) {
                    tms.setList(allSchools);
                    tms.fireTableDataChanged();
                } else {
                    tms.search(name);
                }
            }
         });
        
       mf.deleteSchoolAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = mf.getjTableSchool().getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(mf, "Sistem ne može da obriše školu", "Greška", JOptionPane.ERROR_MESSAGE);
                }else{
                    TableModelSchool tms = (TableModelSchool) mf.getjTableSchool().getModel();
                    School s = tms.getList().get(row);
                    try {
                        Communication.getInstance().deleteSchool(s);
                        JOptionPane.showMessageDialog(mf, "Sistem je uspešno obrisao školu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        prepareTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(mf, "Sistem ne može da obriše školu", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }
        });
        
        mf.nextAddCtionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    next(e);
                } catch (Exception ex) {
                    Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void next(ActionEvent e) throws Exception {
               int row = mf.getjTableSchool().getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(mf, "Nise izabrali skolu!", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    TableModelSchool tms = (TableModelSchool) mf.getjTableSchool().getModel();
                    School s = tms.getList().get(row);
                    Coordinator.getInstance().setSelectedSchool(s);
                    Coordinator.getInstance().openClassForm();
                    mf.dispose();
                }
           }
        });
        
        mf.viewAddCtionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = mf.getjTableSchool().getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(mf, "Nise izabrali školu!", "Greška", JOptionPane.ERROR_MESSAGE);
                }else{
                    TableModelSchool tms = (TableModelSchool) mf.getjTableSchool().getModel();
                    School s = tms.getList().get(row);
                    Coordinator.getInstance().setSelectedSchool(s);
                    try {
                        Coordinator.getInstance().openViewSchForm(FormMod.UPDATE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(mf, "Sistem ne može da prikaže školu", "Greška", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
            }
        });
        
        mf.addSchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Coordinator.getInstance().openViewSchForm(FormMod.ADD);
                } catch (Exception ex) {
                    Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        mf.addClssAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Coordinator.getInstance().openAddClassForm(FormMod.MAIN);
                } catch (Exception ex) {
                    Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        mf.addStudAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Coordinator.getInstance().openViewStudForm(FormMod.ADD);
                } catch (Exception ex) {
                    Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        mf.viewAllStudAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Coordinator.getInstance().openViewAllStudForm();
                } catch (Exception ex) {
                    Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
        
        
    }

    public void updateTableSchool() throws Exception {
        allSchools = Communication.getInstance().getAllSchools();
        TableModelSchool tms = (TableModelSchool) mf.getjTableSchool().getModel();
        tms.setList(allSchools);
    }
    
}
