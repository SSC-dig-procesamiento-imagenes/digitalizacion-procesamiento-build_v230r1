package mx.com.teclo.base.vo.plantilla;


public class MarcasPlantillaVO {

    private Long idMarcas;

	private Integer coordenadaiY;
    
	private Integer coordenadaiX;
    
	private Integer coordenadafY;
    
	private Integer coordenadafX;
    
	private OrientacionesVO orientaciones;
    
	private Integer numeroObjetos;

	/**
	 * @return the idMarcas
	 */
	public Long getIdMarcas() {
		return idMarcas;
	}

	/**
	 * @param idMarcas the idMarcas to set
	 */
	public void setIdMarcas(Long idMarcas) {
		this.idMarcas = idMarcas;
	}

	/**
	 * @return the coordenadaiY
	 */
	public Integer getCoordenadaiY() {
		return coordenadaiY;
	}

	/**
	 * @param coordenadaiY the coordenadaiY to set
	 */
	public void setCoordenadaiY(Integer coordenadaiY) {
		this.coordenadaiY = coordenadaiY;
	}

	/**
	 * @return the coordenadaiX
	 */
	public Integer getCoordenadaiX() {
		return coordenadaiX;
	}

	/**
	 * @param coordenadaiX the coordenadaiX to set
	 */
	public void setCoordenadaiX(Integer coordenadaiX) {
		this.coordenadaiX = coordenadaiX;
	}

	/**
	 * @return the coordenadafY
	 */
	public Integer getCoordenadafY() {
		return coordenadafY;
	}

	/**
	 * @param coordenadafY the coordenadafY to set
	 */
	public void setCoordenadafY(Integer coordenadafY) {
		this.coordenadafY = coordenadafY;
	}

	/**
	 * @return the coordenadafX
	 */
	public Integer getCoordenadafX() {
		return coordenadafX;
	}

	/**
	 * @param coordenadafX the coordenadafX to set
	 */
	public void setCoordenadafX(Integer coordenadafX) {
		this.coordenadafX = coordenadafX;
	}

	/**
	 * @return the orientaciones
	 */
	public OrientacionesVO getOrientaciones() {
		return orientaciones;
	}

	/**
	 * @param orientaciones the orientaciones to set
	 */
	public void setOrientaciones(OrientacionesVO orientaciones) {
		this.orientaciones = orientaciones;
	}

	/**
	 * @return the numeroObjetos
	 */
	public Integer getNumeroObjetos() {
		return numeroObjetos;
	}

	/**
	 * @param numeroObjetos the numeroObjetos to set
	 */
	public void setNumeroObjetos(Integer numeroObjetos) {
		this.numeroObjetos = numeroObjetos;
	}
}