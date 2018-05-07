package com.ruiec.web.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.RedisUtil;

/**
 * 初始化控制器
 * 
 * @author Senghor<br>
 * @date 2018年1月20日 上午8:46:45
 */
@Controller
@RequestMapping("/admin/init")
public class InitController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(InitController.class);

	/**
	 * 初始化所有缓存
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月20日 上午8:48:08
	 */
	@RequestMapping(value = { "initRedis" })
	@ResponseBody
	public JsonReturn initRedis() {
		try {
			RedisUtil.initUser();
			RedisUtil.initUnit();
			RedisUtil.initDictionary();
			RedisUtil.initUserUnit();
			RedisUtil.initApiConfig();
			return new JsonReturn(200, "初始化成功");
		} catch (Exception e) {
			LOGGER.error("初始化失败" + e);
			return new JsonReturn(200, "初始化失败");
		}
	}

	/**
	 * 初始化字典缓存
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月20日 上午8:48:08
	 */
	@RequestMapping(value = { "initDictionary" })
	@ResponseBody
	public JsonReturn initDictionary() {
		try {
			RedisUtil.initDictionary();
			return new JsonReturn(200, "初始化成功");
		} catch (Exception e) {
			LOGGER.error("初始化失败" + e);
			return new JsonReturn(200, "初始化失败");
		}
	}

	/**
	 * 初始化单位缓存
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月20日 上午8:48:08
	 */
	@RequestMapping(value = { "initUnit" })
	@ResponseBody
	public JsonReturn initUnit() {
		try {
			RedisUtil.initUnit();
			return new JsonReturn(200, "初始化成功");
		} catch (Exception e) {
			LOGGER.error("初始化失败" + e);
			return new JsonReturn(200, "初始化失败");
		}
	}

	/**
	 * 初始化民警缓存
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月20日 上午8:48:08
	 */
	@RequestMapping(value = { "initUser" })
	@ResponseBody
	public JsonReturn initUser() {
		try {
			RedisUtil.initUser();
			return new JsonReturn(200, "初始化成功");
		} catch (Exception e) {
			LOGGER.error("初始化失败" + e);
			return new JsonReturn(200, "初始化失败");
		}
	}

	/**
	 * 初始化管理员缓存
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月20日 上午8:48:08
	 */
	@RequestMapping(value = { "initUserUnit" })
	@ResponseBody
	public JsonReturn initUserUnit() {
		try {
			RedisUtil.initUserUnit();
			return new JsonReturn(200, "初始化成功");
		} catch (Exception e) {
			LOGGER.error("初始化失败" + e);
			return new JsonReturn(200, "初始化失败");
		}
	}

	/**
	 * 初始化api缓存
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月20日 上午8:48:08
	 */
	@RequestMapping(value = { "initApiConfig" })
	@ResponseBody
	public JsonReturn initApiConfig() {
		try {
			RedisUtil.initApiConfig();
			return new JsonReturn(200, "初始化成功");
		} catch (Exception e) {
			LOGGER.error("初始化失败" + e);
			return new JsonReturn(200, "初始化失败");
		}
	}
}
