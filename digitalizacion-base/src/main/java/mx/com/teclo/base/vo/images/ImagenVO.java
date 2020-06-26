package mx.com.teclo.base.vo.images;


public class ImagenVO {

	private String nombre;
	private int status;
	private ImagenValidacionVO validacion;
	
	
	public ImagenVO() {
		super();
	}


	public ImagenVO(String nombre, int status, ImagenValidacionVO validacion) {
		super();
		this.nombre = nombre;
		this.status = status;
		this.validacion = validacion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public ImagenValidacionVO getValidacion() {
		return validacion;
	}


	public void setValidacion(ImagenValidacionVO validacion) {
		this.validacion = validacion;
	}

	
}
