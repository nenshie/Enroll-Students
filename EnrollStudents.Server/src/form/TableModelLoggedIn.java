/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

import domain.Administrator;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author neven
 */
public class TableModelLoggedIn extends AbstractTableModel{
    
    private List<Administrator> list;
    private String[] columns = {"ime", "prezime"};

    public TableModelLoggedIn() {
    }

    public TableModelLoggedIn(List<Administrator> list) {
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
        Administrator a = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getName();
            case 1:
                return a.getSurname();
            default:
                return "N/A";
        }
    }
    public String getColumnName(int column) {
        return columns[column];
    }

    public List<Administrator> getList() {
        return list;
    }

    public void setList(List<Administrator> list) {
        this.list = list;
    }
    
    
}
