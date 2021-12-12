package sistema.solar.service.impl;

import java.util.ArrayList;
import java.util.List;

import sistema.solar.dao.WeatherDAO;
import sistema.solar.dao.WeatherIterationDAO;
import sistema.solar.dto.PlanetDTO;
import sistema.solar.dto.WeatherDTO;
import sistema.solar.dto.WeatherIterationDTO;
import sistema.solar.entity.Weather;
import sistema.solar.entity.WeatherIteration;
import sistema.solar.service.WeatherDayEnum;
import sistema.solar.service.WeatherService;
import sistema.solar.service.impl.util.GeometryUtility;

/**
 * @see WeatherService
 * 
 * @author watas
 */
public class WeatherServiceImpl implements WeatherService {

	/**
	 * Números de días en un año. (año Ferengi)
	 */
	private static final int NUMBER_OF_DAYS_IN_A_YEAR = 360;

	/**
	 * Utilidades de Geometría.
	 */
	private GeometryUtility geometryUtility;

	/**
	 * DAO para manejar la entidad Weather.
	 */
	private WeatherDAO weatherDAO;

	/**
	 * DAO para manejar la entidad WeatherIteration.
	 */
	private WeatherIterationDAO weatherIterationDAO;

	/**
	 * Planetas del sistema.
	 */
	private PlanetDTO betasoide;
	private PlanetDTO ferengi;
	private PlanetDTO vulcano;

	/**
	 * @see WeatherService#calculateWeatherInSelectedYear(int year)
	 */
	public WeatherIterationDTO calculateWeatherInSelectedYear(int year) {
		/*
		 * Asumo que un año son 360 días (año Ferengi) ya que en este sistema es el año
		 * que más días tiene y me sirve para contener a los otros dos planetas, además
		 * coincide con la cantidad de días que tardan los planetas en reiniciar el
		 * ciclo en el que se encuentran otra vez en el mismo estado inicial.
		 */
		/*
		 * Asumo que el estado inicial del sistema será aquel donde Betasoide y Ferengi
		 * se encuentren en el grado 360 y Vulcano en el grado 0. De esta manera, los
		 * tres inician alineados tanto entre ellos como con el sol y la diferencia de
		 * grados es solamente para diferenciar si van en sentido horario o antihorario.
		 */
		initDataBase();

		WeatherIteration weatherIteration = weatherIterationDAO.getById(new WeatherIteration(1l));

		int countDroughtPeriod = weatherIteration.getCountDroughtPeriod();
		int countRainyPeriod = weatherIteration.getCountRainyPeriod();
		int countOptimalConditions = weatherIteration.getCountOptimalConditions();
		/*
		 * Sabemos que los tres planetas vuelven al mismo estado inicial cada 360 días.
		 * Por ende, no es necesario recorrer toda la cantidad de días correspondiente a
		 * la cantidad de años pedida, es suficiente con recorrer hasta 360 días y luego
		 * calcular lo deseado.
		 */
		countDroughtPeriod *= year;
		countRainyPeriod *= year;
		countOptimalConditions *= year;
		/*
		 * Por último se calculan los días donde hubo máxima intensidad de lluvia.
		 */
		List<Integer> daysWithMaxRain = new ArrayList<Integer>();
		List<Weather> weatherList = weatherDAO.listByIntenceDay(Weather.class);

		for (int y = 0; y < year; y++) {
			for (Weather weather : weatherList) {
				if (weather.isIntenseRainDay()) {
					daysWithMaxRain.add(weather.getDay() + (360 * y));
				}
			}
		}
		return new WeatherIterationDTO(countDroughtPeriod, countRainyPeriod, countOptimalConditions, daysWithMaxRain);
	}

	/**
	 * Inicializa la base de datos calculando los climas de cada día y la repetición
	 * de los períodos en un año ya que todos los años son iguales.
	 */
	private void initDataBase() {
		long countRows = weatherDAO.countRows(Weather.class);
		if (countRows == NUMBER_OF_DAYS_IN_A_YEAR) {
			// si la base ya está inicializada no es necesario hacer nada.
			return;
		}
		int day;
		int finalDay = NUMBER_OF_DAYS_IN_A_YEAR;

		int countDroughtPeriod = 0;
		int countRainyPeriod = 0;
		int countOptimalConditions = 0;

		List<Weather> weatherRainyPeriodList = new ArrayList<Weather>();
		boolean isRainyPeriod = false;
		boolean isOptimalConditionsPeriod = false;
		boolean countedOptimalDay = true;

		int maxPerimeter = 0;
		/*
		 * Tomo que el día 1 es aquel donde el día está terminado, por ende, en el día 1
		 * considero el primer movimiento de los planetas.
		 */
		for (day = 1; day <= finalDay; day++) {
			Weather weather = new Weather();
			weather.setDay(day);

			// se mueven los planetas 1 día.
			betasoide.move();
			ferengi.move();
			vulcano.move();

			// si están alineados con el sol
			if (geometryUtility.areAlignedWithCenter(betasoide.getActualDegree(), ferengi.getActualDegree(), vulcano.getActualDegree())) {
				weather.setDroughtPeriod(true);
				isRainyPeriod = false;
				isOptimalConditionsPeriod = false;
				countDroughtPeriod++;
			}
			// si forman un triángulo con el sol adentro.
			else if (geometryUtility.formATriangleAndHaveSun(betasoide.getActualDegree(), ferengi.getActualDegree(), vulcano.getActualDegree())) {

				int trianglePerimeter = geometryUtility.getDistance(betasoide, ferengi) + geometryUtility.getDistance(ferengi, vulcano)
						+ geometryUtility.getDistance(betasoide, vulcano);
				if (trianglePerimeter > maxPerimeter) {
					maxPerimeter = trianglePerimeter;
				}
				isRainyPeriod = true;
				isOptimalConditionsPeriod = false;

				weather.setRainyPeriod(isRainyPeriod);
				weather.setPerimeter(trianglePerimeter);
				weatherRainyPeriodList.add(weather);
			}
			// si están alineados (descartamos antes la opción con el sol)
			else if (geometryUtility.areAligned(betasoide, ferengi, vulcano)) {
				isRainyPeriod = false;
				countedOptimalDay = false;
				weather.setOptimalConditions(true);
				isOptimalConditionsPeriod = true;
			} else {
				// es un día normal
				isRainyPeriod = false;
				isOptimalConditionsPeriod = false;
			}
			// guarda el clima en la base.
			weatherDAO.save(weather);

			// busca el día de máxima intensidad de lluvia y contabiliza el período de
			// lluvia.
			if (!isRainyPeriod && maxPerimeter != 0) {
				// esto se da solo cuando estoy saliendo de un período de lluvia...
				countRainyPeriod++;
				updateIntenceWeatherDay(weatherRainyPeriodList, maxPerimeter);
				maxPerimeter = 0;
				weatherRainyPeriodList.clear();
			}
			// contabiliza el período de óptimas condiciones.
			if (!isOptimalConditionsPeriod && !countedOptimalDay) {
				countOptimalConditions++;
				countedOptimalDay = true;
			}
		}
		// por último se guarda la cantidad de veces que se repite cada período en 1 año
		// ferengi.
		WeatherIteration weatherIteration = new WeatherIteration(countDroughtPeriod, countRainyPeriod, countOptimalConditions);
		weatherIterationDAO.save(weatherIteration);
	}

	/**
	 * Actualiza el clima del día en el caso de que sea el día de máxima intensidad
	 * de lluvia.
	 * 
	 * @param weatherRainyPeriodList 
	 * @param maxPerimeter
	 */
	private void updateIntenceWeatherDay(List<Weather> weatherRainyPeriodList, int maxPerimeter) {
		long intenceRainDay = 0;
		for (Weather weatherRainyPeriod : weatherRainyPeriodList) {
			if (weatherRainyPeriod.getPerimeter() == maxPerimeter) {
				intenceRainDay = weatherRainyPeriod.getDay();
				break;
			}
		}
		// actualiza el clima del día de máxima intensidad en la base.
		Weather weatherWithIntenceRainDay = weatherDAO.getById(new Weather(intenceRainDay));
		if (weatherWithIntenceRainDay != null) {
			weatherWithIntenceRainDay.setIntenseRainDay(true);
			weatherDAO.update(weatherWithIntenceRainDay);
		}
	}

	/**
	 * Devuelve el clima propio del díaa pasado por pará|metro.
	 * 
	 * @param day el día buscado.
	 * @return el clima de ese día.
	 */
	public WeatherDTO getWeatherByDay(int day) {
		initDataBase();

		int dayInIteration = 0;
		if (day > NUMBER_OF_DAYS_IN_A_YEAR) {
			dayInIteration = day % NUMBER_OF_DAYS_IN_A_YEAR;
		}
		if (dayInIteration == 0) {
			dayInIteration = day;
		}
		Weather weather = new Weather();
		weather.setId(Long.valueOf(dayInIteration));
		weather = weatherDAO.getById(weather);

		return new WeatherDTO(dayInIteration, clasificateWeatherDay(weather).getDescription());
	}

	/**
	 * Clasifica y devuelve el tipo de clima del día seleccionado.
	 * 
	 * @param weather el día seleccionado.
	 * @return el enum que lo clasifica.
	 */
	private WeatherDayEnum clasificateWeatherDay(Weather weather) {
		WeatherDayEnum weatherDay = WeatherDayEnum.NORMAL_DAY;
		if (weather.isDroughtPeriod()) {
			weatherDay = WeatherDayEnum.DROUGHT_DAY;
		} else if (weather.isRainyPeriod()) {
			if (weather.isIntenseRainDay()) {
				weatherDay = WeatherDayEnum.INTENSE_RAIN_DAY;
			} else {
				weatherDay = WeatherDayEnum.RAINY_DAY;
			}
		} else if (weather.isOptimalConditions()) {
			weatherDay = WeatherDayEnum.OPTIMAL_DAY;
		}
		return weatherDay;
	}

	/**
	 * @param geometryUtility the geometryUtility to set
	 */
	public void setGeometryUtility(GeometryUtility geometryUtility) {
		this.geometryUtility = geometryUtility;
	}

	/**
	 * @param weatherDAO the weatherDAO to set
	 */
	public void setWeatherDAO(WeatherDAO weatherDAO) {
		this.weatherDAO = weatherDAO;
	}

	/**
	 * @param weatherIterationDAO the weatherIterationDAO to set
	 */
	public void setWeatherIterationDAO(WeatherIterationDAO weatherIterationDAO) {
		this.weatherIterationDAO = weatherIterationDAO;
	}

	/**
	 * @param betasoide the betasoide to set
	 */
	public void setBetasoide(PlanetDTO betasoide) {
		this.betasoide = betasoide;
	}

	/**
	 * @param ferengi the ferengi to set
	 */
	public void setFerengi(PlanetDTO ferengi) {
		this.ferengi = ferengi;
	}

	/**
	 * @param vulcano the vulcano to set
	 */
	public void setVulcano(PlanetDTO vulcano) {
		this.vulcano = vulcano;
	}
}