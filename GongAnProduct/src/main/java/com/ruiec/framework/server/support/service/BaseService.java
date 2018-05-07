/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Set;
import com.ruiec.framework.server.support.query.Sort;
/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 基础服务接口
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月11日
 */
public interface BaseService<T, ID extends Serializable> {

	/**
	 * 根据主键id查询指定对象<br><br>
	 * 注意事项：不提供指定关联对象抓取/初始化策略
	 * @param id 主键，可选
	 * @return 存在则返回该对象，反之返回null
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	abstract T get(Integer id);
	/**
	 * 根据主键id查询指定对象<br><br>
	 * 注意事项：按指定的抓取/初始化策略加载关联对象<br><br>
	 * 参数获取示例：<br>
	 * InitPathBuilder.create().add("tags.tagType").build()<br>
	 * Fetch.join("tags")
	 * @param id 主键，可选
	 * @param initPathSet 初始化路径,建议使用treeSet，可选
	 * @param fetchs 关联抓取策略，可选
	 * @return 存在则返回该对象，反之返回null
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	abstract T get(Integer id, java.util.Set<String> initPathSet, Fetch ...fetchs);
	/**
	 * 按过滤条件查询单条记录<br><br>
	 * 注意事项：需要保证查询结果的唯一性，否则出错，不提供指定关联对象抓取/初始化策略
	 * @param filters 过滤条件，必须
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	abstract T get(List<Filter> filters);
	/**
	 * 按过滤条件查询单条记录<br><br>
	 * 注意事项：按指定的抓取/初始化策略加载关联对象，需要保证查询结果的唯一性，否则出错<br><br>
	 * 参数获取示例：<br>
	 * InitPathBuilder.create().add("tags.tagType").build()<br>
	 * Fetch.join("tags")
	 * @param filters 过滤条件，必须
	 * @param initPathSet 初始化路径,建议使用treeSet，可选
	 * @param fetchs 关联抓取策略，可选
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	abstract T get(List<Filter> filters, java.util.Set<String> initPathSet, Fetch ...fetchs);
	/**
	 * 查询所有对象<br><br>
	 * 注意事项：不提供关联对象抓取/初始化策略
	 * @return 返回装载所有对象的List集合，为空则List的长度为0
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	abstract List<T> getAll();
	/**
	 * 查询所有对象<br><br>
	 * 注意事项：按指定的抓取/初始化策略加载关联对象<br><br>
	 * 参数获取示例：<br>
	 * InitPathBuilder.create().add("tags.tagType").build()<br>
	 * Fetch.join("tags")
	 * @param initPathSet 初始化路径,建议使用treeSet，可选
	 * @param fetchs 关联抓取策略，可选
	 * @return 返回装载所有对象的List集合，为空则List的长度为0
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	abstract List<T> getAll(java.util.Set<String> initPathSet, Fetch ...fetchs);
	/**
	 * 根据主键id查询指定对象<br><br>
	 * 注意事项：不提供指定关联对象抓取策略
	 * @param ids 主键数组，可选
	 * @return 返回装载所有符合条件对象的List集合，为空则List的长度为0
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	abstract List<T> getByIds(Integer[] ids);
	/**
	 * 根据主键id查询指定对象<br><br>
	 * 注意事项：按指定的策略加载关联对象<br><br>
	 * 参数获取示例：<br>
	 * InitPathBuilder.create().add("tags.tagType").build()<br>
	 * Fetch.join("tags")
	 * @param ids 主键数组，可选
	 * @param initPathSet 初始化路径，建议使用treeSet，可选
	 * @param fetchs 关联抓取策略，可选
	 * @return 返回装载所有符合条件对象的List集合，为空则List的长度为0
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	abstract List<T> getByIds(Integer[] ids, java.util.Set<String> initPathSet, Fetch ...fetchs);
	/**
	 * 查询总记录数
	 * @return 返回总记录数，没有则为0
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	abstract Long getCount();
	/**
	 * 查询符合条件的总记录数<br><br>
	 * 参数获取示例：<br>
	 * Filter.build().add(Filter.eq("key", "value")).build()
	 * @param filters 过滤条件，可选
	 * @return 返回总记录数，没有则为0
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	abstract Long getCount(List<Filter> filters);
	/**
	 * 保存对象<br><br>
	 * 注意事项：<br>
	 * 1、因序列化的关系，获取保存后对象的主键必须从return返回的对象中获取<br>
	 * 2、一对多关系的对象可级联保存<br>
	 * 3、多对一关系的对象只需持有主键便可关联
	 * @param entity 待保存对象
	 * @return 返回保存后的对象（带主键）
	 * @author 肖学良<br>
	 * Date: 2015年12月16日
	 */
	abstract T save(T entity);
	/**
	 * 更新对象<br><br>
	 * 注意事项：<br>
	 * 1、级联更新时应考虑关联对象或集合是否已为null/空/集合未初始化（多对一，一方须有主键值），<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以避免出现非预期的删除操作<br>
	 * 2、一对多，多对一<br>
	 * 3、不建议使用多对多级联更新操作
	 * @param entity 待更新对象
	 * @author 肖学良<br>
	 * Date: 2015年12月16日
	 */
	abstract void update(T entity);
	/**
	 * 更新对象（排除指定简单类型字段，包含指定复合类型字段）<br><br>
	 * 注意事项：<br>
	 * 1、级联更新时应考虑关联对象或集合是否已为null/空/集合未初始化（多对一，一方须有主键值），<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以避免出现非预期的删除操作<br>
	 * 2、一对多，多对一<br>
	 * 3、不建议使用多对多级联更新操作<br>
	 * @param entity 待更新对象
	 * @param ignoreSimpleProperties 排除更新的简单类型字段
	 * @param includeCompositeProperties 待更新的复合类型字段（强制拷贝）
	 * @author 肖学良<br>
	 * Date: 2015年12月16日
	 */
	abstract void updateIgnore(T entity, String[] ignoreSimpleProperties, String[] includeCompositeProperties);
	/**
	 * 更新对象（包含指定简单类型字段，包含复合类型字段,且简单类型数组字段优先级高于复合类型数组字段）<br><br>
	 * 注意事项：<br>
	 * 1、级联更新时应考虑关联对象或集合是否已为null/空/集合未初始化（多对一，一方须有主键值），<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以避免出现非预期的删除操作<br>
	 * 2、一对多，多对一<br>
	 * 3、不建议使用多对多级联更新操作
	 * @param entity 待更新对象
	 * @param includeSimpleProperties 待更新的简单类型字段
	 * @param includeCompositeProperties 待更新的复合类型字段（强制拷贝）
	 * @author 肖学良<br>
	 * Date: 2015年12月16日
	 */
	abstract void updateInclude(T entity, String[] includeSimpleProperties, String[] includeCompositeProperties);
	/**
	 * 自定义更新接口（hql）<br><br>
	 * 注意事项：只可更新当前对象内字段且不能是一对多类型，支持修改对多对一关联关系字段（外键值），不支持连表过滤条件<br><br>
	 * 参数获取示例：<br>
	 * Set.build().add(Set.single("key", "value")).build()<br>
	 * Filter.build().add(Filter.eq("key", "value")).build()
	 * @param sets 更新集，不可以为null或空
	 * @param filters 过滤条件，可以为null
	 * @return 返回受影响的记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	abstract int update(List<Set> sets, List<Filter> filters);
	/**
	 * 删除指定对象记录（hql）
	 * @param entity 待删除对象
	 * @return 返回受影响记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract int delete(T entity);
	/**
	 * 删除指定id记录（hql）
	 * @param id 主键
	 * @return 返回受影响记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract int delete(Integer id);
	/**
	 * 删除指定id记录（hql）
	 * @param ids 主键数组
	 * @return 返回受影响记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract int delete(Integer[] ids);
	/**
	 * 按过滤条件删除记录(hql)<br><br>
	 * 参数获取示例：<br>
	 * Filter.build().add(Filter.eq("key", "value")).build()
	 * @param filters 过滤条件，可以为null
	 * @return 返回受影响记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月28日
	 */
	abstract int delete(List<Filter> filters);
	/**
	 * 删除指定对象记录（非hql）
	 * @param entity 待删除对象
	 * @return 返回受影响记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract int deleteCascade(T entity);
	/**
	 * 删除指定id记录（非hql）
	 * @param id 主键
	 * @return 返回受影响记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract int deleteCascade(Integer id);
	/**
	 * 删除指定id记录（非hql）
	 * @param ids 主键数组
	 * @return 返回受影响记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract int deleteCascade(Integer[] ids);
	
	abstract void flush();
	
	/**
	 * 通用查询接口<br><br>
	 * 注意事项：不提供关联对象抓取/初始化策略<br><br>
	 * 参数获取示例：<br>
	 * Filter.build().add(Filter.eq("key", "value")).build()<br>
	 * Sort.build().add(Sort.asc("key")).build()<br>
	 * DetachedCriteria.forClass(Entity.class)
	 * @param detachedCriteria 构造离线查询
	 * @param count 最大查询条数，为null不限制查询条数，可选
	 * @param filters 过滤条件，可选
	 * @param sorts 排序方式，可选
	 * @return 返回装载所有符合条件对象的List集合，为空则List的长度为0
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract  List<T> findList(DetachedCriteria detachedCriteria, Integer count, List<Filter> filters, List<Sort> sorts);
	/**
	 * 通用查询接口<br><br>
	 * 注意事项：按指定的抓取/初始化策略加载关联对象<br><br>
	 * 参数获取示例：<br>
	 * Filter.build().add(Filter.eq("key", "value")).build()<br>
	 * Sort.build().add(Sort.asc("key")).build()<br>
	 * InitPathBuilder.create().add("tags.tagType").build()<br>
	 * Fetch.join("tags")<br>
	 * DetachedCriteria.forClass(Entity.class)
	 * @param detachedCriteria 构造离线查询
	 * @param count 最大查询条数，可选
	 * @param filters 过滤条件，可选
	 * @param sorts 排序方式，可选
	 * @param initPathSet 初始化路径，建议使用treeSet，可选
	 * @param fetchs 关联抓取策略，可选
	 * @return 返回装载所有符合条件对象的List集合，为空则List的长度为0
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract  List<T> findList(DetachedCriteria detachedCriteria, Integer count, List<Filter> filters, List<Sort> sorts, java.util.Set<String> initPathSet, Fetch ...fetchs);
	/**
	 * 通用查询接口<br><br>
	 * 注意事项：不提供关联对象抓取/初始化策略<br><br>
	 * 参数获取示例：<br>
	 * Filter.build().add(Filter.eq("key", "value")).build()<br>
	 * Sort.build().add(Sort.asc("key")).build()<br>
	 * @param count 最大查询条数，可选
	 * @param filters 过滤条件，可选
	 * @param sorts 排序方式，可选
	 * @return 返回装载所有符合条件对象的List集合，为空则List的长度为0
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract List<T> findList(Integer count, List<Filter> filters, List<Sort> sorts);
	/**
	 * 通用查询接口<br><br>
	 * 注意事项：按指定的抓取/初始化策略加载关联对象<br><br>
	 * 参数获取示例：<br>
	 * Filter.build().add(Filter.eq("key", "value")).build()<br>
	 * Sort.build().add(Sort.asc("key")).build()<br>
	 * InitPathBuilder.create().add("tags.tagType").build()<br>
	 * Fetch.join("tags")
	 * @param count 最大查询条数，可选
	 * @param filters 过滤条件，可选
	 * @param sorts 排序方式，可选
	 * @param fetchs 关联抓取策略，可选
	 * @return 返回装载所有符合条件对象的List集合，为空则List的长度为0
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract List<T> findList(Integer count, List<Filter> filters, List<Sort> sorts, java.util.Set<String> initPathSet, Fetch ...fetchs);
	/**
	 * 通用查询接口<br><br>
	 * 注意事项：不提供关联对象抓取/初始化策略<br><br>
	 * 参数获取示例：<br>
	 * Filter.eq("key", "value")<br>
	 * Sort.asc("key")
	 * @param count 最大查询条数，可选
	 * @param filter 过滤条件，可选
	 * @param sort 排序方式，可选
	 * @return 返回装载所有符合条件对象的List集合，为空则List的长度为0
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract List<T> findList(Integer count, Filter filter, Sort sort);
	/**
	 * 通用查询接口<br><br>
	 * 注意事项：按指定的抓取/初始化策略加载关联对象<br><br>
	 * 参数获取示例：<br>
	 * Filter.eq("key", "value")<br>
	 * Sort.asc("key")<br>
	 * InitPathBuilder.create().add("tags.tagType").build()<br>
	 * Fetch.join("tags")
	 * @param count 最大查询条数，可选
	 * @param filter 过滤条件，可选
	 * @param sort 排序方式，可选
	 * @param fetchs 关联抓取策略，可选
	 * @return 返回装载所有符合条件对象的List集合，为空则List的长度为0
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract List<T> findList(Integer count, Filter filter, Sort sort, java.util.Set<String> initPathSet, Fetch ...fetchs);
	/**
	 * 分页通用查询接口<br><br>
	 * 注意事项：不提供关联对象抓取/初始化策略<br><br>
	 * 参数获取示例：<br>
	 * Page.build()<br>
	 * 				.add(Filter.eq("key", "value"))<br>
	 *			    .add(Sort.asc("key"))<br>
	 *			    .add("tags.TagType");
	 * @param page 分页对象，可选，为null则使用默认分页参数
	 * @return 返回分页对象
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract Page findByPage(Page page);
	/**
	 * 分页通用查询接口<br><br>
	 * 注意事项：按指定的抓取/初始化策略加载关联对象<br><br>
	 * 参数获取示例：<br>
	 * Page.build()<br>
	 * 				.add(Filter.eq("key", "value"))<br>
	 *			    .add(Sort.asc("key"))<br>
	 *			    .add("tags.TagType");<br>
	 * Fetch.join("tags")
	 * @param page 分页对象，可选，为null则使用默认分页参数
	 * @param fetchs 关联抓取策略
	 * @return 返回分页对象
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract Page findByPage(Page page, Fetch ...fetchs);
	/**
	 * 增强分页通用查询接口<br><br>
	 * 注意事项：不提供指定关联对象抓取策略<br><br>
	 * 参数获取示例：<br>
	 * Page.build()<br>
	 * 				.add(Filter.eq("key", "value"))<br>
	 *			    .add(Sort.asc("key"))<br>
	 *			    .add("tags.TagType");<br>
	 * DetachedCriteria.forClass(Entity.class)
	 * @param page 分页对象，为null则使用默认分页参数
	 * @param detachedCriterria 构造离线查询
	 * @return 返回分页对象
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract Page findByPage(Page page, DetachedCriteria detachedCriterria);
	/**
	 * 增强分页通用查询接口<br><br>
	 * 注意事项：按指定的策略加载关联对象<br><br>
	 * 参数获取示例：<br>
	 * Page.build()<br>
	 * 				.add(Filter.eq("key", "value"))<br>
	 *			    .add(Sort.asc("key"))<br>
	 *			    .add("tags.TagType");<br>
	 * DetachedCriteria.forClass(Entity.class)
	 * @param page 分页对象，为null则使用默认分页参数
	 * @param detachedCriterria 构造离线查询
	 * @param fetchs 关联抓取策略
	 * @return 返回分页对象
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	abstract Page findByPage(Page page, DetachedCriteria detachedCriterria, Fetch ...fetchs);
	
}