package mx.com.teclo.base.vo.images;

import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

public class ImagenPersistenciaVO {

//    private Long idImagen;
    private String placa;
    private String numeroLicencia;
    private String tipoLicencia;
    private String licenciaExpedidaEn;
    private String placaExpedidaEn;
    private Long numeroArticuloInfraccion;
    private Long numeroFraccion;
    private String numeroInciso;
    private Long numeroParrafo;
    private String placaOficial;
    private String utDelegacion;
    private Long numeroFolio;
    private Date fechaInfraccion;
    private Long idlote;
    private String nombreImagen;
    private List<ImagenBlobPersistenciaVO> lbImagenes;
    
	public ImagenPersistenciaVO() {
		super();
	}

	public ImagenPersistenciaVO(String placa, String numeroLicencia, String tipoLicencia, String licenciaExpedidaEn,
			String placaExpedidaEn, Long numeroArticuloInfraccion, Long numeroFraccion, String numeroInciso,
			Long numeroParrafo, String placaOficial, String utDelegacion, Long numeroFolio, Date fechaInfraccion,
			Long idlote, String nombreImagen, List<ImagenBlobPersistenciaVO> lbImagenes) {
		super();
		this.placa = placa;
		this.numeroLicencia = numeroLicencia;
		this.tipoLicencia = tipoLicencia;
		this.licenciaExpedidaEn = licenciaExpedidaEn;
		this.placaExpedidaEn = placaExpedidaEn;
		this.numeroArticuloInfraccion = numeroArticuloInfraccion;
		this.numeroFraccion = numeroFraccion;
		this.numeroInciso = numeroInciso;
		this.numeroParrafo = numeroParrafo;
		this.placaOficial = placaOficial;
		this.utDelegacion = utDelegacion;
		this.numeroFolio = numeroFolio;
		this.fechaInfraccion = fechaInfraccion;
		this.idlote = idlote;
		this.nombreImagen = nombreImagen;
		this.lbImagenes = lbImagenes;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}

	public String getTipoLicencia() {
		return tipoLicencia;
	}

	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}

	public String getLicenciaExpedidaEn() {
		return licenciaExpedidaEn;
	}

	public void setLicenciaExpedidaEn(String licenciaExpedidaEn) {
		this.licenciaExpedidaEn = licenciaExpedidaEn;
	}

	public String getPlacaExpedidaEn() {
		return placaExpedidaEn;
	}

	public void setPlacaExpedidaEn(String placaExpedidaEn) {
		this.placaExpedidaEn = placaExpedidaEn;
	}

	public Long getNumeroArticuloInfraccion() {
		return numeroArticuloInfraccion;
	}

	public void setNumeroArticuloInfraccion(Long numeroArticuloInfraccion) {
		this.numeroArticuloInfraccion = numeroArticuloInfraccion;
	}

	public Long getNumeroFraccion() {
		return numeroFraccion;
	}

	public void setNumeroFraccion(Long numeroFraccion) {
		this.numeroFraccion = numeroFraccion;
	}

	public String getNumeroInciso() {
		return numeroInciso;
	}

	public void setNumeroInciso(String numeroInciso) {
		this.numeroInciso = numeroInciso;
	}

	public Long getNumeroParrafo() {
		return numeroParrafo;
	}

	public void setNumeroParrafo(Long numeroParrafo) {
		this.numeroParrafo = numeroParrafo;
	}

	public String getPlacaOficial() {
		return placaOficial;
	}

	public void setPlacaOficial(String placaOficial) {
		this.placaOficial = placaOficial;
	}

	public String getUtDelegacion() {
		return utDelegacion;
	}

	public void setUtDelegacion(String utDelegacion) {
		this.utDelegacion = utDelegacion;
	}

	public Long getNumeroFolio() {
		return numeroFolio;
	}

	public void setNumeroFolio(Long numeroFolio) {
		this.numeroFolio = numeroFolio;
	}

	public Date getFechaInfraccion() {
		return fechaInfraccion;
	}

	public void setFechaInfraccion(Date fechaInfraccion) {
		this.fechaInfraccion = fechaInfraccion;
	}

	public Long getIdlote() {
		return idlote;
	}

	public void setIdlote(Long idlote) {
		this.idlote = idlote;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public List<ImagenBlobPersistenciaVO> getLbImagenes() {
		return lbImagenes;
	}

	public void setLbImagenes(List<ImagenBlobPersistenciaVO> lbImagenes) {
		this.lbImagenes = lbImagenes;
	}

	@Override
	public String toString() {
		return "ImagenPersistenciaVO [placa=" + placa + ", numeroLicencia=" + numeroLicencia + ", tipoLicencia="
				+ tipoLicencia + ", licenciaExpedidaEn=" + licenciaExpedidaEn + ", placaExpedidaEn=" + placaExpedidaEn
				+ ", numeroArticuloInfraccion=" + numeroArticuloInfraccion + ", numeroFraccion=" + numeroFraccion
				+ ", numeroInciso=" + numeroInciso + ", numeroParrafo=" + numeroParrafo + ", placaOficial="
				+ placaOficial + ", utDelegacion=" + utDelegacion + ", numeroFolio=" + numeroFolio
				+ ", fechaInfraccion=" + fechaInfraccion + ", idlote=" + idlote + ", nombreImagen=" + nombreImagen
				+ ", lbImagenes=" + lbImagenes + "]";
	}
	
	
	
	

	
}
