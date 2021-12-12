package sistema.solar.service.impl.util;

import java.util.Arrays;

import sistema.solar.dto.PlanetDTO;

/**
 * Utilidades de Geometr�a.
 * 
 * @author watas
 */
public class GeometryUtility {

	/**
	 * En base a tres �ngulos pasados por par�metros devolver� si se encuentran
	 * alineados entre ellos y con respecto al sol (centro de la circunsferencia).
	 * 
	 * @param gradeX
	 * @param gradeY
	 * @param gradeZ
	 * @return
	 */
	public boolean areAlignedWithCenter(int gradeX, int gradeY, int gradeZ) {
		// si los grados son mayores a 180, es porque est�n en el cuatro o quinto
		// cuadrante pensando en un sistema de coordenadas cartesiano. Y para poder
		// igualar los tres �ngulos, necesito restarles 180 grados.
		if (gradeX > 180) {
			gradeX = gradeX - 180;
		}
		if (gradeY > 180) {
			gradeY = gradeY - 180;
		}
		if (gradeZ > 180) {
			gradeZ = gradeZ - 180;
		}
		// si los �ngulos coinciden es porque est�n alineados con respecto al centro del
		// eje de coordenadas, en este caso, el sol.
		if (gradeX == gradeY && gradeY == gradeZ) {
			return true;
		}
		return false;
	}

	/**
	 * En base a tres �ngulos pasados por par�metros devolver� si forman un
	 * tri�ngulo entre ellos y con el sol dentro (centro de la circunsferencia).
	 * 
	 * @param gradeX
	 * @param gradeY
	 * @param gradeZ
	 * @return
	 */
	public boolean formATriangleAndHaveSun(int gradeX, int gradeY, int gradeZ) {

		int[] grades = getGradesInOrder(gradeX, gradeY, gradeZ);

		int minimum = grades[0];
		int medium = grades[1];
		int maximum = grades[2];

		if ((minimum - medium) * (-1) > 180) {
			return false;
		}
		if ((medium - maximum) * (-1) > 180) {
			return false;
		}
		if (360 + (maximum - minimum) * (-1) > 180) {
			return false;
		}
		return true;
	}

	/**
	 * Ordena los grados de menor a mayor.
	 * 
	 * @param gradeX
	 * @param gradeY
	 * @param gradeZ
	 * @return
	 */
	private int[] getGradesInOrder(int gradeX, int gradeY, int gradeZ) {
		int[] grades = new int[3];
		grades[0] = gradeX;
		grades[1] = gradeY;
		grades[2] = gradeZ;
		Arrays.sort(grades);
		return grades;
	}

	/**
	 * Devuelve si los tres planetas pasados por par�metros se encuentan alineados.
	 * 
	 * @param betasoide
	 * @param ferengi
	 * @param vulcano
	 * @return
	 */
	public boolean areAligned(PlanetDTO betasoide, PlanetDTO ferengi, PlanetDTO vulcano) {
		/*
		 * Tres puntos a, b y c se encuentan alineados si o solo si los vectores que los
		 * unen (ab y bc) tienen la misma direcci�n. Para eso se usa la f�rmula
		 * (x2-x1)/(x3-x2)=(y2-y1)/(y3-y2). Por ende, necesito saber las coordenadas
		 * cartecianas en donde se encuentra cada planeta.
		 * 
		 * Considero los valores como enteros puesto que despreciamos la diferencia en
		 * metros que pueda haber en coordenadas. Tom� esta decisi�n porque si lo
		 * hacemos m�s preciso, la ecuaci�n nunca da igual.
		 * 
		 * https://www.superprof.es/apuntes/escolar/matematicas/geoana/vec/tres-puntos-
		 * esten-alineados.html
		 */
		int xBetasoide = getX(betasoide);
		int yBetasoide = getY(betasoide);

		int xFerengi = getX(ferengi);
		int yFerengi = getY(ferengi);

		int xVulcano = getX(vulcano);
		int yVulcano = getY(vulcano);

		if ((xVulcano - xFerengi) != 0 && (yVulcano - yFerengi) != 0) {
			if ((xFerengi - xBetasoide) / (xVulcano - xFerengi) == (yFerengi - yBetasoide) / (yVulcano - yFerengi)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Devuelve la coordenada X en coordenadas cartesianas de donde se encuentra el
	 * planeta en base a la ecuaci�n de transformaci�n de coordenadas polares a
	 * cartesianas: (x = radio * cos(�ngulo)).
	 * 
	 * @param planeta
	 * @return
	 */
	private int getX(PlanetDTO planeta) {
		double b = (double) Math.toRadians(planeta.getActualDegree());
		return (int) (planeta.getDistanceOfSun() / 10 * Math.cos(b));
	}

	/**
	 * Devuelve la coordenada Y en coordenadas cartesianas de donde se encuentra el
	 * planeta en base a la ecuaci�n de transformaci�n de coordenadas polares a
	 * cartesianas: (y = radio * sen(�ngulo)).
	 * 
	 * @param planeta
	 * @return
	 */
	private int getY(PlanetDTO planeta) {
		double b = (double) Math.toRadians(planeta.getActualDegree());
		return (int) (planeta.getDistanceOfSun() / 10 * Math.sin(b));
	}

	/**
	 * Devuelva la distancia entre dos planetas.
	 * 
	 * @param planet1
	 * @param planet2
	 * @return
	 */
	public int getDistance(PlanetDTO planet1, PlanetDTO planet2) {
		/*
		 * Para calcular la distancia entre dos puntos en coordenadas polares hay que
		 * utilizar la f�rmula: sacar la ra�z cuadrada de (r1^2 + r2^2 - 2*r1*r2*cos(la
		 * resta de sus �ngulos);
		 * 
		 * http://lya.fciencias.unam.mx/gfgf/ga20101/material/ChapXLehmann.pdf
		 */
		double degreePlanet1 = Math.toRadians(planet1.getActualDegree());
		double degreePlanet2 = Math.toRadians(planet2.getActualDegree());

		double angleBetweenPlanets = (degreePlanet1 - degreePlanet2) * (-1);

		int radiusPlanet1 = planet1.getDistanceOfSun();
		int radiusPlanet2 = planet2.getDistanceOfSun();

		return (int) (Math.sqrt(Math.pow(radiusPlanet1, 2) + Math.pow(radiusPlanet2, 2) - 2 * radiusPlanet1 * radiusPlanet2 * Math.cos(angleBetweenPlanets)));
	}
}