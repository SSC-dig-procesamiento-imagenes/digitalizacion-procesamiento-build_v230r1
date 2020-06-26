package mx.com.teclo.base.vo.images;

import javax.sql.rowset.serial.SerialBlob;

public class ImagenBlobPersistenciaVO {

//		private Long idImagen;
	    private String nombreImagen;
	 	private byte[] lbImagen;//Serializable
	 	private String codigoImagen;
//	    private Long nuImgDepend;
		public ImagenBlobPersistenciaVO() {
			super();
		}
public ImagenBlobPersistenciaVO(String nombreImagen, byte[] lbImagen, String codigoImagen) {
	super();
	this.nombreImagen = nombreImagen;
	this.lbImagen = lbImagen;
	this.codigoImagen = codigoImagen;
}
public String getNombreImagen() {
	return nombreImagen;
}
public void setNombreImagen(String nombreImagen) {
	this.nombreImagen = nombreImagen;
}
public byte[] getLbImagen() {
	return lbImagen;
}
public void setLbImagen(byte[] lbImagen) {
	this.lbImagen = lbImagen;
}
public String getCodigoImagen() {
	return codigoImagen;
}
public void setCodigoImagen(String codigoImagen) {
	this.codigoImagen = codigoImagen;
}
		
		
	    
	    
}
