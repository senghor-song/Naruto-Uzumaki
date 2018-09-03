package com.xiaoyi.ssm.coreFunction;

import com.xiaoyi.ssm.util.HTTPSTrustManager;

/**
 * @Description: 深圳房信网接口功能代码
 * @author 宋高俊
 * @date 2018年7月16日 上午9:35:51
 */
public class SzhomeUtil {
	public static void main(String[] args) {

		HTTPSTrustManager.allowAllSSL();// 信任所有证书
	}
}
