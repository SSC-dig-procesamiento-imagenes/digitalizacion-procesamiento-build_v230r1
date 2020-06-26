package mx.com.teclo.base.vo.escaner;

public class EscanerVO{

	private Long idEscaner;

	private String marca;

	private String modelo;

	/**
	 * @return the idEscaner
	 */
	public Long getIdEscaner() {
		return idEscaner;
	}

	/**
	 * @param idEscaner the idEscaner to set
	 */
	public void setIdEscaner(Long idEscaner) {
		this.idEscaner = idEscaner;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}