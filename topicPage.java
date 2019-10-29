package onlinequiz;

import java.awt.Color;

import java.awt.event.*;
import javax.swing.*;

public class topicPage extends JFrame implements ActionListener{
	JFrame f;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton back;
	JLabel j;
	topicPage()
	{
		f = new JFrame("Topics");
		f.getContentPane().setBackground(Color.gray);
		b1 =new JButton("Height and distance");
		b1.setBackground(Color.white);
		b2 =new JButton("Average");
		b2.setBackground(Color.white);
		b3 =new JButton("Odd man out and series");
		b3.setBackground(Color.white);
		b4 =new JButton("General Science");
		b4.setBackground(Color.white);
		back = new JButton("Back");
		b1.setBounds(80, 50, 120, 80); 
		b2.setBounds(300, 50, 120, 80);
		b3.setBounds(80, 200,120,80);
		b4.setBounds(300, 200,120,80);
		back.setBounds(200,300 ,100 ,50);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		back.addActionListener(this);
		j = new JLabel("Choose a topic");
		JPanel p = new JPanel(); 
		p.add(j);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(back);
		f.add(p);
		f.setSize(520, 400);
		
		f.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == back)
			new studentLogin();
		if(e.getSource() == b1)
			new onlineTest("height");
		if(e.getSource() == b2)
			new onlineTest("average");
		if(e.getSource() == b3)
			new onlineTest("oddmanout");
		if(e.getSource() == b4)
			new onlineTest("generalscience");
	    f.dispose();
	}

}
