/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
//import java.util.*;


public class FastCash extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l1,l2;
    JButton b1,b2,b3,b4,b5,b6,b7,b8;
    JTextField t1;
    Connection con;
    ResultSet rs;
    PreparedStatement pd;
    long bal;
    
    FastCash(){
        
        setFont(new Font("System", Font.BOLD, 22));
        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int x = fm.stringWidth("FAST CASH");
        int y = fm.stringWidth(" ");
        int z = getWidth() - (4*x);
        int w = z/y;
        String pad ="";
        //for (int i=0; i!=w; i++) pad +=" ";   
        pad = String.format("%"+w+"s", pad);
        setTitle(pad+"FAST CASH");
    
        
        
        
        l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setFont(new Font("System", Font.BOLD, 38));
        
        l2 = new JLabel("Enter PIN");
        l2.setFont(new Font("System", Font.BOLD, 13));
        
        t1 = new JTextField();
        t1.setFont(new Font("System", Font.BOLD, 13));
    
       
        b1 = new JButton("Rs 100");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
    
        b2 = new JButton("Rs 500");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("Rs 1000");
        b3.setFont(new Font("System", Font.BOLD, 18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        b4 = new JButton("Rs 2000");
        b4.setFont(new Font("System", Font.BOLD, 18));
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        
        b5 = new JButton("Rs 5000");
        b5.setFont(new Font("System", Font.BOLD, 18));
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        
        b6 = new JButton("Rs 10000");
        b6.setFont(new Font("System", Font.BOLD, 18));
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.WHITE);
        
        b7 = new JButton("BACK");
        b7.setFont(new Font("System", Font.BOLD, 18));
        b7.setBackground(Color.BLACK);
        b7.setForeground(Color.WHITE);
        
        b7 = new JButton("EXIT");
        b7.setFont(new Font("System", Font.BOLD, 18));
        b7.setBackground(Color.BLACK);
        b7.setForeground(Color.WHITE);
        
        
        getContentPane().setLayout(null);
        
        l2.setBounds(640,10,60,40);
        getContentPane().add(l2);
        
        t1.setBounds(710,10,60,40);
        getContentPane().add(t1);
        
        l1.setBounds(100,100,700,40);
        getContentPane().add(l1);
        
        b1.setBounds(40,250,300,60);
        getContentPane().add(b1);
        
        b2.setBounds(440,250,300,60);
        getContentPane().add(b2);
        
        b3.setBounds(40,360,300,60);
        getContentPane().add(b3);
        
        b4.setBounds(440,360,300,60);
        getContentPane().add(b4);
        
        b5.setBounds(40,470,300,60);
        getContentPane().add(b5);
        
        b6.setBounds(440,470,300,60);
        getContentPane().add(b6);
        
        b7.setBounds(240,600,300,60);
        getContentPane().add(b7);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        
        
        getContentPane().setBackground(new Color(53, 234, 21));
        
        setSize(800,800);
        setLocation(400,20);
        setVisible(true);
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
       
            try{        
           
            String a = t1.getText();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
            if(ae.getSource()==b1){
                
            		
            		pd = con.prepareStatement("SELECT BALANCE FROM BANK WHERE PIN = ?");
                	pd.setLong(1,Long.parseLong(a));
                    rs = pd.executeQuery();
                    
                    while(rs.next())
                    
                    bal = rs.getLong("balance");
                    
                    if(bal>=100) {
                    	bal-=100;	
                    pd = con.prepareStatement("INSERT INTO BANK VALUES (?,?,?,?)");
                    pd.setLong(1, Long.parseLong(a));
                    pd.setLong(2, 0);
                    pd.setLong(3, 100);
                    pd.setLong(4, bal);
                    pd.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Rs. "+100+" Debited Successfully");
                    }
                    
                    else
                    JOptionPane.showMessageDialog(null, "Not enough balance!");
                    
                    new Transactions().setVisible(true);
                    setVisible(false);
                    
                    
                    
            }
                
            
            else if(ae.getSource()==b2){
                
            	pd = con.prepareStatement("SELECT BALANCE FROM BANK WHERE PIN = ?");
            	pd.setLong(1,Long.parseLong(a));
                rs = pd.executeQuery();
                
                while(rs.next())
                
                bal = rs.getLong("balance");
                
                if(bal>=500) {
                	bal-=500;	
                pd = con.prepareStatement("INSERT INTO BANK VALUES (?,?,?,?)");
                pd.setLong(1, Long.parseLong(a));
                pd.setLong(2, 0);
                pd.setLong(3, 500);
                pd.setLong(4, bal);
                pd.executeUpdate();
                JOptionPane.showMessageDialog(null, "Rs. "+500+" Debited Successfully");
                }
                
                else
                JOptionPane.showMessageDialog(null, "Not enough balance!");
                
                new Transactions().setVisible(true);
                setVisible(false);
                    
                    
                    
                }
            else if(ae.getSource()==b3){
                
            	pd = con.prepareStatement("SELECT BALANCE FROM BANK WHERE PIN = ?");
            	pd.setLong(1,Long.parseLong(a));
                rs = pd.executeQuery();
                
                while(rs.next())
                
                bal = rs.getLong("balance");
                
                if(bal>=1000) {
                	bal-=1000;	
                pd = con.prepareStatement("INSERT INTO BANK VALUES (?,?,?,?)");
                pd.setLong(1, Long.parseLong(a));
                pd.setLong(2, 0);
                pd.setLong(3, 1000);
                pd.setLong(4, bal);
                pd.executeUpdate();
                JOptionPane.showMessageDialog(null, "Rs. "+1000+" Debited Successfully");
                }
                
                else
                JOptionPane.showMessageDialog(null, "Not enough balance!");
                
                new Transactions().setVisible(true);
                setVisible(false);
                    
                    
                    
                }
            else if(ae.getSource()==b4){
                
            	pd = con.prepareStatement("SELECT BALANCE FROM BANK WHERE PIN = ?");
            	pd.setLong(1,Long.parseLong(a));
                rs = pd.executeQuery();
                
                while(rs.next())
                
                bal = rs.getLong("balance");
                
                if(bal>=2000) {
                	bal-=2000;	
                pd = con.prepareStatement("INSERT INTO BANK VALUES (?,?,?,?)");
                pd.setLong(1, Long.parseLong(a));
                pd.setLong(2, 0);
                pd.setLong(3, 2000);
                pd.setLong(4, bal);
                pd.executeUpdate();
                JOptionPane.showMessageDialog(null, "Rs. "+2000+" Debited Successfully");
                }
                
                else
                JOptionPane.showMessageDialog(null, "Not enough balance!");
                
                new Transactions().setVisible(true);
                setVisible(false);
                    
                    
                }
            else if(ae.getSource()==b5){
                
            	pd = con.prepareStatement("SELECT BALANCE FROM BANK WHERE PIN = ?");
            	pd.setLong(1,Long.parseLong(a));
                rs = pd.executeQuery();
                
                while(rs.next())
                
                bal = rs.getLong("balance");
                
                if(bal>=5000) {
                	bal-=5000;	
                pd = con.prepareStatement("INSERT INTO BANK VALUES (?,?,?,?)");
                pd.setLong(1, Long.parseLong(a));
                pd.setLong(2, 0);
                pd.setLong(3, 5000);
                pd.setLong(4, bal);
                pd.executeUpdate();
                JOptionPane.showMessageDialog(null, "Rs. "+5000+" Debited Successfully");
                }
                
                else
                JOptionPane.showMessageDialog(null, "Not enough balance!");
                
                new Transactions().setVisible(true);
                setVisible(false);
                    
                }
            else if(ae.getSource()==b6){
                
            	pd = con.prepareStatement("SELECT BALANCE FROM BANK WHERE PIN = ?");
            	pd.setLong(1,Long.parseLong(a));
                rs = pd.executeQuery();
                
                while(rs.next())
                
                bal = rs.getLong("balance");
                
                if(bal>=10000) {
                	bal-=10000;	
                pd = con.prepareStatement("INSERT INTO BANK VALUES (?,?,?,?)");
                pd.setLong(1, Long.parseLong(a));
                pd.setLong(2, 0);
                pd.setLong(3, 10000);
                pd.setLong(4, bal);
                pd.executeUpdate();
                JOptionPane.showMessageDialog(null, "Rs. "+10000+" Debited Successfully");
                }
                
                else
                JOptionPane.showMessageDialog(null, "Not enough balance!");
                
                new Transactions().setVisible(true);
                setVisible(false);
                    
                }
                
            
            else if(ae.getSource()==b7){
                
                System.exit(0);
                
            }
        }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
        }
            
    }

    
    public static void main(String[] args){
        new FastCash().setVisible(true);
    }
}
