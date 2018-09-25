package com.ruiec.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.sql.JoinType;
import org.owasp.esapi.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.server.common.util.SpringUtils;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.Dictionary;
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
    public boolean saveMonitor(User user, LoginUserUnit loginUserUnit, Monitor monitor, String Categories, Integer unitId, Integer unitId2) {
        try {
            String[] cs = Categories.split(",");
            if (unitId == null && unitId2 == null && monitor.getUserId() != null && monitor.getUserId().equals(user.getId())) {
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
            if (unitId2 == null) {
                monitor.setUnitId(unitId);
            } else {
                monitor.setUnitId(unitId2);
            }
            // 申请布控人
            monitor.setProposer(user.getId());
            // 是否撤销(默认否)
            monitor.setIsRevoke(0);
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
    public boolean updMonitor(User user, LoginUserUnit loginUserUnit, Monitor monitor, Integer days) {
        try {
            // 是否撤销(默认否)
            monitor.setIsRevoke(0);
            monitor.setDuration(days.toString());
            monitor.setApprovalTime(RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
            update(monitor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 是否有权限操作改人员
     * 
     * @param 人员id
     *            ,user对象,登录用户管理单位对象
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
            map.put("currentAddress", cp.getControlPersonExtend().getHabitualResidence());
            map.put("nativeAddress", cp.getControlPersonExtend().getHouseholdRegisterPlace());
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
                if ("area".equals(unit.getUnitRank())) {
                    map.put("unit", mo.getUnitId());
                } else if ("town".equals(unit.getUnitRank())) {
                    map.put("unit", unit.getParentId());
                    map.put("unit2", mo.getUnitId());
                } else {
                    map.put("unit", mo.getUnitId());
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
        query.setParameterList("list", list);
        if (list != null && list.size() > 0) {
            int count = query.executeUpdate();
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询属于我的人员布控列表
     * 
     * @param
     * @return
     * @exception
     * @author Senghor<br>
     * @date 2018年4月10日 下午4:45:54
     */
    @Override
    @Transactional
    public Map<String, Object> findByPage(Page page, User loginUser, LoginUserUnit loginUserUnit, String type) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        DetachedCriteria m = DetachedCriteria.forClass(Monitor.class);
        // 判断当前用户是否是超管和市局管理员
        if (loginUser.getId() != 1 || !"city".equals(loginUserUnit.getUnitRank().getName())) {
            if (loginUserUnit.getUnitRank() == null) {
                page.add(Filter.eq("userId", loginUser.getId()));
            } else {
                page.add(Filter.in("unitId", loginUserUnit.getUnitSonIds().toArray()));
            }
        }
        page.add(Sort.desc("createDate"));
        page.add(Filter.eq("isDelete", 0));// 查询未删除的数据
        if ("0".equals(type)) {
            page.add(Filter.eq("isRevoke", 0));// 查询正在布控的数据
        } else if ("1".equals(type)) {
            page.add(Filter.eq("isRevoke", 1));// 查询已撤销布控的数据
        }
        m.setFetchMode("monitorTypes", FetchMode.SELECT);
        super.findByPage(page,m);
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < page.getList().size(); i++) {
            Monitor monitor = (Monitor) page.getList().get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            // id
            map.put("id", monitor.getId());
            // 姓名
            map.put("name", ObjectUtils.firstNonNull(monitor.getName(), GlobalUnit.NULLMSG));
            // 身份证号
            map.put("idCard", ObjectUtils.firstNonNull(monitor.getIdCard(), GlobalUnit.NULLMSG));
            // 布控人
            User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, monitor.getProposer());
            map.put("policeName", user != null ? user.getPoliceName() : GlobalUnit.NULLMSG);
            // 布控状态
            map.put("status", monitor.getStatus() == 0 ? "未审核" : monitor.getStatus() == 1 ? "已审核" : monitor.getStatus() == 2 ? "审核拒绝"
                    : monitor.getStatus() == 3 ? "初审" : "暂无");
            if (monitor.getIsRevoke() == null || (monitor.getStatus()!= 1 && monitor.getIsRevoke() != 1) && (monitor.getStatus() != 2 && monitor.getIsRevoke() != 0)) {
                // 布控期限
                map.put("duration", "0天");
            }else if (StringUtils.isBlank(monitor.getApprovalTime())) {
                // 布控期限
                map.put("duration", "0天");
            }else if (monitor.getDuration() == null || "0" == monitor.getDuration()) {
                // 布控期限
                map.put("duration", "0天");
            }else {
                String[] times = monitor.getApprovalTime().split(",");
                String a = times[times.length - 1];
                // 审批时间
                Date pDate = null;
                try {
                    pDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(a);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // 过期时间
                Date overDate = RuiecDateUtils.getNDaysTime(pDate, Integer.valueOf(monitor.getDuration()));
                // 过期天数
                long day = RuiecDateUtils.getTimeDifference(new Date(), overDate, 3);
                day = day + 1;
                if (day < 0) {
                    day = 0;
                }
                String overDay = String.valueOf(day);
                // 布控期限
                map.put("duration", overDay + "天");
            }
            if (loginUser.getId().equals(monitor.getProposer())) {
                map.put("type", monitor.getIsRevoke());
            }else {
                map.put("type", 2);
            }
            // 布控时间
            map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(monitor.getCreateDate()));
            maps.add(map);
        }
        returnMap.put("mapList", maps);
        returnMap.put("pageNumber", page.getPageNumber());
        returnMap.put("totalCount", page.getTotalCount());
        return returnMap;
    }

    /**
     * 根据id查询重点人基本信息
     * 
     * @author qinzhitian<br>
     * @date 2018年1月12日 上午11:11:51
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> findMapById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Monitor monitor = super.get(id);
        // 重新封装结果集
        /** ----------- 个人信息--------------- **/
        map.put("id", monitor.getId());
        map.put("name", ObjectUtils.firstNonNull(monitor.getName(), GlobalUnit.NULLMSG));
        map.put("photo", ObjectUtils.firstNonNull(monitor.getPhoto(), ""));
        map.put("contactInfo", ObjectUtils.firstNonNull(monitor.getContactInfo(), GlobalUnit.NULLMSG));// 联系方式
        map.put("idCard", ObjectUtils.firstNonNull(monitor.getIdCard(), monitor.getIdCard(), GlobalUnit.NULLMSG));
        map.put("sex", ObjectUtils.firstNonNull(monitor.getSex(), GlobalUnit.NULLMSG));// 性别
        // 民族
        Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, monitor.getNationNumber());
        if (dictionary != null) {
            map.put("nation", dictionary.getItemName());
        } else {
            map.put("nation", GlobalUnit.NULLMSG);
        }
        map.put("nativeAddress", ObjectUtils.firstNonNull(monitor.getNativeAddress(), GlobalUnit.NULLMSG));// 户籍地址
        map.put("currentAddress", ObjectUtils.firstNonNull(monitor.getCurrentAddress(), GlobalUnit.NULLMSG));// 现住地址
        /** ----------- 布控信息--------------- **/
        // 布控单位
        Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, monitor.getUnitId());
        if (unit != null) {
            map.put("unitName", unit.getUnitName());
        } else {
            map.put("unitName", GlobalUnit.NULLMSG);
        }
        // 联系民警
        User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, monitor.getUserId());
        if (user != null) {
            map.put("policeName", user.getPoliceName());
        } else {
            map.put("policeName", GlobalUnit.NULLMSG);
        }
        map.put("contactsPolice", ObjectUtils.firstNonNull(monitor.getContactsPolice(), GlobalUnit.NULLMSG));// 民警电话
        map.put("duration", ObjectUtils.firstNonNull(monitor.getDuration(), GlobalUnit.NULLMSG));// 布控时间
        // 人员类别
        dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, monitor.getPersonTypeId());
        if (dictionary != null) {
            map.put("personType", dictionary.getItemName());
        } else {
            map.put("personType", GlobalUnit.NULLMSG);
        }
        // 是否审核
        if (monitor.getStatus() == null || monitor.getStatus() == 0) {
            map.put("status", "未审核");
        } else {
            map.put("status", "已审核");
        }
        map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(monitor.getCreateDate()));// 入库时间
        map.put("phone", monitor.getContactInfo());
        // 布控理由
        map.put("reason", ObjectUtils.firstNonNull(monitor.getReason(), GlobalUnit.NULLMSG));
        return map;
    }
}
