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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import junit.framework.Test;

import org.apache.log4j.Logger;

import com.ruiec.web.util.RuiecDateUtils;

/**
 * 6.布控人员预警表
 * 
 * @author Senghor<br>
 * @date 2018年4月28日 下午4:12:45
 */
public class TABLE_Control_Monitor_Alarm_Generator_6 {
    private static final Logger LOGGER = Logger.getLogger(Test.class);

    private String url = "jdbc:oracle:thin:@192.168.20.220:1521:orcl";

    private String username = "c##gongan10";

    private String password = "c##gongan10";
    private static String[] units = { "694093", "694103", "694100", "694105" };
    private static String[] areas = {"桂林","十堰","深圳","广州","武汉","成都","新疆","北京","天津","上海","长沙","黑龙江","内蒙古","重庆","南京","西安"};
    private static String[] users = { "694116", "694117", "694118", "694119" };
    private List<Integer> dynamicInfos=new ArrayList<Integer>();
    private List<Integer> monitorIds=new ArrayList<Integer>();

    TABLE_Control_Monitor_Alarm_Generator_6(int sizeDate) {
        Connection conn = this.getConnection();
        PreparedStatement pst = null;
        ResultSet res = null;
        IdCardGenerator g = new IdCardGenerator();
        Random random = new Random();
        this.getDynamicInfo(conn, pst, res);
        this.getMonitor(conn, pst, res);
        for (int i = 0; i < sizeDate; i++) {
            Date date = this.randomDate("2018-1-1", "2019-3-1");
            String dateString = RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(date);
            int size = random.nextInt(users.length);
            int area = random.nextInt(areas.length);
            int dynamicInfoId=random.nextInt(this.dynamicInfos.size());//轨迹ID
            int monitorId=random.nextInt(this.monitorIds.size());//布控人员ID
            String sql = "INSERT INTO T_COR_MONITOR_ALARM VALUES (T_COR_MONITOR_ALARM_SEQ.Nextval, "
                    + "TO_DATE('"+dateString+"', 'YYYY-MM-DD HH24:MI:SS'), "
                    + "TO_DATE('"+dateString+"', 'YYYY-MM-DD HH24:MI:SS'), "
                    + "'"+g.generate()+"', null, '"+dynamicInfos.get(dynamicInfoId)+"', null, '1', '"+units[size]+"', "
                    + "null, null, null, '"+areas[area]+"', '"+areas[area]+"', '"+monitorIds.get(monitorId)+"', '1')";
            try {
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                pst.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 关闭连接
        this.close(conn, pst, res);
    }

    public void getDynamicInfo(Connection conn,PreparedStatement pst,ResultSet res){
        LOGGER.info("------ 查询轨迹id----------");
        String sql="select prikey from (select tcd.prikey,ROWNUM n from (select * from T_COR_DYNAMIC_INFO) tcd where ROWNUM<=1000) T_COR_DYNAMIC_INFO";
        try {
            pst=conn.prepareStatement(sql);
            res=pst.executeQuery();
             while (res.next()) {
               dynamicInfos.add(res.getInt(1));                 
            }
            LOGGER.info("------------------------------------"+dynamicInfos.size());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public void getMonitor(Connection conn,PreparedStatement pst,ResultSet res){
        LOGGER.info("------ 查询轨迹id----------");
        String sql="select prikey from (select tcd.prikey,ROWNUM n from (select * from T_COR_Monitor) tcd where ROWNUM<=1000) T_COR_Monitor";
        try {
            pst=conn.prepareStatement(sql);
            res=pst.executeQuery();
             while (res.next()) {
               monitorIds.add(res.getInt(1));                 
            }
             LOGGER.info("------------------------------------"+dynamicInfos.size());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
