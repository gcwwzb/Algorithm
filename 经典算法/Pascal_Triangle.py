#!/usr/bin/env python3
# -*- coding: utf-8 -*-



#       杨辉三角定义如下：
#
#             1
#            / \
#           1   1
#          / \ / \
#          1   2   1
#         / \ / \ / \
#        1   3   3   1
#       / \ / \ / \ / \
#      1   4   6   4   1
#     / \ / \ / \ / \ / \
#   1   5   10  10  5   1
#  把每一行看做一个list，试写一个generator，不断输出下一行的list


def Triangle(max_line):
    n = 0
    list0 = []

    for n in range(1,max_line+1,1):
        l = [1]
        for i in range(1,n-1,1):
            l.append(list0[i-1]+list0[i])
        if n!=1:
            l.append(1)
        list0 = l
        yield list0
    return



for l in Triangle(10):
    print(l)
