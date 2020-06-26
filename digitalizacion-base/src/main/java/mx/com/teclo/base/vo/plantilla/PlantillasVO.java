package mx.com.teclo.base.vo.plantilla;

public class PlantillasVO {
	
	private Long id;
    
	private String nombre;
    
	private String descripcion;

	private Integer algoritmoAjuste;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the algoritmoAjuste
	 */
	public Integer getAlgoritmoAjuste() {
		return algoritmoAjuste;
	}

	/**
	 * @param algoritmoAjuste the algoritmoAjuste to set
	 */
	public void setAlgoritmoAjuste(Integer algoritmoAjuste) {
		this.algoritmoAjuste = algoritmoAjuste;
	}
}