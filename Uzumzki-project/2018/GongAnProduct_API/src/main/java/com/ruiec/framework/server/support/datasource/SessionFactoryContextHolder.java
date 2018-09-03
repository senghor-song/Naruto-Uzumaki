/**
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.datasource;

/**
 * sessionFactory切换工具类(线程安全)
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2016年1月4日
 */
public abstract class SessionFactoryContextHolder {

	public static final String MAIN = "sessionFactory1";
	public static final String SLAVE = "sessionFactory2";
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setCustomerType(String customerType) {  
        contextHolder.set(customerType);
    }  
     
	public static void setSlave(){
		contextHolder.set(SLAVE);
	}
	
    public static String getCustomerType() {  
        return contextHolder.get();  
    }  
      
    public static void clearCustomerType() {  
        contextHolder.remove();  
    }  
}
