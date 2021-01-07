/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;
import java.io.Serializable;
import java.sql.Date;


/**
 *
 * @author darkdestiny
 */
public class Evento implements Serializable{
    private int idEvento;
    private String nombreEvento;
    private String sede;
    private double duracion;
    private Date fechaInicio;
    private Date fechaTermino;
    
    public Evento(){}

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

   
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Evento{idEvento=").append(idEvento).append("\n");
        sb.append(", nombreEvento=").append(nombreEvento).append("\n");
        sb.append(", sede=").append(sede).append("\n");
        sb.append(", duracion=").append(duracion).append("\n");
        sb.append(", fechaInicio=").append(fechaInicio).append("\n");
        sb.append(", fechaTermino=").append(fechaTermino).append("\n");
        sb.append('}');
        return sb.toString();
    }
  
}
