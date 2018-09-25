package com.ruiec.web.enumeration;

/**
 * 预警级别颜色枚举类
 * @author qinzhitian<br>
 * @date 2018年1月9日 上午10:42:18
 */
public enum WarningColor {
	
	RED("红", 1), 
	ORANGE("橙", 2), 
	YELLO("黄", 3),  
	BLUE("蓝", 4);
	/** 颜色名称 */
	private String name;
	/** 颜色值 */
    private int value;  
    // 构造方法  
    private WarningColor(String name, int value) {  
        this.name = name;  
        this.value = value;  
    }  
    /**
     * 根据颜色名称返回颜色值
     * @author qinzhitian<br>
     * @date 2018年1月9日 上午10:47:20
     */
    public static String getName(int value) {  
        for (WarningColor c : WarningColor.values()) {  
            if (c.getValue() == value) {  
                return c.name;  
            }  
        }  
        return null;  
    }  
    /**
     * 根据颜色值返回颜色名称
     * @author qinzhitian<br>
     * @date 2018年1月9日 上午10:48:59
     */
    public static int getValue(String name) {  
        for (WarningColor c : WarningColor.values()) {  
            if (c.getName().equals(name)) {  
                return c.value;  
            }  
        }  
        return 0;
    } 
    /** 颜色名称 */
    public String getName() {  
        return name;  
    } 
    /** 颜色名称 */
    public void setName(String name) {  
        this.name = name;  
    }  
	/** 颜色值 */
    public int getValue() {  
        return value;  
    }  
	/** 颜色值 */
    public void setValue(int value) {  
        this.value = value;  
    }  
}
