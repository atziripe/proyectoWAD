package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Producto;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Atziri Perez
 */
@Data
@AllArgsConstructor
public class ProductoDTO implements Serializable {
    private Producto entidad;
    
    public ProductoDTO(){
        entidad = new Producto();
    }
}
