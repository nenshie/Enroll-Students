/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;


import domain.School;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author neven
 */
public class TableModelSchool extends AbstractTableModel{

    private List<School> list;
    int[] classCount;
    private String[] columns = {"schoolID", "naziv", "adresa", "kontakt", "grad", "broj odeljenja"};

    public TableModelSchool(List<School> list, int[] classCount) {
        this.list = list;
        this.classCount = classCount;
    }

    
    
    
    @Override
    public int getRowCount() {
       return list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      
        
       School s = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getSchoolID();
            case 1:
                return s.getName();
            case 2: 
                return s.getAddress();
            case 3:
                return s.getPhone();
            case 4:
                return s.getCity().getName();
            case 5:
                return classCount[rowIndex];
            default:
                return "N/A";
        }
    }
    public String getColumnName(int column) {
        return columns[column];
    }

    public List<School> getList() {
        return list;
    }

    public void setList(List<School> list) {
        this.list = list;
        fireTableDataChanged();
    }



   public void updateColumnNames(String[] newColumnNames) {
        this.columns = newColumnNames;
        fireTableStructureChanged(); 
    }
    
  

}
