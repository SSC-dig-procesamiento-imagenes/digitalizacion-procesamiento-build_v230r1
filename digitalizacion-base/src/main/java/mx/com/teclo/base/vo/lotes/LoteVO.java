package mx.com.teclo.base.vo.lotes;

import java.util.Date;

import mx.com.teclo.base.vo.configuracionescaner.ConfiguracionEscanerVO;
import mx.com.teclo.base.vo.estatusproceso.EstatusProcesoVO;


public class LoteVO {
	private Long idLote;
	private String fechaCracionLote;
	private String rutaAlmacenamiento;
	private Integer numeroTotalImagenes;
	private Integer numeroTotalRechazadas;
	private Integer numeroTotalAceptadas;
	private EstatusProcesoVO estatusProceso;
	private String nombreLote;
	private ConfiguracionEscanerVO configuracionEscaner;
	private Integer metDigitalizacion;
	private Integer nextNumber=0;
	private Long nuFolioInicial;
	private Long nuFolioFinal;

	public LoteVO() {
		super();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((configuracionEscaner == null) ? 0 : configuracionEscaner.hashCode());
		result = prime * result + ((estatusProceso == null) ? 0 : estatusProceso.hashCode());
		result = prime * result + ((fechaCracionLote == null) ? 0 : fechaCracionLote.hashCode());
		result = prime * result + ((idLote == null) ? 0 : idLote.hashCode());
		result = prime * result + ((metDigitalizacion == null) ? 0 : metDigitalizacion.hashCode());
		result = prime * result + ((nextNumber == null) ? 0 : nextNumber.hashCode());
		result = prime * result + ((nombreLote == null) ? 0 : nombreLote.hashCode());
		result = prime * result + ((numeroTotalAceptadas == null) ? 0 : numeroTotalAceptadas.hashCode());
		result = prime * result + ((numeroTotalImagenes == null) ? 0 : numeroTotalImagenes.hashCode());
		result = prime * result + ((numeroTotalRechazadas == null) ? 0 : numeroTotalRechazadas.hashCode());
		result = prime * result + ((rutaAlmacenamiento == null) ? 0 : rutaAlmacenamiento.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoteVO other = (LoteVO) obj;
		if (configuracionEscaner == null) {
			if (other.configuracionEscaner != null)
				return false;
		} else if (!configuracionEscaner.equals(other.configuracionEscaner))
			return false;
		if (estatusProceso == null) {
			if (other.estatusProceso != null)
				return false;
		} else if (!estatusProceso.equals(other.estatusProceso))
			return false;
		if (fechaCracionLote == null) {
			if (other.fechaCracionLote != null)
				return false;
		} else if (!fechaCracionLote.equals(other.fechaCracionLote))
			return false;
		if (idLote == null) {
			if (other.idLote != null)
				return false;
		} else if (!idLote.equals(other.idLote))
			return false;
		if (metDigitalizacion == null) {
			if (other.metDigitalizacion != null)
				return false;
		} else if (!metDigitalizacion.equals(other.metDigitalizacion))
			return false;
		if (nextNumber == null) {
			if (other.nextNumber != null)
				return false;
		} else if (!nextNumber.equals(other.nextNumber))
			return false;
		if (nombreLote == null) {
			if (other.nombreLote != null)
				return false;
		} else if (!nombreLote.equals(other.nombreLote))
			return false;
		if (numeroTotalAceptadas == null) {
			if (other.numeroTotalAceptadas != null)
				return false;
		} else if (!numeroTotalAceptadas.equals(other.numeroTotalAceptadas))
			return false;
		if (numeroTotalImagenes == null) {
			if (other.numeroTotalImagenes != null)
				return false;
		} else if (!numeroTotalImagenes.equals(other.numeroTotalImagenes))
			return false;
		if (numeroTotalRechazadas == null) {
			if (other.numeroTotalRechazadas != null)
				return false;
		} else if (!numeroTotalRechazadas.equals(other.numeroTotalRechazadas))
			return false;
		if (rutaAlmacenamiento == null) {
			if (other.rutaAlmacenamiento != null)
				return false;
		} else if (!rutaAlmacenamiento.equals(other.rutaAlmacenamiento))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "LoteVO [idLote=" + idLote + ", fechaCracionLote=" + fechaCracionLote + ", rutaAlmacenamiento="
				+ rutaAlmacenamiento + ", numeroTotalImagenes=" + numeroTotalImagenes + ", numeroTotalRechazadas="
				+ numeroTotalRechazadas + ", numeroTotalAceptadas=" + numeroTotalAceptadas + ", estatusProceso="
				+ estatusProceso + ", nombreLote=" + nombreLote + ", configuracionEscaner=" + configuracionEscaner
				+ ", metDigitalizacion=" + metDigitalizacion + ", nextNumber=" + nextNumber + "]";
	}


	public Long getIdLote() {
		return idLote;
	}


	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}


	public String getFechaCracionLote() {
		return fechaCracionLote;
	}


	public void setFechaCracionLote(String fechaCracionLote) {
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


	public EstatusProcesoVO getEstatusProceso() {
		return estatusProceso;
	}


	public void setEstatusProceso(EstatusProcesoVO estatusProceso) {
		this.estatusProceso = estatusProceso;
	}


	public String getNombreLote() {
		return nombreLote;
	}


	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}


	public ConfiguracionEscanerVO getConfiguracionEscaner() {
		return configuracionEscaner;
	}


	public void setConfiguracionEscaner(ConfiguracionEscanerVO configuracionEscaner) {
		this.configuracionEscaner = configuracionEscaner;
	}


	public Integer getMetDigitalizacion() {
		return metDigitalizacion;
	}


	public void setMetDigitalizacion(Integer metDigitalizacion) {
		this.metDigitalizacion = metDigitalizacion;
	}


	public Integer getNextNumber() {
		return nextNumber;
	}


	public void setNextNumber(Integer nextNumber) {
		this.nextNumber = nextNumber;
	}


	public LoteVO(Long idLote, String fechaCracionLote, String rutaAlmacenamiento, Integer numeroTotalImagenes,
			Integer numeroTotalRechazadas, Integer numeroTotalAceptadas, EstatusProcesoVO estatusProceso,
			String nombreLote, ConfiguracionEscanerVO configuracionEscaner, Integer metDigitalizacion,
			Integer nextNumber) {
		super();
		this.idLote = idLote;
		this.fechaCracionLote = fechaCracionLote;
		this.rutaAlmacenamiento = rutaAlmacenamiento;
		this.numeroTotalImagenes = numeroTotalImagenes;
		this.numeroTotalRechazadas = numeroTotalRechazadas;
		this.numeroTotalAceptadas = numeroTotalAceptadas;
		this.estatusProceso = estatusProceso;
		this.nombreLote = nombreLote;
		this.configuracionEscaner = configuracionEscaner;
		this.metDigitalizacion = metDigitalizacion;
		this.nextNumber = nextNumber;
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


	public LoteVO(Long idLote, String fechaCracionLote, String rutaAlmacenamiento, Integer numeroTotalImagenes,
			Integer numeroTotalRechazadas, Integer numeroTotalAceptadas, EstatusProcesoVO estatusProceso,
			String nombreLote, ConfiguracionEscanerVO configuracionEscaner, Integer metDigitalizacion,
			Integer nextNumber, Long nuFolioInicial, Long nuFolioFinal) {
		super();
		this.idLote = idLote;
		this.fechaCracionLote = fechaCracionLote;
		this.rutaAlmacenamiento = rutaAlmacenamiento;
		this.numeroTotalImagenes = numeroTotalImagenes;
		this.numeroTotalRechazadas = numeroTotalRechazadas;
		this.numeroTotalAceptadas = numeroTotalAceptadas;
		this.estatusProceso = estatusProceso;
		this.nombreLote = nombreLote;
		this.configuracionEscaner = configuracionEscaner;
		this.metDigitalizacion = metDigitalizacion;
		this.nextNumber = nextNumber;
		this.nuFolioInicial = nuFolioInicial;
		this.nuFolioFinal = nuFolioFinal;
	}


	
	
}
