/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.GeneroDTO;
import com.ipn.mx.modelo.entidades.Genero;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class GeneroDAO {

    public static final String SQL_INSERT = "insert into Genero values (default,? , ?)";
    public static final String SQL_UPDATE = "update Genero set nombreGenero = ?, descripcionGenero = ? where idGenero  = ?";
    public static final String SQL_DELETE = "delete from Genero where idGenero = ?";
    public static final String SQL_READ = "select * from Genero where idGenero = ?";
    public static final String SQL_READ_ALL = "SELECT idGenero, nombreGenero, descripcionGenero FROM Genero;";

    private Connection con;

    public Connection obtenerConexion() {
        String usr = "postgres";
        String pwd = "1234fyy>";
        String driver = "org.postgresql.Driver";
        //String driver = "com.mysql.jdbc.Driver";
        //String url = "jdbc:mysql://localhost:3306/3CM4";
        String url = "jdbc:postgresql://localhost:5432/proyectoWAD";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void create(GeneroDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getNombreGenero());
            cs.setString(2, dto.getEntidad().getDescripcionGenero());
            cs.executeUpdate();
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void update(GeneroDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getNombreGenero());
            cs.setString(2, dto.getEntidad().getDescripcionGenero());
            cs.setInt(3, dto.getEntidad().getIdGenero());
            cs.executeUpdate();
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void delete(GeneroDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdGenero());
            cs.executeUpdate();
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public CategoriaDTO read(GeneroDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = con.prepareCall(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdGenero());
            rs = cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            if (Resultados.size() > 0) {
                return (GeneroDTO) Resultados.get(0);
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List readAll() throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = con.prepareCall(SQL_READ_ALL);
            rs = cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            System.out.println(Resultados.size());
            if (Resultados.size() > 0) {
                return Resultados;
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        while (rs.next()) {
            GeneroDTO dto = new GeneroDTO();
            dto.getEntidad().setIdGenero(rs.getInt("idGenero"));
            dto.getEntidad().setNombreCategoria(rs.getString("nombreGenero"));
            dto.getEntidad().setDescripcionCategoria(rs.getString("descripcionGenero"));
            resultados.add(dto);
        }
        return resultados;
    }

    public static void main(String[] args) {

        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();

        try {
            List res = dao.readAll();
            System.out.println(res);
        } catch (SQLException ex) {
            Logger.getLogger(Genero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
