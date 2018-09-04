package com.workhelper.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.workhelper.common.dao.support.Page;

@SuppressWarnings({ "rawtypes", "deprecation" })
@Repository
@NoRepositoryBean
public interface IRepository<T, ID extends Serializable> extends
		PagingAndSortingRepository<T, ID> {

	/**
	 * 删除
	 *
	 * @param entity
	 */
	void remove(final Object entity);

	/**
	 * 保存
	 *
	 * @param entity
	 * @return
	 */
	Object saveEntity(final Object entity);

	/**
	 * insert or update
	 *
	 * @param entity
	 */
	void saveOrUpdate(final Object entity);

	/**
	 *
	 * @param entity
	 * @return
	 */
	Object merge(final Object entity);

	Session getHibernateSession();

	/**
	 * 执行HQL更新
	 *
	 * @param hql
	 * @param values
	 * @return
	 */
	int executeUpdate(final String hql, final Object... values);

	/**
	 * 查询,values对应hql中变量
	 *
	 * @param hql
	 * @param values
	 * @return
	 */
	List find(final String hql, final Object... values);

	/**
	 * 查询，map对应sql中变量
	 *
	 * @param hql
	 * @param map
	 * @return
	 */
	List findBy(final String hql, Map<String, Object> map);

	/**
	 * 根据属性名和属性值查询单个对象.
	 *
	 * @return 符合条件的唯一对象 or null if not found
	 */
	Object findUnique(final String ql, final Object... values);

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 *
	 */
	boolean isUnique(final Object entity, final String uniquePropertyNames);

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 *
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原值(orgValue)则不作比较.
	 */
	boolean isPropertyUnique(final String propertyName, final Object newValue,
							 final Object orgValue);

	/**
	 * HQL分页查询
	 *
	 * @return 符合条件的集合
	 */
	Page pagedQuery(final String hql, final QueryMap qm, final Object... values);

	Page pagedNativeQuery(final String sql, final QueryMap qm , final Object... values);

	/**
	 * 创建Query对象.
	 * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 留意可以连续设置,如下：
	 *
	 * <pre>
	 * controller.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 *
	 * 调用方式如下：
	 *
	 * <pre>
	 *        controller.createQuery(hql)
	 *        controller.createQuery(hql,arg0);
	 *        controller.createQuery(hql,arg0,arg1);
	 *        controller.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 *
	 * @param values
	 *            可变参数.
	 */
	Query createQuery(String hql, Object... values);

	/**
	 * 不带条件的查询
	 *
	 * @param hql
	 * @return
	 */
	Query createSQLQuery(String hql);

	void refresh(Object entity);

	/**
	 * 分页查询
	 *
	 * @param hql
	 * @param qm
	 * @param values
	 * @return
	 */
	List query(String hql, QueryMap qm, Object... values);

	/**
	 * 执行原生SQL，用于执行查询
	 *
	 * @param sql
	 * @return
	 */
	javax.persistence.Query createSQLNativeQuery(String sql , Object... values);

	/**
	 * 执行原生SQL，一般用于增删改
	 *
	 * @param sqlString
	 */
	void execSQL(String sqlString, Object... values);

	/**
	 * 为大量数据做分页查询优化接口
	 * @author paul
	 * @date 2018年3月22日下午6:10:43
	 * @param countSql
	 * @param sql
	 * @param qm
	 * @param values
	 * @return
	 */
	Page pagedNativeQueryForMessData(String countSql ,String sql, QueryMap qm , final Object... values) ;
}
