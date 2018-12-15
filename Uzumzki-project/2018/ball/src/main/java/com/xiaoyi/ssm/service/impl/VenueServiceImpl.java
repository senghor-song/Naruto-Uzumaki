package com.xiaoyi.ssm.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyi.ssm.dao.AmountRefundWayMapper;
import com.xiaoyi.ssm.dao.VenueLockMapper;
import com.xiaoyi.ssm.dao.VenueMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.AmountRefundWay;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.model.TrainTeamCoach;
import com.xiaoyi.ssm.model.TrainTeamVenue;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueEnter;
import com.xiaoyi.ssm.model.VenueLog;
import com.xiaoyi.ssm.model.VenueRefund;
import com.xiaoyi.ssm.model.VenueTemplate;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.DistrictService;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OperationLogService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainTeamCoachService;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.service.VenueCoachService;
import com.xiaoyi.ssm.service.VenueEnterService;
import com.xiaoyi.ssm.service.VenueLogService;
import com.xiaoyi.ssm.service.VenueRefundService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.service.VenueTemplateService;
import com.xiaoyi.ssm.util.ChineseCharacterUtil;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.ImportExcelUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXPayUtil;
import com.xiaoyi.ssm.wxPay.WXPayWxappUtil;

/**  
 * @Description: 场馆业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class VenueServiceImpl extends AbstractService<Venue,String> implements VenueService{
	
	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private VenueMapper venueMapper;
	@Autowired
	private FieldService fieldService;
	@Autowired
	private AmountRefundWayMapper amountRefundWayMapper;
    @Autowired
    private FieldTemplateService fieldTemplateService;
    @Autowired
    private VenueLockMapper venueLockMapper;
    @Autowired
    private VenueCoachService venueCoachService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private VenueRefundService venueRefundService;
	@Autowired
	private VenueTemplateService venueTemplateService;
	@Autowired
	private ReserveService reserveService;
	@Autowired
	private MemberService memberService;
    @Autowired
    private OperationLogService operationLogService;
	@Autowired
	private CityService cityService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private VenueLogService venueLogService;
	@Autowired
	private TrainTeamCoachService trainTeamCoachService;
	@Autowired
	private VenueEnterService venueEnterService;
	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private TrainTeamService trainTeamService;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueMapper);
	}
	

	@Override
	public List<Venue> selectByOftenMember(String id) {
		return venueMapper.selectByOftenMember(id);
	}

	/**  
	 * @Description: 根据场馆名查询场馆
	 * @author 宋高俊  
	 * @param venuename
	 * @return 
	 * @date 2018年9月21日 下午8:58:30 
	 */ 
	@Override
	public Venue selectByVenueCity(String venueName, String id) {
		return venueMapper.selectByVenueCity(venueName, id);
	}

	/**  
	 * @Description: 自定义条件查询场馆列表
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月8日 上午9:19:58 
	 */ 
	@Override
	public List<Venue> selectByAllVenue(String cityid, String districtid, Double lng, Double lat, Integer ballType) {
		return venueMapper.selectByAllVenue(cityid, districtid, lng, lat, ballType);
	}
	
	
	/** 
	 * @Description: 根据培训机构ID获取培训场地
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月29日 下午8:48:31 
	 */
	@Override
	public List<Venue> selectByTrainTeamID(String id) {
		return venueMapper.selectByTrainTeamID(id);
	}
	
	/**  
	 * @Description: 
	 * @author 宋高俊  
	 * @param trainTeamId 培训机构ID
	 * @param id 场馆ID
	 * @return 
	 * @date 2018年10月10日 下午2:20:20 
	 */ 
	@Override
	public int deleteByTeamVenue(String trainTeamId, String id) {
		return venueMapper.deleteByTeamVenue(trainTeamId, id);
	}
	
	/**  
	 * @Description: 保存培训机构使用场地
	 * @author 宋高俊  
	 * @param id
	 * @param trainTeamId
	 * @return 
	 * @date 2018年10月10日 下午2:50:27 
	 */ 
	@Override
	public int saveTeamVenue(TrainTeamVenue trainTeamVenue) {
		return venueMapper.saveTeamVenue(trainTeamVenue);
	}

	/**  
	 * @Description: 根据培训机构统计所使用的场地
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月12日 上午9:10:10 
	 */ 
	@Override
	public int countByTeam(String id) {
		return venueMapper.countByTeam(id);
	}
	
	/**  
	 * @Description: 条件查询场地数据
	 * @author 宋高俊  
	 * @param name
	 * @param longitude
	 * @param latitude
	 * @return 
	 * @date 2018年10月13日 下午4:25:07 
	 */ 
	@Override
	public List<Venue> selectTrainVenue(String name, Double longitude, Double latitude) {
		return venueMapper.selectTrainVenue(name, longitude, latitude);
	}

	/**  
	 * @Description: 根据培训机构查询管理场馆
	 * @author 宋高俊  
	 * @param managerid
	 * @return 
	 * @date 2018年9月10日 下午4:20:09 
	 */ 
	@Override
	public List<Venue> selectByManager(String id) {
		return venueMapper.selectByManager(id);
	}

	/**  
	 * @Description: 根据名称模糊查询场地
	 * @author 宋高俊  
	 * @param name
	 * @return 
	 * @date 2018年10月18日 下午2:59:01 
	 */ 
	@Override
	public List<Venue> selectByName(String name) {
		return venueMapper.selectByName(name);
	}

	/**  
	 * @Description: 根据经纬度筛选场馆
	 * @author 宋高俊  
	 * @param begLng
	 * @param endLng
	 * @param begLat
	 * @param endLat
	 * @return 
	 * @date 2018年10月20日 下午3:25:58 
	 */ 
	@Override
	public List<Venue> selectByNearbyMapVenue(double begLng, double endLng, double begLat, double endLat, Integer ballType) {
		return venueMapper.selectByNearbyMapVenue(begLng, endLng, begLat, endLat, ballType);
	}

	/**  
	 * @Description: 条件查询场馆
	 * @author 宋高俊  
	 * @param selectType
	 * @param keyword
	 * @return 
	 * @date 2018年10月30日 上午10:09:42 
	 */ 
	@Override
	public List<Venue> selectBySearch(Integer selectType, String keyword) {
		return venueMapper.selectBySearch(selectType, keyword);
	}

	/**  
	 * @Description: 条件查询模板分析数据
	 * @author 宋高俊  
	 * @param ballType 
	 * @param trainAddFlag 
	 * @return 
	 * @date 2018年11月2日 下午8:08:47 
	 */ 
	@Override
	public List<Venue> selectByVenueSearch(String cityid, Integer sumTemplate, Integer trainAddFlag, Integer ballType) {
		return venueMapper.selectByVenueSearch(cityid, sumTemplate, trainAddFlag, ballType);
	}


	/**  
	 * @Description: 根据时间和状态查询有过订单的场馆
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年11月15日14:23:09
	 */ 
	@Override
	public List<Venue> selectByDateOut(String date, Integer type) {
		return venueMapper.selectByDateOut(date, type);
	}

	/**  
	 * @Description: 根据手机号查询是否有匹配的场馆
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年11月15日14:23:09
	 */ 
	@Override
	public List<Venue> selectByMatchingVenue(String phone) {
		return venueMapper.selectByMatchingVenue(phone);
	}

	/**
	 * @Description: 解除结构入驻绑定
	 * @author 宋高俊
	 * @param venueid
	 * @date 2018年11月22日 上午11:23:48
	 */
	@SuppressWarnings("unused")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Venue relieveVenue(String venueid) {

		// 当前时间串
		String dateStr = venueid + "-" + DateUtil.getFormat(new Date(), "yyyyMMdd-HHmmss");
		
		// 获取场馆数据
		Venue venue = selectByPrimaryKey(venueid);

		venue.setBallsum(1);
		venue.setModifytime(new Date());
		venueMapper.updateByPrimaryKeySelective(venue);
		venueMapper.updateByTrainTeamNull(venueid);
		
		Date nowDate = DateUtil.getWeeHours(new Date(), 0);
		Date dateEnd = DateUtil.getWeeHours(new Date(), 1);
		// 当前场馆的订单全部退款，将当天以后(含当天)所有的订单查询并结算退款
		List<Order> orders = orderService.selectByRelieveVenue(nowDate, venueid);
		for (Order order : orders) {
			// 大于6小时
			WXPayWxappUtil.weiXinRefund(order.getId(), order.getPrice(), order.getPrice(),  "全额退款", 0);

			// 修改订单状态
			order.setType(4);
			orderService.updateByPrimaryKeySelective(order);
			
			Member orderMember = memberService.selectByPrimaryKey(order.getMemberid());
			
			// 通知内容
			List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
			String area = "";
			String timeStr = ""; 
			if (listReserve.size() > 0) {
				area =  venue.getName() + listReserve.get(0).getField().getName();
				timeStr = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
			}
			
			// 预定通知消息
			if (!StringUtil.isBank(orderMember.getOpenid())) {
				JSONObject datajson = new JSONObject();
				datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
				datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
				datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
				datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"退款成功\"}"));
				datajson.put( "remark",
						JSONObject.parseObject("{\"value\":\"您预订的" + area + "，日期"
								+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + timeStr + "用场，由于场馆原因取消，所支付费用已全额退款。\"}"));
				logger.info(WXPayUtil.sendWXappTemplate(orderMember.getOpenid(), WXConfig.wxTemplateId, "/pages/index/index", datajson));
			}
		}
		//所有未处理的退款申请
		List<VenueRefund> venueRefunds = venueRefundService.selectByVenue(venueid, nowDate, dateEnd);
		for (VenueRefund venueRefund : venueRefunds) {

			// 大于6小时
			if(WXPayWxappUtil.weiXinRefund(venueRefund.getOrderId(), venueRefund.getAmountSum(), venueRefund.getAmountRefund(), "审核通过退款", 0)){
				venueRefund.setRefundStatus(1);
			} else {
				venueRefund.setRefundStatus(2);
			}
			venueRefundService.updateByPrimaryKeySelective(venueRefund);

			// 修改订单状态
			Order order = orderService.selectByPrimaryKey(venueRefund.getOrderId());
			order.setType(4);
			orderService.updateByPrimaryKeySelective(order);
			
			Member orderMember = memberService.selectByPrimaryKey(order.getMemberid());
			
			// 通知内容
			List<Reserve> listReserve = reserveService.selectByOrder(venueRefund.getOrderId());
			String area = "";
			String timeStr = ""; 
			if (listReserve.size() > 0) {
				area =  venue.getName() + listReserve.get(0).getField().getName();
				timeStr = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
			}
			
			// 预定通知消息
			if (!StringUtil.isBank(orderMember.getOpenid())) {
				JSONObject datajson = new JSONObject();
				datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
				datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
				datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
				datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"退款成功\"}"));
				datajson.put( "remark",
						JSONObject.parseObject("{\"value\":\"您预订的" + area + "，日期"
								+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + timeStr + "用场，由于场馆原因取消，所支付费用已全额退款。\"}"));
				logger.info(WXPayUtil.sendWXappTemplate(orderMember.getOpenid(), WXConfig.wxTemplateId, "/pages/index/index", datajson));
			}
		}
		
		// 模板逻辑删除
		int venueTemplateCount = venueTemplateService.updateByVenue(venueid,dateStr);
		
		// 场地逻辑删除
		int fieldCount = fieldService.updateByVenue(venueid,dateStr);
		
		// 场地使用模板逻辑删除
		int fieldTemplateCount = fieldTemplateService.updateByVenue(venueid,dateStr);
		
		// 场地锁定表逻辑删除
		int venueLockCount = venueLockMapper.updateByVenue(venueid,dateStr);
		
		// 退费费率逻辑删除
		int amountRefundWayCount = amountRefundWayMapper.updateByVenue(venueid,dateStr);
		
		// 陪练数据逻辑删除
		int venueCoachCount = venueCoachService.updateByVenue(venueid,dateStr);
		
		return venue;
	}
	
	
	/**
	 * @Description: 初始化场馆数据
	 * @author 宋高俊
	 * @param venue
	 * @date 2018年11月18日 下午7:26:47
	 */
	@Override
	public void initVenue(Venue venue) {

		// 导入新增默认系统模板
		VenueTemplate venueTemplate = new VenueTemplate();
		venueTemplate.setId(venue.getId());
		venueTemplate.setCreatetime(new Date());
		venueTemplate.setModifytime(new Date());
		venueTemplate.setName("系统模板");
		venueTemplate.setPrice("-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1");
		venueTemplate.setVenueid(venue.getId());
		venueTemplateService.insertSelective(venueTemplate);
		
		if (venue.getBallsum() != null && venue.getBallsum() > 1) {
			for (int i = 0; i < venue.getBallsum(); i++) {
				// 导入新增默认1号场
				Field field = new Field();
				field.setId(Utils.getUUID());
				field.setCreatetime(new Date());
				field.setModifytime(new Date());
				field.setVenueid(venue.getId());
				field.setName((i + 1)+"号场");
				fieldService.insertSelective(field);
				
				// 场地使用模板数据
				FieldTemplate fieldTemplate = new FieldTemplate();
				fieldTemplate.setCreatetime(new Date());
				fieldTemplate.setFieldid(field.getId());
				fieldTemplate.setTemplateid(venueTemplate.getId());
				fieldTemplate.setVenueid(venue.getId());
				
				// 新增七天的模板
				Date nowDate = new Date();
				for (int j = 0; j < 7; j++) {
					fieldTemplate.setId(Utils.getUUID());
					fieldTemplate.setFieldtime(nowDate);
					fieldTemplateService.insertSelective(fieldTemplate);

					nowDate = DateUtil.getPreTime(nowDate, 3, 1);
				}
			}
		} else {
			// 导入新增默认1号场
			Field field = new Field();
			field.setId(Utils.getUUID());
			field.setCreatetime(new Date());
			field.setModifytime(new Date());
			field.setVenueid(venue.getId());
			field.setName("1号场");
			fieldService.insertSelective(field);
			
			// 场地使用模板数据
			FieldTemplate fieldTemplate = new FieldTemplate();
			fieldTemplate.setCreatetime(new Date());
			fieldTemplate.setFieldid(field.getId());
			fieldTemplate.setTemplateid(venueTemplate.getId());
			fieldTemplate.setVenueid(venue.getId());

			// 新增三十天的模板
			Date nowDate = new Date();
			for (int i = 0; i < 7; i++) {
				fieldTemplate.setId(Utils.getUUID());
				fieldTemplate.setFieldtime(nowDate);
				fieldTemplateService.insertSelective(fieldTemplate);

				nowDate = DateUtil.getPreTime(nowDate, 3, 1);
			}
		}
		
		// 新增默认退费费率
		AmountRefundWay amountRefundWay = new AmountRefundWay();
		amountRefundWay.setId(venue.getId());
		amountRefundWay.setCreateTime(new Date());
		amountRefundWay.setModifyTime(new Date());
		amountRefundWay.setFee1(50);
		amountRefundWay.setFee2(0);
		amountRefundWay.setFee3(0);
		amountRefundWay.setWeatherStart(0);
		amountRefundWay.setWeatherEnd(50);
		amountRefundWayMapper.insertSelective(amountRefundWay);
		
	}

	/**
	 * @Description: 根据用户ID和日期查询场馆
	 * @author 宋高俊
	 * @param id
	 * @param dateStr
	 * @return
	 * @date 2018年12月5日下午3:15:14
	 */
	@Override
	public List<Venue> selectByMemberAndOrderDate(String memberId, String dateStr) {
		return venueMapper.selectByMemberAndOrderDate(memberId, dateStr);
	}


	/**
	 * @Description: excel导入数据
	 * @author 宋高俊
	 * @param staff
	 * @date 2018年12月13日上午10:46:40
	 */
	@Override
	public ApiMessage importExcel(Staff staff, MultipartFile file, String sessionId, String ip) {
		Map<String, Object> redismap = new HashMap<>();
		int countLine = 0;
		int row = 2;
		logger.info("开始导入场馆execl表格");
		Map<String, Object> map = new HashMap<>();
		List<String> errorlist = new ArrayList<String>();
		List<String> succeedlist = new ArrayList<String>();
		if (file.isEmpty()) {
			logger.info("文件不存在,停止导入");
			return new ApiMessage(400, "导入失败,文件不存在!");
		}
		InputStream in = null;
		List<List<Object>> listob = null;
		try {
			in = file.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		} catch (Exception e) {
			logger.info("文件格式不正确,停止导入!");
			return new ApiMessage(400, "请上传execl文件");
		}
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		
		String fristVenueName = "";
		if (listob != null && listob.size() > 0) {
			countLine = listob.size();
			for (List<Object> excelList : listob) {
				String result = numberFormat.format((float) row / (float) (countLine + 1) * 100);
				redismap.put("current", result);
				redismap.put("page", (row-1)+"/"+countLine);
				RedisUtil.addRedis(Global.REDIS_SESSION_UPLOAD_MAP, sessionId + "venue", redismap);
				row++;
				
				if (excelList.size() != 12) {
					errorlist.add("第" + row + "行字段缺少");
					continue;
				}
				String cityName = excelList.get(0).toString();// 城市
				String districtName = excelList.get(1).toString();// 区县
				String type = excelList.get(2).toString();// 类型 网球\足球\羽毛球\篮球，其中选一
				String venueName = excelList.get(3).toString();// 馆名
				String contactPhone = excelList.get(4).toString();// 订场电话
				String informPhone = excelList.get(5).toString();// 订场支付短信通知号码
				String reserveSms = excelList.get(6).toString();// 订场通知短信
				String lng = excelList.get(7).toString();// 经度
				String lat = excelList.get(8).toString();// 纬度
				String address = excelList.get(9).toString();// 地址
				String reserveSshow = excelList.get(10).toString();// 订场入口
				String showFlag = excelList.get(11).toString();// 场馆状态

				if ("无".equals(type)) {
					errorlist.add("第" + row + "行类型为空");
					continue;
				}
				if (!"网球".equals(type) && !"网球".equals(type)) {
					errorlist.add("第" + row + "行类型为空");
					continue;
				}
				if ("无".equals(venueName)) {
					errorlist.add("第" + row + "行馆名为空");
					continue;
				}
				if ("无".equals(cityName)) {
					errorlist.add("第" + row + "行城市为空");
					continue;
				}
				if (!"无".equals(informPhone) && !Utils.getPhone(informPhone)) {
					errorlist.add("第" + row + "行订场支付短信通知号码不正确");
					continue;
				}
				
				Venue nowVenue = new Venue();
				nowVenue.setId(Utils.getUUID());
				nowVenue.setCreatetime(new Date());
				nowVenue.setModifytime(new Date());
				nowVenue.setReserveSms("是".equals(reserveSms) ? 1 : 0 );
				nowVenue.setReserveShow("是".equals(reserveSshow) ? 1 : 0 );
				nowVenue.setShowflag("正常".equals(showFlag) ? 1 : 2 );				
				nowVenue.setInformPhone(informPhone);
				nowVenue.setContactPhone(contactPhone);


				if (!"无".equals(type)) {
					if ("网球".equals(type)) {
						nowVenue.setType(1);
					} else if ("足球".equals(type)) {
						nowVenue.setType(2);
					} else if ("羽毛球".equals(type)) {
						nowVenue.setType(3);
					} else if ("篮球".equals(type)) {
						nowVenue.setType(4);
					}
				}

				// 第一个条件，场馆名是否存在
				City city = cityService.selectByName(cityName);
				if (city == null) {
					city = new City();
					city.setId(Utils.getUUID());
					city.setCity(cityName);
					city.setHotflag(0);
					city.setInitial(ChineseCharacterUtil.getPinYingLetter(cityName));
					city.setVenuesum(0);
					cityService.insertSelective(city);
				}
				nowVenue.setCityid(city.getId()); // 城市ID
				
				Venue oldVenue = venueMapper.selectByVenueCity(venueName, city.getId());

				if (oldVenue != null) {
					errorlist.add("第" + row + "行场馆已存在");
					continue;
				}
				nowVenue.setName(venueName);// 场馆名称

				// 第二个条件，经纬度是否存在
				String mapGetCity = "";
				String mapGetDistrict = "";
				String mapGetAddress = "";
				if (!"无".equals(lng) && !"无".equals(lat)) {

					nowVenue.setLongitude(Double.parseDouble(lng));// 经度
					nowVenue.setLatitude(Double.parseDouble(lat));// 纬度

					// 使用百度地图根据经纬度获取地址信息
					String jsonString = HttpUtils.sendGet("http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + lat + "," + lng
							+ "&output=json&pois=0&ak=" + Global.Baidu_Map_KRY, null);
					if (!StringUtil.isBank(jsonString)) {
						try {
							jsonString = jsonString.replace("renderReverse&&renderReverse(", "");
							jsonString = jsonString.substring(0, jsonString.length() - 1);
							JSONObject jsonObject = JSONObject.parseObject(jsonString);

							mapGetCity = jsonObject.getJSONObject("result").getJSONObject("addressComponent").getString("city");
							mapGetDistrict = jsonObject.getJSONObject("result").getJSONObject("addressComponent").getString("district");
							mapGetAddress = jsonObject.getJSONObject("result").getString("formatted_address");

							mapGetCity = mapGetCity.substring(0, mapGetCity.length() - 1);
							mapGetDistrict = mapGetDistrict.substring(0, mapGetDistrict.length() - 1);

							nowVenue.setAddress(mapGetAddress);// 场馆地址
						} catch (Exception e) {

						}
					}
				}

				// 第三个条件，城市是否存在
				if (!"".equals(mapGetCity)) {
					cityName = mapGetCity;
				}

				// 第四个条件，区县是否存在
				if (!"".equals(mapGetDistrict)) {
					districtName = mapGetDistrict;
				}
				if (!"无".equals(districtName)) {
					District district = districtService.selectByName(districtName);
					if (district == null) {
						district = new District();
						district.setId(Utils.getUUID());
						district.setDistrict(districtName);
						district.setCityid(city.getId());
						districtService.insertSelective(district);
					}
					nowVenue.setDistrictid(district.getId()); // 区县ID
				}

				// 如果表格中有地址则使用表格地址
				if (!"无".equals(address)) {
					nowVenue.setAddress(address);
				}

				// 保存场馆数据
				try {
					venueMapper.insertSelective(nowVenue);
					initVenue(nowVenue);
					venueLogService.saveLog(nowVenue.getId(), staff.getId(), "导入场馆成功");
					fristVenueName = venueName;
					succeedlist.add("第" + row + "行导入成功");
				} catch (Exception e) {
					errorlist.add("第" + row + "行未知错误");
				}
			}
		} else {
			logger.info("文件没有内容,停止导入!");
			return new ApiMessage(400, "请填写的模板表格内容!");
		}
		map.put("count", countLine);// 总条数
		map.put("succeed", succeedlist.size());// 成功条数
		map.put("error", errorlist.size());// 失败条数

		map.put("succeedlist", succeedlist);// 成功内容
		map.put("errorlist", errorlist);// 失败内容

		if (succeedlist.size() > 1) {
			operationLogService.saveLog(staff.getId(),
					"导入场馆" + fristVenueName + "等成功" + succeedlist.size() + "条", ip);
		} else {
			operationLogService.saveLog(staff.getId(),
					"导入场馆" + fristVenueName + "成功" + succeedlist.size() + "条", ip);
		}
		return new ApiMessage(200, "执行完成", map);
	}


	/**
	 * @Description: 修改场馆数据
	 * @author 宋高俊
	 * @param id场馆ID
	 * @param showflag状态
	 * @param venueName场馆名称
	 * @param contactPhone联系电话
	 * @param informPhone通知电话
	 * @param image封面
	 * @param owner联系人
	 * @param reserveShow订场入口
	 * @param reserveSms订场短信
	 * @param reservePaySms订场支付短信
	 * @param cityName城市名
	 * @param districtName区县名
	 * @param address地址
	 * @param lng经度
	 * @param lat纬度
	 * @param staff管理员
	 * @param ipAddr地址ip
	 * @return
	 * @date 2018年12月13日上午11:31:22
	 */
	@Override
	public ApiMessage updateVenue(String id, Integer showflag, String venueName, String contactPhone,
			String informPhone, String image, String owner, boolean reserveShow, boolean reserveSms,
			boolean reservePaySms, String cityName, String districtName, String address, Double lng, Double lat,
			Staff staff, String ipAddr) {
		
		Venue venue = venueMapper.selectByPrimaryKey(id);

		//修改内容
		String content = "";
		if (!StringUtil.isBank(cityName)) {
			cityName = cityName.substring(0, cityName.length()-1);
			City city = cityService.selectByName(cityName);
			if (city == null) {
				return new ApiMessage(400, "'"+cityName+"'城市不存在,保存失败");
			}

			if (!city.getId().equals(venue.getCityid())) {
				content += "城市修改为" + cityName + "。";
			}
			venue.setCityid(city.getId());
		}else {
			return new ApiMessage(400, "城市不存在,保存失败");
		}

		if (!StringUtil.isBank(districtName)) {
			districtName = districtName.substring(0, districtName.length()-1);
			District district = districtService.selectByName(districtName);
			if (district == null) {
				return new ApiMessage(400, "'"+districtName+"'区县不存在,保存失败");
			}

			if (!district.getId().equals(venue.getDistrictid())) {
				content += "区县修改为" + districtName + "。";
			}
			venue.setDistrictid(district.getId());
		}else {
			District district = districtService.selectByName(cityName);
			if (district == null) {
				return new ApiMessage(400, "'"+cityName+"'区县不存在,保存失败");
			}
			venue.setDistrictid(district.getId());
		}
		if (!lng.toString().equals(venue.getLongitude().toString()) && !lat.toString().equals(venue.getLatitude().toString())) {
			content += "经纬度修改为" + lng + "," + lat;
			venue.setLongitude(lng);
			venue.setLatitude(lat);
		}
		
		// 将场馆从禁用状态判断场馆是否重复
		if (showflag == 1 && !venueName.equals(venue.getName())) {
			Venue lodVenue = venueMapper.selectByVenueCity(venueName, venue.getCityid());
			if (lodVenue != null) {
				return new ApiMessage(400, "'"+venueName+"'场馆已存在,保存失败");
			}
		}
		
		if (!showflag.equals(venue.getShowflag())) {
			content += "状态修改为" + (showflag == 1 ? "正常" : "屏蔽") + "。";
		}
		if (!venueName.equals(venue.getName())) {
			content += "场馆名修改为" + venueName + "。";
		}
		if (!owner.equals(venue.getOwner())) {
			content += "联系人修改为" + owner + "。";
		}
		if (!contactPhone.equals(venue.getContactPhone())) {
			content += "联系电话修改为" + contactPhone + "。";
		}
		if (!informPhone.equals(venue.getInformPhone())) {
			content += "通知电话修改为" + informPhone + "。";
		}
		if ((reserveShow ? 1 : 0) != venue.getReserveShow()) {
			content += "订场入口修改为" + (reserveShow ? "开启" : "关闭") + "。";
		}
		if ((reserveSms ? 1 : 0) != venue.getReserveSms()) {
			content += "订场短信修改为" + (reserveSms ? "开启" : "关闭") + "。";
		}
		if ((reservePaySms ? 1 : 0) != venue.getReservePaySms()) {
			content += "订场支付短信修改为" + (reservePaySms ? "开启" : "关闭") + "。";
		}
		if (!image.equals(venue.getImage())) {
			content += "封面修改为" + image + "。";
		}
		if (!address.equals(venue.getAddress())) {
			content += "场馆地址修改为" + address + "。";
		}
		
		if (!StringUtil.isBank(content)) {
			venue.setShowflag(showflag);
			venue.setModifytime(new Date());
			venue.setName(venueName);
			venue.setInformPhone(informPhone);
			venue.setContactPhone(contactPhone);
			venue.setImage(image);
			venue.setOwner(owner);
			venue.setReserveShow(reserveShow ? 1 : 0);
			venue.setReserveSms(reserveSms ? 1 : 0);
			venue.setReservePaySms(reservePaySms ? 1 : 0);
			venue.setAddress(address);
			venueMapper.updateByPrimaryKeySelective(venue);
			
			operationLogService.saveLog(staff.getId(), "场馆：<" + venue.getVenueno() + ">" + venue.getName() + content, ipAddr);

			VenueLog venueLog = new VenueLog();
			venueLog.setId(Utils.getUUID());
			venueLog.setCreatetime(new Date());
			venueLog.setVenueid(id);
			venueLog.setContent(staff.getName()+":"+content);
			venueLogService.insertSelective(venueLog);
		}
		
		return new ApiMessage(200, "修改成功");
	}


	/**
	 * @Description: 场馆入驻审核处理
	 * @author 宋高俊
	 * @param staff管理员
	 * @param check审核状态
	 * @param checkContent审核内容
	 * @param id申请ID
	 * @param contactPhone联系电话
	 * @param informPhone通知电话
	 * @param venueAddress场馆地址
	 * @param mainFlag联系人关系
	 * @param ballSum球场数量
	 * @return
	 * @date 2018年12月13日上午11:48:07
	 */
	@Override
	public ApiMessage venueEnterCheck(Staff staff, Integer check, String checkContent, String id, String contactPhone,
			String informPhone, String venueAddress, Integer mainFlag, Integer ballSum) {
		try {
			VenueEnter venueEnter = venueEnterService.selectByPrimaryKey(id);
			venueEnter.setContent(checkContent);
			venueEnter.setCheckStaff(staff.getId());
			venueEnter.setCheckTime(new Date());
			
			if (check == 1) {
				// 修改申请数据
				venueEnter.setBallSum(ballSum);
				venueEnter.setMainFlag(mainFlag);
				venueEnter.setCheckFlag(check);
				
				Venue venue = new Venue();
				// 判断是否是已有场馆
				if (StringUtil.isBank(venueEnter.getVenueId())) {
					// 获取城市数据
					City city = cityService.selectByName(venueEnter.getCityName());
					if (city == null) {
						return new ApiMessage(400, "'" + venueEnter.getCityName() + "'城市不存在，请先添加城市");
					}
					city.setId(city.getId());

					// 根据场馆名称和城市ID查询是否有重名
					Venue lodVenue = selectByVenueCity(venueEnter.getTitle(), city.getId());
					if (lodVenue != null) {
						return new ApiMessage(400, venueEnter.getTitle()+"场馆已存在");
					}
					
					District district = new District();
					if (StringUtil.isBank(venueEnter.getDistrictName())) {
						// 获取区县数据
						district = districtService.selectByName(venueEnter.getCityName());
					} else {
						// 获取区县数据
						district = districtService.selectByName(venueEnter.getDistrictName());
					}
					if (district != null) {
						venue.setDistrictid(district.getId());
					} else {
						return new ApiMessage(400, "'" + venueEnter.getCityName() + "'区县不存在，请先添加区县");
					}
					// 创建场馆
					venue.setId(Utils.getUUID());
					venue.setCreatetime(new Date());
					venue.setModifytime(new Date());
					venue.setName(venueEnter.getTitle());
					venue.setType(venueEnter.getBallType());
					venue.setInformPhone(venueEnter.getMainPhone());
					venue.setContactPhone(venueEnter.getMainPhone());
					venue.setImage(venueEnter.getHeadImage());
					venue.setLongitude(venueEnter.getLongitude());
					venue.setLatitude(venueEnter.getLatitude());
					venue.setAddress(venueEnter.getAddress());
					insertSelective(venue);
				} else {
					venue = selectByPrimaryKey(venueEnter.getVenueId());
					if (venue != null) {
						if (!StringUtil.isBank(venue.getTrainteam())) {
							return new ApiMessage(400, "该场馆已被入驻,请先解除入驻");
						}
						// 场馆不为空的时候根据填写的信息修改
						venue = relieveVenue(venueEnter.getVenueId());
					} else {
						return new ApiMessage(400, "场馆不存在");
					}
				}
				// 用户数据
				Member member = memberService.selectByPrimaryKey(venueEnter.getMemberId());
	
				venue.setModifytime(new Date());
				venue.setContactPhone(contactPhone);
				venue.setInformPhone(informPhone);
				venue.setAddress(venueAddress);
				venue.setBallsum(ballSum);
				// 初始化场馆数据
				initVenue(venue);
				
				// 记录场馆操作日志
				VenueLog venueLog = new VenueLog();
				venueLog.setId(Utils.getUUID());
				venueLog.setCreatetime(new Date());
				venueLog.setVenueid(venue.getId());
				venueLog.setContent(staff.getName()+"在后台审核场馆入驻数据");
				venueLogService.insertSelective(venueLog);
				
				// 查询是否有基础教练数据
				TrainCoach trainCoach = trainCoachService.selectByMember(venueEnter.getMemberId());
				// 培训机构
				TrainTeam trainTeam = new TrainTeam();
				if (StringUtil.isBank(venueEnter.getTrainTeamId())) {
					// 通过审核即创建培训机构
					trainTeam.setId(Utils.getUUID());
					trainTeam.setCreateTime(new Date());
					trainTeam.setModifyTime(new Date());
					trainTeam.setTitle(venueEnter.getTrainTeamName());
					trainTeam.setLongitude(venueEnter.getLongitude());
					trainTeam.setLatitude(venueEnter.getLatitude());
					trainTeam.setTeachClass(venueEnter.getBallType() == 1 ? "网球场" : venueEnter.getBallType() == 1 ? "足球场" : venueEnter.getBallType() == 1 ? "羽毛球场" : "篮球场");
					trainTeam.setPhone(venueEnter.getMainPhone());
					trainTeam.setCityId(venue.getCityid());
					trainTeam.setAddress(venueEnter.getAddress());
					trainTeam.setLevel(12);
					trainTeam.setLevelTime(new Date());
					trainTeamService.insertSelective(trainTeam);
					
					// 判断教练是否已经生成
					if (trainCoach == null) {
						// 生成教练数据
						trainCoach = new TrainCoach();
						trainCoach.setId(Utils.getUUID());
						trainCoach.setCreateTime(new Date());
						trainCoach.setModifyTime(new Date());
						trainCoach.setMemberId(venueEnter.getMemberId());
						trainCoach.setName(member.getAppnickname());
						trainCoach.setHeadImage(member.getAppavatarurl());
						trainCoachService.insertSelective(trainCoach);
					}
					venueEnter.setTrainTeamId(trainTeam.getId());
				} else {
					// 培训机构已有，则使用该机构入驻
					trainTeam = trainTeamService.selectByPrimaryKey(venueEnter.getTrainTeamId());
				}
				venue.setTrainteam(trainTeam.getId());
				updateByPrimaryKeySelective(venue);
	
				// 添加教练身份
				TrainTeamCoach trainTeamCoach = trainTeamCoachService.selectByCoachTeam(trainCoach.getId(), trainTeam.getId());
				if (trainTeamCoach != null) {
					trainTeamCoach.setManager(1);
					trainTeamCoach.setShowFlag(1);
					trainTeamCoach.setTeachType(1);
					trainTeamCoachService.updateByPrimaryKeySelective(trainTeamCoach);
				} else {
					trainTeamCoach = new TrainTeamCoach();
					trainTeamCoach.setId(Utils.getUUID());
					trainTeamCoach.setManager(1);
					trainTeamCoach.setShowFlag(1);
					trainTeamCoach.setTeachType(1);
					trainTeamCoach.setTrainCoachId(trainCoach.getId());
					trainTeamCoach.setTrainTeamId(trainTeam.getId());
					trainTeamCoachService.insertSelective(trainTeamCoach);
				}
				
				// 生成培训机构管理的场馆数据
				TrainTeamVenue trainTeamVenue = new TrainTeamVenue();
				trainTeamVenue.setId(Utils.getUUID());
				trainTeamVenue.setTrainVenueId(venue.getId());
				trainTeamVenue.setTrainTeamId(venueEnter.getTrainTeamId());
				trainTeamService.saveTrainTeamVenue(trainTeamVenue);
			}else {
				venueEnter.setCheckFlag(check);
			}
			venueEnterService.updateByPrimaryKeySelective(venueEnter);
			return new ApiMessage(200, "审核成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiMessage(400, "审核失败");
		}
	}


	/**
	 * @Description: 根据场馆编号查询场馆
	 * @author 宋高俊
	 * @param venueNo
	 * @return
	 * @date 2018年12月13日下午7:18:57
	 */
	@Override
	public Venue selectByVenueNo(String venueNo) {
		return venueMapper.selectByVenueNo(venueNo);
	}
}
