
#include <iostream>
#include <stdlib.h>
#include <stdio.h>
using namespace std;


typedef struct {
    
    string num1;
    int num2;
   
}Node;





typedef struct{
    
    
    Node *top;
    Node *base;
    int size_num;
}Stack;


void InitStack(Stack &S,int num = 5)//新建栈
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


int IsEmptyStack(Stack &S)
{
    if(S.top==S.base-1)return 1;
    else return 0;
}
int IsFullStack(Stack &S)
{
    if(S.top-S.base>=S.size_num-1)return 1;
    else return 0;
}



int GetTop(Stack &S,string &str)
{
    
    if(S.top==S.base-1)
    {
        
        return 0;
    }
    
    str = (*S.top).num1;
    return 1;
}


int Push(Stack &S,string num1,int num2)
{
    if(S.top-S.base>=S.size_num-1)
    {
        
        return 0;
    }
    S.top++;
    (*S.top).num1=num1;
    (*S.top).num2=num2;
    
    
    return 1;
    
}


int Pop(Stack &S,string &str,int &data)
{
    if(S.top-S.base == -1)
    {
        
        return 0;
    }
    
    
    //n.num1 = 1;
    //n.num2 = 2;
    //n.num2 = 3;
    str = (*S.top).num1;
    data = (*S.top).num2;
    
    
    S.top--;
    return 1;
    
    
}


void PrintStack(Stack S)
{
    Node* p = S.base;
    if(p==S.top+1)
    {cout<<endl<<"停车场内无车辆！"<<endl;return;}
    int i=0;
    while(p!=(S.top+1))
    {
        cout<<"停车场里第"<<++i<<"辆车的车牌号为: "<<p->num1<<endl;
        p++;
    }
}


int charge(Stack S,string id)
{
    Node* p = S.base;
    bool i = false;
    
    while(p!=(S.top+1))
    {
        if(p->num1==id)
        {i=true;break;}
        
        p++;
    }
    if(i==true)return 1;
    else return 0;

}






