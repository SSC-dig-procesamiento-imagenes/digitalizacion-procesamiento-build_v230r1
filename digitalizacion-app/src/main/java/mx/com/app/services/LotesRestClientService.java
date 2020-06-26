package mx.com.app.services;

import java.util.List;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.vo.bitacora.BitacoraVO;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.images.ImagenPersistenciaVO;
import mx.com.teclo.base.vo.lotes.EstadosLotesVO;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.lotes.PersistirLoteLoteVO;
import mx.com.teclo.base.vo.usuario.UsuarioVO;
import mx.com.teclo.restclient.LotesRestClient;

public class LotesRestClientService {

	private LotesRestClient lotesRestClient;
	
	public List<LoteVO> obtenerTodosLotes(ConfiguracionVO conf, Long idstat, String fechaIni, String fechaFin){
		lotesRestClient = (LotesRestClient) BeanLocator.getService("lotesRestClient");
		List<LoteVO> lotes = lotesRestClient.obtenerTodosLotes(conf, idstat, fechaIni, fechaFin);
		return lotes;
	}
	
	public List<EstadosLotesVO> obtenerCatalogoEstatus(ConfiguracionVO conf){
		lotesRestClient = (LotesRestClient) BeanLocator.getService("lotesRestClient");
		List<EstadosLotesVO> lotes = lotesRestClient.obtenerCatalogoEstatus(conf);
		return lotes;
	}
	
	public LoteVO guardarLote(PersistirLoteLoteVO lotepersis, BitacoraVO bitacoraVO, ConfiguracionVO conf){
		System.out.println("llegaste aqui a guardar lote");
		lotesRestClient = (LotesRestClient) BeanLocator.getService("lotesRestClient");
		LoteVO lote = lotesRestClient.guardarLote(lotepersis, bitacoraVO, conf);
		return lote;
	}
	
	public boolean actualizarEstTolImgLote(Long idlote,int totalimagenes,Long idstatus, ConfiguracionVO conf, BitacoraVO bitacoraVO){
		System.out.println("llegaste aqui a update lote");
		lotesRestClient = (LotesRestClient) BeanLocator.getService("lotesRestClient");
		boolean lotes = lotesRestClient.actualizarEstTolImgLote(idlote, totalimagenes, idstatus, conf, bitacoraVO);
		return lotes;
	}
	
	public boolean guardarImagen(ImagenPersistenciaVO imagenp, ConfiguracionVO conf){
		lotesRestClient = (LotesRestClient) BeanLocator.getService("lotesRestClient");
		boolean estatus = lotesRestClient.guardarImagen(imagenp, conf);
		return estatus;
	}
	
	
	
}
