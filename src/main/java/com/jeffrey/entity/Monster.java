package com.jeffrey.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: mybatisPPP
 * @author: Jeffrey
 * @create: 2024-05-06 13:03
 * @description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monster {

    private Integer id;
    private Integer age;
    private String name;
    private String email;
    private Date birthday;
    private double salary;
    private Integer gender;

}