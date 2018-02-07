package project1_Time;

import java.awt.*;
import javax.swing.*;

import project1_Time.PCB;
import project1_Time.PCB_linklist;

import java.awt.event.*;

public class inputDialog extends JFrame implements ActionListener{

	PCB_linklist runninglist;
	PCB_linklist readylist;
	PCB_linklist waitinglist;
	
	
	JPanel jpl = new JPanel();
	
	JLabel tittle = new JLabel("添 加 新 进 程");
	
	JLabel jlb_name =new JLabel( "进程名：");
	JLabel jlb_no = new JLabel("进程编号：");
	JLabel	jlb_value = new JLabel("进程优先级：");
	JLabel jlb_time = new JLabel("所需时间片：");
	JLabel jlb_others = new JLabel("其他信息：");
	
	JTextField jtf_name = new JTextField();
	JTextField jtf_no = new JTextField();
	JTextField jtf_value = new JTextField();
	JTextField jtf_time = new JTextField();
	JTextField jtf_others = new JTextField();
	
	JButton submit = new JButton("确 认");
	
	Font jlb = new Font("宋体", Font.BOLD, 20);

	public inputDialog(int no,PCB_linklist runninglist,PCB_linklist readylist,PCB_linklist waitinglist) {
		this.runninglist = runninglist;
		this.readylist = readylist;
		this.waitinglist = waitinglist;
		this.setTitle("添 加 新 进 程");
		this.setLocation(100, 100);
		this.setVisible(true);
		this.setSize(300, 400);
		this.add(jpl);
		
		jpl.setLayout(null);
		


		

		
		
		jpl.add(tittle);
		tittle.setFont(jlb);
		tittle.setForeground(Color.black);
		tittle.setSize(300, 50);
		tittle.setLocation(80, 0);
		
		jpl.add(jlb_name);
		jlb_name.setForeground(Color.black);
		jlb_name.setFont(jlb);
		jlb_name.setSize(150, 50);
		jlb_name.setLocation(10, 40);
		jpl.add(jtf_name);
		jtf_name.setSize(170,30);
		jtf_name.setLocation(120, 50);
		
		jpl.add(jlb_no);
		jlb_no.setForeground(Color.black);
		jlb_no.setFont(jlb);
		jlb_no.setSize(150, 50);
		jlb_no.setLocation(10, 80);
		jpl.add(jtf_no);
		jtf_no.setSize(170,30);
		jtf_no.setLocation(120, 90);
		jtf_no.setText(String.valueOf(no));
		jtf_no.setEditable(false);
		
		jpl.add(jlb_value);
		jlb_value.setForeground(Color.black);
		jlb_value.setFont(jlb);
		jlb_value.setSize(150, 50);
		jlb_value.setLocation(10, 120);
		jpl.add(jtf_value);
		jtf_value.setSize(170,30);
		jtf_value.setLocation(120, 130);
		jtf_value.setText("10");
		jtf_value.setEditable(false);
		
		jpl.add(jlb_time);
		jlb_time.setForeground(Color.black);
		jlb_time.setFont(jlb);
		jlb_time.setSize(150, 50);
		jlb_time.setLocation(10, 160);
		jpl.add(jtf_time);
		jtf_time.setSize(170,30);
		jtf_time.setLocation(120, 170);
		
		jpl.add(jlb_others);
		jlb_others.setForeground(Color.black);
		jlb_others.setFont(jlb);
		jlb_others.setSize(150, 50);
		jlb_others.setLocation(10, 200);
		jpl.add(jtf_others);
		jtf_others.setSize(170,30);
		jtf_others.setLocation(120, 210);
		
		jpl.add(submit);
		submit.addActionListener(this);
		submit.setSize(100,50);
		submit.setLocation(100,270);
		
	}
	
	


	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String name = jtf_name.getText();
		int no = Integer.parseInt(jtf_no.getText()); 
		int value = Integer.parseInt(jtf_value.getText());
		int time = Integer.parseInt(jtf_time.getText());
		
		PCB  pcb = new PCB(name, no, value, time);
	
			waitinglist.addPCB(pcb);

			Frame.running(runninglist.head, readylist, waitinglist);
			this.dispose();
		
	}
	
	
	
	
}
