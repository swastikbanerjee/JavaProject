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
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class Transactions extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    long bal;
    
    Transactions(){
    	getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
    	setBackground(new Color(90, 249, 68));
        
        setFont(new Font("System", Font.BOLD, 22));
        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int x = fm.stringWidth("TRANSACTION");
        int y = fm.stringWidth(" ");
        int z = getWidth() - (3*x);
        int w = z/y;
        String pad ="";
        //for (int i=0; i!=w; i++) pad +=" ";   
        pad = String.format("%"+w+"s", pad);
        setTitle(pad+"TRANSACTION");
    
        l1 = new JLabel("Please Select Your Transaction");
        l1.setFont(new Font("System", Font.BOLD, 38));
    
       
        b1 = new JButton("DEPOSIT");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
    
        b2 = new JButton("CASH WITHDRAWL");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("FAST CASH");
        b3.setFont(new Font("System", Font.BOLD, 18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        b4 = new JButton("MINI STATEMENT");
        b4.setFont(new Font("System", Font.BOLD, 18));
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        
        b5 = new JButton("PIN CHANGE");
        b5.setFont(new Font("System", Font.BOLD, 18));
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        
        b6 = new JButton("BALANCE ENQUIRY");
        b6.setFont(new Font("System", Font.BOLD, 18));
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.WHITE);
        
        b7 = new JButton("EXIT");
        b7.setFont(new Font("System", Font.BOLD, 18));
        b7.setBackground(Color.BLACK);
        b7.setForeground(Color.WHITE);
        
        
        getContentPane().setLayout(null);
        
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
        
        setSize(850,800);
        setLocation(400,20);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==b1){ 
            
            new Deposit().setVisible(true);
            setVisible(false);
            
        }
        else if(ae.getSource()==b2){ 
            
            new Withdrawl().setVisible(true);
            setVisible(false);
            
        }
        else if(ae.getSource()==b3){ 
            
            new FastCash().setVisible(true);
            setVisible(false);
            
        }else if(ae.getSource()==b4){ 
            
            new MiniStatement().setVisible(true);
//            setVisible(false);
            
        }else if(ae.getSource()==b5){ 
            
            new Pin().setVisible(true);
            setVisible(false);
            
        }else if(ae.getSource()==b6){ 
            
            long pinn = Long.parseLong(JOptionPane.showInputDialog("Enter PIN")); 
            conn c1 = new conn();
                    
            try {
            	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
            	
            	ps = con.prepareStatement("SELECT BALANCE FROM BANK WHERE PIN = ?");
            	ps.setLong(1,pinn);
                rs = ps.executeQuery();
                
                while(rs.next())
                
                bal = rs.getLong("balance");
                
                JOptionPane.showMessageDialog(null,"Your Account Balance is "+bal);
                
                
               
                  
                
            } catch (Exception e) {
             
                e.printStackTrace();
            
            }
            
  
                    
            
        }else if(ae.getSource()==b7){ 
            
            System.exit(0);
            
        }
        
            
        
        
    }
    
    public static void main(String[] args){
        new Transactions().setVisible(true);
    }
}
    
