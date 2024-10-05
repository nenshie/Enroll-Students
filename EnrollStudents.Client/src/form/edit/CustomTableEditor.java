/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.edit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author neven
 */
public class CustomTableEditor {
    private JTable table;

    public CustomTableEditor(JTable table) {
        this.table = table;
        setRowHeight();
        setHeaderBackground();
        setHeaderFont();
        setColumnsNonResizable();
        setCellAlignment();
        setCellAndGridBackground();
        setCellFont();
    }

    
    
    public void setColumnWidths(int[] widths) {
         //int[] widths = {80,200,250,150,200,200,200};
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(widths[i]);
        }
    }

     public void setTableSize(int width, int height) {
        table.setPreferredScrollableViewportSize(new Dimension(width,height));
    }
    public void setRowHeight() {
        table.setRowHeight(40);
    }

    
    public void setHeaderBackground() {
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(207,139,240));
    }

    
    public void setHeaderFont() {
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 17));
    }

    
    public void setColumnsNonResizable() {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setResizable(false);
        }
    }
    
    
    
     public void setCellAlignment() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
    }

     
     public void setCellAndGridBackground() {
        table.setBackground(new Color(227,192,255));
        table.setGridColor(Color.WHITE);
    }

    
    public void setCellFont() {
        table.setFont(new Font("Arial", Font.PLAIN, 15));
    } 
    
   
}
