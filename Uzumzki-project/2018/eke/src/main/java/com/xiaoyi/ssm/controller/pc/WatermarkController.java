package com.xiaoyi.ssm.controller.pc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;

/**
 * @Description: 水印控制器
 * @author 宋高俊
 * @date 2018年7月17日 下午4:15:40
 */
@Controller
@RequestMapping("/watermark")
public class WatermarkController {

	/**
	 * @Description: 个人房源操作列表
	 * @author 宋高俊
	 * @date 2018年6月26日 上午11:08:48
	 */
	@RequestMapping(value = "/watermark")
	public String watermark(Model model) {
		return "personManage/watermark/watermark";
	}

	/**
	 * @Description: 获取在线生成水印的页面
	 * @author 宋高俊
	 * @date 2018年7月17日 下午4:00:05
	 */
	@RequestMapping("/onlineGenWatermark")
	public String onlineGenWatermark() {
		return "personManage/watermark/onlineGenWatermark";
	}

	/**
	 * @Description: 获取在线生成水印的图片
	 * @author 宋高俊
	 * @date 2018年7月17日 下午4:00:05
	 */
	@RequestMapping("/getWaterMarkTemplates")
	@ResponseBody
	public JSONArray getWaterMarkTemplates() {
		String jsonStr = "[{\"id\":1,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/eee3aa4fe0d27f20ec7ac6c35aa52297.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#FF0000\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":3,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/dfcb5e51ed70d4fe21af42cd5c71e3cf.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#FFFFFF\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":5,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/1c379ae6fd735be3e56c1c9cdc195a1a.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#f8e11c\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":7,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/ddc90eccea57c909eac066d3888d6c14.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#349134\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":9,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/1aba98b10fd2a16a2fdef6022c82e493.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#f8e11c\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":11,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/5e9b7e96768a331e87f42abef9a98a36.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#FF0000\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":13,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/24eb05a3defcb5dba6cae3251e5ee227.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#349134\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":15,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/276543410e2a27210e691582879cadc7.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#FF0000\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":17,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/abb2cfc7b54c4a3e705985f244c003c0.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#FF0000\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":19,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/af4f6ef0865fe70e45804ff075b0c4f0.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#FF0000\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":21,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/b5ae2fc981b5c71fd06f9344cc58da46.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#FF0000\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":23,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/cd66c005be28bbaaf9fa719231dc15ab.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#FF0000\",\"urlDomainToken\":\"xms-cimg\"},{\"id\":25,\"url\":\"http://ali.aimg.fcxms.com/watermark/tmpl/d42789b6d7fc693c7d1909953bdf615f.png\",\"text\":\"房小蜜 XXXXXXXXXXX\",\"fontName\":\"微软雅黑\",\"fontSize\":18,\"color\":\"#f8e11c\",\"urlDomainToken\":\"xms-cimg\"}]";
		JSONArray json = JSONArray.fromObject(jsonStr); // 将字符串{“id”：1}
		return json;
	}

}
