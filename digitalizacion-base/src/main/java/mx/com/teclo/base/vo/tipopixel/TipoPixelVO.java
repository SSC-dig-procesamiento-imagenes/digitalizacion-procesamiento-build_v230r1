package mx.com.teclo.base.vo.tipopixel;

public class TipoPixelVO{

	private Long idTipoPixel;

	private Integer valor;

	private String nombre;

	/**
	 * @return the idTipoPixel
	 */
	public Long getIdTipoPixel() {
		return idTipoPixel;
	}

	/**
	 * @param idTipoPixel the idTipoPixel to set
	 */
	public void setIdTipoPixel(Long idTipoPixel) {
		this.idTipoPixel = idTipoPixel;
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
}