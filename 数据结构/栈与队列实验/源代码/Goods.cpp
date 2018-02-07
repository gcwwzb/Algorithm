#include "Stack.h"
#include <iostream>
using namespace std;



//货架管理
//加货时将货架倒出，最新的货物加到最低，再将货物依次倒入货架
//取货时从货架顶上取，确保取到的为最早生产的
//用栈模拟

void PushGoods(Stack &S)
{
   
    Stack SS;
    InitStack(SS,1000);
    
     if(IsFull(S)==1)
    {
        cout<<"货架已满！"<<endl<<endl;
        return;
    } 
    

    
    while(true)
    {   Node n1;
        if(IsEmpty(S)==1)break;
        Pop(S, n1);
        Push(SS, n1.num1);

    }
    
           int num;
cout<<"请输入你要添加的货物编号："<<endl;
    cin>>num;
    cout<<endl<<endl;
    Push(S,num);

    
    
    while(true)
    { Node n2;
        if(IsEmpty(SS)==1)break;
        Pop(SS, n2);
        Push(S, n2.num1);
        
    }
    
}



void SellGoods(Stack &S)
{
    Node n;
    if(IsEmpty(S) == 1){
        cout<<endl<< "货物已取完，货架内无货物！"<<endl<<endl;
        return;
    }
    
    Pop(S, n);
    cout<<endl<<"成功取出编号为"<<n.num1<<"的货物"<<endl;
    
    
}


int main() {
    Stack S;
    InitStack(S);
    while(true)
    {
        cout<<"======="<<"货架管理系统"<<"======="<<endl;
        cout<<"           1.加货"<<endl<<endl;
        cout<<"           2.取货"<<endl<<endl;
        cout<<"           3.查看"<<endl<<endl;
        cout<<"======="<<"======="<<"============"<<endl;
        int i;
        cin>>i;
        cout<<endl;
        switch(i){
                
            case 1:PushGoods(S);break;
            case 2:   SellGoods(S);break;
            case 3:PrintStack(S);break;
            case 0: return 0;
            default: continue;
                
                
        }
    
    
    }
    
    
}
