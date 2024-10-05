/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;


import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author neven
 */
public class TableModelClass extends AbstractTableModel{
    
     private List<domain.Class> list;
    private String[] columns = {"classID", "naziv", "broj ucenika", "razred", "skola"};

    public TableModelClass(List<domain.Class> list) {
        this.list = list;
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
        domain.Class c = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getClassID();
            case 1:
                return c.getName();
            case 2: 
                return c.getNumOfStud();
            case 3:
                return c.getGrade();
            case 4:
                return c.getSchool().getName();
            default:
                return "N/A";
        }
    }
    public String getColumnName(int column) {
        return columns[column];
    }

    public List<domain.Class> getList() {
        return list;
    }

    public void setList(List<domain.Class> list) {
        this.list = list;
    }
    
     public void updateColumnNames(String[] newColumnNames) {
        this.columns = newColumnNames;
        fireTableStructureChanged(); 
    }
}
