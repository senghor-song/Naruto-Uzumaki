package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

public class TABLE_Leave_Person_Generator_4 {
    private static final Logger LOGGER = Logger.getLogger(TABLE_DYNAMIC_INFO_Generator_3.class);
    private int size = 1;
    // private String driver = "oracle.jdbc.OracleDriver";
    private String url = "jdbc:oracle:thin:@192.168.20.220:1521:orcl";
    private String username = "c##gongan10";
    private String password = "c##gongan10";
    private String[] areas = { "桂林", "十堰", "深圳", "广州", "武汉", "成都", "新疆", "北京", "天津", "上海", "长沙", "黑龙江", "内蒙古", "重庆", "南京", "西安" };
    private List<Map<String, Object>> unitIds = new ArrayList<>();
    private List<Integer> personIds = new ArrayList<Integer>();

    /**
     * 主方法
     * 
     * @author qinzhitian<br>
     * @date 2018年3月16日 下午2:36:39
     */
    TABLE_Leave_Person_Generator_4(int sizeDate) {
        long begintime = System.currentTimeMillis();
        Connection conn = this.getConnection();
        String sql = "INSERT INTO T_COR_LEAVE_PERSON(PRIKEY,CREATE_TIME,NAME,SEX,ID_CARD,DYNAMIC_INFO_ID,DEPARTURE_TIME,END_PLACE,NATIVE_PLACE,"
                + "NATIVE_PLACE_POLICE_ID,NATIVE_PLACE_POLICE_STATION_CO,PERSON_TYPE,TRIGGER_TIME,SIGN_UNIT_ID,START_PLACE,NATIVE_PLACE_RESPONSIBILITY_ID) "
                + "VALUES(?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = null;
        ResultSet res = null;
        Random random = new Random();
        this.getUnitId(conn, pst, res);
        IdCardGenerator g = new IdCardGenerator();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int num = 0;
        while (num < sizeDate) {
            num++;
            try {
                conn.setAutoCommit(false);
                pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                // 执行语句
                int id = this.getMaxPrikey(conn, pst, res);
                for (int x = 0; x < this.size; x++) {
                    Date date = this.randomDate("2018-1-1", "2019-3-1");
                    String formatdate = sdf.format(date);// 获取格式化日期，带有时分秒
                    int origin = random.nextInt(this.areas.length);// 出发地
                    int destination = random.nextInt(this.areas.length);// 目的地
                    while (origin == destination) {
                        destination = random.nextInt(this.areas.length);
                    }
                    int type = random.nextInt(14);
                    type++;
                    // int personId =
                    // random.nextInt(test.personIds.size());//重点人ID
                    int unitId = random.nextInt(this.unitIds.size());// 单位ID
                    int unitId2 = random.nextInt(this.unitIds.size());// 单位ID
                    pst.setInt(1, id + x + 1);
                    pst.setString(2, formatdate);
                    pst.setString(3, ChineseName_Generator.toName());
                    pst.setString(4, "男");
                    pst.setString(5, g.generate());
                    pst.setInt(6, type);
                    pst.setString(7, formatdate);
                    pst.setString(8, this.areas[destination]);
                    pst.setString(9, this.unitIds.get(unitId).get("name").toString());
                    pst.setInt(10, (int) this.unitIds.get(unitId).get("id"));
                    pst.setString(11, this.unitIds.get(unitId).get("name").toString());
                    pst.setString(12, "离市人员");
                    pst.setString(13, "");
                    pst.setInt(14, (int) this.unitIds.get(unitId).get("id"));
                    pst.setString(15, this.areas[origin]);
                    pst.setInt(16, (int) this.unitIds.get(unitId2).get("id"));
                    pst.addBatch();
                }
                pst.executeBatch();
                conn.commit();
                pst.close();
                LOGGER.info("-----------------插入成功-------------------" + num * this.size);
            } catch (SQLException e) {
                LOGGER.error("插入失败：" + e);
                this.close(conn, pst, res);
                conn = this.getConnection();
            }
        }
        long endtime = System.currentTimeMillis();
        LOGGER.info("-----------------耗时-------------------" + (endtime - begintime) / 1000 + "s");
        LOGGER.info("-----------------总条数-------------------" + this.getMaxPrikey(conn, pst, res));
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

    // public List<List<Object>> getExcel() {
    // LOGGER.info("---------------------解析Excel文件----------------------");
    // List<List<Object>> list = null;
    // try {
    // File file = new File("F:/c.xlsx");
    // InputStream in = new FileInputStream(file.getAbsolutePath());
    // ImportExcelUtil importExcelUtil = new ImportExcelUtil();
    // list = importExcelUtil.getBankListByExcel(in, "c.xlsx");
    // /*for (List<Object> list2 : list) {
    // for (Object object : list2) {
    // System.out.println(object);
    // }
    // }*/
    // } catch (FileNotFoundException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (Exception e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // return list;
    // }
    /*
     * public JSONObject getJson() { JSONObject jsonObject = null; try { String
     * input = FileUtils.readFileToString(new File("F:\\content.json"),
     * "UTF-8"); jsonObject = JSONObject.fromObject(input); } catch (Exception
     * e) { e.printStackTrace(); } LOGGER.info(jsonObject); return jsonObject; }
     */
    /**
     * 查询轨迹信息最大ID
     * 
     * @author qinzhitian<br>
     * @date 2018年3月16日 上午10:48:07
     */
    public int getMaxPrikey(Connection conn, PreparedStatement pst, ResultSet res) {
        LOGGER.info("-----------------查询轨迹信息最大ID-------------------");
        String sql = "select max(PRIKEY) from T_COR_LEAVE_PERSON";
        int id = 0;
        try {
            pst = conn.prepareStatement(sql);
            // 执行语句
            res = pst.executeQuery();
            if (res.next())
                id = res.getInt(1);
            pst.close();
            LOGGER.info("------------------------------------" + id);
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            LOGGER.error("查询失败：" + e);
        }
        return id;
    }

    /**
     * 查询重点人ID
     * 
     * @author qinzhitian<br>
     * @date 2018年3月16日 上午10:47:52
     */
    public void getPersonId(Connection conn, PreparedStatement pst, ResultSet res) {
        LOGGER.info("-----------------查询重点人ID-------------------");
        // String sql = "select PRIKEY from T_COR_CONTROL_PERSON";
        String sql = "SELECT PRIKEY " + "  FROM (SELECT a.PRIKEY, ROWNUM rn " + "          FROM (SELECT * " + "                  FROM T_COR_CONTROL_PERSON) a "
                + "         WHERE ROWNUM <= 10000) " + " WHERE rn >= 1";
        try {
            pst = conn.prepareStatement(sql);
            // 执行语句
            res = pst.executeQuery();
            while (res.next()) {
                personIds.add(res.getInt(1));
            }
            pst.close();
            LOGGER.info("------------------------------------" + personIds.size());
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            LOGGER.error("查询失败：" + e);
        }
    }

    /**
     * 查询单位ID
     * 
     * @author qinzhitian<br>
     * @date 2018年3月16日 上午10:47:42
     */
    public void getUnitId(Connection conn, PreparedStatement pst, ResultSet res) {
        LOGGER.info("-----------------查询单位ID-------------------");
        String sql = "select PRIKEY,UNIT_NAME from T_SYS_UNIT";
        try {
            pst = conn.prepareStatement(sql);
            // 执行语句
            res = pst.executeQuery();
            while (res.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", res.getInt(1));
                map.put("name", res.getString(2));
                unitIds.add(map);
            }
            pst.close();
            LOGGER.info("------------------------------------" + unitIds.size());
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            LOGGER.error("查询失败：" + e);
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
