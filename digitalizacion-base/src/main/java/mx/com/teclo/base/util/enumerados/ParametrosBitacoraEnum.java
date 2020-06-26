package mx.com.teclo.base.util.enumerados;

public enum ParametrosBitacoraEnum {

	TDP_BITACORA_CAMBIOS("TDP_BITACORA_CAMBIOS"), ORIGEN_W("E"), BITACORA_CAMBIOS("BITACORA_CAMBIOS");
	
	private String parametro;
	
	private ParametrosBitacoraEnum(String parametro) {
		this.setParametro(parametro);
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	static ParametrosBitacoraEnum getArchivoTipo(Integer x) {
		for (ParametrosBitacoraEnum currentType : ParametrosBitacoraEnum.values()) {
	      if (x.equals(currentType.getParametro())) {
	        return currentType;
	      }
	    }
	    return null;
	}
}
