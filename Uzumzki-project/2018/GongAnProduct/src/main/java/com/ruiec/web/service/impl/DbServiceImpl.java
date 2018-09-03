package com.ruiec.web.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.ControlPersonExtend;
import com.ruiec.web.entity.Db;
import com.ruiec.web.entity.DbField;
import com.ruiec.web.entity.DbTable;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.DbFieldService;
import com.ruiec.web.service.DbService;
import com.ruiec.web.service.DbTableService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 数据源服务实现类
 * 
 * @author qinzhitian<br>
 * @date 2017年12月18日 下午4:49:03
 */
@Service("dbServiceImpl")
public class DbServiceImpl extends BaseServiceImpl<Db, Integer> implements DbService {

	private static final Logger LOGGER = Logger.getLogger(DbServiceImpl.class);
	@Resource
	private DbTableService dbTableService;
	@Resource
	private DbFieldService dbFieldService;
	@Resource
	private UnitService unitService;
	@Resource
	private UserService userService;
	@Resource
	private ControlPersonService controlPersonService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ApplicationContext context;
	/*
	 * @Value("${jdbc.driver}") private String driver;
	 * 
	 * @Value("${jdbc.url}") private String url;
	 * 
	 * @Value("${jdbc.username}") private String username;
	 * 
	 * @Value("${jdbc.password}") private String password;
	 */
	// 每次数据导入条数
	private int size = 100;
	// 单位全局变量
	private Map<String, Integer> unitMap = null;
	// 警员全局变量
	private Map<String, Integer> userMap = null;

	/**
	 * 保存数据源
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月18日 下午5:46:15
	 */
	@Override
	@Transactional
	public JsonReturn saveDb(Db db) {
		try {
			Connection conn = getConnection(db);
			if (conn == null) {
				return new JsonReturn(400, "保存失败,无法建立链接");
			} else {
				// 判断数据库地址是否已存在
				List<Filter> filters = new ArrayList<>();
				filters.add(Filter.eq("url", db.getUrl()));
				filters.add(Filter.eq("userName", db.getUserName()));
				if (db.getId() != null) {
					filters.add(Filter.ne("id", db.getId()));
				}
				Db db2 = super.get(filters);
				if (db2!=null && db2.getId()!=null) {
					return new JsonReturn(400, "数据库地址已存在");
				}
			}
			conn.close();
			super.save(db);
			return new JsonReturn(200, "保存成功");
		} catch (Exception e) {
			return new JsonReturn(400, "保存失败,无法建立链接");
		}
	}

	/**
	 * 修改数据源
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月18日 下午5:46:15
	 */
	@Override
	@Transactional
	public JsonReturn updateDb(Db db) {
		try {
			Connection conn = getConnection(db);
			if (conn == null) {
				return new JsonReturn(400, "修改失败,无法建立链接");
			} else {
				// 判断数据库地址是否已存在
				List<Filter> filters = new ArrayList<>();
				filters.add(Filter.eq("url", db.getUrl()));
				filters.add(Filter.eq("userName", db.getUserName()));
				if (db.getId() != null) {
					filters.add(Filter.ne("id", db.getId()));
				}
				Db db2 = super.get(filters);
				if (db2!=null && db2.getId()!=null) {
					return new JsonReturn(400, "数据库地址已存在");
				}
			}
			conn.close();
			super.update(db);
			return new JsonReturn(200, "保存成功");
		} catch (Exception e) {
			return new JsonReturn(400, "修改失败,无法建立链接");
		}
	}

	/**
	 * 检查是否可以建立数据库连接
	 * 
	 * @author qinzhitian<br>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @date 2017年12月18日 下午5:57:54
	 */
	@Override
	public Connection getConnection(Db db){
		Connection conn;
		try {
			conn = null;
			Integer type = db.getType();// 数据库类型
			String urll = db.getUrl();// 数据库链接地址
			String user = db.getUserName();// 用户名
			String pass = db.getPassword();// 密码
			if (type == 1) {
				// 加载mysql驱动程序
				Class.forName("com.mysql.jdbc.Driver");
			} else if (type == 2) {
				// 加载Oracle驱动程序
				Class.forName("oracle.jdbc.OracleDriver");
			}
			// 建立连接
			conn = DriverManager.getConnection(urll, user, pass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("找不到驱动");
		} catch (SQLException e) {
			throw new RuntimeException("数据库连接错误");
		}
		return conn;

	}

	/**
	 * 查询所有启用表信息
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月21日 上午9:56:40
	 * @param id
	 *            数据源id
	 */
	@Override
	@Transactional
	public List<DbTable> getTableList(Integer id) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DbTable.class);
		DetachedCriteria dca = detachedCriteria.createCriteria("db");
		detachedCriteria.add(Restrictions.eq("isUse", 1));
		dca.add(Restrictions.eq("id", id));
		List<DbTable> dbTables = dbTableService.findList(detachedCriteria, null, null, null);
		List<DbTable> dbTableList = new ArrayList<DbTable>();
		for (DbTable dbTable : dbTables) {
			List<DbField> dbFieldList = dbFieldService.findList(null, Filter.eq("dbTable", dbTable), null);
			dbTable.setDbFields(dbFieldList);
			dbTableList.add(dbTable);
		}
		return dbTableList;
	}

	/**
	 * 数据导入(库)
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月21日 上午9:56:40
	 * @param id
	 *            数据源id
	 */
	@Override
	@Transactional
	public JsonReturn dataImport(Integer id, String startDate, String endDate) {
		String msg = "导入数据完毕：";
		try {
			// 查询导入表信息
			List<DbTable> dbTables = getTableList(id);
			// 校验表信息是否完全
			boolean b = dbTableService.checkField(dbTables);
			if (!b) {
				return new JsonReturn(400, "请提供所有必须的字段");
			}
			boolean is_update = false;
			// 导入单位数据
			for (DbTable dbTable : dbTables) {
				if (dbTable.getDbShow().getDbName().equals("T_SYS_UNIT")) {
					Long count = getCount(dbTable, startDate, endDate);
					LOGGER.info("表" + dbTable.getTableName() + "可导入数据为：" + count + "条");
					if (count > 0) {
						saveUnitData(count, dbTable, startDate, endDate);
					}
					LOGGER.info("单位数据导入成功：" + GlobalUnit.successTotal + "条");
					LOGGER.info("单位数据导入失败：" + GlobalUnit.failTotal + "条");
					if (GlobalUnit.successTotal > 0) {
						is_update = true;
					}
					msg = msg + "单位数据导入：" + GlobalUnit.successTotal + "条；";
					GlobalUnit.successTotal = 0;
					GlobalUnit.failTotal = 0;
					// 修改最后一次导入时间
					dbTable.setLastImportTime(new Date());
					dbTableService.update(dbTable);
				}
			}

			// 缓存单位信息
			setUnitMap();
			// 修改单位父级id
			if (is_update) {
				updateUnitParentId();
			}

			// 导入民警数据
			for (DbTable dbTable : dbTables) {
				if (dbTable.getDbShow().getDbName().equals("T_SYS_USER")) {
					Long count = getCount(dbTable, startDate, endDate);
					LOGGER.info("表" + dbTable.getTableName() + "可导入数据为：" + count + "条");
					if (count > 0) {
						saveUserData(count, dbTable, startDate, endDate);
					}
					LOGGER.info("民警数据导入成功：" + GlobalUnit.successTotal + "条");
					LOGGER.info("民警数据导入失败：" + GlobalUnit.failTotal + "条");
					msg = msg + "民警数据导入：" + GlobalUnit.successTotal + "条；";
					GlobalUnit.successTotal = 0;
					GlobalUnit.failTotal = 0;
					// 修改最后一次导入时间
					dbTable.setLastImportTime(new Date());
					dbTableService.update(dbTable);
				}

			}
			// 缓存民警信息
			setUserMap();
			// 导入重点人数据
			for (DbTable dbTable : dbTables) {
				if (dbTable.getDbShow().getDbName().equals("T_COR_CONTROL_PERSON")) {
					Long count = getCount(dbTable, startDate, endDate);
					LOGGER.info("表" + dbTable.getTableName() + "可导入数据为：" + count + "条");
					if (count > 0) {
						saveControlPersonData(count, dbTable, startDate, endDate);
					}
					LOGGER.info("重点人数据导入成功：" + GlobalUnit.successTotal + "条");
					LOGGER.info("重点人数据导入失败：" + GlobalUnit.failTotal + "条");
					msg = msg + "重点人数据导入：" + GlobalUnit.successTotal + "条。";
					GlobalUnit.successTotal = 0;
					GlobalUnit.failTotal = 0;
					// 修改最后一次导入时间
					dbTable.setLastImportTime(new Date());
					dbTableService.update(dbTable);
				}
			}
			clear();
		} catch (SQLException e) {
			LOGGER.error("无法建立链接" + e);
			return new JsonReturn(400, "导入数据失败,无法建立链接，请检查数据库地址是否正确！");
		} catch (ClassNotFoundException e) {
			LOGGER.error("找不到方法" + e);
			return new JsonReturn(400, "导入数据失败,找不到方法");
		} catch (Exception e) {
			LOGGER.error("导入数据失败" + e);
			return new JsonReturn(400, "导入数据失败");
		}
		return new JsonReturn(200, msg);
	}

	/**
	 * 缓存单位编码
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月19日 上午9:02:36
	 */
	@Override
	@Transactional
	public void setUnitMap() {
		unitMap = new HashMap<>();
		List<Unit> units = unitService.getAll();
		for (Unit unit : units) {
			// 单位编码
			String code = Unit.getUnitCode(unit);
			unitMap.put(code, unit.getId());
			// unitMap.put(code, unit);
		}
	}

	/**
	 * 缓存警员信息
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月19日 上午9:04:43
	 */
	@Override
	@Transactional
	public void setUserMap() {
		userMap = new HashMap<>();
		List<User> users = userService.getAll();
		for (User user : users) {
			// 警员身份证
			String idCard = user.getIdCard();
			userMap.put(idCard, user.getId());
			// userMap.put(idCard, user);
		}
	}

	/**
	 * 清除缓存
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月19日 上午9:07:17
	 */
	@Override
	public void clear() {
		if (unitMap != null) {
			unitMap.clear();
		}
		if (userMap != null) {
			userMap.clear();
		}
	}

	/**
	 * 获取创建时间字段名
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 下午1:58:27
	 */
	public String getCreateTime(DbTable dbTable) {
		String createTime = null;
		try {
			List<DbField> dbFields = dbTable.getDbFields();
			for (DbField dbField : dbFields) {
				if ("CREATE_TIME".equals(dbField.getDbShow().getDbName())) {
					createTime = dbField.getFieldName();
				}
			}
		} catch (Exception e) {
			LOGGER.info("创建时间字段名获取失败");
		}
		return createTime;
	}

	/**
	 * 获取主键字段名
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 下午1:58:27
	 */
	public String getPrikey(DbTable dbTable) {
		String createTime = null;
		try {
			List<DbField> dbFields = dbTable.getDbFields();
			for (DbField dbField : dbFields) {
				if ("PRIKEY".equals(dbField.getDbShow().getDbName())) {
					createTime = dbField.getFieldName();
				}
			}
		} catch (Exception e) {
			LOGGER.info("主键字段名获取失败");
		}
		return createTime;
	}

	/**
	 * 查询总条数
	 * 
	 * @author qinzhitian<br>
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 * @date 2017年12月21日 上午9:56:12
	 */
	@Override
	public Long getCount(DbTable dbTable, String startDate, String endDate) throws SQLException, ClassNotFoundException, ParseException {
		Long count = 0L;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		// 获取连接
		conn = getConnection(dbTable.getDb());
		// 创建时间字段名
		String createTime = getCreateTime(dbTable);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String sql = "select count(*) from " + dbTable.getTableName();
		if (dbTable.getDb().getType() == 1) {// mysql
			if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
				// 查询上次导入后创建的数据条数
				if (StringUtils.isNotBlank(createTime) && dbTable.getLastImportTime() != null) {
					sql = sql + " where " + createTime + " >= " + dbTable.getLastImportTime();
				}
			} else {
				// 根据用户给出的起始时间与结束时间查询
				sql = sql + " where ";
				if (StringUtils.isNotBlank(startDate)) {
					sql = sql + createTime + " >= " + dateFormat.parse(startDate);
				}
				if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
					sql = sql + " and ";
				}
				if (StringUtils.isNotBlank(endDate)) {
					sql = sql + createTime + " <= " + dateFormat.parse(endDate);
				}
			}
		} else {// oracle
			if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
				// 查询上次导入后创建的数据条数
				if (StringUtils.isNotBlank(createTime) && dbTable.getLastImportTime() != null) {
					String dataStr = dateFormat.format(dbTable.getLastImportTime());
					sql = sql + " where " + createTime + " >= to_date('" + dataStr + "','yyyy-MM-dd')";
				}
			} else {
				// 根据用户给出的起始时间与结束时间查询
				sql = sql + " where ";
				if (StringUtils.isNotBlank(startDate)) {
					sql = sql + createTime + " >= to_date('" + dateFormat.format(dateFormat.parse(startDate)) + "','yyyy-MM-dd')";
				}
				if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
					sql = sql + " AND ";
				}
				if (StringUtils.isNotBlank(endDate)) {
					sql = sql + createTime + " <= to_date('" + dateFormat.format(dateFormat.parse(endDate)) + "','yyyy-MM-dd')";
				}
			}
		}

		pst = conn.prepareStatement(sql);
		// 执行语句
		res = pst.executeQuery();
		res.next();
		count = res.getLong(1);
		// 关闭连接
		close(conn, pst, res);
		return count;
	}

	/**
	 * 抓取数据
	 * 
	 * @author qinzhitian<br>
	 * @throws Exception
	 * @date 2017年12月23日 下午2:11:00
	 */
	@Override
	public List<Map<String, Object>> getDataList(Connection conn, PreparedStatement pst, ResultSet res, long begin, long end, DbTable dbTable,
			String startDate, String endDate) throws Exception {
		// 创建时间字段名
		String createTime = getCreateTime(dbTable);
		String prikey = getPrikey(dbTable);// 主键
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (dbTable.getDb().getType() == 1) {
			String sql = "select * from " + dbTable.getTableName();
			if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
				// 查询上次导入后创建的数据条数
				if (StringUtils.isNotBlank(createTime) && dbTable.getLastImportTime() != null) {
					sql = sql + " where " + createTime + " >= " + dbTable.getLastImportTime();
				}
			} else {
				// 根据用户给出的起始时间与结束时间查询
				sql = sql + " where ";
				if (StringUtils.isNotBlank(startDate)) {
					sql = sql + createTime + " >= " + dateFormat.parse(startDate);
				}
				if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
					sql = sql + " and ";
				}
				if (StringUtils.isNotBlank(endDate)) {
					sql = sql + createTime + " <= " + dateFormat.parse(endDate);
				}
			}
			if (StringUtils.isNotBlank(prikey)) {
				sql = sql + " order by " + prikey;
			}
			// mysql数据库分页查询
			sql = sql + " limit " + begin + ", " + size;
			pst = conn.prepareStatement(sql);
			// 执行语句
			res = pst.executeQuery();
		} else if (dbTable.getDb().getType() == 2) {
			// oracle数据库分页查询
			String sql = "SELECT * FROM " + "(SELECT a.*, ROWNUM rn FROM " + "(SELECT * FROM " + dbTable.getTableName();
			if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
				// 查询上次导入后创建的数据条数
				if (StringUtils.isNotBlank(createTime) && dbTable.getLastImportTime() != null) {
					String dataStr = dateFormat.format(dbTable.getLastImportTime());
					sql = sql + " WHERE " + createTime + " >= TO_DATE('" + dataStr + "','yyyy-MM-dd')";
				}
			} else {
				// 根据用户给出的起始时间与结束时间查询
				sql = sql + " WHERE ";
				if (StringUtils.isNotBlank(startDate)) {
					sql = sql + createTime + " >= TO_DATE('" + dateFormat.format(dateFormat.parse(startDate)) + "','yyyy-MM-dd')";
				}
				if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
					sql = sql + " AND ";
				}
				if (StringUtils.isNotBlank(endDate)) {
					sql = sql + createTime + " <= TO_DATE('" + dateFormat.format(dateFormat.parse(endDate)) + "','yyyy-MM-dd')";
				}
			}
			// 主键排序
			if (StringUtils.isNotBlank(prikey)) {
				sql = sql + " ORDER BY " + prikey;
			}
			// 分页
			sql = sql + ") a WHERE ROWNUM <= " + end + ") " + "WHERE rn > " + begin;
			pst = conn.prepareStatement(sql);
			// 执行语句
			res = pst.executeQuery();
		}
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// 获取查询结果
		while (res.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 获取一条数据的所有字段值
			for (DbField dbField : dbTable.getDbFields()) {
				map.put(dbField.getDbShow().getDbName(), res.getObject(dbField.getFieldName()));
			}
			// LOGGER.info("保存数据：" + map.toString());
			mapList.add(map);
		}
		// 关闭res与pst
		close(null, pst, res);
		return mapList;
	}

	/**
	 * 保存单位数据
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月20日 下午3:12:08
	 */
	@Override
	//@Transactional
	public boolean saveUnitData(Long count, DbTable dbTable, String startDate, String endDate) {
		SessionFactory sessionFactory = (SessionFactory)
		context.getBean("sessionFactory1");
		// 获取Session
		Session session = sessionFactory.openSession();
		// 开始事务
		Transaction t = session.beginTransaction();
		
		// 获取单位前四位编码
		Unit cityUnit = (Unit)RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, "1");
		String cityCode = cityUnit.getProvinceCode() + cityUnit.getCityCode();

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		try {
			// 获取连接
			conn = getConnection(dbTable.getDb());
			// 总页数
			int pageCount = (int) (count / size);
			if (count % size > 0) {
				pageCount += 1;
			}
			// 导入表字段
			List<DbField> dbFields = new ArrayList<DbField>();
			dbFields = dbTable.getDbFields();
			for (int i = 0; i < pageCount; i++) {
				long begin = i * size;
				long end = (i + 1) * size;
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				// 抓取数据
				mapList = getDataList(conn, pst, res, begin, end, dbTable, startDate, endDate);
				if (mapList.size() > 0) {
					// 缓存插入数据信息
					for (Map<String, Object> map : mapList) {
						GlobalUnit.total++;
						Unit unit = new Unit();
						try {
							for (int j = 0; j < dbFields.size(); j++) {
								DbField dbField = dbFields.get(j);
								String fieldName = dbField.getDbShow().getDbName();
								String value = ObjectUtils.toString(map.get(fieldName));
								if (StringUtils.isBlank(value)) {
									continue;
								}
								if ("UNIT_NAME".equals(fieldName)) {
									// 单位名称
									unit.setUnitName(value);
								} else if ("UNIT_RANK".equals(fieldName)) {
									// 单位级别
									unit.setUnitRank(value);
								} else if ("PARENT_ID".equals(fieldName)) {
									// 父ID
									unit.setParentId(Integer.parseInt(value));
								} else if ("IS_POLICE_SECTION".equals(fieldName)) {
									// 是否警种部门
									unit.setIsPoliceSection(Integer.parseInt(value));
								} else if ("POLICE_TYPES_PARENT_ID".equals(fieldName)) {
									// 警种所属父ID
									unit.setPoliceTypesParentId(Integer.parseInt(value));
								} else if ("CODE".equals(fieldName)) {
									// 一个字段存储的单位编码
									if (StringUtils.isNotBlank(value)) {
										if (value.length()!=12) {
											throw new RuntimeException("非法单位编码");
										} else if(cityCode.equals(value.substring(0,4))) {
											throw new RuntimeException("单位编码与本市编码不匹配");
										}
										unit.setProvinceCode(value.substring(0, 2));
										unit.setCityCode(value.substring(2, 4));
										unit.setAreaCode(value.substring(4, 6));
										unit.setTownCode(value.substring(6, 8));
										unit.setOther1Code(value.substring(8, 10));
										unit.setOther2Code(value.substring(10, 12));
									}
								} else {
									// 多个字段存储的单位编码
									if ("PROVINCE_CODE".equals(fieldName)) {
										unit.setProvinceCode(value);
									} else if ("CITY_CODE".equals(fieldName)) {
										unit.setCityCode(value);
									} else if ("AREA_CODE".equals(fieldName)) {
										unit.setAreaCode(value);
									} else if ("TOWN_CODE".equals(fieldName)) {
										unit.setTownCode(value);
									} else if ("OTHER1_CODE".equals(fieldName)) {
										unit.setOther1Code(value);
									} else if ("OTHER2_CODE".equals(fieldName)) {
										unit.setOther2Code(value);
									}
								}
							}
							if (!"00".equals(unit.getAreaCode()) && !"00".equals(unit.getOther1Code())) {
								GlobalUnit.failTotal++;
								continue;
							}
							// 判断当前单位的级别和警种
							if ("00".equals(unit.getAreaCode())) {
								if ("00".equals(unit.getTownCode())) {
									// 市局
									unit.setIsPoliceSection(0);
									unit.setUnitRank("city");
								} else {
									// 警种
									unit.setIsPoliceSection(1);
									if ("00".equals(unit.getOther1Code())) {// 支队
										unit.setUnitRank("area");
									} else {//大队
										unit.setUnitRank("town");
									}
								}
							} else {
								if ("00".equals(unit.getTownCode())) {
									// 分县局
									unit.setIsPoliceSection(0);
									unit.setUnitRank("area");
								} else {
									// 镇级
									unit.setIsPoliceSection(0);
									unit.setUnitRank("town");
								}
							}
							unitService.save(unit);
							session.flush();
							LOGGER.info("保存单位成功：" + unit.toString());
							GlobalUnit.successTotal++;
						} catch (Exception e) {
							t.rollback();
							t = session.beginTransaction();
							LOGGER.info("保存单位失败："+ e.getMessage() + map.toString());
							GlobalUnit.failTotal++;
						}
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error("插入数据失败" + e);
			return false;
		} finally {
			// 关闭连接
			close(conn, pst, res);
		}
		return true;
	}

	/**
	 * 修改导入单位父级id
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月26日 下午5:09:18
	 */
	@Override
	@Transactional
	public boolean updateUnitParentId() {
		try {
			// 抓取数据
			List<Filter> filters = new ArrayList<>();
			filters.add(Filter.isNull("parentId"));
			filters.add(Filter.isNull("policeTypesParentId"));
			List<Unit> units = unitService.findList(null, filters, null);
			if (units != null && !units.isEmpty() && units.size() > 0) {
				// 缓存插入数据信息
				for (Unit unit : units) {
					String provinceCode = unit.getProvinceCode();
					String cityCode = unit.getCityCode();
					String areaCode = unit.getAreaCode();
					String townCode = unit.getTownCode();
					// 单位编码
					if ("00".equals(areaCode)) {
						if ("00".equals(townCode)) {
							// 市局
							unit.setParentId(0);
						} else {
							if ("00".equals(unit.getOther1Code())) {// 支队
								unit.setParentId(GlobalUnit.CITY_UNIT_ID);
							} else {//大队
								// 区级编码
								String code = provinceCode + cityCode + areaCode + townCode + "0000";
								Integer parentId = MapUtils.getInteger(unitMap, code);
								unit.setPoliceTypesParentId(parentId);
							}
							
						}
					} else {
						if ("00".equals(townCode)) {
							// 分县局
							unit.setParentId(GlobalUnit.CITY_UNIT_ID);
						} else {
							// 区级编码
							String code = provinceCode + cityCode + areaCode + "000000";
							// 镇级
							Integer parentId = MapUtils.getInteger(unitMap, code);
							// 判断是否为警种
							unit.setParentId(parentId);
						}
					}
					try {
						// unitService.updateInclude(unit, new
						// String[]{"parentId"}, null);
						unitService.update(unit);
						getSession().flush();
						LOGGER.info("保存单位父级id成功：" + unit.toString());
					} catch (Exception e) {
						LOGGER.error("修改单位父级id失败" + e);
						LOGGER.info("保存单位父级id失败：" + unit.toString());
					}
					// getSession().flush();

				}
			}
		} catch (Exception e) {
			LOGGER.error("修改单位父级id失败" + e);
			return false;
		}
		return true;
	}

	/**
	 * 保存警员信息
	 * 
	 * @author qinzhitian<br>
	 * @throws Exception
	 * @date 2017年12月25日 上午9:45:07
	 */
	@Override
	//@Transactional
	public boolean saveUserData(Long count, DbTable dbTable, String startDate, String endDate) {
		SessionFactory sessionFactory = (SessionFactory)
		context.getBean("sessionFactory1");
		// 获取Session
		Session session = sessionFactory.openSession();
		// 开始事务
		Transaction t = session.beginTransaction();
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		try {
			// String phone_regex = "1[3|4|5|7|8][0-9]\\d{4,8}$";
			// 获取单位
			if (unitMap == null) {
				setUnitMap();
			}
			// 获取连接
			conn = getConnection(dbTable.getDb());
			// 总页数
			int pageCount = (int) (count / size);
			if (count % size > 0) {
				pageCount += 1;
			}
			// 导入表字段
			List<DbField> dbFields = new ArrayList<DbField>();
			dbFields = dbTable.getDbFields();
			for (int i = 0; i < pageCount; i++) {
				long begin = i * size;
				long end = (i + 1) * size;
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				// 抓取数据
				mapList = getDataList(conn, pst, res, begin, end, dbTable, startDate, endDate);
				if (mapList.size() > 0) {
					// SimpleDateFormat dateFormat= new
					// SimpleDateFormat("yyyy-MM-dd");
					// 缓存插入数据信息
					for (Map<String, Object> map2 : mapList) {
						GlobalUnit.total++;
						User user = null;
						try {
							user = new User();
							for (int j = 0; j < dbFields.size(); j++) {
								DbField dbField = dbFields.get(j);
								String fieldName = dbField.getDbShow().getDbName();
								String value = ObjectUtils.toString(map2.get(fieldName));
								// 性别
								if ("SEX".equals(fieldName)) {
									if (StringUtils.isNotBlank(value)) {
										user.setSex(value);
									} else {
										user.setSex("不详");
									}

								}
								if (StringUtils.isBlank(value)) {
									continue;
								}
								// 警员姓名
								if ("POLICE_NAME".equals(fieldName)) {
									user.setPoliceName(value);
								} else if ("GRADE".equals(fieldName)) {// 级别
									user.setGrade(value);
								} else if ("PHONE".equals(fieldName)) {// 电话
									/*
									 * Pattern p = Pattern.compile(phone_regex);
									 * Matcher m = p.matcher(value); boolean
									 * isMatch = m.matches(); if (isMatch) {
									 */
									user.setPhone(value);
									/*
									 * }else { throw new
									 * RuntimeException("手机号格式错误"); }
									 */
								} else if ("ID_CARD".equals(fieldName)) {// 身份证号
									/*
									 * Pattern p =
									 * Pattern.compile(id_Card_regex); Matcher m
									 * = p.matcher(value); boolean isMatch =
									 * m.matches(); if (isMatch) {
									 */
									user.setIdCard(value);
									/*
									 * }else { throw new
									 * RuntimeException("身份证格式错误"); }
									 */
								} else if ("POLICE_NUMBER".equals(fieldName)) {// 警号
									user.setPoliceNumber(value);
								} else if ("RESERVED_KEYWORDS_ONE".equals(fieldName)) {// 保留关键字1
									user.setReservedKeywordsOne(value);
								} else if ("RESERVED_KEYWORDS_TWO".equals(fieldName)) {// 保留关键字2
									user.setReservedKeywordsTwo(value);
								} else if ("PASSWORD".equals(fieldName)) {// 密码
									user.setPassword(value);
								} else if ("UNIT_ID".equals(fieldName)) {// 单位
									user.setUnitId(unitMap.get(value));
								} else if ("UNIT_CODE".equals(fieldName)) {// 单位编码
									user.setUnitId(((Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_CODE_MAP, value)).getId());
								}
							}
							userService.save(user);
							session.flush();
							GlobalUnit.successTotal++;
							LOGGER.info("保存警员信息成功：姓名：" + user.getPoliceName() + ",性别：" + user.getSex() + ",身份证号：" + user.getIdCard() + ",单位id："
									+ user.getUnitId() + ",级别：" + user.getGrade());
						} catch (Exception e) {
							t.rollback();
							t = session.beginTransaction();
							GlobalUnit.failTotal++;
							LOGGER.info("保存警员信息失败：" + map2.toString());
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("插入警员信息数据失败" + e);
			return false;
		} finally {
			// 关闭连接
			close(conn, pst, res);
		}
		return true;
	}

	/**
	 * 导入重点人
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月25日 下午2:44:41
	 */
	@Override
	//@Transactional
	public boolean saveControlPersonData(Long count, DbTable dbTable, String startDate, String endDate) {
		SessionFactory sessionFactory = (SessionFactory)
		context.getBean("sessionFactory1");
		// 获取Session
		Session session = sessionFactory.openSession();
		// 开始事务
		Transaction t = session.beginTransaction();

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		try {
			// 正则表达式
			// String phone_regex = "1[3|4|5|7|8][0-9]\\d{4,8}$";
			// 获取单位
			if (unitMap == null) {
				setUnitMap();
			}
			// 获取警员信息
			if (userMap == null) {
				setUserMap();
			}
			// 获取连接
			conn = getConnection(dbTable.getDb());
			// 总页数
			int pageCount = (int) (count / size);
			if (count % size > 0) {
				pageCount += 1;
			}
			// 导入表字段
			List<DbField> dbFields = new ArrayList<DbField>();
			// 获取字典数据信息
			dbFields = dbTable.getDbFields();
			for (int i = 0; i < pageCount; i++) {
				long begin = i * size;
				long end = (i + 1) * size;
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				// 抓取数据
				mapList = getDataList(conn, pst, res, begin, end, dbTable, startDate, endDate);
				if (mapList.size() > 0) {
					// 缓存插入数据信息
					for (Map<String, Object> map2 : mapList) {
						GlobalUnit.total++;
						ControlPerson controlPerson = null;
						ControlPersonExtend controlPersonExtend = null;
						try {
							controlPerson = new ControlPerson();
							controlPersonExtend = new ControlPersonExtend();
							for (int j = 0; j < dbFields.size(); j++) {
								DbField dbField = dbFields.get(j);
								String fieldName = dbField.getDbShow().getDbName();
								String value = ObjectUtils.toString(map2.get(fieldName));
								if (StringUtils.isBlank(value)) {
									continue;
								}

								if ("NAME".equals(fieldName)) {// 姓名
									controlPerson.setName(value);
								} else if ("SEX".equals(fieldName)) {// 性别
									controlPerson.setSex(value);
								} else if ("IS_CONTROL".equals(fieldName)) {// 是否在控（1是2否）
									//controlPerson.setIsControl(Integer.parseInt(value));
								} else if ("PHONE".equals(fieldName)) {// 电话
									controlPerson.setPhone(value);
								} else if ("ID_CARD".equals(fieldName)) {// 身份证号
									controlPerson.setIdCard(value);
								} else if ("DANGEROUS_LEVEL".equals(fieldName)) {// 危险级别
									controlPerson.setDangerousLevel(Integer.parseInt(value));
								} else if ("COLUMN_TUBE_STATE".equals(fieldName)) {// 列管状态
									controlPerson.setColumnTubeState(value);
								} else if ("COLUMN_DATE".equals(fieldName)) {// 列管时间
									try {
										controlPerson.setColumnDate(RuiecDateUtils.parse_yyyy_MM_dd(value));
									} catch (Exception e) {
										LOGGER.info("列管时间日期转换错误");
									}
								} else if ("REGISTER_STATE".equals(fieldName)) {// 登记状态
									controlPerson.setRegisterState(value);
								} else if ("PERSONNEL_LEVEL".equals(fieldName)) {// 人员级别
									controlPerson.setPersonnelLevel(Integer.parseInt(value));
								} else if ("COLUMN_TUBE_LEVEL".equals(fieldName)) {// 列控级别
									controlPerson.setColumnTubeLevel(Integer.parseInt(value));
								} else if ("PERSONNEL_TYPE".equals(fieldName)) {// 人员类型
									controlPerson.setPersonnelType(Integer.parseInt(value));
								} else if ("RESPONSIBILITY_POLICE_ID".equals(fieldName)) {// 责任民警主键
									controlPerson.setUserId(userMap.get(value));
								} else if ("IS_AUDIT_KEY_PERSON_COLUMN_TUB".equals(fieldName)) {// 重点人员列管是否审核1是未审核2是审核3是拒绝
									controlPerson.setIsAuditKeyPersonColumnTub(value);
								} else if ("COLUMN_TUBE_MODE".equals(fieldName)) {// 列控方式
									controlPerson.setColumnTubeMode(value);
								} else if ("UNIT_ID".equals(fieldName)) {// 关联单位id
									controlPerson.setUnitId(unitMap.get(value));
								} else if ("ISDELETE".equals(fieldName)) {// 逻辑删除(1.已删除,0.未删除)
									controlPerson.setIsDelete(Integer.parseInt(value));
								} else if ("REMARK".equals(fieldName)) {// 备注
									controlPersonExtend.setRemark(value);
								} else if ("HOUSEHOLD_REGISTER_PLACE".equals(fieldName)) {// 户籍地址
									controlPersonExtend.setHouseholdRegisterPlace(value);
								} else if ("HABITUAL_RESIDENCE".equals(fieldName)) {// 经常居住地
									controlPersonExtend.setHabitualResidence(value);
								} else if ("REASON".equals(fieldName)) {// 事由
									controlPersonExtend.setReason(value);
								} else if ("QQ".equals(fieldName)) {// QQ
									controlPersonExtend.setQq(value);
								} else if ("PLATE_NUMBER".equals(fieldName)) {// 车牌号
									controlPersonExtend.setPlateNumber(value);
								} else if ("WECHAT".equals(fieldName)) {// 微信号
									controlPersonExtend.setWechat(value);
								} else if ("OTHER_ADDRESS".equals(fieldName)) {// 其他地址
									controlPersonExtend.setOtherAddress(value);
								} else if ("PHOTO".equals(fieldName)) {// 照片
									controlPersonExtend.setPhoto(value);
								} else if ("NATIVE_PLACE".equals(fieldName)) {// 籍贯
									controlPersonExtend.setNativePlace(value);
								} else if ("OTHER_DOCUMENT_NUMBER".equals(fieldName)) {// 其他证件号
									controlPersonExtend.setOtherDocumentNumber(value);
								} else if ("NOW_LIVE_AREA".equals(fieldName)) {// 现住地区
									controlPersonExtend.setNowLiveArea(value);
								} else if ("HOUSEHOLD_REGISTER_AREA".equals(fieldName)) {// 户籍地区
									controlPersonExtend.setHouseholdRegisterArea(value);
								} else if ("OCCUPATION".equals(fieldName)) {// 职业
									controlPersonExtend.setOccupation(value);
								} else if ("COMMUNITY".equals(fieldName)) {// 所在社区
									controlPersonExtend.setCommunity(value);
								} else if ("POLICE_COMPREHENSIVE_CONTACT_I".equals(fieldName)) {// 警综联系方式
									controlPersonExtend.setPoliceComprehensiveContactI(value);
								} else if ("BIRTH_DATE".equals(fieldName)) {// 出生日期
									controlPersonExtend.setBirthDate(value);
								}
							}
							// 列管状态
							if (StringUtils.isBlank(controlPerson.getColumnTubeState())) {
								controlPerson.setColumnTubeState("1");
							}
							// 列管时间
							if (controlPerson.getColumnDate() == null) {
								controlPerson.setColumnDate(new Date());
							}
							// 逻辑判断存储的数据
							controlPerson.setIsAuditKeyPersonColumnTub("1");
							controlPersonExtend.setControlPerson(controlPerson);
							// 将重点人扩展放在重点人里进行
							controlPerson.setControlPersonExtend(controlPersonExtend);
							// 默认登记状态
							if (StringUtils.isBlank(controlPerson.getRegisterState())) {
								controlPerson.setRegisterState("1");
							}
							if (!controlPerson.emiptyControlPerson()) {
								// 已下发未完善
								controlPerson.setRegisterState("3");
							}
							if (controlPerson.emiptyControlPerson()) {
								// 已下发已完善
								controlPerson.setRegisterState("4");
							}
							// 保存重点人数据
							controlPersonService.save(controlPerson);

							session.flush();
							LOGGER.info("保存重点人信息成功：姓名：" + controlPerson.getName() + ",性别：" + controlPerson.getSex() + ",身份证号：" + controlPerson.getIdCard());
							GlobalUnit.successTotal++;
						} catch (Exception e) {
							t.rollback();
							t = session.beginTransaction();
							LOGGER.info("保存重点人信息失败：" + map2.toString());
							GlobalUnit.failTotal++;
							LOGGER.error("保存重点人信息失败"+e);
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("插入重点人信息数据失败" + e);
		} finally {
			// 关闭连接
			close(conn, pst, res);
		}
		return true;
	}

	/**
	 * 关闭连接
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月23日 上午9:00:26
	 */
	@Override
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
