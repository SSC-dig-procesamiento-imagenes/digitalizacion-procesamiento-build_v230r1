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
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.plantilla.PlantillaCompletaVO;
import mx.com.teclo.base.vo.plantilla.PlantillaVO;

@Service("plantillaRestClient")
public class PlantillaRestClient {

	public PlantillaVO getPlantilla(ConfiguracionVO conf){
		
		String rest_url = conf.getBaseurl()+"/paltillas/PlatillaPorId?plantillaI=1";
//		String rest_url =  "http://172.18.44.99:8080/parametrosplantilla?idplantilla=1";
		
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
		
		/*****************************/
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", conf.getToken());
    		
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<PlantillaVO> response = restTemplate.exchange(
			rest_url, 
			HttpMethod.GET, 
			entity, 
			new ParameterizedTypeReference<PlantillaVO>() {});

    	PlantillaVO plantilla = response.getBody();
    	/*****************************/
    	return plantilla;
    		
	}
	
public PlantillaCompletaVO getPlantilla2(ConfiguracionVO conf){
		
		String rest_url = conf.getBaseurl()+"/paltillas/PlatillaPorId?plantillaI=1";		
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
		
		/*****************************/
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", conf.getToken());
    		
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<PlantillaCompletaVO> response = restTemplate.exchange(
			rest_url, 
			HttpMethod.GET, 
			entity, 
			new ParameterizedTypeReference<PlantillaCompletaVO>() {});

		PlantillaCompletaVO plantilla = response.getBody();
    	/*****************************/
    	return plantilla;
    		
	}
	
}
