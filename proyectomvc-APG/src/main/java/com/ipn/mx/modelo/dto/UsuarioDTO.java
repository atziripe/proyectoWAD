/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Atziri Perez
 */
@Data
@AllArgsConstructor
public class UsuarioDTO {
    private Usuario entidad;
    
    public UsuarioDTO(){
        entidad = new Usuario();
    }
}
