package sistema.solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad que guarda la cantidad de veces que se repite un clima específico en
 * un ciclo de 360 días.
 * 
 * @see Entity
 * 
 * @author watas
 */
@Entity
@Table(name = "weather_iteration")
public class WeatherIteration extends sistema.solar.entity.Entity {

	/**
	 * El id.
	 */
	private Long id;

	/**
	 * Cantidad de veces que hubo un período de sequía.
	 */
	private int countDroughtPeriod;

	/**
	 * Cantidad de veces que hubo un período de lluvia.
	 */
	private int countRainyPeriod;

	/**
	 * Cantidad de veces que hubo un período de óptimas condiciones.
	 */
	private int countOptimalConditions;

	/**
	 * Constructor.
	 */
	public WeatherIteration() {
	}

	/**
	 * Constructor con id.
	 * 
	 * @param id
	 */
	public WeatherIteration(Long id) {
		this.id = id;
	}

	/**
	 * Constructor con los períodos contabilizados.
	 * 
	 * @param countDroughtPeriod
	 * @param countRainyPeriod
	 * @param countOptimalConditions
	 * @param countMaxRainyPeriod
	 */
	public WeatherIteration(int countDroughtPeriod, int countRainyPeriod, int countOptimalConditions) {
		super();
		this.countDroughtPeriod = countDroughtPeriod;
		this.countRainyPeriod = countRainyPeriod;
		this.countOptimalConditions = countOptimalConditions;
	}

	/**
	 * Obtiene el id.
	 * 
	 * @return id el id.
	 */
	@Id
	@GeneratedValue
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * Configura el id.
	 * 
	 * @param id el id.
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene la cantidad de veces que hubo un período de sequía.
	 * 
	 * @return the countDroughtPeriod Cantidad de veces que hubo un período de
	 *         sequía.
	 */
	@Column(name = "count_drought_period")
	public int getCountDroughtPeriod() {
		return countDroughtPeriod;
	}

	/**
	 * Configura la cantidad de veces que hubo un período de sequía.
	 * 
	 * @param countDroughtPeriod Cantidad de veces que hubo un período de sequía.
	 */
	public void setCountDroughtPeriod(int countDroughtPeriod) {
		this.countDroughtPeriod = countDroughtPeriod;
	}

	/**
	 * Obtiene la cantidad de veces que hubo un período de lluvia.
	 * 
	 * @return the countRainyPeriod Cantidad de veces que hubo un período de lluvia.
	 */
	@Column(name = "count_rainy_period")
	public int getCountRainyPeriod() {
		return countRainyPeriod;
	}

	/**
	 * Configura la cantidad de veces que hubo un período de lluvia.
	 * 
	 * @param countRainyPeriod Cantidad de veces que hubo un período de lluvia.
	 */
	public void setCountRainyPeriod(int countRainyPeriod) {
		this.countRainyPeriod = countRainyPeriod;
	}

	/**
	 * Obtiene la cantidad de veces que hubo un período de óptimas condiciones.
	 * 
	 * @return the countOptimalConditions Cantidad de veces que hubo un período de
	 *         óptimas condiciones.
	 */
	@Column(name = "count_optimal_conditions")
	public int getCountOptimalConditions() {
		return countOptimalConditions;
	}

	/**
	 * Configura la cantidad de veces que hubo un período de óptimas condiciones.
	 * 
	 * @param countOptimalConditions Cantidad de veces que hubo un período de
	 *                               óptimas condiciones.
	 */
	public void setCountOptimalConditions(int countOptimalConditions) {
		this.countOptimalConditions = countOptimalConditions;
	}
}