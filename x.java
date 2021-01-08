package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.modelo.entidades.Categoria;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**@author Atziri Perez*/
public class ProductoDAO {
    private static final String SQL_INSERT ="insert into Producto values (default,?, ?, ?, ?, ?)"; 
    private static final String SQL_UPDATE ="update Producto set nombreProducto = ?, descripcionProducto = ?, precio = ?, existencia = ?, idCategoria = ? where idProducto  = ?"; 
    private static final String SQL_DELETE ="delete from Producto where idProducto = ?";  
    private static final String SQL_READ ="select * from Producto where idProducto = ?"; 
    private static final String SQL_READ_CAT ="select * from Producto where idCategoria = ?"; 
    private static final String SQL_READ_ALL ="select * from Producto"; 
    
    
    private Connection con;
    
    public Connection obtenerConexion(){
        String usr = "iyiqhsmhtpakpc";
        String pwd = "1b3bfea7a51bbb7196a4dcbc554109b9526caf55685d0953a83ca2b8ed61a5b9";
        String driver = "org.postgresql.Driver";
        String url ="jdbc:postgresql://ec2-54-205-26-79.compute-1.amazonaws.com:5432/d4ifmdscgbpo2?sslmode=require";       
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
//    private void obtenerConexion(){
//        Context initialContext;
//        Context environmentContext;
//        String dsName="jdbc/ProyectoMVC";
//        try{
//            initialContext = new InitialContext();
//            environmentContext = (Context) initialContext.lookup("java:comp/env");
//            DataSource ds = (DataSource) environmentContext.lookup(dsName);
//            con = ds.getConnection();
//        } catch(NamingException | SQLException ex){
//            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE,null,ex);
//        }
//    }
        
    public void create(ProductoDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
       try{
           cs = con.prepareCall(SQL_INSERT);
           cs.setString(1, dto.getEntidad().getNombreProducto());
           cs.setString(2, dto.getEntidad().getDescripcionProducto());
           cs.setFloat(3, dto.getEntidad().getPrecio());
           cs.setInt(4, dto.getEntidad().getExistencia());
           cs.setInt(5, dto.getEntidad().getIdCategoria());
           cs.executeUpdate();
       } finally {
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close();
       }
    }  
    
    public void update(ProductoDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
       try{
           cs = con.prepareCall(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getNombreProducto());
           cs.setString(2, dto.getEntidad().getDescripcionProducto());
           cs.setFloat(3, dto.getEntidad().getPrecio());
           cs.setInt(4, dto.getEntidad().getExistencia());
           cs.setInt(5, dto.getEntidad().getIdCategoria());
           cs.setInt(6, dto.getEntidad().getIdProducto());
           cs.executeUpdate();
       } finally {
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close();
       }
    }
    
    public void delete(ProductoDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
       try{
           cs = con.prepareCall(SQL_DELETE);
           cs.setInt(1, dto.getEntidad().getIdProducto());
           cs.executeUpdate();
       } finally {
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close();
       }
    }  
    

    public ProductoDTO read(ProductoDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdProducto());
            rs = cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            if(Resultados.size() > 0){
                return (ProductoDTO) Resultados.get(0);
            } else {
                return null;
            }
        } finally{
            if (rs!=null)
                rs.close();
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close(); 
        }
    }

    public List readCat(ProductoDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_READ_CAT);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
            rs =cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            System.out.println(Resultados.size());
            if(Resultados.size() > 0){
                return Resultados;
            } else {
                return null;
            }
        } finally{
            if (rs!=null)
                rs.close();
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close(); 
        }
    }
    
    
    public List readAll() throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_READ_ALL);
            rs =cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            System.out.println(Resultados.size());
            if(Resultados.size() > 0){
                return Resultados;
            } else {
                return null;
            }
        } finally{
            if (rs!=null)
                rs.close();
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close(); 
        }
    }
    
    private List obtenerResultados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        while(rs.next()){
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setIdProducto(rs.getInt("idProducto"));
            dto.getEntidad().setNombreProducto(rs.getString("nombreProducto"));
            dto.getEntidad().setDescripcionProducto(rs.getString("descripcionProducto"));
            dto.getEntidad().setPrecio(rs.getFloat("precio"));
            dto.getEntidad().setExistencia(rs.getInt("existencia"));
            dto.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            resultados.add(dto);
        }
        return resultados;
    }
   
//    public static void main(String[] args){
//        ProductoDAO dao = new ProductoDAO();
//        ProductoDTO dto = new ProductoDTO();
//        dto.getEntidad().setIdProducto(12);
//        dto.getEntidad().setNombreProducto("Love your glasses");
//        dto.getEntidad().setDescripcionProducto("Russian Red");
//        dto.getEntidad().setPrecio(2100);
//        dto.getEntidad().setExistencia(50);
//        dto.getEntidad().setIdCategoria(5);
//        try {
//            dao.update(dto);
//            //System.out.println(dao.readAll());
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //System.out.println(dto.toString());
//    }
}
