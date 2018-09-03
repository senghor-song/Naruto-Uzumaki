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

import org.apache.log4j.Logger;

public class TABLE_DYNAMICINFO_Generator {
	private static final Logger LOGGER = Logger.getLogger(TABLE_DYNAMIC_INFO_Generator_3.class);
	
	private int size  = 100;
	
	//private String driver = "oracle.jdbc.OracleDriver";
	
	private String url = "jdbc:oracle:thin:@192.168.10.130:1521:orcl";
	
	private String username = "c##gongantest";
	
	private String password = "c##gongantest";
	
	private String jsonString = "{\"title\":{\"姓名\":\"姓名\",\"性别\":\"性别\",\"证件号码\":\"证件号码\",\"户籍地址名称\":\"户籍地址名称\",\"重点人派出所代码\":\"重点人派出所代码\",\"航班号\":\"航班号\",\"证件号码_航班数据\":\"证件号码_航班数据\",\"入市局时间\":\"入市局时间\",\"出行方式\":\"出行方式\",\"是否离鄂\":\"是否离鄂\",\"出发机场\":\"出发机场\",\"到达机场\":\"到达机场\"},"
			+ "\"value\":[{\"key\":\"姓名\",\"value\":\"姓名11\",\"type\":\"string\"},"
			+ "{\"key\":\"性别\",\"value\":\"女\",\"type\":\"string\"},"
			+ "{\"key\":\"证件号码\",\"value\":\"420117199708113512\",\"type\":\"string\"},"
			+ "{\"key\":\"户籍地址名称\",\"value\":\"湖北省郧县大柳乡左溪寺村８组\",\"type\":\"string\"},"
			+ "{\"key\":\"重点人派出所代码\",\"value\":\"\",\"type\":\"string\"},"
			+ "{\"key\":\"航班号\",\"value\":\"6585\",\"type\":\"string\"},"
			+ "{\"key\":\"证件号码_航班数据\",\"value\":\"420321199108054922\",\"type\":\"string\"},"
			+ "{\"key\":\"入市局时间\",\"value\":\"2017-11-04 20:00:50\",\"type\":\"string\"},"
			+ "{\"key\":\"出行方式\",\"value\":\"飞机\",\"type\":\"string\"},"
			+ "{\"key\":\"是否离鄂\",\"value\":\"是\",\"type\":\"string\"},"
			+ "{\"key\":\"出发机场\",\"value\":\"天河国际机场\",\"type\":\"string\"},"
			+ "{\"key\":\"到达机场\",\"value\":\"首都国际机场\",\"type\":\"string\"}]}";
	private String[] areas = {"桂林","十堰","深圳","广州","武汉","成都","新疆","北京","天津","上海","长沙","黑龙江","内蒙古","重庆","南京","西安"}; 
	private List<Integer> unitIds = new ArrayList<>();
	private List<Integer> personIds = new ArrayList<Integer>();
	public static void main(String[] args) {
		TABLE_DYNAMICINFO_Generator test = new TABLE_DYNAMICINFO_Generator();
		long begintime = System.currentTimeMillis();
		Connection conn = test.getConnection();
		String sql = "INSERT INTO T_COR_DYNAMIC_INFO(PRIKEY,CREATE_TIME,ORIGIN,DESTINATION,TRIGGER_TIME,TYPE,INFORMATION,CONTROL_PERSON_ID,ALARM_UNIT_ID) "
				+ "VALUES(?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?)";
		PreparedStatement pst = null;
		ResultSet res = null;
		Random random = new Random();
		test.getPersonId(conn,pst,res);
		test.getUnitId(conn,pst,res);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int num = 0;
		while (num < 20000) {
			try {
				conn.setAutoCommit(false);
				pst = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				// 执行语句
				int id = test.getMaxPrikey(conn, pst, res);
				for(int x = 0; x < test.size; x++){ 
					Date date = test.randomDate("2018-1-1 00:00:00","2019-3-1 00:00:00");
					String formatdate = sdf.format(date);//获取格式化日期，带有时分秒
					int origin = random.nextInt(test.areas.length);//出发地
					int destination = random.nextInt(test.areas.length);//目的地
					while (origin==destination) {
						destination = random.nextInt(test.areas.length);
					}
					int type = random.nextInt(14);
					type++;
					int personId = random.nextInt(test.personIds.size());//重点人ID
					int unitId = random.nextInt(test.unitIds.size());//单位ID
					pst.setInt(1, id+x+1); 
					pst.setString(2, formatdate); 
					pst.setString(3, test.areas[origin]); 
					pst.setString(4, test.areas[destination]); 
					pst.setString(5, formatdate); 
					pst.setInt(6, type); 
					pst.setString(7, test.jsonString); 
					pst.setInt(8, test.personIds.get(personId)); 
					pst.setInt(9, test.unitIds.get(unitId)); 
					pst.addBatch(); 
				} 
				pst.executeBatch(); 
				conn.commit();
				pst.close();
				num++;
				if (num % 100 == 0) {
					test.close(conn, pst, res);
					conn = test.getConnection();
				}
				LOGGER.info("-----------------插入成功-------------------" + num * test.size);
			} catch (SQLException e) {
				LOGGER.error("插入失败：" + e);
				test.close(conn, pst, res);
				conn = test.getConnection();
				num--;
			}
		}
		long endtime = System.currentTimeMillis();
		LOGGER.info("-----------------耗时-------------------" + (endtime - begintime) / 1000 + "s");
		LOGGER.info("-----------------总条数-------------------" + test.getMaxPrikey(conn, pst, res));
		// 关闭连接
		test.close(conn, pst, res);
	}
	
	public void test() {
		int i = 0;
		while (i<100) {
			Random typeId = new Random();
			LOGGER.info(typeId.nextInt(15));
			i++;
		}
		
	}
	/*public JSONObject getJson() {
		JSONObject jsonObject = null;
        try {
            String input = FileUtils.readFileToString(new File("F:\\content.json"), "UTF-8");
            jsonObject = JSONObject.fromObject(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info(jsonObject);
        return jsonObject;
    }*/
	/**
	 * 查询轨迹信息最大ID
	 * @author qinzhitian<br>
	 * @date 2018年3月16日 上午10:48:07
	 */
	public int getMaxPrikey(Connection conn,PreparedStatement pst, ResultSet res) {
		LOGGER.info("-----------------查询轨迹信息最大ID-------------------");
		String sql = "select max(PRIKEY) from T_COR_DYNAMIC_INFO";
		int id = 0;
		try {
			pst = conn.prepareStatement(sql);
			// 执行语句
			res = pst.executeQuery();
			if(res.next()) id = res.getInt(1);
			LOGGER.info("------------------------------------"+id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			LOGGER.error("查询失败：" + e);
		}
		return id;
	}
	/**
	 * 查询重点人ID
	 * @author qinzhitian<br>
	 * @date 2018年3月16日 上午10:47:52
	 */
	public void getPersonId(Connection conn,PreparedStatement pst, ResultSet res) {
		LOGGER.info("-----------------查询重点人ID-------------------");
		//String sql = "select PRIKEY from T_COR_CONTROL_PERSON";
		String sql = "SELECT PRIKEY " + 
				"  FROM (SELECT a.PRIKEY, ROWNUM rn " + 
				"          FROM (SELECT * " + 
				"                  FROM T_COR_CONTROL_PERSON) a " + 
				"         WHERE ROWNUM <= 10000) " + 
				" WHERE rn >= 1";
		try {
			pst = conn.prepareStatement(sql);
			// 执行语句
			res = pst.executeQuery();
			while (res.next()) {
				personIds.add(res.getInt(1));
			}
			LOGGER.info("------------------------------------"+personIds.size());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			LOGGER.error("查询失败：" + e);
		}
	}
	/**
	 * 查询单位ID
	 * @author qinzhitian<br>
	 * @date 2018年3月16日 上午10:47:42
	 */
	public void getUnitId(Connection conn,PreparedStatement pst, ResultSet res) {
		LOGGER.info("-----------------查询单位ID-------------------");
		String sql = "select PRIKEY from T_SYS_UNIT";
		try {
			pst = conn.prepareStatement(sql);
			// 执行语句
			res = pst.executeQuery();
			while (res.next()) {
				unitIds.add(res.getInt(1));
			}
			LOGGER.info("------------------------------------"+unitIds.size());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			LOGGER.error("查询失败：" + e);
		}
	}
	/**
	 * 生成随机时间
	 * @author qinzhitian<br>
	 * @date 2018年3月13日 下午3:26:01
	 */
	public Date randomDate(String beginDate,String endDate){  
        try {  
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            Date start = format.parse(beginDate);  
            Date end = format.parse(endDate);  
              
            if(start.getTime() >= end.getTime()){  
                return null;  
            }  
              
            long date = random(start.getTime(),end.getTime());  
              
            return new Date(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
	/**
	 * 生成随机时间
	 * @author qinzhitian<br>
	 * @date 2018年3月13日 下午3:26:01
	 */
	public long random(long begin,long end){  
        long rtn = begin + (long)(Math.random() * (end - begin));  
	// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值  
        if(rtn == begin || rtn == end){  
            return random(begin,end);  
        }  
        return rtn;  
    }  

	/**
	 * 获取连接
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
