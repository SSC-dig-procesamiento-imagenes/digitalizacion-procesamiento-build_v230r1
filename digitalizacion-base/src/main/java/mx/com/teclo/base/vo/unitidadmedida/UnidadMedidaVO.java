package mx.com.teclo.base.vo.unitidadmedida;

public class UnidadMedidaVO{

	private Long idUnidadMedida;

	private Integer valor;

	private String nombre;

	/**
	 * @return the idUnidadMedida
	 */
	public Long getIdUnidadMedida() {
		return idUnidadMedida;
	}

	/**
	 * @param idUnidadMedida the idUnidadMedida to set
	 */
	public void setIdUnidadMedida(Long idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
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