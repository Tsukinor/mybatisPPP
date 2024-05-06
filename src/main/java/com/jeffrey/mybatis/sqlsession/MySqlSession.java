package com.jeffrey.mybatis.sqlsession;

import java.lang.reflect.Proxy;

/**
 * @program: mybatisPPP
 * @author: Jeffrey
 * @create: 2024-05-06 13:17
 * @description:
 **/
public class MySqlSession {
    private MyConfiguration myConfiguration =
                            new MyConfiguration();
    private MyExecutor myExecutor =
                            new MyExecutor();

    //编写一个方法selectOne  返回一条记录-对象
    //在原生 Mybatis 中 statement 不是sql，而是要执行的接口方法
    //这里做了简化

    public <T>T selectOne(String statement,Object parameter){
        return myExecutor.query(statement,parameter);
    }

    public <T>T getMapper(Class<T> clazz){
        //返回动态代理对象
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},
                new MyMapperProxy(myConfiguration,this,clazz));
    }
}
