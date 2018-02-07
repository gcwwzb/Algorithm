package project1_Time;

public class Running {

	
	
	
	public static PCB TimePiece(PCB_linklist runninglist,PCB_linklist readylist,PCB_linklist waitinglist) {

		PCB running  = runninglist.head;
		
			while(waitinglist.num>0&&readylist.num<10)
			{//判断是否有新进程
				PCB pcb = waitinglist.head;
				
				waitinglist.deletePCB(pcb);
				readylist.addPCB(pcb);
				pcb.wait=0;
				
			}

			
			
			if(running==null)
			{	//初始化			
				System.out.println("初始化");
			PCB newrunning = readylist.head;
			if(newrunning==null) return null;
			readylist.deletePCB(newrunning);			
			newrunning.tag = 1;
			newrunning.wait=0;
			runninglist.addPCB(newrunning );
			
			newrunning.value--;
			newrunning.used++;
			
			PCB p = readylist.head; 
			while(p!=null)
			{
				p.wait++;
				p=p.next;
			}
			
			
			
			return newrunning;
			}
			
			
			else if(running.time==running.used)
			{//执行完毕
				System.out.println("执行完毕");
				runninglist.deletePCB(running);
				PCB newrunning =readylist.head;		
				if(newrunning==null) return null;
				readylist.deletePCB(newrunning);
				

				newrunning.tag = 1;		
				newrunning.wait=0;
				runninglist.addPCB(newrunning);
				
				
				newrunning.value--;
				newrunning.used++;
				
				PCB p = readylist.head; 
				while(p!=null)
				{
					p.wait++;
					p=p.next;
				}
				
				
				
				return newrunning;
			}
			
			else if(running.value==0)
			{//优先级变小
				System.out.println("时间片用光");
				running.tag = 2;
				running.wait=0;
				running.value=10;
				runninglist.deletePCB(running);
				
				if(readylist.num<10)
					readylist.addPCB(running);
				else waitinglist.addPCB(running);
				

				PCB newrunning = readylist.head;

				readylist.deletePCB(newrunning);
				newrunning.tag = 1;	
				newrunning.wait=0;
				runninglist.addPCB(newrunning);
				
				newrunning.value--;
				newrunning.used++;
				
				PCB p = readylist.head; 
				while(p!=null)
				{
					p.wait++;
					p=p.next;
				}
				
				
				
				return newrunning;

			}
			
			else
			{
				System.out.println("继续");
			running.value--;
			running.used++;
			
			PCB p = readylist.head; 
			while(p!=null)
			{
				p.wait++;
				p=p.next;
			}
			
			
			
			return running;
			}
			
			
	}
	

	
	
}
