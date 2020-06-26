package mx.com.teclo.base.vo.tipoarchivos;

public class TipoArchivosVO{

	private Long idTipoArchivos;

	private Integer valor;

	private String nombre;
	
	private String extencion;

	/**
	 * @return the idTipoArchivos
	 */
	public Long getIdTipoArchivos() {
		return idTipoArchivos;
	}

	/**
	 * @param idTipoArchivos the idTipoArchivos to set
	 */
	public void setIdTipoArchivos(Long idTipoArchivos) {
		this.idTipoArchivos = idTipoArchivos;
	}

	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Integer valor) {
		this.valor = valor;
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
	 * @return the extencion
	 */
	public String getExtencion() {
		return extencion;
	}

	/**
	 * @param extencion the extencion to set
	 */
	public void setExtencion(String extencion) {
		this.extencion = extencion;
	}
}