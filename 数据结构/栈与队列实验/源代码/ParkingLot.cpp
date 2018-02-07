#include <iostream>
using namespace std;
#include "Queeue-Link.h"
#include "Stack.h"
#define FEE 10  //每小时停车费用十元

//停车场问题
//假设停车场内空间为5，过道空间无限
//停车场内部用栈表示，过道空间用队列表示
//负责暂时停放让行车辆的假设也在过道，但与等候车辆不属于同一过道，用栈表示，假设空间也为10





void arrive(Stack &parkinglot,Queeue &road){
    
    string id;
    int data;
    
    cout<<"请输入到来车辆的车牌号：";
    cin>>id;
    cout<<endl<<"请输入车辆到来时间：";
    cin>>data;
    cout<<endl;
    
    if(IsFullStack(parkinglot)==1)
    {
       EnQueeue(road,id,data);
        cout<<"停车场已满，暂时在外排队等候"<<endl<<endl;
    }
    
    else{
        if(IsFullStack(parkinglot)==0)
        {
            Push(parkinglot,id,data);
            cout<<"开始计费"<<endl<<endl;
        }
        
    }
    
}

void leave(Stack &parkinglot,Queeue &road,Stack &waitline){
    
    
    if(IsEmptyStack(parkinglot))
       {
           cout<<endl<<"没有车可以离开，停车场为空!"<<endl;
           return;
       }
    
    
    
    string id;
    int data;
    
    cout<<"请输入要离开车辆的车牌号：";
    cin>>id;
    int charge_id = charge(parkinglot, id);
    if(charge_id==0)
    {
        cout<<endl<<"停车场里没有此车！请确认！"<<endl<<endl;
        return;
    }
    
    cout<<endl<<"请输入车辆离开时间：";
    cin>>data;
    cout<<endl;
  
    
    while(true)
    {
        string id2;
        int data2;
        Pop(parkinglot, id2, data2);
        if(id2==id)//车辆已出停车场，缴费
        {
            int fee;
            fee = FEE*(data - data2);
            cout<<"请缴费"<<fee<<"元"<<endl;
            break;
        }
        else{
            Push(waitline, id2, data2);//后面车辆依次退出停车场，让行
        }
    }
    
    while(!IsEmptyStack(waitline))//暂时离开的车辆依次进入停车场
    {
        string id3;
        int data3;
        
        Pop(waitline, id3, data3);
        Push(parkinglot, id3, data3);
    }
  //由于每次函数执行总是仅仅出来一辆车，所以过道队列中如果有车辆，仅仅进来一辆即可
    if(!IsEmptyQueeue(road))
    {
        string id4;
        int data4;
        DeQueeue(road, id4, data4);
        Push(parkinglot, id4, data4);
    }
    
    
    
}

void start(Stack &parkinglot,Queeue &road,Stack &waitline)
{
    
    while(true)
    {
        cout<<"======="<<"停车场管理系统"<<"======="<<endl;
        cout<<"           1.泊车"<<endl<<endl;
        cout<<"           2.离开"<<endl<<endl;
        cout<<"           3.查看"<<endl<<endl;
        cout<<"======="<<"======="<<"============"<<endl;
        int i;
        cin>>i;
        switch (i) {
            case 1:
                arrive(parkinglot, road);
                break;
            case 2:leave(parkinglot, road, waitline);
                break;
           
            case 3:PrintStack(parkinglot);PrintQueeue(road);break;
               default:continue;
                
        }
        
        
    }
    
    
    
}


int main()
{
    Queeue road;
    Stack parkinglot;
    Stack waitline;
    
    
    InitStack(parkinglot,3);
    InitStack(waitline,3);
    InitQueeue(road);
    
    start(parkinglot,road,waitline);
    
    
    
    
    return 0;
}
