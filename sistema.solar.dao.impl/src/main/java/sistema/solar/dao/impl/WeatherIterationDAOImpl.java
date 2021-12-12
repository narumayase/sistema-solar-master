package sistema.solar.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sistema.solar.dao.WeatherIterationDAO;
import sistema.solar.entity.WeatherIteration;

/**
 * @see WeatherIterationDAO
 * 
 * @author watas
 */
public class WeatherIterationDAOImpl implements WeatherIterationDAO {

	private SessionFactory sessionFactory;

	/**
	 * @see WeatherIterationDAO#getById()
	 */
	public WeatherIteration getById(WeatherIteration entity) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			entity = (WeatherIteration) session.get(entity.getClass(), entity.getId());
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
	 * @see WeatherIterationDAO#save()
	 */
	public void save(WeatherIteration entity) {
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
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
