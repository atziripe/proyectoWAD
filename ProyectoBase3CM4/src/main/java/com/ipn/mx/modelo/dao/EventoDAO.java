/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.Evento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Atziri Perez
 */
public class EventoDAO {
    public static final String SQL_INSERT ="insert into evento(nombreEvento, sede, duracion,fechaInicio,fechaTermino)" + " values(?, ?, ?, ?, ?)"; 
    public static final String SQL_UPDATE ="update evento set nombreEvento = ?, sede = ?, duracion = ?, fechaInicio = ?, fechaTermino = ? where idEvento = ?"; 
    public static final String SQL_DELETE ="delete from evento where idEvento = ?";  
    public static final String SQL_SELECT_ALL ="select * from evento"; 
    public static final String SQL_SELECT ="select * from evento where idEvento = ?"; 

    private Connection conexion = null;
    private void obtenerConexion(){
        String usr = "root";
        String pwd = "password";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url ="jdbc:mysql://localhost:3306/ProyectoBase3CM4?serverTimezone=America/Mexico_City&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";       
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create(Evento e)throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, e.getNombreEvento());
            ps.setString(2, e.getSede());
            ps.setDouble(3, e.getDuracion());
            ps.setDate(4, e.getFechaInicio());
            ps.setDate(5, e.getFechaTermino());
            ps.executeUpdate();
        } finally{
            if (ps!=null)
                ps.close();
            if(conexion!=null)
                conexion.close();
        }
    }
    public void update(Evento e) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, e.getNombreEvento());
            ps.setString(2, e.getSede());
            ps.setDouble(3, e.getDuracion());
            ps.setDate(4, e.getFechaInicio());
            ps.setDate(5, e.getFechaTermino());
            ps.setInt(6, e.getIdEvento());
            ps.executeUpdate();
        } finally{
            if (ps!=null)
                ps.close();
            if(conexion!=null)
                conexion.close();
        }
    }
     
    public void delete(Evento e) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, e.getIdEvento());
            ps.executeUpdate();
        } finally{
            if (ps!=null)
                ps.close();
            if(conexion!=null)
                conexion.close();
        }
    }
     
    public List readAll()throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conexion.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size() > 0)
                return resultados;
            else
                return null;
        }finally{
            if(rs!=null)
                rs.close();
            if(ps!=null)
                ps.close();
            if(conexion!= null)
                conexion.close();
        }
    }
            
    public Evento read(Evento e) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, e.getIdEvento());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size()>0)
                return (Evento)resultados.get(0);
            else
                return null;
        }finally{
            if(rs!=null)
                rs.close();
            if(ps!=null)
                ps.close();
            if(conexion!= null)
                conexion.close();
        }
    }
       
    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while(rs.next()){
            Evento e = new Evento();
            e.setIdEvento(rs.getInt("idEvento"));
            e.setNombreEvento(rs.getString("nombreEvento"));
            e.setSede(rs.getString("sede"));
            e.setDuracion(rs.getDouble("duracion"));
            e.setFechaInicio(rs.getDate("fechaInicio"));
            e.setFechaTermino(rs.getDate("fechaTermino"));
            resultados.add(e);
        }
        return resultados;
    }
    
    public static void main(String[] args) {
        Evento e = new Evento();
        e.setIdEvento(1);
        e.setNombreEvento("Hackaton");
        e.setSede("Auditorio ESCOM");
        e.setDuracion(5);
        e.setFechaInicio(Date.valueOf("2020-10-16"));
        e.setFechaTermino(Date.valueOf("2020-10-24"));  
        
        EventoDAO dao = new EventoDAO();
        try {
            dao.create(e);
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



