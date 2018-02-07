#include "Grap.h"
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
    InitGrap(G,vex_num,arc_num);
    
    cout<<endl<<"深度优先便利："<<endl;
    DFStraverse(G, vex_num);
    cout<<endl<<"广度优先便利："<<endl;
    BFStarverse(G, vex_num);
    return 0;
}
