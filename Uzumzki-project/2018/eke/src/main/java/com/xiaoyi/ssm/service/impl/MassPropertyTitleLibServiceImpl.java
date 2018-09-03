package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassPropertyDescTemplateMapper;
import com.xiaoyi.ssm.dao.MassPropertyTitleLibMapper;
import com.xiaoyi.ssm.model.MassPropertyDescTemplate;
import com.xiaoyi.ssm.model.MassPropertyTitleLib;
import com.xiaoyi.ssm.service.MassPropertyTemplateService;

/**
 * @Description: 网站业务逻辑实现
 * @author 宋高俊
 * @date 2018年7月20日 下午7:20:26
 */
@Service("massPropertyTitleLibServiceImpl")
public class MassPropertyTitleLibServiceImpl extends AbstractService<MassPropertyTitleLib, String> implements MassPropertyTemplateService {

	@Autowired
	private MassPropertyTitleLibMapper massPropertyTitleLibMapper;
	@Autowired
	private MassPropertyDescTemplateMapper massPropertyDescTemplateMapper;

	@Override
	public void setBaseMapper() {
		super.setBaseMapper(massPropertyTitleLibMapper);
	}

	/**
	 * @Description: 根据小区和房源类型获取标题模板
	 * @author 宋高俊
	 * @date 2018年7月23日 下午3:27:01
	 */
	@Override
	public List<MassPropertyTitleLib> selectByEstateTypeTitle(String estateid, int type) {
		return massPropertyTitleLibMapper.selectByEstateTypeAll(estateid, type);
	}

	/**
	 * @Description: 根据小区和房源类型获取描述模板
	 * @author 宋高俊
	 * @date 2018年7月23日 下午3:27:01
	 */
	@Override
	public List<MassPropertyDescTemplate> selectByEstateTypeDesc(int type) {
		return massPropertyDescTemplateMapper.selectByEstateTypeAll(type);
	}

}
