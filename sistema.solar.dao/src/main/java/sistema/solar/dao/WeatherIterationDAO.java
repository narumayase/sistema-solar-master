package sistema.solar.dao;

import sistema.solar.entity.WeatherIteration;

/**
 * Interfaz para la persistencia de la entidad WeatherIteration.
 * 
 * @author watas
 */
public interface WeatherIterationDAO {

	/**
	 * Guarda la entidad.
	 * 
	 * @param entity
	 */
	public void save(WeatherIteration entity);

	/**
	 * Obtiene la entidad en base a su id.
	 * 
	 * @param entity
	 * @return
	 */
	public WeatherIteration getById(WeatherIteration entity);
}