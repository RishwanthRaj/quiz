package onlinequiz;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


class adminPage extends JFrame implements ActionListener {  
	JFrame f;   
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton back;
	adminPage() 
	{ 
		f = new JFrame("Admin Login"); 
		b1 = new JButton("Add");
		b2 = new JButton("Modify");
		b3=new JButton("View");
		b4=new JButton("Exit");
		back = new JButton("Back");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		back.addActionListener(this);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(back);
		f.add(b4);
		f.getContentPane().setBackground(Color.BLACK);
		f.setSize(480,300);
		b1.setBounds(150,40,150,40);
		b2.setBounds(150,90,150,40);
		b3.setBounds(150,140,150,40);
		back.setBounds(70,200,100,50);
		b4.setBounds(280,200,100,50);
		b1.setBackground(Color.white);
		b2.setBackground(Color.white);
		b3.setBackground(Color.white);
		f.setLayout(null);
		f.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	public void actionPerformed(ActionEvent e1)  
	{
		if(e1.getSource() == back)
		{
			f.dispose();
			new adminLogin();
		}
		if (e1.getSource() == b1) 
		{
			f.dispose();
			new addQuestion();

		}
		if (e1.getSource() == b2) 
		{

			f.dispose();
			new modifyQuestion();
		}
		if(e1.getSource() == b3)
		{
			f.dispose();
			new viewQuestion();
		}
		if (e1.getSource() == b4) 
		{
			f.dispose();

		}

	}

}



