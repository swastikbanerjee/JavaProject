/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
//import java.sql.*;
//import java.util.*;

public class Deposit extends JFrame implements ActionListener{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3;
    Connection con;
    ResultSet rs;
    PreparedStatement pd;
    long bal;
    
    Deposit(){        
        setFont(new Font("System", Font.BOLD, 22));
        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int x = fm.stringWidth("DEPOSIT");
        int y = fm.stringWidth(" ");
        int z = getWidth() - (6*x);
        int w = z/y;
        String pad ="";
        pad = String.format("%"+w+"s", pad);
        setTitle(pad+"DEPOSIT PAGE");
        
        
        l1 = new JLabel("ENTER AMOUNT YOU WANT");
        l1.setFont(new Font("System", Font.BOLD, 35));
        
        l2 = new JLabel("TO DEPOSIT");
        l2.setFont(new Font("System", Font.BOLD, 35));
        
        l3 = new JLabel("Enter Pin");
        l3.setFont(new Font("System", Font.BOLD, 14));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.BOLD, 14));
        
        b1 = new JButton("DEPOSIT");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
    
        b2 = new JButton("BACK");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("EXIT");
        b3.setFont(new Font("System", Font.BOLD, 18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        
        getContentPane().setLayout(null);
        
        l3.setBounds(610,10,70,30);
        getContentPane().add(l3);
        
        t2.setBounds(685,10,45,30);
        getContentPane().add(t2);
        
        l1.setBounds(150,150,800,60);
        getContentPane().add(l1);
        
        l2.setBounds(290,210,800,60);
        getContentPane().add(l2);
        
        t1.setBounds(250,300,300,50);
        getContentPane().add(t1);
        
        b1.setBounds(260,380,125,50);
        getContentPane().add(b1);
        
        b2.setBounds(415,380,125,50);
        getContentPane().add(b2);
        
        b3.setBounds(300,550,200,50);
        getContentPane().add(b3);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        getContentPane().setBackground(new Color(53, 234, 21));
        
        setSize(750,650);
        setLocation(400,100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
       
        try{        
           
            
            
            if(ae.getSource()==b1){
            	long a = Long.parseLong(t1.getText());
                long b = Long.parseLong(t2.getText());
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
            	pd = con.prepareStatement("Select * from login where pin=?");
            	pd.setLong(1, b);
            	rs = pd.executeQuery();
                if(t1.getText().equals("")){
                    
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
                
                }else if(!rs.next()){JOptionPane.showMessageDialog(null, "Wrong PIN!");
        		new Transactions().setVisible(true);
                setVisible(false);} else{
                    
                    conn c1 = new conn();
                    try {
                    	
                    pd = con.prepareStatement("Select balance from bank where pin = ?");
                    
                    pd.setLong(1, b);
                    
                    rs = pd.executeQuery();
                    
                    while(rs.next())
                    {
                    	bal = rs.getLong("balance");
                    }
                    	bal += a;}
                    catch(NullPointerException e)
                    {
                    	bal = a;
                    }
                    String q1= "insert into bank values('"+b+"','"+a+"','"+0+"','"+bal+"')";
                    c1.s.executeUpdate(q1);
                    
                   
                    JOptionPane.showMessageDialog(null, "Rs. "+a+" Deposited Successfully");
                    
                    new Transactions().setVisible(true);
                    setVisible(false);
                    
                    
                    
                }
                
            }else if(ae.getSource()==b2){
                
                new Transactions().setVisible(true);
                setVisible(false);
                
            }else if(ae.getSource()==b3){
                
                System.exit(0);
                
            }
        }catch(Exception e){
//                e.printStackTrace();
//                System.out.println("error: "+e);
        	JOptionPane.showMessageDialog(null, "Empty fields!");
        }
            
    }
    
    public static void main(String[] args){
        new Deposit().setVisible(true);
    }

    
}
