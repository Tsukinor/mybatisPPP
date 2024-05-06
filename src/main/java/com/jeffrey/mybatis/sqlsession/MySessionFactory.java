package com.jeffrey.mybatis.sqlsession;

/**
 * @program: mybatisPPP
 * @author: Jeffrey
 * @create: 2024-05-06 14:06
 * @description:
 **/
public class MySessionFactory {
    public static MySqlSession openSession(){
        return new MySqlSession();
    }
}