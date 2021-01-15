package com.ipn.mx.controlador.web;

import java.io.Serializable;
import lombok.Data;
/**
 *
 * @author Atziri Perez
 */
@Data
public class BaseBean implements Serializable {
    protected static final String ACC_CREAR = "CREAR";
    protected static final String ACC_ACTUALIZAR = "ACTUALIZAR";
    protected String accion;
    
    public boolean isModoCrear(){
        if(accion != null){
            return accion.equals(ACC_CREAR);
        }else{
            return false;
        }
    }
    
    public boolean isModoActualizar(){
        if(accion != null){
            return accion.equals(ACC_ACTUALIZAR);
        }else{
            return false;
        }
    }

}
