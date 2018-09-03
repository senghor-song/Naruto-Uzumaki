package com.xiaoyi.ssm.controller.pc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.coreFunction.LeJuUtil;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 测试接口控制器
 * @author 宋高俊
 * @date 2018年7月11日 下午7:47:10
 */
@RequestMapping("/test")
@Controller
public class TestController {

	/**
	 * @Description: 获取片区
	 * @author 宋高俊
	 * @date 2018年6月30日 上午11:07:12
	 */
	@RequestMapping(value = "/")
	public String test(Model model) {
		List<String> keys = LeJuUtil.sendGet("http://j.esf.leju.com/ucenter/login");
		model.addAttribute("pubkey", keys.get(1));
		return "test";
	}

	/**
	 * @Description: 安居客登录接口测试
	 * @author 宋高俊
	 * @date 2018年6月30日 上午11:07:12
	 */
	@RequestMapping(value = "/anjukeLogin")
	@ResponseBody
	public String anjukeLogin(String username, String password) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("signExpiresTime", "1800000");
		parameters.put("sid", "broker");
		parameters.put("url", "aHR0cDovL3d3dy5hbmp1a2UuY29t");
		parameters.put("history", "");
		parameters.put("password", Utils.BaseEncode(password));
		parameters.put("telphone", username);
		parameters.put("seccodetxt", "");
		parameters.put("telcode", "");
		parameters.put("verifyType", "sms");
		parameters.put("bcb41ccdc4363c6848a1d760f26c28a0", "7e9f3b4647316b0463a7eb72273e2422");
		parameters.put("se45088a1", "e84badbe93e08f70b11ff251b9bff2ef%7Cze1NdrvoKiRgdUQCyaND4A%3D%3Dae%3D%3D");
		String result = HttpUtils.sendPost("http://vip.anjuke.com/login", parameters);
		return result;
	}


	/**
	 * @Description: 乐居登录接口测试
	 * @author 宋高俊
	 * @date 2018年6月30日 上午11:07:12
	 */
	@RequestMapping(value = "/lejuLogin")
	@ResponseBody
	public String lejuLogin(String username, String password) {
		List<String> keys = LeJuUtil.sendGet("http://j.esf.leju.com/ucenter/login");
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", username);
		parameters.put("password", password);
		parameters.put("ckey", keys.get(0));
		parameters.put("imgcode", "");
		Map<String, String> map = LeJuUtil.sendPost("http://j.esf.leju.com/ucenter/login?curcity=sz", parameters);
		return map.get("html");
	}
	
	/**
	 * @Description: 乐居录入房源测试接口测试
	 * @author 宋高俊
	 * @date 2018年6月30日 上午11:07:12
	 */
	@RequestMapping(value = "/anjukeEnter")
	@ResponseBody
	public String anjukeEnter(String username, String password,String housetitle, String house_desc, String price, String buildingarea) {
		List<String> keys = LeJuUtil.sendGet("http://j.esf.leju.com/ucenter/login");
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", username);
		parameters.put("password", password);
		parameters.put("ckey", keys.get(0));
		parameters.put("imgcode", "");
		Map<String, String> map = LeJuUtil.sendPost("http://j.esf.leju.com/ucenter/login?curcity=sz", parameters);
		
		Map<String, String> parameters2 = new HashMap<String, String>();
		parameters2.put("pub", "0");
		parameters2.put("edithouse", "0");
		parameters2.put("district", "龙华");
		parameters2.put("block", "民治");
		parameters2.put("communityname", "京基御景华城");
		parameters2.put("housesite", "福田区福田华强南路与滨河路交汇处");
		parameters2.put("sina_id", "384");
		parameters2.put("communityPrice", "");
		parameters2.put("avgprice", "57064");
		parameters2.put("upPriceRange", "4.56512|100000");
		parameters2.put("downPriceRange", "4.56512|100000");
		parameters2.put("PriceLimit", "1|9999999.99");
		parameters2.put("tradetype", "1");
		parameters2.put("propername", "住宅");
		parameters2.put("deleteData", "");
		parameters2.put("tkdeleteData", "");
		parameters2.put("check_code", "");
		parameters2.put("housecode", "");
		parameters2.put("price", price);
		parameters2.put("down", "1");
		parameters2.put("downtype", "1");
		parameters2.put("downrate", "30");
		parameters2.put("downtax", "1");
		parameters2.put("downremark", "");
		parameters2.put("atype", "1");
		parameters2.put("arate", "2");
		parameters2.put("model_room", "2");
		parameters2.put("model_hall", "2");
		parameters2.put("model_toilet", "1");
		parameters2.put("buildingarea", "80");
		parameters2.put("propertype", "3");
		parameters2.put("fitment", "1");
		parameters2.put("direction", "2");
		parameters2.put("floor", "-102");
		parameters2.put("total_floor", "80");
		parameters2.put("complete", "2005");
		parameters2.put("property_rights", "0");
		parameters2.put("house_fee", "");
		parameters2.put("newtag", "");
		parameters2.put("house_state", "0");
		parameters2.put("look_time", "1");
		parameters2.put("date[]", "6");
		parameters2.put("date[]", "6");
		parameters2.put("housetitle", housetitle+"----测试时测试时测试时测试时");
		parameters2.put("house_desc", house_desc+"----测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时测试时");
		parameters2.put("pixcover", "");
		parameters2.put("pkey", "c203fc3508d8b8e9cec564188f1de659");
		parameters2.put("sign", "77c15a34347030c43a81af8fbe2913f7");
		parameters2.put("u", "3478307");
		parameters2.put("citycode", "sz");
		String result = HttpUtils.sendPost1("http://j.esf.leju.com/house/publishsubmit/?ajax=y", parameters2, map.get("cookie"));
		return result;
	}
}
