package com.jeffrey.mybatis.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: mybatisPPP
 * @author: Jeffrey
 * @create: 2024-05-06 13:14
 * @description:
 **/
@Getter
@Setter
@ToString
public class Function {

    //属性
    private String sqlType;//sql类型，比如 select，update，delete，insert
    private String funcName;//方法名
    private String sql;//要执行的sql语句
    private Object resultType;//返回类型、对象
    private String parameterType;//参数类型



}
