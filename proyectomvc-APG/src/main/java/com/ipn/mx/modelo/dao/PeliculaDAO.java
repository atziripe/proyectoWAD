package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.PeliculaDTO;
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
public class PeliculaDAO {
    private static final String SQL_INSERT ="insert into Pelicula values (default,?, ?, ?, ?, ?, ?, ?, ?)"; 
    private static final String SQL_UPDATE ="update Pelicula set nombrePelicula = ?, sinopsis = ?, clasificacion = ?, anio = ?, idGenero = ?, duracion = ?, director = ?, votosPositivos = ?, votosNegativos = ?, link = ? where idPelicula  = ?"; 
    private static final String SQL_DELETE ="delete from Pelicula where idPelicula = ?";  
    private static final String SQL_READ ="select * from Pelicula where idPelicula = ?"; 
    private static final String SQL_READ_CAT ="select * from Pelicula where idCategoria = ?"; 
    private static final String SQL_READ_ALL ="select * from Pelicula"; 
    
    
    private Connection con;
    
    public Connection obtenerConexion(){
        String usr = "postgres";
        String pwd = "1234";
        String driver = "org.postgresql.Driver";
        String url ="jdbc:postgresql://localhost:5432/proyectoWAD";    
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PeliculaDTO.class.getName()).log(Level.SEVERE, null, ex);
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
//            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE,null,ex);
//        }
//    }
        
    public void create(PeliculaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
       try{
            cs = con.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getNombrePelicula());
            cs.setString(2, dto.getEntidad().getSinopsis());
            cs.setString(3, dto.getEntidad().getClasificacion());
            cs.setInt(4, dto.getEntidad().getAnio());
            cs.setInt(5, dto.getEntidad().getIdGenero());
            cs.setFloat(6, dto.getEntidad().getDuracion());
            cs.setString(7, dto.getEntidad().getDirector());
            cs.setInt(8, dto.getEntidad().getVotosPositivos());
            cs.setInt(9, dto.getEntidad().getVotosNegativos());
            cs.setString(9, dto.getEntidad().getLink());
           cs.executeUpdate();
       } finally {
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close();
       }
    }  
    
    public void update(PeliculaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
       try{
            cs = con.prepareCall(SQL_INSERT);
           cs.setString(1, dto.getEntidad().getNombrePelicula());
           cs.setString(2, dto.getEntidad().getSinopsis());
           cs.setString(3, dto.getEntidad().getClasificacion());
           cs.setInt(4, dto.getEntidad().getAnio());
           cs.setInt(5, dto.getEntidad().getIdGenero());
		   cs.setFloat(6, dto.getEntidad().getDuracion());
		   cs.setString(7, dto.getEntidad().getDirector());
		   cs.setInt(8, dto.getEntidad().getVotosPositivos());
		   cs.setInt(9, dto.getEntidad().getVotosNegativos());
		   cs.setString(9, dto.getEntidad().getLink());
           cs.executeUpdate();
       } finally {
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close();
       }
    }
    
    public void delete(PeliculaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
       try{
           cs = con.prepareCall(SQL_DELETE);
           cs.setInt(1, dto.getEntidad().getIdPelicula());
           cs.executeUpdate();
       } finally {
            if (cs!=null)
                cs.close();
            if(con!=null)
                con.close();
       }
    }  
    

    public PeliculaDTO read(PeliculaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdPelicula());
            rs = cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            if(Resultados.size() > 0){
                return (PeliculaDTO) Resultados.get(0);
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

    public List readCat(PeliculaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = con.prepareCall(SQL_READ_CAT);
            cs.setInt(1, dto.getEntidad().getIdGenero());
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
            PeliculaDTO dto = new PeliculaDTO();
            dto.getEntidad().setIdPelicula(rs.getInt("idPelicula"));
            dto.getEntidad().setNombrePelicula(rs.getString("nombrePelicula"));
            dto.getEntidad().setSinopsis(rs.getString("sinopsis"));
            dto.getEntidad().setClasificacion(rs.getString("clasificacion"));
            dto.getEntidad().setAnio(rs.getInt("anio"));
			dto.getEntidad().setDuracion(rs.getInt("duracion"));
			dto.getEntidad().setDirector(rs.getString("director"));
			dto.getEntidad().setVotosPositivos(rs.getInt("votosPositivos"));
			dto.getEntidad().setVotosNegativos(rs.getInt("votosNegativos"));
			dto.getEntidad().setLink(rs.getString("link"));
            resultados.add(dto);
        }
        return resultados;
    }
   
//    public static void main(String[] args){
//        PeliculaDAO dao = new PeliculaDAO();
//        PeliculaDTO dto = new PeliculaDTO();
//        dto.getEntidad().setPelicula(12);
//        dto.getEntidad().setNombrePelicula("Love your glasses");
//        dto.getEntidad().setDescripcionPelicula("Russian Red");
//        dto.getEntidad().setPrecio(2100);
//        dto.getEntidad().setExistencia(50);
//        dto.getEntidad().setIdCategoria(5);
//        try {
//            dao.update(dto);
//            //System.out.println(dao.readAll());
//        } catch (SQLException ex) {
//            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //System.out.println(dto.toString());
//    }
}