package mx.com.teclo.base.util.enumerados;

import java.util.HashMap;
import java.util.Map;


public enum ConfiguracionEnum {
	
	lecturaHorizontalArribaAbajo(1),
	metDigitalizacionDirecta(1),
	metDigitalizacionExterna(2),
	
	loteNoCreado(0),
	actualizarLote(1),
	guardarLote(2),
	
	loteEnPausa(1),
	loteEnValidacion(2),
	loteEnProcesamiento(3),
	loteEnValidacionResultados(4),
	loteProcesado(7),
	
	imagenValida(1),
	imagenInvalida(2),
	imagenNones(3),
	imagenPares(4),
	
	ladofrontal(1),
	ladotrasero(3),
	ladoambos(2);

	private Integer constante;
	private static Map<Integer, ConfiguracionEnum> dictionary;
	
	static {
		  dictionary = new HashMap<Integer, ConfiguracionEnum>();
		  for(ConfiguracionEnum state : values()) {
		      dictionary.put(state.constante, state);
		  }
		}
	
	ConfiguracionEnum(Integer constante){
		this.constante = constante;
	}
	
	public static ConfiguracionEnum fromInteger(Integer fromValue) {
		ConfiguracionEnum var = dictionary.get(fromValue);
		  if(var == null) {
		      return null;
		  }
		  return var;
		}

	public Integer getConstante() {
		return constante;
	}

	public void setConstante(Integer constante) {
		this.constante = constante;
	}

	public static Map<Integer, ConfiguracionEnum> getDictionary() {
		return dictionary;
	}

	public static void setDictionary(Map<Integer, ConfiguracionEnum> dictionary) {
		ConfiguracionEnum.dictionary = dictionary;
	}
	
	
}
