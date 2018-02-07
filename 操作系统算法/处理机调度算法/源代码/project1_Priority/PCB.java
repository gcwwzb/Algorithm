package project1_Priority;
//程名，进程PID，进程优先数，进程状态、PCB指针
public class PCB {

	public String name ;
	public int PID;
	public int value;
	public int tag=2;
	public int time;
	int used=0;
	int wait=0;
	PCB next;
	
	public PCB(String name,int PID,int value,int time) {
		this.name = name;
		this.PID = PID;
		this.value = value;
		this.time = time;
		next = null;
		
	}
	
	
	
	
	
}
