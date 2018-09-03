package com.xiaoyi.ssm.controller.pc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.BlackListEmpMapper;
import com.xiaoyi.ssm.dao.DistrictMapper;
import com.xiaoyi.ssm.dao.MassPersonBuyWantEmpCondiMapper;
import com.xiaoyi.ssm.dao.MassPersonRentEmpCondiMapper;
import com.xiaoyi.ssm.dao.MassPersonRentWantEmpCondiMapper;
import com.xiaoyi.ssm.dao.MassPersonSaleEmpCondiMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.BlackListEmp;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.MassPersonBuyWantEmpCondi;
import com.xiaoyi.ssm.model.MassPersonRentEmpCondi;
import com.xiaoyi.ssm.model.MassPersonRentWantEmpCondi;
import com.xiaoyi.ssm.model.MassPersonSaleEmpCondi;
import com.xiaoyi.ssm.service.MassPersonBuyWantService;
import com.xiaoyi.ssm.service.MassPersonRentService;
import com.xiaoyi.ssm.service.MassPersonRentWantService;
import com.xiaoyi.ssm.service.MassPersonSaleService;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 个人房源操作控制器
 * @author 宋高俊
 * @date 2018年6月25日 下午6:31:58
 */
@Controller
@RequestMapping("/massPerson")
public class MassPersonController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MassPersonBuyWantService massPersonBuyWantService;
	@Autowired
	private MassPersonRentService massPersonRentService;
	@Autowired
	private MassPersonRentWantService massPersonRentWantService;
	@Autowired
	private MassPersonSaleService massPersonSaleService;
	@Autowired
	private MassPersonSaleEmpCondiMapper massPersonSaleEmpCondiMapper;
	@Autowired
	private MassPersonRentEmpCondiMapper massPersonRentEmpCondiMapper;
	@Autowired
	private MassPersonBuyWantEmpCondiMapper massPersonBuyWantEmpCondiMapper;
	@Autowired
	private MassPersonRentWantEmpCondiMapper massPersonRentWantEmpCondiMapper;
	@Autowired
	private DistrictMapper districtMapper;
	@Autowired
	private BlackListEmpMapper blackListEmpMapper;

	/**
	 * @Description: 个人房源操作列表
	 * @author 宋高俊
	 * @date 2018年6月26日 上午11:08:48
	 */
	@RequestMapping(value = "/list")
	public String list(Model model, String postType, int cust) {
		model.addAttribute("postType", postType);
		model.addAttribute("cust", cust);
		List<District> districts = districtMapper.selectByAll(null);
		model.addAttribute("districts", districts);
		return "massPerson/list";
	}

	/**
	 * @Description: 条件查询分页数据
	 * @author 宋高俊
	 * @date 2018年6月26日 上午11:08:48
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ajaxList")
	public String ajaxList(Model model, HttpServletRequest request, MassPersonDto massPersonDto, PageInfo pageInfo) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		//获取黑民单数据并且根据电话号码将数据筛选
		List<BlackListEmp> blackListEmps = blackListEmpMapper.selectAll(employee.getId());
		String[] blacklist = new String[blackListEmps.size()];
		for (int i = 0; i < blackListEmps.size(); i++) {
			blacklist[i] = blackListEmps.get(i).getTel();
		}
		massPersonDto.setBlacklist(blacklist);
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
	 * @Description: 验证保存条数是否已达上限
	 * @author 宋高俊
	 * @date 2018年6月27日 上午11:41:37
	 */
	@RequestMapping(value = "/verifySearch")
	@ResponseBody
	public ApiMessage verifySearch(Model model, MassPersonDto massPersonDto) {
		return new ApiMessage(200, "查询成功", false);
	}

	/**
	 * @Description: 跳转保存的查询条件页面
	 * @author 宋高俊
	 * @date 2018年6月27日 上午11:41:37
	 */
	@RequestMapping(value = "/getSaveSearch")
	public String getSaveSearch(Model model, HttpServletRequest request) {
		List<District> districts = districtMapper.selectByAll(null);
		model.addAttribute("districts", districts);
		return "massPerson/search";
	}

	/**
	 * @Description: 保存查询条件数据
	 * @author 宋高俊
	 * @date 2018年6月27日 上午11:41:37
	 */
	@RequestMapping(value = "/saveSearch")
	@ResponseBody
	public ApiMessage saveSearch(Model model, HttpServletRequest request, MassPersonDto massPersonDto) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		int flag = 0;
		int postType = massPersonDto.getPostType();
		if (postType == 0) {
			MassPersonSaleEmpCondi mpsec = new MassPersonSaleEmpCondi();
			mpsec.setId(Utils.getUUID());
			mpsec.setEmpid(employee.getId());
			mpsec.setAddtime(new Date());
			mpsec.setArea(massPersonDto.getAreaNas());
			mpsec.setDef((byte) massPersonDto.getIsDefault());
			mpsec.setCountf(massPersonDto.getRoomType());
			mpsec.setPricemax(massPersonDto.getMaxPrice());
			mpsec.setPricemin(massPersonDto.getMinPrice());
			mpsec.setSizemax(massPersonDto.getMaxHouseArea());
			mpsec.setSizemin(massPersonDto.getMinHouseArea());
			mpsec.setDistrict(massPersonDto.getDistrictNa());
			mpsec.setDistricts(massPersonDto.getDistrictNas());
			mpsec.setType(massPersonDto.getHouseType());
			mpsec.setSites(massPersonDto.getWebNames());
			mpsec.setKeyword(massPersonDto.getPlace());
			mpsec.setAreaid(massPersonDto.getAreaNa());
			flag = massPersonSaleService.insertMassPersonSaleEmpCondi(mpsec);
		} else if (postType == 1) {
			MassPersonRentEmpCondi mprec = new MassPersonRentEmpCondi();
			mprec.setId(Utils.getUUID());
			mprec.setEmpid(employee.getId());
			mprec.setAddtime(new Date());
			mprec.setArea(massPersonDto.getAreaNas());
			mprec.setDef((byte) massPersonDto.getIsDefault());
			mprec.setCountf(massPersonDto.getRoomType());
			mprec.setPricemax(massPersonDto.getMaxPrice());
			mprec.setPricemin(massPersonDto.getMinPrice());
			mprec.setSizemax(massPersonDto.getMaxHouseArea());
			mprec.setSizemin(massPersonDto.getMinHouseArea());
			mprec.setDistrict(massPersonDto.getDistrictNa());
			mprec.setDistricts(massPersonDto.getDistrictNas());
			mprec.setType(massPersonDto.getHouseType());
			mprec.setSites(massPersonDto.getWebNames());
			mprec.setKeyword(massPersonDto.getPlace());
			mprec.setAreaid(massPersonDto.getAreaNa());
			flag = massPersonRentService.insertMassPersonRentEmpCondi(mprec);
		} else if (postType == 2) {
			MassPersonBuyWantEmpCondi mpbwec = new MassPersonBuyWantEmpCondi();
			mpbwec.setId(Utils.getUUID());
			mpbwec.setEmpid(employee.getId());
			mpbwec.setAddtime(new Date());
			mpbwec.setArea(massPersonDto.getAreaNas());
			mpbwec.setDef((byte) massPersonDto.getIsDefault());
			mpbwec.setCountf(massPersonDto.getRoomType());
			mpbwec.setPricemax(massPersonDto.getMaxPrice());
			mpbwec.setPricemin(massPersonDto.getMinPrice());
			mpbwec.setSizemax(massPersonDto.getMaxHouseArea());
			mpbwec.setSizemin(massPersonDto.getMinHouseArea());
			mpbwec.setDistrict(massPersonDto.getDistrictNa());
			mpbwec.setDistricts(massPersonDto.getDistrictNas());
			mpbwec.setType(massPersonDto.getHouseType());
			mpbwec.setSites(massPersonDto.getWebNames());
			mpbwec.setKeyword(massPersonDto.getPlace());
			mpbwec.setAreaid(massPersonDto.getAreaNa());
			flag = massPersonBuyWantService.insertMassPersonBuyWantEmpCondi(mpbwec);
		} else if (postType == 3) {
			MassPersonRentWantEmpCondi mprwec = new MassPersonRentWantEmpCondi();
			mprwec.setId(Utils.getUUID());
			mprwec.setEmpid(employee.getId());
			mprwec.setAddtime(new Date());
			mprwec.setArea(massPersonDto.getAreaNas());
			mprwec.setDef((byte) massPersonDto.getIsDefault());
			mprwec.setCountf(massPersonDto.getRoomType());
			mprwec.setPricemax(massPersonDto.getMaxPrice());
			mprwec.setPricemin(massPersonDto.getMinPrice());
			mprwec.setSizemax(massPersonDto.getMaxHouseArea());
			mprwec.setSizemin(massPersonDto.getMinHouseArea());
			mprwec.setDistrict(massPersonDto.getDistrictNa());
			mprwec.setDistricts(massPersonDto.getDistrictNas());
			mprwec.setType(massPersonDto.getHouseType());
			mprwec.setSites(massPersonDto.getWebNames());
			mprwec.setKeyword(massPersonDto.getPlace());
			mprwec.setAreaid(massPersonDto.getAreaNa());
			flag = massPersonRentWantService.insertMassPersonRentWantEmpCondi(mprwec);
		}
		if (flag == 1) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 获取保存的查询条件数据
	 * @author 宋高俊
	 * @date 2018年6月27日 上午11:41:37
	 */
	@RequestMapping(value = "/getSaveSearchList")
	@ResponseBody
	@SuppressWarnings({ "rawtypes" })
	public ApiMessage getSaveSearchList(Model model, HttpServletRequest request, String postType) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		List list = new ArrayList();
		if ("0".equals(postType)) {
			list = massPersonSaleService.selectAllSearch(employee.getId());
		} else if ("1".equals(postType)) {
			list = massPersonRentService.selectAllSearch(employee.getId());
		} else if ("2".equals(postType)) {
			list = massPersonBuyWantService.selectAllSearch(employee.getId());
		} else if ("3".equals(postType)) {
			list = massPersonRentWantService.selectAllSearch(employee.getId());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("postType", postType);
		return new ApiMessage(200, "查询成功", map);
	}

	/**
	 * @Description: 删除查询条件
	 * @author 宋高俊
	 * @date 2018年6月27日 下午6:31:29
	 */
	@RequestMapping(value = "/delSearch")
	@ResponseBody
	public ApiMessage delSearch(Model model, String collectSearchId, String postType) {
		int flag = 0;
		if ("0".equals(postType)) {
			flag = massPersonSaleEmpCondiMapper.deleteByPrimaryKey(collectSearchId);
		} else if ("1".equals(postType)) {
			flag = massPersonRentEmpCondiMapper.deleteByPrimaryKey(collectSearchId);
		} else if ("2".equals(postType)) {
			flag = massPersonBuyWantEmpCondiMapper.deleteByPrimaryKey(collectSearchId);
		} else if ("3".equals(postType)) {
			flag = massPersonRentWantEmpCondiMapper.deleteByPrimaryKey(collectSearchId);
		}
		if (flag == 0) {
			return new ApiMessage(400, "删除失败");
		} else {
			return new ApiMessage(200, "删除成功");
		}
	}

}
