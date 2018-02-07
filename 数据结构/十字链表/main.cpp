

#include <iostream>
#include <stdlib.h>
using namespace std;



typedef struct OlNode{
    int i,j;
    float e;
    OlNode * right,* down;
    
}OlNode, * OLink;

typedef struct CrossList{
    OLink *rhead,*chead;
    int mu,nu,tu;
    
}CrossList;


void InitList(CrossList &M){
    int m,n;
    cout<<"请选择矩阵长度："<<endl;
    cin>>m;
    cout<<"请选择矩阵宽度："<<endl;
    cin>>n;
    
    system("CLS");
    M.mu=m;
    M.nu=n;
    if(!(M.rhead=(OLink *)malloc(m*sizeof(OlNode)))) exit(1);
    if(!(M.chead=(OLink *)malloc(n*sizeof(OlNode)))) exit(1);
    
}

void Insert(CrossList &M){
    OlNode * NewNode=(OlNode * )malloc(sizeof(OlNode));
    int i,j;
    float e;
    cout<<"请输入要插入元素在矩阵中的位置："<<endl;
    cin>>i;
    cin>>j;
    
    cout<<"请输入要插入的元素："<<endl;
    cin>>e;
    
    NewNode->i=i;
    NewNode->j=j;
    NewNode->e=e;
    
    if(M.rhead[i]== NULL || M.rhead[i]->j > j)//采用头插
    { NewNode->right = M.rhead[i];
        M.rhead[i]=NewNode;
        
    }
    else{
        OlNode *q=M.rhead[i];
        while(q->right && q->right->j < j)q=q->right;
        
        NewNode->right = q->right;
        q->right =  NewNode;
        
    }
    
    
    
    if(M.chead[j]==NULL || M.chead[j]->i > i)
    {
        NewNode->down = M.chead[j];
        M.chead[j]= NewNode;
    }
    else{
        OlNode *q = M.chead[j];
        while(q->down && q->down->i < i)
            q=q->down;
        
        
        NewNode->down  = q->down;
        q->down = NewNode;
        
    }
    cout<<"Success!"<<endl;
}









int main() {
    
    CrossList M;
    
    InitList(M);
    while(1)
    {int i;
        cin>>i;
        if(i==0)break;
        
        Insert(M);
        
    }
    
    
}
