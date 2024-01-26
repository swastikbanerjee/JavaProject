/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

//import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.awt.Color;

public class MiniStatement extends JFrame implements ActionListener{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable t1;
    JButton b1;
    String x[] = {"Deposit","Withdraw","Balance"};
    String y[][] = new String[20][5];
    int i=0, j=0;
    
    MiniStatement(){
        super("Mini Statement");
        setBackground(new Color(90, 249, 68));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200,650);
        setLocation(200,200);
     		 String pinn = JOptionPane.showInputDialog("Enter PIN");

        
        try{
            conn c1  = new conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '" + pinn + "'");
          //  String s1 = "select * from bank";
          //  ResultSet rs  = c1.s.executeQuery(s1);
            while(rs.next()){
                y[i][j++]=rs.getString("deposit");
                y[i][j++]=rs.getString("withdraw");
                y[i][j++]=rs.getString("balance");
                i++;
                j=0;
            }
            t1 = new JTable(y,x);
            t1.setBackground(new Color(53, 234, 21));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        b1 = new JButton("Print");
        getContentPane().add(b1,"South");
        JScrollPane sp = new JScrollPane(t1);
        getContentPane().add(sp);
        b1.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            t1.print();
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        new MiniStatement().setVisible(true);
    }
    
}