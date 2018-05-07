/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonData;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.APIConfig;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.ControlPersonExtend;
import com.ruiec.web.entity.ControlPersonType;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.DynamicInfo;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.enumeration.WarningColor;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.ControlPersonExtendService;
import com.ruiec.web.service.ControlPersonInstructiService;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DynamicInfoService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 重点人员预警服务接口实现类
 * 
 * @author Senghor<br>
 * @date 2017年11月30日 上午9:01:37
 */
@Service("controlPersonAlarmServiceImpl")
public class ControlPersonAlarmServiceImpl extends BaseServiceImpl<ControlPersonAlarm, Integer> implements ControlPersonAlarmService {

	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ControlPersonService controlPersonService;
    @Resource
    private ControlPersonExtendService controlPersonExtendService;
	@Resource
	private ControlPersonInstructiService controlPersonInstructiService;
	@Resource
	private UnitService unitService;
	@Resource
	private UserService userService;
	@Resource
	private DynamicInfoService dynamicInfoService;

	// 获取时间
	public Date getTimeOfDay(Date date, int hour, int min, int sec, int misec) {
		Calendar day = Calendar.getInstance();
		day.setTime(date);
		day.set(Calendar.HOUR_OF_DAY, hour);
		day.set(Calendar.MINUTE, min);
		day.set(Calendar.SECOND, sec);
		day.set(Calendar.MILLISECOND, misec);
		return day.getTime();
	}
    


	// 获取字典数据
	public List<Map<String, Object>> getDictionary(String name) {
		List<Map<String, Object>> maps = dictionaryService.findByItemCode(name, 0);
		if (name != null) {
			// 根据类型id查询简单类型数据
			maps = dictionaryService.findByItemCode(name, 0);
		}
		return maps;
	}

	
	/**
	 * 解析动态信息
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> getDynamic(String j) {
		JSONObject json = JSONObject.fromObject(j);
		// 标题(键)
		JSONObject jsons = JSONObject.fromObject(json.opt("title"));
		System.out.println();
		Iterator<String> titles = jsons.keys();
		Map<String, Object> map = new HashMap<String, Object>();
		while (titles.hasNext()) {
			// 获得key
			String key = titles.next();
			map.put(key, jsons.getString(key));
		}
		//值(json数组)
		JSONArray js = JSONArray.fromObject(json.opt("value"));
		List<Map<String,Object>> ls = new ArrayList<Map<String,Object>>();
		for (Object object : js) {
			JSONObject obj = JSONObject.fromObject(object);
			Iterator<String> values = obj.keys();
			Map<String,Object> mapz = new HashMap<String,Object>();
			while (values.hasNext()) {
				// 获得keyz  
				String keyz = values.next();
				String value = obj.getString(keyz);
				mapz.put(keyz, value);
				for (String key : map.keySet()) {
					if(value.equals(key)) {
						mapz.put(keyz, map.get(key)); 
					}
				}
			}
			ls.add(mapz);
		}
		return ls;
	}
	/**
	 * 是否有权限进行预警或指令操作
	 * 
	 * @author 陈靖原<br>
	 * @param aid预警编号
	 *            iid指令编号
	 * @date 2017年12月22日 下午10:50:06
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isPower(User user, LoginUserUnit loginUserUnit, Integer aid, String type) {
		if (aid == null) {
			return false;
		}
		// 预警
		ControlPersonAlarm c = get(aid);
		if (c == null) {
			return false;
		}
		if (StringUtils.isBlank(type)) {
			return false;
		}
		// 是否管理单位(身份)
		boolean flag = isManager(loginUserUnit);
		// 是否管理该单位
		boolean isManagerUnit = isManagerUnit(loginUserUnit, c.getControlUnit());
		// 超管
		switch (type) {
		// 反馈
		case "feedback":
			if (c.getSignPrikey() != null && c.getSignPrikey().equals(user.getId())) {
				return true;
			}
			break;
		// 签收
		case "sign":
			if (flag || user.getId() == 1) {
				if (isManagerUnit || user.getId() == 1) {
					return true;
				}
			} else {
				if (c.getControlPerson().getUserId() != null && c.getControlPerson().getUserId().equals(user.getId())) {
					return true;
				}
			}
			break;
		// 查看
		case "look":
			if (flag || user.getId() == 1) {
				if (isManagerUnit || user.getId() == 1) {
					return true;
				}
			} else {
				if (c.getControlPerson().getUserId() != null && c.getControlPerson().getUserId().equals(user.getId())) {
					return true;
				}
			}
			break;
		// 下发
		case "issued":
			if (flag || user.getId() == 1) {
				if (isManagerUnit || user.getId() == 1) {
					return true;
				}
			}
			break;
		default:
			break;
		}
		return false;
	}

	/**
	 * 是否为管理员
	 * @author 陈靖原<br>
	 * @date 2018年1月20日 上午11:30:03
	 */
	private boolean isManager(LoginUserUnit loginUserUnit) {
		if (loginUserUnit != null) {
			if (!"警员".equals(loginUserUnit.getUserName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 管理员是否管理该单位
	 * @author 陈靖原<br>
	 * @date 2018年1月20日 上午11:30:03
	 */
	private boolean isManagerUnit(LoginUserUnit loginUserUnit,Integer unitId) {
		if (null != loginUserUnit.getUnitSonIds() && loginUserUnit.getUnitSonIds().size() > 0) {
			for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
				if (unitId.equals(loginUserUnit.getUnitSonIds().get(i))) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 查询api预警信息列表
	 * @author qinzhitian<br>
	 * @date 2018年1月8日 上午10:28:55
	 */
	@Override
	@Transactional
	public Map<String, Object> findApiAlarmList(Page page, LoginUserUnit loginUserUnit, User loginUser){
		//
		DetachedCriteria cpa = DetachedCriteria.forClass(ControlPersonAlarm.class);
		DetachedCriteria cpn = cpa.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
		cpn.setFetchMode("controlPersonTypes", FetchMode.SELECT);
		// 根据登录用户身份查看对应数据
		if (null != loginUserUnit) {
			if (!"警员".equals(loginUserUnit.getUserName())) {
				if ("city".equals(loginUserUnit.getUnitRank().getName())) {
					// 市局管理员
				} else {
					// 管理员
					cpa.add(Restrictions.in("controlUnit", loginUserUnit.getUnitSonIds().toArray()));
				}
			} else {
				cpn.add(Restrictions.eq("userId", loginUser.getId()));
			}
		} else {
			// 警员
			cpn.add(Restrictions.eq("userId", loginUser.getId()));
		}
		// 尚未被逻辑删除的重点人
		cpn.add(Restrictions.eq("isDelete", 0));
		// 分页
		findByPage(page, cpa);
		// 重新封装结果集
		Map<String, Object> map = entityToMap(page, loginUser);
		return map;
	}
	
	/**
	 * 查询api预警信息详情
	 * @author qinzhitian<br>
	 * @date 2018年1月8日 上午10:28:55
	 */
	@Override
	@Transactional
	public Map<String, Object> getMapById(Integer id){
		Map<String, Object> map = new HashMap<>();
		// 预警信息详情
		ControlPersonAlarm controlPersonAlarm = super.get(id);
		// 轨迹详情
		DynamicInfo dynamicInfo = dynamicInfoService.get(controlPersonAlarm.getDynamicInfoId());
        List<JsonData> jsonDatas=new ArrayList<JsonData>();
		if (dynamicInfo != null) {
			// 轨迹类型
			Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, dynamicInfo.getType());
			if (dictionary==null) {
				dictionary = dictionaryService.get(dynamicInfo.getType());
			}
			//标题集合
			List<String> titles=new ArrayList<String>();
			//将json字符串转换为json格式
			JSONObject json = JSONObject.fromObject(dynamicInfo.getInformation().toString());
	        //获取title标题数据
			JSONObject titleJsonObject = (JSONObject) json.get("title");
	        //获取value内容数据集合
			JSONArray valueJsonObject =  json.getJSONArray("value");
	        //迭代标题数据
	        for (Iterator<?> iter = titleJsonObject.keys(); iter.hasNext();) {
				// 获取标题的key
				String key = (String) iter.next();
				// 根据标题的key获取value
				String value = titleJsonObject.get(key).toString();
				// 给标题集合赋值
				titles.add(value);

	        }
	        //将标题对应的值获取到
	        for (int j = 0; j < valueJsonObject.size(); j++) {
	        	JsonData jsonData=(JsonData) JSONObject.toBean(valueJsonObject.getJSONObject(j), JsonData.class);
	        	jsonDatas.add(jsonData);
	        }
	        map.put("dynamicInfo", jsonDatas);
	        if (dictionary==null) {
	            map.put("dynamicInfoName", GlobalUnit.NULLMSG);
            }else {
                map.put("dynamicInfoName", ObjectUtils.firstNonNull(dictionary.getItemName(), GlobalUnit.NULLMSG));
            }
		} else {
            map.put("dynamicInfo", jsonDatas);
			map.put("dynamicInfoName", GlobalUnit.NULLMSG);
		}
		map.put("id", controlPersonAlarm.getId());
		// 预警级别
		Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonAlarm.getWarningLevel());
		if (dictionary == null) {
			dictionary = dictionaryService.get(Integer.parseInt(controlPersonAlarm.getWarningLevel()));
		}
		if (dictionary == null) {
			map.put("warningLevelColor", GlobalUnit.NULLMSG);
			map.put("warningLevelValue", GlobalUnit.NULLMSG);
		} else {
			map.put("warningLevelColor", dictionary.getItemName());
			map.put("warningLevelValue", WarningColor.getValue(dictionary.getItemName()));
		}
		
		map.put("signTime", ObjectUtils.firstNonNull(controlPersonAlarm.getSignTime(), GlobalUnit.NULLMSG));
		map.put("feedbackTime", ObjectUtils.firstNonNull(controlPersonAlarm.getFeedbackTime(), GlobalUnit.NULLMSG));
		map.put("createTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(controlPersonAlarm.getCreateDate()));
		return map;
	}
	
	/**
	 * 查询历史预警记录
	 * @author qinzhitian<br>
	 * @date 2018年1月8日 下午8:11:36
	 */
	@Override
	@Transactional
	public Map<String, Object> findAlarmHistorical(Page page,Integer id, User user){
		//根据重点人Id查询所有预警
		DetachedCriteria cpa = DetachedCriteria.forClass(ControlPersonAlarm.class);
		DetachedCriteria cp = cpa.createCriteria("controlPerson",JoinType.LEFT_OUTER_JOIN);
		//尚未被逻辑删除的重点人
		cp.add(Restrictions.eq("isDelete",0));
		cp.add(Restrictions.eq("id",id));
		cp.setFetchMode("controlPersonTypes", FetchMode.SELECT);
		super.findByPage(page,cpa);
		// 重新封装结果集
		Map<String, Object> map = entityToMap(page, user);
		return map;
	}
	
	/**
	 * 重新封装预警列表结果集
	 * @author qinzhitian<br>
	 * @date 2018年1月8日 下午8:23:41
	 */
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String, Object> entityToMap(Page page, User loginUser){
		Map<String, Object> resultMap = new HashMap<>();//最终返回数据
		List<Map<String, Object>> mapList = new ArrayList<>();//预警数据集合
		Dictionary dictionary = null;
		List<ControlPersonAlarm> alarms = (List<ControlPersonAlarm>)page.getList();
		if (alarms != null && alarms.size() > 0) {
			for (ControlPersonAlarm controlPersonAlarm : alarms) {
				Map<String, Object> map = new HashMap<String, Object>();
				String cpt_str = "";// 人员类别
				map.put("id", controlPersonAlarm.getId());
				map.put("name", ObjectUtils.firstNonNull(controlPersonAlarm.getControlPerson().getName(),GlobalUnit.NULLMSG));
				map.put("idCard", ObjectUtils.firstNonNull(controlPersonAlarm.getIdCard(),GlobalUnit.NULLMSG));
				//map.put("createTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(controlPersonAlarm.getCreateDate()));
				
				// 人员类别
				List<ControlPersonType> cptList = controlPersonAlarm.getControlPerson().getControlPersonTypes();
				if (cptList != null && cptList.size() > 0) {
					for (ControlPersonType controlPersonType : cptList) {
						dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP,controlPersonType.getDictionaryId());
						if (dictionary == null) {
							dictionary = dictionaryService.get(controlPersonType.getDictionaryId());
						}
						cpt_str = cpt_str + dictionary.getItemName() + ",";
					}
					// 移除最后一个，
					cpt_str = cpt_str.substring(0, cpt_str.length() - 1);
				}
				map.put("personnelType", cpt_str == null ? GlobalUnit.NULLMSG : cpt_str == "" ? GlobalUnit.NULLMSG : cpt_str);
				
				// 预警级别
				dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonAlarm.getWarningLevel());
				if (dictionary == null) {
					dictionary = dictionaryService.get(Integer.parseInt(controlPersonAlarm.getWarningLevel()));
				}
				if (dictionary == null) {
					map.put("warningLevelColor", GlobalUnit.NULLMSG);
					map.put("warningLevelValue", GlobalUnit.NULLMSG);
				} else {
					map.put("warningLevelColor", dictionary.getItemName());
					map.put("warningLevelValue", WarningColor.getValue(dictionary.getItemName()));
				}
				// 预警状态
				if (loginUser != null) {
					// 判断预警信息是否已签收
					if (controlPersonAlarm.getSignStatus() == 2) {
						// 判断预警信息是否为当前用户签收
						if (loginUser.getId() == controlPersonAlarm.getSignPrikey()) {
							// 判断预警信息是否已反馈
							if (StringUtils.isNotBlank(controlPersonAlarm.getFeedbackTime())) {
								map.put("state", "已反馈");
							} else {
								map.put("state", "未反馈");
							}
						}
					} else {
						map.put("state", "未签收");
					}
				}
				//信息类别（轨迹类型）
				APIConfig apiConfig = (APIConfig) RedisUtil.getRedisOne(GlobalUnit.API_CONFIG_MAP, controlPersonAlarm.getDynamicInfoId());
				if (apiConfig != null) {
                    map.put("dynamicInfoName", apiConfig.getName());
                }else {
                    map.put("dynamicInfoName", GlobalUnit.NULLMSG);
                }
                map.put("createTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(controlPersonAlarm.getCreateDate()));
                ControlPersonExtend controlPersonExtend = controlPersonExtendService.get(controlPersonAlarm.getControlPerson().getId());
                map.put("photo", controlPersonExtend == null ? "" : ObjectUtils.firstNonNull(controlPersonExtend.getPhoto(),""));
                if (loginUser.getId().equals(controlPersonAlarm.getSignPrikey())) {
                    map.put("type", 1);
                }else {
                    map.put("type", 0);
                }
				mapList.add(map);
			}
		}
		resultMap.put("mapList", mapList);
		resultMap.put("pageSize", page.getPageSize());
		resultMap.put("pageNumber", page.getPageNumber());
		resultMap.put("totalCount", page.getTotalCount());
		return resultMap;
	}
	/**
	 * 系统查询api预警信息列表
	 * @author yk<br>
	 * @date 2018年1月8日 上午10:28:55
	 */
	@Override
	@Transactional
	public Map<String, Object> controlPersonAlarmList(Page page,LoginUserUnit loginUserUnit,String searchType,String search){
		DetachedCriteria cpa = DetachedCriteria.forClass(ControlPersonAlarm.class);
		DetachedCriteria cpn = cpa.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
		cpn.createCriteria("controlPersonExtend", JoinType.LEFT_OUTER_JOIN);
		cpn.setFetchMode("controlPersonTypes", FetchMode.SELECT);
 	    if ("name".equals(searchType)) {
 	    	cpn.add(Restrictions.like("name", search, MatchMode.ANYWHERE));
 		}else if ("idCard".equals(searchType)) {
			//cpn.add(Restrictions.like("idCard", searchType,MatchMode.ANYWHERE));
			cpa.add(Restrictions.eq("idCard", search));
			/*page.add(Filter.eq("idCard", searchType));*/
		}else {
		    return null;
        }
		// 分页
		findByPage(page,cpa);
		// 重新封装结果集
		Map<String, Object> map = controlPersonAlarmMap(page);
		return map;
	}
	/**
	 * 系统查询预警信息封装预警列表结果集
	 * @author yuankai<br>
	 * @date 2018年1月8日 下午8:23:41
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String, Object> controlPersonAlarmMap(Page page){
		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, Object>> mapList = new ArrayList<>();
		Map<String, Object> map = null;
		Dictionary dictionary = null;
		String cpt_str = null;    //人员类别
		List<ControlPersonAlarm> alarms = (List<ControlPersonAlarm>)page.getList();
		if (alarms != null && alarms.size() > 0) {
			for (ControlPersonAlarm controlPersonAlarm : alarms) {
				map = new HashMap<String, Object>();
				cpt_str = new String();
				map.put("id", controlPersonAlarm.getId());//预警的主键
				map.put("idCard", ObjectUtils.firstNonNull(controlPersonAlarm.getIdCard(),GlobalUnit.NULLMSG));//预警人的身份证号
				map.put("name", ObjectUtils.firstNonNull(controlPersonAlarm.getControlPerson().getName(),GlobalUnit.NULLMSG));//预警人的姓名
				map.put("createTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(controlPersonAlarm.getCreateDate()));//预警时间
				// 人员类型
				List<ControlPersonType> cptList = controlPersonAlarm.getControlPerson().getControlPersonTypes();
				if (cptList != null && cptList.size() > 0) {
					for (ControlPersonType controlPersonType : cptList) {
						dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonType.getDictionaryId());
						if (dictionary == null) {
							dictionary = dictionaryService.get(controlPersonType.getDictionaryId());
						}
						cpt_str = cpt_str + dictionary.getItemName() + ",";
					}
					// 移除最后一个，
					cpt_str = cpt_str.substring(0, cpt_str.length() - 1);
				}
				map.put("personnelType", ObjectUtils.firstNonNull(cpt_str,GlobalUnit.NULLMSG));
				
				// 预警级别
				dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonAlarm.getWarningLevel());
				if (dictionary == null) {
					dictionary = dictionaryService.get(Integer.parseInt(controlPersonAlarm.getWarningLevel()));
				}
				if (dictionary == null) {
					map.put("warningLevelColor", GlobalUnit.NULLMSG);
				} else {
					map.put("warningLevelColor", dictionary.getItemName());
				}
				map.put("photo", ObjectUtils.firstNonNull(controlPersonAlarm.getControlPerson().getControlPersonExtend().getPhoto(),""));//人员头像
				map.put("type", controlPersonAlarm.getDistributeStatus() == 0 ? "未下发" : "已下发");//状态
				mapList.add(map);
			}
		}
		resultMap.put("mapList", mapList);
		resultMap.put("pageNumber", page.getPageNumber());
		resultMap.put("pageSize", page.getPageSize());
		resultMap.put("totalCount", page.getTotalCount());
		return resultMap;
	}
      
	/**
	 * 查询重点人信息
	 * @author qinzhitian<br>
	 * @date 2018年1月13日 下午1:47:28
	 */
	@Override
	@Transactional
	public Map<String, Object> findPersonById(Integer id) {
		Map<String, Object> map = new HashMap<>();
		ControlPersonAlarm controlPersonAlarm = super.get(id, null,
				Fetch.alias("controlPerson", "controlPersonAlias", JoinType.LEFT_OUTER_JOIN),
				Fetch.alias("controlPersonAlias.controlPersonExtend", "controlPersonExtendAlias", JoinType.LEFT_OUTER_JOIN));
		ControlPerson controlPerson = controlPersonAlarm.getControlPerson();
		if (controlPerson != null) {
			map.put("photo", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getPhoto(), ""));
			map.put("name", controlPerson.getName());
			map.put("phone", ObjectUtils.firstNonNull(controlPerson.getPhone(), GlobalUnit.NULLMSG));
			map.put("idCard", controlPerson.getIdCard());
			map.put("sex", controlPerson.getSex());
			// 入库时间
			map.put("createTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(controlPerson.getCreateDate()));
			// 户籍地区
			map.put("householdRegisterPlace", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getHouseholdRegisterPlace(), GlobalUnit.NULLMSG));
			// 现住地区
			map.put("nowLiveArea", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getNowLiveArea(), GlobalUnit.NULLMSG));
			// 现住地区
			map.put("nativePlace", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getNativePlace(), GlobalUnit.NULLMSG));
			// 民族
			Dictionary dictionary = null;
			/*if (controlPerson.getControlPersonExtend().getNation() != null) {
				dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPerson.getControlPersonExtend().getNation());
				if (dictionary == null) {
					dictionary = dictionaryService.get(controlPerson.getControlPersonExtend().getNation());
				}
				if (dictionary != null) {
					map.put("nation", dictionary.getItemName());
				} else {
					map.put("nation", GlobalUnit.NULLMSG);
				}
			} else {
				map.put("nation", GlobalUnit.NULLMSG);
			}*/
			// 人员类别
			List<ControlPersonType> list = controlPerson.getControlPersonTypes();
			String personTypes = new String();
			if (list != null && list.size() > 0) {
				for (ControlPersonType controlPersonType : list) {
					dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonType.getDictionaryId());
					if (dictionary == null) {
						dictionary = dictionaryService.get(controlPersonType.getDictionaryId());
					}
					personTypes = personTypes + dictionary.getItemName() + ",";
				}
				// 移除最后一个，
				personTypes = personTypes.substring(0, personTypes.length() - 1);
			} else {
				personTypes = GlobalUnit.NULLMSG;
			}
			map.put("controlPersonTypes", personTypes);
			// 单位名称
			if (controlPerson.getUnitId()!=null) {
				Unit unit = (Unit)RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, controlPerson.getUnitId());
				if (unit == null) {
					unit = unitService.get(controlPerson.getUnitId());
				}
				map.put("unitName", unit.getUnitName());
			} else {
				map.put("unitName",GlobalUnit.NULLMSG );
			}
			if (controlPerson.getUserId() != null) {
				User user = (User)RedisUtil.getRedisOne(GlobalUnit.USER_MAP, controlPerson.getUserId());
				if (user == null) {
					user = userService.get(controlPerson.getUserId());
				}
				map.put("policeName", user.getPoliceName());
				map.put("policePhone", user.getPhone());
			} else {
				map.put("policeName",GlobalUnit.NULLMSG );
				map.put("policePhone", GlobalUnit.NULLMSG);
			}
			map.put("reason", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getReason(), GlobalUnit.NULLMSG));
			map.put("columnTub", "1".equals(controlPerson.getIsAuditKeyPersonColumnTub()) ? "未审核" : "2".equals(controlPerson.getIsAuditKeyPersonColumnTub()) ? "已审核" : "未通过审核");
			// 危险级别中文
            Dictionary dangerousLevel = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPerson.getDangerousLevel());
            if (dangerousLevel != null) {
                map.put("dangerousLevel", dangerousLevel.getItemName());
            } else {
                map.put("dangerousLevel", GlobalUnit.NULLMSG);
            }
		}
		return map;
	}

	/**
	 * 根据签收和反馈状态查询
	 * @author Senghor<br>
	 * @date 2018年4月10日 下午3:50:11
	 */
	@Override
	@Transactional
	public Map<String, Object> findByTypeApiAlarmList(Page page, LoginUserUnit loginUserUnit, User loginUser,String type){
		DetachedCriteria cpa = DetachedCriteria.forClass(ControlPersonAlarm.class);
		DetachedCriteria cpn = cpa.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
		cpn.setFetchMode("controlPersonTypes", FetchMode.SELECT);
		// 根据登录用户身份查看对应数据
		if (null != loginUserUnit) {
			if (!"警员".equals(loginUserUnit.getUserName())) {
				if ("city".equals(loginUserUnit.getUnitRank().getName())) {
					// 市局管理员
				} else {
					// 管理员
					cpa.add(Restrictions.in("controlUnit", loginUserUnit.getUnitSonIds().toArray()));
				}
			} else {
				cpn.add(Restrictions.eq("userId", loginUser.getId()));
			}
		} else {
			// 警员
			cpn.add(Restrictions.eq("userId", loginUser.getId()));
		}
		// 尚未被逻辑删除的重点人
		cpn.add(Restrictions.eq("isDelete", 0));
		if (type !=null && "0".equals(type)) {
			//查询待签收预警
			cpa.add(Restrictions.eq("signStatus", 1));
	        //查询尚未被签收的数据
	        cpa.add(Restrictions.isNull("signPrikey"));
            cpa.addOrder(Order.desc("createDate"));
		}else {
			//查询待反馈预警
			cpa.add(Restrictions.eq("signStatus", 2));
            cpa.add(Restrictions.eq("signPrikey",loginUser.getId()));//签收人必须是自己
			cpa.add(Restrictions.isNull("feedbackTime"));
			cpa.addOrder(Order.desc("signTime"));
		}
		// 分页
		findByPage(page, cpa);
		// 重新封装结果集
		Map<String, Object> map = entityToMap(page, loginUser);
		return map;
	}
}
