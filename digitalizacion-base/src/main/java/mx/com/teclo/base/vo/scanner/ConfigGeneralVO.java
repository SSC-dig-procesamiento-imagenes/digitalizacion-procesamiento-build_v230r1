package mx.com.teclo.base.vo.scanner;

import java.util.List;

import mx.com.teclo.base.vo.configuracionescaner.ConfiguracionEscanerVO;
import mx.com.teclo.base.vo.lotes.LoteVO;


public class ConfigGeneralVO {
	private ConfiguracionEscanerVO configuracionescaner;
	private LoteVO lote;
	private List<ConfigScannerVO> configscann;

	public ConfigGeneralVO() {
		super();
	}

	public ConfigGeneralVO(ConfiguracionEscanerVO configuracionescaner, LoteVO lote,
			List<ConfigScannerVO> configscann) {
		super();
		this.configuracionescaner = configuracionescaner;
		this.lote = lote;
		this.configscann = configscann;
	}

	public ConfiguracionEscanerVO getConfiguracionescaner() {
		return configuracionescaner;
	}

	public void setConfiguracionescaner(ConfiguracionEscanerVO configuracionescaner) {
		this.configuracionescaner = configuracionescaner;
	}

	public LoteVO getLote() {
		return lote;
	}

	public void setLote(LoteVO lote) {
		this.lote = lote;
	}

	public List<ConfigScannerVO> getConfigscann() {
		return configscann;
	}

	public void setConfigscann(List<ConfigScannerVO> configscann) {
		this.configscann = configscann;
	}

	
	
	
	
}
