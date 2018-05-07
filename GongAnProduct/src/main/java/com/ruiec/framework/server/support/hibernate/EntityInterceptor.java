/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.hibernate;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

/**
 * 实体拦截器<br>
 * 自动填充创建日期<br>
 * 自动修改更新日期<br>
 * 分表以后, 切换表名的处理<br>
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2016年1月4日
 */
@Repository("entityInterceptor")
public class EntityInterceptor extends EmptyInterceptor {
	
	private static final long serialVersionUID = 6438944651205009083L;
	
//	private static final ThreadLocal<TableNameInfo[]> contextHolder = new ThreadLocal<TableNameInfo[]>();
//	
//	/**
//	 * 设置表名替换信息
//	 * @author 肖学良<br>
//	 * Date: 2015年12月8日
//	 */
//	public static void setTableNameInfo(TableNameInfo ... tableNameInfos) {  
//        contextHolder.set(tableNameInfos);
//    }  
//	/**
//	 * 清除表名替换信息
//	 * @author 肖学良<br>
//	 * Date: 2015年12月8日
//	 */
//    public static void clearTableNameInfos() {  
//        contextHolder.remove();  
//    }  
//	
//	//保存前回调
//	@Override
//	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//		Date date = new Date();
//		for(int i=0;i<propertyNames.length;i++){
//			if("createDate".equals(propertyNames[i])){
//				state[i] = date;
//			}
//			if("modifyDate".equals(propertyNames[i])){
//				state[i] = date;
//			}
//			if(state[i] instanceof String){
//				if("".equals(state[i].toString().trim())){
//					state[i] = null;
//				}
//			}
//		}
//		return true;
//	}
//	//更新前回调
//	@Override
//	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
//		Date date = new Date();
//		for(int i=0;i<propertyNames.length;i++){
//			if("modifyDate".equals(propertyNames[i])){
//				currentState[i] = date;
//			}
//			if(currentState[i] instanceof String){
//				if("".equals(currentState[i].toString().trim())){
//					currentState[i] = null;
//				}
//			}
//		}
//		return true;
//	}
//	//删除前回调
//	@Override
//	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//		
//		super.onDelete(entity, id, state, propertyNames, types);
//	}
//	
//	@Override
//	public String onPrepareStatement(String sql) {
//		
//		TableNameInfo[] tableNameInfos = contextHolder.get();
//		if(tableNameInfos != null && tableNameInfos.length > 0){
//			String tempSql = sql;
//			for(TableNameInfo tableNameInfo : tableNameInfos){
//				tempSql = tempSql.replaceAll(tableNameInfo.getFrom(), tableNameInfo.getTo());
//			}
//			return tempSql;
//		}
//		return super.onPrepareStatement(sql);
//	}
//	private static final ThreadLocal<Set<TableNameInfo>> contextHolder = new ThreadLocal<Set<TableNameInfo>>();
	
	/**
	 * 增加一个替换表名
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
//	public static void addTableNameInfo(TableNameInfo... tableNameInfos) {
//		Set<TableNameInfo> tableNameInfoSet=contextHolder.get();
//		if(tableNameInfoSet==null)
//			contextHolder.set(new HashSet<TableNameInfo>());
//		tableNameInfoSet=contextHolder.get();
//		for(TableNameInfo t:tableNameInfos){
//			tableNameInfoSet.add(t);
//		}
//	}
	
	/**
	 * 清除一个替换表名
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
//	public static boolean deleteTableNameInfo(TableNameInfo tableNameInfo) {
//		Set<TableNameInfo> tableNameInfoSet=contextHolder.get();
//		if(tableNameInfoSet==null)
//			contextHolder.set(new HashSet<TableNameInfo>());
//		tableNameInfoSet=contextHolder.get();
//		return tableNameInfoSet.remove(tableNameInfo);
//	}
	
	/**
	 * 清除全部替换表名
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
//	public static void clearTableNameInfos() {
//		Set<TableNameInfo> tableNameInfoSet=contextHolder.get();
//		if(tableNameInfoSet==null)
//			contextHolder.set(new HashSet<TableNameInfo>());
//		tableNameInfoSet=contextHolder.get();
//		tableNameInfoSet.clear();
//	}
	
	/**
	 * 查询前调用该方法，对表名进行替换
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
//	@Override
//	public String onPrepareStatement(String sql) {
//		Set<TableNameInfo> tableNameInfoSet=contextHolder.get();
//		if(tableNameInfoSet==null)
//			contextHolder.set(new HashSet<TableNameInfo>());
//		tableNameInfoSet=contextHolder.get();
//		if(tableNameInfoSet.size()>0){
//			String tempSql = sql;
//			for(TableNameInfo tableNameInfo : tableNameInfoSet){
//				tempSql = tempSql.replaceAll(tableNameInfo.getFrom(), tableNameInfo.getTo());
//			}
//			
//			return tempSql;
//		}
//		return super.onPrepareStatement(sql);
//	}
	
	private static final ThreadLocal<Map<String, String>> contextHolder = new ThreadLocal<Map<String, String>>();
	
	/**
	 * 增加一个替换表名
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
	public static void addTableNameInfo(TableNameInfo... tableNameInfos) {
		Map<String, String> tableNameInfoMap = contextHolder.get();
		if (tableNameInfoMap == null)
			contextHolder.set(new HashMap<String, String>());
		tableNameInfoMap = contextHolder.get();
		for (TableNameInfo t : tableNameInfos) {
			tableNameInfoMap.put(t.getFrom(), t.getTo());
		}
	}
	
	/**
	 * 清除一个替换表名
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
	public static void deleteTableNameInfo(TableNameInfo tableNameInfo) {
		Map<String, String> tableNameInfoMap = contextHolder.get();
		if (tableNameInfoMap == null)
			contextHolder.set(new HashMap<String, String>());
		tableNameInfoMap = contextHolder.get();
		tableNameInfoMap.remove(tableNameInfo.getFrom());
	}
	
	/**
	 * 清除全部替换表名
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
	public static void clearTableNameInfos() {
		Map<String, String> tableNameInfoMap = contextHolder.get();
		if (tableNameInfoMap == null)
			contextHolder.set(new HashMap<String, String>());
		tableNameInfoMap = contextHolder.get();
		tableNameInfoMap.clear();
	}
	
	/**
	 * 查询前调用该方法，对表名进行替换
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
	/*@Override
	public String onPrepareStatement(String sql) {
		Map<String, String> tableNameInfoMap = contextHolder.get();
		if (tableNameInfoMap == null)
			contextHolder.set(new HashMap<String, String>());
		tableNameInfoMap = contextHolder.get();
		if (tableNameInfoMap.size() > 0) {
			String tempSql = sql;
			for (String from : tableNameInfoMap.keySet()) {
				//增加空格处理，为了防止表名有相似的但其实不应该被替换，比如说表名字为为chargerecord,和charge,charge表会替换为charge_2016,
				//但是chargerecord不需要，如果不增加空格chargerecord会被替换为charge_2016record，而此表是不存在的，会报错
				tempSql = tempSql.replaceAll(from+" ", tableNameInfoMap.get(from)+" ");
			}
			return tempSql;
		}
		return super.onPrepareStatement(sql);
	}*/
	
	/**
	 * 保存前回调
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		Date date = new Date();
		for (int i = 0; i < propertyNames.length; i++) {
			if ("createDate".equals(propertyNames[i])) {
				state[i] = date;
			}
			if ("modifyDate".equals(propertyNames[i])) {
				state[i] = date;
			}
			if (state[i] instanceof String) {
				if ("".equals(state[i].toString().trim())) {
					state[i] = null;
				}
			}
		}
		return true;
	}
	
	/**
	 * 更新前回调
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		Date date = new Date();
		for (int i = 0; i < propertyNames.length; i++) {
			if ("modifyDate".equals(propertyNames[i])) {
				currentState[i] = date;
			}
			if (currentState[i] instanceof String) {
				if ("".equals(currentState[i].toString().trim())) {
					currentState[i] = null;
				}
			}
		}
		return true;
	}
	
	/**
	 * 删除前回调
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		super.onDelete(entity, id, state, propertyNames, types);
	}
	
}
