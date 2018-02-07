#include <iostream>

using namespace std;

typedef struct {
    //顶点信息
    int no;
  
}Node1;

typedef struct {
    
    int weight;//边的权重
    
    
}Arc,ArcMatrix[20][20];

typedef struct{
    
    Node1 vexs[20];//顶点信息
    ArcMatrix arc;//边的权重
    int vex_num,arc_num;//顶点数、边数
}Grap;



void InitGrap(Grap &Grap,int vex_num,int arc_num)
{
    
    Grap.vex_num=vex_num;
    
    
    Grap.arc_num=arc_num;
    
    
    cout<<"请依次输入每个顶点的信息："<<endl;
    
    for(int i=0;i<vex_num;i++)
    {
        int num;
        cout<<"请输入第"<<i+1<<"个点的编号";
        cin>>num;
        Grap.vexs[i].no = num;
        cout<<endl;
    }
    cout<<endl;
    
    
    for(int i=0;i<vex_num;i++)
        for(int j = 0;j<vex_num;j++)
            Grap.arc[i][j].weight = 1000;//每条边初始化为无穷
    
    
    
    
    cout<<"请依次输入每条边的权重："<<endl;
    
    for(int i=0;i<arc_num;i++)
    {
        int vnum1,vnum2;
        double anum;
        cout<<"请输入第"<<i+1<<"条边的信息";
        cout<<endl;
        
        cout<<"请输入第1个点编号：";
        cin>>vnum1;
        int search1;
        for(search1=0;search1<vex_num;search1++)
            if(Grap.vexs[search1].no == vnum1)break;
        cout<<endl;
        
        cout<<"请输入第2个点编号：";
        cin>>vnum2;
        int search2;
        for(search2=0;search2<vex_num;search2++)
            if(Grap.vexs[search2].no == vnum2)break;
        cout<<endl;
        
        cout<<"请输入边的权重：";
        cin>>anum;
        Grap.arc[search1][search2].weight = anum;
        Grap.arc[search2][search1].weight = anum;
        cout<<endl;cout<<endl;
    }
    
    
}

int visit0[20];

void DFS(Grap &G,int num,int vex)
{
    if(visit0[vex]==0)
    {
        visit0[vex]=1;
        cout<<G.vexs[vex].no<<" ";
        for(int i=0;i<num;i++)
            if(G.arc[vex][i].weight!=1000)DFS(G,num,i);
        
    }
}

void DFStraverse(Grap &G,int num)
{
    
    for(int i=0;i<20;i++)
        visit0[i]=0;//全部设置为未被访问
    
    for(int i=0;i<num;i++)
    {
        if(visit0[i]==0)
        {
            DFS(G,num,i);
            cout<<endl;
        }
        
    }
}






        
        
        









