package onlinequiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.util.ArrayList;
import java.util.Collections;


class onlineTest extends JFrame implements ActionListener  
{  
	String table;
	JLabel l;
	JRadioButton jb[] = new JRadioButton[5];
	JButton b1,b2;
	ButtonGroup bg;
	int count = 0, current = 0, x = 1, y = 1, now = 0;
	int m[] = new int[10];
	Connection conn = null;
	ResultSet rs;
	ArrayList<Integer> list;

	onlineTest(String s) 
	{
		super(s);
		table = s;
		l = new JLabel();
		add(l);
		bg = new ButtonGroup();
		for (int i = 0; i < 5; i++) 
		{
			jb[i] = new JRadioButton();
			add(jb[i]);
			bg.add(jb[i]);
		}
		b1 = new JButton("Next");
		b2 = new JButton("Result");

		b1.addActionListener(this);
		b2.addActionListener(this);

		add(b1);
		add(b2);
		b2.setEnabled(false);
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/demo","root","");
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select count(*) from "+ table);
			rs.next();

			int rowCount = rs.getInt("count(*)");

			list = new ArrayList<Integer>();
			for (int i=1; i<=rowCount; i++) {
				list.add(new Integer(i));
			}
			Collections.shuffle(list);
			set();

		}
		catch(Exception exe)
		{
			System.out.println("Error - "+exe);
			exe.printStackTrace();
		}

		l.setBounds(30, 40, 450, 20);
		jb[0].setBounds(50, 80, 100, 20);
		jb[1].setBounds(50, 110, 100, 20);
		jb[2].setBounds(50, 140, 100, 20);
		jb[3].setBounds(50, 170, 100, 20);
		b1.setBounds(200, 240, 100, 30);
		b2.setBounds(320, 240, 100, 30);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocation(250, 100);
		setVisible(true);
		setSize(600, 350);

	}

	public void actionPerformed(ActionEvent e) {

		try
		{
			if (e.getSource() == b1) {
				current++;
				if (check(rs.getString("answer")))
				{
					count = count + 1;
				}
				if (current == 9) {
					b1.setEnabled(false);
					b2.setEnabled(true);
					add(b2);
					set();
				}
				else
				{
					set();
				}
					
			}
			if (e.getActionCommand().equals("Result")) 
			{
				if (check(rs.getString("answer"))){
					count = count + 1;

				}

				rs.close();
				conn.close();
				JOptionPane.showMessageDialog(this, "Your score is " + count);
				dispose();
				System.exit(0);

			}
		}

		catch(Exception exe)
		{
			System.out.println("Error- " + exe);
			exe.printStackTrace();
		}
	}

	boolean check(String answer)
	{

		if(answer.equals("1"))
			return jb[0].isSelected();
		else if(answer.equals("2"))
			return jb[1].isSelected();
		else if(answer.equals("3"))
			return jb[2].isSelected();
		else
			return jb[3].isSelected();

	}

	void set() throws Exception{


		Collections.shuffle(list);
		PreparedStatement st = conn.prepareStatement("SELECT * FROM " + table + " where no =? " );

		st.setInt(1, list.get(0));
		list.remove(0);

		rs = st.executeQuery(); 
		rs.next();
		String question = rs.getString("ques");
		String option1 = rs.getString("option1");
		String option2 = rs.getString("option2");
		String option3 = rs.getString("option3");
		String option4 = rs.getString("option4");

		l.setText(question);
		jb[0].setText(option1);
		jb[1].setText(option2);
		jb[2].setText(option3);
		jb[3].setText(option4);
		
		jb[0].setSelected(false);
		jb[1].setSelected(false);
		jb[2].setSelected(false);
		jb[3].setSelected(false);
		
		l.setBounds(30, 40, 450, 20);
		
		
		for (int i = 0, j = 0; i <= 90; i += 30, j++) {
			jb[j].setBounds(50, 80 + i, 200, 20);
		}
		

	}


}


