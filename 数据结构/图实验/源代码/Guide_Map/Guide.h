#include"Graph.h"
#include <iostream>

using namespace std;







void ShowPath(Grap G,int v0,int vn)
{
    int P[G.vex_num][G.vex_num];
    int D[G.vex_num];
    int finally[G.vex_num];
    for(int i=0;i<G.vex_num;i++)//初始化
    {
        finally[i]=0;
        D[i]=G.arc[v0][i].weight;
        
        for(int j=0;j<G.vex_num;j++)
            P[i][j]=0;
        
        if(D[i]<1000)//判断v0到i有没有路径
        {
            P[i][v0]=1;
            P[i][i]=1;
            
            
        }
        
    }
    
    D[v0]=0;
    finally[v0]=1;
    int v;//v保存到v0路径最短的点
    for(int i=1;i<G.vex_num;i++)
    {
        int min = 1000;
        
        for(int w=0;w<G.vex_num;w++)
        { if(!finally[w])
            if(D[w]<min)
            {
                min = D[w];
                v = w;
            }
            
        }
        finally[v]=1;
        
        for(int w=0;w<G.vex_num;w++)
        {
            if(!finally[w]&&(D[w]>min+G.arc[v][w].weight))
                //如果w没有被并入最短路径集合，并且通过v到w的路径更短
            {
                D[w]=min+G.arc[v][w].weight;
                for(int j=0;j<G.vex_num;j++)
                {//将v的路径赋值给P[w]
                    if(P[v][j]==1)P[w][j]=1;
                }
                P[w][w]=1;
            }
            
        }
        
        
        
    }
    
    
    cout<<"最短游览路径为一次经过下面景点的路径："<<endl;
    for(int i=0;i<G.vex_num;i++)
    {
        if(P[vn][i]==1)cout<<" "<<i+1<<" ";
    }
    
    cout<<endl<<"最短游览路径长度为："<<D[vn];
    
    
}






