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
public class PlantillaRespuestaVO {
    
    private Long id;
    private String nombre;
    private String descripcion;
    private List<CaracteresVO> caracteres;

    public PlantillaRespuestaVO() {
    }

    public PlantillaRespuestaVO(Long id, String nombre, String descripcion, List<CaracteresVO> caracteres) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteres = caracteres;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<CaracteresVO> getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(List<CaracteresVO> caracteres) {
        this.caracteres = caracteres;
    }
    
    
    
}
