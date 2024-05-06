package com.jeffrey.mybatis.sqlsession;

import com.jeffrey.mybatis.config.Function;
import com.jeffrey.mybatis.config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @program: mybatisPPP
 * @author: Jeffrey
 * @create: 2024-05-06 13:32
 * @description:
 **/
public class MyMapperProxy implements InvocationHandler {

    private MySqlSession mySqlSession;
    private String mapperFile;
    private MyConfiguration myConfiguration;

    public MyMapperProxy( MyConfiguration myConfiguration,
                          MySqlSession mySqlSession,
                         Class clazz) {
        this.mySqlSession = mySqlSession;
        this.myConfiguration = myConfiguration;
        this.mapperFile = clazz.getSimpleName() + ".xml";
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean mapperBean = myConfiguration.readMapper(this.mapperFile);
        //判断是否是xml文件对应的接口
        if (!method.getDeclaringClass().getName().equals(mapperBean.getInterfaceName())){
            return null;
        }
        //取出mapperBean的 functions
        List<Function> functions = mapperBean.getFunctions();
        //判断mapperBean解析对应 xml 文件后 是否有方法
        for (Function function :functions) {
            //当前要执行的方法和 function.getFuncName() 一样
            //说明我们可以从当前遍历的 function 对象中，取出相应的sql信息，并执行方法
            if (method.getName().equals(function.getFuncName())){
                //如果我们当前的function 要执行的sql type 是select
                //就执行selectOne （这里做了简化，在sqlSession中就写了一个方法）
                //原生的 sqlSession 应该对应不同的方法，根据不同匹配情况调用不同方法
                //并且还需要进行参数解析处理，还有比较复杂的字符串处理，拼接sql，处理返回类型等等
                //这里主要是 想了解 mybatis 生成 mapper 动态代理对象，调用方法的机制
                if ("select".equalsIgnoreCase(function.getSqlType())){
                    return mySqlSession.selectOne(function.getSql(),String.valueOf(args[0]));
                }
            }
        }


        return null;
    }
}
