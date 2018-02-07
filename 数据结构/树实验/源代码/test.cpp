
#include <iostream>
#include "Tree.h"
using namespace std;

int main() {
  
   
    Tree T;
    
    
    
    InitTree(T);
    cout<<endl;
    cout<<"先序遍历二叉树：";
    preOrderTraverse(T);
   cout<<endl;
    cout<<"中序遍历二叉树：";
    inOrderTraverse(T);
    cout<<endl;
    cout<<"后序遍历二叉树：";
    postOrderTraverse(T);
    cout<<endl;
    
    
    
    return 0;
}
