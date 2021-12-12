package sistema.solar.dto;

/**
 * Objeto que representa al planeta.
 * 
 * @author watas
 */
public class PlanetDTO {

	/**
	 * Nombre del planeta.
	 */
	private String name;

	/**
	 * Cantidad de grados que se mueve en un día.
	 */
	private int degreePerDay;

	/**
	 * Si gira en sentido horario.
	 */
	private Boolean clockwise;

	/**
	 * La distancia entre el planeta y el sol en kilómetros.
	 */
	private int distanceOfSun;

	/**
	 * El grado en el que se encuentra el planeta.
	 */
	private int actualDegree;

	/**
	 * Constructor.
	 */
	public PlanetDTO() {
	}

	/**
	 * Obtiene el grado en el que se encuentra el planeta.
	 * 
	 * @return El grado en el que se encuentra el planeta.
	 */
	public int getActualDegree() {
		if (clockwise) {
			if (actualDegree < 0) {
				actualDegree += 360;
			}
		} else {
			if (actualDegree > 360) {
				actualDegree -= 360;
			}
		}
		return actualDegree;
	}

	/**
	 * Que el planeta se mueva significa que aumentará el grado en el que se
	 * encuentra si va en sentido antihorario y disminuirá ese grado si va en
	 * sentido horario.
	 * 
	 * Este método moverá al planeta según el sentido.
	 */
	public void move() {
		if (clockwise) {
			actualDegree = actualDegree - degreePerDay;
		} else {
			actualDegree = actualDegree + degreePerDay;
		}
	}

	/**
	 * Configura el grado en el que se encuentra el planeta.
	 * 
	 * @param actualDegree El grado en el que se encuentra el planeta.
	 */
	public void setActualDegree(int actualDegree) {
		this.actualDegree = actualDegree;
	}

	/**
	 * Obtiene la distancia entre el planeta y el sol en kilómetros.
	 * 
	 * @return the distanceOfSun La distancia entre el planeta y el sol en
	 *         kilómetros.
	 */
	public int getDistanceOfSun() {
		return distanceOfSun;
	}

	/**
	 * Configura la distancia entre el planeta y el sol en kilómetros.
	 * 
	 * @param distanceOfSun La distancia entre el planeta y el sol en kilómetros.
	 */
	public void setDistanceOfSun(int distanceOfSun) {
		this.distanceOfSun = distanceOfSun;
	}

	/**
	 * Obtiene el nombre del planeta.
	 * 
	 * @return Nombre del planeta.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Configura el nombre del planeta.
	 * 
	 * @param name Nombre del planeta.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene la cantidad de grados que se mueve en un día.
	 * 
	 * @return Cantidad de grados que se mueve en un día.
	 */
	public int getDegreePerDay() {
		return degreePerDay;
	}

	/**
	 * Configura la cantidad de grados que se mueve en un día.
	 * 
	 * @param degreePerDay Cantidad de grados que se mueve en un día.
	 */
	public void setDegreePerDay(int degreePerDay) {
		this.degreePerDay = degreePerDay;
	}

	/**
	 * Obtiene si gira en sentido horario.
	 * 
	 * @return clockwise true Si gira en sentido horario. false caso contrario.
	 */
	public Boolean getClockwise() {
		return clockwise;
	}

	/**
	 * Configura si gira en sentido horario.
	 * 
	 * @param clockwise true Si gira en sentido horario. false caso contrario.
	 */
	public void setClockwise(Boolean clockwise) {
		this.clockwise = clockwise;
	}
}