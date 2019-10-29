package onlinequiz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class addQuestion extends JFrame implements ActionListener  
{
	JTextField table;
	JTextField ques;
	JTextField a;
	JTextField b;
	JTextField c;
	JTextField d;
	JTextField ans;
	JFrame f;
	JButton b1;
	JButton back;
	JLabel l;
	JLabel l1;
	JLabel l2;;
	JLabel l3;
	JLabel l4;
	JLabel l5;
	JLabel lans;
	addQuestion()
	{

		f=new JFrame("Add question");
		l = new JLabel("Topic:");
		l1=new JLabel("Question:");
		l2=new JLabel("A:");
		l3=new JLabel("B:");
		l4=new JLabel("C:");
		l5=new JLabel("D:");
		lans=new JLabel("Ans:");
		table = new JTextField();
		ques=new JTextField();
		a=new JTextField();
		b=new JTextField();
		c=new JTextField();
		d=new JTextField();
		ans=new JTextField();
		b1=new JButton("Add");
		back = new JButton("Back");
		b1.addActionListener(this);
		back.addActionListener(this);
		l.setForeground(Color.white);
		l1.setForeground (Color.white);
		l2.setForeground (Color.white);
		l3.setForeground (Color.white);
		l4.setForeground (Color.white);
		l5.setForeground (Color.white);
		lans.setForeground(Color.white);        
		l.setBounds(0,0,480,20);
		table.setBounds(0,30,480,20);
		l1.setBounds(0,60,480,20);
		ques.setBounds(0,80,480,20);
		l2.setBounds(0,100,480,20);
		a.setBounds(0,120,480,20);
		l3.setBounds(0,140,480,20);
		b.setBounds(0,160,480,20);
		l4.setBounds(0,180,480,20);
		c.setBounds(0,200,480,20);
		l5.setBounds(0,220,480,20);
		d.setBounds(0,240,480,20);
		lans.setBounds(0,260,480,20);
		ans.setBounds(0,280,480,20);

		b1.setBounds(200,330,100,50);
		back.setBounds(200,400,100,50);
		f.getContentPane().setBackground(Color.gray);
		f.add(l);
		f.add(table);
		f.add(l1);
		f.add(ques);
		f.add(l2);
		f.add(a);
		f.add(l3);
		f.add(b);
		f.add(l4);
		f.add(c);
		f.add(l5);
		f.add(d);
		f.add(lans);
		f.add(ans);
		f.add(b1);
		f.add(back);
		f.setSize(520,520);
		f.setLayout(null);
		f.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			String tablename = table.getText();
			String question = ques.getText();
			String option1 = a.getText();
			String option2 = b.getText();
			String option3 = c.getText();
			String option4 = d.getText();
			String answer = ans.getText();

			try
			{
				if(question.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty() || answer.isEmpty())
					throw new Exception("Incorrect value ");
				if(e.getSource() == b1)
				{
					Connection conn = null;
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/demo","root","");

					PreparedStatement st = conn.prepareStatement("insert into " + tablename +"(ques,option1,option2,option3,option4,answer) values(?,?,?,?,?,?)" );
					st.setString(1, question);
					st.setString(2, option1);
					st.setString(3, option2);
					st.setString(4, option3);
					st.setString(5, option4);
					st.setString(6, answer);

					int rs = st.executeUpdate();
					conn.close();
					JOptionPane.showMessageDialog(this, "Question added");
					f.dispose();

				}
			}
			catch(Exception exe)
			{
				System.out.println("Error - " + exe);
				exe.printStackTrace();
			}
		}
	}

}


