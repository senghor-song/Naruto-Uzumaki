package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.PropertyFalseMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.Property;
import com.xiaoyi.ssm.model.PropertyFollow;
import com.xiaoyi.ssm.model.PropertyScoreLog;
import com.xiaoyi.ssm.service.EstateEmpService;
import com.xiaoyi.ssm.service.PropertyFollowService;
import com.xiaoyi.ssm.service.PropertyImageService;
import com.xiaoyi.ssm.service.PropertyScoreLogService;
import com.xiaoyi.ssm.service.PropertyService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 房源控制器
 * @author 宋高俊
 * @date 2018年7月25日 下午2:43:06
 */
@Controller(value = "adminPropertyController")
@RequestMapping(value = "/admin/property")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;
	@Autowired
	private PropertyImageService propertyImageService;
	@Autowired
	private PropertyFollowService propertyFollowService;
	@Autowired
	private PropertyScoreLogService propertyScoreLogService;
	@Autowired
	private EstateEmpService estateEmpService;
	@Autowired
	private PropertyFalseMapper propertyFalseMapper;

	/**
	 * @Description: 房源页面
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/property/list";
	}

	/**
	 * @Description: 房源数据
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Property> list = propertyService.selectByAll(null);
		PageInfo<Property> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Property property = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", property.getId());// ID
			map.put("city", property.getMassProperty().getEstateT().getCityT().getCity());// 城市
			map.put("estate", property.getMassProperty().getEstateT().getEstate());// 小区
			map.put("propertyno", property.getMassProperty().getPropertyno());// 房源编号
			map.put("status", property.getMassProperty().getStatus());// 状态
			map.put("trade", property.getMassProperty().getTrade());// 交易
			map.put("empid", property.getMassProperty().getEmpid());// 经纪人ID
			map.put("emp", property.getMassProperty().getEmployeeT().getEmp());// 经纪人
			map.put("store", property.getMassProperty().getEmpStoreT().getEmpstore());// 商户
			map.put("propertyImageSum", propertyImageService.countByProperty(property.getId()));// 室内图
			map.put("propertyFalseSum", propertyFalseMapper.countByProperty(property.getId()));// 投诉
			map.put("sortscore", property.getSortscore());// 排序积分
			map.put("propertyFollowSum", propertyFollowService.countByProperty(property.getId()));// 跟进
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 房源
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/details")
	public String details(Model model, String id) {
		Property property = propertyService.selectByPrimaryKey(id);
		model.addAttribute("property", property);
		return "admin/property/details";
	}

	/**
	 * @Description: 室内图片页面
	 * @author 宋高俊
	 * @date 2018年7月31日 下午5:57:19
	 */
	@RequestMapping("/propertyImg")
	public String propertyImg(Model model, String id) {
		// 封面图
		Property property = propertyService.selectByPrimaryKey(id);
		model.addAttribute("headimg", property.getMassProperty().getHeadimgpath());
		model.addAttribute("id", id);
		String empid = property.getMassProperty().getEmpid();
		String massPropertyid = property.getMassProperty().getId();
		// 获取到小区图片文件的列表
		List<Map<String, Object>> estateUrls = new ArrayList<Map<String, Object>>();
		// 获取到房型图文件的列
		List<Map<String, Object>> houseTypeUrls = new ArrayList<Map<String, Object>>();
		// 获取到室内图片文件的列表
		List<Map<String, Object>> indoorUrls = new ArrayList<Map<String, Object>>();
		estateUrls = estateEmpService.selectByImageUrl(empid, massPropertyid, 0);
		houseTypeUrls = estateEmpService.selectByImageUrl(empid, massPropertyid, 1);
		indoorUrls = estateEmpService.selectByImageUrl(empid, massPropertyid, 2);
		model.addAttribute("estateUrls", estateUrls);
		model.addAttribute("houseTypeUrls", houseTypeUrls);
		model.addAttribute("indoorUrls", indoorUrls);
		return "admin/property/propertyImg";
	}

	/**
	 * @Description: 跟进明细页面
	 * @author 宋高俊
	 * @date 2018年7月31日 下午5:57:19
	 */
	@RequestMapping("/propertyFollow/list")
	@ResponseBody
	public AdminMessage propertyFollowList(AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		PropertyFollow propertyFollow = new PropertyFollow();
		propertyFollow.setEmpid(id);
		List<PropertyFollow> list = propertyFollowService.selectByAll(propertyFollow);
		PageInfo<PropertyFollow> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			PropertyFollow pf = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", pf.getId());// ID
			map.put("followdate", DateUtil.getFormat(pf.getFollowdate()));// 跟进时间
			map.put("emp", pf.getEmployee().getEmp());// 跟进人
			map.put("theme", pf.getTheme());// 主题
			map.put("content", pf.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 排序积分页面
	 * @author 宋高俊
	 * @date 2018年7月31日 下午5:57:19
	 */
	@RequestMapping("/propertyScoreLog/list")
	@ResponseBody
	public AdminMessage propertyScoreLogList(AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		PropertyScoreLog propertyScoreLog = new PropertyScoreLog();
		propertyScoreLog.setPropertyid(id);
		List<PropertyScoreLog> list = propertyScoreLogService.selectByAll(propertyScoreLog);
		PageInfo<PropertyScoreLog> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			PropertyScoreLog psl = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", psl.getId());// ID
			map.put("createTime", DateUtil.getFormat(psl.getCreatetime()));// 时间
			map.put("source", psl.getSource());// 获分来源
			map.put("sourceitem", psl.getSourceitem());// 获分项目
			map.put("score", psl.getScore());// 分值
			map.put("remark", psl.getRemark());// 备注
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}

}
