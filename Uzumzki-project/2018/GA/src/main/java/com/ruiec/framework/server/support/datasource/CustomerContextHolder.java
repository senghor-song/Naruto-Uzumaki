package com.ruiec.framework.server.support.datasource;
/**
 * 
 * @author Xxl<br>
 * 数据源切换工具类(线程安全)
 */
public abstract class CustomerContextHolder {

	public static final String MAIN = "dataSource";
	public static final String SLAVE = "dataSource2";
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setCustomerType(String customerType) {  
        contextHolder.set(customerType);
    }  
     
	public static void setSlave(){
		contextHolder.set(SLAVE);
	}
	
    public static String getCustomerType() {
    	String key = contextHolder.get();
        return key == null? MAIN : key;  
    }  
      
    public static void clearCustomerType() {  
        contextHolder.remove();  
    }  
}
