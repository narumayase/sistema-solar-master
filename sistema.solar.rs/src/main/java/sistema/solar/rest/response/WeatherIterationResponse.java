package sistema.solar.rest.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Objeto de respuesta para dar los datos solicitados.
 * 
 * representa:
 * 
 * Cantidad de veces que hubo un período de sequía. Cantidad de veces que hubo
 * un período de lluvia. Cantidad de veces que hubo un período de óptimas
 * condiciones.
 * 
 * Y en qué días hubo un pico de lluvia máximo.
 * 
 * @author watas
 */
public class WeatherIterationResponse {

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
	 * Los días de picos de máxima intensidad de lluvia.
	 */
	private List<Integer> daysWithMaxRain;

	/**
	 * Constructor con los atributos del objeto.
	 * 
	 * @param countDroughtPeriod
	 * @param countRainyPeriod
	 * @param countOptimalConditions
	 * @param daysWithMaxRain
	 */
	public WeatherIterationResponse(int countDroughtPeriod, int countRainyPeriod, int countOptimalConditions, List<Integer> daysWithMaxRain) {
		super();
		this.countDroughtPeriod = countDroughtPeriod;
		this.countRainyPeriod = countRainyPeriod;
		this.countOptimalConditions = countOptimalConditions;
		this.daysWithMaxRain = daysWithMaxRain;
	}

	/**
	 * Obtiene la cantidad de veces que hubo un período de sequía.
	 * 
	 * @return the countDroughtPeriod Cantidad de veces que hubo un período de
	 *         sequía.
	 */
	@JsonProperty("cantidadDePeriodosConSequia")
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
	@JsonProperty("cantidadDePeriodosConLluvia")
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
	@JsonProperty("cantidadDePeriodosConCondicionesOptimas")
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

	/**
	 * Obtiene los días de picos de máxima intensidad de lluvia.
	 * 
	 * @return the daysWithMaxRain Los días de picos de máxima intensidad de lluvia.
	 */
	@JsonProperty("diasDeMaximaIntensidadDeLluvia")
	public List<Integer> getDaysWithMaxRain() {
		return daysWithMaxRain;
	}

	/**
	 * Configura los días de picos de máxima intensidad de lluvia.
	 * 
	 * @param daysWithMaxRain Los días de picos de máxima intensidad de lluvia.
	 */
	public void setDaysWithMaxRain(List<Integer> daysWithMaxRain) {
		this.daysWithMaxRain = daysWithMaxRain;
	}
}