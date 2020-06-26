package mx.com.teclo.base.vo.plantilla;

import java.util.List;



public class PlantillaCompletaVO {
	private Long idPlantilla;
    
	private String nombre;
    
	private String descripcion;

	private Integer algoritmoAjuste;
    
	private List<CamposPlantillaVO> campos;
    
	private List<AreasTrabajoPlantillaVO> areasTrabajo;
    
	private List<AlveolosPlantillaVO> alveolos;
    
	private List<MarcasPlantillaVO> marcas;

	/**
	 * @return the idPlantilla
	 */
	public Long getIdPlantilla() {
		return idPlantilla;
	}

	/**
	 * @param idPlantilla the idPlantilla to set
	 */
	public void setIdPlantilla(Long idPlantilla) {
		this.idPlantilla = idPlantilla;
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

	/**
	 * @return the campos
	 */
	public List<CamposPlantillaVO> getCampos() {
		return campos;
	}

	/**
	 * @param campos the campos to set
	 */
	public void setCampos(List<CamposPlantillaVO> campos) {
		this.campos = campos;
	}

	/**
	 * @return the areasTrabajo
	 */
	public List<AreasTrabajoPlantillaVO> getAreasTrabajo() {
		return areasTrabajo;
	}

	/**
	 * @param areasTrabajo the areasTrabajo to set
	 */
	public void setAreasTrabajo(List<AreasTrabajoPlantillaVO> areasTrabajo) {
		this.areasTrabajo = areasTrabajo;
	}

	/**
	 * @return the alveolos
	 */
	public List<AlveolosPlantillaVO> getAlveolos() {
		return alveolos;
	}

	/**
	 * @param alveolos the alveolos to set
	 */
	public void setAlveolos(List<AlveolosPlantillaVO> alveolos) {
		this.alveolos = alveolos;
	}

	/**
	 * @return the marcas
	 */
	public List<MarcasPlantillaVO> getMarcas() {
		return marcas;
	}

	/**
	 * @param marcas the marcas to set
	 */
	public void setMarcas(List<MarcasPlantillaVO> marcas) {
		this.marcas = marcas;
	}
}