package sistema.solar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import sistema.solar.dao.WeatherDAO;
import sistema.solar.entity.Weather;

/**
 * @see WeatherDAO
 * 
 * @author watas
 */
public class WeatherDAOImpl implements WeatherDAO {

	private SessionFactory sessionFactory;

	/**
	 * @see WeatherDAO#save()
	 */
	public void save(Weather entity) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception sqlException) {
			if (null != session.getTransaction()) {
				// rollback
				session.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * @see WeatherDAO#update()
	 */
	public void update(Weather entity) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(entity);
			session.getTransaction().commit();
		} catch (Exception sqlException) {
			if (null != session.getTransaction()) {
				// rollback
				session.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * @see WeatherDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Weather> getAll(Class<Weather> class1) {
		List<Weather> entities = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			entities = session.createCriteria(class1).list();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return entities;
	}

	/**
	 * @see WeatherDAO#listByIntenceDay()
	 */
	@SuppressWarnings("unchecked")
	public List<Weather> listByIntenceDay(Class<Weather> class1) {
		List<Weather> entities = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(class1);
			criteria.add(Restrictions.eq("intenseRainDay", true));

			entities = criteria.list();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return entities;
	}

	/**
	 * @see WeatherDAO#getById()
	 */
	public Weather getById(Weather entity) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			entity = (Weather) session.get(entity.getClass(), entity.getId());
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return entity;
	}

	/**
	 * @see WeatherDAO#countRows()
	 */
	public Long countRows(Class<Weather> class1) {
		Long rows = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			rows = (Long) session.createCriteria(class1).setProjection(Projections.rowCount()).uniqueResult();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return rows;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
