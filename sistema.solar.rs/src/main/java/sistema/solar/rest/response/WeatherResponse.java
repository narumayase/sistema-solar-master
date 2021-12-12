package sistema.solar.rest.response;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author watas
 *
 */
public class WeatherResponse {

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
	public WeatherResponse() {
	}

	/**
	 * Constructor con el día y el clima.
	 * 
	 * @param day
	 * @param weather
	 */
	public WeatherResponse(Integer day, String weather) {
		super();
		this.day = day;
		this.weather = weather;
	}

	/**
	 * Obtiene el número del día.
	 * 
	 * @return dia El número del día.
	 */
	@JsonProperty("dia")
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
	@JsonProperty("clima")
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