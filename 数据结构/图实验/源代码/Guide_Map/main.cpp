#include "Guide.h"
#include <iostream>
using namespace std;

int main() {
    
    int vex_num,arc_num;
    cout<<"请输入总的顶点数：";
    cin>>vex_num;
    cout<<endl;
    cout<<"请输入总的边数：";
    cin>>arc_num;
    cout<<endl;
    Grap G;
    InitGrap(G, vex_num, arc_num);
    
    
    int vo,vn;
    
    
    while(true)
    {
        cout<<endl;

        cout<<"======="<<"旅游导航图"<<"======="<<endl;
        cout<<"   1.最佳游览路径"<<endl<<endl;
        cout<<"   2.景点最短路径"<<endl<<endl;
        cout<<"   3.退出"<<endl<<endl;
        cout<<"======="<<"======="<<"============"<<endl;
        int i;
        cin>>i;
        switch (i) {
            case 1:
                cout<<"最佳游览路径如下："<<endl;
                DFStraverse(G, vex_num);
                break;
            case 2:
                cout<<"请输入起点和终点：";
                cin>>vo>>vn;
                int search1,search2;
                for(search1=0;search1<vex_num;search1++)
                    if(G.vexs[search1].no == vo)break;
                for(search2=0;search2<vex_num;search2++)
                    if(G.vexs[search2].no == vn)break;

                ShowPath(G,search1, search2);
                break;
                
            case 3:exit(1);break;
            default:continue;
                
        }
        
        
        
        
        
    }
    
    
    
    return 0;
    
}
