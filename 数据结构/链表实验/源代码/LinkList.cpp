#include "LinkList.hpp"






void insert()
{
    cout<<"请输入总人数：";
    int sum=0;
    cin>>sum;
    
    
    int i=1;
    
    while(i<=sum)
    {
        cout<<"请输入第"<<i<<"个人的密码：";
        int data;
        cin>>data;
        Insert(L,i,data);
        i++;
        
    }
}


void output()
{
    int i;
    cout<<"请输入要查找的密码：";
    cin>>i;
    cout<<"密码对应序号为："<< Search(L,i)<<endl;
    int pwd0;
    int j;
    cout<<"请输入要删除的序号：";
    cin>>j;
    
    Delete(L,j,pwd0);
    cout<< pwd0<<endl;
    show(L);
}



int main()
{
    InitList(L);
    insert();
    show(L);cout<<endl<<endl;
    output();
    return 0;
}
