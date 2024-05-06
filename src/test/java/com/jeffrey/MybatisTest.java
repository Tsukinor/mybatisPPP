package com.jeffrey;

import com.jeffrey.entity.Monster;
import com.jeffrey.mybatis.sqlsession.Executor;
import com.jeffrey.mybatis.sqlsession.MyConfiguration;
import com.jeffrey.mybatis.sqlsession.MyExecutor;
import com.jeffrey.mybatis.sqlsession.MySqlSession;
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
}
