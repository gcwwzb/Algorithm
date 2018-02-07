#include <stdio.h>
#include <stdlib.h>
#include <iostream>
using namespace std;

typedef struct Lnode{
    int num;
    int pwd;
    Lnode *next;
}Node,*Linklist;

 Linklist L;

void InitList(Linklist &L){
     Node *head = (Node*)malloc(sizeof(Node));
    L=head;
    head->num=head->pwd=0;
    head->next=NULL;
    
    
}



void Insert(Linklist &L,int i,int data)
{
    Node *newnode = (Node*)malloc(sizeof(Node));
    
    newnode->num=i;
    newnode->pwd=data;
    newnode->next=L->next;
    L->next=newnode;//头插
    
}

void Delete(Linklist &L,int num0,int &pwd0)
{
    if(L->next==NULL){cout<<"链表为空！";return ;}
    Node *find = L;
    while(find->next->num!=num0)
    {
        
       find=find->next;
        if(find->next==NULL ){cout<<"未找到！";return;}
    }
    
    Node *q = find->next;
    pwd0=q->pwd;
    find->next=q->next;
    free(q);
   
    
}



int Search(Linklist &L,int pwd0)
{
    if(L->next==NULL){cout<<"链表为空！";return 0;}
    Node *find = L->next;
    while(find->pwd!=pwd0){
        
       find=find->next;
        if(find==NULL)
        {
            cout<<"未找到！";
           return 0;
        }
        
    }
    return find->num;
    
    
}

void show(Linklist L)
{
    Node *find = L->next;
    while(find!=NULL&&L->next!=NULL)
    {
        cout<<find->num<<"  "<<find->pwd<<endl;
        find=find->next;
    }
}


