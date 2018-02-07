#include <stdio.h>
#include <stdlib.h>
#include <iostream>
using namespace std;

typedef struct Lnode{
    int num;
    int pwd;
    Lnode *next;
}Node,*Linklist;

Node *rear;//尾指针
Linklist L;

void Insert(Linklist &L,int i,int data)
{
    Node *newnode = (Node*)malloc(sizeof(Node));
    
    newnode->num=i;
    newnode->pwd=data;
    newnode->next=rear->next;
    rear->next=newnode;
    rear=newnode;
    
}

void InitList(Linklist &L){
    
    int sum=0;
    while(1){
    cout<<"请输入总人数（不超过30人）：";
    
    cin>>sum;
        if(sum>30){
            cout<<"输入人数超过30！请重新输入！" ;
            cout<<endl<<endl;
            continue;
        }
    cout<<endl<<endl;
        break;
    }
    int i=1;
    
    while(i<=sum)
    {
        cout<<"请输入第"<<i<<"个人的密码：";
        int data;
        cin>>data;
        if(i==1){
            Node *newnode = (Node*)malloc(sizeof(Node));
            L=newnode;
            newnode->num=i;
            newnode->pwd=data;
            newnode->next=newnode;//循环链表构建
            rear=L;
            
        }else{
            Insert(L,i,data);
        }
        
        i++;
        
    }

    
    
}





void Delete(Linklist &L,Node *N)
{
    if(L==NULL){cout<<"链表为空！";return ;}
    Node *find = N;
    while(find->next!=N)
        find=find->next;
    
    
    Node *q = N;
    find->next=q->next;
    
    
    
}


void Show(){
    Node *find = rear;
    do{
        cout<<" 第"<<find->next->num<<"个人，密码为"<<find->next->pwd<<endl;
        
        find=find->next;
    } while(find!=rear);
    
}








