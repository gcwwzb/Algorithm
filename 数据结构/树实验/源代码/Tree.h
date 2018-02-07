

#include <iostream>
#include "HuffamnTree.h"

using namespace std;

int main() {
    int sum,num;
    Tree T;
    HuffmanCode HC;
    
    while(true)
    {   cout<<endl;
        cout<<"======="<<"HuffmanTree"<<"======="<<endl;
        cout<<"        1.初始化"<<endl<<endl;
        cout<<"        2.Huffman编码"<<endl<<endl;
        cout<<"        3.Huffman译码"<<endl<<endl;
        cout<<"        4.打印译码结果"<<endl<<endl;
        cout<<"        5.退出"<<endl<<endl;
        cout<<"======="<<"======="<<"============"<<endl;
        int i;
        cin>>i;
        switch (i) {
            case 1:
                cout<<"请输入你总共要输入的编码数量：";
                
                cin>>sum;
                cout<<endl;
                Huffmantree(sum, T);
                break;
            case 2: HuffmanCoding(sum, T, HC);
                break;
                
            case 3:num=Decode(sum,T,HC);
                break;
            case 4:PrintCode(num);
                
                break;
                
                
                
            case 5:exit(1);
                
            default:continue;
                
        }
        
        
    }
    
    
    
    
    
    return 0;
    
}
