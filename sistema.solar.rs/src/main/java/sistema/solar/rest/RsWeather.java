package sistema.solar.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sistema.solar.dto.WeatherDTO;
import sistema.solar.dto.WeatherIterationDTO;
import sistema.solar.rest.response.WeatherIterationResponse;
import sistema.solar.rest.response.WeatherResponse;
import sistema.solar.service.WeatherService;

/**
 * Clase para exponer los endpoints REST solcitados.
 * 
 * @author watas
 */
@Path("/clima")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RsWeather {

	/**
	 * Servicio para la obtención de datos.
	 */
	private static WeatherService service;

	/**
	 * Inicializa por primera y única vez el servicio a utilizar.
	 */
	public RsWeather() {
		if (service == null) {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			service = (WeatherService) context.getBean("weatherService");
		}
	}

	/**
	 * Devuelve el clima propio del día pasado por parámetro.
	 * 
	 * @param day el día seleccionado.
	 * @return el clima de ese día.
	 */
	@GET
	@Path("/")
	public WeatherResponse getWeatherByDay(@QueryParam("dia") Integer day) {
		WeatherDTO weather = service.getWeatherByDay(day);
		return new WeatherResponse(day, weather.getWeather());
	}

	/**
	 * Devuelve la cantidad de veces que hubo períodos de sequía, de lluvia y de
	 * condiciones óptimas en la cantidad de años solicitada. Además también
	 * devuelve el listado de días que tuvieron un pico máximo de intensidad de
	 * lluvia.
	 * 
	 * @param year cantidad de años solicitada
	 * @return
	 */
	@GET
	@Path("/iteracion")
	public WeatherIterationResponse getWeatherIterationByNumberOfYears(@QueryParam("cantAnios") Integer year) {
		WeatherIterationDTO dto = service.calculateWeatherInSelectedYear(year);
		WeatherIterationResponse response = new WeatherIterationResponse(dto.getCountDroughtPeriod(), dto.getCountRainyPeriod(),
				dto.getCountOptimalConditions(), dto.getDaysWithMaxRain());
		return response;
	}
}