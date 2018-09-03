package com.xiaoyi.ssm.wxPay;

/**  
 * @Description: 用于回复微信公众号消息的对象
 * @author 宋高俊  
 * @date 2018年8月30日 下午7:23:44 
 */ 
public class ReplyBaseMessage {

	protected String ToUserName;
	protected String FromUserName;
	protected String CreateTime;
	protected String MsgType;

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	/**
	 * 返回时间戳给微信服务器
	 */
	public void setCreateTime() {
		CreateTime = String.valueOf(System.currentTimeMillis()).substring(0, 10);

	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public String getMsgType() {
		return MsgType;
	}
}