package com.jeffrey.mybatis.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @program: mybatisPPP
 * @author: Jeffrey
 * @create: 2024-05-06 13:14
 * @description:
 **/
@Setter
@Getter
@ToString
public class MapperBean {

    private String interfaceName;//接口名
    //接口下的所有方法
    List<Function> functions;
}
