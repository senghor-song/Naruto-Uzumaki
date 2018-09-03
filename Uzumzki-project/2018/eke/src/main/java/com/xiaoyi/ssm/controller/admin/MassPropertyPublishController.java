package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.MassPropertyPublish;
import com.xiaoyi.ssm.service.MassPropertyPublishService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 群发任务控制器
 * @author 宋高俊
 * @date 2018年7月30日 下午4:30:05
 */
@Controller(value = "adminMassPropertyPublishController")
@RequestMapping("admin/massPropertyPublish")
public class MassPropertyPublishController {
	@Autowired
	private MassPropertyPublishService mpps;

	/**
	 * @Description: 群发任务页面
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/massPropertyPublish/list";
	}

	/**
	 * @Description: 群发任务数据
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<MassPropertyPublish> list = mpps.selectByAll(null);
		PageInfo<MassPropertyPublish> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			MassPropertyPublish massPropertyPublish = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", massPropertyPublish.getId());// ID
			map.put("publishtime", DateUtil.getFormat(massPropertyPublish.getPublishtime()));// 群发时间
			map.put("status", massPropertyPublish.getStatus() == 0 ? "失败" : "成功");// 状态
			map.put("statumes", massPropertyPublish.getStatumes());// 状态信息
			map.put("site", massPropertyPublish.getSite());// 群发平台
			map.put("empstore", massPropertyPublish.getEmpStore().getEmpstore());// 经纪公司
			map.put("emp", massPropertyPublish.getEmployee().getEmp());// 经纪人
			map.put("tel", massPropertyPublish.getEmployee().getTel());// 经纪人电话
			map.put("propertyno", massPropertyPublish.getProperty().getMassProperty().getPropertyno());// 房源编号
			map.put("title", massPropertyPublish.getProperty().getMassProperty().getTitle());// 房源信息
			map.put("trade", massPropertyPublish.getProperty().getMassProperty().getTrade() == 0 ? "售" : "租");// 类型
			map.put("usage", massPropertyPublish.getProperty().getMassProperty().getUsage());// 物业
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
}
