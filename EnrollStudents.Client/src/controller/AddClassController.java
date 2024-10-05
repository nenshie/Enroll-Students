/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import coordinator.Coordinator;
import domain.Grade;
import domain.School;
import domain.Class;
import domain.Student;
import form.AddClassForm;
import form.ClassForm;
import form.FormMod;
import form.MainForm;
import form.TableModelSchool;
import form.TableModelStudent;
import form.ViewClassForm;
import form.edit.CustomTableEditor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author neven
 */
public class AddClassController {
    private final AddClassForm acf;
    FormMod fm;
    ResourceBundle bundle = ResourceBundle.getBundle("resources.messages", Coordinator.getInstance().getCurrentLocale());
    
    public AddClassController(AddClassForm acf) {
        this.acf = acf;
        addActionListener();
    }

    public void openForm(FormMod fm) throws Exception {
        this.fm=fm;
        acf.setVisible(true);
        int width = 1300;
        int height = 800;
        acf.setSize(width, height);
        acf.setDefaultCloseOperation(acf.DISPOSE_ON_CLOSE);
        acf.setLocationRelativeTo(null);
        prepareForm();
        prepareTable();
        localizeComponents();
    }
     private void prepareForm() throws Exception {
        List<School> schools = Communication.getInstance().getAllSchools();
        for(School sc: schools){
            acf.getjComboBoxSchool().addItem(sc);
        }
        
        acf.getjComboBoxGrade().addItem(null); 
        acf.getjComboBoxGrade().addItem(Grade.I);
        acf.getjComboBoxGrade().addItem(Grade.II);
        acf.getjComboBoxGrade().addItem(Grade.III);
        acf.getjComboBoxGrade().addItem(Grade.IV);
        acf.getjComboBoxGrade().addItem(Grade.V);
        acf.getjComboBoxGrade().addItem(Grade.VI);
        acf.getjComboBoxGrade().addItem(Grade.VII);
        acf.getjComboBoxGrade().addItem(Grade.VIII);
        
        if(Coordinator.getInstance().getSelectedSchool() != null){
            acf.getjComboBoxSchool().setSelectedItem(Coordinator.getInstance().getSelectedSchool());
            acf.getjComboBoxSchool().setEditable(false);
        }
        
       acf.getjLabel1().setMinimumSize(new Dimension(100, 30));
       acf.getjLabel1().setPreferredSize(new Dimension(100, 30));
       acf.getjLabel1().setMaximumSize(new Dimension(100, 30));
       
       acf.getjLabel2().setMinimumSize(new Dimension(100, 30));
       acf.getjLabel2().setPreferredSize(new Dimension(100, 30));
       acf.getjLabel2().setMaximumSize(new Dimension(100, 30));
       
       acf.getjLabel3().setMinimumSize(new Dimension(135, 30));
       acf.getjLabel3().setPreferredSize(new Dimension(135, 30));
       acf.getjLabel3().setMaximumSize(new Dimension(135, 30));
       
       acf.getjLabel4().setMinimumSize(new Dimension(100, 30));
       acf.getjLabel4().setPreferredSize(new Dimension(100, 30));
       acf.getjLabel4().setMaximumSize(new Dimension(100, 30));
       
       acf.getjLabel5().setMinimumSize(new Dimension(300, 30));
       acf.getjLabel5().setPreferredSize(new Dimension(300, 30));
       acf.getjLabel5().setMaximumSize(new Dimension(300, 30));
       
       acf.getjTextFieldName().setMinimumSize(new Dimension(200, 30));
       acf.getjTextFieldName().setPreferredSize(new Dimension(200, 30));
       acf.getjTextFieldName().setMaximumSize(new Dimension(200, 30));
       
       acf.getjTextFieldNumOfStud().setMinimumSize(new Dimension(200, 30));
       acf.getjTextFieldNumOfStud().setPreferredSize(new Dimension(200, 30));
       acf.getjTextFieldNumOfStud().setMaximumSize(new Dimension(200, 30));
       
       
       acf.getjComboBoxGrade().setMinimumSize(new Dimension(200, 30));
       acf.getjComboBoxGrade().setPreferredSize(new Dimension(200, 30));
       acf.getjComboBoxGrade().setMaximumSize(new Dimension(200, 30));
       
       acf.getjComboBoxSchool().setMinimumSize(new Dimension(200, 30));
       acf.getjComboBoxSchool().setPreferredSize(new Dimension(200, 30));
       acf.getjComboBoxSchool().setMaximumSize(new Dimension(200, 30));
       
        
    }
     
     private void prepareTable() throws Exception {
        List<Student> allfStud = Communication.getInstance().getAllStudents();
         System.out.println("Broj ucenika u listi je : "+allfStud.size());
        TableModelStudent tms = new TableModelStudent(allfStud);
        acf.getjTableStudents().setModel(tms);
        CustomTableEditor editTable = new CustomTableEditor(acf.getjTableStudents());
        editTable.setTableSize(980, 350);
        int[] widths = {80,150,150,150,150,150,150};
        editTable.setColumnWidths(widths);
        acf.getjScrollPane1().setBackground(new Color(207,139,240));
        acf.getjScrollPane1().getVerticalScrollBar().setBackground(new Color(207,139,240));
        acf.getjTableStudents().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    private void addActionListener()  {
       acf.addClsAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              School sch = (School) acf.getjComboBoxSchool().getSelectedItem();
              String name = acf.getjTextFieldName().getText().trim();
              if(acf.getjTextFieldNumOfStud().getText() == "" || acf.getjTextFieldNumOfStud().getText().equals(null) ||
                    acf.getjTextFieldNumOfStud().getText().isEmpty() || acf.getjTextFieldNumOfStud().getText().isBlank()  ){
                   JOptionPane.showMessageDialog(acf, "Nije unet broj učenika u odeljenju", "Greška", JOptionPane.ERROR_MESSAGE);
                   return;
              }
              int numOfStud = Integer.parseInt(acf.getjTextFieldNumOfStud().getText().trim());
              Grade grade = (Grade) acf.getjComboBoxGrade().getSelectedItem();
              
              if(name == "" || name.equals(null) || name.isBlank() || name.isEmpty()){
                 JOptionPane.showMessageDialog(acf, "Nije unet naziv odeljenja", "Greška", JOptionPane.ERROR_MESSAGE);
                 return;
              }
              
              if(sch == null ){
                   JOptionPane.showMessageDialog(acf, "Niste izabrali skolu", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
              }
              
              if(grade == null){
                   JOptionPane.showMessageDialog(acf, "Niste odabrali razred", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
              }
              if(numOfStud > 35){
                   JOptionPane.showMessageDialog(acf, "U jednom odeljenju ne moe biti vise od 35 ucenika", "Greška", JOptionPane.ERROR_MESSAGE);
                   return;
              }
              
              if(!(isValidFormat(name))){
                 JOptionPane.showMessageDialog(acf, "Naziv odeljenja nije dobar, mora u formatu npr. III-3", "Greška", JOptionPane.ERROR_MESSAGE);
                 return;
              }
              
              String gradeS = String.valueOf(grade);
              String classGrade = name.split("-")[0];
               if (!classGrade.equals(gradeS)) {
                    JOptionPane.showMessageDialog(null, "Odeljenje nije validno, ne poklapa se sa nazivom!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                    } 
               
               List<Student> selectedStudents = new ArrayList<>();
               int[] selectedRows = acf.getjTableStudents().getSelectedRows();
               TableModelStudent tms = (TableModelStudent) acf.getjTableStudents().getModel();
               for (int rowIndex : selectedRows) {
                Student selectedStudent = (Student) tms.getList().get(rowIndex);
                selectedStudents.add(selectedStudent);
            }
               System.out.println(selectedStudents);
               
               
               Class cl = new Class(0, name, numOfStud, grade, sch);
               try {
                   List<Class> allCls = Communication.getInstance().getAllClasses(cl.getSchool());
                   for(Class clss: allCls){
                       if(cl.equals(clss)){
                         JOptionPane.showMessageDialog(acf, "Ovakvo odeljenje već postoji u izabranoj školi!", "Greška", JOptionPane.ERROR_MESSAGE);
                            return;
                       }
                   }
               } catch (Exception ex) {
                   Logger.getLogger(AddClassController.class.getName()).log(Level.SEVERE, null, ex);
               }
               
                HashMap<Class, List<Student>> map = new HashMap<>();
                map.put(cl, selectedStudents);
               try {
                   Communication.getInstance().addClass(map);
                   JOptionPane.showMessageDialog(acf, "Sistem je uspešno kreirao odeljenje", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                   if(fm.equals(FormMod.NOT_MAIN)){
                       Coordinator.getInstance().refreshTableClassForm();
                   }
                   acf.dispose();
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(acf, "Učenici su već upisani u postojeće odeljenje, odeljenje nije kreirano", "Greska", JOptionPane.ERROR_MESSAGE);//"Sistem ne može da doda odeljenje"
                   selectedStudents.removeAll(selectedStudents);
                   ListSelectionModel selectionModel = acf.getjTableStudents().getSelectionModel();
                   for (int row : selectedRows) {
                        selectionModel.removeSelectionInterval(row, row);
                    }
                       return;
               }
           }
       });
       
       acf.cancelBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               acf.dispose();
                }
       });
         
    }

  public boolean isValidFormat(String name) {
        String regex = "^(I{1,3}|IV|V|VI{0,3}|VII|VIII|1|2|3|4|5|6|7|8|9|10)(-(I{1,3}|IV|V|VI{0,3}|VII|VIII|1|2|3|4|5|6|7|8|9|10))*$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(name);
            boolean matches = matcher.matches();
            return matches;
        }

    private void localizeComponents() {
        try {
        acf.getjButtonAdd().setText(bundle.getString("btn.add"));
        acf.getjButtonCnacel().setText(bundle.getString("btn.cancel"));
        acf.getjLabel1().setText(capitalizeFirstLetter(bundle.getString("school")));
        acf.getjLabel2().setText(capitalizeFirstLetter(bundle.getString("name")));
        acf.getjLabel3().setText(capitalizeFirstLetter(bundle.getString("num_of_stud")));
        acf.getjLabel4().setText(capitalizeFirstLetter(bundle.getString("grade")));
        acf.getjLabel5().setText(capitalizeFirstLetter(bundle.getString("pick_stud")));
        updateTableHeaders();
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

        ((TableModelStudent) acf.getjTableStudents().getModel()).updateColumnNames(newColumnNames);
        acf.getjTableStudents().revalidate();
        acf.getjTableStudents().repaint();
       
    }
}
