package sistema.solar.entity;

/**
 * Entidad base, atributos en común.
 * 
 * @author watas
 */
public abstract class Entity {

	/**
	 * Obtiene el id de la entidad.
	 * 
	 * @return id el Id de la entidad.
	 */
	public abstract Long getId();

	/**
	 * Configura el id de la entidad.
	 * 
	 * @param id id de la entidad.
	 */
	public abstract void setId(Long id);
}
