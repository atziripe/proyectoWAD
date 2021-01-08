package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "Pelicula",schema = "public")
public class Pelicula implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

