package com.ruiec.web.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.dto.ControlPersonDTO;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.ControlPersonExtend;
import com.ruiec.web.entity.User;

/**
 * 重点人服务接口
 * @author Senghor<br>
 * @date 2017年12月1日 上午9:16:28
 */
public interface ControlPersonService extends BaseService<ControlPerson, String>{
	/**
	 * 获取重点人员列表分页
	 * @author Senghor<br>
	 * @date 2017年12月1日 上午9:30:08
	 */
	void findByPage(Page page, ControlPerson controlPerson, ControlPersonDTO controlPersonDTO);
	
	/**
	 * 重写保存重点人数据
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午10:11:18
	 */
	JsonReturn save(ControlPerson controlPerson, ControlPersonExtend controlPersonExtend, ControlPersonDTO controlPersonDTO);
	
	/**
	 * 重写保存重点人员修改信息
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午10:13:45
	 */
	ControlPerson update(ControlPerson controlPerson,ControlPersonExtend controlPersonExtend, ControlPersonDTO controlPersonDTO);
	
	/**
	 * 审核重点人
	 * @author Senghor<br>
	 * @date 2017年12月22日 下午4:34:36
	 */
	JsonReturn isColumn(Integer id, Integer isColumn);
	
	/**
	 * 导入execl表格数据
	 * @author Senghor<br>
	 * @date 2017年12月22日 下午4:34:36
	 */
	JsonReturn toLeadExcel(MultipartFile execlFile,Integer personnelType);
	
	/**
	 * 是否有权限操作改人员
	 * @author Senghor<br>
	 * @date 2017年12月28日 下午4:07:47
	 */
	boolean isPower(Integer id,User user,LoginUserUnit loginUserUnit);
	
	/**
	 * 根据id查询重点人详情（api）
	 * @author qinzhitian<br>
	 * @date 2018年1月10日 上午9:51:54
	 */
	public Map<String, Object> findById(Integer id);

}