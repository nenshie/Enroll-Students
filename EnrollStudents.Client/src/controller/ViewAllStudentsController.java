/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import communication.Communication;
import coordinator.Coordinator;
import domain.Enrollment;
import domain.Student;
import form.FormMod;
import form.TableModelStudent;
import form.ViewAllStudentsForm;
import form.edit.CustomTableEditor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author neven
 */
public class ViewAllStudentsController {
    private final ViewAllStudentsForm vasf;
    List<Student> allfStud;
    ResourceBundle bundle = ResourceBundle.getBundle("resources.messages", Coordinator.getInstance().getCurrentLocale());
    
    public ViewAllStudentsController(ViewAllStudentsForm vasf) {
        this.vasf = vasf;
    }

    public void openForm() throws Exception {
        vasf.setVisible(true);
        int width = 1200;
        int height = 700;
        vasf.setSize(width, height);
        vasf.setLocationRelativeTo(null);
        vasf.setDefaultCloseOperation(vasf.DISPOSE_ON_CLOSE);
        
        prepareForm();
        addActionListener();
        localizeComponents();
    }

    private void prepareForm() throws Exception {
        allfStud = Communication.getInstance().getAllStudents();
        TableModelStudent tms = new TableModelStudent(allfStud);
        vasf.getjTableAllStud().setModel(tms);
        CustomTableEditor editTable = new CustomTableEditor(vasf.getjTableAllStud());
        editTable.setTableSize(980, 445);
       int[] widths = {80,150,150,150,150,150,150};
       editTable.setColumnWidths(widths);
       vasf.getjScrollPane1().setBackground(new Color(207,139,240));
       vasf.getjScrollPane1().getVerticalScrollBar().setBackground(new Color(207,139,240));
       updateTableHeaders();
      
       vasf.getjLabel1().setMinimumSize(new Dimension(100, 30));
       vasf.getjLabel1().setPreferredSize(new Dimension(100, 30));
       vasf.getjLabel1().setMaximumSize(new Dimension(100, 30));
       
       vasf.getjTextFieldName().setMinimumSize(new Dimension(200, 30));
       vasf.getjTextFieldName().setPreferredSize(new Dimension(200, 30));
       vasf.getjTextFieldName().setMaximumSize(new Dimension(200, 30));
       
       vasf.getjLabelName().setMinimumSize(new Dimension(50, 30));
       vasf.getjLabelName().setPreferredSize(new Dimension(50, 30));
       vasf.getjLabelName().setMaximumSize(new Dimension(50, 30));
       
       vasf.getjTextFieldSurname().setMinimumSize(new Dimension(200, 30));
       vasf.getjTextFieldSurname().setPreferredSize(new Dimension(200, 30));
       vasf.getjTextFieldSurname().setMaximumSize(new Dimension(200, 30));
       
       
    }

    private void addActionListener() {
        vasf.backBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vasf.dispose();
            }
        });
        
        vasf.getjTextFieldName().getDocument().addDocumentListener(new DocumentListener() {
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
                String name = vasf.getjTextFieldName().getText().trim();
                System.out.println(name);
                TableModelStudent tms = (TableModelStudent) vasf.getjTableAllStud().getModel();
                 if (name.isEmpty()) {
                    tms.setList(allfStud);
                    tms.fireTableDataChanged();
                } else {
                    tms.searchName(name);
                    
                }
             } 
         });
        
        vasf.getjTextFieldSurname().getDocument().addDocumentListener(new DocumentListener() {
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
                String surname = vasf.getjTextFieldSurname().getText().trim();
                System.out.println(surname);
                TableModelStudent tms = (TableModelStudent) vasf.getjTableAllStud().getModel();
                    if (surname.isEmpty()) {
                         tms.setList(allfStud);
                         tms.fireTableDataChanged();
                     } else {
                         tms.searchSurname(surname);
                         
                     }
                }
            
         });
        
        vasf.delBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = vasf.getjTableAllStud().getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(vasf, "Niste izabrali studenta!", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    TableModelStudent tms = (TableModelStudent) vasf.getjTableAllStud().getModel();
                    Student s = tms.getList().get(row);
                    
                    try {
                        List<Enrollment> allEnrolls = Communication.getInstance().getAllEnrolls();
                        for (Enrollment enrl : allEnrolls) {
                            if(enrl.getStudent().getStudentID() == s.getStudentID()){
                                JOptionPane.showMessageDialog(vasf, "Sistem ne može da obriše učenika jer je već upisan u odeljenje", "Greška", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        
                        try {
                            Communication.getInstance().deleteStudent(s);
                            JOptionPane.showMessageDialog(vasf, "Sistem je uspešno obrisao učenika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                            prepareTable();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(vasf, "Sistem ne može da obriše učenika", bundle.getString("error"), JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(ViewAllStudentsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

           
        });
        
         vasf.viewStudBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = vasf.getjTableAllStud().getSelectedRow();
                try{
                    if(row == -1){
                    JOptionPane.showMessageDialog(vasf, bundle.getString("didnt_pick_stud"), bundle.getString("error"), JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                    TableModelStudent tms = (TableModelStudent) vasf.getjTableAllStud().getModel();
                    Student s = tms.getList().get(row);
                    Coordinator.getInstance().setSelectedStudent(s);
                    try {
                        Coordinator.getInstance().openViewStudForm(FormMod.UPDATE_MAIN);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vasf, "Sistem ne može da prikaže učenika", "Greška", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(ViewClassController.class.getName()).log(Level.SEVERE, null, ex);
                    
                }}
                    }catch(IndexOutOfBoundsException exc){
                        JOptionPane.showMessageDialog(vasf, "Sistem ne može da prikaže učenika", "Greška", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(ViewClassController.class.getName()).log(Level.SEVERE, null, exc);
                    }
                   
                
                }
            
        });
    }
    
     public void prepareTable() throws Exception {
                allfStud = Communication.getInstance().getAllStudents();
                TableModelStudent tms = new TableModelStudent(allfStud);
                vasf.getjTableAllStud().setModel(tms);
     }

    public void refreshViewAllStudTable() throws Exception  {
        prepareTable();
    }

    private void localizeComponents() {
         try {
        vasf.getjButtonDelete().setText(bundle.getString("btn.delete"));
        vasf.getjButtonBack().setText(bundle.getString("btn.back"));
        vasf.getjButtonView().setText(bundle.getString("view"));
        vasf.getjLabel1().setText(capitalizeFirstLetter(bundle.getString("surname")));
        vasf.getjLabelName().setText(capitalizeFirstLetter(bundle.getString("nameStud")));
        } catch (MissingResourceException e) {
             System.err.println("Key '' not found in resource bundle.");
        }
    }
    
    private void updateTableHeaders() {
        String[] newColumnNames = {
            bundle.getString("studentID"),
            bundle.getString("JMBG"),
            bundle.getString("nameStud"),
            bundle.getString("surname"),
            bundle.getString("birthday"),
            bundle.getString("parent_name"),
            bundle.getString("city")
        };

        ((TableModelStudent) vasf.getjTableAllStud().getModel()).updateColumnNames(newColumnNames);
        vasf.getjTableAllStud().revalidate();
        vasf.getjTableAllStud().repaint();
       
    }
    
     private String capitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
