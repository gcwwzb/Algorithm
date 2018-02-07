#include <iostream>
using namespace std;



typedef struct QNode{
    
    string num;
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
    return 0;
}

void EnQueeue(Queeue &Q,string num,int data){
    QNode *newnode = (QNode *)malloc(sizeof(QNode));
    
    
    rear->next = newnode;
    newnode->next = head;
    rear = newnode;//采用尾插法入队
    newnode->num = num;
    newnode->data = data;
    
}

int DeQueeue(Queeue &Q,string &num,int &data){
    
    
    if(head->next==head)return 0;//队列为空
    
    
    QNode *oldnode = head->next;
    
    if(oldnode == rear)rear=head;
    
    head->next = oldnode->next;
    num = oldnode->num;
    data = oldnode->data;
    free(oldnode);
    return 1;
    
}

void PrintQueeue(Queeue Q)
{
    

    
        QNode* p = head;
    
    if(p->next==head)
    {cout<<endl<<"过道内无车辆等候！"<<endl;return;}
    int i=0;
        while(p->next!=head)
        {
        cout<<"过道里等候的第"<<++i<<"辆车的车牌号为: "<<p->next->num<<endl;
          p=p->next;
    }

}

