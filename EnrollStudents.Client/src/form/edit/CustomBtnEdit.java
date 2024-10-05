/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.edit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author neven
 */
public class CustomBtnEdit {
    private JButton button;

    public CustomBtnEdit(JButton button) {
        this.button = button;
        setFont();
        setBackgroundColor();
        setForegroundColor();
        setBorder();
        setSize();
        button.getParent().revalidate();
        button.getParent().repaint();
    }
    
     public void setFont() {
        button.setFont(new Font("Arial", Font.BOLD, 18));
    }
     
    public void setBackgroundColor() {
        button.setBackground(new Color(200,122,238));
    }
    
    public void setForegroundColor() {
        button.setForeground(Color.WHITE);
    }


    public void setBorder() {
        Border border = new LineBorder(new Color(200,122,238), 10);
        button.setBorder(border);
    }
    
    public void setSize() {
        button.setMinimumSize(new Dimension(100, 35));
        button.setPreferredSize(new Dimension(100, 35));
        button.setMaximumSize(new Dimension(100, 35));
        
    }


    public void setAlignment(int x, int y) {
        //button.setAlignmentX(horizontalAlignment);
       // button.setAlignmentY(verticalAlignment);
        button.setBounds(x, y, 100, 35);
    }
}
