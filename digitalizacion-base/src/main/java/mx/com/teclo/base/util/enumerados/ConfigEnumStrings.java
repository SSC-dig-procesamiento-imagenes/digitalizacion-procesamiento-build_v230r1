package mx.com.teclo.base.util.enumerados;

import java.util.HashMap;
import java.util.Map;

public enum ConfigEnumStrings {

	codigo_formatocompresion("VFORMATCOMP"),
	codigo_nomenclatura("VNOMENCLATU"),
	codigo_archivodaniado("VARCHCORRUP"),
	codigo_inconsistencia("VINCONSISTE"),
	codigo_asociacionlados("VASOCIACION");
	
	private String constante;
	private static Map<String, ConfigEnumStrings> dictionary;
	
	static {
		  dictionary = new HashMap<String, ConfigEnumStrings>();
		  for(ConfigEnumStrings state : values()) {
		      dictionary.put(state.constante, state);
		  }
		}

	private ConfigEnumStrings(String constante) {
		this.constante = constante;
	}
	
	public static ConfigEnumStrings fromInteger(String fromValue) {
		ConfigEnumStrings var = dictionary.get(fromValue);
		  if(var == null) {
		      return null;
		  }
		  return var;
	}
	
	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}

	public static Map<String, ConfigEnumStrings> getDictionary() {
		return dictionary;
	}

	public static void setDictionary(Map<String, ConfigEnumStrings> dictionary) {
		ConfigEnumStrings.dictionary = dictionary;
	}
	
	
}
