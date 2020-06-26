package mx.com.app.services;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.mathworks.toolbox.javabuilder.MWException;

//import com.mathworks.toolbox.javabuilder.MWArray;
//import com.mathworks.toolbox.javabuilder.MWException;
//import com.mathworks.toolbox.javabuilder.MWException;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.images.ImagenPersistenciaVO;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.plantilla.PlantillaCompletaVO;
import mx.com.teclo.base.vo.plantilla.PlantillaRespuestaVO;
import mx.com.teclo.base.vo.plantilla.PlantillaVO;
import mx.com.teclo.omr.Omr;


public class OmrService {

	private Omr omr;
	PlantillaVO plantilla;
	PlantillaCompletaVO plantilla2;
	PlantillaRestService plantillaRestClientService;
    
    public void procesoReconocimiento(String[] images, LoteVO lote, ConfiguracionVO config) throws SerialException, IOException, SQLException{
    	plantillaRestClientService = new PlantillaRestService();
		plantilla = plantillaRestClientService.obtenerTodosLotes(config);
		
		System.out.println(plantilla.getNombre());
    	
		omr = (Omr) BeanLocator.getService("omr");
//		try {
//			omr.sistemareconocimientoOpenCv(images, lote, plantilla, config);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			omr.sistemaReconocimiento(images, lote, plantilla, config);
		} catch (MWException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
    }
    
    public Object[] procesoReconocimiento2(LoteVO lote, String imagenName, int cantidadObjetosRespuesta, double area, double densidad){
//    	plantillaRestClientService = new PlantillaRestService();
//		plantilla = plantillaRestClientService.obtenerTodosLotes(config);
		omr = (Omr) BeanLocator.getService("omr");
		Object[] obj=null;
		obj= omr.sistemaReconocimiento2(lote, imagenName, cantidadObjetosRespuesta, area, densidad);
		return obj;
    }
    
    public void loadNativeLibrary() {
		omr = (Omr) BeanLocator.getService("omr");
		omr.loadNativeLibrary();
    }
    
    public PlantillaVO getPlantilla(ConfiguracionVO config) {
    	plantillaRestClientService = new PlantillaRestService();
		plantilla = plantillaRestClientService.obtenerPlantilla(config);  	
    	return plantilla;
    }
    
    public PlantillaCompletaVO getPlantilla2(ConfiguracionVO config) {
    	plantillaRestClientService = new PlantillaRestService();
		plantilla2 = plantillaRestClientService.obtenerPlantilla2(config);  	
    	return plantilla2;
    }
    
    public String extractResults(String vector, int idorientacionrespuesta, List<PlantillaRespuestaVO> plantillarespuesta) {
    	omr = (Omr) BeanLocator.getService("omr");
    	return omr.extractResults(vector, idorientacionrespuesta, plantillarespuesta);
    }
    
    public ImagenPersistenciaVO setValueCampo(String campo, String respuesta, ImagenPersistenciaVO imagenp) {
    	omr = (Omr) BeanLocator.getService("omr");
    	return omr.setValueCampo(campo, respuesta, imagenp);
    }
    
}
