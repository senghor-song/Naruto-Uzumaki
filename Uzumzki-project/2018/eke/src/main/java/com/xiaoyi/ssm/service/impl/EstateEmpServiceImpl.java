package com.xiaoyi.ssm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EstateImageEmpMapper;
import com.xiaoyi.ssm.dao.EstateMapper;
import com.xiaoyi.ssm.dao.HouseTypeImageEmpMapper;
import com.xiaoyi.ssm.dao.PropertyImageEmpMapper;
import com.xiaoyi.ssm.dto.EstateImageDto;
import com.xiaoyi.ssm.dto.HouseEnterDto;
import com.xiaoyi.ssm.model.Estate;
import com.xiaoyi.ssm.model.EstateImageEmp;
import com.xiaoyi.ssm.model.HouseTypeImageEmp;
import com.xiaoyi.ssm.model.PropertyImageEmp;
import com.xiaoyi.ssm.service.EstateEmpService;

/**
 * @Description: 小区业务逻辑实现
 * @author 宋高俊
 * @date 2018年7月10日 上午9:34:17
 */
@Service
public class EstateEmpServiceImpl extends AbstractService<Estate, String> implements EstateEmpService {

	@Autowired
	private EstateMapper estateMapper;
	@Autowired
	private EstateImageEmpMapper estateImageEmpMapper;
	@Autowired
	private HouseTypeImageEmpMapper houseTypeImageEmpMapper;
	@Autowired
	private PropertyImageEmpMapper propertyImageEmpMapper;

	@Override
	public void setBaseMapper() {
		super.setBaseMapper(estateMapper);
	}

	/**
	 * @Description: 模糊查询小区信息
	 * @author 宋高俊
	 * @date 2018年7月10日 上午9:35:26
	 */
	@Override
	public List<Estate> selectByName(HouseEnterDto houseEnterDto) {
		return estateMapper.selectByName(houseEnterDto);
	}

	/**
	 * @Description: 根据条件查询室内图片
	 * @author 宋高俊
	 * @date 2018年7月10日 上午11:58:14
	 */
	@Override
	public List<PropertyImageEmp> selectByEmpPropertyImage(EstateImageDto estateImageDto) {
		return propertyImageEmpMapper.selectByEmpImage(estateImageDto);
	}

	/**
	 * @Description: 根据条件查询房型图片
	 * @author 宋高俊
	 * @date 2018年7月10日 上午11:58:14
	 */
	@Override
	public List<HouseTypeImageEmp> selectByEmpHouseTypeImage(EstateImageDto estateImageDto) {
		return houseTypeImageEmpMapper.selectByEmpImage(estateImageDto);
	}

	/**
	 * @Description: 根据条件查询小区图片
	 * @author 宋高俊
	 * @date 2018年7月10日 上午11:58:14
	 */
	@Override
	public List<EstateImageEmp> selectByEmpEstateImage(EstateImageDto estateImageDto) {
		return estateImageEmpMapper.selectByEmpImage(estateImageDto);
	}

	/**
	 * @Description: 根据经纪人查询已保存图片的小区
	 * @author 宋高俊
	 * @date 2018年7月10日 下午12:06:12
	 */
	@Override
	public List<EstateImageDto> selectByEmpEstate(String empid, Integer type) {
		if (type == 0) {
			return estateImageEmpMapper.selectByEmpEstate(empid);
		} else if (type == 1) {
			return propertyImageEmpMapper.selectByEmpEstate(empid);
		} else if (type == 2) {
			return houseTypeImageEmpMapper.selectByEmpEstate(empid);
		} else {
			return null;
		}
	}

	/**
	 * @Description: 批量删除小区图片
	 * @author 宋高俊
	 * @date 2018年7月10日 下午5:16:49
	 */
	@Override
	public int delImageManage(String[] ids, Integer type) {
		if (type == 0) {
			return estateImageEmpMapper.deleteByPrimaryKeys(ids);
		} else if (type == 1) {
			return propertyImageEmpMapper.deleteByPrimaryKeys(ids);
		} else if (type == 2) {
			return houseTypeImageEmpMapper.deleteByPrimaryKeys(ids);
		} else {
			return 0;
		}
	}

	/**
	 * @Description: 根据经纪人和房源ID,房源类型获取图片
	 * @author 宋高俊
	 * @param type 0=小区图1=室内图2=房型图
	 * @date 2018年7月10日 下午12:06:12
	 */
	@Override
	public List<Map<String, Object>> selectByImageUrl(String empid, String houseid, Integer type) {
		if (type == 0) {
			return estateImageEmpMapper.selectByImageUrl(empid, houseid);
		} else if (type == 1) {
			return propertyImageEmpMapper.selectByImageUrl(empid, houseid);
		} else if (type == 2) {
			return houseTypeImageEmpMapper.selectByImageUrl(empid, houseid);
		} else {
			return null;
		}
	}

}
