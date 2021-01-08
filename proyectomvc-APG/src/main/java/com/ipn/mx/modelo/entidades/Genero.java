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
@Table(name = "Genero",schema = "public")
public class Genero implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenero;
    private String nombreGenero;
    private String descripcionGenero;
}

