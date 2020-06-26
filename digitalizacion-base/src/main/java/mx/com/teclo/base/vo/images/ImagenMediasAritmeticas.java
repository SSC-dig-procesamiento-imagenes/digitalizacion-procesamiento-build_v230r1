package mx.com.teclo.base.vo.images;

import java.util.ArrayList;


public class ImagenMediasAritmeticas {

	private int listDpi_height;
	private int listDpi_width;
	private int listHeight;
	private int listWidth;
	private int listColorType;
	
	
	public ImagenMediasAritmeticas() {
		super();
	}


	public ImagenMediasAritmeticas(int listDpi_height, int listDpi_width, int listHeight, int listWidth,
			int listColorType) {
		super();
		this.listDpi_height = listDpi_height;
		this.listDpi_width = listDpi_width;
		this.listHeight = listHeight;
		this.listWidth = listWidth;
		this.listColorType = listColorType;
	}


	public int getListDpi_height() {
		return listDpi_height;
	}


	public void setListDpi_height(int listDpi_height) {
		this.listDpi_height = listDpi_height;
	}


	public int getListDpi_width() {
		return listDpi_width;
	}


	public void setListDpi_width(int listDpi_width) {
		this.listDpi_width = listDpi_width;
	}


	public int getListHeight() {
		return listHeight;
	}


	public void setListHeight(int listHeight) {
		this.listHeight = listHeight;
	}


	public int getListWidth() {
		return listWidth;
	}


	public void setListWidth(int listWidth) {
		this.listWidth = listWidth;
	}


	public int getListColorType() {
		return listColorType;
	}


	public void setListColorType(int listColorType) {
		this.listColorType = listColorType;
	}


	
	
	
	
}
