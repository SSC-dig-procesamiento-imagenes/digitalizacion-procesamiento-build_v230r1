package mx.com.teclo.base.vo.plantilla;



public class AlveolosPlantillaVO {

    private Long idAlvelo;
    
    private Integer densidad;
    
    private Integer area;
    
    private PlantillasVO plantillas;

	/**
	 * @return the idAlvelo
	 */
	public Long getIdAlvelo() {
		return idAlvelo;
	}

	/**
	 * @param idAlvelo the idAlvelo to set
	 */
	public void setIdAlvelo(Long idAlvelo) {
		this.idAlvelo = idAlvelo;
	}

	/**
	 * @return the densidad
	 */
	public Integer getDensidad() {
		return densidad;
	}

	/**
	 * @param densidad the densidad to set
	 */
	public void setDensidad(Integer densidad) {
		this.densidad = densidad;
	}

	/**
	 * @return the area
	 */
	public Integer getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(Integer area) {
		this.area = area;
	}

	/**
	 * @return the plantillas
	 */
	public PlantillasVO getPlantillas() {
		return plantillas;
	}

	/**
	 * @param plantillas the plantillas to set
	 */
	public void setPlantillas(PlantillasVO plantillas) {
		this.plantillas = plantillas;
	}
}