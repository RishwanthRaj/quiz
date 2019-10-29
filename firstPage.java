package onlinequiz;

import java.awt.Color;

import java.awt.event.*;
import javax.swing.*;

public class firstPage extends JFrame implements ActionListener{
	JFrame frame;
	JButton button1,button2,button3;
	firstPage()
	{
		frame=new JFrame("Aptitude Quiz");
		frame.getContentPane().setBackground(Color.BLACK);
		button1 =new JButton("Student");
		button1.setBackground(Color.green);
		button2 =new JButton("Admin");
		button2.setBackground(Color.red);
		button3=new JButton("Exit");
		button1.setBounds(150, 50, 150, 80); 
		button2.setBounds(150, 140, 150, 80);
		button3.setBounds(175,250,100,50);
		button3.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this); 
		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
		frame.setSize(480, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}
	public void actionPerformed(ActionEvent e1)  
	{  
		if(e1.getSource()==button1)  
		{
			new studentLogin();
			frame.dispose();
		}
		if(e1.getSource()==button2)
		{
			new adminLogin();
			frame.dispose();
		}
		if(e1.getSource()==button3)
		{
			frame.dispose();
		}
	}    


}


