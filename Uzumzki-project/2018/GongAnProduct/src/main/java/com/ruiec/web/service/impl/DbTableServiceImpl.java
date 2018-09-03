package com.ruiec.web.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.InitThread;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.entity.DbField;
import com.ruiec.web.entity.DbShow;
import com.ruiec.web.entity.DbTable;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.DictionaryType;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.DbFieldService;
import com.ruiec.web.service.DbService;
import com.ruiec.web.service.DbShowService;
import com.ruiec.web.service.DbTableService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 数据导入表服务实现类
 * 
 * @author qinzhitian<br>
 * @date 2017年12月18日 下午4:49:03
 */
@Service("dbTableServiceImpl")
public class DbTableServiceImpl extends BaseServiceImpl<DbTable, Integer> implements DbTableService {

	private static final Logger LOGGER = Logger.getLogger(DbTableServiceImpl.class);
	@Resource
	private DbService dbService;
	@Resource
	private DbFieldService dbFieldService;
	@Resource
	private DbShowService dbShowService;
	@Resource
	private UnitService unitService;
	@Resource
	private UserService userService;
	@Resource
	private ControlPersonService controlPersonService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private DictionaryTypeService dictionaryTypeService;
	@Resource
	private ApplicationContext context;

	// 每次数据导入条数
	private int size = 100;
	// 字典数据（有层级）
	List<Dic> dicList = new ArrayList<Dic>();
	// 字典编码对应字典数据
	Map<String, Dic> dicMap = new HashMap<String, Dic>();

	/**
	 * 查询是否已提供所有必须字段
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月28日 上午8:58:23
	 */
	@Override
	@Transactional
	public boolean checkField(List<DbTable> dbTables) {
		for (DbTable dbTable : dbTables) {
			// 查询必须的字段
			List<Filter> filters = new ArrayList<>();
			filters.add(Filter.eq("parentId", dbTable.getDbShow().getId()));
			filters.add(Filter.eq("isNeed", 1));
			List<DbShow> shows = dbShowService.findList(null, filters, null);
			// 用户已提供的字段
			List<DbField> dbFields = dbTable.getDbFields();
			// 去重
			dbFields = new ArrayList<DbField>(new LinkedHashSet<>(dbFields));
			int n = 0;
			for (DbField dbField : dbFields) {
				for (DbShow dbShow : shows) {
					if (dbField.getDbShow().getDbName().equals(dbShow.getDbName())) {
						n++;
					}
				}
			}
			if (n < shows.size()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 导入表
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月19日 下午3:10:06
	 */
	@Override
	//@Transactional
	public JsonReturn tableImport(DbTable dbTable, String startDate, String endDate) {
		String msg = "导入数据完毕：";
		Long count = 0L;
		JsonReturn jsonReturn = null;
		boolean result = true;
		try {
			// 查询导入数据总条数
			count = dbService.getCount(dbTable, startDate, endDate);
			if (count > 0) {
				LOGGER.info("表" + dbTable.getTableName() + "可导入数据为：" + count + "条");
				GlobalUnit.count = count;
			} else {
				return new JsonReturn(200, "无可导入数据");
			}
			if (GlobalUnit.total > 0) {
				GlobalUnit.total = 0;
			}
			String table = dbTable.getDbShow().getDbName();
			if (StringUtils.isBlank(table)) {
				return new JsonReturn(400, "没有表名");
			}
			// 导入字典数据
			if (table.equals("T_SYS_DICTIONARY")) {
				LOGGER.info("-------------------导入字典数据------------------");
				// 获取字典类型
				String code = null;
				// 获取到表的所有字段名
				List<DbField> dbFields = dbTable.getDbFields();
				// 遍历所有字段
				for (DbField dbField : dbFields) {
					// 等于字典类型别名
					if ("ITEMCODE".equals(dbField.getDbShow().getDbName())) {
						code = dbField.getFieldName();
						DbField dbField1 = dbField;
						dbFields.remove(dbField1);
						break;
					}
				}
				if (StringUtils.isBlank(code)) {
					return new JsonReturn(400, "导入数据失败，请输入字典数据类型别名！");
				}
				dbTable.setDbFields(dbFields);
				// 保存字典数据
				jsonReturn = saveDictionary(count, dbTable, startDate, endDate, code);
				// 字典数据不为空
				if (dicList.size() > 0) {
					LOGGER.info("-------------------修改有层级关系的字典数据父级ID------------------");
					// 修改有层级关系的数据
					updateParentId();
					InitThread thread = new InitThread(3);
					new Thread(thread).start();
					dicList.clear();
					dicMap.clear();
				}
				new InitThread(3).run();
			} else if (table.equals("T_SYS_UNIT")) {
				LOGGER.info("-------------------导入单位数据------------------");
				// 导入单位数据
				// 插入数据
				result = dbService.saveUnitData(count, dbTable, startDate, endDate);
				// 缓存单位信息
				dbService.setUnitMap();
				LOGGER.info("-------------------修改单位父级id------------------");
				// 修改单位父级id
				dbService.updateUnitParentId();
				InitThread thread = new InitThread(1);
				new Thread(thread).start();
				// 清除缓存
				dbService.clear();
			} else if (table.equals("T_SYS_USER")) {
				LOGGER.info("-------------------导入警员数据------------------");
				// 导入警员数据
				// 缓存警员信息
				dbService.setUserMap();
				// 插入数据
				result = dbService.saveUserData(count, dbTable, startDate, endDate);
				InitThread thread = new InitThread(2);
				new Thread(thread).start();
				// 清除缓存
				dbService.clear();
			} else if (table.equals("T_COR_CONTROL_PERSON")) {
				LOGGER.info("-------------------导入重点人数据------------------");
				// 导入重点人数据
				// 缓存单位信息
				dbService.setUnitMap();
				// 缓存警员信息
				dbService.setUserMap();
				// 插入数据
				result = dbService.saveControlPersonData(count, dbTable, startDate, endDate);
				// 清除缓存
				dbService.clear();
			}

			// 判断数据导入是否成功
			if (!result) {
				return new JsonReturn(400, "导入数据失败");
			}
			if (jsonReturn != null && jsonReturn.getCode() == 400) {
				return jsonReturn;
			}
			LOGGER.info("导入成功：" + GlobalUnit.successTotal + "条");
			LOGGER.info("导入失败：" + GlobalUnit.failTotal + "条");
			msg = msg + "可导入：" + count + "条，成功导入：" + GlobalUnit.successTotal + "条；";
			//GlobalUnit.successTotal = 0;
			//GlobalUnit.failTotal = 0;
			LOGGER.info("修改最后一次导入时间");
			this.updateLastImportTime(dbTable, endDate);
		} catch (Exception e) {
			LOGGER.error("导入数据失败" + e);
			return new JsonReturn(400, "导入数据失败");
		}
		return new JsonReturn(200, msg);
	}
	
	/**
	 * 修改最后一次导入时间
	 * @author qinzhitian<br>
	 * @date 2018年2月8日 下午2:34:24
	 */
	//@Transactional
	public void updateLastImportTime(DbTable dbTable, String endDate) {
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory1");
		// 获取Session
		Session session = sessionFactory.openSession();
		// 开始事务
		Transaction t = session.beginTransaction();
		try {
			// 修改最后一次导入时间
			if (StringUtils.isNotBlank(endDate)) {
				dbTable.setLastImportTime(RuiecDateUtils.parse_yyyyMMddHHmmss(endDate));
			} else {
				dbTable.setLastImportTime(new Date());
			}
			session.update(dbTable);
		} catch (Exception e) {
			t.rollback();
			LOGGER.error("修改最后一次导入时间失败", e);
		}
	}

	/**
	 * 导入字典数据
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月18日 上午9:20:53
	 */
	//@Transactional
	public JsonReturn saveDictionary(Long count, DbTable dbTable, String startDate, String endDate, String code) {
		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory1");
		// 获取Session
		Session session = sessionFactory.openSession();
		// 开始事务
		Transaction t = session.beginTransaction();
		
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("itemCode", code));
		// 获取字典数据类型
		DictionaryType dictionaryType = dictionaryTypeService.get(filters);
		if (dictionaryType == null) {
			return new JsonReturn(400, "导入数据失败，字典数据类型别名不存在！");
		}
		// 定义连接变量
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		try {
			// 获取连接
			conn = dbService.getConnection(dbTable.getDb());
		} catch (Exception e) {
			LOGGER.error("获取连接失败" + e);
		}
		// 总页数
		int pageCount = (int) (count / size);
		if (count % size > 0) {
			pageCount += 1;
		}
		// 获取所有字段名
		List<DbField> dbFields = dbTable.getDbFields();
		// 分页导入
		for (int i = 0; i < pageCount; i++) {
			// 第一条数据下标
			long begin = i * size;
			// 最后一条数据下标
			long end = (i + 1) * size;
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			// 抓取数据
			try {
				mapList = dbService.getDataList(conn, pst, res, begin, end, dbTable, startDate, endDate);
			} catch (Exception e) {
				LOGGER.error("抓取数据失败" + e);
				return new JsonReturn(400, "抓取数据失败！");
			}
			if (mapList.size() > 0) {
				// 当前页的所有插入数据信息
				for (Map<String, Object> map : mapList) {
					GlobalUnit.total++;
					Dictionary dictionary = new Dictionary();
					Dic dic = new Dic();
					try {
						// 字典编码变量
						String no = null;
						dictionary.setIsDef(0);
						// 字典类型
						dictionary.setDictionaryType(dictionaryType);
						for (int j = 0; j < dbFields.size(); j++) {
							// 获取当前字段名
							String fieldName = dbFields.get(j).getDbShow().getDbName();
							// 根据字段名获取字段值
							String value = ObjectUtils.toString(map.get(fieldName));
							if (StringUtils.isBlank(value)) {
								continue;
							}
							if ("ITEMNAME".equals(fieldName)) {
								// 字典名称
								dictionary.setItemName(value);
							} else if ("ITEMVALUE".equals(fieldName)) {
								// 字典值
								dictionary.setItemValue(value);
							} else if ("SORTCODE".equals(fieldName)) {
								// 字典排序
								if (StringUtils.isNotBlank(value)) {
									dictionary.setSortCode(Integer.parseInt(value));
								} else {
									dictionary.setSortCode(0);
								}
							} else if ("DESCRIPTION".equals(fieldName)) {
								// 字典描述
								dictionary.setDescription(ObjectUtils.firstNonNull(value, GlobalUnit.NULLMSG));
							} else if ("NO".equals(fieldName)) {
								// 字典编码
								if (StringUtils.isBlank(dictionary.getItemValue())) {
									// 字典值没有值则赋值
									dictionary.setItemValue(value);
								}
								no = value;
							}
						}
						if (StringUtils.isBlank(dictionary.getDescription())) {
							// 字典描述没有值则赋值
							dictionary.setDescription(dictionary.getItemName());
						}
						if (dictionary.getSortCode() == null) {
							// 字典排序没有值则赋值
							dictionary.setSortCode(GlobalUnit.successTotal);
						}
						dictionary = dictionaryService.save(dictionary);
						// 立即提交数据
						session.flush();
						// 统计成功条数
						GlobalUnit.successTotal++;
						// 数据有字典编码则为层级数据
						if (StringUtils.isNotBlank(no)) {
							dic.setNo(no);
							dic.setDictionaryId(dictionary.getId());
							// 所有层级关系的字典数据
							dicList.add(dic);
							// 存储一,二级字典数据
							if ("00000000".equals(no.substring(4, 12))) {
								dicMap.put(no, dic);
							}
						}
						LOGGER.info("保存字典成功：字典名称：" + dictionary.getItemName() + "，字典值：" + dictionary.getItemValue() + "，字典排序：" + dictionary.getSortCode()
								+ "，字典描述：" + dictionary.getDescription());
					} catch (Exception e) {
						t.rollback();
						t = session.beginTransaction();
						LOGGER.info("保存字典失败：字典名称：" + dictionary.getItemName() + "，字典值：" + dictionary.getItemValue() + "，字典排序：" + dictionary.getSortCode()
								+ "，字典描述：" + dictionary.getDescription());
						// 统计失败条数
						GlobalUnit.failTotal++;
					}
				}
			}
		}
		dbService.close(conn, pst, res);
		return new JsonReturn(200, "数据导入成功！");
	}

	/**
	 * 修改字典数据父级id
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月18日 下午3:44:32
	 */
	@Transactional
	public void updateParentId() {
		for (Dic dic : dicList) {
			try {
				Dictionary dictionary = new Dictionary();
				String no = dic.getNo();
				Integer id = dic.getDictionaryId();
				String no1 = no.substring(0, 2);// 一级编码
				String no2 = no.substring(2, 4);// 二级编码
				if ("0000000000".equals(no.substring(2, 12))) {
					// 一级字典数据
					dictionary.setId(id);
					dictionary.setSortCode(Integer.parseInt(no1));
					dictionaryService.updateInclude(dictionary, new String[] { "sortCode" }, null);
				} else if ("00000000".equals(no.substring(4, 12))) {
					// 二级字典数据
					no = no1 + "0000000000";// 获取一级完整编码
					dic = dicMap.get(no);// 获取一级字典数据
					if (dic == null) {
						// 删除
						List<Filter> flFilters = new ArrayList<Filter>();
						flFilters.add(Filter.eq("id", id));
						flFilters.add(Filter.eq("parentId", id));
						dictionaryService.delete(flFilters);
						GlobalUnit.successTotal--;
						GlobalUnit.failTotal++;
						continue;
					}
					dictionary.setId(id);
					dictionary.setSortCode(Integer.parseInt(no2));
					dictionary.setParentId(dic.getDictionaryId());
					dictionaryService.updateInclude(dictionary, new String[] { "sortCode", "parentId" }, null);
				} else {
					// 三级字典数据
					no = no1 + no2 + "00000000";// 获取二级完整编码
					dic = dicMap.get(no);// 获取二级字典数据
					if (dic == null) {
						dictionaryService.delete(id);
						GlobalUnit.successTotal--;
						GlobalUnit.failTotal++;
						continue;
					}
					dictionary.setId(id);
					dictionary.setSortCode(Integer.parseInt(no.substring(4, 6)));
					dictionary.setParentId(dic.getDictionaryId());
					dictionaryService.updateInclude(dictionary, new String[] { "sortCode", "parentId" }, null);
				}
			} catch (Exception e) {
				LOGGER.error("修改父级id失败" + e);
			}
		}
	}
}

/**
 * 字典编码对应字典数据内部类
 * 
 * @author qinzhitian<br>
 * @date 2018年1月18日 下午3:50:28
 */
class Dic {
	/** 编码 */
	private String no;
	/** 字典id */
	private Integer dictionaryId;

	/** 编码 */
	public String getNo() {
		return no;
	}

	/** 编码 */
	public void setNo(String no) {
		this.no = no;
	}

	/** 字典id */
	public Integer getDictionaryId() {
		return dictionaryId;
	}

	/** 字典id */
	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
}
