package mx.com.teclo.base.util.methods;

import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.SanselanException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.images.ImagenMediasAritmeticas;
import mx.com.teclo.base.vo.images.ImagenPersistenciaVO;
import mx.com.teclo.base.vo.lotes.LoteVO;

import java.lang.reflect.Type;


public class Methods {
	
	public List<String> getImages(String path, List<String> extension, boolean validarExtension){
//		System.out.println("estas aqui"+path);
		File f = new File(path);
		List<String> imagesvalid = null;
		if (f.exists() && f.isDirectory()) {
			File[] files = f.listFiles();
	        if (files != null){
	        	imagesvalid = new ArrayList();
		        for (int i = 0; i < files.length; i++) {
		            File file = files[i];
		            if(file != null) {// extension.trim()..contains(search) file.getName().endsWith(extension)){            	
		            		if(validarExtension) {
		            			for(int j=0;j<extension.size();j++) {
		            				if(file.getName().endsWith(extension.get(j))) {
				            			imagesvalid.add(file.getName());
				            		}
		            			}
		            			
		            		}else {
		            			imagesvalid.add(file.getName());
		            		}	            		
		            	          	
		            }
		        }
	        }
		}else {
			 //JOptionPane.showMessageDialog(null,"La ruta de archivos no existe o no es válida");
		}
		 
        return imagesvalid;      
    }
	
	
	public BufferedImage ScaleImage(java.awt.Image img, int w, int h){
        BufferedImage resizedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();
        return resizedImage;
    }
	
	public boolean deleteFile(String path, String fileName) {
		boolean isDelete=false;
		File file = new File(path+fileName); 
        
        if(file.delete()) 
        { 
            isDelete=true;
        } 
        else
        { 
            System.out.println("Fallo al borrar el archivo"); 
        }
        return isDelete;
	}
	
	public BufferedImage rotate180( BufferedImage inputImage ) {
		//We use BufferedImage because it’s provide methods for pixel manipulation
			int width = inputImage.getWidth(); //the Width of the original image
			int height = inputImage.getHeight();//the Height of the original image

			BufferedImage returnImage = new BufferedImage( width, height, inputImage.getType()  );
		//we created new BufferedImage, which we will return in the end of the program
		//it set up it to the same width and height as in original image
		// inputImage.getType() return the type of image ( if it is in RBG, ARGB, etc. )

			for( int x = 0; x < width; x++ ) {
				for( int y = 0; y < height; y++ ) {
		                    returnImage.setRGB(width-x-1, height-y-1, inputImage.getRGB(x, y));
					//returnImage.setRGB( width - x – 1, height – y – 1, inputImage.getRGB( x, y  )  );
				}
			}
		//so we used two loops for getting information from the whole inputImage
		//then we use method setRGB by whitch we sort the pixel of the return image
		//the first two parametres is the X and Y location of the pixel in returnImage and the last one is the //source pixel on the inputImage
		//why we put width – x – 1 and height –y – 1 is hard to explain for me, but when you rotate image by //180degree the pixel with location [0, 0] will be in [ width, height ]. The -1 is for not to go out of
		//Array size ( remember you always start from 0 so the last index is lower by 1 in the width or height
		//I enclose Picture for better imagination  ... hope it help you
			return returnImage;
		//and the last return the rotated image
		//reference: http://forum.codecall.net/topic/69182-java-image-rotation/
		}
	
	public void makeFolders(String path, String[] folders) throws IOException{            
        for(int i=0; i<folders.length; i++){
        	path=path+folders[i]+"\\";
            File file = new File(path);
            if (!file.exists()) {
                if (file.mkdir()) {
//                    System.out.println("Directory is created!");
                } else {
//                    System.out.println("Failed to create directory!");
                }
            }
        }       
    }
	
//	public void copyToPath(String pathRoot, String pathDestination, String OriginalImageName, String NewImageName){
////		 System.out.println("va de: "+pathRoot+" a "+pathDestination);
//		File file = new File(pathRoot+OriginalImageName);
//		ImageInfo imageInfo;
//        try {
//        	imageInfo = Sanselan.getImageInfo(file);
//			Files.copy(Paths.get(pathRoot+OriginalImageName), Paths.get(pathDestination+NewImageName+"."+imageInfo.getFormat().extension));
//		} catch (IOException | SanselanException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//   }
	public void copyToPath(String pathRoot, String pathDestination, String OriginalImageName, String NewImageName){
//		 System.out.println("va de: "+pathRoot+" a "+pathDestination);
		File file = new File(pathRoot+OriginalImageName);
		ImageInfo imageInfo;
       try {
       	imageInfo = Sanselan.getImageInfo(file);
			Files.copy(Paths.get(pathRoot+OriginalImageName), Paths.get(pathDestination+NewImageName+"."+imageInfo.getFormat().extension));
		} catch (IOException | SanselanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  }
	
	public void copyFileToPath(String pathRoot, String pathDestination, String OriginalName, String NewName){
		try {
			Files.copy(Paths.get(pathRoot+OriginalName), Paths.get(pathDestination+NewName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  		
	}
	
	public void deleteCompleteFolder(String folderpath) {
		System.out.println("vas a borrar folder");
		File folder = new File(folderpath);
		if(folder.exists()&&folder.isDirectory()) {
			System.out.println("Si existe el folder y es carpeta");
//			String[]entries = folder.list();
//			for(String s: entries){
//				System.out.println("se borra: "+s);
//			    File currentFile = new File(folder.getPath(),s);
//			    currentFile.delete();
//			}
//			folder.delete();
			try {
				//Deleting the directory recursively.
				delete(folderpath);
				System.out.println("Directory has been deleted recursively !");
			} catch (IOException e) {
				System.out.println("Problem occurs when deleting the directory : " + folderpath);
				e.printStackTrace();
			}
		}
		
		
	}
	
	private void delete(String directoryName) throws IOException {
		 
		Path directory = Paths.get(directoryName);
		Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
 
			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}
 
			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc)
					throws IOException {
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	public void replaceToPath(String pathRoot, String pathDestination, String OriginalImageName, String NewImageName){
//		 System.out.println("va reemplazar: "+pathDestination+" con "+OriginalImageName);
		if(deleteFile(pathDestination, NewImageName)) {
			try {
					Files.copy(Paths.get(pathRoot), Paths.get(pathDestination+NewImageName));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		}else {
			System.out.println("ocurrio un probema al reemplazar el archivo");
		}		
 }
	
	public static boolean isDateValid(String date, String dateformat) {
		boolean isvaliddate=true;
		DateFormat df = new SimpleDateFormat(dateformat);
		df.setLenient(false);
		try {
			df.parse(date);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isvaliddate=false;
		}
		return isvaliddate;
	 }
	
	public static Date convertStringToDate(String fecha, String format) {
		Date date=null;
		try {
			date = new SimpleDateFormat(format).parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Ocurrio un error al parsear la fecha: "+fecha);
		}
		return date;
	}
	
	public static String convertDateToString(Date fecha, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String fecha_str=null;
		try {
			fecha_str=simpleDateFormat.format(fecha);	
		}catch(Exception e){
			System.out.println("Ocurrio un error al parsear la fecha: "+fecha+"; error: "+e);
		}
		return fecha_str;
			 
	}
	
	public static Long convertDateToLong(String date, String format) {
		long milliseconds=0L;
		SimpleDateFormat f = new SimpleDateFormat(format);
		try {
		    Date d = f.parse(date);
		    milliseconds = d.getTime();
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return milliseconds;
	}
	
	public static Date convertLongToDate(Long fecha, String format) {
		Date d = new Date(fecha);
    	String fecha_str= convertDateToString(d, format);
    	Date fecha_d = convertStringToDate(fecha_str, format);
    	return fecha_d;
	}
	
	
	/* @Author:Gibran Garcia
	 * Descripcion: recibe la nomenclatura(plantilla) del formato del nombre que se va a asignar a la imagen
	 * y valida que este formato cumpla con los siguientes estandares: 
	 * 1.- Debe haber unicamente un solo segmento para la numeracion
	 * 2.- No debe haber ausencia de segmento para la numeracion
	 * 3.- El segmento de la numeracion debe estar representado por n numero de caracteres iguales
	 * 4.- No debe contener caracteres especiales que windows no acepte para el nombrado de archivos
	 * 
	 * Busca el caracter para saber el tamaño del formato numerico y poder parsear el número.
	 * Ejemplo entrada: "SSP###A2018", "#"
	 * Ejemplo de salida: true
	 * */
	public boolean validaFormatoNomenclatura(String nomenclatura, char character) {
		boolean pasando=false;
		boolean detener=false;
		int totalcaracteres=0;
		char[] invalidChars= {'\\','/','?','*',':','<','>','|'};
		
		for(int i=0;i<invalidChars.length;i++) {
			if(nomenclatura.contains(invalidChars[i]+"")) {
				System.out.println("La nomenclatura no debe contener caracteres especiales");
				return false;
			}
		}
			
		char[] charArray = nomenclatura.toCharArray();
		
		for(int i=0;i<charArray.length;i++) {
			if (Character.toString(charArray[i]).matches(Character.toString(character))) {
				if(pasando&&detener) {
					System.out.println("No puede haber dos segmentos numéricos");
					return false;
				}else {
					pasando=true;
					totalcaracteres++;
				}
				
			}else {
				if(pasando) {
					detener=true;
				}
			}
		}	
		if(totalcaracteres<=0) {
			System.out.println("no hay caracteres para el segmento numérico");
			return false;
		}
		return true;
	}
	
	
	/* @Author:Gibran Garcia
	 * Descripcion: Recibe un número, la nomenclatura (plantilla) del nombre de la imagen y el caracter a reemplazar
	 * Busca el caracter para saber el tamaño del formato numerico y poder parsear el número.
	 * Ejemplo entrada: 1 , "SSP###A2018", "#"
	 * Ejemplo de salida: SSP001A2018
	 * 
	 * */
	public String parsearNumeroANomenclatura(int number, String nomenclatura, char character) {
		String nombreImagen="";
		if(validaFormatoNomenclatura(nomenclatura, character)) {
			String ceros="";
			String squares="";
			char[] charArray = nomenclatura.toCharArray();
			for(int i=0;i<charArray.length;i++) {
				if (Character.toString(charArray[i]).matches(Character.toString(character))) {
					squares=squares+character;
					ceros=ceros+"0";
				}
			}
			NumberFormat formatter = new DecimalFormat(ceros);
			String numberWithFormat = formatter.format(number);
			nombreImagen = nomenclatura.replaceAll(squares, numberWithFormat);
//			System.out.println("nomenclatura= "+nomenclatura);
//			System.out.println("numeroConFormato= "+nombreImagen);			
			
		}else {
			System.out.println("La nomenclatura tiene un formato inválido!");
			return null;
		}
		return nombreImagen;
	}
	
	
	public void moveToPath(String pathRoot, String pathDestination, String OriginalImageName, String NewImageName){
        try {
//            System.out.println("va de: "+pathRoot+" a "+pathDestination);
                Files.move(Paths.get(pathRoot+OriginalImageName), Paths.get(pathDestination+NewImageName));               
            } catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
    }
	
	/* @Author:Gibran Garcia
	 * Descripcion: Valida que el nombre de una imagen haga match correctamente con la nomenclatura definida para tal nombre.
	 * Ejemplo nomenclatura: SSP###A2018
	 * Si el nombre de la imagen es SSP15000A2018.JPG, es valido ya que al incrementar la iteración en el escaneo, el
	 * segmento dedicado a los numeros con el caracter '#' queda pequeño, entonces nuestro programa permite incrementar
	 * la secuencia para no arrojar una excepcion.
	 * Ejemplos validos con la nomenclatura: SSP001A2018, SSP050A2018, SSP1000A2018, SSP30500A2018
	 * 
	 * */
	public boolean validaNombreImagenCorrespondaNomenclatura(String nomenclatura, String nombreImagen, char caracter) {
		
		nombreImagen = nombreImagen.replaceFirst("[.][^.]+$", "");
				
		if(nombreImagen.length()>=nomenclatura.length()) {
			int ini=nomenclatura.indexOf(caracter);
			int fini=nomenclatura.lastIndexOf(caracter);
//			System.out.println(""+nomenclatura);
//			System.out.println("inic: "+ini+", fini: "+fini);
			
			String before = nomenclatura.substring(0, ini);
			String while_ = nomenclatura.substring(ini, fini+1);
			String after = nomenclatura.substring(fini+1, nomenclatura.length());
			
//			System.out.println("before: "+before+" -> size:"+before.length());
//			System.out.println("while: "+while_+" -> size:"+while_.length());
//			System.out.println("after: "+after+" -> size:"+after.length());
			
			if(before.length()>0) {
				String before_nomImagen = nombreImagen.substring(0, before.length());
				if(before_nomImagen.equals(before)) {
//					System.out.println("before: "+before+" y before_nomImagen: "+before_nomImagen+" si coincide!");
				}else {
					System.out.println("no cumple con el formato de nomenclatura lado anterior");
//					System.out.println("before: "+before+" y before_nomImagen: "+before_nomImagen+" No coincide!");
					return false;				
				}				
			}
			if(after.length()>0) {
				String after_nomImagen = nombreImagen.substring(nombreImagen.length()-after.length(), nombreImagen.length());
				if(after_nomImagen.equals(after)) {
//					System.out.println("after: "+after+" y after_nomImagen: "+after_nomImagen+" Si coincide!");
				}else {
					System.out.println("no cumple con el formato de nomenclatura lado posterior");
//					System.out.println("after: "+after+" y after_nomImagen: "+after_nomImagen+" No coincide!");
					return false;				
				}				
			}
			
			if(while_.length()>0) {
				String numeroImagen = nombreImagen.substring(before.length(), nombreImagen.length()-after.length());
//				System.out.println("numeroImagen: "+numeroImagen);
				try {
//					System.out.println("Trataremos de parsear: "+numeroImagen);
					int numImagen = Integer.parseInt(numeroImagen);	
				} catch (NumberFormatException e) {  
					System.out.println("El numero de secuencia no es un numero válido");
					return false;  
				} 			
			}else {
				System.out.println("No hay segmento para numeros en el formato nomenclatura de la imagen");
				return false;
			}						
			
			
		}else {
			System.out.println("La longitud del nombre de la imagen es inferior al establecido en la nomenclatura");
			return false;
		}
		return true;			
	}
	
	
public int retornaNumeroDeImagenRespectoANomenclatura(String nomenclatura, String nombreImagen, char caracter) {
		boolean allGood=false;
		int numImagen=0;
		nombreImagen = nombreImagen.replaceFirst("[.][^.]+$", "");
				
		if(nombreImagen.length()>=nomenclatura.length()) {
			int ini=nomenclatura.indexOf(caracter);
			int fini=nomenclatura.lastIndexOf(caracter);
//			System.out.println(""+nomenclatura);
//			System.out.println("inic: "+ini+", fini: "+fini);
			
			String before = nomenclatura.substring(0, ini);
			String while_ = nomenclatura.substring(ini, fini+1);
			String after = nomenclatura.substring(fini+1, nomenclatura.length());
			
//			System.out.println("before: "+before+" -> size:"+before.length());
//			System.out.println("while: "+while_+" -> size:"+while_.length());
//			System.out.println("after: "+after+" -> size:"+after.length());
			
			if(before.length()>0) {
				String before_nomImagen = nombreImagen.substring(0, before.length());
				if(before_nomImagen.equals(before)) {
//					System.out.println("before: "+before+" y before_nomImagen: "+before_nomImagen+" si coincide!");
				}else {
					System.out.println("no cumple con el formato de nomenclatura lado anterior");
//					System.out.println("before: "+before+" y before_nomImagen: "+before_nomImagen+" No coincide!");
//					allGood= false;				
				}				
			}
			if(after.length()>0) {
				String after_nomImagen = nombreImagen.substring(nombreImagen.length()-after.length(), nombreImagen.length());
				if(after_nomImagen.equals(after)) {
//					System.out.println("after: "+after+" y after_nomImagen: "+after_nomImagen+" Si coincide!");
				}else {
					System.out.println("no cumple con el formato de nomenclatura lado posterior");
//					System.out.println("after: "+after+" y after_nomImagen: "+after_nomImagen+" No coincide!");
//					return false;				
				}				
			}
			
			if(while_.length()>0) {
				String numeroImagen = nombreImagen.substring(before.length(), nombreImagen.length()-after.length());
//				System.out.println("numeroImagen: "+numeroImagen);
				try {
//					System.out.println("Trataremos de parsear: "+numeroImagen);
					numImagen = Integer.parseInt(numeroImagen);	
					allGood=true;
				} catch (NumberFormatException e) {  
					System.out.println("El numero de secuencia no es un numero válido");
//					return false;  
				} 			
			}else {
				System.out.println("No hay segmento para numeros en el formato nomenclatura de la imagen");
//				return false;
			}						
			
			
		}else {
			System.out.println("La longitud del nombre de la imagen es inferior al establecido en la nomenclatura");
//			return false;
		}
		return numImagen;			
	}
	
	/* @Author:Gibran Garcia
	 * Descripcion: Valida que el archivo no se encuentre dañado o no contenga informacion en absoluto o este inaccesible.
	 * */
	public boolean validaArchivoInaccesibleODaniado(String path,String filename) {
		File outputfile = new File(path+filename);
		Long size=outputfile.getTotalSpace();
		if(size<=0) {
			return false;
		}
		if(!outputfile.exists()||!outputfile.canRead()) {
			return false;
		}
		return true;
	}
	
	public double calculateAverage(List <Integer> marks) {
		  Integer sum = 0;
		  if(!marks.isEmpty()) {
		    for (Integer mark : marks) {
		        sum += mark;
		    }
		    return sum.doubleValue() / marks.size();
		  }
		  return sum;
	}
	
	public int getmode(List<Integer> conjunto) {
		// Contains all the modes
	      List<Integer> modes = new ArrayList<Integer>();
	   // list of all the numbers with no duplicates
	      TreeSet<Integer> tree = new TreeSet<Integer>();
	 
	      tree.addAll(conjunto);
	      
	      //System.out.println("list: " + conjunto);
	      //System.out.println("tree: " + tree);
	      
	      int highmark = 0;
	      for (Integer x: tree) {
	         int freq = Collections.frequency(conjunto, x);
	         if (freq == highmark) {
	            modes.add(x);
	         }
	         if (freq > highmark) {
	            modes.clear();
	            modes.add(x);
	            highmark = freq;
	         } 
	      }
	      //System.out.println("modes: " + modes);
	      return modes.get(0);
	}
	
	
	/* @Author:Gibran Garcia
	 * Descripcion: Obtiene informacion relevante de cada archivo y lo almacena en una lista con el objetivo de
	 * calcular la media aritmetica del ancho, alto, dpi ancho, dpi alto de la imagen, y así evaluar si una imagen
	 * es potencialmente erronea o no pertenece al conjunto de imagenes a procesar.
	 * 
	 * Ejemplos de entrada: "C:\SistemaDigitalizacion\01-02-2018\gibby\", listaDeImagenes
	 * Salida:  
	 * 
	 * */
	public ImagenMediasAritmeticas generaListaInfoPromedioImagenes(String path, List<String> imagenes) {
		
		ImagenMediasAritmeticas medias = new ImagenMediasAritmeticas();
		
		List<Integer> listDpi_height = new ArrayList<Integer>();
		List<Integer> listDpi_width = new ArrayList<Integer>();
		List<Integer> listHeight = new ArrayList<Integer>();
		List<Integer> listWidth = new ArrayList<Integer>();
		List<Integer> listColorType = new ArrayList<Integer>();
		
		for(int i=0;i<imagenes.size();i++) {
			File file = new File(path+imagenes.get(i));
			ImageInfo imageInfo;
			try {
				
				imageInfo = Sanselan.getImageInfo(file);
				
				//De esta seccion obtener la moda
				listDpi_width.add(imageInfo.getPhysicalWidthDpi());//DPI width
				listDpi_height.add(imageInfo.getPhysicalHeightDpi());//DPI Height
				listColorType.add(imageInfo.getColorType());
				
				//De esta seccion obtener el promedio
				listHeight.add(imageInfo.getHeight());
				listWidth.add(imageInfo.getWidth());
		
				
			} catch (ImageReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Obtenemos los valores importantes
		medias.setListDpi_height(getmode(listDpi_height));
		medias.setListDpi_width(getmode(listDpi_width));
		medias.setListColorType(getmode(listColorType));
		medias.setListHeight((int) calculateAverage(listHeight));
		medias.setListWidth((int) calculateAverage(listWidth));
			
		return medias;
	}
	
	
	public void  writeImagesFromPathToAnotherCustomFormat(String originalPath, String newPath, 
			String originalName, String newName, String format) {
//		System.out.println("este formato completo: "+format);
		String formato = format.replace(".", "");
//		System.out.println("este formato: "+formato);
		File inputFile = new File(originalPath+originalName);
		File outputFile = new File(newPath+newName+format);
		
		try (InputStream is = new FileInputStream(inputFile)) {
		    BufferedImage image = ImageIO.read(is);
		    try (OutputStream os = new FileOutputStream(outputFile)) {
		    	ImageOutputStream ios = ImageIO.createImageOutputStream(os);
		        ImageIO.write(image, formato, ios);
		    } catch (Exception exp) {
		        exp.printStackTrace();
		    }
		} catch (Exception exp) {
		    exp.printStackTrace();
		}
		
		
	}
	

	/********************SECCION PARA PRUEBAS CON BINARIOS
	 * @throws SQLException 
	 * @throws SerialException *******************************/
	
	public static SerialBlob convertFileToBlob(String fileName) throws IOException, SerialException, SQLException {
	    File file = new File(fileName);
	    byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
//	    System.out.println("base: "+encoded);
	    SerialBlob blob =new javax.sql.rowset.serial.SerialBlob(encoded);
//	    return new String(encoded, StandardCharsets.US_ASCII);
//	    System.out.println(blob);
	    return blob;
	}
	
	
	
	
	
	public static String convertByteToString(byte[] binario){
        String cadenaBytes="";       
            StringBuilder binary = new StringBuilder();
            for (byte b : binario){
               int val = b;
               for (int i = 0; i < 8; i++){
                  binary.append((val & 128) == 0 ? 0 : 1);
                  val <<= 1;
               }
               binary.append(' ');
            }
            return cadenaBytes= binary.toString();
    }
	
	public static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
	public List<ImagenPersistenciaVO> convertFileToJson(String path)
		    throws FileNotFoundException
		  {
		String jsonn="";
		try {
			jsonn=readFile(path,StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final Gson gson = new Gson();
		final Type tipoListadatosExcel = new TypeToken<List<ImagenPersistenciaVO>>(){}.getType();
		final List<ImagenPersistenciaVO> datosExcel = gson.fromJson(jsonn, tipoListadatosExcel);
		
		return datosExcel;
		 }
	
	public static void saveStringToFile(String cadenaBytes, String destinationPath) {
//		System.out.println("ssss"+cadenaBytes);
        try (PrintStream out = new PrintStream(new FileOutputStream(destinationPath))) {
            out.print(cadenaBytes);
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static byte[] convertFileContentToBlob(String filePath) throws IOException {
		byte[] fileContent = null;
		try {
			fileContent = FileUtils.readFileToByteArray(new File(filePath));
		} catch (IOException e) {
			throw new IOException("Unable to convert file to byte array. " + e.getMessage());
		}
		return fileContent;
	}
	/********************FIN DE SECCION PARA PRUEBAS CON BINARIOS*******************************/
	
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	
	public boolean validaImagenSospechosa(String path, String image, ImagenMediasAritmeticas medias) {
//		System.out.println("/**************************************************/");
//		System.out.println(""+image);
		File file = new File(path+image);
		ImageInfo imageInfo;
		
		boolean anchoDpi=false;
		boolean altoDpi=false;
		boolean color=false;
		
		boolean anchoimg=false;
		boolean altoimg=false;
		
		int countAlertas=0;
		
		try {
			
			imageInfo = Sanselan.getImageInfo(file);
			
			//De esta seccion obtener la moda
			int dpi_width = imageInfo.getPhysicalWidthDpi();//DPI width
			int dpi_height = imageInfo.getPhysicalHeightDpi();//DPI Height
			int colortype = imageInfo.getColorType();
			
			
			if(dpi_width==medias.getListDpi_width()) {
				anchoDpi=true;
				System.out.println("dpi anchura correcta: "+dpi_width);
			}else {
				System.out.println("dpi anchura incorrecta: "+dpi_width);
			}
			if(dpi_height==medias.getListDpi_height()) {
				altoDpi=true;
				System.out.println("dpi anchura correcta: "+dpi_height);
			}else {
				System.out.println("dpi anchura incorrecta: "+dpi_height);
			}
			if(colortype==medias.getListColorType()) {
				color=true;
				System.out.println("color correcta: "+colortype);
			}else {
				System.out.println("color incorrecta: "+colortype);
			}
		
			
			//De esta seccion obtener el porcentaje promedio			
			int height=imageInfo.getHeight();
			int width = imageInfo.getWidth();
			
			double percent_height_min= medias.getListHeight()-(medias.getListHeight()*0.2);
			double percent_height_max= medias.getListHeight()+(medias.getListHeight()*0.2);
			
			double percent_width_min= medias.getListWidth()-(medias.getListWidth()*0.2);
			double percent_width_max= medias.getListWidth()+(medias.getListWidth()*0.2);
			
//			System.out.println("altura promedio min_height: "+percent_height_min);
//			System.out.println("altura promedio max_height: "+percent_height_max);
//			System.out.println("altura promedio min_width: "+percent_width_min);
//			System.out.println("altura promedio max_width: "+percent_width_max);
			
			if(height>=percent_height_min&&height<=percent_height_max) {
				anchoimg=true;
//				System.out.println("altura correcta: "+height);
			}else {
				System.out.println("altura incorrecta: "+height);
			}
			if(width>=percent_width_min&&width<=percent_width_max) {
//				System.out.println("anchura correcta: "+width);
				altoimg=true;
			}else {
				System.out.println("anchura incorrecta: "+width);
			}
	
			
		} catch (ImageReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	
		if(!anchoDpi) {
			countAlertas++;
		}
		if(!altoDpi) {
			countAlertas++;
		}
		if(!color) {
			countAlertas++;
		}
		if(!anchoimg) {
			countAlertas++;
		}
		if(!altoimg) {
			countAlertas++;
		}
		
		if(countAlertas>=3) {
			return false;
		}
		
//		System.out.println("/*************************************************************/");
		
		return true;
	}
	
	
	private JPanel getPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Java Technology Dive Log");
        ImageIcon image = null;
        try {
            image = new ImageIcon(ImageIO.read(
                    new URL("http://i.imgur.com/6mbHZRU.png")));
        } catch(MalformedURLException mue) {
            mue.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } 

        label.setIcon(image);
        panel.add(label);

        return panel;
    }

	public static void writeToZipFile(String path, ZipOutputStream zipStream) throws FileNotFoundException, IOException { 
		System.out.println("Writing file : '" + path + "' to zip file"); 
		File aFile = new File(path); FileInputStream fis = new FileInputStream(aFile); 
		ZipEntry zipEntry = new ZipEntry(path); 
		zipStream.putNextEntry(zipEntry); 
		byte[] bytes = new byte[1024]; int length; 
			while ((length = fis.read(bytes)) >= 0) { 
				zipStream.write(bytes, 0, length);
			} 
			zipStream.closeEntry(); fis.close(); 
		}
	
	
	public void download(String remotePath, String localPath) {
	    BufferedInputStream in = null;
	    FileOutputStream out = null;

	    try {
	        URL url = new URL(remotePath);
	        URLConnection conn = url.openConnection();
	        int size = conn.getContentLength();
//	        int size = 210;
	        if (size < 0) {
	            System.out.println("Could not get the file size");
	        } else {
	            System.out.println("File size: " + size);
	        }

	        in = new BufferedInputStream(url.openStream());
	        out = new FileOutputStream(localPath);
	        byte data[] = new byte[1024];
	        int count;
	        double sumCount = 0.0;

	        while ((count = in.read(data, 0, 1024)) != -1) {
	            out.write(data, 0, count);

	            sumCount += count;
	            if (size > 0) {
	                System.out.println("Percentace: " + (sumCount / size * 100.0) + "%");
	            }
	        }
	        System.out.println("ce fini");
	                

	    } catch (MalformedURLException e1) {
	        e1.printStackTrace();
	    } catch (IOException e2) {
	        e2.printStackTrace();
	    } finally {
	        if (in != null)
	            try {
	                in.close();
	            } catch (IOException e3) {
	                e3.printStackTrace();
	            }
	        if (out != null)
	            try {
	                out.close();
	            } catch (IOException e4) {
	                e4.printStackTrace();
	            }
	    }
	}
	
	public void getFile2() {
		ConfiguracionVO conf = new ConfiguracionVO();
		String FisDll=conf.getBaseAppurl()+"/jnlp/jar/Scan/FiScn.dll";
		String FisJar=conf.getBaseAppurl()+"/jnlp/jar/Scan/Fiscn.jar";
		String GsonJar=conf.getBaseAppurl()+"/jnlp/jar/Scan/gson-2.6.2.jar";
		String JsonJar=conf.getBaseAppurl()+"/jnlp/jar/Scan/json-simple-1.1.jar";
		String TecloScanJar=conf.getBaseAppurl()+"/jnlp/jar/Scan/TecloScan.jar";
		
		
		
		String localpath=conf.getLocalFilePath()+"Scan.zip";
		
		download(FisDll, conf.getLocalFilePath()+"/lib/FiScn.dll");
		download(FisJar, conf.getLocalFilePath()+"/lib/Fiscn.jar");
		download(GsonJar, conf.getLocalFilePath()+"/lib/gson-2.6.2.jar");
		download(JsonJar, conf.getLocalFilePath()+"/lib/json-simple-1.1.jar");
		download(TecloScanJar, conf.getLocalFilePath()+"TecloScan.jar");
		
		
//		File zip = new File(conf.getLocalFilePath()+"Scan.zip");
//		if(zip.exists()&&zip.isFile()) {
//			try {
//				unzip(conf.getLocalFilePath()+"Scan.zip", conf.getLocalFilePath());
//				deleteFile(conf.getLocalFilePath(),"Scan.zip");
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}else {
//			System.out.println("El archivo no existe");
//		}
	}
	
	

	public void getFile() throws IOException {
		ConfiguracionVO conf = new ConfiguracionVO();
//		JOptionPane.showMessageDialog(null, "entraste a getfile ");
		File zip = new File("C:\\TECLO_PROCESSING\\tempjar.zip");
		File jar = null;
		String ruute="";
//		JOptionPane.showMessageDialog(null, "pasaste limite ");
		try {
			ruute="http://172.18.44.99:9080"+(Methods.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
			jar = new File("http://172.18.44.99:9080"+(Methods.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(null, "error1: "+e);
			e.printStackTrace();
			
		}
//		JOptionPane.showMessageDialog(null, "pasaste primer try ");
		if(jar.exists()) {
//			JOptionPane.showMessageDialog(null, "Si existe "+ruute);
			try {
//				JOptionPane.showMessageDialog(null, "si: "+(Methods.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath());
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
//				JOptionPane.showMessageDialog(null, "error2: "+e1);
				e1.printStackTrace();
				
			}
			try {
				FileUtils.copyFile(
						jar, 
						zip);
				descomprimeSpecificFileFromZip("C:\\TECLO_PROCESSING\\tempjar.zip", 
						"Scan.zip", "C:\\TECLO_PROCESSING\\Scan.zip");
				
				unzip("C:\\TECLO_PROCESSING\\Scan.zip", "C:\\TECLO_PROCESSING\\");
				deleteFile("C:\\TECLO_PROCESSING\\","tempjar.zip");
				deleteFile("C:\\TECLO_PROCESSING\\","Scan.zip");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
//			JOptionPane.showMessageDialog(null, "No existe "+ruute);
		}

	}
		
	public void unzip( String fileZip, String destin) throws FileNotFoundException, IOException{
        File destDir = new File(destin);
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        int count=0;
        while (zipEntry != null) {
//            System.out.println(count);
            File newFile = newFile(destDir, zipEntry);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
	
	public void descomprimeSpecificFileFromZip(String zipPackage, String fileToBeExtracted, String rutaDestino) throws IOException {
		
        try (FileSystem fileSystem = FileSystems.newFileSystem(Paths.get(zipPackage), null)) {
            Path fileToExtract = fileSystem.getPath(fileToBeExtracted);
            Files.copy(fileToExtract, Paths.get(rutaDestino));
        }
//        JOptionPane.showMessageDialog(null, "Descomprime: ");
	}
	
	public static void main(String[] args) {
		
		Methods met = new Methods();
		met.getFile2();
		
		
//		
//		try {
//			met.getFile("zipScan/Scan.zip");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Methods met = new Methods();
//		try {
//			met.descomprimeSpecificFileFromZip("C:\\TECLO_PROCESSING\\tempjar.zip", 
//					"Scan.zip", "C:\\TECLO_PROCESSING\\Scan.zip");
//			
//			met.unzip("C:\\TECLO_PROCESSING\\Scan.zip", "C:\\TECLO_PROCESSING\\");
//			met.deleteFile("C:\\TECLO_PROCESSING\\","tempjar.zip");
//			met.deleteFile("C:\\TECLO_PROCESSING\\","Scan.zip");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		 JOptionPane.showConfirmDialog(null,
//                 met.getPanel(),
//                 "JOptionPane Example : ",
//                 JOptionPane.OK_CANCEL_OPTION,
//                 JOptionPane.PLAIN_MESSAGE);
		
		
		
//        System.out.println("empieza la funcion");
        
//        String path="C:\\SistemaDigitalizacion\\01-02-2018\\gibby\\";
//        List<String> images = met.getImages(path, null, false);
//        ImagenMediasAritmeticas medias = met.generaListaInfoPromedioImagenes(path, images);
//        System.out.println("getListDpi_height: "+medias.getListDpi_height());
//        System.out.println("getListDpi_width: "+medias.getListDpi_width());
//        System.out.println("getListHeight: "+medias.getListHeight());
//        System.out.println("getListWidth: "+medias.getListWidth());
//        System.out.println("getListColorType: "+medias.getListColorType());        
//        
//        for(int i=0; i<images.size();i++) {
//        	
//        	boolean isValid=met.validaImagenSospechosa(path, images.get(i), medias);
//        	System.out.println("la imagen: "+images.get(i)+", es Valida?= "+isValid);
//        }
        
        
        
//        char caracter = '#';
//        String nomenclatura="G##AA";
//        System.out.println("nomenclatura: "+nomenclatura);
//        
//        boolean nomenclaturaValida=met.validaFormatoNomenclatura(nomenclatura, caracter);
//        System.out.println("nomenclaturaValida: "+nomenclaturaValida);
//        
//        String nombreParseado=met.parsearNumeroANomenclatura(1000, nomenclatura, caracter);
//        System.out.println("nombreParseado: "+nombreParseado);
//
//        boolean nomImagenValido=met.validaNombreImagenCorrespondaNomenclatura(nomenclatura, "G01aa.jpg", '#');
//        System.out.println("nomImagenValido: "+nomImagenValido);
        
        /*********************************************************************/
        
//        String filename="C:\\SistemaDigitalizacion\\01-02-2018\\gibby\\P000188A.TIF";
//        String filename="C:\\SistemaDigitalizacion\\01-02-2018\\gibby\\SSP0009.JPG";
//        String filename="C:\\SistemaDigitalizacion\\01-02-2018\\gibby\\SSP0008.JPG";
//
//        File file = new File(filename);
//		ImageInfo imageInfo;
//		
//		try {
//			
//			imageInfo = Sanselan.getImageInfo(file);
//			final int physicalWidthDpi = imageInfo.getPhysicalWidthDpi();
//			final int physicalHeightDpi = imageInfo.getPhysicalHeightDpi();
//			
//			System.out.println("physicalWidthDpi: "+physicalWidthDpi);
//			System.out.println("physicalHeightDpi: "+physicalHeightDpi);
//			
//			System.out.println("getColorType: "+imageInfo.getColorTypeDescription());
//			System.out.println("getColorType: "+imageInfo.getColorType());
//			System.out.println("getBitsPerPixel: "+imageInfo.getBitsPerPixel());
//			System.out.println("getMimeType: "+imageInfo.getMimeType());
//			System.out.println("getFormat: "+imageInfo.getFormat().extension);
//			System.out.println("getHeight: "+imageInfo.getHeight());
//			System.out.println("getWidth: "+imageInfo.getWidth());
//			
//			
//		} catch (ImageReadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
        
        /***********************************************************************/
		
//        List<String> nones = new ArrayList<String>();
//        List<String> pares = new ArrayList<String>();
//        
//        
//		String path="C:\\SistemaDigitalizacion\\01-02-2018\\gibby\\";
//		List<String> images = met.getImages(path, null, false);
//		char caracter = '#';
//		String nomenclatura="SSP####";
//		
//		if ( (images.size() & 1) == 0 ) {
//			for(int i=1;i<=images.size();i++) {
//				String nombreParseado=met.parsearNumeroANomenclatura(i, nomenclatura, caracter);
//				String imagesAComparar=images.get(i-1).replaceAll(".JPEG", "");
//				if ( (i & 1) == 0 ) {
//					if(nombreParseado.equals(imagesAComparar)) {
//						pares.add(images.get(i-1));
//					}
//				}else{
//					if(nombreParseado.equals(imagesAComparar)) {
//						nones.add(images.get(i-1));
//					}
//				}
//			}
//		}else{
//			System.out.println("No existe relacion de paridad en las imágenes");
//		}
		
//		System.out.println("pares:");
//		for(int i=0;i<pares.size();i++) {
//			System.out.println(pares.get(i));
//		}
//		System.out.println("nones:");
//		for(int i=0;i<nones.size();i++) {
//			System.out.println(nones.get(i));
//		}
		/***********************************************************************/
//        float div=((1+1)*100)/70;
//        System.out.println(div);
		/********************************************************************/
        
//       int eacheven=2;
//        
//        for (int i = 0; i < 10; i+=eacheven) {
//        	System.out.println(i);
//        }
//        
        
        
        
//        try {
//			byte[] blob = convertFileContentToBlob("C:\\Users\\JuanMa\\Gibran\\SistemaDigitalizacion\\20180823122321316_batch\\test2.TIF");
//			String blob_str = convertByteToString(blob);
//			saveStringToFile(blob_str, "C:\\SistemaDigitalizacion\\binario2.txt");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
//        try {
//        	SerialBlob imagenb64=convertFileToBlob("C:\\SistemaDigitalizacion\\01-10-2018\\EJEMPLO_COMPLETO\\SSP0001.JPG");
			
//        } catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SerialException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
		
    }
	
	
	
	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
//        System.out.println(zipEntry.getName());
//        System.out.println(destinationDir);
        
        File destFile = new File(destinationDir, zipEntry.getName());
         
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
        
//        System.out.println(destDirPath);
//        System.out.println(destFilePath);
          
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
         
        return destFile;
    }
	
	
	public void runJar() {
//		System.out.println("vas a ejecutar archivo");
		Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("java -jar C:\\TECLO_PROCESSING\\TecloScan.jar");
            System.out.println("se ejecuto jar");
        } catch (IOException ex) {
            System.out.println(ex);
        }
	}
	
public void saveDataFile(String path, String fileName, String data){
        
		deleteFile(path,fileName);
//        System.out.println("Se guarda el lote:"+path+fileName);
//        System.out.println("Se guarda el lote:"+data);
        
        
        File fnew=new File(path+fileName);
        String source = data;
//        System.out.println(source);

        try {
            FileWriter f2 = new FileWriter(fnew, false);
            f2.write(source);
            f2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
	
	

}
