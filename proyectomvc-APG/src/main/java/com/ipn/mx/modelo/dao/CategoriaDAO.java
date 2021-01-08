package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * @author Atziri Perez y el team
 */
public class CategoriaDAO { 
//    public static final String SQL_INSERT ="call spInsertarCategoria(?,?)"; 
//    public static final String SQL_UPDATE ="call spActualizarCategoria(?,?,?)"; 
//    public static final String SQL_DELETE ="call spEliminarCategoria(?)";  
//    public static final String SQL_READ ="call spSeleccionarCategoria(?)"; 
//    public static final String SQL_READ_ALL ="call spSeleccionarTodo()"; 
    public static final String SQL_INSERT ="insert into Categoria values (default,? , ?)"; 
    public static final String SQL_UPDATE ="update Categoria set nombreCategoria = ?, descripcionCategoria = ? where idCategoria  = ?"; 
    public static final String SQL_DELETE ="delete from Categoria where idCategoria = ?";  
    public static final String SQL_READ ="select * from Categoria where idCategoria = ?"; 
    public static final String SQL_READ_ALL ="SELECT idcategoria, nombrecategoria, descripcioncategoria FROM categoria;"; 
    
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
            Logger.getLogger(CategoriaDTO.class.getName()).log(Level.SEVERE, null, ex);
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
//            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE,null,ex);
//        }
//    }
        
    public void create(CategoriaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
       try{
           cs = con.prepareCall(SQL_INSERT);
           cs.setString(1, dto.getEntidad().getNombreCategoria());
           cs.setString(2, dto.getEntidad().getDescripcionCategoria());
           cs.executeUpdate();
       } finally {
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close();
       }
    }  
    
    public void update(CategoriaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
       try{
           cs = con.prepareCall(SQL_UPDATE);
           cs.setString(1, dto.getEntidad().getNombreCategoria());
           cs.setString(2, dto.getEntidad().getDescripcionCategoria());
           cs.setInt(3, dto.getEntidad().getIdCategoria());
           cs.executeUpdate();
       } finally {
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close();
       }
    }
    
    public void delete(CategoriaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
       try{
           cs = con.prepareCall(SQL_DELETE);
           cs.setInt(1, dto.getEntidad().getIdCategoria());
           cs.executeUpdate();
       } finally {
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close();
       }
    }  
    

    public CategoriaDTO read(CategoriaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
            rs = cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            if(Resultados.size() > 0){
                return (CategoriaDTO) Resultados.get(0);
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
            CategoriaDTO dto = new CategoriaDTO();
            dto.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            dto.getEntidad().setNombreCategoria(rs.getString("nombreCategoria"));
            dto.getEntidad().setDescripcionCategoria(rs.getString("descripcionCategoria"));
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
            Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
