/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.base.vo.plantilla;

import java.util.List;

/**
 *
 * @author Gibran Garcia
 */
public class PlantillaVO {
    
    private Long id;
    private String nombre;
    private Long algoritmo_ajuste;
    private AlveoloVO alveolo;
    private List<CamposVO> campos;

    public PlantillaVO() {
    }

    public PlantillaVO(Long id, String nombre, Long algoritmo_ajuste, AlveoloVO alveolo, List<CamposVO> campos) {
        this.id = id;
        this.nombre = nombre;
        this.algoritmo_ajuste = algoritmo_ajuste;
        this.alveolo = alveolo;
        this.campos = campos;
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

    public Long getAlgoritmo_ajuste() {
        return algoritmo_ajuste;
    }

    public void setAlgoritmo_ajuste(Long algoritmo_ajuste) {
        this.algoritmo_ajuste = algoritmo_ajuste;
    }

    public AlveoloVO getAlveolo() {
        return alveolo;
    }

    public void setAlveolo(AlveoloVO alveolo) {
        this.alveolo = alveolo;
    }

    public List<CamposVO> getCampos() {
        return campos;
    }

    public void setCampos(List<CamposVO> campos) {
        this.campos = campos;
    }
        
   
    
}
