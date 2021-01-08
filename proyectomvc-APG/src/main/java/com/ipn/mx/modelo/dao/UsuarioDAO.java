package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.UsuarioDTO;
import java.sql.CallableStatement;
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
public class UsuarioDAO {

    public static final String SQL_INSERT = "insert into Usuario values (default,? , ?, ? , ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE = "update Usuario set nombre = ?, paterno = ?, materno = ?, email = ?, nombreUsuario = ?, claveUsuario = ?, tipoUsuario = ?, imagen = ? where idUsuario  = ?";
    public static final String SQL_DELETE = "delete from Usuario where idUsuario = ?";
    public static final String SQL_READ = "select * from Usuario where idUsuario = ?";
    public static final String SQL_READ_ALL = "SELECT *FROM Usuario;";
    public static final String SQL_SIGN_IN = "SELECT * FROM Usuario WHERE nombreUsuario = ? and claveUsuario = ?;";

    private Connection con;


//    public Connection obtenerConexion(){
//        String usr = "postgres";
//        String pwd = "password";
//        String driver = "org.postgresql.Driver";
//        String url ="jdbc:postgresql://localhost:5432/proyectoWAD";       

//    public Connection obtenerConexion(){
//        String usr = "postgres";
//        String pwd = "12345fyy>";
//        String driver = "org.postgresql.Driver";
//        String url ="jdbc:postgresql://localhost:5432/proyectoWAD?sslmode=require";       
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url, usr, pwd);
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(UsuarioDTO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return con;
//    }
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
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void create(UsuarioDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getNombre());
            cs.setString(2, dto.getEntidad().getPaterno());
            cs.setString(3, dto.getEntidad().getMaterno());
            cs.setString(4, dto.getEntidad().getEmail());
            cs.setString(5, dto.getEntidad().getNombreUsuario());
            cs.setString(6, dto.getEntidad().getClaveUsuario());
            cs.setString(7, dto.getEntidad().getTipoUsuario());
            cs.setString(8, dto.getEntidad().getImagen());
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

    public void update(UsuarioDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getNombre());
            cs.setString(2, dto.getEntidad().getPaterno());
            cs.setString(3, dto.getEntidad().getMaterno());
            cs.setString(4, dto.getEntidad().getEmail());
            cs.setString(5, dto.getEntidad().getNombreUsuario());
            cs.setString(6, dto.getEntidad().getClaveUsuario());
            cs.setString(7, dto.getEntidad().getTipoUsuario());
            cs.setString(8, dto.getEntidad().getImagen());
            cs.setInt(9, dto.getEntidad().getIdUsuario());
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

    public void delete(UsuarioDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdUsuario());
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

    public UsuarioDTO read(UsuarioDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = con.prepareCall(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdUsuario());
            rs = cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            if (Resultados.size() > 0) {
                return (UsuarioDTO) Resultados.get(0);
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
            UsuarioDTO dto = new UsuarioDTO();
            dto.getEntidad().setIdUsuario(rs.getInt("idUsuario"));
            dto.getEntidad().setNombre(rs.getString("nombre"));
            dto.getEntidad().setPaterno(rs.getString("paterno"));
            dto.getEntidad().setMaterno(rs.getString("materno"));
            dto.getEntidad().setEmail(rs.getString("email"));
            dto.getEntidad().setNombreUsuario(rs.getString("nombreUsuario"));
            dto.getEntidad().setClaveUsuario(rs.getString("claveUsuario"));
            dto.getEntidad().setTipoUsuario(rs.getString("tipoUsuario"));
            dto.getEntidad().setImagen(rs.getString("imagen"));
            resultados.add(dto);
        }
        return resultados;
    }

    public UsuarioDTO verify(UsuarioDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = con.prepareCall(SQL_SIGN_IN);
            cs.setString(1, dto.getEntidad().getNombreUsuario());
            cs.setString(2, dto.getEntidad().getClaveUsuario());
            rs = cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            if (Resultados.size() > 0) {
                return (UsuarioDTO) Resultados.get(0);
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

    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        try {
            //dao.create(dto);
            System.out.println(dao.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
