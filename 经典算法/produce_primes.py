#!/usr/bin/env python3
# -*- coding: utf-8 -*-

#produce a list from 2 to NAN
def list_iter():
    i = 1
    while True:
        i = i+1
        yield i


#charge if x can be div by n ---------
#       if x can be div(no remaind),then it won't be a prime
#       thus, if has remaind,return True
def charge(n):
    return lambda x : x % n > 0

#using the alogrithm to produce the primes list
def primes():
    l = list_iter()
    while True:
        t = next(l)
        yield t
        l = filter(charge(t),l)


# 打印1000以内的素数:
for n in primes():
    if n < 1000:
        print(n)
    else:
        break
