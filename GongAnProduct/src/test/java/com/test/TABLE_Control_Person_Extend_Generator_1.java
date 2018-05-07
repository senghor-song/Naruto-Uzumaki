/**
 * 
 */
package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import junit.framework.Test;

import org.apache.log4j.Logger;

import com.ruiec.web.util.RuiecDateUtils;

/**
 * 1重点人详表
 * @author Senghor<br>
 * @date 2018年4月28日 下午4:12:45
 */
public class TABLE_Control_Person_Extend_Generator_1 {
    private static final Logger LOGGER = Logger.getLogger(Test.class);

    private String url = "jdbc:oracle:thin:@192.168.20.220:1521:orcl";

    private String username = "c##gongan10";

    private String password = "c##gongan10";
    private static String[] areas = {"桂林","十堰","深圳","广州","武汉","成都","新疆","北京","天津","上海","长沙","黑龙江","内蒙古","重庆","南京","西安"};

    TABLE_Control_Person_Extend_Generator_1(int sizeDate) {
        Connection conn = this.getConnection();
       
        PreparedStatement pst = null;
        ResultSet res = null;
        Random random = new Random();
        for (int i = 0; i < sizeDate; i++) {
            int id = 0;
            String sqlmaxid = "SELECT T_COR_CONTROL_PERSON_SEQ.Nextval FROM T_COR_MONITOR where ROWNUM = 1";
            try {
                pst = conn.prepareStatement(sqlmaxid);
                // 执行语句
                res = pst.executeQuery();
                if(res.next()) id = res.getInt(1);
                pst.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Date date = this.randomDate("2018-1-1", "2019-3-1");
            String dateString = RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(date);
            int origin = random.nextInt(areas.length);
            String sql = "INSERT INTO T_COR_CONTROL_PERSON_EXTEND VALUES ('"+id+"', null, '"+areas[origin]+"', '"+areas[origin]+"', '犯法', "
                    + "'583597', '1374656712', 'AE86', "
                    + "'AAA55', '"+areas[origin]+"', 'http://192.168.10.131:8091/attached/image/20180429/59f31ae05f4f45019f8fea772c5fdd94.jpg', "
                    + "'"+areas[origin]+"', null, '"+areas[origin]+"', null, 'IT', null, '"+Phone_Generator.getMobile()+"', to_date('"+dateString+"','yyyy-mm-dd hh24:mi:ss'))";
            try {
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                pst.close();
                System.out.println(id+"成功");
                new TABLE_Control_Person_Generator_2(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 关闭连接
        this.close(conn, pst, res);
    }

    public void test() {
        int i = 0;
        while (i < 100) {
            Random typeId = new Random();
            LOGGER.info(typeId.nextInt(15));
            i++;
        }

    }

    /**
     * 生成随机时间
     * 
     * @author qinzhitian<br>
     * @date 2018年3月13日 下午3:26:01
     */
    public Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() >= end.getTime()) {
                return null;
            }

            long date = random(start.getTime(), end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成随机时间
     * 
     * @author qinzhitian<br>
     * @date 2018年3月13日 下午3:26:01
     */
    public long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    /**
     * 获取连接
     * 
     * @author qinzhitian<br>
     * @date 2018年3月13日 下午3:26:34
     */
    public Connection getConnection() {
        Connection conn;
        try {
            conn = null;
            // 加载Oracle驱动程序
            Class.forName("oracle.jdbc.OracleDriver");
            // 建立连接
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("找不到驱动");
        } catch (SQLException e) {
            throw new RuntimeException("数据库连接错误");
        }
        return conn;

    }

    /**
     * 关闭连接
     * 
     * @author qinzhitian<br>
     * @date 2018年3月13日 下午3:26:43
     */
    public void close(Connection conn, PreparedStatement pst, ResultSet res) {
        try {
            if (res != null) {
                res.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            LOGGER.error("关闭连接失败" + e);
        }
    }
}
