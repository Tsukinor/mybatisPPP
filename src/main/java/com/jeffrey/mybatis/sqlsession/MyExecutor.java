package com.jeffrey.mybatis.sqlsession;

import com.jeffrey.entity.Monster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: mybatisPPP
 * @author: Jeffrey
 * @create: 2024-05-06 12:22
 * @description:
 **/
public class MyExecutor implements Executor{
    @Override
    public <T> T query(String sql, Object parameter) {
        //得到连接的 Connection
        Connection connection = getConnection();
        //返回查询结果集
        ResultSet set = null;
        PreparedStatement pre = null;

        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1,parameter.toString());
            set = pre.executeQuery();
            // 原生是通过反射得到对象，再进行封装，这里进行简化
            Monster monster = new Monster();
            //把set 数据 封装到monster
            //遍历结果集，把数据封装到monster对象
            while (set.next()){
                monster.setId(set.getInt("id"));
                monster.setName(set.getString("name"));
                monster.setEmail(set.getString("email"));
                monster.setGender(set.getInt("gender"));
                monster.setAge(set.getInt("age"));
                monster.setBirthday(set.getDate("birthday"));
                monster.setSalary(set.getDouble("salary"));
            }
            return (T)monster ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (set != null){
                    set.close();
                }
                if (pre != null){
                    pre.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Connection getConnection(){
        return new MyConfiguration().build("my_mybatis_config.xml");
    }
}
