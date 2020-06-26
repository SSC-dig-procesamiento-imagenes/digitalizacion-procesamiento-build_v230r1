package mx.com.teclo.base.vo.lotes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LoteBitacoraVO {

	private Long idLote;
	private Long fechaCracionLote;
	private String rutaAlmacenamiento;
	private Integer numeroTotalImagenes;
	private Integer numeroTotalRechazadas;
	private Integer numeroTotalAceptadas;
	private Long idEtatusProceso;
	private String nombreLote;
	private Long idConfiguracionEscaner;
	private Integer metDigitalizacion;
	
	private Long nuFolioInicial;
	private Long nuFolioFinal;
	
	private String tabla;
	private Long componenteId;
	private Long conceptoId;
	private String valorOriginal;
	private String valorFinal;
	private Long modificadoPor;
	private String idRegistro;
	private String registroDescripcion;
	private String origen;
	
	
	
	public Long getNuFolioInicial() {
		return nuFolioInicial;
	}
	public void setNuFolioInicial(Long nuFolioInicial) {
		this.nuFolioInicial = nuFolioInicial;
	}
	public Long getNuFolioFinal() {
		return nuFolioFinal;
	}
	public void setNuFolioFinal(Long nuFolioFinal) {
		this.nuFolioFinal = nuFolioFinal;
	}
	public Long getIdLote() {
		return idLote;
	}
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}
	public Long getFechaCracionLote() {
		return fechaCracionLote;
	}
	public void setFechaCracionLote(Long fechaCracionLote) {
		this.fechaCracionLote = fechaCracionLote;
	}
	public String getRutaAlmacenamiento() {
		return rutaAlmacenamiento;
	}
	public void setRutaAlmacenamiento(String rutaAlmacenamiento) {
		this.rutaAlmacenamiento = rutaAlmacenamiento;
	}
	public Integer getNumeroTotalImagenes() {
		return numeroTotalImagenes;
	}
	public void setNumeroTotalImagenes(Integer numeroTotalImagenes) {
		this.numeroTotalImagenes = numeroTotalImagenes;
	}
	public Integer getNumeroTotalRechazadas() {
		return numeroTotalRechazadas;
	}
	public void setNumeroTotalRechazadas(Integer numeroTotalRechazadas) {
		this.numeroTotalRechazadas = numeroTotalRechazadas;
	}
	public Integer getNumeroTotalAceptadas() {
		return numeroTotalAceptadas;
	}
	public void setNumeroTotalAceptadas(Integer numeroTotalAceptadas) {
		this.numeroTotalAceptadas = numeroTotalAceptadas;
	}
	public Long getIdEtatusProceso() {
		return idEtatusProceso;
	}
	public void setIdEtatusProceso(Long idEtatusProceso) {
		this.idEtatusProceso = idEtatusProceso;
	}
	public String getNombreLote() {
		return nombreLote;
	}
	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}
	public Long getIdConfiguracionEscaner() {
		return idConfiguracionEscaner;
	}
	public void setIdConfiguracionEscaner(Long idConfiguracionEscaner) {
		this.idConfiguracionEscaner = idConfiguracionEscaner;
	}
	public Integer getMetDigitalizacion() {
		return metDigitalizacion;
	}
	public void setMetDigitalizacion(Integer metDigitalizacion) {
		this.metDigitalizacion = metDigitalizacion;
	}
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public Long getComponenteId() {
		return componenteId;
	}
	public void setComponenteId(Long componenteId) {
		this.componenteId = componenteId;
	}
	public Long getConceptoId() {
		return conceptoId;
	}
	public void setConceptoId(Long conceptoId) {
		this.conceptoId = conceptoId;
	}
	public String getValorOriginal() {
		return valorOriginal;
	}
	public void setValorOriginal(String valorOriginal) {
		this.valorOriginal = valorOriginal;
	}
	public String getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public String getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}
	public String getRegistroDescripcion() {
		return registroDescripcion;
	}
	public void setRegistroDescripcion(String registroDescripcion) {
		this.registroDescripcion = registroDescripcion;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	
	
}
