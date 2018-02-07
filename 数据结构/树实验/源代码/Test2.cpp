

#include <iostream>
#include "HuffamnTree.h"

using namespace std;

int main() {
    
    Tree T;
    HuffmanCode HC;
    cout<<"请输入你总共要输入的编码数量：";
    int sum;
    cin>>sum;
    cout<<endl;
    
    Huffmantree(sum, T);
    HuffmanCoding(sum, T, HC);
    Decode(sum,T,HC);
    
    return 0;
}
