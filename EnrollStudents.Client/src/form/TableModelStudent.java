/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

import domain.School;
import domain.Student;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author neven
 */
public class TableModelStudent extends AbstractTableModel{
    private List<Student> list;
     //private List<Student> displayedList;
    private String[] columns = {"ID", "JMBG", "ime", "prezime", "rodjendan", "ime roditelja", "grad"};
    private String notFoundMessage = "Not Found";
    
    public TableModelStudent(List<Student> list) {
        this.list = list;
    }
    
    
    @Override
    public int getRowCount() {
          return list.isEmpty() ? 1 : list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         if (list.isEmpty()) {
            if (columnIndex == 0) {
                return notFoundMessage;
            }
            return "";
        }
        
       Student s = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getStudentID();
            case 1:
                return s.getJMBG();
            case 2: 
                return s.getName();
            case 3:
                return s.getSurname();
            case 4:
                return s.getBirthday();
            case 5:
                return s.getParent();
            case 6: 
                return s.getCity().getName();
            default:
                return "N/A";
        }
    }
    public String getColumnName(int column) {
        return columns[column];
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> students) {
        this.list = new ArrayList<>(students); 
        fireTableDataChanged(); 
    }

    public void searchName(String name) {
        List<Student> foundStudent = new ArrayList<>();
        for (Student s : list) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                foundStudent.add(s);
            }
        }
        this.list = foundStudent;
        fireTableDataChanged();
    }

    public void searchSurname(String surname) {
        List<Student> foundStudent = new ArrayList<>();
        for (Student s : list) { 
            if (s.getSurname().toLowerCase().contains(surname.toLowerCase())) {
                foundStudent.add(s);
            }
        }
        this.list = foundStudent;
        fireTableDataChanged(); 
    }

 public void updateColumnNames(String[] newColumnNames) {
        this.columns = newColumnNames;
        fireTableStructureChanged(); 
    }
}
