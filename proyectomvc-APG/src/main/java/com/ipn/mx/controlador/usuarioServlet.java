/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.utilerias.Utilerias;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;


/**
 *
 * @author Atziri Perez
 */
@WebServlet(name = "usuarioServlet", urlPatterns = {"/usuarioServlet"})
@MultipartConfig(maxFileSize = 16177216)
public class usuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        if (accion.equals("usuarios")) {
            listaDeUsuarios(request, response);
        } else {
            if (accion.equals("nuevo")) {
                registrarUsuario(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarUsuario(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarUsuario(request, response);
                    } else {
                        if (accion.equals("guardar")) {
                            almacenarUsuario(request, response);
                        } else {
                            if (accion.equals("iniciar")) {
                                iniciarUsuario(request, response);
                            } /*else {
                                if (accion.equals("ingresar")) {
                                    ingresarUsuario(request, response);
                                } else {
                                    if (accion.equals("salir")) {
                                        cerrarSesion(request, response);
                                    } else {
                                        if (accion.equals("reporte")) {
                                            verPDF(request, response);
                                        } else {
                                            if (accion.equals("reportesolo")) {
                                                verPDFUsuario(request, response);
                                            }else{
                                                if(accion.equals("imagen")){
                                                    cargarImagen(request, response);
                                                }
                                            }
                                        }
                                    }
                                }
                            }*/
                        }
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listaDeUsuarios(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            List lista = dao.readAll();
            request.setAttribute("listaDeUsuarios", lista);
            RequestDispatcher rd = request.getRequestDispatcher("listaUsuarios.jsp");
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("registro.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        dao.delete(dto);
        listaDeUsuarios(request, response);
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("usuario", dto);
            RequestDispatcher vista = request.getRequestDispatcher("updateUser.jsp");
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        Utilerias mandarCorreo = new Utilerias();

        String destinatario = request.getParameter("email");
        String asunto = "";
        String texto = "";

        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            dto.getEntidad().setNombre(request.getParameter("name"));
            dto.getEntidad().setPaterno(request.getParameter("paterno"));
            dto.getEntidad().setMaterno(request.getParameter("materno"));
            dto.getEntidad().setEmail(request.getParameter("email"));
            dto.getEntidad().setNombreUsuario(request.getParameter("username"));
            dto.getEntidad().setClaveUsuario(request.getParameter("pass"));
            dto.getEntidad().setTipoUsuario(request.getParameter("tipo"));
            dto.getEntidad().setImagen(request.getParameter("imagen"));
            try {
                dao.create(dto);
                asunto = "¡GRACIAS POR REGISTRARTE!";
                texto = "Hola " + request.getParameter("name") + ", gracias por registrarte en mi práctica de WAD. Checala, esta chida.\n Username: " + request.getParameter("username") + "";
                RequestDispatcher vista = request.getRequestDispatcher("index.jsp");
                vista.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
            dto.getEntidad().setNombre(request.getParameter("name"));
            dto.getEntidad().setPaterno(request.getParameter("paterno"));
            dto.getEntidad().setMaterno(request.getParameter("materno"));
            dto.getEntidad().setEmail(request.getParameter("email"));
            dto.getEntidad().setNombreUsuario(request.getParameter("username"));
            dto.getEntidad().setClaveUsuario(request.getParameter("pass"));
            dto.getEntidad().setTipoUsuario(request.getParameter("tipo"));
            dto.getEntidad().setImagen(request.getParameter("imagen"));
            try {
                dao.update(dto);
                asunto = "¿Acabas de modificar tus datos?";
                texto = "Hola, los datos de cuenta en la practica de WAD acaban de ser actualizados. ¿Fuiste tú?";
                RequestDispatcher vista = request.getRequestDispatcher("inicioAdmin.jsp");
                vista.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        mandarCorreo.enviarCorreo(destinatario, asunto, texto);
    }

    private void iniciarUsuario(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void cargarImagen(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            String uploadPath = "C:\\Users\\atzir\\Documents\\9semestre\\WAD\\proyectomvc-APG\\src\\main\\webapp\\assets\\img";
//            for (Part p : request.getParts()) {
//                p.write(uploadPath + File.separator + getFileName(p));
//            }
//            response.sendRedirect("inicioAdmin.jsp");
//        } catch (IOException | ServletException ex) {
//            Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    private void ingresarUsuario(HttpServletRequest request, HttpServletResponse response) {
//        UsuarioDAO dao = new UsuarioDAO();
//        UsuarioDTO dto = new UsuarioDTO();
//        dto.getEntidad().setNombreUsuario(request.getParameter("username"));
//        dto.getEntidad().setClaveUsuario(request.getParameter("pass"));
//        HttpSession session = request.getSession();
//        try {
//            dto = dao.verify(dto);
//            if (dto != null) {
//                session.setAttribute("user", dto);
//                response.sendRedirect("inicioAdmin.jsp");
//            } else {
//                response.sendRedirect("index.jsp");
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {

        HttpSession sesion = request.getSession();
        sesion.invalidate();
        try {
            response.sendRedirect("index.jsp");
        } catch (IOException ex) {
            Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void verPDF(HttpServletRequest request, HttpServletResponse response) {
//        UsuarioDAO dao = new UsuarioDAO();
//        ServletOutputStream sos = response.getOutputStream();
//        File reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/Usuarios.jasper"));
//        byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(), null, dao.obtenerConexion());
//        response.setContentType("application/pdf");
//        response.setContentLength(bytes.length);
//        try {
//            sos.write(bytes, 0, bytes.length);
//        } catch (IOException ex) {
//            Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            sos.flush();
//        } catch (IOException ex) {
//            Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            sos.close();
//        } catch (IOException ex) {
//            Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    private void verPDFUsuario(HttpServletRequest request, HttpServletResponse response) {
//        UsuarioDAO dao = new UsuarioDAO();
//        Map<String, Object> parametro = new HashMap<>();
//        parametro.put("Usuario_ID", Integer.parseInt(request.getParameter("id")));
//        try {
//            ServletOutputStream sos = response.getOutputStream();
//            File reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/Usuario.jasper"));
//            byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(), parametro, dao.obtenerConexion());
//            response.setContentType("application/pdf");
//            response.setContentLength(bytes.length);
//            sos.write(bytes, 0, bytes.length);
//            sos.flush();
//            sos.close();
//        } catch (IOException | JRException ex) {
//            Logger.getLogger(usuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    private String getFileName(final Part part) {
//       String header = part.getHeader("content-disposition");
//
//        for (String content : header.split(";")) {
//            if (content.trim().startsWith("filename")) {
//                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
//            }
//        }
//        return null;
//    }

}
