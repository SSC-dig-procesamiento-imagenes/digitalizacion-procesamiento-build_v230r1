package mx.com.teclo.base.vo.resolucion;

public class ResolucionVO {
	
	private Long idresolucion;
	private int valor;
	private String nombre;
	private double width;
	private double height;
	public ResolucionVO(Long idresolucion, int valor, String nombre, double width, double height) {
		super();
		this.idresolucion = idresolucion;
		this.valor = valor;
		this.nombre = nombre;
		this.width = width;
		this.height = height;
	}
	public ResolucionVO() {
		super();
	}
	public Long getIdresolucion() {
		return idresolucion;
	}
	public void setIdresolucion(Long idresolucion) {
		this.idresolucion = idresolucion;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
	
}
