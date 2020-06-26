package mx.com.teclo.base.vo.sumistropapel;

public class SuministroPapelVO{

	private Long idSuministroPapel;

	private Integer valor;

	private String nombre;

	/**
	 * @return the idSuministroPapel
	 */
	public Long getIdSuministroPapel() {
		return idSuministroPapel;
	}

	/**
	 * @param idSuministroPapel the idSuministroPapel to set
	 */
	public void setIdSuministroPapel(Long idSuministroPapel) {
		this.idSuministroPapel = idSuministroPapel;
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