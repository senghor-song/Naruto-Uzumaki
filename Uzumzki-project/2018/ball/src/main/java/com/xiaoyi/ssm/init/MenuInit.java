package com.xiaoyi.ssm.init;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.ResourceUtils;

import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.util.ParseXMLUtils;

/**
 * @Description:
 * @author 宋高俊
 * @date 2018年11月6日 下午2:27:11
 */
public class MenuInit implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = Logger.getLogger(MenuInit.class);

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() != null) {
			return;
		}
		LOGGER.info("初始创建菜单开始 ------------------>");
		try {
			File xmlFile = ResourceUtils.getFile("classpath:menus.xml");
			// xml转map
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(xmlFile);
			Element incomingForm = document.getRootElement();
			Map menus = ParseXMLUtils.Dom2Map(incomingForm, false);

			List<Map> menuList = (List<Map>) menus.get("menu");
			for (Map menu : menuList) {
				Permission permission = new Permission();
				permission.setId(menu.getOrDefault("id", "id").toString());
			}

			Iterator entries = menus.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				String key = (String) entry.getKey();
				List<Map> value = (List<Map>) entry.getValue();
				for (Map map : value) {
					createMenu(map);
				}

				System.out.println("Key = " + key + ", Value = " + value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("初始创建菜单开始 ------------------>");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void createMenu(Map map) {
		// System.out.println("map >>> "+ menus);
		Iterator entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			String key = (String) entry.getKey();
			if ("menuSon".equals(key)) {
				List<Map> value = (List<Map>) entry.getValue();
				for (Map sonMap : value) {
					createMenu(sonMap);
				}
				System.out.println("Key = " + key + ", Value = " + value);
			} else {
				Object value = entry.getValue();
				System.out.println("Key = " + key + ", Value = " + value);
				Object parentId = map.getOrDefault("parentId", "无");
				System.out.println(parentId);
			}

		}
	}
}
