package mx.com.teclo.base.vo.lotes;

import java.util.Date;

public class PersistirLoteLoteVO {
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
	
	
	public PersistirLoteLoteVO() {
		super();
	}
	public PersistirLoteLoteVO(Long idLote, Long fechaCracionLote, String rutaAlmacenamiento,
			Integer numeroTotalImagenes, Integer numeroTotalRechazadas, Integer numeroTotalAceptadas,
			Long idEtatusProceso, String nombreLote, Long idConfiguracionEscaner, Integer metDigitalizacion) {
		super();
		this.idLote = idLote;
		this.fechaCracionLote = fechaCracionLote;
		this.rutaAlmacenamiento = rutaAlmacenamiento;
		this.numeroTotalImagenes = numeroTotalImagenes;
		this.numeroTotalRechazadas = numeroTotalRechazadas;
		this.numeroTotalAceptadas = numeroTotalAceptadas;
		this.idEtatusProceso = idEtatusProceso;
		this.nombreLote = nombreLote;
		this.idConfiguracionEscaner = idConfiguracionEscaner;
		this.metDigitalizacion = metDigitalizacion;
	}
	
	
	
	public PersistirLoteLoteVO(Long idLote, Long fechaCracionLote, String rutaAlmacenamiento,
			Integer numeroTotalImagenes, Integer numeroTotalRechazadas, Integer numeroTotalAceptadas,
			Long idEtatusProceso, String nombreLote, Long idConfiguracionEscaner, Integer metDigitalizacion,
			Long nuFolioInicial, Long nuFolioFinal) {
		super();
		this.idLote = idLote;
		this.fechaCracionLote = fechaCracionLote;
		this.rutaAlmacenamiento = rutaAlmacenamiento;
		this.numeroTotalImagenes = numeroTotalImagenes;
		this.numeroTotalRechazadas = numeroTotalRechazadas;
		this.numeroTotalAceptadas = numeroTotalAceptadas;
		this.idEtatusProceso = idEtatusProceso;
		this.nombreLote = nombreLote;
		this.idConfiguracionEscaner = idConfiguracionEscaner;
		this.metDigitalizacion = metDigitalizacion;
		this.nuFolioInicial = nuFolioInicial;
		this.nuFolioFinal = nuFolioFinal;
	}
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
	@Override
	public String toString() {
		return "PersistirLoteLoteVO [idLote=" + idLote + ", fechaCracionLote=" + fechaCracionLote
				+ ", rutaAlmacenamiento=" + rutaAlmacenamiento + ", numeroTotalImagenes=" + numeroTotalImagenes
				+ ", numeroTotalRechazadas=" + numeroTotalRechazadas + ", numeroTotalAceptadas=" + numeroTotalAceptadas
				+ ", idEtatusProceso=" + idEtatusProceso + ", nombreLote=" + nombreLote + ", idConfiguracionEscaner="
				+ idConfiguracionEscaner + ", metDigitalizacion=" + metDigitalizacion + "]";
	}
	
	
	
}
