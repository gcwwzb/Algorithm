#include <iostream>
#include <fstream>
#include <string>
using std::string;
using namespace std;



typedef struct{
    
    char code;
    int weight;
    int parent,lchild,rchild;
    
    
    
}Node,*Tree;


typedef char ** HuffmanCode;


//从已生成的树中选择出父母为零且权重最小和次小的两个结点，返回他们的序号
void select(Tree T,int end,int &s1,int &s2)
{
    int min=65535;
    int min_no = -1;
    int second_min_no=-1;
    for(int i=0;i<end;i++)//选择最小值
    {
        if(T[i].weight<min && T[i].parent==0)
        {
            min=T[i].weight;
            min_no=i;
        }
        
    }
    
    
    int second_min = 65535;
    
    for(int i=0;i<end;i++)//选择最小值
    {
        if(T[i].weight<second_min && i!=min_no && T[i].parent==0)
        {
            second_min=T[i].weight;
            second_min_no=i;
        }
        
    }
    
    
    s1=min_no;
    s2=second_min_no;
    
    
}





void Huffmantree(int sum,Tree &T)
{
    ofstream outfile("HfmTree.txt",ios::out);
    
    int m = 2*sum-1;
    
    T =(Node*)malloc((2*sum-1)*sizeof(double));
    int i ;
    for( i=0;i<sum;i++)//对原始编码初始化，每个编码为一棵树
    {
        cout<<"请输入第"<<i+1<<"个编码：";
        cin>>T[i].code;
        cout<<endl;
        cout<<"请输入第"<<i+1<<"个编码的权重：";
        cin>>T[i].weight;
        cout<<endl;
        T[i].parent=0;
        T[i].lchild=0;
        T[i].rchild=0;
        outfile<<T[i].code<<" "<<T[i].weight<<" "<<T[i].lchild<<" "<<T[i].rchild<<" "<<T[i].parent<<endl;
    }
    
    for(;i<m;i++)//对树中其他结点初始化
    {
        T[i].weight=0;
        T[i].parent=0;
        T[i].lchild=0;
        T[i].rchild=0;
    }
    for(int j=sum;j<m;j++)
    {
        int s1;
        int s2;
        
        select(T, j, s1, s2);
        
        T[j].lchild = s1;
        T[j].rchild = s2;
        T[s1].parent = j;
        T[s2].parent = j;
        T[j].weight = T[s1].weight+T[s2].weight;
        outfile<<T[j].code<<" "<<T[j].weight<<" "<<T[j].lchild<<" "<<T[j].rchild<<" "<<T[j].parent<<endl;
        
    }
    
    
    outfile.close();
    
    
}




void HuffmanCoding(int sum,Tree T,HuffmanCode &HC)//Huffman译码
{
    ofstream outfile("CodeFile.txt",ios::out);
    
    HC = (HuffmanCode)malloc(sum*sizeof(char*));
    char * cd = (char*)malloc(sum*sizeof(char));
    cd[sum-1]='\0';
    
    
    
    for(int i=0;i<sum;i++)
    {
        int j,k;
        int start = sum-1;//方便逆向记录下编码
        for(j=i,k=T[i].parent;k!=0;j=k,k=T[j].parent)//从叶子到根逆向求编码
        {
            if(T[k].lchild==j)cd[--start]='0';
            else if(T[k].rchild==j)cd[--start]='1';
        }
        HC[i]=(char*)malloc((sum-start)*sizeof(char));
        strcpy(HC[i],&cd[start]);
    }
    
    cout<<endl;
    for(int i=0;i<sum;i++)
    {cout<<T[i].code<<"---->"<<HC[i]<<endl;
        outfile<<T[i].code<<"---->"<<HC[i]<<endl;}
    
    
    outfile.close();
    
    
}



int Decode(int sum,Tree T,HuffmanCode HC)
{
    int num=0;
    ofstream outfile("TextFile.txt",ios::out);
    cout<<"请依次输入字符：(输入$表示终止)";
    char c;
    cin>>c;
    while(c!='$')
    { num++;
        for(int i=0;i<sum;i++)
            if(T[i].code==c)outfile<<HC[i]<<" ";
        
        cin>>c;
    }
    
    return num;
    
}


void PrintCode(int num)
{
    
    ifstream infile("TextFile.txt");
    string str;
    for(int i=0;i<num;i++)
    {
        infile>>str;
        cout<<str;
        
    }
    
    
}





