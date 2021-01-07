package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.GraficaDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Atziri Perez
 */
public class GraficaDAO {
    private static final String SQL_GRAFICA_NUMCAT = "select c.nombrecategoria as Categoria, count (*) as Productos from Producto p, Categoria c where c.idCategoria = p.idCategoria group by c.idCategoria";
    private static final String SQL_GRAFICA_AVG_PRECIO = "select c.nombrecategoria as Categoria, avg(p.precio) as PrecioProm from Producto p, Categoria c where c.idCategoria = p.idCategoria group by c.idCategoria";
    private static final String SQL_GRAFICA_INVENTARIO = "select c.nombrecategoria as Categoria, sum(p.existencia) as Elementos from Producto p, Categoria c where c.idCategoria = p.idCategoria group by c.idCategoria";
    private static final String SQL_GRAFICA_P_EXISTENCIA = "select nombreProducto as Producto, existencia as Existencia from producto where idcategoria = ?";
    private static final String SQL_GRAFICA_P_PRECIO = "select nombreProducto as Producto, precio as Precio from producto where idcategoria = ?";


     private Connection con;
    
    private void obtenerConexion(){
        String usr = "iyiqhsmhtpakpc";
        String pwd = "1b3bfea7a51bbb7196a4dcbc554109b9526caf55685d0953a83ca2b8ed61a5b9";
        String driver = "org.postgresql.Driver";
        String url ="jdbc:postgresql://ec2-54-205-26-79.compute-1.amazonaws.com:5432/d4ifmdscgbpo2?sslmode=require";       
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GraficaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List graficaNumCat() throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List resultados = new ArrayList();
        try{
            ps = con.prepareStatement(SQL_GRAFICA_NUMCAT);
            rs = ps.executeQuery();
            while(rs.next()){
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(rs.getString("Categoria"));
                dto.setCantidad(rs.getInt("Productos"));
                resultados.add(dto);
            }
        }finally{
            if (rs!=null)
                rs.close();
            if (ps!=null)
                ps.close();
            if(con!=null)
                con.close(); 
        }
        return resultados;
    }
    
    public List graficaAvgPrecio() throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List resultados = new ArrayList();
        try{
            ps = con.prepareStatement(SQL_GRAFICA_AVG_PRECIO);
            rs = ps.executeQuery();
            while(rs.next()){
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(rs.getString("Categoria"));
                dto.setCantidad(rs.getInt("PrecioProm"));
                resultados.add(dto);
            }
        }finally{
            if (rs!=null)
                rs.close();
            if (ps!=null)
                ps.close();
            if(con!=null)
                con.close(); 
        }
        return resultados;
    }
    
    public List graficaInventario() throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List resultados = new ArrayList();
        try{
            ps = con.prepareStatement(SQL_GRAFICA_INVENTARIO);
            rs = ps.executeQuery();
            while(rs.next()){
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(rs.getString("Categoria"));
                dto.setCantidad(rs.getInt("Elementos"));
                resultados.add(dto);
            }
        }finally{
            if (rs!=null)
                rs.close();
            if (ps!=null)
                ps.close();
            if(con!=null)
                con.close(); 
        }
        return resultados;
    }
    
    public List graficaPExistencia(CategoriaDTO dtoC) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List resultados = new ArrayList();
        try{
            ps = con.prepareStatement(SQL_GRAFICA_P_EXISTENCIA);
            ps.setInt(1, dtoC.getEntidad().getIdCategoria());
            rs = ps.executeQuery();
            while(rs.next()){
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(rs.getString("Producto"));
                dto.setCantidad(rs.getInt("Existencia"));
                resultados.add(dto);
            }
        }finally{
            if (rs!=null)
                rs.close();
            if (ps!=null)
                ps.close();
            if(con!=null)
                con.close(); 
        }
        return resultados;
    }
    
    public List graficaPPrecio(CategoriaDTO dtoC) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List resultados = new ArrayList();
        try{
            ps = con.prepareStatement(SQL_GRAFICA_P_PRECIO);
            ps.setInt(1, dtoC.getEntidad().getIdCategoria());
            rs = ps.executeQuery();
            while(rs.next()){
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(rs.getString("Producto"));
                dto.setCantidad(rs.getInt("Precio"));
                resultados.add(dto);
            }
        }finally{
            if (rs!=null)
                rs.close();
            if (ps!=null)
                ps.close();
            if(con!=null)
                con.close(); 
        }
        return resultados;
    }
    
//    public static void main(String[] args) {
//        GraficaDAO dao = new GraficaDAO();
//        GraficaDTO dto = new GraficaDTO();       
//        try {
//            List res = dao.graficaInventario();
//            System.out.println(res);
//        } catch (SQLException ex) {
//            Logger.getLogger(GraficaDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
}
