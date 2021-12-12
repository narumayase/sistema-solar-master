package sistema.solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad que representa al clima en un día.
 * 
 * @see Entity
 * 
 * @author watas
 */
@Entity
@Table
public class Weather extends sistema.solar.entity.Entity {

	/**
	 * El número del día.
	 */
	private int day;

	/**
	 * El id del clima.
	 */
	private Long id;

	/**
	 * Período de sequía. Se da cuando los planetas están alineados entre ellos y
	 * con el sol.
	 */
	private boolean droughtPeriod;

	/**
	 * Período de lluvia. Se da cuando el triángulo que forman los tres planetas
	 * tiene al sol dentro.
	 */
	private boolean rainyPeriod;

	/**
	 * Día de máxima intensidad de lluvia. Se da cuando el perímetro formado por el
	 * triángulo tiene el sol dentro y es máximo.
	 */
	private boolean intenseRainDay;

	/**
	 * Condiciones Óptimas de temperatura y presión. Se da cuando los planetas están
	 * alineados entre ellos pero no con el sol.
	 */
	private boolean optimalConditions;

	/**
	 * Perímetro del triángulo cuando el sol se encuentra dentro. Necesario para
	 * saber cuál es el máximo.
	 */
	private int perimeter;

	/**
	 * Constructor.
	 */
	public Weather() {
	}

	/**
	 * Constructor con id.
	 * 
	 * @param id el id del clima.
	 */
	public Weather(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el id del clima.
	 * 
	 * @return the id El id del clima.
	 */
	@Id
	@GeneratedValue
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * Configura el id del clima.
	 * 
	 * @param id El id del clima.
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el número del día.
	 * 
	 * @return day El número del día.
	 */
	@Column(name = "day")
	public int getDay() {
		return day;
	}

	/**
	 * Configura el número del día.
	 * 
	 * @param day El número del día.
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Obtiene el perímetro del triángulo cuando el sol se encuentra dentro.
	 * 
	 * @return the perimeter
	 */
	@Column(name = "perimeter")
	public int getPerimeter() {
		return perimeter;
	}

	/**
	 * Configura el perímetro del triángulo cuando el sol se encuentra dentro.
	 * 
	 * @param perimeter Perímetro del triángulo cuando el sol se encuentra dentro.
	 */
	public void setPerimeter(int perimeter) {
		this.perimeter = perimeter;
	}

	/**
	 * Obtiene si es período de sequía.
	 * 
	 * @return droughtPeriod true si es Período de sequía. false caso contrario.
	 */
	@Column(name = "drought_period")
	public boolean isDroughtPeriod() {
		return droughtPeriod;
	}

	/**
	 * Configura si es período de sequía.
	 * 
	 * @param droughtPeriod true si es Período de sequía. false caso contrario.
	 */
	public void setDroughtPeriod(boolean droughtPeriod) {
		this.droughtPeriod = droughtPeriod;
	}

	/**
	 * Obtiene si es período de lluvia.
	 * 
	 * @return rainyPeriod true si es Período de lluvia. false caso contrario.
	 */
	@Column(name = "rainy_period")
	public boolean isRainyPeriod() {
		return rainyPeriod;
	}

	/**
	 * Configura el período de lluvia.
	 * 
	 * @param rainyPeriod true si es Período de lluvia. false caso contrario.
	 */
	public void setRainyPeriod(boolean rainyPeriod) {
		this.rainyPeriod = rainyPeriod;
	}

	/**
	 * Obtiene si el día es de condiciones Óptimas de temperatura y presión.
	 * 
	 * @return optimalConditions true si es de condiciones Óptimas de temperatura y
	 *         presión. false caso contrario.
	 */
	@Column(name = "optimal_conditions")
	public boolean isOptimalConditions() {
		return optimalConditions;
	}

	/**
	 * Configura si el día es de condiciones Óptimas de temperatura y presión.
	 * 
	 * @param optimalConditions true si es de condiciones Óptimas de temperatura y
	 *                          presión. false caso contrario.
	 */
	public void setOptimalConditions(boolean optimalConditions) {
		this.optimalConditions = optimalConditions;
	}

	/**
	 * Obtiene si es día de máxima intensidad de lluvia.
	 * 
	 * @return intenseRainDay true si es día de máxima intensidad de lluvia. false
	 *         caso contrario.
	 */
	@Column(name = "instense_rain_day")
	public boolean isIntenseRainDay() {
		return intenseRainDay;
	}

	/**
	 * Configura si es día de máxima intensidad de lluvia.
	 * 
	 * @param intenseRainDay true si es día de máxima intensidad de lluvia. false
	 *                       caso contrario.
	 */
	public void setIntenseRainDay(boolean intenseRainDay) {
		this.intenseRainDay = intenseRainDay;
	}
}