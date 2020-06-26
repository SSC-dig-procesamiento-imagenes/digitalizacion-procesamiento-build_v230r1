package mx.com.teclo.escanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;


//import com.fujitsu.pfu.fiscn.sdk.Fiscn;
//import com.fujitsu.pfu.fiscn.sdk.FiscnException;

import mx.com.teclo.base.util.methods.Methods;
import mx.com.teclo.base.vo.lotes.LoteVO;
//import scannoe3.ScanNoe3;

@Service("scanner")
public class Scanner{
//	public class Scanner extends Fiscn{
	
//	public Scanner() throws FiscnException {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
//	public void consultaTest(LoteVO lote, int startNumber, boolean isReemplazoImg, String currentImage){
//		ScanNoe3 scan = new ScanNoe3();
//		scan.escanea();
//	}

	public void consultaTest(LoteVO lote, int startNumber, boolean isReemplazoImg, String currentImage){
		
		System.setProperty("java.library.path", "C:\\Program Files (x86)\\Java\\jdk1.8.0_191\\bin\\");
	       File fDir = new File("C:\\Program Files (x86)\\Java\\jdk1.8.0_191\\bin\\FiScn.dll");
			File fFiscn=null;
			if (fDir.isFile())
	        {
				System.out.println("es archivo");
	          fFiscn = new File(fDir.getParent() + "\\FiScn.dll");
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
			
			
		Methods met = new Methods();
		
		met.runJar();
		
//		try {
//			met.unzip();
//			met.copyFileToPath(System.getProperty("user.dir")+"\\src\\main\\resources\\zipScan\\", "C:\\TECLO_PROCESSING\\lib\\", "FiScn.dll", "FiScn.dll");
//			met.runJar();
			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Scanner app = null;
//		try {
//			app = new Scanner();
//			//Initialize
//			app.initialize(app);
//			
//			
////	        app.setEdgeFiller(1);
////	        app.setShadow(254);
////	        app.setSharpness(4);
//			app.setAutoBorderDetection(true);
////	        app.setBackgroundColor(1);
////	        app.setLengthDetection(1);
//	        app.setPixelType(lote.getConfiguracionEscaner().getTipoPixel().getValor()); //Pixel type: RGB color, GreyScale, Binary
//            
//	        app.setThreshold(-2);
//	        app.setCropMarginSize(-5.0f);
//	        app.setCropPriority(1);
//	        app.setOrientation(0);
//	        
//	    
//	        
//			 //Set the scan parameters (properties)
//            app.setScanTo(0); //Data output method: file
//            app.setCompressionType(5); //Compression format: JPEG compression
//            app.setFileType(lote.getConfiguracionEscaner().getTipoArchivos().getValor()); //File format: JPEG file:  0->bmp, 1->tif, 2-> tif, 3->jpg, 4->pdf, 5->pdf, 6->jpg, 7->jpg, 8->tif
////           
//            if(lote.getConfiguracionEscaner().getResolucion().getValor()==99||
//            		lote.getConfiguracionEscaner().getTamanioPapael().getValor()==14) {//Es customizada
//            	
//            	app.setCustomResolution(lote.getConfiguracionEscaner().getResolucion().getValor());   
//            	app.setResolution((int) lote.getConfiguracionEscaner().getResolucion().getWidth());  
//            	
//            }else {
//            	app.setResolution(lote.getConfiguracionEscaner().getResolucion().getValor());// Dpi; Manual pagina 149
//            }
//
////            app.setContrast(lote.getConfiguracionEscaner().getContraste());
////            app.setBrightness(lote.getConfiguracionEscaner().getBrillo());
//            app.setContrast(255);
//            app.setBrightness(90);
//            
////            app.setPaperSupply(lote.getConfiguracionEscaner().getSuministroPapel().getValor());//Una cara, dos caras, ambos lados, ETC..
////          
//            if(lote.getConfiguracionEscaner().getTamanioPapael().getValor()==99||
//            		lote.getConfiguracionEscaner().getTamanioPapael().getValor()==14) {//Es customizada
//            	
//            	app.setUnit(lote.getConfiguracionEscaner().getTamanioPapael().getUnidadMedida().getValor());
//            	
////            	app.setPaperSize(lote.getConfiguracionEscaner().getTamanioPapael().getValor());//Page 126  
//            	
//            	app.setCustomPaperLength(Float.parseFloat(lote.getConfiguracionEscaner().getTamanioPapael().getAlto()));
//                app.setCustomPaperWidth(Float.parseFloat(lote.getConfiguracionEscaner().getTamanioPapael().getAncho()));
//                
//            	
//            	app.setPaperSupply(2);//Una cara, dos caras, ambos lados              
////            	app.setUnit(0);
//            	app.setPaperSize(99);
////            	app.setCustomPaperLength(9.505f);
////            	app.setCustomPaperWidth(5.515f);
//            	
//                /*
//                 * Nota:
//                 * 9.505 inches //tamaño de las imagenes prueba ssp en pulgadas = 1902px
//                 * 5.515 inches//tamaño de las imagenes prueba ssp en pulgadas = 1104px
//                 * 
//                 */
//            }else {
//            	app.setPaperSize(lote.getConfiguracionEscaner().getTamanioPapael().getValor()); 	
//            }
//            
//              
//            if(isReemplazoImg) {
//            	app.setFileCounter(-1);
//            	app.setFileName(lote.getRutaAlmacenamiento()+currentImage); //File name
//            }else {
//            	app.setFileCounter(startNumber);//Se configura de esta forma para empezar a partir de un numero determinado
//            	app.setFileName(lote.getRutaAlmacenamiento()+lote.getConfiguracionEscaner().getNombreNomenclatura()); //File name
//            }
//            
//            
//            
//            
//            app.setShowSourceUI(false); //Do not display the source for the user interface
//            
//            /*
//             * OTROS PARÁMETROS POR REVISAR:
//             * app.setBarcodeDetectionInf(true); //Barcode detection: ON
//             * app.setShowSourceUIInf(false); //Do not display the source for the user interface
//             * Cancelscan page:186
//             * app.setFileCounter(-1);//poner esto si no queremos que haya un numero consecutivo
//             */
//
//            
//			//Open the scanner (method)
//			app.openScanner2(); //OpenScanner2 is recommended
//			//Start scanning images (method)
//			
//			app.startScan();
//			//Close the scanner (method)
//			app.closeScanner();
//			}
//			//An error occurred during a scan
//			catch(FiscnException ex){
//				//Display the error information
//				System.err.println("An error occurred. Error code: " + ex.getErrorCode());
//				
//				System.out.println(ex.getStackTrace());
//			}
//			finally {
//				if (app != null) {
//					//Finish
//					app.unInitialize();
//				}
//			}
		
	}
}
