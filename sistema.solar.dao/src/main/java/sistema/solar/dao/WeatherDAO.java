package sistema.solar.dao;

import java.util.List;

import sistema.solar.entity.Weather;

/**
 * Interfaz para la persistencia de la entidad Weather.
 * 
 * @author watas
 */
public interface WeatherDAO {

	/**
	 * Guarda la entidad.
	 * 
	 * @param entity
	 */
	public void save(Weather entity);

	/**
	 * Actualiza la entidad.
	 * 
	 * @param entity
	 */
	public void update(Weather entity);

	/**
	 * Obtiene todos los registros de la entidad.
	 * 
	 * @param class1
	 * @return
	 */
	public List<Weather> getAll(Class<Weather> class1);

	/**
	 * Devuelve un listado de entidades que tienen el atributo isIntenceDay en true.
	 * Es decir, aquellos días que tuvieron un pico de intensidad de lluvia.
	 * 
	 * @param class1
	 * @return
	 */
	public List<Weather> listByIntenceDay(Class<Weather> class1);

	/**
	 * Obtiene la entidad en base a su id.
	 * 
	 * @param entity
	 * @return
	 */
	public Weather getById(Weather entity);

	/**
	 * Obtiene la cantidad de registros que hay.
	 * 
	 * @param class1
	 * @return
	 */
	public Long countRows(Class<Weather> class1);
}