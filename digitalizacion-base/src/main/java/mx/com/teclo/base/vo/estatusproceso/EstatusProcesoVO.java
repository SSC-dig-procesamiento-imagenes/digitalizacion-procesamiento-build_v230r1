package mx.com.teclo.base.vo.estatusproceso;

public class EstatusProcesoVO {

	private Long idEstatusProceso;
	
	private String codigoEstatusProceso;
	
	private String descripcion;
	
	private String nombreEstatus;

	public EstatusProcesoVO() {
		super();
	}

	public EstatusProcesoVO(Long idEstatusProceso, String codigoEstatusProceso, String descripcion,
			String nombreEstatus) {
		super();
		this.idEstatusProceso = idEstatusProceso;
		this.codigoEstatusProceso = codigoEstatusProceso;
		this.descripcion = descripcion;
		this.nombreEstatus = nombreEstatus;
	}

	public Long getIdEstatusProceso() {
		return idEstatusProceso;
	}

	public void setIdEstatusProceso(Long idEstatusProceso) {
		this.idEstatusProceso = idEstatusProceso;
	}

	public String getCodigoEstatusProceso() {
		return codigoEstatusProceso;
	}

	public void setCodigoEstatusProceso(String codigoEstatusProceso) {
		this.codigoEstatusProceso = codigoEstatusProceso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreEstatus() {
		return nombreEstatus;
	}

	public void setNombreEstatus(String nombreEstatus) {
		this.nombreEstatus = nombreEstatus;
	}
	
	

}