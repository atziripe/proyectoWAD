package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Categoria;
import java.io.Serializable;

/**
 *
 * @author Atziri Perez
 */
public class CategoriaDTO implements Serializable{
    private Categoria entidad;

    public CategoriaDTO() {
        entidad = new Categoria(); 
    }

    public Categoria getEntidad() {
        return entidad;
    }

    public void setEntidad(Categoria entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id Categoria: ").append(getEntidad().getIdCategoria()).append("\n");
        sb.append("nombre Categoria: ").append(getEntidad().getNombreCategoria()).append("\n");
        sb.append("descripcion Categoria: ").append(getEntidad().getDescripcionCategoria()).append("\n");
        return sb.toString();
    }
    
}
