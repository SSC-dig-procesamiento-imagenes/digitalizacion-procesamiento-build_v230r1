package mx.com.teclo.restclient;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclo.base.vo.bitacora.BitacoraVO;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.images.ImagenPersistenciaVO;
import mx.com.teclo.base.vo.lotes.ActualizarLoteVO;
import mx.com.teclo.base.vo.lotes.EstadosLotesVO;
import mx.com.teclo.base.vo.lotes.LoteBitacoraVO;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.lotes.PersistirLoteLoteVO;
import mx.com.teclo.base.vo.usuario.UsuarioVO;

@Service("lotesRestClient")
public class LotesRestClient {

	public List<LoteVO> obtenerTodosLotes2(ConfiguracionVO conf){
		String rest_url = conf.getBaseurl()+"/lotes/obtenerTodosLotes";
//		String rest_url = @Value("${service.url}")+"/lotes/obtenerTodosLotes";
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", conf.getToken());
		
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<List<LoteVO>> response = restTemplate.exchange(
				rest_url, 
				HttpMethod.GET, 
				entity, 
				new ParameterizedTypeReference<List<LoteVO>>() {});

		List<LoteVO> lotes = response.getBody();
		
		return lotes;
	}
	
	
	/**
	 * @param conf
	 * @return
	 */
	public List<LoteVO> obtenerTodosLotes(ConfiguracionVO conf, Long idstat, String fechaIni, String fechaFin){
		
		
//		parametrosc.setIdstat(1L);
//		parametrosc.setFechaIni("2019-01-18");
//		parametrosc.setFechaFin("2019-22-18");
		String rest_url = conf.getBaseurl()+"/lotes/obtenerTodosLotes";
		
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", conf.getToken());
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(rest_url)
		        .queryParam("idstat", idstat)
		        .queryParam("fechaIni", fechaIni)
		        .queryParam("fechaFin", fechaFin);
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
//		HttpEntity<?> entity = new HttpEntity<>(parametrosc, headers);
		ResponseEntity<List<LoteVO>> response = restTemplate.exchange(
				builder.toUriString(),  
				HttpMethod.GET, 
				entity, 
				new ParameterizedTypeReference<List<LoteVO>>() {});

		List<LoteVO> lotes = response.getBody();
		
		return lotes;
	}
	
	public List<EstadosLotesVO> obtenerCatalogoEstatus(ConfiguracionVO conf){
		String rest_url = conf.getBaseurl()+"/lotes/todosEstadosLotes";
		
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", conf.getToken());
		
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<List<EstadosLotesVO>> response = restTemplate.exchange(
				rest_url, 
				HttpMethod.GET, 
				entity, 
				new ParameterizedTypeReference<List<EstadosLotesVO>>() {});

		List<EstadosLotesVO> lotes = response.getBody();
		
		return lotes;
	}
	
	public LoteVO guardarLote(PersistirLoteLoteVO lotep, BitacoraVO bitacoraVO, ConfiguracionVO conf){
		String rest_url = conf.getBaseurl()+"/lotes/guardarLote";
		
		LoteBitacoraVO lotbit = new LoteBitacoraVO();
		lotbit.setFechaCracionLote(lotep.getFechaCracionLote());
		lotbit.setIdConfiguracionEscaner(lotep.getIdConfiguracionEscaner());
		lotbit.setIdEtatusProceso(lotep.getIdEtatusProceso());
		lotbit.setIdLote(lotep.getIdLote());
		lotbit.setMetDigitalizacion(lotep.getMetDigitalizacion());
		lotbit.setNombreLote(lotep.getNombreLote());
		lotbit.setNumeroTotalAceptadas(lotep.getNumeroTotalAceptadas());
		lotbit.setNumeroTotalImagenes(lotep.getNumeroTotalImagenes());
		lotbit.setNumeroTotalRechazadas(lotep.getNumeroTotalRechazadas());
		lotbit.setRutaAlmacenamiento(lotep.getRutaAlmacenamiento());	
		/**************************/
		lotbit.setTabla(bitacoraVO.getTabla());
		lotbit.setComponenteId(bitacoraVO.getComponenteId());
		lotbit.setConceptoId(bitacoraVO.getConceptoId());
		lotbit.setValorOriginal(bitacoraVO.getValorOriginal());
		lotbit.setValorFinal(bitacoraVO.getValorFinal());
		Long valorId = bitacoraVO.getModificadoPor()==null ? (long) 2 :  bitacoraVO.getModificadoPor();

		lotbit.setModificadoPor(valorId);
		lotbit.setRegistroDescripcion(bitacoraVO.getRegistroDescripcion());
		lotbit.setOrigen(bitacoraVO.getOrigen());
		lotbit.setNuFolioInicial(lotep.getNuFolioInicial());
		lotbit.setNuFolioFinal(lotep.getNuFolioFinal());
				
		
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");	
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", conf.getToken());
		
		 HttpEntity<?> entity = new HttpEntity<>(lotbit, headers);
		 
		 ResponseEntity<LoteVO> response = new RestTemplate().postForEntity(rest_url, entity, LoteVO.class);
		 
		 LoteVO lote_final = (LoteVO) response.getBody();
	        System.out.println(lote_final.getNombreLote());
		return lote_final;
	}
	
//	public boolean guardarImagen(ImagenPersistenciaVO imagenp, ConfiguracionVO conf){
//		String rest_url = conf.getBaseurl()+"/imagenes/informacionImagen";
//		
//		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");	
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//		headers.add("Authorization", conf.getToken());
//		
//		 HttpEntity<?> entity = new HttpEntity<>(imagenp, headers);
//		 
//		 ResponseEntity<ImagenPersistenciaVO> response = new RestTemplate().postForEntity(rest_url, entity, ImagenPersistenciaVO.class);
//		 
//		 ImagenPersistenciaVO result = response.getBody();
//		return false;
//	}
	
public Boolean guardarImagen(ImagenPersistenciaVO imagenp, ConfiguracionVO conf){
    	
		String rest_url = conf.getBaseurl()+"/imagenes/informacionImagen";
		Boolean status=false;
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");	
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", conf.getToken());
		
		 HttpEntity<?> entity = new HttpEntity<>(imagenp, headers);
		 
		 System.out.println(imagenp.getIdlote()+", "+imagenp.getNombreImagen()+", "+imagenp.getNumeroFolio()+
				 ", "+imagenp.getLbImagenes().get(0).getNombreImagen()+", "+imagenp.getLbImagenes().get(0).getLbImagen());
		 
//		 Object response = new RestTemplate().postForEntity(rest_url, entity, Boolean.class);
		 ResponseEntity<Boolean> response=null;
		 try {
			 response = new RestTemplate().postForEntity(rest_url, entity, Boolean.class);
			 System.out.println("Respuesta de inserción: "+response.toString());
			 status = (Boolean ) response.getBody();
			 System.out.println("Respuesta de inserción2: "+response.getBody());
		 }catch(Exception e) {
			 System.out.println(e);
		 }	
		return status;
	}

public Boolean actualizarEstTolImgLote(Long idlote,int totalimagenes,Long idstatus, ConfiguracionVO conf, BitacoraVO bitacoraVO){
	
	String rest_url = conf.getBaseurl()+"/lotes/actualizarEstTolImgLote";
	Boolean status=false;
	
	ActualizarLoteVO lote = new ActualizarLoteVO();
	lote.setIdEtatusProceso(idstatus);
	lote.setIdLote(idlote);
	lote.setNumeroTotalImagenes(totalimagenes);
	lote.setIdRegistro(idlote);
	lote.setTabla(bitacoraVO.getTabla());
	lote.setComponenteId(bitacoraVO.getComponenteId());
	lote.setConceptoId(bitacoraVO.getConceptoId());
	lote.setValorOriginal(bitacoraVO.getValorOriginal());
	lote.setValorFinal(bitacoraVO.getValorFinal());
	/*Modificar los parámetros del lote siempre, incluyendo los folios y el nombre*/
	lote.setNombreLote(bitacoraVO.getNombreLote());
	lote.setNuFolioInicial(bitacoraVO.getNuFolioInicial());
	lote.setNuFolioFinal(bitacoraVO.getNuFolioFinal());
	
	Long valorId = bitacoraVO.getModificadoPor()== null ? 2 : bitacoraVO.getModificadoPor();
	
	lote.setModificadoPor(valorId);
	lote.setIdUsuario(bitacoraVO.getIdUsuario());
	lote.setRegistroDescripcion(bitacoraVO.getRegistroDescripcion());
	lote.setOrigen(bitacoraVO.getOrigen());
	
	
	RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");	
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	headers.add("Authorization", conf.getToken());
	
	 HttpEntity<?> entity = new HttpEntity<>(lote, headers);
	 
//	 System.out.println(imagenp.getIdlote()+", "+imagenp.getNombreImagen()+", "+imagenp.getNumeroFolio()+
//			 ", "+imagenp.getLbImagenes().get(0).getNombreImagen()+", "+imagenp.getLbImagenes().get(0).getLbImagen());
//	 
//	 Object response = new RestTemplate().postForEntity(rest_url, entity, Boolean.class);
	 ResponseEntity<Boolean> response=null;
	 try {
		 response = new RestTemplate().postForEntity(rest_url, entity, Boolean.class);
		 System.out.println("Respuesta de inserción: "+response.toString());
		 status = (Boolean ) response.getBody();
		 System.out.println("Respuesta de inserción2: "+response.getBody());
	 }catch(Exception e) {
		 System.out.println(e);
	 }	
	return status;
}
	
	public boolean actualizarEstTolImgLote2(Long idlote,int totalimagenes,Long idstatus, ConfiguracionVO conf, BitacoraVO bitacoraVO) {
		boolean sent=false;
		int statusCode=0;
		
		ActualizarLoteVO lote = new ActualizarLoteVO();
		lote.setIdEtatusProceso(idstatus);
		lote.setIdLote(idlote);
		lote.setNumeroTotalImagenes(totalimagenes);
		
		lote.setTabla(bitacoraVO.getTabla());
		lote.setComponenteId(bitacoraVO.getComponenteId());
		lote.setConceptoId(bitacoraVO.getConceptoId());
		lote.setValorOriginal(bitacoraVO.getValorOriginal());
		lote.setValorFinal(bitacoraVO.getValorFinal());
		Long valorId = bitacoraVO.getModificadoPor()== null ? 2 : bitacoraVO.getModificadoPor();
		
		lote.setModificadoPor(valorId);
		lote.setRegistroDescripcion(bitacoraVO.getRegistroDescripcion());
		lote.setOrigen(bitacoraVO.getOrigen());
		
		lote.setNuFolioInicial(bitacoraVO.getNuFolioInicial());
		lote.setNuFolioFinal(bitacoraVO.getNuFolioFinal());
				
		
		System.out.println(conf.getBaseurl()+"/lotes/actualizarEstTolImgLote");
		
		try {
			HttpClient c = new DefaultHttpClient();        
		    HttpPost p = new HttpPost(conf.getBaseurl()+"/lotes/actualizarEstTolImgLote");
		    System.out.println(conf.getBaseurl()+"/lotes/actualizarEstTolImgLote");
		    /*
		    HttpParams params = p.getParams();
	        HttpConnectionParams.setConnectionTimeout(params, 5000);
	        HttpConnectionParams.setSoTimeout(params, 5000); 
		     */
		    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		    String jsonEnviar = gson.toJson(lote);
		    System.out.println(jsonEnviar);
		    p.setEntity(new StringEntity(jsonEnviar, ContentType.create("application/json")));
			p.setHeader("Authorization",conf.getToken());
			
			HttpResponse r = c.execute(p);
			statusCode=r.getStatusLine().getStatusCode();
			System.out.println("coigo status: "+statusCode);
			JOptionPane.showMessageDialog(null, statusCode);
			if(statusCode==201){
				sent=true;
	        }else{
	        	sent=false;
	        }
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sent;
	}
}
