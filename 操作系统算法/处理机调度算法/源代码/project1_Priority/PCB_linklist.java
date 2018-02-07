package project1_Priority;

public class PCB_linklist {

	PCB head;
	PCB rear;
	int num;
	
	public PCB_linklist() {
		PCB head = null;
		PCB rear = null;
		num = 0;
	}

	//找到指定PID的PCB，若队列为空，返回null
	public PCB searchPCB(int id) {
		if(head==null)return null;
		PCB p = head;
		while(p.next.PID!=id&&p.next!=null)
			p = p.next;
		return p.next;
		
	}
	//找到PCB前一项，若队列为空，返回null
	public PCB prePCB(PCB pcb) {
		if(head==null)return null;
		PCB p = head;
		while(p.next!=pcb)
			p = p.next;
		return p;
		
	}
	//添加PCB
	public void addPCB(PCB pcb) {
		
		
		if(head==null)
		{
			head = pcb;
			rear = pcb;
			num++;
		}
			
		
		else{
			rear.next = pcb;
			rear = pcb;
			num++;
		}
		
		
	}
	//删除PCB，队列为空或未找到则返回false
	public boolean deletePCB(PCB pcb) {
		
		if(pcb==head&&pcb==rear)
		{
			head=rear=null;
			num--;
			pcb.next = null;
			return true;
		}
			
		else if(pcb==head)
		{
			head=head.next;
			num--;
			pcb.next = null;
			return true;
		}
		else{
			PCB pre = prePCB(pcb);
			if(pre==null)return false;
			
			pre.next = pcb.next;
			num--;
			pcb.next = null;
			return true;
		}

			
		
	}
	
	
}
