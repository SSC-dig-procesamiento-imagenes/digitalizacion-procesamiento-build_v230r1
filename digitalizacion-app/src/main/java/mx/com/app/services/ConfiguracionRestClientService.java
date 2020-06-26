package mx.com.app.services;

import java.util.List;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.configuracionescaner.ConfiguracionEscanerVO;
import mx.com.teclo.restclient.ConfiguracionRestClient;


public class ConfiguracionRestClientService {

	private ConfiguracionRestClient configuracionRestClient;
	
	public ConfiguracionVO obtenerConfiguracion() {
		configuracionRestClient = (ConfiguracionRestClient) BeanLocator.getService("configuracionRestClient");
		ConfiguracionVO conf = configuracionRestClient.obtenerConfiguracion();
		return conf;
	}
	
	public List<ConfiguracionEscanerVO> buscarConfigEscaners(ConfiguracionVO conf) {
		configuracionRestClient = (ConfiguracionRestClient) BeanLocator.getService("configuracionRestClient");
		List<ConfiguracionEscanerVO>  listEscaners = configuracionRestClient.buscarConfigEscaners(conf);
		return listEscaners;
	}
	
	
}
