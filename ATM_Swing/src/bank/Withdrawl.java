/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Withdrawl extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField t1, t2;
	JButton b1, b2, b3;
	JLabel l1, l2, l3;
	Connection con;
	PreparedStatement pd;
	ResultSet rs;

	Withdrawl() {
		setFont(new Font("System", Font.BOLD, 22));
		Font f = getFont();
		FontMetrics fm = getFontMetrics(f);
		int x = fm.stringWidth("WITHDRAWL");
		int y = fm.stringWidth(" ");
		int z = getWidth() - (4 * x);
		int w = z / y;
		String pad = "";
		pad = String.format("%" + w + "s", pad);
		setTitle(pad + "WITHDRAWL PAGE");

		l1 = new JLabel("ENTER AMOUNT YOU WANT");
		l1.setFont(new Font("System", Font.BOLD, 35));

		l2 = new JLabel("TO WITHDRAW");
		l2.setFont(new Font("System", Font.BOLD, 35));

		l3 = new JLabel("Enter Pin");
		l3.setFont(new Font("System", Font.BOLD, 14));

		t1 = new JTextField();
		t1.setFont(new Font("Raleway", Font.BOLD, 22));

		t2 = new JTextField();
		t2.setFont(new Font("Raleway", Font.BOLD, 14));

		b1 = new JButton("WITHDRAW");
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

		l3.setBounds(620, 10, 80, 30);
		getContentPane().add(l3);

		t2.setBounds(700, 10, 40, 30);
		getContentPane().add(t2);

		l1.setBounds(150, 150, 800, 60);
		getContentPane().add(l1);

		l2.setBounds(290, 210, 800, 60);
		getContentPane().add(l2);

		t1.setBounds(250, 300, 300, 50);
		getContentPane().add(t1);

		b1.setBounds(260, 380, 140, 50);
		getContentPane().add(b1);

		b2.setBounds(415, 380, 125, 50);
		getContentPane().add(b2);

		b3.setBounds(300, 550, 200, 50);
		getContentPane().add(b3);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		getContentPane().setBackground(new Color(53, 234, 21));

		setSize(800, 800);
		setLocation(400, 20);
		setVisible(true);
	}

	public synchronized void actionPerformed(ActionEvent ae) {

		try {

			String a = t1.getText();
			String b = t2.getText();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");
			pd = con.prepareStatement("Select * from login where pin=?");
        	pd.setLong(1, Long.parseLong(b));
        	rs = pd.executeQuery();
        	
			if (ae.getSource() == b1) {
				if (t1.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");

				} else if(!rs.next())
	        	{
	        		JOptionPane.showMessageDialog(null, "Wrong PIN!");
	        		new Transactions().setVisible(true);
	                setVisible(false);
	        	} else{

					conn c1 = new conn();
					
					ResultSet rs = c1.s.executeQuery(" select * from bank where pin = '" + b + "' ");

					long balance = 0;
					while(rs.next())
//						String pin = rs.getString("pin");
						balance = rs.getLong("balance");
						JFrame f = new JFrame();

						long d = Long.parseLong(a);
						/*
						 * Comment out old code as there was no multithreading
						 **/
//						if (d > balance) {
//							JOptionPane.showMessageDialog(f, "You don't have enough cash.", "Alert",
//									JOptionPane.WARNING_MESSAGE);
//							setVisible(false);
//						} else {
//							balance -= d;
//							String q1 = "insert into bank values('" + pin + "','" + 0 + "','" + d + "','" + balance
//									+ "')";
//
//							c1.s.executeUpdate(q1);
//						}
						if(balance > d) {
							// Implemented multithreading in case of withdrawl
							try {
								Thread.sleep(10);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("error: " + e);
							}
							balance -= d;
							String q1 = "insert into bank values('" + b + "','" + 0 + "','" + d + "','" + balance
									+ "')";

							c1.s.executeUpdate(q1);
							JOptionPane.showMessageDialog(null, "Rs. " + a + " Withdrawn Successfully");

						}
						else {
							JOptionPane.showMessageDialog(f, "You don't have enough cash.", "Alert",
									JOptionPane.WARNING_MESSAGE);
							setVisible(false);
						}
					}
				
			
					new Transactions().setVisible(true);
					setVisible(false);

				}

			 else if (ae.getSource() == b2) {

				new Transactions().setVisible(true);
				setVisible(false);

			} else if (ae.getSource() == b3) {

				System.exit(0);

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error: " + e);
		}

	}

	public static void main(String[] args) {
		new Withdrawl().setVisible(true);
	}

}
