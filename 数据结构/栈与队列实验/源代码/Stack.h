
#include <iostream>
#include <stdlib.h>
#include <stdio.h>
using namespace std;


typedef struct {
    
    int num1;
    int num2;
    int num3;
}Node;





typedef struct{
    
    
    Node *top;
    Node *base;
    int size_num;
}Stack;


void InitStack(Stack &S,int num = 10)//新建栈
{
    S.base=(Node *)malloc(num*sizeof(Node));
    S.top=S.base-1;//先挖坑再种萝卜
    S.size_num = num;
}

void DestoryStack(Stack &S)//撤销栈
{
    free(S.base);
}

void ClearStack(Stack &S)
{
    S.top=S.base-1;
}


int GetTop(Stack &S,Node &n)
{
    
    if(S.top==S.base-1)
    {
        
        return 0;
    }
    
    n = *S.top;
    return 1;
}

int IsEmpty(Stack S)
{
    
    if(S.top-S.base == -1)
        return 1;
    
    
    
    else return 0;
}
int IsFull(Stack S)
{
    if(S.top-S.base>=S.size_num-1)
    {
        
        return 1;
    }
    else return 0;
}

void Push(Stack &S,int num1)
{
   
    S.top++;
    S.top->num1=num1;
   
    
    
    
}


int Pop(Stack &S,Node &n)
{
    if(S.top-S.base == -1)
    {
       
        return 0;
    }
    
 
  
    n.num1 = S.top->num1;
   
    
    S.top--;
    return 1;
    
    
}





void PrintStack(Stack S)
{
    Node* p = S.base;
    if(p==S.top+1)
    {cout<<endl<<"货架内无货物！"<<endl;return;}
    int i=0;
    while(p!=(S.top+1))
    {
        cout<<"货架从下到上里第"<<++i<<"个货物编号为: "<<p->num1<<endl;
        p++;
    }
}







