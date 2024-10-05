/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import coordinator.Coordinator;
import form.FormMod;
import form.ViewClassForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import domain.Class;
import domain.Grade;
import domain.School;
import domain.Student;
import form.TableModelSchool;
import form.TableModelStudent;
import form.edit.CustomTableEditor;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author neven
 */
public class ViewClassController {
    private final ViewClassForm vcf;
    List<Student> listOfStud;
    List<Student> studToAdd = new ArrayList<>();
    public ViewClassController(ViewClassForm vcf) throws Exception {
        this.vcf = vcf;
        int width = 1100;
        int height = 700;
        vcf.setSize(width, height);
        vcf.setDefaultCloseOperation(vcf.DISPOSE_ON_CLOSE);
        addActionListener();
        prepareForm();
    }

    public void openForm() {
        vcf.setVisible(true);
        vcf.setLocationRelativeTo(null);
    }

     private void prepareForm() throws Exception {
        Class cl = Coordinator.getInstance().getSelectedClass();
        vcf.getjTextFieldClassID().setText(String.valueOf(cl.getClassID()));
        vcf.getjTextFieldClassID().setEditable(false);
        vcf.getjTextFieldName().setText(cl.getName());
        vcf.getjTextFieldNumOfStud().setText(String.valueOf(cl.getNumOfStud()));
        vcf.getjTextFieldGrade().setText(cl.getGrade().toString());
        vcf.getjTextFieldSchool().setText(Coordinator.getInstance().getSelectedSchool().getName());
        vcf.getjTextFieldSchool().setEditable(false);
       preprareTable();
       
       vcf.getjLabel1().setMinimumSize(new Dimension(120, 30));
       vcf.getjLabel1().setPreferredSize(new Dimension(120, 30));
       vcf.getjLabel1().setMaximumSize(new Dimension(120, 30));
        
       vcf.getjLabel2().setMinimumSize(new Dimension(120, 30));
       vcf.getjLabel2().setPreferredSize(new Dimension(120, 30));
       vcf.getjLabel2().setMaximumSize(new Dimension(120, 30));
       
       vcf.getjLabel3().setMinimumSize(new Dimension(100, 30));
       vcf.getjLabel3().setPreferredSize(new Dimension(100, 30));
       vcf.getjLabel3().setMaximumSize(new Dimension(100, 30));
       
       vcf.getjLabel4().setMinimumSize(new Dimension(100, 30));
       vcf.getjLabel4().setPreferredSize(new Dimension(100, 30));
       vcf.getjLabel4().setMaximumSize(new Dimension(100, 30));
       
       vcf.getjLabel5().setMinimumSize(new Dimension(150, 30));
       vcf.getjLabel5().setPreferredSize(new Dimension(150, 30));
       vcf.getjLabel5().setMaximumSize(new Dimension(150, 30));
       
       vcf.getjLabelName().setMinimumSize(new Dimension(100, 30));
       vcf.getjLabelName().setPreferredSize(new Dimension(100, 30));
       vcf.getjLabelName().setMaximumSize(new Dimension(100, 30));
       
       vcf.getjTextFieldName().setMinimumSize(new Dimension(200, 30));
       vcf.getjTextFieldName().setPreferredSize(new Dimension(200, 30));
       vcf.getjTextFieldName().setMaximumSize(new Dimension(200, 30));
       
       vcf.getjTextFieldClassID().setMinimumSize(new Dimension(200, 30));
       vcf.getjTextFieldClassID().setPreferredSize(new Dimension(200, 30));
       vcf.getjTextFieldClassID().setMaximumSize(new Dimension(200, 30));
       
       vcf.getjTextFieldGrade().setMinimumSize(new Dimension(200, 30));
       vcf.getjTextFieldGrade().setPreferredSize(new Dimension(200, 30));
       vcf.getjTextFieldGrade().setMaximumSize(new Dimension(200, 30));
       
       vcf.getjTextFieldNumOfStud().setMinimumSize(new Dimension(200, 30));
       vcf.getjTextFieldNumOfStud().setPreferredSize(new Dimension(200, 30));
       vcf.getjTextFieldNumOfStud().setMaximumSize(new Dimension(200, 30));
       
       vcf.getjTextFieldSchool().setMinimumSize(new Dimension(200, 30));
       vcf.getjTextFieldSchool().setPreferredSize(new Dimension(200, 30));
       vcf.getjTextFieldSchool().setMaximumSize(new Dimension(200, 30));
    }
     
     public void preprareTable() throws Exception {
         
         listOfStud = Communication.getInstance().getStudents(Coordinator.getInstance().getSelectedClass());
         TableModelStudent tms = new TableModelStudent(listOfStud);
         vcf.getjTableStudent().setModel(tms);
         
         CustomTableEditor editTable = new CustomTableEditor(vcf.getjTableStudent()); 
         editTable.setTableSize(980, 200);
         int[] widths = {80,150,150,150,150,150,150};
         editTable.setColumnWidths(widths);
         vcf.getjScrollPane1().setBackground(new Color(207,139,240));
         vcf.getjScrollPane1().getVerticalScrollBar().setBackground(new Color(207,139,240));
         
         
     }

    
    private void addActionListener() {
        vcf.backBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int result = JOptionPane.showConfirmDialog(vcf, "Da li ste sigurni?", "?", JOptionPane.YES_NO_OPTION);
              if (result == JOptionPane.YES_OPTION) {
                  try {
                      Coordinator.getInstance().openClassForm();
                  } catch (Exception ex) {
                      Logger.getLogger(ViewSchoolController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                    vcf.dispose();
                } else if (result == JOptionPane.NO_OPTION) {
                    return;
                }
            }
        });
        
        vcf.enrollStudentBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int result = JOptionPane.showConfirmDialog(vcf, "Dodajte ucenika u odeljenje?", "?", JOptionPane.YES_NO_OPTION);
              if (result == JOptionPane.YES_OPTION) {
                  try {
                      Coordinator.getInstance().openEnrollStudForm();
                  } catch (Exception ex) {
                      Logger.getLogger(ViewSchoolController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                    
                } else if (result == JOptionPane.NO_OPTION) {
                    return;
                }
            }
        });
        
        vcf.viewStudBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = vcf.getjTableStudent().getSelectedRow();
                try{
                    if(row == -1){
                    JOptionPane.showMessageDialog(vcf, "Niste izabrali učenika!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                    TableModelStudent tms = (TableModelStudent) vcf.getjTableStudent().getModel();
                    Student s = tms.getList().get(row);
                    Coordinator.getInstance().setSelectedStudent(s);
                    try {
                        Coordinator.getInstance().openViewStudForm(FormMod.UPDATE);
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vcf, "Sistem ne može da prikaže učenika", "Greška", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(ViewClassController.class.getName()).log(Level.SEVERE, null, ex);
                    
                }}
                    }catch(IndexOutOfBoundsException exc){
                        JOptionPane.showMessageDialog(vcf, "Sistem ne može da prikaže učenika", "Greška", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(ViewClassController.class.getName()).log(Level.SEVERE, null, exc);
                    }
                   
                
                }

        });
        
        vcf.saveChangeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = vcf.getjTextFieldName().getText();
                int numOfStud = Integer.parseInt(vcf.getjTextFieldNumOfStud().getText());
                Grade grade = Grade.valueOf(vcf.getjTextFieldGrade().getText());
                School sc = Coordinator.getInstance().getSelectedSchool();
                int id = Coordinator.getInstance().getSelectedClass().getClassID();
                 
                if(name == "" || name.equals(null) || name.isBlank() || name.isEmpty()){
                 JOptionPane.showMessageDialog(vcf, "Nije unet naziv odeljenja", "Greška", JOptionPane.ERROR_MESSAGE);
                 return;
               }
                
                if(!(isValidFormat(name))){
                 JOptionPane.showMessageDialog(vcf, "Naziv odeljenja nije dobar, mora u formatu npr. III-3", "Greška", JOptionPane.ERROR_MESSAGE);
                 return;
              }
              
                 if(numOfStud > 35){
                   JOptionPane.showMessageDialog(vcf, "U jednom odeljenju ne moe biti vise od 35 ucenika", "Greška", JOptionPane.ERROR_MESSAGE);
                   return;
              }
                String gradeS = String.valueOf(grade);
                String classGrade = name.split("-")[0];
                 if (!classGrade.equals(gradeS)) {
                      JOptionPane.showMessageDialog(null, "Odeljenje nije validno, ne poklapa se sa nazivom!", "Greška", JOptionPane.ERROR_MESSAGE);
                      return;
                      } 
                
                
                 Class newcl = new Class(id, name, numOfStud, grade, sc);
                 HashMap<Class, List<Student>> map = new HashMap<>();
                    map.put(newcl, studToAdd);
                 try {
                    Communication.getInstance().updateClass(map);
                    JOptionPane.showMessageDialog(vcf, "Sistem je uspešno ažurirao odeljenje", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Coordinator.getInstance().setSelectedClass(newcl);
                    prepareForm();
                    refreshTable();
                    studToAdd = new ArrayList<>();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vcf, "Sistem ne moze da ažurira odeljenje", "Greska", JOptionPane.ERROR_MESSAGE);
                       return;
                }
            }
        });
    }

    public void refreshTable() throws Exception {
        preprareTable();
    }

    public void updateStudentTable(List<Student> selectedStudents) throws Exception {
        studToAdd = selectedStudents;
        TableModelStudent tms = (TableModelStudent) vcf.getjTableStudent().getModel();
        List<Student> currentStudents = tms.getList();
        currentStudents.addAll(selectedStudents);
        tms.setList(currentStudents);
        tms.fireTableDataChanged();
    }

    
    public boolean isValidFormat(String name) {
        String regex = "^(I{1,3}|IV|V|VI{0,3}|VII|VIII|1|2|3|4|5|6|7|8|9|10)(-(I{1,3}|IV|V|VI{0,3}|VII|VIII|1|2|3|4|5|6|7|8|9|10))*$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(name);
            boolean matches = matcher.matches();
            return matches;
        }
    
}
