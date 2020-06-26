package mx.com.teclo.base.vo.configuracionescaner;

import mx.com.teclo.base.vo.escaner.EscanerVO;
import mx.com.teclo.base.vo.resolucion.ResolucionVO;
import mx.com.teclo.base.vo.sumistropapel.SuministroPapelVO;
import mx.com.teclo.base.vo.tamaniopapel.TamanioPapaelVO;
import mx.com.teclo.base.vo.tipoarchivos.TipoArchivosVO;
import mx.com.teclo.base.vo.tipopixel.TipoPixelVO;

public class ConfiguracionEscanerVO{
	
	private Long idConfiguracionEscaner;

	private String nombreConfiguracionEscaner;

	private String nombreNomenclatura;

	private Integer contraste;
	
	private Integer brillo;
	
	private EscanerVO escaner;
	
	private TamanioPapaelVO tamanioPapael;
	
	private ResolucionVO resolucion;
	
	private TipoPixelVO tipoPixel;
	
	private TipoArchivosVO tipoArchivos;
	
	private SuministroPapelVO suministroPapel;

	public ConfiguracionEscanerVO() {
		super();
	}

	public ConfiguracionEscanerVO(Long idConfiguracionEscaner, String nombreConfiguracionEscaner,
			String nombreNomenclatura, Integer contraste, Integer brillo, EscanerVO escaner,
			TamanioPapaelVO tamanioPapael, ResolucionVO resolucion, TipoPixelVO tipoPixel, TipoArchivosVO tipoArchivos,
			SuministroPapelVO suministroPapel) {
		super();
		this.idConfiguracionEscaner = idConfiguracionEscaner;
		this.nombreConfiguracionEscaner = nombreConfiguracionEscaner;
		this.nombreNomenclatura = nombreNomenclatura;
		this.contraste = contraste;
		this.brillo = brillo;
		this.escaner = escaner;
		this.tamanioPapael = tamanioPapael;
		this.resolucion = resolucion;
		this.tipoPixel = tipoPixel;
		this.tipoArchivos = tipoArchivos;
		this.suministroPapel = suministroPapel;
	}

	public Long getIdConfiguracionEscaner() {
		return idConfiguracionEscaner;
	}

	public void setIdConfiguracionEscaner(Long idConfiguracionEscaner) {
		this.idConfiguracionEscaner = idConfiguracionEscaner;
	}

	public String getNombreConfiguracionEscaner() {
		return nombreConfiguracionEscaner;
	}

	public void setNombreConfiguracionEscaner(String nombreConfiguracionEscaner) {
		this.nombreConfiguracionEscaner = nombreConfiguracionEscaner;
	}

	public String getNombreNomenclatura() {
		return nombreNomenclatura;
	}

	public void setNombreNomenclatura(String nombreNomenclatura) {
		this.nombreNomenclatura = nombreNomenclatura;
	}

	public Integer getContraste() {
		return contraste;
	}

	public void setContraste(Integer contraste) {
		this.contraste = contraste;
	}

	public Integer getBrillo() {
		return brillo;
	}

	public void setBrillo(Integer brillo) {
		this.brillo = brillo;
	}

	public EscanerVO getEscaner() {
		return escaner;
	}

	public void setEscaner(EscanerVO escaner) {
		this.escaner = escaner;
	}

	public TamanioPapaelVO getTamanioPapael() {
		return tamanioPapael;
	}

	public void setTamanioPapael(TamanioPapaelVO tamanioPapael) {
		this.tamanioPapael = tamanioPapael;
	}

	public ResolucionVO getResolucion() {
		return resolucion;
	}

	public void setResolucion(ResolucionVO resolucion) {
		this.resolucion = resolucion;
	}

	public TipoPixelVO getTipoPixel() {
		return tipoPixel;
	}

	public void setTipoPixel(TipoPixelVO tipoPixel) {
		this.tipoPixel = tipoPixel;
	}

	public TipoArchivosVO getTipoArchivos() {
		return tipoArchivos;
	}

	public void setTipoArchivos(TipoArchivosVO tipoArchivos) {
		this.tipoArchivos = tipoArchivos;
	}

	public SuministroPapelVO getSuministroPapel() {
		return suministroPapel;
	}

	public void setSuministroPapel(SuministroPapelVO suministroPapel) {
		this.suministroPapel = suministroPapel;
	}

	
	
	
}