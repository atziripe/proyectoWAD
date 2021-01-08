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
public class Pelicula implements Serializable{
    private int idPelicula;
    private int idGenero;
    private String nombrePelicula;
    private String sinopsis;
    private String clasificacion;
    private int anio;
    private float duracion;
    private String director;
    private int votosPositivo;
    private int votosNegativos;
    private String link;
}

