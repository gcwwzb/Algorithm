

#include <iostream>
#include "List.h"

using namespace  std;

void start(){
    
    cout<<"        约瑟夫环实验：        "<<endl<<endl;
    
    InitList(L);
    cout<<endl<<endl;
    Show();
    cout<<endl<<endl;
    cout<<"请指定报数上限M值：";
    
    int M;
    cin>>M;
    Node *visit=rear->next;
    int no=1;
    while(visit->next!=visit)
    {
        
        int count=1;
        
        while(true)
        {
            if(count==M){
                
                Node *p=visit;
                visit=visit->next;
                
                
        cout<<"第"<<no++<<"个出列的人编号为"<<p->num<<"，密码为"<<p->pwd<<endl;
                Delete(L,p);
                M=p->pwd;//修改每次报数的上限
                break;
                        }
            visit=visit->next;
            count++;
            
            
            
        }

        
    }
    
cout<<"第"<<no<<"个出列的人编号为"<<visit->num<<"，密码为"<<visit->pwd<<endl;
 
    
}






int main(int argc, const char * argv[]) {
    
    
    
    
    
    
    start();
    
    free(L);
    
    
    
    
    return 0;
}
