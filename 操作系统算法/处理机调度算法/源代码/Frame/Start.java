package Frame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Start extends JFrame implements ActionListener{
	
	JPanel jpl = new JPanel();
	JButton jbt1 = new JButton("优先权调度");
	JButton jbt2 = new JButton("时间片调度");
	
	
	
	public Start() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(200, 300);
		this.setLocation(100,100);
		getContentPane().add(jpl);
		jpl.setLayout(null);
		jbt1.setLocation(42, 41);
		jpl.add(jbt1);
		jbt2.setLocation(42, 149);
		jpl.add(jbt2);
		jbt1.setSize(100,50);
		jbt2.setSize(100,50);
		
		jbt1.addActionListener(this);
		jbt2.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbt1)
			new project1_Priority.Main();
		
		if(e.getSource()==jbt2)
			new project1_Time.Main();
	}

	public static void main(String[] args) {	
		new Start();
	}
}


		
