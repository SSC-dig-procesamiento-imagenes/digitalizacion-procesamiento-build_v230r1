package mx.com.app.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.plantilla.PlantillaVO;
import mx.com.teclo.base.vo.scanner.ConfigGeneralPersistenciaVO;
import mx.com.teclo.base.vo.scanner.ConfigGeneralVO;
import mx.com.teclo.base.vo.scanner.ConfigScannerVO;
import mx.com.teclo.restclient.RestClient;

public class RestclientService {
	
private RestClient restClient;


//	public List<LoteVO> getLotesAll(){
//		httpGetRequest = (HttpGetRequest) BeanLocator.getService("httpGetRequest");
//		httpGetRequest.setApiURL("http://172.18.44.165:9080/digitalizacion-desk-wsw_v100r1/lotes/obtenerTodosLotes");
//		httpGetRequest.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWNsb3NhcGkiLCJmdW5jaW9uUGFzc3dvcmQiOiI1NVRiNkgqVyIsImNyZWF0ZWQiOjE1Mzg3NjU3NTgyNTh9.lzxG0WFHKhhP_q8j5HLU_WqA7Y2OC0ijQq4CcKb0SEG0ZRZZG7FryQ43h0SMXR4m_tHnOj9LlIiWV7f7cE5QNA");
//		List<LoteVO> lotes = httpGetRequest.getLotesRequest();
//	    return lotes;
//	}
//	

	
	
	public ConfigScannerVO getConfigScanLoteById(Long idconfigscann) {
		restClient = (RestClient) BeanLocator.getService("restClient");
		ConfigScannerVO configscan = restClient.getConfigScanLoteById(idconfigscann);
		return configscan;
	}
	
//	public boolean actualizarEstTolImgLote(Long idlote,int totalimagenes,Long idstatus) {
//		restClient = (RestClient) BeanLocator.getService("restClient");
//		boolean loteUpdated = restClient.updateLote(idlote,totalimagenes,idstatus);
//		return loteUpdated;
//	}
	
//	public boolean insertLote(LotePersistenciaVO lote) {		
//		httpPostRequest = (HttpPostRequest) BeanLocator.getService("httpPostRequest");
//		httpPostRequest.setApiURL("http://localhost:8080/insertlote");
//		httpPostRequest.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWNsb3NhcGkiLCJmdW5jaW9uUGFzc3dvcmQiOiI1NVRiNkgqVyIsImNyZWF0ZWQiOjE1Mzg3NjU3NTgyNTh9.lzxG0WFHKhhP_q8j5HLU_WqA7Y2OC0ijQq4CcKb0SEG0ZRZZG7FryQ43h0SMXR4m_tHnOj9LlIiWV7f7cE5QNA");
//		boolean loteUpdated = httpPostRequest.saveLoteRequest(lote);
//		return loteUpdated;
//	}

	/*************************SERVICIOS POR REVISAR***********************************/
    public PlantillaVO getPlantilla(){
            restClient = (RestClient) BeanLocator.getService("restClient");
            PlantillaVO plantilla = restClient.getPlantilla();
            return plantilla;
    }
    
    public ConfigScannerVO getConfigScann(){
    	restClient = (RestClient) BeanLocator.getService("restClient");
    	ConfigScannerVO plantilla = restClient.getConfigScann();
        return plantilla;
    }
    
    public ConfigGeneralPersistenciaVO getConfigScannAll(){
    	restClient = (RestClient) BeanLocator.getService("restClient");
    	ConfigGeneralPersistenciaVO configgeneral = restClient.getConfigScannAll();
        return configgeneral;
    }
    
    

}
