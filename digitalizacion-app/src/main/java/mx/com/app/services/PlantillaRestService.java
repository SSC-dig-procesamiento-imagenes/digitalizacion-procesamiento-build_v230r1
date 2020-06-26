package mx.com.app.services;

import java.util.List;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.plantilla.PlantillaCompletaVO;
import mx.com.teclo.base.vo.plantilla.PlantillaVO;
import mx.com.teclo.restclient.PlantillaRestClient;

public class PlantillaRestService {

	private PlantillaRestClient plantillaRestClient;
	
	public PlantillaVO obtenerTodosLotes(ConfiguracionVO conf){
		plantillaRestClient = (PlantillaRestClient) BeanLocator.getService("plantillaRestClient");
		PlantillaVO lotes = plantillaRestClient.getPlantilla(conf);
		return lotes;
	}
	
	public PlantillaVO obtenerPlantilla(ConfiguracionVO conf){
		plantillaRestClient = (PlantillaRestClient) BeanLocator.getService("plantillaRestClient");
		PlantillaVO plantilla = plantillaRestClient.getPlantilla(conf);
		return plantilla;
	}
	
	public PlantillaCompletaVO obtenerPlantilla2(ConfiguracionVO conf){
		plantillaRestClient = (PlantillaRestClient) BeanLocator.getService("plantillaRestClient");
		PlantillaCompletaVO plantilla = plantillaRestClient.getPlantilla2(conf);
		return plantilla;
	}
	
}
