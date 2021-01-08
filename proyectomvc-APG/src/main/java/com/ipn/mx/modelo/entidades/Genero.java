package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Atziri Perez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genero implements Serializable{
    private int idGenero;
    private String nombreGenero;
    private String descripcionGenero;
}

