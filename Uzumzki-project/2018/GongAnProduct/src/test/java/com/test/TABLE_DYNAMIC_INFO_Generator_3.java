package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;


public class TABLE_DYNAMIC_INFO_Generator_3 {
    TABLE_DYNAMIC_INFO_Generator_3(int sizeDate) {
        //1、定义变量
        String driverClass = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.20.220:1521:orcl";
        String user = "c##gongan10";
        String password = "c##gongan10";
        try{
        Class.forName(driverClass);
        //2、获取连接对象
        Connection connection = DriverManager.getConnection(url, user, password);
        //3、创建执行存储过程的语句对象
        String sql = "{call CRE_INFO("+sizeDate+")}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        //4、设置参数
        //5、执行
        callableStatement.execute();
        //6、获取数据
        //7、释放资源
        callableStatement.close();
        connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
