/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.GeneroDAO;
import com.ipn.mx.modelo.dao.GraficaDAO;
import com.ipn.mx.modelo.dao.PeliculaDAO;
import com.ipn.mx.modelo.dto.GeneroDTO;
import com.ipn.mx.modelo.dto.PeliculaDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "peliculaServlet", urlPatterns = {"/peliculaServlet"})
public class peliculaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        if (accion.equals("listaDeProductos")) {
            listaDeProductos(request, response);
        } else {
            if (accion.equals("nuevo")) {
                agregarPelicula(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarPelicula(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarPelicula(request, response);
                    } else {
                        if (accion.equals("guardar")) {
                            almacenarPelicula(request, response);
                        } else {
                            if (accion.equals("ver")) {
                                mostrarPelicula(request, response);
                            } else {
                                if (accion.equals("grafica")) {
                                    graficar(request, response);
                                } else {
                                    if (accion.equals("reporte")) {
                                        verPDF(request, response);
                                    }
                                }
                            }
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


private void listaDeProductos(HttpServletRequest request, HttpServletResponse response) {
        PeliculaDAO dao = new PeliculaDAO();
        List lista = dao.readAll();
        request.setAttribute("listaDeProductos", lista);
        RequestDispatcher rd = request.getRequestDispatcher("listaPelicula.jsp");
        try {
            rd.forward(request,response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(peliculaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarPelicula(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("peliculaForm.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(peliculaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarPelicula(HttpServletRequest request, HttpServletResponse response) {
        PeliculaDAO dao = new PeliculaDAO();
        PeliculaDTO dto = new PeliculaDTO();
        dto.getEntidad().setIdPelicula(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher rd = request.getRequestDispatcher("generoServlet?accion=listaDeGeneros");
        dao.delete(dto);
        try {     
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(peliculaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarPelicula(HttpServletRequest request, HttpServletResponse response) {
        PeliculaDAO dao = new PeliculaDAO();
        PeliculaDTO dto = new PeliculaDTO();
        dto.getEntidad().setIdPelicula(Integer.parseInt(request.getParameter("id")));
        dto = dao.read(dto);
        request.setAttribute("pelicula",dto);
        RequestDispatcher vista = request.getRequestDispatcher("peliculaForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(peliculaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarPelicula(HttpServletRequest request, HttpServletResponse response) {
        PeliculaDAO dao = new PeliculaDAO();
        PeliculaDTO dto = new PeliculaDTO(); 
        RequestDispatcher rd = request.getRequestDispatcher("generoServlet?accion=listaDeGeneros");
         if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            dto.getEntidad().setIdGenero(Integer.parseInt(request.getParameter("txtIdGenero")));
            dto.getEntidad().setSinopsis(request.getParameter("txtSinopsisPelicula"));
            dto.getEntidad().setClasificacion(request.getParameter("txtClasificacion"));
            dto.getEntidad().setAnio(Integer.parseInt(request.getParameter("txtAnio")));
            dto.getEntidad().setDuracion(Float.parseFloat(request.getParameter("txtDuracion")));
            dto.getEntidad().setDirector(request.getParameter("Director"));
//            dto.getEntidad().setVotosPositivos(Integer.parseInt(request.getParameter("votosPositivos")));
//            dto.getEntidad().setVotosNegativos(Integer.parseInt(request.getParameter("votosNegativos")));
dao.create(dto);
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(peliculaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }else{
            dto.getEntidad().setIdPelicula(Integer.parseInt(request.getParameter("id")));
            dto.getEntidad().setIdGenero(Integer.parseInt(request.getParameter("txtIdGenero")));
            dto.getEntidad().setSinopsis(request.getParameter("txtSinopsisPelicula"));
            dto.getEntidad().setClasificacion(request.getParameter("txtClasificacion"));
            dto.getEntidad().setAnio(Integer.parseInt(request.getParameter("txtAnio")));
            dto.getEntidad().setDuracion(Float.parseFloat(request.getParameter("txtDuracion")));
            dto.getEntidad().setDirector(request.getParameter("Director"));
//            dto.getEntidad().setVotosPositivos(Integer.parseInt(request.getParameter("votosPositivos")));
//            dto.getEntidad().setVotosNegativos(Integer.parseInt(request.getParameter("votosNegativos")));
dao.update(dto);
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(peliculaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }

    private void mostrarPelicula(HttpServletRequest request, HttpServletResponse response) {    
        PeliculaDAO dao = new PeliculaDAO();
        PeliculaDTO dto = new PeliculaDTO();
        RequestDispatcher rd = request.getRequestDispatcher("verProducto.jsp");
        dto.getEntidad().setIdPelicula(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("product",dto);
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(peliculaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void graficar(HttpServletRequest request, HttpServletResponse response) {
        GraficaDAO dao = new GraficaDAO();
        GeneroDAO daoC = new GeneroDAO();
        GeneroDTO dto = new GeneroDTO();
        RequestDispatcher rd = request.getRequestDispatcher("graficaProducto.jsp");
        dto.getEntidad().setIdGenero(Integer.parseInt(request.getParameter("id")));
        try {            
            List listaEx = dao.graficaPExistencia(dto);
            List listPrecio = dao.graficaPPrecio(dto);
            dto = daoC.read(dto);
            request.setAttribute("ex",listaEx);
            request.setAttribute("pr",listPrecio);
            request.setAttribute("category",dto);
            rd.forward(request,response);
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(peliculaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void verPDF(HttpServletRequest request, HttpServletResponse response) {
//        PeliculaDAO dao = new PeliculaDAO();
//        try {
//            ServletOutputStream sos = response.getOutputStream();
//            File reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/Productos.jasper"));
//            //byte[] bytes  = JasperRunManager.runReportToPdf(reporte.getPath(),null, dao.obtenerConexion());
//            response.setContentType("application/pdf");
//            response.setContentLength(bytes.length);
//            sos.write(bytes, 0, bytes.length);
//            sos.flush();
//            sos.close();
//        } catch (IOException | JRException ex) {
//            Logger.getLogger(peliculaServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
