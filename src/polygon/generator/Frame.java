/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polygon.generator;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author hp
 */
public class Frame extends JFrame{
    
    private Screen app;
    private JButton info,clear;
    private JFrame frame;
    private JLabel label;
    
    Frame(){
        frame= this;
        this.setSize(700,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Font font = new Font("Arial",Font.ITALIC,20);

        JPanel panel2= new JPanel();
        label= new JLabel("Click on Screen For First Point Of Polygon");
        panel2.add(label);
        label.setFont(font);
        label.setForeground(Color.red);
        panel2.setBackground(new Color(204,204,255));
        
        this.add(panel2,BorderLayout.NORTH);
        app=new Screen(label);
        this.add(app);
        
        JPanel panel = new JPanel();
        
        info=new JButton("Get Info");
        info.setFont(font);
        
        clear=new JButton("Clear");
        clear.setFont(font);
    
        panel.add(info);
        panel.add(clear);
        panel.setBackground(new Color(255,153,153));
        
        this.add(panel,BorderLayout.SOUTH);
        info.addActionListener(new ListenForInfo());
        clear.addActionListener(new ListenForClear());
        
        this.setVisible(true);
        this.setTitle("Polygon Generator");
        
    }

    private class ListenForClear implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==clear){
                app.clear();
            }
            
        }
        
        
    }
    
    private class ListenForInfo implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==info){
                
                if(!app.getD()){
                    
                    JOptionPane.showMessageDialog(frame, "First create Polygon By Clicking 'Done'!!!");
                    
                }
                
                else{
                    
                    String str=app.getShape().getString();                  
                    JOptionPane.showMessageDialog(frame, str);                    
                    
                }
                
            }
            
        }
        
        
    }
    
    public static void main(String args[]){
        new Frame();
    }
    
    
    
}
