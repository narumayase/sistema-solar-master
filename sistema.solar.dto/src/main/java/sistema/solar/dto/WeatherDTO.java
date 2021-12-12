package sistema.solar.dto;

/**
 * Objeto que representa al clima del día.
 * 
 * @author watas
 */
public class WeatherDTO {

	/**
	 * El número del día.
	 */
	private Integer day;

	/**
	 * El clima del día.
	 */
	private String weather;

	/**
	 * Constructor.
	 */
	public WeatherDTO() {
	}

	/**
	 * Constructor con el día y el clima.
	 * 
	 * @param day
	 * @param weather
	 */
	public WeatherDTO(Integer day, String weather) {
		super();
		this.day = day;
		this.weather = weather;
	}

	/**
	 * Obtiene el número del día.
	 * 
	 * @return dia El número del día.
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * Configura el número del día.
	 * 
	 * @param day El número del día.
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * Obtiene el clima del día.
	 * 
	 * @return clima El clima del día.
	 */
	public String getWeather() {
		return weather;
	}

	/**
	 * Configura el clima del día.
	 * 
	 * @param weather El clima del día.
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}
}