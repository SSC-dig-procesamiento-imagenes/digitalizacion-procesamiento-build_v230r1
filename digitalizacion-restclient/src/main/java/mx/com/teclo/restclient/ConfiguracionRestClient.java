package mx.com.teclo.restclient;

import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.configuracionescaner.ConfiguracionEscanerVO;
import mx.com.teclo.base.vo.lotes.LoteVO;

@Service("configuracionRestClient")
public class ConfiguracionRestClient {
	
	public ConfiguracionVO obtenerConfiguracion() {
		ConfiguracionVO conf = new ConfiguracionVO();
		return conf;
	}
	
	public List<ConfiguracionEscanerVO> buscarConfigEscaners(ConfiguracionVO conf){
		String rest_url = conf.getBaseurl()+"/configuracionescaner/buscarConfigEscaners";
		
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", conf.getToken());
		
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<List<ConfiguracionEscanerVO>> response = restTemplate.exchange(
				rest_url, 
				HttpMethod.GET, 
				entity, 
				new ParameterizedTypeReference<List<ConfiguracionEscanerVO>>() {});

		List<ConfiguracionEscanerVO> listEscaners = response.getBody();
		
		return listEscaners;
	}

}
