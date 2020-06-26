package mx.com.teclo.base.vo.lotes;

public class ActualizarLoteVO {
	
	private Long idLote;
	private Integer numeroTotalImagenes;
	private Long idEtatusProceso;
	
	private String tabla;
	private Long componenteId;
	private Long conceptoId;
	private String valorOriginal;
	private String valorFinal;
	private Long modificadoPor;
	private Long idRegistro;
	private String registroDescripcion;
	private String origen;
	private Long nuFolioInicial;
	private Long nuFolioFinal;
	private String nombreLote;
	private Long idUsuario;
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreLote() {
		return nombreLote;
	}
	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
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
	public Integer getNumeroTotalImagenes() {
		return numeroTotalImagenes;
	}
	public void setNumeroTotalImagenes(Integer numeroTotalImagenes) {
		this.numeroTotalImagenes = numeroTotalImagenes;
	}
	public Long getIdEtatusProceso() {
		return idEtatusProceso;
	}
	public void setIdEtatusProceso(Long idEtatusProceso) {
		this.idEtatusProceso = idEtatusProceso;
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
	public Long getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(Long idRegistro) {
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