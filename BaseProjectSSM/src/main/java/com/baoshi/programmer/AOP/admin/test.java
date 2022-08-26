package com.baoshi.programmer.AOP.admin;

import org.aspectj.lang.JoinPoint;

import javax.sql.rowset.Joinable;

public class test {
    public void log(JoinPoint joinPoint){
        System.out.println(joinPoint.getThis().getClass().getClassLoader());
        System.out.println("我执行了");
    }
}
