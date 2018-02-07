#include <iostream>
using namespace std;



typedef struct QNode{
    
    
    int data;
    QNode *next;
}QNode,*Queeue;

QNode *head,*rear;

void InitQueeue(Queeue &Q){
    head = (QNode *)malloc(sizeof(QNode));
    Q = head;
    rear = head;
    head->next = head;
    
}

int GetlenthQueeue(Queeue &q)
{
    
    
    QNode *p = head;
    int num=0;
    while(p->next!=head){
        p=p->next;
        num++;
    }
    return num;
}
int IsEmptyQueeue(Queeue &Q)
{
    if(head->next == head)return 1;
    else return 0;
}

void EnQueeue(Queeue &Q,int data){
    QNode *newnode = (QNode *)malloc(sizeof(QNode));
    
    
    rear->next = newnode;
    newnode->next = head;
    rear = newnode;//采用尾插法入队
    
    newnode->data = data;
    
}

int DeQueeue(Queeue &Q,int &data){
    
    
    if(head->next==head)return 0;//队列为空
    
    
    QNode *oldnode = head->next;
    
    if(oldnode == rear)rear=head;
    
    head->next = oldnode->next;
    
    data = oldnode->data;
    free(oldnode);
    return 1;
    
}



