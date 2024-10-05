/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import communication.Response;
import coordinator.Coordinator;
import domain.Administrator;
import domain.School;
import domain.Class;
import domain.Enrollment;
import domain.Student;
import form.ClassForm;
import form.EnrollStudentForm;
import form.TableModelSchool;
import form.TableModelStudent;
import form.ViewClassForm;
import form.edit.CustomTableEditor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author neven
 */
public class EnrollStudentController {
    private final EnrollStudentForm esf;
    List<Student> enrollStud = new ArrayList<>();
    ResourceBundle bundle = ResourceBundle.getBundle("resources.messages", Coordinator.getInstance().getCurrentLocale());

    public EnrollStudentController(EnrollStudentForm esf) {
        this.esf = esf;
        addActionListener();
        localizeComponents();
    }

    public void openForm() throws Exception {
        esf.setVisible(true);
        
        int width = 1200;
        int height = 700;
        esf.setSize(width, height);
        esf.setDefaultCloseOperation(esf.DISPOSE_ON_CLOSE);
        esf.setLocationRelativeTo(null);
        prepareTable();
        editForm();
    }
    private void editForm() {
       esf.getjLabel1().setMinimumSize(new Dimension(100, 30));
       esf.getjLabel1().setPreferredSize(new Dimension(100, 30));
       esf.getjLabel1().setMaximumSize(new Dimension(100, 30));
       
       esf.getjTextFieldName().setMinimumSize(new Dimension(200, 30));
       esf.getjTextFieldName().setPreferredSize(new Dimension(200, 30));
       esf.getjTextFieldName().setMaximumSize(new Dimension(200, 30));
       
       esf.getjLabel2().setMinimumSize(new Dimension(100, 30));
       esf.getjLabel2().setPreferredSize(new Dimension(100, 30));
       esf.getjLabel2().setMaximumSize(new Dimension(100, 30));
       
       esf.getjTextFieldSurname().setMinimumSize(new Dimension(200, 30));
       esf.getjTextFieldSurname().setPreferredSize(new Dimension(200, 30));
       esf.getjTextFieldSurname().setMaximumSize(new Dimension(200, 30));
    }

    private void prepareTable() throws Exception {
        
        List<Enrollment> enrollments = Communication.getInstance().getAllEnrolls();
        List<Student> allStudents = Communication.getInstance().getAllStudents();
        for (Student student : allStudents) {
            boolean isEnrolled = false;
            for (Enrollment enrollment : enrollments) {
                if (enrollment.getStudent().getStudentID() == student.getStudentID()) {
                    isEnrolled = true;
                    break; 
                }
            }
            if (!isEnrolled) {
                enrollStud.add(student);
            }
        }   
        
        TableModelStudent tms = new TableModelStudent(enrollStud);
        esf.getjTableStudent().setModel(tms);
        
       CustomTableEditor editTable = new CustomTableEditor(esf.getjTableStudent());
       editTable.setTableSize(980, 445);
       int[] widths = {80,150,150,150,150,150,150};
       editTable.setColumnWidths(widths);
       esf.getjScrollPane1().setBackground(new Color(207,139,240));
       esf.getjScrollPane1().getVerticalScrollBar().setBackground(new Color(207,139,240));
        esf.getjTableStudent().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        updateTableHeaders();
    }

    private void addActionListener() {
        esf.cancelBtnAddAcitonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(esf, bundle.getString("confirm_message"), "?", JOptionPane.YES_NO_OPTION);
              if (result == JOptionPane.YES_OPTION) {
                    esf.dispose();
                } else if (result == JOptionPane.NO_OPTION) {
                    return;
                }
            }
        });
        
        esf.pickBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = esf.getjTableStudent().getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(esf,bundle.getString("didnt_pick_stud"), bundle.getString("error"), JOptionPane.ERROR_MESSAGE);
                }else{
                    List<Student> selectedStudents = new ArrayList<>();
                    int[] selectedRows = esf.getjTableStudent().getSelectedRows();
                    TableModelStudent tms = (TableModelStudent) esf.getjTableStudent().getModel();
                    for (int rowIndex : selectedRows) {
                     Student selectedStudent = (Student) tms.getList().get(rowIndex);
                     selectedStudents.add(selectedStudent);
                 }
                    System.out.println(selectedStudents);
                    try {
                        Coordinator.getInstance().updateStudentTable(selectedStudents);
                    } catch (Exception ex) {
                        Logger.getLogger(EnrollStudentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    esf.dispose();
            }
 
            }
        });
        
        esf.getjTextFieldName().getDocument().addDocumentListener(new DocumentListener() {
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
                    String name = esf.getjTextFieldName().getText().trim();
                    TableModelStudent tms = (TableModelStudent) esf.getjTableStudent().getModel();

                    if (name.isEmpty()) {
                        tms.setList(enrollStud); 
                    } else {
                        tms.searchName(name);
                    }
                }
            });

            esf.getjTextFieldSurname().getDocument().addDocumentListener(new DocumentListener() {
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
                    String surname = esf.getjTextFieldSurname().getText().trim();
                    TableModelStudent tms = (TableModelStudent) esf.getjTableStudent().getModel();

                    if (surname.isEmpty()) {
                        tms.setList(enrollStud);  
                    } else {
                        tms.searchSurname(surname);
                    }
                }
            });
    }

    private void localizeComponents() {
         try {
        esf.getjButtonPick().setText(bundle.getString("pick"));
        esf.getjButtonCnacel().setText(bundle.getString("btn.cancel"));
        esf.getjLabel1().setText(capitalizeFirstLetter(bundle.getString("nameStud")));
        esf.getjLabel2().setText(capitalizeFirstLetter(bundle.getString("surname")));
        } catch (MissingResourceException e) {
             System.err.println("Key '' not found in resource bundle.");
        }
    }

    private String capitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
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

        ((TableModelStudent) esf.getjTableStudent().getModel()).updateColumnNames(newColumnNames);
        esf.getjTableStudent().revalidate();
        esf.getjTableStudent().repaint();
       
    }


    
    
}
