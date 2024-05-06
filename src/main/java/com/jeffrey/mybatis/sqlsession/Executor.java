package com.jeffrey.mybatis.sqlsession;

/**
 * @program: mybatisPPP
 * @author: Jeffrey
 * @create: 2024-05-06 12:21
 * @description:
 **/
public interface Executor {
        <T>T query(String statement,Object parameter);
}
