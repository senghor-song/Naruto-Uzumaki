package com.xiaoyi.ssm.zfhPay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/* * 
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”
 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */
public class AlipayConfig {
	//ServiceURL
	public static String ServiceUrl="https://openapi.alipay.com/gateway.do";
	//APP支付宝支付业务：app_id
	public static String app_id = "2016081501752742";//正式
	// 商户的私钥,使用支付宝自带的openssl工具生成。
	public static String private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCenpgkxXK5fy0mZR6CUlAEoRIwyhPKKb5DN+HsW5Y/DY7/1TjmSljJDwq+TctnnORN8N+v1QYkUb3j3yPGotS+HYYfKuGOHbOsm+3BTEr4oJyC0GhOJZLA2vpXARm17OTixtO0bZL5Xp2cmKbxR+L9RNjSw/1RyssY0pGEYkn9/oqOtVc3oFyluQtsmg5hfg5Lequr2G0sw6Gdo2HnpMboJqEBxazO5zBMfiZT/SnxJKtvOMULoJgnsSA1kqhCUmJ5sxoq3B5ZlXuAERMiquBWdX3QyzxurfiMoW6ijIWJsp5eCND0z3LiMda3cbsuQDzHd+tPeEL31MMqU8h/W+O9AgMBAAECggEAMX4o2OhnCKw/CUjqAR3v69HaXDDhheYKgOzR6K8XLWtsoDUGwQyXsXypDII3oofY0N0E3tdoSDLdPlnkztkF6qBJtjXqFS79XjF1OSg2CGgNDeA7e47LwLNTA4zLWfGl5YDGk/jQsFCueytCg+y7Te2KfhE7XKcbQg6J6zXEAqVJBb2MxXzWz3t1mZmSvWvY53a6mfgLsW7MvY0mhe89OccH2DVh650hAa4NjA99f2S4OczqfSF+pd4K9Sm5DRvR+MoVlVJAKQcVgUMtQ8thNB6DRJlfVoG2sQQEiOpdltJlsjCUW7bZJMDI3W43cJ7SSNGJUbXxtO5840D2QWjmXQKBgQDPg38evAqS+ayAUqZ88EcR3CjyXTs61s+Kx2m65xYVNIy/5Ak0MHsAWzEsumaoNwZeiWrpUOgo23KX96P+5C9HjRFQBnq4RGjb3OrqWieLmYB2TT1xD9LiXSjJJ3JdcDSV6rrE7pPTkoRVKNq56ZyxaE2N8CJPpfkA2uspy8gXqwKBgQDDrnoN7i0Juj36y3FkT76JhP5/P3upn3IwvyRBrDhfvWNAW3oRrPGoE6oZwnyit0YaVvLZIyqPbe/HFaAQoEnzUbv8s3lhCLo4AEgRCQCogjjG7V5ZL8vi3HMBiJYZtoSGG7gGiZENq7fNKzNuTDWkSfkgQHXxlMhE76cyHrZqNwKBgD75yC1aodbi4J/89Tu7a8YWy3JqKtx7bnbOd97Z3JDI5uIhYU2uXNXJ1w2c8CSitlMgXwq5nAsyNf+6jtF/DH4paJShssKTOENpMv22KIQsLJk6bagPRk+eOA974jBLKhpJJy82vDQcJf72LBL5u6z3W3n042TKmNvSlEWV636LAoGASgDlNfl6XPBrWjqwYoBqDbYXTWZjzS140QionJGWLFcCfydZTe/64Fa3gNB1tE/dmj6Bzy6mfOHVyZPbGg6UiQnDL3lskQ7s5hrtJkwiTJgbAthoUxBpah12/QB7y7OkANfgy9Ag5GupslUBOVhm/P/g0JqwQDHFX5TGxYSp9hcCgYAXV6qm8D+2S93YppO2KwU9mQd571ukPiTTbllusle2Wpy3+EJ3pnB3TcBqhn6dWxMWkF0IrO8IKYSbZgLQ7NevvJ0Koya5mXPOaZ+avXNXVsJ1z0Zg03qSzmVMQgHfBGeB1qX0Yevlhotg3Qq7749Tol1Tl5pw11E6Pj7lWHAxIg==";
	// 应用的公钥，无需修改该值
	public static String ali_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnp6YJMVyuX8tJmUeglJQBKESMMoTyim+Qzfh7FuWPw2O/9U45kpYyQ8Kvk3LZ5zkTfDfr9UGJFG9498jxqLUvh2GHyrhjh2zrJvtwUxK+KCcgtBoTiWSwNr6VwEZtezk4sbTtG2S+V6dnJim8Ufi/UTY0sP9UcrLGNKRhGJJ/f6KjrVXN6BcpbkLbJoOYX4OS3qrq9htLMOhnaNh56TG6CahAcWszucwTH4mU/0p8SSrbzjFC6CYJ7EgNZKoQlJiebMaKtweWZV7gBETIqrgVnV90Ms8bq34jKFuooyFibKeXgjQ9M9y4jHWt3G7LkA8x3frT3hC99TDKlPIf1vjvQIDAQAB";
	// 支付宝的公钥，去open.alipay.com对应应用下查看。
	public static String zfb_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsNSENswgn49cTqpArOySDdy1Ol8JJ6XkZnw5Oy66twwBDWbvUmyEoiO7Lq3zgf1vB0o5JV4rR9BUT7L0eyYCN3IPvWUQTD9Wr1V46Gaay9tLeZsCLZgqkBtbmZ6GHf/rL6HTfUGvFl+Qs1/Alh7MjqKEAr3QZhJDWaVQItG83AqNPuvGpZa1K0zSxfCeb9MuEk4qlJdhm9QSgQZKNe6ItFB+SANWbzRrXMRIuMqMkUExz42/kmPXBtnw6wdNdNZGGDLLzp3nP7Z9wiqlzvTvGxjDLGt6DHHK+0ZixbUTtYK+OSTgVjfMc6ZE7k+wkjHjX9ix1XiyxlAZlFOzUmJUtQIDAQAB";
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "UTF-8";
	
	
//	/** ServiceURL */
//	public static String ServiceUrl="https://openapi.alipaydev.com/gateway.do";
//	/** APP支付宝支付业务：app_id */
//	public static String app_id = "2016072900121542";//测试
//	/** 商户的私钥,使用支付宝自带的openssl工具生成。 */
//	public static String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQChNahLQPfK4cAe92vqPwaI48+OpOJPYoRbHHI2f4KwITtyhkLNxtultEKFqKJkcppX3gcTD+Q5DNCqYqlNXdIT27gq53ApI4tLNbgemg+P4wlR4+4nA8chefLzpbtdH933Iowc/y6kawgYB670USXIaTQaseuHTPYMCv49OQZL1MjzUbENCh7V9VV3p05IGX3YZaJcwQnkpEmowI048OwWQWcxriXOYgtvtV3b/A00nRBLuTzk7SJqbHG0f1g9frZ2sRazRy3tqKSOLu+JKPh2nlMC/J7vBhY2yRiUhTkmLzt6LMj93XgrFyv/8kf8bg/y8UFRHkxhLzgotYFcnkD5AgMBAAECggEAEvFwLi+bLn7qiXj/eE4hoXJLkHJ1mWh1LxOfpCHFpJazGpkGPpHwe388FeKj+nOxCUQwxp+mbMHBv1PKmym8JtM5x+jcOClSE+tjEE2AKKNAFBxZfsIl4xwmXVULOD0nYwPvPPULcQyWreQxPcpPS0Z5de2URJjZjL65803BHKXbL9pAzSTiDeCB3eydQc1yGQ2yIvdJCOMrNmGcvlxT2CZc1pm2/TTKftOFTMRgqgIQqYKYCGF/ZHDePHNTHXSR90ck/SL8O4H0+Q/5sGjnyTT+LRycdItHnkBWAMeK3g87Vde5n/Xru0zcRcN2SuKWUidE68TbRmuMpLdgQ2QMNQKBgQDoJwneOvws1dSF+1P0Q6bGSdcQMeTdzjnwphhDejczFHkdHVVZNQ7BgBFBgsTcaDmow4uKHIBpeMa0SIEz+PwZSFllUT/fmP8cSOkgO+JMwpezdg8S0njKD0Hnw6sBcd0mU3BVMnTVIgciQ2JUKkea/sr7fQ7JmVwHUzdW1BzvOwKBgQCxxQTif2yc+W2CYdC8bzMPofo0IsSi9O77UJ3hCEFg1Qf7dlxFI3z8GFRKnMZie6bDujMqUtQdLAMx9ZCFxmYceKtBab3cdX/dEkfQBThNsJv4AVEW7UG7ZjENE41F6YFXGhGamVPWWrg5yM3qGkHO+5wD10JezZTs8a6I2Yo1WwKBgEfpl9/0K5uD3WJneRkoP6gaxXjpy7h9omsOOGPmnuS+ycmgBYktq1V7Y10EshCIZJwHftkYfRqu4/aCoytDTGxUggSlh4Cu9w9+8mAezLn0aWOZBZBSDEKjOUoezYEUEWpG3Hw3tgMzmDwf3Xyu4CF+HciLc6I1VFGckH7gE9pzAoGBAJXRNQyt1xt0GpaHWSN1k9LY78SAiLfNNJsYlfXQv0icQN3sIlciUXMkDeXTul3FWAYcJoLEy37KWOcBzYrwaZ4VFD9MnnXtkJT/kwX9YDYZYVjhwzVPPtZwiWY71BPXdumxTqS8E6vTSO9qDB//gPavlgymnYjIOXp3NGCg9ZmBAoGBAMHy7cSfKtLF7l2zR1dQg9Z1+PQbMtR+JD50hjSyC67TfgT869K2bsw36+WSqy/pftBjikYUyVX7wJFnS5k9HI98xI4YFrSaQJVSqY0V0AnOiO/3i5gd2liom/QlLg1MNsRHlpWxOW8O0QxhmnW78+W4eJkUXYQOcTAfE/5a/Xao";
//	/** 应用的公钥，无需修改该值 */
//	public static String ali_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoTWoS0D3yuHAHvdr6j8GiOPPjqTiT2KEWxxyNn+CsCE7coZCzcbbpbRChaiiZHKaV94HEw/kOQzQqmKpTV3SE9u4KudwKSOLSzW4HpoPj+MJUePuJwPHIXny86W7XR/d9yKMHP8upGsIGAeu9FElyGk0GrHrh0z2DAr+PTkGS9TI81GxDQoe1fVVd6dOSBl92GWiXMEJ5KRJqMCNOPDsFkFnMa4lzmILb7Vd2/wNNJ0QS7k85O0iamxxtH9YPX62drEWs0ct7aikji7viSj4dp5TAvye7wYWNskYlIU5Ji87eizI/d14Kxcr//JH/G4P8vFBUR5MYS84KLWBXJ5A+QIDAQAB";
//	/** 支付宝的公钥，去open.alipay.com对应应用下查看。 */
//	public static String zfb_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyPu7gukLj0wYJQSFqW8Fe2DA1KXmuopVSsyRmhMAg6FxTPkyzv2rfrTRsLmZ0Q10SROM+ramkzNOm0yshdo15PA2ptoYz9iuQt14uRv6UkwUAepkwnNDgO8jsIgYD7IGJxcxmhQURfxcaOvDpyv+4p0jxSGgOWBXNY1VniieM6XhO60oc+p4EzXpg0S2yPXU2dhWBdf+rn7ZOj515CLIIDkp2eAyU0C7l7og7/KXRUuQ0pnl4LczuJMhCot7YF6fPmu0R01OkwaRi5ukM+dLoalDt75zq/o7h2daPEAQlnWqmRNLPXAXYFBVNYQCTv10oJRPhaYqOJK40ZNUO3ihbwIDAQAB";
//	/** 字符编码格式 目前支持 gbk 或 utf-8 */
//	public static String input_charset = "UTF-8";
	/** 设置回跳页面地址 */
	public static String return_url = "https://ball.ekeae.com/WebBackAPI/admin/common/login";
	/** 设置通知结果地址 */
	public static String notify_url = "https://ball.ekeae.com/WebBackAPI/api/common/aliPayCallBack";
	
	public static void main(String[] args) {
		AlipayClient alipayClient = new DefaultAlipayClient(ServiceUrl,app_id,private_key,"json",input_charset,zfb_public_key);
	}
}