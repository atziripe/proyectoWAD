package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Genero;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Atziri Perez
 */
@Data
@AllArgsConstructor
public class GeneroDTO implements Serializable {
    private Genero entidad;
    
    public GeneroDTO(){
        entidad = new Genero();
    }
}
