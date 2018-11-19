package com.xiaoyi.ssm.wxPay;

import com.thoughtworks.xstream.XStream;

/**  
 * @Description: 用于回复微信公众号消息的对象
 * @author 宋高俊  
 * @date 2018年8月30日 下午7:24:29 
 */ 
public class ReplyTextMsg extends ReplyBaseMessage {

    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
    /**
     * 将对象转换为XML
     * @return
     */
    public String Msg2Xml(){
        XStream xstream=new XStream();
        xstream.alias("xml", this.getClass());
        return xstream.toXML(this);
    }
}
