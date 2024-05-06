package com.jeffrey;

import com.jeffrey.entity.Monster;
import com.jeffrey.mapper.MonsterMapper;
import com.jeffrey.mybatis.config.MapperBean;
import com.jeffrey.mybatis.sqlsession.*;
import org.junit.Test;

/**
 * @program: mybatisPPP
 * @author: Jeffrey
 * @create: 2024-05-06 12:27
 * @description:
 **/
public class MybatisTest {

    @Test
    public void t1(){
        MyConfiguration myConfiguration = new MyConfiguration();
//        myConfiguration.build();
    }

    @Test
    public void query(){
        MyExecutor myExecutor = new MyExecutor();
        Monster monster = myExecutor.query("select * from monster where id=?", 1);
        System.out.println(monster);
    }

    @Test
    public void selectOne(){
        MySqlSession mySqlSession = new MySqlSession();
        Monster o = mySqlSession.selectOne("select * from monster where id=?", 1);
        System.out.println(o);
    }

    @Test
    public void readMapper(){
        MyConfiguration myConfiguration = new MyConfiguration();
        MapperBean mapperBean = myConfiguration.readMapper("MonsterMapper.xml");
        System.out.println(mapperBean.getFunctions());
        System.out.println(mapperBean.getInterfaceName());
    }
    @Test
    public void getMapper(){
        MySqlSession mySqlSession = new MySqlSession();
        MonsterMapper mapper = mySqlSession.getMapper(MonsterMapper.class);
        Monster monsterById = mapper.getMonsterById(1);
        System.out.println(monsterById);
    }

    @Test
    public void openSession(){
        MySqlSession mySqlSession = MySessionFactory.openSession();
        MonsterMapper mapper = mySqlSession.getMapper(MonsterMapper.class);
        Monster monsterById = mapper.getMonsterById(1);
        System.out.println(monsterById);
    }
}
