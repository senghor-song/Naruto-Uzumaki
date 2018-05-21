package com.ruiec.springboot.util;

import com.github.pagehelper.PageInfo;

/**
 * 跳页工具类
 * @author 宋高俊
 * Date:2017年8月16日
 * Version 1.0
 */
public class PageSkip {
	public static PageBean PageSkip(PageBean page,PageInfo page2) {
		//判断输入的页数是否小于1，是则跳转到第一页
		if (page.getCurrentPage()<1) {
			page.setCurrentPage(1);
		}
		//判断输入的页数是否存在，不存在则跳转到最后一页
		if (page.getCurrentPage()>page.getPageSize()) {
			page.setCurrentPage(page2.getPages());
		}
		return page;
	}
}
