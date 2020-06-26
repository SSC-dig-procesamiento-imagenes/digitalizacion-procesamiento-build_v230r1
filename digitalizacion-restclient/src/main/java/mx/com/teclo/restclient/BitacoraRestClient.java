package mx.com.teclo.restclient;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.vo.bitacora.BitacoraVO;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;

@Service("bitacoraRestClient")
public class BitacoraRestClient {

	public boolean guardarBitacora(BitacoraVO bitacora, ConfiguracionVO conf){
		String rest_url = conf.getBaseurl()+"/bitacora/guardarbitacora";
		
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");	
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", conf.getToken());
		
		 HttpEntity<?> entity = new HttpEntity<>(bitacora, headers);
		 
		 ResponseEntity<Boolean> response = new RestTemplate().postForEntity(rest_url, entity, Boolean.class);
		 
		return false;
	}
}
