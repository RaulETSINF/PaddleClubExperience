/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import javafx.scene.image.Image;

/**
 *
 * @author RaulP
 */
public class Perfiles {
    private String nombre;
    private Image imagen;

    public Perfiles(String nombre, Image imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
    
}
