package sistema.solar.service;

import sistema.solar.dto.WeatherDTO;
import sistema.solar.dto.WeatherIterationDTO;

/**
 * Servicio para las operaciones de predicción del clima.
 * 
 * @author watas
 */
public interface WeatherService {

	/**
	 * Devuelve la cantidad de veces que hubo períodos de sequía, de lluvia y de
	 * condiciones óptimas en la cantidad de años solicitada. Además también
	 * devuelve el listado de días que tuvieron un pico máximo de intensidad de
	 * lluvia.
	 * 
	 * @param year cantidad de años solicitada
	 */
	public WeatherIterationDTO calculateWeatherInSelectedYear(int year);

	/**
	 * Devuelve el clima propio del día pasado por parámetro.
	 * 
	 * @param day número del día solicitado
	 * @return el clima del día
	 */
	public WeatherDTO getWeatherByDay(int day);
}