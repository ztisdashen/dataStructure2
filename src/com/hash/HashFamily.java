package com.hash;
/*
 * @Author zt648
 * @Description //散列函数的集合，允许布谷鸟散列使用任意数量的散列函数
 * @Date 14:08 2019/5/22
 * @Param
 * @return
 * @throw
 **/
public interface HashFamily<T> {
    int hash(T t,int which);
    int getNumberOfFunctions();
    void generateFunction();
}
