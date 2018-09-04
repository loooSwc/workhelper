package com.workhelper.common.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.workhelper.common.dao.IRepository;
import com.workhelper.common.dao.QueryMap;
import com.workhelper.common.dao.support.Page;

@NoRepositoryBean
@SuppressWarnings({ "rawtypes", "deprecation" })
public class RepositoryDao<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements IRepository<T, ID> {
	private static final Logger log = LoggerFactory
			.getLogger(RepositoryDao.class);

	private final EntityManager entityManager;

	public RepositoryDao(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		entityManager = em;
	}

	public RepositoryDao(final JpaEntityInformation<T, ?> entityInformation,
						 final EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public void remove(Object entity) {
		Assert.notNull(entity);
		getJpaSession().delete(entity);

	}

	@Override
	public void saveOrUpdate(Object entity) {
		Assert.notNull(entity);
		getJpaSession().saveOrUpdate(entity);
	}

	private Session getJpaSession() {
		return entityManager.unwrap(org.hibernate.Session.class);
	}

	@Override
	public Object merge(Object entity) {
		Assert.notNull(entity);
		return getJpaSession().merge(entity);
	}

	@Override
	public Session getHibernateSession() {
		return getJpaSession();
	}

	@Override
	public int executeUpdate(String ql, Object... values) {
		Session session = this.getJpaSession();
		int result = 0;
		try {
			Query query = session.createQuery(ql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			result = query.executeUpdate();
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public List find(String ql, Object... values) {
		String hql = this.getHql(ql);
		Assert.hasText(hql);
		return createQuery(hql, values).list();
	}

	private String getHql(final String ql) {
		return ql;
	}

	public List findByProperty(Object Entity, Map<String, Object> map) {
		Criteria criteria = getJpaSession().createCriteria(Entity.getClass());
		for (Iterator it = map.keySet().iterator(); it.hasNext();) {
			String name = (String) it.next();
			if (map.get(name) != null
					&& StringUtils.hasLength(map.get(name).toString())) {
				Criterion c = Restrictions.eq(name, map.get(name));
				criteria.add(c);
			}
		}
		return criteria.list();
	}

	@Override
	public List findBy(String ql, Map<String, Object> map) {
		String hql = this.getHql(ql);
		Query query = this.createQuery(hql);
		for (Iterator it = map.keySet().iterator(); it.hasNext();) {
			String name = (String) it.next();
			query.setParameter(name, map.get(name));
		}
		return query.list();
	}
	@Override
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getJpaSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	@Override
	public Object findUnique(String ql, Object... values) {
		try {
			String hql = this.getHql(ql);
			return (Object) createQuery(hql, values).uniqueResult();
		} catch (HibernateException e) {
			log.error(e.getMessage(),e);
			return null;
		}
	}

	@Override
	public boolean isUnique(Object entity, String uniquePropertyNames) {
		return false;
	}

	public String getIdName(Object obj) {
		ClassMetadata meta = this.getJpaSession().getSessionFactory()
				.getClassMetadata(obj.getClass());
		String idName = meta.getIdentifierPropertyName();
		return idName;
	}

	@Override
	public Query createSQLQuery(String ql) {
		String sql = this.getHql(ql);
		Query query = getJpaSession().createSQLQuery(sql);
		return query;
	}

	@Override
	public void refresh(Object entity) {
		flush();
		this.getJpaSession().refresh(entity, LockMode.UPGRADE);
		// 此处一定要用lockMode.UPGRADE模式进行锁定刷新

	}

	public void flush() {
		getJpaSession().flush();
	}


	@Override
	public Page pagedNativeQueryForMessData (String countSql ,String sql, QueryMap qm , final Object... values) {
		Page page = null;
		javax.persistence.Query query = null;
		query = createSQLNativeQuery(sql , values);

		javax.persistence.Query countQuery = createSQLNativeQuery(countSql, values);
		Object o = countQuery.getSingleResult();
		int totalCount =0;
		if(o instanceof BigInteger){
			totalCount = ((BigInteger)o).intValue();
		}
		/*page = pagedNativeQuery(query, qm.getPageNum(), qm.getCURRENT_ROWS_SIZE(),20);*/
		page = pagedNativeQuery(query, qm.getPageNum(), qm.getCURRENT_ROWS_SIZE(),totalCount);

		return page;
	}
	@Override
	public Page pagedNativeQuery(String sql, QueryMap qm , final Object... values) {
		Page page = null;
		javax.persistence.Query query = null;
		query = createSQLNativeQuery(sql , values);

		javax.persistence.Query countQuery = createSQLNativeQuery("select count(*) from ("+sql+") as logCount" , values);
		Object o = countQuery.getSingleResult();
		int totalCount =0;
		if(o instanceof BigInteger){
			totalCount = ((BigInteger)o).intValue();
		}
		/*page = pagedNativeQuery(query, qm.getPageNum(), qm.getCURRENT_ROWS_SIZE(),20);*/
		page = pagedNativeQuery(query, qm.getPageNum(), qm.getCURRENT_ROWS_SIZE(),totalCount);

		return page;
	}
	public Page pagedQuery(final String ql, final QueryMap qm,
						   final Object... values) {
		Page page = null;
		Query query = null;

		String hql = this.getHql(ql);

		if (qm.getValueMap() != null) {
			query = createQuery(
					com.workhelper.common.dao.support.Query.queryAccession(hql, qm),
					values);
			Iterator iterator = qm.getValueMap().keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object value = qm.getValueMap().get(key);
				query.setParameter(key, value);
			}
		} else if (qm.getDataProp() != null
				&& StringUtils.hasText(qm.getSearchQuery())) {
			query = createQuery(
					com.workhelper.common.dao.support.Query.queryAccession(hql,
							qm.getDataProp(), qm.getSearchQuery()), values);
			for (String column : qm.getDataProp()) {
				java.util.regex.Pattern pattern = java.util.regex.Pattern
						.compile("[0-9]*");
				java.util.regex.Matcher match = pattern.matcher(column);
				if (match.matches() == false) {
					query.setParameter(column, qm.getSearchQuery());
				}
			}

		} else {
			query = createQuery(hql, values);

		}
		page = pagedQuery(query, qm.getPageNum(), qm.getCURRENT_ROWS_SIZE());

		return page;
	}
	protected Page pagedQuery(final Query query, final int pageNo,
							  final int pageSize) {
		// Count查询
		long totalCount = 0;
		int startIndex;

		query.getNamedParameters();

		ScrollableResults scrollableResults = query.scroll();
		scrollableResults.last();

		totalCount = scrollableResults.getRowNumber() + 1;

		if(totalCount<1){
			return new Page(0,0,pageSize,new ArrayList());
		}
		startIndex = Page.getStartOfPage(pageNo, pageSize,totalCount);

		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();

		return new Page(startIndex, totalCount, pageSize, list);
	}
	protected Page pagedNativeQuery(final javax.persistence.Query query, final int pageNo,
									final int pageSize,int totalCount) {
		if(totalCount<1){
			return new Page(0,0,pageSize,new ArrayList());
		}
		int startIndex = Page.getStartOfPage(pageNo, pageSize,totalCount);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).getResultList();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	@Override
	public Object saveEntity(Object entity) {
		Assert.notNull(entity);
		try {
			Session session = this.getJpaSession();
			session.saveOrUpdate(entity);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
		}
		return entity;
	}

	@Override
	public boolean isPropertyUnique(String propertyName, Object newValue,
									Object orgValue) {
		return false;
	}

	@Override
	public List query(String ql, QueryMap qm, Object... values) {
		Query query = null;
		String hql = this.getHql(ql);
		if (qm.getEntity() != null) {
			query = createQuery(
					com.workhelper.common.dao.support.Query.queryAccession(hql, qm),
					values);
			Iterator iterator = qm.getValueMap().keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object value = qm.getValueMap().get(key);
				query.setParameter(key, value);
			}

		} else {
			query = createQuery(hql, values);

		}

		return query.list();
	}
	@Override
	public javax.persistence.Query createSQLNativeQuery(String sqlString , Object... values) {
		javax.persistence.Query query = entityManager
				.createNativeQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i+1, values[i]);
			}
		}
		return query;
	}

	@Override
	public void execSQL(String sqlString , Object... values) {
		javax.persistence.Query query = entityManager
				.createNativeQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i+1, values[i]);
			}
		}
		query.executeUpdate();
	}
}
