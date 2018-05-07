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
 * @author yuankai<br>
 * @date 2018年3月15日 下午6:00:08
 */

public class TABLE_Control_Person_Alarm_Generator {
private static final Logger LOGGER = Logger.getLogger(Test.class);
	
	private int size  =2;
	
	private String url = "jdbc:oracle:thin:@192.168.20.220:1521:orcl";
	
	private String username = "c##gongan10";
	
	private String password = "c##gongan10";
	
	private String[] areas = {"桂林","十堰","深圳","广州","武汉","成都","新疆","北京","天津","上海","长沙","黑龙江","内蒙古","重庆","南京","西安"};
//	private String[] color={"红","蓝","黄","橙"};
//	private String[] issues={"下发","未下发"};
	private String[] names={"航空购票","火车购票","公路汽车客运","省厅进港数据","卡口过车数据","国成进站信息","云从-人脸识别数据","东方网力-人脸识别轨迹数据","海康-人脸识别数据","国成火车购票数据"};
	private List<Integer> unitIds = new ArrayList<>();
	private  List<Integer> personIds = new ArrayList<Integer>();
	private List<Integer> dynamicInfos=new ArrayList<Integer>();
	public void asd(){
		
	}
	public static void main(String[] args) { 
		TABLE_Control_Person_Alarm_Generator test = new TABLE_Control_Person_Alarm_Generator();
		long begintime = System.currentTimeMillis();
		Connection conn = test.getConnection();
		String sql = "insert into T_COR_CONTROL_PERSON_ALARM(PRIKEY,CREATE_TIME,MODIFY_TIME,ID_CARD,WARNING_LEVEL,DYNAMIC_INFO_TYPE_ID,DYNAMIC_INFO_ID,DYNAMIC_INFO_NAME,DISTRIBUTE_STATUS"
        +" ,SIGN_NAME,SIGN_PRIKEY,SIGN_STATUS,LOCAL_UNIT_ID,REMARK,SIGN_TIME,FEEDBACK_TIME,ORIGIN,DESTINATION,CONTROL_PERSON_ID,UNIT_ID)"
        +" values(?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = null;
		ResultSet res = null;
		Random random = new Random();
		test.getPersonId(conn,pst,res);
		test.getUnitId(conn,pst,res);
		test.getDynamicInfo(conn,pst,res);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//	ControlPerson cPerson =new ControlPerson();
		
		int num = 0;
		while (num < 10000) {
			num++;
			try {
				pst = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				// 执行语句
				int id = test.getMaxPrikey(conn, pst, res);
				    Date date = test.randomDate("2018-1-1", "2019-3-1");
		            String dateString = RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(date);
//					int colors=random.nextInt(test.color.length);//预警级别
					int origin = random.nextInt(test.areas.length);//出发地
					int destination = random.nextInt(test.areas.length);//目的地
//					int issues=random.nextInt(test.issues.length);//下发状态
					int gnames=random.nextInt(test.names.length);//动态信息名称
					while (origin==destination) {
						destination = random.nextInt(test.areas.length);
					}
					int type = random.nextInt(9);
					int personId = random.nextInt(test.personIds.size());//重点人ID
					int unitId = random.nextInt(test.unitIds.size());//单位ID
					int dynamicInfoId=random.nextInt(test.dynamicInfos.size());//轨迹ID
					pst.setInt(1, id+1); //id
					pst.setString(2, dateString);//创建时间 
					pst.setString(3, dateString);//修改时间
					IdCardGenerator idCardGenerator = new IdCardGenerator();
		            pst.setString(4, idCardGenerator.generate());//身份证
					pst.setInt(5, 2);//预警级别
					pst.setInt(6,type);   //轨迹类型ID
					pst.setInt(7, test.dynamicInfos.get(dynamicInfoId));//轨迹主键ID
					pst.setString(8, test.names[gnames]);//动态信息名称
					pst.setInt(9,1);//下发状态
					pst.setString(10,"");//签收人
					pst.setString(11, null);//签收id
					pst.setInt(12,2);//签收状态
					pst.setInt(13, test.unitIds.get(unitId));//单位
					pst.setString(14, null);//备注
					pst.setString(15, null); //签收时间
					pst.setString(16, null);//反馈时间
					pst.setString(17, test.areas[origin]); //目的地
					pst.setString(18, test.areas[destination]); //终点
					pst.setInt(19, test.personIds.get(personId)); //重点人ID
					pst.setInt(20, test.unitIds.get(unitId)); //管控责任单位
					pst.executeUpdate(); 
				pst.close();
				LOGGER.info("-----------------插入成功-------------------" + num * test.size);
			} catch (SQLException e) {
				LOGGER.error("插入失败：" + e);
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
	public int getMaxPrikey(Connection conn,PreparedStatement pst, ResultSet res) {
		LOGGER.info("-----------------查询估计信息最大ID-------------------");
		String sql = "select max(PRIKEY) from T_COR_CONTROL_PERSON_ALARM";
		int id = 0;
		try {
			pst = conn.prepareStatement(sql);
			// 执行语句
			res = pst.executeQuery();
			if(res.next()) id = res.getInt(1);
			pst.close();
			LOGGER.info("------------------------------------"+id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			LOGGER.error("查询失败：" + e);
		}
		return id;
	}
	
	public void getPersonId(Connection conn,PreparedStatement pst, ResultSet res) {
		LOGGER.info("-----------------查询重点人ID-------------------");
		//String sql = "select PRIKEY from T_COR_CONTROL_PERSON";
		String sql = "SELECT PRIKEY " + 
				"  FROM (SELECT a.PRIKEY, ROWNUM rn " + 
				"          FROM (SELECT * " + 
				"                  FROM T_COR_CONTROL_PERSON) a " + 
				"         WHERE ROWNUM <= 1000) " + 
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
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
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
