/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.base.vo.plantilla;

/**
 *
 * @author JuanMa
 */
public class CaracteresVO {
    
    private Long id;
    private String caracter;

    public CaracteresVO() {
    }

    public CaracteresVO(Long id, String caracter) {
        this.id = id;
        this.caracter = caracter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }
    
    
}
