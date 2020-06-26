package mx.com.teclo.omr;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

//import org.opencv.core.Core;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWException;

import Tecloprocessing19.omrteclo;
//import Tecloprocessing14.omrteclo;
//import TecloProcessing13.omrteclo;
//import Tecloprocessing14.omrteclo;
import mx.com.teclo.base.context.BeanLocator;

//import TecloProcessing13.omrteclo;

//import com.mathworks.toolbox.javabuilder.MWArray;
//import com.mathworks.toolbox.javabuilder.MWException;
//
//import TecloProcessing13.omrteclo;

//import Tecloprocessing14.omrteclo;

//import com.mathworks.toolbox.javabuilder.MWArray;
//import com.mathworks.toolbox.javabuilder.MWException;

//import TecloProcessing13.omrteclo;
import mx.com.teclo.base.util.enumerados.ConfiguracionEnum;
import mx.com.teclo.base.util.methods.Methods;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.images.ImagenBlobPersistenciaVO;
import mx.com.teclo.base.vo.images.ImagenPersistenciaVO;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.plantilla.PlantillaRespuestaVO;
import mx.com.teclo.base.vo.plantilla.PlantillaVO;


@Service("omr")
public class Omr {
	
	Methods met = new Methods();
	omrteclo obj2=null;
	Object[] response2 = null;
	
	public void consultaTest(){
		System.out.println("OMR JAR Presente");
	}
	
	public void loadNativeLibrary() {
		System.setProperty("java.library.path", "C:\\Program Files (x86)\\MATLAB\\MATLAB Runtime\\v90\\runtime\\win32\\");
	       File fDir = new File("C:\\Program Files (x86)\\MATLAB\\MATLAB Runtime\\v90\\runtime\\win32\\mclmcrrt9_0.dll");
			File fFiscn=null;
			if (fDir.isFile())
	        {
				System.out.println("es archivo");
	          fFiscn = new File(fDir.getParent() + "\\mclmcrrt9_0.dll");
	          try
	          {
	            System.load(fFiscn.getCanonicalPath());
	            System.out.println("se cargo el archivo");
	          }
	          catch (IOException e)
	          {
	            System.out.println("Ocurrio una excepcion al cargar un archivo");
	          }
	        }
	}
	
	public Object[] sistemaReconocimiento2(LoteVO lote, String imagenName, int cantidadObjetosRespuesta, double area, double densidad) {
		try {
			obj2 = new omrteclo();
			response2 = obj2.tecloprocessing(cantidadObjetosRespuesta, lote.getRutaAlmacenamiento()+imagenName, area,  densidad);
			
		} catch (MWException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//Limpiamos memoria
	        MWArray.disposeArray(obj2);
	        obj2.dispose();
	        obj2.disposeAllInstances();
	        obj2=null;
		}
			
		return response2;
	}
	

	public void sistemaReconocimiento(String[] images, LoteVO lote, PlantillaVO plantilla, ConfiguracionVO config) throws MWException, SerialException, IOException, SQLException {
//    	
		System.setProperty("java.library.path", "C:\\Program Files (x86)\\MATLAB\\MATLAB Runtime\\v90\\runtime\\win32\\");
	       File fDir = new File("C:\\Program Files (x86)\\MATLAB\\MATLAB Runtime\\v90\\runtime\\win32\\mclmcrrt9_0.dll");
			File fFiscn=null;
			if (fDir.isFile())
	        {
				System.out.println("es archivo");
	          fFiscn = new File(fDir.getParent() + "\\mclmcrrt9_0.dll");
	          try
	          {
	            System.load(fFiscn.getCanonicalPath());
	            System.out.println("se cargo el archivo");
	          }
	          catch (IOException e)
	          {
	            System.out.println("Ocurrio una excepcion al cargar un archivo");
	          }
	        }
			
		ProcesamientoFrame frame = new ProcesamientoFrame(lote, config);
		int tamimages=images.length;
		frame.setTotal(tamimages+"");
    	frame.setVisible(true);
    	
    	/*******************/
		System.out.println(plantilla.getNombre());
		
		long lStartTime2 = System.nanoTime();
		
		
		int eacheven=1;
		if(lote.getConfiguracionEscaner().getSuministroPapel().getValor()==ConfiguracionEnum.ladoambos.getConstante()) {
			eacheven=2;
    	}
		
		omrteclo obj=null;
//        	Recorremos cada imagen para reconocer los datos
    	
    	ImagenPersistenciaVO imagenp;
    	List<ImagenBlobPersistenciaVO> imagenesBl= new ArrayList();
    	ImagenBlobPersistenciaVO imagenBl= null;
    	/*********************FOR******************************/
            for (int i = 0; i < images.length; i++) {
            	
            	imagenp = new ImagenPersistenciaVO();
            	imagenBl= new ImagenBlobPersistenciaVO();
//            	 System.out.println("Imagen "+i+": "+images[i]);
            	 frame.setTextToLogResultados("/**************"+images[i]+"*********************");
                obj = new omrteclo();      
                
                Object[] response = null;
                
                if(lote.getConfiguracionEscaner().getSuministroPapel().getValor()==ConfiguracionEnum.ladoambos.getConstante()) {
                	if ( (i & 1) == 0 ) {
                		//Asignamos datos para el blob (Imagen Blob) -- Objeto para Persistencia de Blob
                        imagenBl.setNombreImagen(images[i]);
//                        SerialBlob imagenA=met.convertFileToBlob(lote.getRutaAlmacenamiento()+images[i]);   
                        byte[] imagenA = met.convertFileContentToBlob(lote.getRutaAlmacenamiento()+images[i]); 
                        String base64String = Base64.encodeBase64String(imagenA);
                        imagenBl.setLbImagen(imagenA);
                        imagenBl.setCodigoImagen("A");  
                        imagenesBl.add(imagenBl);
                		System.out.println("Es una imagen anversa, no se procesa solo se persiste");
                	}else {
                		/*************************TRY*********************************************************/
                        try {
                        	int cantidadObjetosRespuesta=plantilla.getCampos().size();
               	         	double area= plantilla.getAlveolo().getArea();
                        	double densidad= plantilla.getAlveolo().getDensidad();
                        	
                        	
                            response = obj.tecloprocessing(cantidadObjetosRespuesta, lote.getRutaAlmacenamiento()+images[i], area,  densidad);
                            
                        	
//                            Recorremos los campos
                            String impresion="";
                            String dia="", mes="", anio="",folio="";
                            for(int j=0;j<response.length;j++){
                                String matriz=(response[j].toString().replace("[", "")).replace("]", "");
                                String respuesta = extractResults(matriz, plantilla.getCampos().get(j).getOrientaciones_id().intValue(),
                                		plantilla.getCampos().get(j).getPlantillarespuesta());
                                                
                                frame.setTextToLogResultados(plantilla.getCampos().get(j).getNombre()+": "+respuesta);
                                System.out.println(""+plantilla.getCampos().get(j).getNombre()+": "+respuesta); 
                                
                                /***************************Persistencia de campos********************************/
                                imagenp = setValueCampo(plantilla.getCampos().get(j).getNombre(), respuesta, imagenp);
                                
                                if(plantilla.getCampos().get(j).getNombre().equals("DIA")) {
                                	dia=respuesta;
                                }
                                if(plantilla.getCampos().get(j).getNombre().equals("MES")) {
                                	mes=respuesta;
                                }
                                if(plantilla.getCampos().get(j).getNombre().equals("AÑO")) {
                                	anio=respuesta;
                                } 
                                if(plantilla.getCampos().get(j).getNombre().equals("NUMERO DE FOLIO")) {
                                	folio=respuesta;
                                }
                                System.out.println("esta imagen: "+imagenp.toString());
                   /***************************End Persistencia********************************/   
                             } 
                            
                            /***************************Persistencia de campos********************************/         
                            //Asignamos datos para el blob (Imagen Blob) -- Objeto para Persistencia de Blob
                              imagenBl.setNombreImagen(images[i]);
                              System.out.println(images[i]);
//                              SerialBlob imagenA=met.convertFileToBlob(lote.getRutaAlmacenamiento()+images[i]); 
                              byte[] imagenA = met.convertFileContentToBlob(lote.getRutaAlmacenamiento()+images[i]); 
                              String base64String = Base64.encodeBase64String(imagenA);
                              imagenBl.setLbImagen(imagenA);
                              imagenBl.setCodigoImagen("R");  
                              imagenesBl.add(imagenBl);
                              
                           //  Persistencia de Imagen 
                              imagenp.setFechaInfraccion(met.convertStringToDate(dia+"-"+mes+"-"+anio, config.getFormatDateDecode()));
                              imagenp.setIdlote(lote.getIdLote());
                              imagenp.setNombreImagen(images[i]);      
                              imagenp.setLbImagenes(imagenesBl);
//                              guardarImagen(imagenp, config);
                              imagenesBl.clear();
                              /***************************End Persistencia********************************/  
                              
                              
                            frame.setTextToLogResultados("/***********************************");
                            frame.setTotalAvance((i+1)+"");
                            
                            float result=((i+1)*100)/tamimages;
                            
                            frame.updateGraphics((int) result);
                        	
//                            System.out.println("\n");
                         } catch (MWException e) {
                             System.out.println("Exception: " + e.toString());
                         } finally {
                             MWArray.disposeArray(response);
                             obj.dispose();
                             obj.disposeAllInstances();
                         }
                        
                        /*************************END TRY*********************************************************/
                		
                	}
                	
            	}else {//Si solo es de una cara se procesan todos
            		
                /*************************TRY*********************************************************/
                try {
                	int cantidadObjetosRespuesta=plantilla.getCampos().size();
       	         	double area= plantilla.getAlveolo().getArea();
                	double densidad= plantilla.getAlveolo().getDensidad();
                	
                	
                    response = obj.tecloprocessing(cantidadObjetosRespuesta, lote.getRutaAlmacenamiento()+images[i], area,  densidad);
                    
                	
//                    Recorremos los campos
                    String impresion="";
                    String dia="", mes="", anio="",folio="";
                    for(int j=0;j<response.length;j++){
                        String matriz=(response[j].toString().replace("[", "")).replace("]", "");
                        String respuesta = extractResults(matriz, plantilla.getCampos().get(j).getOrientaciones_id().intValue(),
                        		plantilla.getCampos().get(j).getPlantillarespuesta());
                                        
                        frame.setTextToLogResultados(plantilla.getCampos().get(j).getNombre()+": "+respuesta);
                        System.out.println(""+plantilla.getCampos().get(j).getNombre()+": "+respuesta); 
                        
                        
           /***************************Persistencia de campos********************************/
                        imagenp = setValueCampo(plantilla.getCampos().get(j).getNombre(), respuesta, imagenp);
                        
                        if(plantilla.getCampos().get(j).getNombre().equals("DIA")) {
                        	dia=respuesta;
                        }
                        if(plantilla.getCampos().get(j).getNombre().equals("MES")) {
                        	mes=respuesta;
                        }
                        if(plantilla.getCampos().get(j).getNombre().equals("AÑO")) {
                        	anio=respuesta;
                        } 
                        if(plantilla.getCampos().get(j).getNombre().equals("NUMERO DE FOLIO")) {
                        	folio=respuesta;
                        }
           /***************************End Persistencia********************************/         
                        
                     } 
                    /***************************Persistencia de campos********************************/         
                  //Asignamos datos para el blob (Imagen Blob) -- Objeto para Persistencia de Blob
                    imagenBl.setNombreImagen(images[i]);
//                    SerialBlob imagenA=met.con .convertFileToBlob(lote.getRutaAlmacenamiento()+images[i]);   
                    byte[] imagenA = met.convertFileContentToBlob(lote.getRutaAlmacenamiento()+images[i]);   
//                    String base64String = Base64.encodeBase64String(imagenA);
                    imagenBl.setLbImagen(imagenA);
                    imagenBl.setCodigoImagen("A");  
                    imagenesBl.add(imagenBl);
                    
                 //  Persistencia de Imagen 
                    imagenp.setFechaInfraccion(met.convertStringToDate(dia+"-"+mes+"-"+anio, config.getFormatDateDecode()));
                    imagenp.setIdlote(lote.getIdLote());
                    imagenp.setNombreImagen(images[i]);      
                    imagenp.setLbImagenes(imagenesBl);
//                    guardarImagen(imagenp, config);
                    /***************************End Persistencia********************************/         
                   
                    
                    frame.setTextToLogResultados("/***********************************");
                    frame.setTotalAvance((i+1)+"");
                    
                    float result=((i+1)*100)/tamimages;
                    
                    frame.updateGraphics((int) result);
                	
//                    System.out.println("\n");
                 } catch (MWException e) {
                     System.out.println("Exception: " + e.toString());
                 } finally {
                     MWArray.disposeArray(response);
                     obj.dispose();
                     obj.disposeAllInstances();
                 }
                
                /*************************END TRY*********************************************************/
            	}
                obj.dispose();
                obj.disposeAllInstances();
                
            }	
            
            /*********************END FOR******************************/
            
            long lEndTime2 = System.nanoTime(); 
        	long output2 = lEndTime2 - lStartTime2; 
        	System.out.println("Elapsed time in seconds2: " + ((output2 / 1000000)/1000));
        	frame.dispose();
        	/******************/
        	
	}
    
    public String extractResults(String vector, int idorientacionrespuesta, List<PlantillaRespuestaVO> plantillarespuesta){
    	String cadenaRespuesta="";
    	List<String> caracteres = new ArrayList<String>();  	
    	
    	for(int i=0; i<plantillarespuesta.size();i++) {
    		for(int j=0;j<plantillarespuesta.get(i).getCaracteres().size();j++) {
    			caracteres.add(plantillarespuesta.get(i).getCaracteres().get(j).getCaracter());
    		}
    	}
    	String[] caracteres_arr = new String[caracteres.size()];
    	caracteres.toArray( caracteres_arr );

    	if(idorientacionrespuesta==ConfiguracionEnum.lecturaHorizontalArribaAbajo.getConstante()){//Cambiar por un enumerado
            String[] rows = vector.split(";");
            String[] columns_temp = rows[0].split(" ");
            String[] respuestas= new String[columns_temp.length];

            for(int i=0;i<rows.length;i++){                
                String[] columns = rows[i].split(" ");
                for(int j=0;j<columns.length;j++){
                    if(caracteres_arr[0].equals("RECONOCIMIENTO BINARIO")){
                        respuestas[j]=columns[j];
                    }else if(columns[j].equals("1")){ 
                            respuestas[j]=caracteres_arr[i];                                      
                        
                    }                    
                }
            }
            
            
            for(int i=0;i<respuestas.length;i++){ 
                if(respuestas[i]!=null){
                    cadenaRespuesta=cadenaRespuesta+respuestas[i];
                }                
            }
            /*****************************/
            if(caracteres_arr[0].equals("RECONOCIMIENTO BINARIO")){
                int cadenaRespuesta_int=Integer.parseInt(cadenaRespuesta,2);
                cadenaRespuesta=String.valueOf(cadenaRespuesta_int);
//                System.out.println("Resultado: "+cadenaRespuesta_int);
            }else{
//                System.out.println("Resultado: "+cadenaRespuesta);
            }
            /*************************/     
            
        }else {
        	System.out.println("No hay soporte para una lectura diferente a la horizontal");
        }	
    	
    	return cadenaRespuesta;
    }
	
//	public void sistemareconocimientoOpenCv(String[] images, LoteVO lote, PlantillaVO plantilla, ConfiguracionVO config) throws IOException {
//		
//		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//	        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
//	        System.out.println("mat = " + mat.dump());
//	        JOptionPane.showMessageDialog(null, "este resultado "+"mat = " + mat.dump());
//	        
//	        
//	        ProcesamientoFrame frame = new ProcesamientoFrame(lote, config);
//			int tamimages=images.length;
//			frame.setTotal(tamimages+"");
//	    	frame.setVisible(true);
//	    	System.out.println(plantilla.getNombre());
//
//			long lStartTime2 = System.nanoTime();
//			
//
////	    	
////	    	/*********************FOR******************************/
//			ImagenPersistenciaVO imagenp;
//			 Imgcodecs imageCodecs =null;
//			 
//			 /*******reconocimiento******/
//         	imageCodecs = new Imgcodecs(); 
//          	String file ="C:\\SistemaDigitalizacion\\01-10-2018\\jaja3\\SSP0006.jpg"; 
//          // Creating the destination matrix
//          	Mat matrix = imageCodecs.imread(file); 
//          	Mat dst = new Mat();
//          // Converting to binary image...
//          	Imgproc.threshold(matrix, dst, 200, 500, Imgproc.THRESH_BINARY);
//          	
//          // Extracting data from the transformed image (dst)
//             byte[] data1 = new byte[dst.rows() * dst.cols() * (int)(dst.elemSize())];
//             dst.get(0, 0, data1);
//             
//          // Creating Buffered image using the data
//             BufferedImage bufImage = new BufferedImage(dst.cols(),dst.rows(), 
//                BufferedImage.TYPE_BYTE_GRAY);
//             
//          // Setting the data elements to the image
//             bufImage.getRaster().setDataElements(0, 0, dst.cols(), dst.rows(), data1);
//             File outputfile = new File("binar_y");
//             ImageIO.write(bufImage, "jpg", outputfile);
//             
//             System.out.println("se escribe en: "+lote.getRutaAlmacenamiento()+"binar_y");
//          	/*******fin de reconocimiento******/	
//             
//             
////	        for (int i = 0; i < images.length; i++) {
////	        	imagenp = new ImagenPersistenciaVO();          	
////            	 System.out.println("Imagen "+i+": "+images[i]);
////            	 frame.setTextToLogResultados("/**************"+images[i]+"*********************");
////            	 if(lote.getConfiguracionEscaner().getSuministroPapel().getValor()==ConfiguracionEnum.ladoambos.getConstante()) {
////                 	if ( (i & 1) == 0 ) {
////                 		System.out.println("Es una imagen anversa, no se procesa solo se persiste");
////                 	}else {
////            		 
////                 		int cantidadObjetosRespuesta=plantilla.getCampos().size();
////       	         	double area= plantilla.getAlveolo().getArea();
////                	double densidad= plantilla.getAlveolo().getDensidad();
////                	/*******reconocimiento******/
////                	imageCodecs = new Imgcodecs(); 
////                 	String file =lote.getRutaAlmacenamiento()+images[i]; 
////                 // Creating the destination matrix
////                 	Mat matrix = imageCodecs.imread(file); 
////                 	Mat dst = new Mat();
////                 // Converting to binary image...
////                 	Imgproc.threshold(matrix, dst, 200, 500, Imgproc.THRESH_BINARY);
////                 	
////                 // Extracting data from the transformed image (dst)
////                    byte[] data1 = new byte[dst.rows() * dst.cols() * (int)(dst.elemSize())];
////                    dst.get(0, 0, data1);
////                    
////                 // Creating Buffered image using the data
////                    BufferedImage bufImage = new BufferedImage(dst.cols(),dst.rows(), 
////                       BufferedImage.TYPE_BYTE_GRAY);
////                    
////                 // Setting the data elements to the image
////                    bufImage.getRaster().setDataElements(0, 0, dst.cols(), dst.rows(), data1);
////                    File outputfile = new File("binar_"+images[i]);
////                    ImageIO.write(bufImage, "jpg", outputfile);
////                    
////                    System.out.println("se escribe en: "+lote.getRutaAlmacenamiento()+"binar_"+images[i]);
////                 	/*******fin de reconocimiento******/	
////                 	} 
////            	 
////            	 
////            	 }
////	        }
//	        
//	        
//	       
//	        
//	}
    
    public ImagenPersistenciaVO setValueCampo(String campo, String respuesta, ImagenPersistenciaVO imagenp) {
    	String nombre;
    	Long value=null;
        switch (campo) {
            case "PLACA":
            	if(respuesta.equals("")) {
            		imagenp.setPlaca(null);
            	}else {
            		imagenp.setPlaca(respuesta);
            	}          	
                break;
            case "NUMERO DE LICENCIA":
            	if(respuesta.equals("")) {
            		imagenp.setNumeroLicencia(null);
            	}else {
            		imagenp.setNumeroLicencia(respuesta);
            	}
            	
                     break;
            case "TIPO": 
            	if(respuesta.equals("")) {
            		imagenp.setTipoLicencia(null);
            	}else {
            		imagenp.setTipoLicencia(respuesta);
            	}
            	
                     break;
            case "LICENCIA EXPEDIDA EN":
            	if(respuesta.equals("")) {
            		imagenp.setLicenciaExpedidaEn(null);
            	}else {
            		imagenp.setLicenciaExpedidaEn(respuesta);
            	}
            	
                     break;
            case "PLACA EXPEDIDA EN":
            	if(respuesta.equals("")) {
            		imagenp.setPlacaExpedidaEn(null);
            	}else {
            		imagenp.setPlacaExpedidaEn(respuesta);
            	}
            	
                     break;
            case "ARTICULO DE LA INFRACCION":
            	if(respuesta.equals("")||!met.isNumeric(respuesta)) {
            	}else {
            		value=Long.parseLong(respuesta);
            	}
            	imagenp.setNumeroArticuloInfraccion(value);
                     break;
            case "FRACCION":
            	if(respuesta.equals("")||!met.isNumeric(respuesta)) {
            	}else {
            		value=Long.parseLong(respuesta);
            	}
            	imagenp.setNumeroFraccion(value);
                     break;
            case "INCISO":
            	imagenp.setNumeroInciso(respuesta);
                     break;
            case "PARRAFO":
            	if(respuesta.equals("")||!met.isNumeric(respuesta)) {
//            		System.out.println("parrafo: "+respuesta);
            	}else {
            		value=Long.parseLong(respuesta);
            	}
            	imagenp.setNumeroParrafo(value);
                     break;
            case "PLACA DEL OFICIAL":
            	if(respuesta.equals("")) {
            		imagenp.setPlacaOficial(null);
            	}else {
            		imagenp.setPlacaOficial(respuesta);
            	}
            	
            		 break;
			case "UT DELEGACION":
				if(respuesta.equals("")) {
					imagenp.setUtDelegacion(null);
            	}else {
            		imagenp.setUtDelegacion(respuesta);
            	}
				
			         break;
			case "NUMERO DE FOLIO":
				if(respuesta.equals("")||!met.isNumeric(respuesta)) {
				}else {
            		value=Long.parseLong(respuesta);
            	}
				imagenp.setNumeroFolio(value);
			         break;
			
        }
        return imagenp;
    }
    
    
    public Boolean guardarImagen(ImagenPersistenciaVO imagenp, ConfiguracionVO conf){
    	
		String rest_url = conf.getBaseurl()+"/imagenes/informacionImagen";
		
		RestTemplate restTemplate = (RestTemplate) BeanLocator.getService("restTemplate");	
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", conf.getToken());
		
		 HttpEntity<?> entity = new HttpEntity<>(imagenp, headers);
		 
		 System.out.println(imagenp.getIdlote()+", "+imagenp.getNombreImagen()+", "+imagenp.getNumeroFolio()+
				 ", "+imagenp.getLbImagenes().get(0).getNombreImagen()+", "+imagenp.getLbImagenes().get(0).getLbImagen());
		 
//		 Object response = new RestTemplate().postForEntity(rest_url, entity, Boolean.class);
		 ResponseEntity<Boolean> response = new RestTemplate().postForEntity(rest_url, entity, Boolean.class);
		 System.out.println("Respuesta de inserción: "+response.toString());
		 Boolean status = (Boolean ) response.getBody();
		return status;
	}
    
   
}
