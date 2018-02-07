package Bankers_Algorithm;

import java.util.Scanner;

import org.omg.CORBA.Request;

public class Banker {
	int[][] Max;
	int[][] Needed;
	int[][] Allocation;
	int[] Available;
	int N;
	int Pnum;
	
	//手动初始化
	public void Init_myself() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入资源种类：");
		N = scanner.nextInt();
		Available = new int[N];
		System.out.println("请依次输入初始可用资源数量");
		for(int i=0;i<N;i++)
			Available[i] = scanner.nextInt();
		System.out.println("请输入进程总数：");
		Pnum = scanner.nextInt();
		Max = new int[Pnum][N];
		Needed = new int[Pnum][N];
		Allocation = new int[Pnum][N];
		for(int i=0;i<Pnum;i++)
		{
			System.out.println("请依次输入进程"+i+"各资源总需求：");
			for(int j = 0;j<N;j++)
			{
				Max[i][j] = scanner.nextInt();
			}
			
			System.out.println("请依次输入进程"+(i+1)+"已拥有各资源的数量：");
			for(int j = 0;j<N;j++)
			{
				Allocation[i][j] = scanner.nextInt();
			}
			
			for(int j = 0;j<N;j++)
			{
				Needed[i][j] = Max[i][j] - Allocation[i][j];
			}
			
		}
		
		System.out.println("初始化完毕！");
		System.out.println("----------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------");

	}
	
	//自动初始化
	public void Init_auto() {
		N = 3;
		Pnum = 5;
		
		Available = new int[N];
		Available[0] = 3;
		Available[1] = 3;
		Available[2] = 2;
		
		Max = new int[Pnum][N];
		Needed = new int[Pnum][N];
		Allocation = new int[Pnum][N];
		
		int tem1[][] = {{7,5,3},{3,2,2},{9,0,2},{2,2,2},{4,3,3}};
		Max = tem1;
		
		int tem2[][] = {{0,1,0},{2,0,0},{3,0,2},{2,1,1},{0,0,2}};
		Allocation = tem2;
		
		for(int i=0;i<Pnum;i++)
			for(int j=0;j<N;j++)
				Needed[i][j] = Max[i][j] - Allocation[i][j];
		
		
		
	
	}
	
	//判断是否可以预分配
	public int Judge_1(int[][] Needed,int[] Available,int[] Request,int no) {
		
		for(int i=0;i<N;i++)
		{
			if(Request[i]>Needed[no][i])
			return 0;//失败
			if(Request[i]>Available[i])
				return 2;//等待
			
		}
		
		return 1;//成功
		
		
		
	}

	//预分配
	public void Allocate(int[] Request,int no) {
		for(int i=0;i<N;i++)
		{
			Needed[no][i] = Needed[no][i]-Request[i];
			Allocation[no][i] = Allocation[no][i] + Request[i];
			Available[i] = Available[i] - Request[i];
		}
				
	}

	//返还原状
	public void reAllocate(int[] Request,int no) {
		for(int i=0;i<N;i++)
		{
			Needed[no][i] = Needed[no][i]+Request[i];
			Allocation[no][i] = Allocation[no][i] - Request[i];
			Available[i] = Available[i] + Request[i];
		}
				
	}
	
	//安全检查时判断是否有符合条件的进程
	public int Judge_2(int[] Work,int[][] Needed0,boolean[] Finish) {
		
		
		
		for(int i=0;i<Pnum;i++)
		{
			
			if(Finish[i]==true)
				continue;
			
			boolean flag=true;
			for(int j=0;j<N;j++)
				if(Work[j]<Needed0[i][j])
				{
					flag=false;
					break;
				}
					
			
			if(flag==true)return i;
			
			
			
		}
		
		return -1;
		
	}
	
	//进行安全性检查
	public boolean Safty_Judge(StringBuffer str) {
		
		//临时进行安全性检查而复制的矩阵
		int[][] Max0 = new int[Pnum][N];
		int[][] Needed0=  new int[Pnum][N];;
		int[][] Allocation0=  new int[Pnum][N];
		int[] Work= new int[N];
		
		for(int i=0;i<N;i++)
			Work[i] = Available[i];
		
		for(int i=0;i<Pnum;i++)
			for(int j=0;j<N;j++)
			{
				Needed0[i][j] = Needed[i][j];
				Max0[i][j] = Max[i][j];
				Allocation0[i][j] = Allocation[i][j];
			}
		
		
		
		boolean[] Finish = new boolean[Pnum];
		for(int i=0;i<Pnum;i++)
			Finish[i] = false;
		
		while(true)
		{
			//判断是否已完成
			boolean flag0 = true;
			for(int i=0;i<Pnum;i++)
				if(Finish[i]==false)
				{
					flag0 = false;
					break;
				}
			
			if(flag0==true) return true;
					
			
			
			//进行检测，寻找安全序列
			
				int Pno = Judge_2(Work, Needed0,Finish);
				//System.out.println(Pno);
				if(Pno==-1) return false;
				
				Finish[Pno] = true;
				str.append("P"+Pno+"  ");
				for(int i=0;i<N;i++)
				{
					Work[i] = Work[i] + Allocation0[Pno][i];
					
				}
				
				
			
		}
		
		
		
	}
	
	
	//某进程请求资源
	public boolean Judge_init() {
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println();
		
		System.out.println("请输入请求资源的进程号：");
		int no = scanner.nextInt();
		
		int[] Request = new int[N];
		System.out.println();
		System.out.println();
		System.out.println("请依次输入所请求资源数量");
		for(int i=0;i<N;i++)
			Request[i] = scanner.nextInt();
		
		
		int flag = Judge_1(Needed, Available, Request, no);
		if(flag==0)
			{
			System.out.println("请求失败！不允许超出所需资源最大值！");
			return false;
			}
		else if(flag==2)
			{
			System.out.println("请求失败！当前尚无足够资源！");
			return false;
			}
		else 
		{
			
			Allocate(Request, no);
			StringBuffer sBuffer = new StringBuffer("");
			boolean flag0 = Safty_Judge(sBuffer);
			
			if(flag0==true)
			{
				System.out.println("存在安全序列"+sBuffer.toString()+"，允许分配");
				System.out.println();
				System.out.println();
				System.out.println("分配后处理结果和资源状态如下：");
				System.out.println();
				System.out.println();
				printAll();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				return true;
			}
				
			else{
				System.out.println("不存在安全序列，不允许分配！！");
				reAllocate(Request, no);
				return false;
			}
			
			
			
		}
			
		
	}
	
	
	//打印全部矩阵
	public void printAll() {

		System.out.println("最大需求矩阵：");
		for(int i=0;i<Pnum;i++)
		{
			for(int j=0;j<N;j++)		
				System.out.print(Max[i][j]+"  ");
			
			System.out.println();
		}
		
		System.out.println("当前已分配矩阵：");
		for(int i=0;i<Pnum;i++)
		{
			for(int j=0;j<N;j++)		
				System.out.print(Allocation[i][j]+"  ");
			
			System.out.println();
		}
		System.out.println();
		System.out.println();
			
		System.out.println("剩余需求矩阵：");
		for(int i=0;i<Pnum;i++)
		{
			for(int j=0;j<N;j++)		
				System.out.print(Needed[i][j]+"  ");
			
			System.out.println();
		}
		System.out.println();
		System.out.println();
		
		System.out.println("剩余资源：");
		
			for(int j=0;j<N;j++)		
				System.out.print(Available[j]+"  ");
			
			System.out.println();
			System.out.println();
		
		
	}
	
	public void Bankers_Algorithm() {
		
		System.out.println("输入1选择手动初始化");
		System.out.println("输入2选择自动初始化");
		Scanner scanner = new Scanner(System.in);
		int flag = scanner.nextInt();
		if(flag==1)
			Init_myself();
		else if(flag==2)
			Init_auto();
		else
			{
				System.out.println("指令错误");
				return;
			}
		
		printAll();
		
		StringBuffer sBuffer = new StringBuffer("");
		
		boolean flag2 = Safty_Judge(sBuffer);
		if(flag2==false){
			System.out.println("当前不存在安全序列，初始化失败！");
			return;
		}
		
		else{
			System.out.println("当前存在安全序列："+sBuffer.toString());
		}
		
		
		
		while(true)
		{
			Judge_init();
		}
		
		
	}

	
	
	public static void main(String[] args) {
	new Banker().Bankers_Algorithm();
	}
	
}
