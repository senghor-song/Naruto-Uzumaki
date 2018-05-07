/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.service.impl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Transient;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ruiec.framework.server.support.datasource.SessionFactoryContextHolder;
import com.ruiec.framework.server.support.entity.BaseEntity;
import com.ruiec.framework.server.support.entity.SortEntity;
import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Filter.FilterType;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Set;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.server.support.query.Sort.SortType;
import com.ruiec.framework.server.support.service.BaseService;
/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 基础服务接口实现类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月11日
 */
public  class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

	private static final String[] COMMON_FEILDS = { "id", "createDate", "modifyDate" };
	
	private Class<T> clazz;
	
	@Resource
	private ApplicationContext context;

	public Session getSession() { 
		String key = SessionFactoryContextHolder.getCustomerType();
		if(key == null){
			key = SessionFactoryContextHolder.MAIN;
		}
		SessionFactory sessionFactory = (SessionFactory) context.getBean(key);
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public T get(Integer id) {
		if(id == null){
			return null;
		}
		return (T) getSession().get(clazz, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public T get(Integer id, java.util.Set<String> initPathSet, Fetch ...fetchs) {
		if(id == null){
			return null;
		}
		Criteria criteria = getSession().createCriteria(clazz).add(Restrictions.eq("id", id));
		processFetchs(criteria, fetchs);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		T t = (T) criteria.uniqueResult();
		init(t, initPathSet);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public T get(List<Filter> filters) {
		Assert.notEmpty(filters);
		Criteria criteria = getSession().createCriteria(clazz);
		processFilters(criteria, filters);
		return (T) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public T get(List<Filter> filters, java.util.Set<String> initPathSet, Fetch ...fetchs) {
		Assert.notEmpty(filters);
		Criteria criteria = getSession().createCriteria(clazz);
		processFilters(criteria, filters);
		processFetchs(criteria, fetchs);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		T t = (T) criteria.uniqueResult();
		init(t, initPathSet);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<T> getAll() {
		return (List<T>)getSession().createCriteria(clazz).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<T> getAll(java.util.Set<String> initPathSet, Fetch ...fetchs) {
		Criteria criteria = getSession().createCriteria(clazz);
		processFetchs(criteria, fetchs);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<T> list = (List<T>)criteria.list();
		init(list, initPathSet);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<T> getByIds(Integer[] ids){
		if(ids == null || ids.length == 0){
			return new ArrayList<T>();
		}
		return getSession().createCriteria(clazz).add(Restrictions.in("id", ids)).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<T> getByIds(Integer[] ids, java.util.Set<String> initPathSet, Fetch ...fetchs){
		if(ids == null || ids.length == 0){
			return new ArrayList<T>();
		}
		Criteria criteria = getSession().createCriteria(clazz).add(Restrictions.in("id", ids));
		processFetchs(criteria, fetchs);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<T> list = (List<T>)criteria.list();
		init(list, initPathSet);
		return list;
	}
	@Override
	@Transactional(readOnly=true)
	public Long getCount() {
		String str = "select count(*) from " + clazz.getName();
		return (Long) getSession().createQuery(str).uniqueResult();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long getCount(List<Filter> filters) {
		//字段名和值应该拼在一起，以前的代码可能没有考虑
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from ");
		sb.append(clazz.getSimpleName());
		sb.append(" this_ where 1=1");//this_是表别名
		if(filters != null && filters.size() > 0){
			for(Filter localFilter : filters){
				if (localFilter != null && localFilter.getProperty() != null) {
					if ("".equals(localFilter.getProperty())){
						continue;
					}
					if (localFilter.getFilterType() == FilterType.eq && localFilter.getValue() != null) {//已经判过空了但是还判第二次
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" = :");//这里应该直接append localFilter.getValue而不是在后面通过setParameter,迭代fiters两次
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.ne && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" != :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.gt && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" > :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.lt && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" < :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.ge && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" >= :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.le && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" <= :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());//localFilter.getValue() + "%"
					} else if (localFilter.getFilterType() == FilterType.like && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" like :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.in && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" in(:");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
						sb.append(")");
					} else if (localFilter.getFilterType() == FilterType.isNull) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" is null");
					} else if (localFilter.getFilterType() == FilterType.isNotNull) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" is not null");
					}
				}
			}
		}
		Query query = getSession().createQuery(sb.toString());
		if(filters != null && filters.size() > 0){
			for(Filter localFilter : filters){
				if (localFilter != null && localFilter.getProperty() != null) {
					if ("".equals(localFilter.getProperty())){
						continue;
					}
					if (localFilter.getFilterType() == FilterType.eq && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.ne && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.gt && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.lt && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.ge && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.le && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());//localFilter.getValue() + "%"
					} else if (localFilter.getFilterType() == FilterType.like && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), "%" + localFilter.getValue() + "%");
					} else if (localFilter.getFilterType() == FilterType.in && localFilter.getValue() != null) {
						query.setParameterList(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), ((Object[])localFilter.getValue()));
					} else if (localFilter.getFilterType() == FilterType.isNull) {
						
					} else if (localFilter.getFilterType() == FilterType.isNotNull) {
						
					}
				}
			}
		}
		return (Long) query.uniqueResult();
	}

	@Override
	@Transactional
	public T save(T entity) {
		Assert.notNull(entity);
		getSession().save(entity);
		return entity;
	}
	@Override
	@Transactional
	public void update(T entity) {
		Assert.notNull(entity);
		getSession().update(entity);
	}
	
	@Override
	@Transactional
	public void updateIgnore(T entity, String[] ignoreSimpleProperties, String[] includeCompositeProperties) {
		Assert.notNull(entity);
		if (getSession().contains(entity)) {
			throw new IllegalArgumentException("Entity must not be managed");
		}
		Object localObject = getSession().get(clazz, ((BaseEntity)entity).getId());
		if(localObject != null){
			copyPropertiesIgnore(entity, localObject, ArrayUtils.addAll(ignoreSimpleProperties, COMMON_FEILDS), includeCompositeProperties);
			getSession().update(localObject);
		}else{
			getSession().merge(entity);
		}
	}
	
	@Override
	@Transactional
	public void updateInclude(T entity, String[] includeSimpleProperties, String[] includeCompositeProperties) {
		Assert.notNull(entity);
		if (getSession().contains(entity)) {
			throw new IllegalArgumentException("Entity must not be managed");
		}
		Object localObject = getSession().get(clazz, ((BaseEntity)entity).getId());
		if(localObject != null){
			copyPropertiesInclude(entity, localObject, includeSimpleProperties, includeCompositeProperties);
			getSession().update(localObject);
		}else{
			getSession().merge(entity);
		}
	}
	
	@Override
	@Transactional
	public int update(List<Set> sets, List<Filter> filters) {
		Assert.notEmpty(sets);
		StringBuffer sb = new StringBuffer();
		sb.append("update ");
		sb.append(clazz.getSimpleName());
		sb.append(" this_ set this_.modifyDate = :modifyDate");
		for(Set set : sets){
			if(set.getName() != null){
				sb.append(", ");
				sb.append("this_.");
				sb.append(set.getName());
				sb.append(" = :");
				sb.append(set.getNameAlias() == null ? set.getName() : set.getNameAlias());
			}
		}
		
		sb.append(" where 1 = 1");
		
		if(filters != null && filters.size() > 0){
			for(Filter localFilter : filters){
				if (localFilter != null && localFilter.getProperty() != null) {
					if ("".equals(localFilter.getProperty())){
						continue;
					}
					if (localFilter.getFilterType() == FilterType.eq && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" = :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.ne && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" != :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.gt && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" > :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.lt && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" < :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.ge && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" >= :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.le && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" <= :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());//localFilter.getValue() + "%"
					} else if (localFilter.getFilterType() == FilterType.like && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" like :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.in && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" in(:");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
						sb.append(")");
					} else if (localFilter.getFilterType() == FilterType.isNull) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" is null");
					} else if (localFilter.getFilterType() == FilterType.isNotNull) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" is not null");
					}
				}
			}
		}
		Query query = getSession().createQuery(sb.toString());
		for(Set set : sets){
			if(set.getName() != null){
				if(set.getType() == Set.Type.single){
					query.setParameter(set.getNameAlias() == null ? set.getName() : set.getNameAlias(), set.getValue());
				}else{
					query.setParameterList(set.getNameAlias() == null ? set.getName() : set.getNameAlias(), ((Object[])set.getValue()));
				}
			}
		}
		if(filters != null && filters.size() > 0){
			for(Filter localFilter : filters){
				if (localFilter != null && localFilter.getProperty() != null) {
					if ("".equals(localFilter.getProperty())){
						continue;
					}
					if (localFilter.getFilterType() == FilterType.eq && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.ne && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.gt && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.lt && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.ge && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.le && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());//localFilter.getValue() + "%"
					} else if (localFilter.getFilterType() == FilterType.like && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), "%" + localFilter.getValue() + "%");
					} else if (localFilter.getFilterType() == FilterType.in && localFilter.getValue() != null) {
						query.setParameterList(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), ((Object[])localFilter.getValue()));
					} else if (localFilter.getFilterType() == FilterType.isNull) {
						
					} else if (localFilter.getFilterType() == FilterType.isNotNull) {
						
					}
				}
			}
		}
		query.setParameter("modifyDate", new Date());
		return query.executeUpdate();
	}

	@Override
	@Transactional
	public int delete(T entity) {
		Assert.notNull(entity);
		Assert.notNull(((BaseEntity)entity).getId());
		return getSession().createQuery("delete from " + clazz.getName() + " entity where entity.id = :id").setParameter("id", ((BaseEntity)entity).getId()).executeUpdate();
	}
	@Override
	@Transactional
	public int delete(Integer id) {
		Assert.notNull(id);
		return getSession().createQuery("delete from " + clazz.getName() + " entity where entity.id = :id").setParameter("id", id).executeUpdate();
	}
	@Override
	@Transactional
	public int delete(Integer[] ids) {
		Assert.notEmpty(ids);
		return getSession().createQuery("delete from " + clazz.getName() + " entity where entity.id in(:ids)").setParameterList("ids", ids).executeUpdate();
	}
	
	@Override
	@Transactional
	public int delete(List<Filter> filters) {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from ");
		sb.append(clazz.getSimpleName());
		sb.append(" this_ where 1=1");
		if(filters != null && filters.size() > 0){
			for(Filter localFilter : filters){
				if (localFilter != null && localFilter.getProperty() != null) {
					if ("".equals(localFilter.getProperty())){
						continue;
					}
					if (localFilter.getFilterType() == FilterType.eq && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" = :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.ne && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" != :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.gt && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" > :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.lt && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" < :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.ge && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" >= :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.le && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" <= :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());//localFilter.getValue() + "%"
					} else if (localFilter.getFilterType() == FilterType.like && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" like :");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
					} else if (localFilter.getFilterType() == FilterType.in && localFilter.getValue() != null) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" in(:");
						sb.append(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias());
						sb.append(")");
					} else if (localFilter.getFilterType() == FilterType.isNull) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" is null");
					} else if (localFilter.getFilterType() == FilterType.isNotNull) {
						sb.append(" and ");
						sb.append("this_.");
						sb.append(localFilter.getProperty());
						sb.append(" is not null");
					}
				}
			}
		}
		Query query = getSession().createQuery(sb.toString());
		if(filters != null && filters.size() > 0){
			for(Filter localFilter : filters){
				if (localFilter != null && localFilter.getProperty() != null) {
					if ("".equals(localFilter.getProperty())){
						continue;
					}
					if (localFilter.getFilterType() == FilterType.eq && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.ne && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.gt && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.lt && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.ge && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());
					} else if (localFilter.getFilterType() == FilterType.le && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), localFilter.getValue());//localFilter.getValue() + "%"
					} else if (localFilter.getFilterType() == FilterType.like && localFilter.getValue() != null) {
						query.setParameter(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), "%" + localFilter.getValue() + "%");
					} else if (localFilter.getFilterType() == FilterType.in && localFilter.getValue() != null) {
						query.setParameterList(localFilter.getPropertyAlias() == null ? localFilter.getProperty() : localFilter.getPropertyAlias(), ((Object[])localFilter.getValue()));
					} else if (localFilter.getFilterType() == FilterType.isNull) {
						
					} else if (localFilter.getFilterType() == FilterType.isNotNull) {
						
					}
				}
			}
		}
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public int deleteCascade(T entity) {
		Assert.notNull(entity);
		Assert.notNull(((BaseEntity)entity).getId());
		T temp = (T) getSession().get(clazz, ((BaseEntity)entity).getId());
		if(temp == null){
			return 0;
		}
		getSession().delete(temp);
		return 1;
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public int deleteCascade(Integer id) {
		Assert.notNull(id);
		T temp = (T) getSession().get(clazz, id);
		if(temp == null){
			return 0;
		}
		getSession().delete(temp);
		return 1;
	}
	@Override
	@Transactional
	public int deleteCascade(Integer[] ids) {
		Assert.notEmpty(ids);
		int count = 0;
		for(Integer id : ids){
			count += deleteCascade(id);
		}
		return count;
	}

	public void flush() {
		getSession().flush();
	}
	@Override
	@Transactional(readOnly=true)
	public List<T> findList(Integer count, Filter filter, Sort sort) {
		List<Filter> filters = null;
		if(filter != null){
			filters = new ArrayList<Filter>();
			filters.add(filter);
		}
		List<Sort> sorts = null;
		if(sort != null){
			sorts = new ArrayList<Sort>();
			sorts.add(sort);
		}
		return findList(count, filters, sorts);
	}
	@Override
	@Transactional(readOnly=true)
	public List<T> findList(Integer count, Filter filter, Sort sort, java.util.Set<String> initPathSet, Fetch ...fetchs) {
		List<Filter> filters = null;
		if(filter != null){
			filters = new ArrayList<Filter>();
			filters.add(filter);
		}
		List<Sort> sorts = null;
		if(sort != null){
			sorts = new ArrayList<Sort>();
			sorts.add(sort);
		}
		return findList(count, filters, sorts, initPathSet, fetchs);
	}
	@Override
	@Transactional(readOnly=true)
	public List<T> findList(Integer count, List<Filter> filters, List<Sort> sorts){
		return findList(DetachedCriteria.forClass(clazz), count, filters, sorts);
	}
	@Override
	@Transactional(readOnly=true)
	public List<T> findList(Integer count, List<Filter> filters, List<Sort> sorts, java.util.Set<String> initPathSet, Fetch ...fetchs){
		return findList(DetachedCriteria.forClass(clazz), count, filters, sorts, initPathSet, fetchs);
	}
	@Override
	@Transactional(readOnly=true)
	public List<T> findList(DetachedCriteria detachedCriteria, Integer count, List<Filter> filters, List<Sort> sorts){
		return findList(detachedCriteria, count, filters, sorts, null, new Fetch[]{});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional(readOnly=true)
	public List<T> findList(DetachedCriteria detachedCriteria, Integer count, List<Filter> filters, List<Sort> sorts, java.util.Set<String> initPathSet, Fetch ...fetchs){
		Criteria localCriteria = detachedCriteria.getExecutableCriteria(getSession());
		
		//处理过滤集
		processFilters(localCriteria, filters);
		
		//处理抓取策略
		processFetchs(localCriteria, fetchs);
		
		//处理排序集
		if(sorts!=null&&sorts.size()>0){
			Iterator<Sort> iterator=sorts.iterator();
			while(iterator.hasNext()){
				Sort sort =iterator.next();
				if(sort!=null&&sort.getProperty()!=null&&sort.getSortType()!=null){
					if(sort.getSortType()==SortType.asc){
						localCriteria.addOrder(Order.asc(sort.getProperty()));
					}else if(sort.getSortType()==SortType.desc){
						localCriteria.addOrder(Order.desc(sort.getProperty()));
					}
				}
			}
		}else{
			if(SortEntity.class.isAssignableFrom(clazz)){//有排序字段默认使用sort升序
				localCriteria.addOrder(Order.asc("sort"));
			}else{
				localCriteria.addOrder(Order.desc("createDate"));//无排序字段默认使用创建时间降序排序
			}
		}
		if(count!=null&&count>0){
			localCriteria.setMaxResults(count);
		}
		
		localCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List list = localCriteria.list();
		init(list, initPathSet);
		return list;
	}
	@Override
	@Transactional(readOnly=true)
	public Page findByPage(Page page) {
		if (page == null) {
			page = new Page();
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
		return findByPage(page, detachedCriteria);
	}
	@Override
	@Transactional(readOnly=true)
	public Page findByPage(Page page, Fetch ...fetchs) {
		if (page == null) {
			page = new Page();
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
		return findByPage(page, detachedCriteria, fetchs);
	}
	@Override
	@Transactional(readOnly=true)
	public Page findByPage(Page page, DetachedCriteria detachedCriteria) {
		return findByPage(page, detachedCriteria, new Fetch[]{});
	}
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly=true)
	public Page findByPage(Page page, DetachedCriteria detachedCriteria, Fetch ...fetchs) {
		if (page == null) {
			page = new Page();
		}
		int pageNumber = page.getPageNumber();
		int pageSize = page.getPageSize();
		String property = page.getProperty();
		String keyWord = page.getKeyword();
		String sortProperty = page.getSortProperty();
		Sort.SortType sortType = page.getSortType();
		List<Filter> filters = page.getFilters();
		List<Sort> sorts = page.getSorts();

		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		//模糊搜索
		if(property!=null&&keyWord!=null&&StringUtils.isNotBlank(property)&&StringUtils.isNotBlank(keyWord)){
			String propertyString="";
			if(property.contains(".")){
				String prefix=StringUtils.substringBefore(property, ".");
				String suffix=StringUtils.substringAfter(property, ".");
				criteria.createAlias(prefix, "alias");//如果是复合类型字段内的字段创建别名（eg: product.name)
				propertyString="alias."+suffix;
			}else{
				propertyString=property;
			}
			criteria.add(Restrictions.like(propertyString, "%"+keyWord+"%"));
		}
		//处理过滤集
		processFilters(criteria, filters);
		
		//总数
		Long totalCount=(Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		page.setTotalCount(totalCount);
		if(pageNumber>page.getPageCount()){
			pageNumber = 1;
			page.setPageNumber(pageNumber);
		}
		criteria.setProjection(null);
		
		//处理抓取策略
		processFetchs(criteria, fetchs);
		
		//处理排序字段
		if(sortProperty!=null&&sortType!=null){
			String sortPropertyString = "";
			if(sortProperty.contains(".")){
				String prefix=StringUtils.substringBefore(sortProperty, ".");
				String suffix=StringUtils.substringAfter(sortProperty, ".");
				criteria.createAlias(prefix, "sortAlias");//如果是复合类型字段内的字段创建别名（eg: product.name)
				sortPropertyString = "sortAlias."+suffix;
			}else{
				sortPropertyString=sortProperty;
			}
			if(sortType==SortType.asc){
				criteria.addOrder(Order.asc(sortPropertyString));
			}else{
				criteria.addOrder(Order.desc(sortPropertyString));
			}
		}
		//处理排序集
		if(sorts!=null&&sorts.size()>0){
			Iterator<Sort> iterator=sorts.iterator();
			while(iterator.hasNext()){
				Sort sort =iterator.next();
				if(sort!=null&&sort.getProperty()!=null&&sort.getSortType()!=null){
					if(StringUtils.isNotBlank(sortProperty)){
						if(sortProperty.equalsIgnoreCase(sort.getProperty()))
							continue;
					}
					if(sort.getSortType()==SortType.asc){
						criteria.addOrder(Order.asc(sort.getProperty()));
					}else if(sort.getSortType()==SortType.desc){
						criteria.addOrder(Order.desc(sort.getProperty()));
					}
				}
			}
		}else{
			if(StringUtils.isBlank(sortProperty)){
				if(SortEntity.class.isAssignableFrom(clazz)){//有排序字段默认使用sort升序
					criteria.addOrder(Order.asc("sort"));
				}else{
					criteria.addOrder(Order.desc("createDate"));//无排序字段默认使用创建时间降序排序
				}
			}
		}
	
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult((pageNumber-1)*pageSize);
		criteria.setMaxResults(pageSize);
		List list = criteria.list();
		init(list, page.getPathSet());
		page.setList(list);
		return page;
	}
	
	//处理过滤集
	private void processFilters(Criteria criteria,List<Filter> filters){
		if (filters != null && filters.size() > 0) {
			Iterator<Filter> iterator = filters.iterator();
			while (iterator.hasNext()) {
				Filter localFilter = iterator.next();
				if (localFilter != null && localFilter.getProperty() != null) {
					if (StringUtils.isBlank(localFilter.getProperty())){
						continue;
					}
					if(localFilter.getFilterType() != FilterType.isNull && localFilter.getFilterType() != FilterType.isNotNull){
						if(localFilter.getValue() == null){
							continue;
						}
						if(localFilter.getValue() instanceof String && StringUtils.isBlank(localFilter.getValue().toString())){
							continue;
						}
					}
					if (localFilter.getFilterType() == FilterType.eq&& localFilter.getValue() != null) {
						//如果区分大小写并且是字符串类型
						if ((localFilter.getIgnoreCase() != null) && localFilter.getIgnoreCase().booleanValue() && localFilter.getValue() instanceof String) {
							criteria.add(Restrictions.eq(localFilter.getProperty(), ((String) localFilter.getValue()).toLowerCase()));
								//不区分大小写，或者是对象类型直接添加	
							} else {
							criteria.add(Restrictions.eq(localFilter.getProperty(), localFilter.getValue()));
							}
					} else if (localFilter.getFilterType() == FilterType.ne&& localFilter.getValue() != null) {
							//如果区分大小写并且是字符串类型
							if ((localFilter.getIgnoreCase() != null) && localFilter.getIgnoreCase().booleanValue() && localFilter.getValue() instanceof String) {
								criteria.add(Restrictions.ne(localFilter.getProperty(), ((String) localFilter.getValue()).toLowerCase()));
								//不区分大小写，或者是对象类型直接添加
							} else {
								criteria.add(Restrictions.ne(localFilter.getProperty(), localFilter.getValue()));
							}
					} else if (localFilter.getFilterType() == FilterType.gt&& localFilter.getValue() != null) {
						criteria.add(Restrictions.gt(localFilter.getProperty(), /*(Number) */localFilter.getValue()));
					} else if (localFilter.getFilterType() == FilterType.lt&& localFilter.getValue() != null) {
						criteria.add(Restrictions.lt(localFilter.getProperty(), /*(Number)*/ localFilter.getValue()));
					} else if (localFilter.getFilterType() == FilterType.ge&& localFilter.getValue() != null) {
						criteria.add(Restrictions.ge(localFilter.getProperty(), /*(Number)*/ localFilter.getValue()));
					} else if (localFilter.getFilterType() == FilterType.le&& localFilter.getValue() != null) {
						criteria.add(Restrictions.le(localFilter.getProperty(), /*(Number)*/ localFilter.getValue()));
					} else if (localFilter.getFilterType() == FilterType.like&& localFilter.getValue() != null) {
						criteria.add(Restrictions.like(localFilter.getProperty(), (String)"%" + localFilter.getValue() + "%"));
					} else if (localFilter.getFilterType() == FilterType.in&& localFilter.getValue() != null) {
						criteria.add(Restrictions.in(localFilter.getProperty(), (Object[])localFilter.getValue()));
					} else if (localFilter.getFilterType() == FilterType.isNull) {
						criteria.add(Restrictions.isNull(localFilter.getProperty()));
					} else if (localFilter.getFilterType() == FilterType.isNotNull) {
						criteria.add(Restrictions.isNotNull(localFilter.getProperty()));
					}

				}
			}

		}
	}
	
	//处理抓取策略
	private void processFetchs(Criteria criteria, Fetch ...fetchs){
		if(fetchs == null || fetchs.length == 0){
			return;
		}
		for(Fetch fetch : fetchs){
			if(fetch.getAlias() != null && !"".equals(fetch.getAlias().trim())){
				if(fetch.getJoinType() != null){
					criteria.createAlias(fetch.getAssociationPath(), fetch.getAlias(), fetch.getJoinType());
				}else{
					criteria.createAlias(fetch.getAssociationPath(), fetch.getAlias());
				}
			}else{
				criteria.setFetchMode(fetch.getAssociationPath(), fetch.getFetchMode());
			}
		}
	}

	//初始化
	private void init(Object result, java.util.Set<String> pathList){
		if(result == null || pathList == null){
			return;
		}
		for(String path : pathList){
			if(path == null || path.equals("")){
				throw new RuntimeException("invalid path");
			}
			String[] pathArray = path.split("\\.");
			if(pathArray.length < 1){
				throw new RuntimeException("invalid path");
			}
			recurse(pathArray, result , 0);
		}
	}
	
	//递归初始化
	@SuppressWarnings("rawtypes")
	private void recurse(String[] pathArray, Object obj, int pathIndex) {
		try {
			if(obj instanceof Collection){
				for(Object element : (Collection)obj){
					PropertyDescriptor propertyDescriptor  = BeanUtils.getPropertyDescriptor(element.getClass(), pathArray[pathIndex]);//tags
					Method method = propertyDescriptor.getReadMethod();
					Object value = method.invoke(element, new Object[]{});
					Hibernate.initialize(value);
					if(value != null && pathArray.length -1 > pathIndex){//如果还有下级
						recurse(pathArray, value, pathIndex + 1);
					}
				}
			}else{
				PropertyDescriptor propertyDescriptor  = BeanUtils.getPropertyDescriptor(obj.getClass(), pathArray[pathIndex]);
				Method method = propertyDescriptor.getReadMethod();
				Object value = method.invoke(obj, new Object[]{});
				Hibernate.initialize(value);
				if(value != null && pathArray.length -1 > pathIndex){
					recurse(pathArray, value, pathIndex + 1);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	/**
	 * 字段copy
	 * @author 肖学良<br>
	 * Date: 2016年1月5日
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private void copyProperties(Object source, Object dest) {
		Assert.notNull(source, "source must not be null");
		Assert.notNull(dest, "dest must not be null");
		PropertyDescriptor[] destPropertyDescriptors = BeanUtils.getPropertyDescriptors(dest.getClass());
		for (PropertyDescriptor destPropertyDescriptor : destPropertyDescriptors) {
			if ((destPropertyDescriptor.getWriteMethod() == null)){
				continue;
			}
			PropertyDescriptor sourcePropertyDescriptor = BeanUtils.getPropertyDescriptor(source.getClass(), destPropertyDescriptor.getName());
			if ((sourcePropertyDescriptor == null) || (sourcePropertyDescriptor.getReadMethod() == null)){
				continue;
			}
			try {
				Method sourceMethod = sourcePropertyDescriptor.getReadMethod();
				if (!Modifier.isPublic(sourceMethod.getDeclaringClass().getModifiers())){
					sourceMethod.setAccessible(true);
				}
				Object sourceValue = sourceMethod.invoke(source, new Object[0]);
				Object destValue = sourceMethod.invoke(dest, new Object[0]);
				Object temp;
				if ((sourceValue != null) && (destValue != null) && ((destValue instanceof Collection))) {
					temp = destValue;
					((Collection) temp).clear();
					((Collection) temp).addAll((Collection) sourceValue);
				} else if((sourceValue != null) && (destValue != null) && ((destValue instanceof Map))){
					temp = destValue;
					((Map) temp).clear();
					((Map) temp).putAll((Map) sourceValue);
				} else {
					temp = destPropertyDescriptor.getWriteMethod();
					if (!Modifier.isPublic(((Method) temp).getDeclaringClass().getModifiers())){
						((Method) temp).setAccessible(true);
					}
					((Method) temp).invoke(dest, new Object[] { sourceValue });
				}
			} catch (Throwable localThrowable) {
				throw new FatalBeanException("Could not copy properties from source to dest", localThrowable);
			}
		}
		
	}
	
	/**
	 * 字段copy
	 * @author 肖学良<br>
	 * Date: 2016年1月5日
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void copyPropertiesIgnore(Object source, Object dest, String[] ignoreSimpleProperties, String[] includeCompositeProperties) {
		//注释参考函数copyPropertiesInclude
		Assert.notNull(source, "source must not be null");
		Assert.notNull(dest, "dest must not be null");
		ignoreSimpleProperties=ignoreSimpleProperties==null ? new String[]{} : ignoreSimpleProperties;
		includeCompositeProperties=includeCompositeProperties==null ? new String[]{} : includeCompositeProperties;
		PropertyDescriptor[] destPropertyDescriptors = BeanUtils.getPropertyDescriptors(dest.getClass());
		List<String> ignoreSimpleList = ignoreSimpleProperties != null ? Arrays.asList(ignoreSimpleProperties) : null;
		List<String> includeCompositeList = includeCompositeProperties != null ? Arrays.asList(includeCompositeProperties) : null;
		for (PropertyDescriptor destPropertyDescriptor : destPropertyDescriptors) {
			boolean notInclude = includeCompositeList == null || !includeCompositeList.contains(destPropertyDescriptor.getName());//不包含在复合属性中
			//在简单字段集合或者不在复杂字段集合
			if ((destPropertyDescriptor.getWriteMethod() == null) || ((ignoreSimpleList != null) && (ignoreSimpleList.contains(destPropertyDescriptor.getName())))){
				if(includeCompositeList == null || notInclude){
					continue;
				}
			}
			PropertyDescriptor sourcePropertyDescriptor = BeanUtils.getPropertyDescriptor(source.getClass(), destPropertyDescriptor.getName());
			if ((sourcePropertyDescriptor == null) || (sourcePropertyDescriptor.getReadMethod() == null)){
				continue;
			}
			try {
				Method sourceMethod = sourcePropertyDescriptor.getReadMethod();
				if (!Modifier.isPublic(sourceMethod.getDeclaringClass().getModifiers())){
					sourceMethod.setAccessible(true);
				}
				Object sourceValue = sourceMethod.invoke(source, new Object[0]);
				Object destValue = sourceMethod.invoke(dest, new Object[0]);
				Object temp;
				if ((sourceValue != null) && (destValue != null) && ((destValue instanceof Collection))) {
					if(notInclude){
						continue;
					}
					temp = destValue;
					((Collection) temp).clear();
					((Collection) temp).addAll((Collection) sourceValue);
				} else if((sourceValue != null) && (destValue != null) && ((destValue instanceof Map))){
					if(notInclude){
						continue;
					}
					temp = destValue;
					((Map) temp).clear();
					((Map) temp).putAll((Map) sourceValue);
				} else {
					//因为所有的对象都有getClass方法，所以该方法直接忽略
					if ("getClass".equals(sourceMethod.getName())) {
						continue;
					}
					//判断该方法是否有瞬时注解
					if (sourceMethod.getAnnotation(Transient.class) != null) {
						continue;
					}
					if(BaseEntity.class.isAssignableFrom((Class)sourceMethod.getGenericReturnType())){
						if(notInclude){
							continue;
						}
					}
					temp = destPropertyDescriptor.getWriteMethod();
					if (!Modifier.isPublic(((Method) temp).getDeclaringClass().getModifiers())){
						((Method) temp).setAccessible(true);
					}
					((Method) temp).invoke(dest, new Object[] { sourceValue });
				}
			} catch (Throwable localThrowable) {
				throw new FatalBeanException("Could not copy properties from source to dest", localThrowable);
			}
		}

	}
	
	/**
	 * 拷贝source中的指定属性到dest
	 * @param source 被复制对象
	 * @param dest 目标对象
	 * @param includeProperties 要拷贝的字段
	 * @author 肖学良<br>
	 * Date: 2015年3月30日
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void copyPropertiesInclude(Object source, Object dest, String[] includeSimpleProperties, String[] includeCompositeProperties){
		//开发过程中加入下面这两行行代码，因为如果不加这行代码，下面判断（1）处中会出现notInclude=true应该进入continue但是实际没有，为了保持原来的代码，就加入这两行行代码，其它地方不做修改
		includeSimpleProperties=includeSimpleProperties==null ? new String[]{} : includeSimpleProperties;
		includeCompositeProperties=includeCompositeProperties==null ? new String[]{} : includeCompositeProperties;
		Assert.notNull(source, "source must not be null");
		Assert.notNull(dest, "dest must not be null");
		PropertyDescriptor[] destPropertyDescriptors = BeanUtils.getPropertyDescriptors(dest.getClass());//获取dest实体的全部字段
		List<String> includeSimpleList = includeSimpleProperties != null ? Arrays.asList(includeSimpleProperties) : null;
		List<String> includeCompositeList = includeCompositeProperties != null ? Arrays.asList(includeCompositeProperties) : null;
		for (PropertyDescriptor destPropertyDescriptor : destPropertyDescriptors) {
//			System.out.println(destPropertyDescriptor.getName());
			boolean notInclude = includeCompositeList == null || !includeCompositeList.contains(destPropertyDescriptor.getName());//不在复合字段集合中
			//不在简单字段集合中也不在复合字段集合中或者没有提供写方法
			if ((destPropertyDescriptor.getWriteMethod() == null) || ((includeSimpleList != null) && (!includeSimpleList.contains(destPropertyDescriptor.getName())))){
				//简单字段集合不为空或者不在复合字段中，直接迭代下一个destPropertyDescriptor
				if(includeCompositeList != null && notInclude){//(1)处判断
					continue;
				}
			}
			//获取source实体对象在dest实体对象中对应的字段的PropertyDescriptor
			PropertyDescriptor sourcePropertyDescriptor = BeanUtils.getPropertyDescriptor(source.getClass(), destPropertyDescriptor.getName());
			//如果source实体中没有dest对应的该字段或者该字段没有提供可读方法，直接迭代下一个destPropertyDescriptor
			if ((sourcePropertyDescriptor == null) || (sourcePropertyDescriptor.getReadMethod() == null)){
				continue;
			}
			//都不满足上述条件后进行字段替换（将source实体对应字段的值复制到dest实体中，覆盖dest原有的值
			try {
				Method sourceMethod = sourcePropertyDescriptor.getReadMethod();
//				System.out.println(sourceMethod.getName()+"---"+sourceMethod.getGenericReturnType());
				//如果读方法不是public，则修改其访问权限，调用该方法
				if (!Modifier.isPublic(sourceMethod.getDeclaringClass().getModifiers())){
					sourceMethod.setAccessible(true);
				}
				Object sourceValue = sourceMethod.invoke(source, new Object[0]);//new Object[0],传递参数的类型，反射的时候需要用到参数的类型;
				Object destValue = sourceMethod.invoke(dest, new Object[0]);
				Object temp;
				//如果该字段是容器对象
				if ((sourceValue != null) && (destValue != null) && ((destValue instanceof Collection))){//容器继承集合
					if(notInclude){
						continue;
					}
					temp = destValue;
					((Collection) temp).clear();
					((Collection) temp).addAll((Collection) sourceValue);
				} else if((sourceValue != null) && (destValue != null) && ((destValue instanceof Map))){//容器继承图
					if(notInclude){
						continue;
					}
					temp = destValue;
					((Map) temp).clear();
					((Map) temp).putAll((Map) sourceValue);
				}
				//如果该字段是简单对象
				else {
					/* if("getUser".equals(sourceMethod.getName())){
						System.out.println("66666666");
					} */
					//因为所有的对象都有getClass方法，所以该方法直接忽略
					if ("getClass".equals(sourceMethod.getName())) {
						continue;
					}
					//判断该方法是否有瞬时注解
					if (sourceMethod.getAnnotation(Transient.class) != null) {
						continue;
					}
					//判断该字段的读方法的返回类型是否继承BaseEntity
					if(BaseEntity.class.isAssignableFrom((Class)sourceMethod.getGenericReturnType())){
//						System.out.println(sourceMethod.getName()+"---"+sourceMethod.getGenericReturnType());
						if(notInclude){
							continue;
						}
					}
					//获取写方法，将source字段的值覆盖dest对应字段的值
					temp = destPropertyDescriptor.getWriteMethod();
					if (!Modifier.isPublic(((Method) temp).getDeclaringClass().getModifiers())){
						((Method) temp).setAccessible(true);
					}
					((Method) temp).invoke(dest, new Object[] { sourceValue });
				}
			} catch (Throwable localThrowable) {
				throw new FatalBeanException("Could not copy properties from source to dest", localThrowable);
			}
		}
		
	}
}
