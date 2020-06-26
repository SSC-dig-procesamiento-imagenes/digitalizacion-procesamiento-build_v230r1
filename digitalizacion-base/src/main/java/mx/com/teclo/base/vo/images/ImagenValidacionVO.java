package mx.com.teclo.base.vo.images;

public class ImagenValidacionVO {

	private String cdvalidacion;
	private String descripcion;
	
	public ImagenValidacionVO() {
		super();
	}
	public ImagenValidacionVO(String cdvalidacion, String descripcion) {
		super();
		this.cdvalidacion = cdvalidacion;
		this.descripcion = descripcion;
	}
	public String getCdvalidacion() {
		return cdvalidacion;
	}
	public void setCdvalidacion(String cdvalidacion) {
		this.cdvalidacion = cdvalidacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
