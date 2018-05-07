package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.server.common.util.SpringUtils;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.Monitor;
import com.ruiec.web.entity.MonitorType;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.MonitorService;
import com.ruiec.web.service.MonitorTypeService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 布控人员服务实现类
 * 
 * @author 陈靖原<br>
 * @date 2018年1月8日 上午10:58:25
 */
@Service("monitorServiceImpl")
public class MonitorServiceImpl extends BaseServiceImpl<Monitor, Integer> implements MonitorService {
	@Resource
	private MonitorTypeService monitorTypeService;
	@Resource
	private UnitService unitService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ControlPersonService controlPersonService;

	/**
	 * 保存布控人员
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午10:59:58
	 */
	@Override
	@Transactional
	public boolean saveMonitor(User user, LoginUserUnit loginUserUnit, Monitor monitor, String Categories,
			Integer unitId, Integer unitId2) {
		try {
			String[] cs = Categories.split(",");
			if (unitId == null && unitId2 == null && monitor.getUserId() != null
					&& monitor.getUserId().equals(user.getId())) {
				if (!"警员".equals(loginUserUnit.getUserName())) {
					if (loginUserUnit.getUnitId() == null) {
						return false;
					}
					monitor.setUnitId(loginUserUnit.getUnitId());
				} else {
					if (user.getUnitId() == null) {
						return false;
					}
					monitor.setUnitId(user.getUnitId());
				}
			}
			if (unitId != null) {
				if (unitId2 == null || unitId2 == -1) {
					monitor.setUnitId(unitId);
				} else {
					monitor.setUnitId(unitId2);
				}
			}
			// 审核状态
			if (!"警员".equals(loginUserUnit.getUserName())) {
				if (!"town".equals(loginUserUnit.getUnitRank().getName())) {
					// 镇级以上管理员直接通过审核
					monitor.setStatus(1);
				} else {
					// 镇级管理员直接通过初核
					monitor.setStatus(3);
				}
				monitor.setApprovalUnitId(loginUserUnit.getUnitId().toString());
				monitor.setApprovalUserId(user.getId().toString());
				monitor.setApprovalTime(RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
			} else {
				// 普通民警未审核
				monitor.setStatus(0);
			}
			// 申请布控人
			monitor.setProposer(user.getId());
			// 是否撤销(默认否)
			monitor.setIsRevoke(0);
			List<Map<String, Object>> list = getDictionary("monitorType");
			// 布控人员类型
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (monitor.getPersonTypeId() != null) {
						if (monitor.getPersonTypeId() == 1 && "重点人员".equals(list.get(i).get("itemName"))) {
							monitor.setPersonTypeId(Integer.valueOf(list.get(i).get("id").toString()));
							break;
						} else if (monitor.getPersonTypeId() == 2 && "关注人员".equals(list.get(i).get("itemName"))) {
							monitor.setPersonTypeId(Integer.valueOf(list.get(i).get("id").toString()));
							break;
						} else if (monitor.getPersonTypeId() == 3 && "外地人员".equals(list.get(i).get("itemName"))) {
							monitor.setPersonTypeId(Integer.valueOf(list.get(i).get("id").toString()));
							break;
						} else if (monitor.getPersonTypeId() == 4 && "布控人员".equals(list.get(i).get("itemName"))) {
							monitor.setPersonTypeId(Integer.valueOf(list.get(i).get("id").toString()));
							break;
						}
					} else {
						if ("布控人员".equals(list.get(i).get("itemName"))) {
							monitor.setPersonTypeId(Integer.valueOf(list.get(i).get("id").toString()));
							break;
						}
					}
				}
			}
			monitor.setIsDelete(0);
			Monitor mon = save(monitor);
			for (String c : cs) {
				MonitorType m = new MonitorType();
				m.setMonitor(mon);
				m.setDictionaryId(Integer.valueOf(c));
				monitorTypeService.save(m);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 修改布控人员
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午10:59:58
	 */
	@Override
	@Transactional
	public boolean updMonitor(User user, LoginUserUnit loginUserUnit, Monitor monitor, String Categories,
			Integer unitId, Integer unitId2) {
		try {
			String[] cs = Categories.split(",");
			if (unitId == null && unitId2 == null && monitor.getUserId() != null
					&& monitor.getUserId().equals(user.getId())) {
				if (!"警员".equals(loginUserUnit.getUserName())) {
					if (loginUserUnit.getUnitId() == null) {
						return false;
					}
					monitor.setUnitId(loginUserUnit.getUnitId());
				} else {
					if (user.getUnitId() == null) {
						return false;
					}
					monitor.setUnitId(user.getUnitId());
				}
			}
			if (unitId != null) {
				if (unitId2 == null || unitId2 == -1) {
					monitor.setUnitId(unitId);
				} else {
					monitor.setUnitId(unitId2);
				}
			}
			// 审核状态
			if (!"警员".equals(loginUserUnit.getUserName())) {
				if (!"town".equals(loginUserUnit.getUnitRank().getName())) {
					// 镇级以上管理员直接通过审核
					monitor.setStatus(1);
				} else {
					// 镇级管理员直接通过初核
					monitor.setStatus(3);
				}
				monitor.setApprovalUnitId(loginUserUnit.getUnitId().toString());
				monitor.setApprovalUserId(user.getId().toString());
				monitor.setApprovalTime(RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
			} else {
				// 普通民警未审核
				monitor.setStatus(0);
			}
			// 申请布控人
			monitor.setProposer(user.getId());
			// 是否撤销(默认否)
			monitor.setIsRevoke(0);
			List<Map<String, Object>> list = getDictionary("monitorType");
			// 布控人员类型
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (monitor.getPersonTypeId() != null) {
						if (monitor.getPersonTypeId() == 1 && "重点人员".equals(list.get(i).get("itemName"))) {
							monitor.setPersonTypeId(Integer.valueOf(list.get(i).get("id").toString()));
							break;
						} else if (monitor.getPersonTypeId() == 2 && "关注人员".equals(list.get(i).get("itemName"))) {
							monitor.setPersonTypeId(Integer.valueOf(list.get(i).get("id").toString()));
							break;
						} else if (monitor.getPersonTypeId() == 3 && "外地人员".equals(list.get(i).get("itemName"))) {
							monitor.setPersonTypeId(Integer.valueOf(list.get(i).get("id").toString()));
							break;
						} else if (monitor.getPersonTypeId() == 4 && "布控人员".equals(list.get(i).get("itemName"))) {
							monitor.setPersonTypeId(Integer.valueOf(list.get(i).get("id").toString()));
							break;
						}
					} else {
						if ("布控人员".equals(list.get(i).get("itemName"))) {
							monitor.setPersonTypeId(Integer.valueOf(list.get(i).get("id").toString()));
							break;
						}
					}
				}
			}
			monitor.setIsDelete(0);
			update(monitor);
			Monitor mon = get(monitor.getId());
			monitorTypeService.delete(Filter.build().add(Filter.eq("monitor", mon)).build());
			for (String c : cs) {
				MonitorType m = new MonitorType();
				m.setMonitor(mon);
				m.setDictionaryId(Integer.valueOf(c));
				monitorTypeService.save(m);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 是否有权限操作改人员
	 * 
	 * @param 人员id,user对象,登录用户管理单位对象
	 * @author Senghor<br>
	 * @date 2017年12月28日 下午4:43:12
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isPower(Integer id, User user, LoginUserUnit loginUserUnit) {
		// 超管直接则允许
		if (user.getId() == 1) {
			return true;
		}
		// 得到当前操作的人员
		Monitor monitor = get(id);
		if (monitor != null) {
			if (loginUserUnit.getUnitIds() != null && loginUserUnit.getUnitIds().size() > 0) {
				for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
					// 市局管理员直接则允许
					if (loginUserUnit.getUnitSonIds().get(i) == 1) {
						return true;
					} else if (loginUserUnit.getUnitSonIds().get(i).equals(monitor.getUnitId())
							|| (monitor.getUserId() != null && monitor.getUserId().equals(user.getId()))) {
						return true;
					}
				}
			} else {
				if (monitor.getUserId() != null && monitor.getUserId().equals(user.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 根据身份证查询已布控人信息(是否存在)
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午10:55:04
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean selByIdCard(String idCard) {
		List<Filter> filters = new ArrayList<Filter>();
		if (StringUtils.isBlank(idCard)) {
			idCard = "";
		}
		filters.add(Filter.eq("idCard", idCard));
		// 该身份证的布控人
		Long count = getCount(filters);
		// 该身份证的重点人
		Long cpcount = controlPersonService.getCount(filters);
		if (count > 0 || cpcount > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据身份证查询布控人信息(个人)
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月9日 下午4:04:27
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> selOneByIdCard(Integer id, Integer ptype) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != ptype && 0 == ptype) {
			ControlPerson cp = controlPersonService.get(id, null, Fetch.join("controlPersonExtend"));
			map.put("name", cp.getName());
			map.put("nationNumber", cp.getControlPersonExtend().getNation());
			map.put("personTypeIdz", cp.getPersonnelType());
			map.put("licensePlateNumber", cp.getControlPersonExtend().getPlateNumber());
			map.put("photo", cp.getControlPersonExtend().getPhoto());
			map.put("contactInfo", cp.getPhone());
			map.put("currentAddress", cp.getControlPersonExtend().getNowLiveArea() == null ? ""
					: cp.getControlPersonExtend().getNowLiveArea());
			map.put("nativeAddress", cp.getControlPersonExtend().getHouseholdRegisterPlace() == null ? ""
					: cp.getControlPersonExtend().getHouseholdRegisterPlace());
			map.put("sex", cp.getSex());
			if (null != cp.getUserId()) {
				User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, cp.getUserId());
				map.put("userId", user.getId());
				map.put("contactsPolice", user.getPhone());
			}
			if (null != cp.getUnitId()) {
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, cp.getUnitId());
				if ("area".equals(unit.getUnitRank())) {
					map.put("unit", unit.getId());
				} else if ("town".equals(unit.getUnitRank())) {
					map.put("unit", unit.getParentId());
					map.put("unit2", unit.getId());
				} else {
					map.put("unit", unit.getId());
				}
			}
			map.put("idCard", cp.getIdCard());
		} else {
			Monitor mo = get(id);
			if (null != mo) {
				map.put("name", mo.getName());
				map.put("nationNumber", mo.getNationNumber());
				map.put("personTypeId", mo.getPersonTypeId());
				map.put("licensePlateNumber", mo.getLicensePlateNumber());
				map.put("photo", mo.getPhoto());
				map.put("contactInfo", mo.getContactInfo());
				map.put("currentAddress", mo.getCurrentAddress());
				map.put("nativeAddress", mo.getNationNumber());
				map.put("sex", mo.getSex());
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, mo.getUnitId());
				if (unit == null) {
					UnitService unitService = (UnitService) SpringUtils.getBean("unitServiceImpl");
					unit = unitService.get(Integer.valueOf(mo.getUnitId()));
				}
				if (unit != null) {
					if ("area".equals(unit.getUnitRank())) {
						map.put("unit", mo.getUnitId());
					} else if ("town".equals(unit.getUnitRank())) {
						map.put("unit", unit.getParentId());
						map.put("unit2", mo.getUnitId());
					} else {
						map.put("unit", mo.getUnitId());
					}
				}
				List<MonitorType> types = mo.getMonitorTypes();
				String type = "";
				String typez = "";
				for (int i = 0; i < types.size(); i++) {
					type += dictionaryService.get(types.get(i).getDictionaryId()).getItemName() + ",";
					typez += types.get(i).getDictionaryId() + ",";
				}
				map.put("personType", type);
				map.put("personTypes", typez);
				map.put("userId", mo.getUserId());
				map.put("duration", mo.getDuration());
				map.put("handleMode", mo.getHandleMode());
				map.put("contactsPolice", mo.getContactInfo());
				map.put("idCard", mo.getIdCard());
				map.put("reason", mo.getReason());
			}
		}
		return map;
	}

	/**
	 * 登录用户是否可以审核该布控人信息
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月9日 下午4:04:27
	 */
	@Override
	@Transactional(readOnly = true)
	public String isAudit(User user, LoginUserUnit loginUserUnit, Monitor monitor) {
		if (null != monitor) {
			// 申请布控的用户的所在单位
			Unit unit = unitService.get(monitor.getUnitId());
			// 管理员才允许审核
			if (null != loginUserUnit.getUnitIds() && loginUserUnit.getUnitIds().size() > 0) {
				// 布控人的审核状态为未审核(只有民警的审核状态为未审核)
				if (monitor.getStatus() == 0) {
					// 派出所管理审核派出所民警
					if ("town".equals(loginUserUnit.getUnitRank().getName())) {
						if (this.isUnitId(loginUserUnit, monitor.getUnitId())) {
							if ("town".equals(unit.getUnitRank())) {
								// 通过则为初审
								return "town";
							}
						}
					} else if ("area".equals(loginUserUnit.getUnitRank().getName())) {
						if (this.isUnitId(loginUserUnit, monitor.getUnitId())) {
							if ("area".equals(unit.getUnitRank())) {
								// 分县局审核分县局民警
								// 通过则为已审核
								return "area";
							}
						}
					} else {
						if (this.isUnitId(loginUserUnit, monitor.getUnitId())) {
							if ("city".equals(unit.getUnitRank())) {
								// 市局审核市局民警
								// 通过则为已审核
								return "city";
							}
						}
					}
				} else if (monitor.getStatus() == 3) {
					// 布控人的审核状态为初审
					if ("area".equals(loginUserUnit.getUnitRank().getName())) {
						if (this.isUnitId(loginUserUnit, monitor.getUnitId())) {
							if ("town".equals(unit.getUnitRank())) {
								// 通过则为已审核
								return "areaTown";
							}
						}
					}
				}
			}
		}
		// 无权限审核
		return "noPower";
	}

	private boolean isUnitId(LoginUserUnit loginUserUnit, Integer unitId) {
		for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
			if (loginUserUnit.getUnitSonIds().get(i).equals(unitId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据字典类型id查询简单字典类型数据
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	private List<Map<String, Object>> getDictionary(String name) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		if (name != null) {
			// 根据类型id查询简单类型数据
			maps = dictionaryService.findByItemCode(name, 0);
		}
		return maps;
	}

	/**
	 * 撤除所有过期信息
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月9日 下午4:04:27
	 */
	@Override
	@Transactional
	public boolean updateIsExpired(List<Integer> list) {
		String hql = "update Monitor m set isRevoke = 1 where m.id in (:list)";
		Query query = getSession().createQuery(hql);
		if (list != null && list.size() > 0) {
            int size = list.size()/1000 + 1;
            for (int i = 0; i < size; i++) {
                if (i+1 == size) {
                    List<Integer> ids = list.subList(i*1000, list.size());
                    query.setParameterList("list", ids);
                    query.executeUpdate();
                }else {
                    List<Integer> ids = list.subList(i*1000, (i+1)*1000);
                    query.setParameterList("list", ids);
                    query.executeUpdate();
                }
            }
			return true;
		}
		return false;
	}
}
