package com.xiaoyi.ssm.controller.managerapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dao.TrainBaseMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.Train;
import com.xiaoyi.ssm.model.TrainBase;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.service.TrainService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 培训接口控制器
 * @author 宋高俊
 * @date 2018年8月23日 下午2:59:21
 */
@Controller("managerapiTrainController")
@RequestMapping("managerapi/train")
public class ApiTrainController {

	@Autowired
	private TrainService trainService;
	@Autowired
	private TrainBaseMapper trainBaseMapper;
	@Autowired
	private VenueService venueService;

	/**
	 * @Description: 培训列表
	 * @author 宋高俊
	 * @date 2018年8月23日 下午3:04:29
	 */
	@RequestMapping(value = "/trainList")
	@ResponseBody
	public ApiMessage trainList(String token) {
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);

		Train t = new Train();
		t.setManagerid(manager.getId());
		// 根据管理员查询培训课程
		List<Train> list = trainService.selectByAll(t);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (Train train : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", train.getId());// id
			map.put("image", train.getImage());// 海报地址
			map.put("content", train.getContent());// 表格
			map.put("contentText", train.getContenttext());// 表格内容
			map.put("qrcode", "https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/2018/8/23/timg.jpg");// 二维码地址
			map.put("price", train.getPrice());// 单价
			listmap.add(map);
		}
		return new ApiMessage(200, "查询成功", listmap);
	}

	/**
	 * @Description: 培训详情
	 * @author 宋高俊
	 * @date 2018年8月23日 下午3:04:29
	 */
	@RequestMapping(value = "/trainDetails")
	@ResponseBody
	public ApiMessage trainDetails(String trainid) {
		Train t = trainService.selectByPrimaryKey(trainid);
		Map<String, Object> map = new HashMap<>();
		map.put("id", t.getId());// id
		map.put("trainno", t.getTrainno());// 编号
		map.put("venueid", t.getVenueid());// 场馆ID
		map.put("contenttext", t.getContenttext());// 培训内容
		map.put("price", t.getPrice());// 
		map.put("image", t.getImage());// 
		return new ApiMessage(200, "查询成功", map);
	}

	/**
	 * @Description: 删除培训课程
	 * @author 宋高俊
	 * @date 2018年8月23日 下午3:13:52
	 */
	@RequestMapping(value = "/deleteTrain")
	@ResponseBody
	public ApiMessage deleteTrain(String token, String trainid) {
		int flag = trainService.deleteByPrimaryKey(trainid);
		if (flag > 0) {
			return new ApiMessage(200, "删除成功");
		} else {
			return new ApiMessage(400, "删除失败");
		}
	}

	/**
	 * @Description: 获取培训模板列表
	 * @author 宋高俊
	 * @date 2018年8月23日 下午3:04:29
	 */
	@RequestMapping(value = "/trainBaseList")
	@ResponseBody
	public ApiMessage trainBaseList() {
		// 根据管理员查询培训课程
		List<TrainBase> list = trainBaseMapper.selectByAll(null);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (TrainBase trainBase : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", trainBase.getId());// id
			map.put("content", trainBase.getContent());// 表格内容
			map.put("image", trainBase.getImage());// 图片地址
			listmap.add(map);
		}
		return new ApiMessage(200, "查询成功", listmap);
	}

	/**
	 * @Description: 新增培训课程
	 * @author 宋高俊
	 * @date 2018年8月23日 下午3:13:52
	 */
	@RequestMapping(value = "/saveTrain")
	@ResponseBody
	public ApiMessage saveTrain(String token, String content, String image, String trainBaseId, Double price,
			String contenttext) {
		// 管理员数据
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);
		
		Venue venue = venueService.selectByManager(manager.getId());
		// 创建培训课程数据
		Train train = new Train();
		String uuid = Utils.getUUID();
		train.setId(uuid);
		train.setCreatetime(new Date());
		train.setModifytime(new Date());
//		train.setContent(content);
		train.setContenttext(contenttext);
		train.setPrice(price);
		train.setManagerid(manager.getId());
		train.setVenueid(venue.getId());
		train.setImage(image);
		train.setTrainbaseid(trainBaseId);
		int flag = trainService.insertSelective(train);
		if (flag > 0) {
			return new ApiMessage(200, "新增成功", uuid);
		} else {
			return new ApiMessage(400, "新增失败");
		}
	}

	/**
	 * @Description: 修改培训课程
	 * @author 宋高俊
	 * @date 2018年8月23日 下午3:13:52
	 */
	@RequestMapping(value = "/updateTrain")
	@ResponseBody
	public ApiMessage updateTrain(String token, String trainid, String content, Double price, String contenttext) {
		// 创建培训课程数据
		Train train = new Train();
		train.setId(trainid);
		train.setModifytime(new Date());
		train.setContent(content);
		train.setContenttext(contenttext);
		train.setPrice(100.00);
		train.setImage("https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/2018/8/23/timg.jpg");
		int flag = trainService.updateByPrimaryKeyWithBLOBs(train);
		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "修改失败");
		}
	}
}
