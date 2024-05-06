package com.jeffrey.mapper;

import com.jeffrey.entity.Monster;

/**
 * @program: mybatisPPP
 * @author: Jeffrey
 * @create: 2024-05-06 13:31
 * @description:
 **/
public interface MonsterMapper {

    //查询
    Monster getMonsterById(Integer id);
}
