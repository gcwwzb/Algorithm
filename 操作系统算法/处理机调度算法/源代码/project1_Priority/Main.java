package project1_Priority;

public class Main {
	Frame frame;
	PCB_linklist runninglist = new PCB_linklist();
	PCB_linklist readylist = new PCB_linklist();
	PCB_linklist waitinglist = new PCB_linklist();
	
	public Main() {
		
		
		 PCB p1 = new PCB("img", 		1, 		20, 	5);
		 PCB p2 = new PCB("music", 2, 		63, 	28);
		 PCB p3 = new PCB("movie", 3, 		13, 	10);
		 PCB p4 = new PCB("TAC",     4, 		2, 		58);
		 PCB p5 = new PCB("SPK", 5, 	22, 	34);
		 PCB p6 = new PCB("test", 		6, 		9, 		17);
		 PCB p7 = new PCB("Atom", 	7, 		21, 	11);
		 
		 waitinglist.addPCB(p1);
		 waitinglist.addPCB(p2);
		 waitinglist.addPCB(p3);
		 waitinglist.addPCB(p4);
		 waitinglist.addPCB(p5);
		 waitinglist.addPCB(p6);
		 waitinglist.addPCB(p7);
		 
		 frame = new Frame(runninglist,readylist,waitinglist);
		Frame.running(null, readylist, waitinglist);
		
	}

	public static void main(String[] args) {
		
		new Main();
		
		
	}
	
}
