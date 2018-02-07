import java.util.Scanner;

public class Pages {
	int page_size = 1024;
	int size = 3;// 为每个进程分配3个物理块
	int[] list = new int[20];// 虚地址
	int[] pages = new int[20];// 页号
	int[] inner = { -1, -1, -1 };// 内存物理块状态
	int cursor = 0;// 游标

	public int choose() {

		int random = (int) (10000 * Math.random());
		random = (random / 10) * 10+1;// 末尾置1

		return random;

	}

	public void Init2() {
		pages[0] = 7;
		pages[1] = 0;
		pages[2] = 1;
		pages[3] = 2;
		pages[4] = 0;
		pages[5] = 3;
		pages[6] = 0;
		pages[7] = 4;
		pages[8] = 2;
		pages[9] = 3;
		pages[10] = 0;
		pages[11] = 3;
		pages[12] = 2;
		pages[13] = 1;
		pages[14] = 2;
		pages[15] = 0;
		pages[16] = 1;
		pages[17] = 7;
		pages[18] = 0;
		pages[19] = 1;

	}

	// 初始化随机调度页号
	public void Init() {

		for (int i = 0; i < 20; i++) {
			list[i] = choose();
		}

		for (int i = 0; i < 20; i++) {
			pages[i] = list[i] / page_size;
		}

	}

	// 判断内存是否已满
	public boolean isFull() {

		if (inner[0] != -1 && inner[1] != -1 && inner[2] != -1)
			return true;

		else
			return false;

	}

	public boolean isexists(int no) {
		boolean flag = false;
		for (int i = 0; i < 3; i++) {
			if (inner[i] == no) {
				flag = true;
				break;
			}

		}
		return flag;
	}

	public void FIFO() {

		// 初始化工作
		inner[0] = -1;
		inner[1] = -1;
		inner[2] = -1;
		cursor = 0;

		int j = 0;

		for (int i = 0; i < 20; i++) {
			System.out.print("当前请求访问页面" + pages[i] + ":     ");
			if (!isFull()) {
				if (isexists(pages[i])) {
					System.out.print("内存中已存在此页面");
					System.out.println();
					continue;

				}

				else
					inner[j++] = pages[i];
			}

			else {
				if (isexists(pages[i])) {
					System.out.print("内存中已存在此页面");
					System.out.println();
					continue;

				}

				// FIFO
				else {
					inner[cursor] = pages[i];
					cursor = (cursor + 1) % 3;
				}
			}

			for (int t = 0; t < 3; t++) {
				if (inner[t] != -1)
					System.out.print(inner[t] + "  ");
			}

			System.out.println();
		}

	}

	public void OPT() {

		// 初始化工作
		inner[0] = -1;
		inner[1] = -1;
		inner[2] = -1;

		int j = 0;

		for (int i = 0; i < 20; i++) {
			System.out.print("当前请求访问页面" + pages[i] + ":     ");
			if (!isFull()) {
				if (isexists(pages[i])) {
					System.out.print("内存中已存在此页面");
					System.out.println();
					continue;

				}

				else
					inner[j++] = pages[i];
			}

			else {
				if (isexists(pages[i])) {
					System.out.print("内存中已存在此页面");
					System.out.println();
					continue;

				}

				// OPT
				else {
					int count = 0;
					int[] tag = new int[2];
					tag[0] = -1;
					tag[1] = -1;
					for (int t = i + 1; t < 20; t++) {
						if (count == 2)
							break;
						if (inner[0] == pages[t] && tag[0] != 0 && tag[1] != 0) {
							tag[count++] = 0;

						} else if (inner[1] == pages[t] && tag[0] != 1 && tag[1] != 1) {
							tag[count++] = 1;

						} else if (inner[2] == pages[t] && tag[0] != 2 && tag[1] != 2) {
							tag[count++] = 2;

						}

					}

					if (tag[0] == 1 && tag[1] == 0 || tag[0] == 0 && tag[1] == 1) {
						inner[2] = pages[i];
					} else if (tag[0] == 1 && tag[1] == 2 || tag[0] == 2 && tag[1] == 1) {
						inner[0] = pages[i];
					} else if (tag[0] == 2 && tag[1] == 0 || tag[0] == 0 && tag[1] == 2) {
						inner[1] = pages[i];
					} else if (tag[0] == 0 && tag[1] == -1 || tag[0] == -1 && tag[1] == 0) {
						inner[1] = pages[i];
					} else if (tag[0] == 1 && tag[1] == -1 || tag[0] == -1 && tag[1] == 1) {
						inner[2] = pages[i];
					} else if (tag[0] == 2 && tag[1] == -1 || tag[0] == -1 && tag[1] == 2) {
						inner[0] = pages[i];
					} else
						inner[0] = pages[i];
				}
			}

			for (int t = 0; t < 3; t++) {
				if (inner[t] != -1)
					System.out.print(inner[t] + "  ");
			}

			System.out.println();
		}

	}

	
	public void LRU() {

		// 初始化工作
		inner[0] = -1;
		inner[1] = -1;
		inner[2] = -1;

		int j = 0;

		for (int i = 0; i < 20; i++) {
			System.out.print("当前请求访问页面" + pages[i] + ":     ");
			if (!isFull()) {
				if (isexists(pages[i])) {
					System.out.print("内存中已存在此页面");
					System.out.println();
					continue;

				}

				else
					inner[j++] = pages[i];
			}

			else {
				if (isexists(pages[i])) {
					System.out.print("内存中已存在此页面");
					System.out.println();
					continue;

				}

				// LRU
				else {
					int count = 0;
					int[] tag = new int[2];
					tag[0] = -1;
					tag[1] = -1;
					for (int t = i - 1; t >= 0; t--) {
						if (count == 2)
							break;
						if (inner[0] == pages[t] && tag[0] != 0 && tag[1] != 0) {
							tag[count++] = 0;

						} else if (inner[1] == pages[t] && tag[0] != 1 && tag[1] != 1) {
							tag[count++] = 1;

						} else if (inner[2] == pages[t] && tag[0] != 2 && tag[1] != 2) {
							tag[count++] = 2;

						}

					}

					if (tag[0] == 1 && tag[1] == 0 || tag[0] == 0 && tag[1] == 1) {
						inner[2] = pages[i];
					} else if (tag[0] == 1 && tag[1] == 2 || tag[0] == 2 && tag[1] == 1) {
						inner[0] = pages[i];
					} else if (tag[0] == 2 && tag[1] == 0 || tag[0] == 0 && tag[1] == 2) {
						inner[1] = pages[i];
					} else if (tag[0] == 0 && tag[1] == -1 || tag[0] == -1 && tag[1] == 0) {
						inner[1] = pages[i];
					} else if (tag[0] == 1 && tag[1] == -1 || tag[0] == -1 && tag[1] == 1) {
						inner[2] = pages[i];
					} else if (tag[0] == 2 && tag[1] == -1 || tag[0] == -1 && tag[1] == 2) {
						inner[0] = pages[i];
					} else
						inner[0] = pages[i];
				}
			}

			for (int t = 0; t < 3; t++) {
				if (inner[t] != -1)
					System.out.print(inner[t] + "  ");
			}

			System.out.println();
		}

	}

	public void Clock() {

		int[] flag = {0,0,0};
		//初始化工作
		inner[0] = -1;
		inner[1] = -1;
		inner[2] = -1;
		
		
		int j=0;
		
		for(int i=0;i<20;i++)
		{
			System.out.print("当前请求访问页面"+pages[i]+":     ");
			if(!isFull())
			{
				if(isexists(pages[i]))
				{
					
					if(inner[0]==pages[i])
						flag[0]=1;
					else if(inner[1]==pages[i])
						flag[1]=1;
					else if(inner[2]==pages[i])
						flag[2]=1;
					
					
					System.out.print("内存中已存在此页面");
					System.out.println();
					continue;
					
				}
					
				
				else 
					inner[j++] = pages[i];
			}
				
			else{
				if(isexists(pages[i]))
				{
					if(inner[0]==pages[i])
						flag[0]=1;
					else if(inner[1]==pages[i])
						flag[1]=1;
					else if(inner[2]==pages[i])
						flag[2]=1;
					
					System.out.print("内存中已存在此页面");
					System.out.println();
					continue;
					
				}
				
				//Clock
				else{
					int t=0;
					
					while(true)
					{
						
						if(flag[t]==1)
						{
							flag[t]=0;
							t=(t+1)%3;
						}
						else if(flag[t]==0)
						{
							inner[t] = pages[i];
							break;
						}
							
					}
				
					
					
					
				}
			}
			
			for(int t=0;t<3;t++)
			{
				if(inner[t]!=-1)
				System.out.print(inner[t]+"  ");	
			}
			
			System.out.println();
		}
	}

	public static void main(String[] args) {

		Pages p = new Pages();
	System.out.println("1.随机生成页面请求");
	System.out.println("2.指定生成页面请求");
	Scanner scanner = new Scanner(System.in);
	int flag1 = scanner.nextInt();
	if(flag1==1)
		p.Init();
	else if(flag1==2)
		p.Init2();
	else 
	{
		System.out.println("指令有误，已自动分配");
	}
	System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
	System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
	while (true) 
	{
		
		System.out.println();
		System.out.println();
		System.out.println();
	
	System.out.println("1.OPT");
	System.out.println("2.FIFO");
	System.out.println("3.LRU");
	System.out.println("4.Clock");
	System.out.println("0.Exit");

	int flag2 = scanner.nextInt();
	if(flag2==0)break;
	switch (flag2) {
	case 1: 
		p.OPT();
		break;
	case 2: 
		p.FIFO();
		break;		
	
	case 3: 
		p.LRU();
		break;
		
	case 4: 
		p.Clock();
		break;
	default:
		break;
	}
	}
	
	}

}
