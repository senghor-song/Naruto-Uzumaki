package com.xiaoyi.ssm.controller.pc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.EmpCustPersonMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.EmpCustPerson;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.service.MassPersonBuyWantService;
import com.xiaoyi.ssm.service.MassPersonRentService;
import com.xiaoyi.ssm.service.MassPersonRentWantService;
import com.xiaoyi.ssm.service.MassPersonSaleService;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 房源收藏
 * @author 宋高俊
 * @date 2018年7月5日 下午7:10:20
 */
@Controller
@RequestMapping("/empCustPerson")
public class EmpCustPersonController {

	@Autowired
	private EmpCustPersonMapper empCustPersonMapper;
	@Autowired
	private MassPersonBuyWantService massPersonBuyWantService;
	@Autowired
	private MassPersonRentService massPersonRentService;
	@Autowired
	private MassPersonRentWantService massPersonRentWantService;
	@Autowired
	private MassPersonSaleService massPersonSaleService;

	/**
	 * @Description: 收藏列表
	 * @author 宋高俊
	 * @date 2018年7月5日 下午7:11:03
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/empCustPersonList")
	public String empCustPersonList(Model model, HttpServletRequest request, MassPersonDto massPersonDto, PageInfo pageInfo) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		massPersonDto.setEmpId(employee.getId());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		int postType = massPersonDto.getPostType();
		List list = new ArrayList();

		if (!StringUtils.isBlank(massPersonDto.getDistrictNas())) {
			massPersonDto.setDistrictNasList(massPersonDto.getDistrictNas().split(","));
		}
		if (!StringUtils.isBlank(massPersonDto.getWebNames())) {
			massPersonDto.setWebNameList(massPersonDto.getWebNames().split(","));
		}
		if (!StringUtils.isBlank(massPersonDto.getHouseArea())) {
			String[] strings = massPersonDto.getHouseArea().split(",");
			massPersonDto.setMinHouseArea(Integer.valueOf(strings[0]));
			if (strings.length > 1) {
				massPersonDto.setMaxHouseArea(Integer.valueOf(strings[1]));
			}
		}
		if (postType == 0) {
			list = massPersonSaleService.selectAll(massPersonDto);
		} else if (postType == 1) {
			list = massPersonRentService.selectAll(massPersonDto);
		} else if (postType == 2) {
			list = massPersonBuyWantService.selectAll(massPersonDto);
		} else if (postType == 3) {
			list = massPersonRentWantService.selectAll(massPersonDto);
		}
		pageInfo = new PageInfo<>(list);
		model.addAttribute("postType", postType);
		model.addAttribute("page", pageInfo);
		return "massPerson/ajaxList";
	}

	/**
	 * @Description: 添加收藏
	 * @author 宋高俊
	 * @date 2018年7月5日 下午6:47:36
	 */
	@RequestMapping("/addStorelist")
	@ResponseBody
	public ApiMessage addStorelist(String housetype, String solrId, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		EmpCustPerson ecp = new EmpCustPerson();
		ecp.setId(Utils.getUUID());
		ecp.setEmpid(employee.getId());
		ecp.setCreatetime(new Date());
		ecp.setHouseid(solrId);
		ecp.setHousetype(housetype);
		int flag = empCustPersonMapper.insertSelective(ecp);
		if (flag == 1) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 取消收藏
	 * @author 宋高俊
	 * @date 2018年7月5日 下午6:47:36
	 */
	@RequestMapping("/delStorelist")
	@ResponseBody
	public ApiMessage delStorelist(String solrId, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		int flag = empCustPersonMapper.delEmpCustPerson(solrId, employee.getId());
		if (flag == 1) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}
}
