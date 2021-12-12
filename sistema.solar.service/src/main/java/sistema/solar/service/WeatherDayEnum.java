package sistema.solar.service;

/**
 * Enumerador del tipo de día.
 * 
 * @author watas
 */
public enum WeatherDayEnum {

	NORMAL_DAY("normal"), DROUGHT_DAY("sequía"), INTENSE_RAIN_DAY("pico máximo de intensidad de lluvia"), RAINY_DAY("lluvia"), OPTIMAL_DAY("óptimo");

	/**
	 * Descripción del clima.
	 */
	private String description;

	/**
	 * Constructor con descripción.
	 * 
	 * @param description la descripción del clima del día.
	 */
	private WeatherDayEnum(String description) {
		this.description = description;
	}

	/**
	 * Obtiene la descripción del clima del día.
	 * 
	 * @return description la descripción del clima del día.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Configura la descripción del clima del día.
	 * 
	 * @param description la descripción del clima del día.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}