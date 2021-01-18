/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;
import com.ipn.mx.modelo.dao.GeneroDAO;
import com.ipn.mx.modelo.dto.GeneroDTO;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author Atziri Perez
 */
@ManagedBean(name = "GeneroMB")
@SessionScoped
public class GeneroMB extends BaseBean implements Serializable{
    private final GeneroDAO dao = new GeneroDAO();
    private GeneroDTO dto;
    private List<GeneroDTO> listaGeneros;
    
    /**
     * Creates a new instance of GeneroMB
     */
    public GeneroMB() {
    }
    
    @PostConstruct
    public void init(){
        listaGeneros = new ArrayList<>();
        listaGeneros = dao.readAll();
    }
    
    public String prepareAdd(){
        dto = new GeneroDTO();
        setAccion(ACC_CREAR);
        return "/categoria/categoriaForm?faces-redirect=true";
    }
    
    public String prepareUpdate(){
        setAccion(ACC_ACTUALIZAR);
        return "/categoria/categoriaForm?faces-redirect=true";
    }
    
    public String prepareIndex(){
        init();
        return "/categoria/listadoGeneros?faces-redirect=true";
    }
    
    public String back(){
        return prepareIndex();
    }
    
    public boolean validate(){
        boolean valido = true;
        //las validaciones
        return valido;
    }
    
    public String add(){
        boolean valido = validate();
        if (valido) {
            dao.create(dto);
            return prepareIndex();
        } else{
            return prepareAdd();
        }
    }
    
    public String update(){
        boolean valido = validate();
        if (valido) {
            dao.update(dto);
            return prepareIndex();
        } else{
            return prepareUpdate();
        }
    }
    
    public String delete(){
        dao.delete(dto);
        return prepareIndex();
    }
    
    public void seleccionarCateoria(ActionEvent event){
        String claveSel = (String) 
                FacesContext.getCurrentInstance().
                        getExternalContext().
                        getRequestParameterMap().get("claveSel");
        dto = new GeneroDTO();
        try {
            dao.read(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GeneroDTO getDto() {
        return dto;
    }

    public void setDto(GeneroDTO dto) {
        this.dto = dto;
    }

    public List<GeneroDTO> getListaGeneros() {
        return listaGeneros;
    }

    public void setListaGeneros(List<GeneroDTO> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }
 }
