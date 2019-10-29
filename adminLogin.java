package onlinequiz;

import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class adminLogin extends JFrame implements ActionListener {  
	JTextField t; 
	JPasswordField pwd;  
	JFrame f;  
	JLabel l1;
	JLabel l2; 
	JButton b; 
	JButton back;
	Connection conn = null;
	adminLogin() 
	{ 
		f = new JFrame("Admin Login"); 
		b = new JButton("submit");
		back = new JButton("Back");
		l1=new JLabel("User Name:");
		l2=new JLabel("Password :");
		b.addActionListener(this); 
		back.addActionListener(this);
		t = new JTextField("", 6); 
		pwd = new JPasswordField(10); 
		Font fo = new Font("Serif", Font.ITALIC, 20); 
		t.setFont(fo); 
		JPanel p = new JPanel(); 
		p.add(l1);
		p.add(t); 
		p.add(l2);
		p.add(pwd); 
		p.add(b); 
		p.add(back);
		f.add(p); 
		f.setSize(480, 300); 
		f.setVisible(true); 
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/demo","root","");

		}
		catch(Exception exe)
		{
			System.out.println("Error -" + exe);
			exe.printStackTrace();
		}
	}  
	public void actionPerformed(ActionEvent e) 
	{ 
		if(e.getSource() == back)
		{
			f.dispose();
			new firstPage();
		}
		else
		{
			String s = e.getActionCommand(); 
			String s1=t.getText();  
			char s2[]=pwd.getPassword();  
			String s3 = new String(s2);

			int flag = 0;
			try
			{
				PreparedStatement st = conn.prepareStatement("SELECT * FROM adminlogin");
				ResultSet rs = st.executeQuery();
				flag = 0;
				while(rs.next()){
					String user = rs.getString("username");
					String pwd = rs.getString("password");

					if(user.equals(s1) && pwd.equals(s3)) {
						flag = 1;
						break;
					}

				}
				rs.close();

			}
			catch(Exception exe)
			{
				System.out.print(" Error:"+exe);
				exe.printStackTrace();
			}

			if (s.equals("submit") && flag==1) {  

				f.dispose(); 
				try
				{
					conn.close();
				}
				catch(Exception exe)
				{
					System.out.println("Error -"+exe);
					exe.printStackTrace();
				}
				new adminPage();
			} 
			else
			{
				JOptionPane.showMessageDialog(this, "wrong password");
				f.dispose();
				new adminLogin();
			}
		}
	}

}

