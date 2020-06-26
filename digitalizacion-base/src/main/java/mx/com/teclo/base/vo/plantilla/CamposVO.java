/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.base.vo.plantilla;

import java.util.List;

/**
 *
 * @author JuanMa
 */
public class CamposVO {
    private Long id;
    private String nombre;
    private int coordenadaiy;
    private int coordenadaix;
    private int coordenadafy;
    private int coordenadafx;
    private Long orientaciones_id;
    private Long plantillas_id;
    private List<PlantillaRespuestaVO> plantillarespuesta;

    public CamposVO() {
    }

	public CamposVO(Long id, String nombre, int coordenadaiy, int coordenadaix, int coordenadafy, int coordenadafx,
			Long orientaciones_id, Long plantillas_id, List<PlantillaRespuestaVO> plantillarespuesta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.coordenadaiy = coordenadaiy;
		this.coordenadaix = coordenadaix;
		this.coordenadafy = coordenadafy;
		this.coordenadafx = coordenadafx;
		this.orientaciones_id = orientaciones_id;
		this.plantillas_id = plantillas_id;
		this.plantillarespuesta = plantillarespuesta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCoordenadaiy() {
		return coordenadaiy;
	}

	public void setCoordenadaiy(int coordenadaiy) {
		this.coordenadaiy = coordenadaiy;
	}

	public int getCoordenadaix() {
		return coordenadaix;
	}

	public void setCoordenadaix(int coordenadaix) {
		this.coordenadaix = coordenadaix;
	}

	public int getCoordenadafy() {
		return coordenadafy;
	}

	public void setCoordenadafy(int coordenadafy) {
		this.coordenadafy = coordenadafy;
	}

	public int getCoordenadafx() {
		return coordenadafx;
	}

	public void setCoordenadafx(int coordenadafx) {
		this.coordenadafx = coordenadafx;
	}

	public Long getOrientaciones_id() {
		return orientaciones_id;
	}

	public void setOrientaciones_id(Long orientaciones_id) {
		this.orientaciones_id = orientaciones_id;
	}

	public Long getPlantillas_id() {
		return plantillas_id;
	}

	public void setPlantillas_id(Long plantillas_id) {
		this.plantillas_id = plantillas_id;
	}

	public List<PlantillaRespuestaVO> getPlantillarespuesta() {
		return plantillarespuesta;
	}

	public void setPlantillarespuesta(List<PlantillaRespuestaVO> plantillarespuesta) {
		this.plantillarespuesta = plantillarespuesta;
	}

    
        
}
