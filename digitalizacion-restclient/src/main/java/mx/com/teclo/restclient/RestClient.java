package mx.com.teclo.restclient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.plantilla.PlantillaVO;
import mx.com.teclo.base.vo.scanner.ConfigGeneralPersistenciaVO;
import mx.com.teclo.base.vo.scanner.ConfigGeneralVO;
import mx.com.teclo.base.vo.scanner.ConfigScannerVO;
import mx.com.teclo.utils.Methods;


@Service("restClient")
public class RestClient {
	private String base="http://172.18.44.165:9080/digitalizacion-desk-wsw_v100r1/";
	
	private String getAllLotes="lotes/obtenerTodosLotes";
	
	private String REST_API =  "https://jsonplaceholder.typicode.com/"; //http://172.18.44.153:8080/sspcdmxsai_api_v600r3/
	private String REST_API2 =  "http://localhost:8080/parametrosplantilla?idplantilla=1"; //http://172.18.44.153:8080/sspcdmxsai_api_v600r3/
	private String REST_API3 =  "http://localhost:8080/parametrosconfigscanner?idconfigscanner=1";
	private String REST_API4 =  "http://localhost:8080/parametrosconfigscannerAll";
	private String REST_API5 =  "http://localhost:8080/getlotesall";
	private String REST_API6 =  "http://localhost:8080/configscannerbyid?idconfigscanner=";
	private String REST_API7 =  "http://localhost:8080/updatelote";
	
	
	
	
	
	
	
	/****************************REVISAR A PARTIR DE AQUI***************************************************/
	
	
	
	
	
	
	
	public List<LoteVO> getLotesAll(){
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
		String rest_url = getAllLotes;
		Methods met = new Methods();
		final String json = met.getJsonString(rest_url);
		//Implementacion tomada de: https://www.adictosaltrabajo.com/tutoriales/gson-java-json/#032
//		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
//         dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));         
//		Gson gson = new GsonBuilder().setDateFormat(dateFormat).create();
//		final String FORMATO_FECHA = "dd/MM/yyyy";
//		final DateFormat DF = new SimpleDateFormat(FORMATO_FECHA);			
//		gsonBuilder.setDateFormat("M/d/yy hh:mm a");
		
		final Gson gson = new Gson();
		final Type tipoListaLote = new TypeToken<List<LoteVO>>(){}.getType();
		final List<LoteVO> lotes = gson.fromJson(json, tipoListaLote);
		
		return lotes;
	}
	
	public ConfigScannerVO getConfigScanLoteById(Long idconfigscann){
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
		String rest_url = REST_API6+idconfigscann;
		ConfigScannerVO configscan = (ConfigScannerVO) restTemplate.getForObject(rest_url, ConfigScannerVO.class);
		return configscan;
	}
	
//	public boolean actualizarEstTolImgLote(Long idlote,int totalimagenes,Long idstatus){
//		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
//		String rest_url = REST_API7+"?idlote="+idlote+"&totalimagenes="+totalimagenes+"&idstatus="+idstatus;
//		boolean loteUpdated = (boolean) restTemplate.getForObject(rest_url, boolean.class);
//		System.out.println("este resultado booleano: "+loteUpdated);
//		return loteUpdated;
//	}	
	
	/************************SERVICIOS POR REVISAR****************************************/
	
	
	public PlantillaVO getPlantilla(){
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
		String rest_url = REST_API2;
		PlantillaVO plantilla = (PlantillaVO) restTemplate.getForObject(rest_url, PlantillaVO.class);
            return plantilla;
	}
	
	public ConfigScannerVO getConfigScann(){
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
		String rest_url = REST_API3;
		ConfigScannerVO configscann = (ConfigScannerVO) restTemplate.getForObject(rest_url, ConfigScannerVO.class);
		return configscann;
	}
	
	public ConfigGeneralPersistenciaVO getConfigScannAll(){
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
		String rest_url = REST_API4;
		ConfigGeneralPersistenciaVO configgeneral = (ConfigGeneralPersistenciaVO) restTemplate.getForObject(rest_url, ConfigGeneralPersistenciaVO.class);
		return configgeneral;
	}
	
	
	
	
}