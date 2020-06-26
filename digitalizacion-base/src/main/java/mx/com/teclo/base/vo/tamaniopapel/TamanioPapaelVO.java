package mx.com.teclo.base.vo.tamaniopapel;

import mx.com.teclo.base.vo.unitidadmedida.UnidadMedidaVO;

public class TamanioPapaelVO{

	private Long idTamanioPapel;

	private Integer valor;

	private String nombre;
	
	private String ancho;
	
	private String alto;
	
	private UnidadMedidaVO UnidadMedida;
	

	/**
	 * @return the idTamanioPapel
	 */
	public Long getIdTamanioPapel() {
		return idTamanioPapel;
	}

	/**
	 * @param idTamanioPapel the idTamanioPapel to set
	 */
	public void setIdTamanioPapel(Long idTamanioPapel) {
		this.idTamanioPapel = idTamanioPapel;
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
	 * @return the ancho
	 */
	public String getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(String ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the alto
	 */
	public String getAlto() {
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(String alto) {
		this.alto = alto;
	}

	/**
	 * @return the unidadMedida
	 */
	public UnidadMedidaVO getUnidadMedida() {
		return UnidadMedida;
	}

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(UnidadMedidaVO unidadMedida) {
		UnidadMedida = unidadMedida;
	}
}