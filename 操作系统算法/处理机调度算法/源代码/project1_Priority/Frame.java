package project1_Priority;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;


public class Frame extends JFrame implements ActionListener{
	int flag = 1;
	
	PCB_linklist readylist ;
	PCB_linklist waitinglist ;
	PCB_linklist runninglist ;
	
	static JPanel jpl = new JPanel();
	
	static JLabel tittle = new JLabel("处 理 机 调 度");
	static JLabel jlb_running0 = new JLabel("Running:");
	static JLabel jlb_running = new JLabel();
	static JLabel jlb_ready0 = new JLabel("Ready:");
	static JLabel[] jlb_ready = new JLabel[10];
	static JLabel[] jlb_reversing = new JLabel[10];
	static Font jlb = new Font("宋体", Font.BOLD, 30);
	static Font jlb2 = new Font("宋体", Font.BOLD, 20);
	
	static JLabel lblRunningPcb = new JLabel("Running PCB");
	static JLabel lblPid = new JLabel("PID：");
	static JLabel lblNaMe = new JLabel("Name:");
	static JLabel lblPriority = new JLabel("Priority:");
	static JLabel lblTimeneeded = new JLabel("Time_Total:");
	static JLabel label = new JLabel("Time_Needed:");
	static JLabel lblTimeused = new JLabel("Time_Used:");
	static JLabel pid = new JLabel();
	static  JLabel name = new JLabel();
	static JLabel priority = new JLabel();
	static JLabel total = new JLabel();
	static JLabel needed = new JLabel();
	static JLabel used = new JLabel();
	static JLabel[] lblPidgroup = new JLabel[10];			 
	static JLabel[] lblPrigroup  = new JLabel[10];			  
	static JLabel[] lblTimegroup = new JLabel[10];
	static JLabel[] pidgroup= new JLabel[10];
	static JLabel[] prigroup= new JLabel[10];
	static JLabel[] timegroup= new JLabel[10];
	static JButton button_suspend = new JButton("Suspend");
	static JButton button_new = new JButton("New");	
	static JButton button_start = new JButton("Start");
	static JLabel lblReadyPcb = new JLabel("Ready PCB");
	  
	 JLabel lblRe = new JLabel("Reversing");
	 
	public Frame(PCB_linklist runninglist,PCB_linklist readylist,PCB_linklist waitinglist) {
		this.runninglist = runninglist;
		this.readylist = readylist;
		this.waitinglist = waitinglist;
		
		this.setTitle("处 理 机 调 度");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 900);
		
		getContentPane().add(jpl);
		jpl.setLayout(null);
		jpl.add(tittle);
		tittle.setFont(jlb);
		tittle.setLocation(350, 10);
		tittle.setSize(300, 50);
		
		jpl.add(jlb_running0);
		jlb_running0.setLocation(10, 70);
		jlb_running0.setSize(107, 50);
		jlb_running0.setFont(jlb2);
		
		jlb_running.setSize(30, 30);
		jlb_running.setOpaque(true);
		jlb_running.setBackground(Color.red);
		jpl.add(jlb_running);
		jlb_running.setLocation(228,80);
		
		jpl.add(jlb_ready0);
		jlb_ready0.setLocation(10, 120);
		jlb_ready0.setSize(95, 50);
		jlb_ready0.setFont(jlb2);
		
		
		button_start.setBounds(52, 334, 258, 65);
		jpl.add(button_start);
		button_start.addActionListener(this);
		
		
		button_new.setBounds(52, 222, 258, 68);
		jpl.add(button_new);
		button_new.addActionListener(this);
		
		
		lblRe.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRe.setBounds(711, 37, 107, 50);
		jpl.add(lblRe);
		lblRunningPcb.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRunningPcb.setBounds(621, 138, 150, 50);
		
		jpl.add(lblRunningPcb);
		lblPid.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPid.setBounds(566, 204, 53, 30);
		
		jpl.add(lblPid);
		lblNaMe.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNaMe.setBounds(566, 239, 53, 30);
		
		jpl.add(lblNaMe);
		lblPriority.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPriority.setBounds(566, 279, 68, 30);
		
		jpl.add(lblPriority);
		lblTimeneeded.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTimeneeded.setBounds(743, 200, 117, 30);
		
		jpl.add(lblTimeneeded);
		label.setFont(new Font("Dialog", Font.BOLD, 15));
		label.setBounds(743, 239, 117, 30);
		
		jpl.add(label);
		lblTimeused.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTimeused.setBounds(743, 279, 117, 30);
		
		jpl.add(lblTimeused);
		pid.setFont(new Font("Dialog", Font.BOLD, 15));
		pid.setBounds(651, 204, 53, 30);
		
		jpl.add(pid);
		name.setFont(new Font("Dialog", Font.BOLD, 15));
		name.setBounds(651, 239, 53, 30);
		
		jpl.add(name);
		priority.setFont(new Font("Dialog", Font.BOLD, 15));
		priority.setBounds(651, 279, 53, 30);
		
		jpl.add(priority);
		total.setFont(new Font("Dialog", Font.BOLD, 15));
		total.setBounds(872, 204, 53, 30);
		
		jpl.add(total);
		needed.setFont(new Font("Dialog", Font.BOLD, 15));
		needed.setBounds(872, 239, 53, 30);
		
		jpl.add(needed);
		used.setFont(new Font("Dialog", Font.BOLD, 15));
		used.setBounds(872, 279, 53, 30);
		
		jpl.add(used);
		button_suspend.setBounds(52, 441, 258, 65);
		jpl.add(button_suspend);
		button_suspend.addActionListener(this);
		
		lblReadyPcb.setFont(new Font("Dialog", Font.BOLD, 20));
		lblReadyPcb.setBounds(638, 357, 150, 50);
		
		jpl.add(lblReadyPcb);
		
		
		for(int i=0;i<10;i++)
		{
			lblPidgroup[i] = new JLabel("PID "+(i+1)+"： ");
			jpl.add(lblPidgroup[i]);
			lblPidgroup[i].setFont(new Font("Dialog", Font.BOLD, 15));
			lblPidgroup[i].setBounds(513, 395+20*i, 75, 30);
			
			lblPrigroup[i] = new JLabel("PRI "+(i+1)+"： ");
			jpl.add(lblPrigroup[i]);
			lblPrigroup[i].setFont(new Font("Dialog", Font.BOLD, 15));
			lblPrigroup[i].setBounds(654, 395+20*i, 75, 30);
			
			lblTimegroup[i] = new JLabel("Wait "+(i+1)+"： ");
			jpl.add(lblTimegroup[i]);
			lblTimegroup[i].setFont(new Font("Dialog", Font.BOLD, 15));
			lblTimegroup[i].setBounds(778, 395+20*i, 110, 30);
			
			pidgroup[i] = new JLabel("");
			jpl.add(pidgroup[i]);
			pidgroup[i].setFont(new Font("Dialog", Font.BOLD, 15));
			pidgroup[i].setBounds(580, 395+20*i, 68, 30);
			
			prigroup[i] = new JLabel("");
			jpl.add(prigroup[i]);
			prigroup[i].setFont(new Font("Dialog", Font.BOLD, 15));
			prigroup[i].setBounds(717, 395+20*i, 68, 30);
			
			timegroup[i] = new JLabel("");
			jpl.add(timegroup[i]);
			timegroup[i].setFont(new Font("Dialog", Font.BOLD, 15));
			timegroup[i].setBounds(859, 395+20*i, 68, 30);
		}
		
		
		
		
	
		
		for(int i=0;i<10;i++)
		{
			jlb_reversing[i] = new JLabel();
			jlb_reversing[i].setSize(30, 30);
			jlb_reversing[i].setOpaque(true);
			Color color = new Color(211,211,211);
			jlb_reversing[i].setBackground(color);
			jpl.add(jlb_reversing[i]);
			jlb_reversing[i].setLocation(570+40*i,80);
		}
		
		
		
		for(int i=0;i<10;i++)
		{
			jlb_ready[i] = new JLabel();
			jlb_ready[i].setSize(30, 30);
			jlb_ready[i].setOpaque(true);
			Color color = new Color(135,206,250);
			jlb_ready[i].setBackground(color);
			jpl.add(jlb_ready[i]);
			jlb_ready[i].setLocation(90+40*i,130);
		}
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button_suspend)
		{
			flag=0;
		}
			
		if(e.getSource()==button_new)
		{
			flag=0;
			
			//分配合适的id号
			int id=0;
			PCB p1 = readylist.head;
			while(p1!=null)
			{
				if(p1.PID>id)
					id = p1.PID;
				
				p1=p1.next;
			}
			
			PCB p2 = waitinglist.head;
			while(p2!=null)
			{
				if(p2.PID>id)
					id = p2.PID;
				
				p2=p2.next;
			}
			
			if(readylist.head!=null&&readylist.head.PID>id)
				id=readylist.head.PID;

				inputDialog inputDialog = new inputDialog(id+1, runninglist,readylist,waitinglist);

	
			
			

		}
		
		
		
		
		
		
		if(e.getSource()==button_start)
		{
			
			 
		flag=1;

			
			new Thread(){
				
				public void run() {
					while (flag==1) {
						try{Thread.sleep(1000);}catch (Exception ex) {}
						PCB runningpcb = Running.TimePiece(runninglist, readylist, waitinglist);
						if(runningpcb==null)return;
						Frame.running(runningpcb, readylist, waitinglist);
						
					}
				}
			}.start();
			
			
		}
		
	}
	
	public static void clear() {
		pid.setText("");
		name.setText("");
		priority.setText("");
		total.setText("");
		used.setText("");
		needed.setText("");
		jlb_running.setText("");
		for(int i=0;i<10;i++)		
		{
			pidgroup[i].setText("");
			prigroup[i].setText("");
			timegroup[i].setText("");
			jlb_ready[i].setText("");
			jlb_reversing[i].setText("");
		}
	}
	
	
	public static void running(PCB pcb,PCB_linklist readylist,PCB_linklist waitinglist) {
		
		
		
		int i=0;
		Frame.clear();
		
		if(pcb!=null)
		{
		pid.setText(String.valueOf(pcb.PID));
		name.setText(String.valueOf(pcb.name));
		priority.setText(String.valueOf(pcb.value));
		total.setText(String.valueOf(pcb.time));
		used.setText(String.valueOf(pcb.used));
		needed.setText(String.valueOf(pcb.time-pcb.used));
		
		jlb_running.setText("   "+String.valueOf(pcb.PID));
		}
		
		if(readylist.num!=0)
		{
			
		PCB p1 = readylist.head;
		
		while(p1!=null)
		{
			
			pidgroup[i].setText(String.valueOf(p1.PID));
			prigroup[i].setText(String.valueOf(p1.value));
			timegroup[i].setText(String.valueOf(p1.wait));
			jlb_ready[i].setText("   "+String.valueOf(p1.PID));
			i++;
			p1=p1.next;
			
		}
		}
		if(waitinglist.num!=0)
		{
		PCB p2 = waitinglist.head;
		 int j=0;
		while(p2!=null)
		{
			jlb_reversing[j].setText("   "+String.valueOf(p2.PID));
			j++;
			p2=p2.next;
		}
	
		}
		
		
		
		
	}
	
	
	
	
}
