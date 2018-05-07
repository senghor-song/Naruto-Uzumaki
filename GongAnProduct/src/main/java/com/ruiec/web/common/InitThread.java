package com.ruiec.web.common;
/**
 * 异步初始化缓存
 * @author qinzhitian<br>
 * @date 2018年1月23日 下午5:35:38
 */
public class InitThread implements Runnable{

	private Integer type = 0;
	/**
	 * 1：单位
	 * 2：警员
	 * 3：字典
	 * @param i
	 */
	public InitThread(Integer i) {
		this.type = i;
	}
	@Override
	public void run() {
		switch (type) {
		case 1:
			RedisUtil.initUnit();
			break;
		case 2:
			RedisUtil.initUser();
			break;
		case 3:
			RedisUtil.initDictionary();
			break;
		default:
			break;
		}
		
	}

}
