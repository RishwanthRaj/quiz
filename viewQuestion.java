package onlinequiz;

import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;


public class viewQuestion extends JFrame implements ActionListener  {
	JTable t;
	JTextField tb;
	JFrame f;
	JButton b;
	JLabel l;
	JButton back;
	DefaultTableModel model;
	String table;
	viewQuestion()
	{
		tb = new JTextField("",15);
		f = new JFrame("Question view");
		b = new JButton("View");
		l = new JLabel("Table name:");
		back = new JButton("Back");
		b.addActionListener(this);
		back.addActionListener(this);
		JPanel p = new JPanel();
		p.add(l);
		p.add(tb);
		p.add(b);
		p.add(back);
		f.add(p); 
		
		f.setSize(480, 300); 
		f.setVisible(true); 
	}


	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == back)
		{
			f.dispose();
			new adminPage();
		}
		else
		{
			try
			{
				table = tb.getText();
				Connection conn = null;
				Statement st = null;
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/demo","root","");
				st = conn.createStatement();
				String sql="SELECT * FROM " + table;
				ResultSet rs = st.executeQuery(sql);
				model = new DefaultTableModel(new String[]{"No", "Question", "optionA","Option B","Option C","Option D","Answer"}, 0);
				while(rs.next())
				{
					int no = rs.getInt("No");
					String ques = rs.getString("ques");
					String a = rs.getString("option1");
					String b = rs.getString("option2");
					String c = rs.getString("option3");
					String d = rs.getString("option4");
					String ans = rs.getString("answer");
					model.addRow(new Object[]{no,ques,a,b,c,d,ans});
				}
				conn.close();
				t = new JTable();
				t.setModel(model);
				f.dispose();
				f = new JFrame();
				f.setTitle("Questions view");

				t.setBounds(0, 0, 520, 200);
				back.setBounds(150,250,100,50);

				JScrollPane sp = new JScrollPane(t);
				f.add(t);
				sp.add(back);
				f.add(back);
				
				f.add(sp);
				
				f.setSize(520,520);
				f.setVisible(true);
			}
			catch(Exception exe)
			{
				System.out.println("Error -"+exe);
				exe.printStackTrace(); 
			}
		}
	}

}


