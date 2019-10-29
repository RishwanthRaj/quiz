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

public class modifyQuestion extends JFrame implements ActionListener  
{
	JTextField no;
	JTextField ques;
	JTextField a;
	JTextField b;
	JTextField c;
	JTextField d;
	JTextField ans;
	JTextField t;
	JFrame f;
	JButton b1;
	JLabel l;
	JLabel l1;
	JLabel l2;;
	JLabel l3;
	JLabel l4;
	JLabel l5;
	JLabel lans;
	JLabel l6;
	JButton back;
	modifyQuestion()
	{
		l6 = new JLabel("Table name:");
		l = new JLabel("Question number");
		l1=new JLabel("Question:");
		l2=new JLabel("A:");
		l3=new JLabel("B:");
		l4=new JLabel("C:");
		l5=new JLabel("D:");
		lans=new JLabel("Ans:");
		f=new JFrame("Add question");

		t = new JTextField();
		no = new JTextField();
		ques=new JTextField();
		a=new JTextField();
		b=new JTextField();
		c=new JTextField();
		d=new JTextField();
		ans=new JTextField();
		b1=new JButton("Modify");
		back = new JButton("Back");
		b1.addActionListener(this);
		back.addActionListener(this);
		l6.setForeground(Color.white);
		l.setForeground(Color.white);;
		l1.setForeground (Color.white);
		l2.setForeground (Color.white);
		l3.setForeground (Color.white);
		l4.setForeground (Color.white);
		l5.setForeground (Color.white);
		lans.setForeground(Color.white);


		l6.setBounds(0,0,480,20);
		t.setBounds(0,30,480,20);
		l.setBounds(0,60,480,20);
		no.setBounds(0,80,480,20);
		l1.setBounds(0,100,480,20);
		ques.setBounds(0,120,480,20);
		l2.setBounds(0,140,480,20);
		a.setBounds(0,160,480,20);
		l3.setBounds(0,180,480,20);
		b.setBounds(0,200,480,20);
		l4.setBounds(0,220,480,20);
		c.setBounds(0,240,480,20);
		l5.setBounds(0,260,480,20);
		d.setBounds(0,280,480,20);
		lans.setBounds(0,300,480,20);
		ans.setBounds(0,320,480,20);
		b1.setBounds(200,360,100,40);
		back.setBounds(200,410,100,40);
		f.getContentPane().setBackground(Color.BLACK);
		f.add(l6);
		f.add(t);
		f.add(l);
		f.add(no);
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
			String table = t.getText();
			String question = ques.getText();
			String option1 = a.getText();
			String option2 = b.getText();
			String option3 = c.getText();
			String option4 = d.getText();
			int number = Character.getNumericValue( no.getText().charAt(0));
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

					PreparedStatement st = conn.prepareStatement("update "+table+" set ques =?, option1 =?, option2 =?, option3 = ?, option4 =?,answer =? where no =?" );
					st.setString(1, question);
					st.setString(2, option1);
					st.setString(3, option2);
					st.setString(4, option3);
					st.setString(5, option4);
					st.setString(6, answer);
					st.setInt(7, number);


					int rs = st.executeUpdate();
					JOptionPane.showMessageDialog(this, "Question modified");
					f.dispose();
					conn.close();
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



