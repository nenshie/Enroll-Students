/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import coordinator.Coordinator;
import domain.City;
import domain.Student;
import form.FormMod;
import form.ViewStudentForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author neven
 */
public class ViewStudentController {
    private final ViewStudentForm vsf;
    FormMod fm;

    public ViewStudentController(ViewStudentForm vsf) {
        this.vsf = vsf;
        addActionListener();
    }

    public void openForm(FormMod fm) throws Exception {
        this.fm =fm;
        vsf.setVisible(true);
        vsf.setLocationRelativeTo(null);
        vsf.setDefaultCloseOperation(vsf.DISPOSE_ON_CLOSE);
        prepareForm(fm);
        
    }

    private void prepareForm(FormMod fm) throws Exception {
        if(fm.equals(FormMod.UPDATE)){
            Student st = Coordinator.getInstance().getSelectedStudent();
            List<City> allCities = Communication.getInstance().getAllCities();
            for (City cit : allCities) {
                vsf.getjComboBoxCity().addItem(cit);
            }
            vsf.getjButtonAdd().setVisible(false);
            vsf.getjButtonUpdate().setVisible(true);
            
            vsf.getjTextFieldStudentID().setText(String.valueOf(st.getStudentID()));
            vsf.getjTextFieldStudentID().setEditable(false);
            vsf.getjTextFieldJMBG().setText(st.getJMBG());
            vsf.getjTextFieldName().setText(st.getName());
            vsf.getjTextFieldSurname().setText(st.getSurname());
            Date birthday = st.getBirthday();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = dateFormat.format(birthday);
            vsf.getjTextFieldBirhday().setText(formatDate);
            vsf.getjTextFieldParent().setText(st.getParent());
            vsf.getjComboBoxCity().setSelectedItem(st.getCity());
            
            
        }else if(fm.equals(FormMod.ADD)){
            List<City> allCities = Communication.getInstance().getAllCities();
            for (City cit : allCities) {
                vsf.getjComboBoxCity().addItem(cit);
            }
            vsf.getjButtonAdd().setVisible(true);
            vsf.getjButtonUpdate().setVisible(false);
            vsf.getjTextFieldStudentID().setEditable(false);
            
        }else if(fm.equals(FormMod.UPDATE_MAIN)){
           Student st = Coordinator.getInstance().getSelectedStudent();
            List<City> allCities = Communication.getInstance().getAllCities();
            for (City cit : allCities) {
                vsf.getjComboBoxCity().addItem(cit);
            }
            vsf.getjButtonAdd().setVisible(false);
            vsf.getjButtonUpdate().setVisible(true);
            
            vsf.getjTextFieldStudentID().setText(String.valueOf(st.getStudentID()));
            vsf.getjTextFieldStudentID().setEditable(false);
            vsf.getjTextFieldJMBG().setText(st.getJMBG());
            vsf.getjTextFieldName().setText(st.getName());
            vsf.getjTextFieldSurname().setText(st.getSurname());
            Date birthday = st.getBirthday();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = dateFormat.format(birthday);
            vsf.getjTextFieldBirhday().setText(formatDate);
            vsf.getjTextFieldParent().setText(st.getParent());
            vsf.getjComboBoxCity().setSelectedItem(st.getCity());
             
        
        }
    }
    
    private void addActionListener() {
        vsf.backBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               vsf.dispose(); 
            }
        });
        
        vsf.updateAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(vsf.getjTextFieldStudentID().getText().trim());
                String jmbg = vsf.getjTextFieldJMBG().getText().trim();
                String name = vsf.getjTextFieldName().getText().trim();
                String surname = vsf.getjTextFieldSurname().getText().trim();
                String dateString = vsf.getjTextFieldBirhday().getText().trim(); //2012-07-15
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if(dateString.equals(null)|| dateString.equals("") || dateString.matches(".*[a-zA-Z]+.*")){
                    JOptionPane.showMessageDialog(vsf, "Nije unet ispravan datum", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Date utilDate = dateFormat.parse(dateString);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                
                System.out.println(dateString);
                System.out.println("datum util: "+utilDate);
                System.out.println("datum sq;: "+sqlDate);
                String parent = vsf.getjTextFieldParent().getText().trim();
                City city = (City) vsf.getjComboBoxCity().getSelectedItem();
                
                if(!(isValidJMBG(jmbg))){
                    JOptionPane.showMessageDialog(vsf, "Jmbg mora da ima 13 karaktera i svi moraju biti brojevi","Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if(name == "" || surname == "" || parent == ""|| jmbg.equals(null) || jmbg.equals("") || name.equals(null) ||
                        surname.equals(null) || parent.equals(null) || name.isEmpty() || surname.isEmpty() || parent.isEmpty() ||
                        name.isBlank() || surname.isBlank() || parent.isBlank()){
                    JOptionPane.showMessageDialog(vsf, "Nisu uneta sva polja", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Student newStud = new Student(id, jmbg, name, surname, sqlDate, parent, city);
                
                try {
                    Communication.getInstance().updateStudent(newStud);
                    JOptionPane.showMessageDialog(vsf, "Sistem je uspešno ažurirao učenika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    if(fm.equals(FormMod.UPDATE_MAIN)){
                        Coordinator.getInstance().updateViewAllStudTable();
                    }else{
                        Coordinator.getInstance().refreshViewClassTable();
                    }
                    
                    vsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vsf, "Sistem ne moze da ažurira učenika", "Greska", JOptionPane.ERROR_MESSAGE);
                       return;
                }
                
                    } catch (Exception ex) {
                        Logger.getLogger(ViewStudentController.class.getName()).log(Level.SEVERE, null, ex);
                    
                    }
            }
        });
        
        vsf.addStudAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String jmbg = vsf.getjTextFieldJMBG().getText().trim();
                String name = vsf.getjTextFieldName().getText().trim();
                String surname = vsf.getjTextFieldSurname().getText().trim();
                String dateString = vsf.getjTextFieldBirhday().getText().trim(); //2012-07-15
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if(dateString.equals(null)|| dateString.equals("") || dateString.matches(".*[a-zA-Z]+.*")){
                    JOptionPane.showMessageDialog(vsf, "Nije unet ispravan datum", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Date utilDate = dateFormat.parse(dateString);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                
                System.out.println(dateString);
                System.out.println("datum util: "+utilDate);
                System.out.println("datum sq;: "+sqlDate);
                String parent = vsf.getjTextFieldParent().getText().trim();
                City city = (City) vsf.getjComboBoxCity().getSelectedItem();
                
                if(!(isValidJMBG(jmbg))){
                    JOptionPane.showMessageDialog(vsf, "Jmbg mora da ima 13 karaktera i svi moraju biti brojevi","Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if(name == "" || surname == "" || parent == ""|| jmbg.equals(null) || jmbg.equals("") || name.equals(null) ||
                        surname.equals(null) || parent.equals(null) || name.isEmpty() || surname.isEmpty() || parent.isEmpty() ||
                        name.isBlank() || surname.isBlank() || parent.isBlank()){
                    JOptionPane.showMessageDialog(vsf, "Nisu uneta sva polja", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Student newStud = new Student(0, jmbg, name, surname, sqlDate, parent, city);
                
                try {
                    Communication.getInstance().addStudent(newStud);
                    JOptionPane.showMessageDialog(vsf, "Sistem je uspešno dodao učenika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Coordinator.getInstance().updateViewAllStudTable();
                    vsf.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(vsf, "Sistem ne može da zapamti učenika, učenik već postoji u sistemu", "Greska", JOptionPane.ERROR_MESSAGE);
                       return;
                }
                
                    } catch (Exception ex) {
                        Logger.getLogger(ViewStudentController.class.getName()).log(Level.SEVERE, null, ex);
                    
                    }
            }
        });
        
    }

    public static boolean isValidJMBG(String jmbg) {
        return jmbg.matches("^\\d{13}$");
    }
    
    
}
