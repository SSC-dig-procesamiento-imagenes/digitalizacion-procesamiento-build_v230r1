package mx.com.teclo.base.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mx.com.teclo.base.util.methods.Methods;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.images.ImagenBlobPersistenciaVO;
import mx.com.teclo.base.vo.images.ImagenPersistenciaVO;
import mx.com.teclo.base.vo.lotes.LoteVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

public class ExcelWriter {
	
	Methods met = new Methods();
	ConfiguracionVO conf = new ConfiguracionVO();
	 //ImagenPersistenciaVO
	
//	static String placa="xxx";
//    static String numeroLicencia="xxx";
//    static String tipoLicencia="xxx";
//    static String licenciaExpedidaEn="xxx";
//    static String placaExpedidaEn="xxx";
//    static Long numeroArticuloInfraccion=1L;
//    static Long numeroFraccion=1L;
//    static Long numeroInciso=1L;
//    static Long numeroParrafo=1L;
//    static String placaOficial="xxx";
//    static String utDelegacion="xxx";
//    static Long numeroFolio=1L;
//    static Date fechaInfraccion= new Date();
//    static Long idlote=1L;
//    static String nombreImagen="xxx";
    
    
	
//	private static String[] columns2 = {"placa", "numeroLicencia", "tipoLicencia", "licenciaExpedidaEn",
//			"placaExpedidaEn", "numeroArticuloInfraccion", "numeroFraccion", "numeroInciso","numeroParrafo", 
//			"placaOficial", "utDelegacion", "numeroFolio", "fechaInfraccion", "idlote","nombreImagen",};
//    private static List<ImagenPersistenciaVO> datosImagen2 =  new ArrayList<>();

	// Initializing employees data to insert into the excel file
    static {
//        Calendar dateOfBirth = Calendar.getInstance();
//        dateOfBirth.set(1992, 7, 21);
//        datosImagen.add(new ImagenPersistenciaVO(placa, numeroLicencia, tipoLicencia, licenciaExpedidaEn,
//    			placaExpedidaEn, numeroArticuloInfraccion, numeroFraccion, numeroInciso,
//    			numeroParrafo, placaOficial, utDelegacion, numeroFolio, fechaInfraccion,
//    			idlote, nombreImagen));
//
//        dateOfBirth.set(1965, 10, 15);
//        datosImagen.add(new ImagenPersistenciaVO(placa, numeroLicencia, tipoLicencia, licenciaExpedidaEn,
//    			placaExpedidaEn, numeroArticuloInfraccion, numeroFraccion, numeroInciso,
//    			numeroParrafo, placaOficial, utDelegacion, numeroFolio, fechaInfraccion,
//    			idlote, nombreImagen));
//
//        dateOfBirth.set(1987, 4, 18);
//        datosImagen.add(new ImagenPersistenciaVO(placa, numeroLicencia, tipoLicencia, licenciaExpedidaEn,
//    			placaExpedidaEn, numeroArticuloInfraccion, numeroFraccion, numeroInciso,
//    			numeroParrafo, placaOficial, utDelegacion, numeroFolio, fechaInfraccion,
//    			idlote, nombreImagen));
    }
    
    public void crearExcel(LoteVO lote, String[] columnas, List<ImagenPersistenciaVO> datosImagen) throws IOException, InvalidFormatException {
    	 // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Reporte Procesamiento Digitalización");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(ImagenPersistenciaVO datos: datosImagen) {
            Row row = sheet.createRow(rowNum++);
            //
            if(datos.getNombreImagen()==null) {
            	 row.createCell(0)
         		.setCellValue("");
            }else {
            	 row.createCell(0)
         		.setCellValue(datos.getNombreImagen());
            }
           
            //
            if(datos.getPlaca()==null) {
            	 row.createCell(1)
             	.setCellValue("");
            }else {
            	 row.createCell(1)
             	.setCellValue(datos.getPlaca());
            }
           
            
            //
            if(datos.getNumeroLicencia()==null) {
            	row.createCell(2)
                .setCellValue("");
            }else {
            	row.createCell(2)
                .setCellValue(datos.getNumeroLicencia());
            }
            
            
            //
            if(datos.getTipoLicencia()==null) {
            	row.createCell(3)
            	.setCellValue("");
            }else {
            	row.createCell(3)
            	.setCellValue(datos.getTipoLicencia());
            }
            
            
            //
            if(datos.getLicenciaExpedidaEn()==null) {
            	row.createCell(4)
        		.setCellValue("");
            }else {
            	row.createCell(4)
        		.setCellValue(datos.getLicenciaExpedidaEn());
            }
            
            
            //
            if(datos.getPlacaExpedidaEn()==null) {
            	row.createCell(5)
         		.setCellValue("");
            }else {
            	row.createCell(5)
         		.setCellValue(datos.getPlacaExpedidaEn());
            }
                    
            //
            if(datos.getNumeroArticuloInfraccion()==null) {
            	row.createCell(6)
        		.setCellValue("");
            }else {
            	row.createCell(6)
        		.setCellValue(datos.getNumeroArticuloInfraccion());
            }
                       
            //
            if(datos.getNumeroFraccion()==null) {
            	row.createCell(7)
        		.setCellValue("");
            }else {
            	row.createCell(7)
        		.setCellValue(datos.getNumeroFraccion());
            }
            
            
            //
            if(datos.getNumeroInciso()==null) {
            	row.createCell(8)
        		.setCellValue("");
            }else {
            	row.createCell(8)
        		.setCellValue(datos.getNumeroInciso());
            }
            
            //
            if(datos.getNumeroParrafo()==null) {
            	row.createCell(9)
        		.setCellValue("");
            }else {
            	row.createCell(9)
        		.setCellValue(datos.getNumeroParrafo());
            }
            
            if(met.convertDateToString(datos.getFechaInfraccion(),conf.getFormatDateDecode())==null) {
            	row.createCell(10)//Fecha infraccion
        		.setCellValue("");
            }else {
            	row.createCell(10)//Fecha infraccion
        		.setCellValue(met.convertDateToString(datos.getFechaInfraccion(),conf.getFormatDateDecode()));
            }
            
            
           
            if(datos.getPlacaOficial()==null) {
            	row.createCell(11)
        		.setCellValue("");
            }else {
            	row.createCell(11)
        		.setCellValue(datos.getPlacaOficial());
            }
            
            
            if(datos.getUtDelegacion()==null) {
            	row.createCell(12)
            	.setCellValue("");
            }else {
            	row.createCell(12)
            	.setCellValue(datos.getUtDelegacion());
            }
            
            
            
            if(datos.getNumeroFolio()==null) {
            	row.createCell(13)
        		.setCellValue("");
            }else {
            	row.createCell(13)
        		.setCellValue(datos.getNumeroFolio());
            }
            
            
            //Estilo para fecha
//            Cell fechaInfraccion = row.createCell(12);
//            fechaInfraccion.setCellValue(datos.getFechaInfraccion());
//            fechaInfraccion.setCellStyle(dateCellStyle);
            if(datos.getIdlote()==null) {
            	row.createCell(14)
        		.setCellValue("");
            }else {
            	row.createCell(14)
        		.setCellValue(datos.getIdlote());
            }
            
            
            

        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(lote.getRutaAlmacenamiento()+"Reporte_Digitalización_"+lote.getNombreLote().toUpperCase()+".xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
        openExcel(lote.getRutaAlmacenamiento()+"Reporte_Digitalización_"+lote.getNombreLote().toUpperCase()+".xlsx");
        
    }
    
    
    
    
    
    public void crearExcel2(LoteVO lote, String[] columnas, List<DataExcel> datosImagen) throws IOException, InvalidFormatException {
   	 // Create a Workbook
       Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

       /* CreationHelper helps us create instances of various things like DataFormat, 
          Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
       CreationHelper createHelper = workbook.getCreationHelper();

       // Create a Sheet
       Sheet sheet = workbook.createSheet("Reporte Procesamiento Digitalización");

       // Create a Font for styling header cells
       Font headerFont = workbook.createFont();
       headerFont.setBold(true);
       headerFont.setFontHeightInPoints((short) 14);
       headerFont.setColor(IndexedColors.RED.getIndex());

       // Create a CellStyle with the font
       CellStyle headerCellStyle = workbook.createCellStyle();
       headerCellStyle.setFont(headerFont);

       // Create a Row
       Row headerRow = sheet.createRow(0);

       // Create cells
       for(int i = 0; i < columnas.length; i++) {
           Cell cell = headerRow.createCell(i);
           cell.setCellValue(columnas[i]);
           cell.setCellStyle(headerCellStyle);
       }

       // Create Cell Style for formatting Date
       CellStyle dateCellStyle = workbook.createCellStyle();
       dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

       // Create Other rows and cells with employees data
       int rowNum = 1;
       for(DataExcel datos: datosImagen) {
           Row row = sheet.createRow(rowNum++);
           //
           if(datos.getNombredeimagen()==null) {
           	 row.createCell(0)
        		.setCellValue("");
           }else {
           	 row.createCell(0)
        		.setCellValue(datos.getNombredeimagen());
           }
          
           //
           if(datos.getPlaca()==null) {
           	 row.createCell(1)
            	.setCellValue("");
           }else {
           	 row.createCell(1)
            	.setCellValue(datos.getPlaca());
           }
          
           
           //
           if(datos.getNumerodelicencia()==null) {
           	row.createCell(2)
               .setCellValue("");
           }else {
           	row.createCell(2)
               .setCellValue(datos.getNumerodelicencia());
           }
           
           
           //
           if(datos.getTipodelicencia()==null) {
           	row.createCell(3)
           	.setCellValue("");
           }else {
           	row.createCell(3)
           	.setCellValue(datos.getTipodelicencia());
           }
           
           
           //
           if(datos.getLicenciaexpedidaen()==null) {
           	row.createCell(4)
       		.setCellValue("");
           }else {
           	row.createCell(4)
       		.setCellValue(datos.getLicenciaexpedidaen());
           }
           
           
           //
           if(datos.getPlacaexpedidaen()==null) {
           	row.createCell(5)
        		.setCellValue("");
           }else {
           	row.createCell(5)
        		.setCellValue(datos.getPlacaexpedidaen());
           }
                   
           //
           if(datos.getArticulo()==null) {
           	row.createCell(6)
       		.setCellValue("");
           }else {
           	row.createCell(6)
       		.setCellValue(datos.getArticulo());
           }
                      
           //
           if(datos.getFraccion()==null) {
           	row.createCell(7)
       		.setCellValue("");
           }else {
           	row.createCell(7)
       		.setCellValue(datos.getFraccion());
           }
           
           
           //
           if(datos.getInciso()==null) {
           	row.createCell(8)
       		.setCellValue("");
           }else {
           	row.createCell(8)
       		.setCellValue(datos.getInciso());
           }
           
           //
           if(datos.getParrafo()==null) {
           	row.createCell(9)
       		.setCellValue("");
           }else {
           	row.createCell(9)
       		.setCellValue(datos.getParrafo());
           }
           
           if(datos.getFechadeinfraccion()==null) {
           	row.createCell(10)//Fecha infraccion
       		.setCellValue("");
           }else {
           	row.createCell(10)//Fecha infraccion
       		.setCellValue(datos.getFechadeinfraccion());
           }
           
           
          
           if(datos.getPlacadeloficial()==null) {
           	row.createCell(11)
       		.setCellValue("");
           }else {
           	row.createCell(11)
       		.setCellValue(datos.getPlacadeloficial());
           }
           
           
           if(datos.getUtdelegacion()==null) {
           	row.createCell(12)
           	.setCellValue("");
           }else {
           	row.createCell(12)
           	.setCellValue(datos.getUtdelegacion());
           }
           
           
           
           if(datos.getFolio()==null) {
           	row.createCell(13)
       		.setCellValue("");
           }else {
           	row.createCell(13)
       		.setCellValue(datos.getFolio());
           }
           
           
           //Estilo para fecha
//           Cell fechaInfraccion = row.createCell(12);
//           fechaInfraccion.setCellValue(datos.getFechaInfraccion());
//           fechaInfraccion.setCellStyle(dateCellStyle);
           if(datos.getIdlote()==null) {
           	row.createCell(14)
       		.setCellValue("");
           }else {
           	row.createCell(14)
       		.setCellValue(datos.getIdlote());
           }
           
           
           

       }

		// Resize all columns to fit the content size
       for(int i = 0; i < columnas.length; i++) {
           sheet.autoSizeColumn(i);
       }

       // Write the output to a file
       FileOutputStream fileOut = new FileOutputStream(lote.getRutaAlmacenamiento()+"Reporte_Digitalización_"+lote.getNombreLote().toUpperCase()+".xlsx");
       workbook.write(fileOut);
       fileOut.close();

       // Closing the workbook
       workbook.close();
       openExcel(lote.getRutaAlmacenamiento()+"Reporte_Digitalización_"+lote.getNombreLote().toUpperCase()+".xlsx");
       
   }
    
    
    
    public static ArrayList<ArrayList<String>> GetExcelTableInto2DArrayListString(String excelFile, boolean debug){

        ArrayList<ArrayList<String>> OUT = new ArrayList<ArrayList<String>>();  
            File myFile = new File(excelFile); 
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(myFile);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 

            // Finds the workbook instance for XLSX file 
            XSSFWorkbook myWorkBook = null;
            try {
                myWorkBook = new XSSFWorkbook (fis);
            } catch (IOException e) {
                e.printStackTrace();
            } 

            // Return first sheet from the XLSX workbook 
            XSSFSheet mySheet = myWorkBook.getSheetAt(0); 

            // Get iterator to all the rows in current sheet 
            Iterator<Row> rowIterator = mySheet.iterator(); 

            // Traversing over each row of XLSX file 
            int count=1;
            while (rowIterator.hasNext()) { 
                Row row = rowIterator.next();
                ArrayList<String> InnerArray = new ArrayList<String>() ;
                if(debug)System.out.print(count + ". \t");
            // For each row, iterate through each columns 
                Iterator<Cell> cellIterator = row.cellIterator(); 

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next(); 

                    switch (cell.getCellType()) { 
                    case Cell.CELL_TYPE_STRING: 
                        String c = cell.getStringCellValue();
                        if(debug)System.out.print(c + "\t");
                        InnerArray.add(c);
                        break; 
                    case Cell.CELL_TYPE_NUMERIC: 
                        int n = (int) cell.getNumericCellValue();
                        if(debug)System.out.print(n + "\t");
                        InnerArray.add(String.valueOf(n));
                        break; 
                    case Cell.CELL_TYPE_BOOLEAN:
                        boolean b = cell.getBooleanCellValue();
                        if(debug)System.out.print(b + "\t");
                        InnerArray.add(String.valueOf(b));
                    break; 
                    default : 
                        } 
                    }
                if(debug)System.out.println("");
                OUT.add(InnerArray);
                count++; 
                }

        return OUT;
    }
    
    private static void modifyExistingWorkbook() throws InvalidFormatException, IOException {
        // Obtain a workbook from the excel file
        Workbook workbook = WorkbookFactory.create(new File("C:\\SistemaDigitalizacion\\06-12-2018\\NEW_ALGORITMO_TEST03\\hola.xlsx"));

        ArrayList<ArrayList<String>> FilasExcel;
        
        FilasExcel=GetExcelTableInto2DArrayListString("C:\\SistemaDigitalizacion\\06-12-2018\\NEW_ALGORITMO_TEST03\\hola.xlsx",true);
        
        System.out.println("este tamaño de ");
        
//        // Get Sheet at index 0
//        Sheet sheet = workbook.getSheetAt(0);
//        
//        int rowCount = sheet.getLastRowNum();
//        System.out.println("hay filas: "+rowCount);
//
//        // Get Row at index 1
//        Row row = sheet.getRow(rowCount);
//        
//        // Get the Cell at index 2 from the above row
//        Cell cell = row.getCell(2);
//
//        // Create the cell if it doesn't exist
//        if (cell == null)
//            cell = row.createCell(2);
//
//        // Update the cell's value
//        cell.setCellType(CellType.STRING);
//        cell.setCellValue("Updated Value");
//
//        // Write the output to the file
//        FileOutputStream fileOut = new FileOutputStream("C:\\SistemaDigitalizacion\\06-12-2018\\NEW_ALGORITMO_TEST03\\hola2.xlsx");
//        workbook.write(fileOut);
//        fileOut.close();
//
//        // Closing the workbook
//        workbook.close();
    }
    
    public void openExcel(String path) {
    	try {
			Process p = 
				  Runtime.getRuntime()
				   .exec("rundll32 url.dll,FileProtocolHandler " + path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
    }
    
    
    
    
    public static void main(String[] args) throws IOException, InvalidFormatException {
//    	modifyExistingWorkbook();
    }
    
    
	

}
