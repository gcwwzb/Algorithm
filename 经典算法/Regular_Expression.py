#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import re


#Test1:
#   验证Email地址的正则表达式。应该可以验证出类似的Email：
#       someone@gmail.com
#       bill.gates@microsoft.com
def is_valid_email(addr):
    str = re.match(r'^\w+[\.\w]*[\@]\w+[\.com]',addr)
    if str:
        return True
    else:
        return False


assert is_valid_email('someone@gmail.com')
assert is_valid_email('bill.gates@microsoft.com')
assert not is_valid_email('bob#example.com')
assert not is_valid_email('mr-bob@example.com')
print('ok')


#Test2:
#   可以提取出带名字的Email地址：
#        <Tom Paris> tom@voyager.org => Tom Paris
#        bob@example.com => bob

def name_of_email(addr):
    str = re.match(r'\<?([\w\s]+)\>?[\w\s]*[\@]\w+\.org',addr)
    if str:
        return str.group(1)
    else:
        return None


# 测试:
assert name_of_email('<Tom Paris> tom@voyager.org') == 'Tom Paris'
assert name_of_email('tom@voyager.org') == 'tom'
print('ok')
