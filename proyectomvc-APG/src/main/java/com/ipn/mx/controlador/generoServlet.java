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
import com.ipn.mx.modelo.dto.GraficaDTO;
import com.ipn.mx.modelo.dto.PeliculaDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
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
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartUtils;
//import org.jfree.chart.JFreeChart;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.data.general.PieDataset;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "generoServlet", urlPatterns = {"/generoServlet"})
public class generoServlet extends HttpServlet {

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
        if (accion.equals("listaDeGeneros")) {
            listaDeGeneros(request, response);
        } else {
            if (accion.equals("nuevo")) {
                agregarGenero(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarGenero(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarGenero(request, response);
                    } else {
                        if (accion.equals("guardar")) {
                            almacenarGenero(request, response);
                        } else {
                            if (accion.equals("ver")) {
                                mostrarGenero(request, response);
                            } else {
                                if (accion.equals("grafica")) {
                                    //graficar(request, response);
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

    private void listaDeGeneros(HttpServletRequest request, HttpServletResponse response) {
        GeneroDAO dao = new GeneroDAO();
        try {
            List lista = dao.readAll();
            request.setAttribute("listaDeGeneros", lista);
            RequestDispatcher rd = request.getRequestDispatcher("listaGeneros.jsp");
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(generoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarGenero(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("generoForm.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(generoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarGenero(HttpServletRequest request, HttpServletResponse response) {
        GeneroDAO dao = new GeneroDAO();
        GeneroDTO dto = new GeneroDTO();
        dto.getEntidad().setIdGenero(Integer.parseInt(request.getParameter("id")));
        dao.delete(dto);
        listaDeGeneros(request, response);
    }

    private void actualizarGenero(HttpServletRequest request, HttpServletResponse response) {
        GeneroDAO dao = new GeneroDAO();
        GeneroDTO dto = new GeneroDTO();
        dto.getEntidad().setIdGenero(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("genero", dto);
            RequestDispatcher vista = request.getRequestDispatcher("generoForm.jsp");
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(generoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarGenero(HttpServletRequest request, HttpServletResponse response) {
        GeneroDAO dao = new GeneroDAO();
        GeneroDTO dto = new GeneroDTO();
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            dto.getEntidad().setNombreGenero(request.getParameter("txtNombreGenero"));
            dto.getEntidad().setDescripcionGenero(request.getParameter("txtDescripcionGenero"));
            dao.create(dto);
            listaDeGeneros(request, response);
        } else {
            dto.getEntidad().setIdGenero(Integer.parseInt(request.getParameter("id")));
            dto.getEntidad().setNombreGenero(request.getParameter("txtNombreGenero"));
            dto.getEntidad().setDescripcionGenero(request.getParameter("txtDescripcionGenero"));
            dao.update(dto);
            listaDeGeneros(request, response);
        }
    }

    private void mostrarGenero(HttpServletRequest request, HttpServletResponse response) {
        GeneroDAO daoC = new GeneroDAO();
        GeneroDTO dtoC = new GeneroDTO();
        PeliculaDAO daoP = new PeliculaDAO();
        PeliculaDTO dtoP = new PeliculaDTO();
        RequestDispatcher rd = request.getRequestDispatcher("verGenero.jsp");
        dtoC.getEntidad().setIdGenero(Integer.parseInt(request.getParameter("id")));
        dtoP.getEntidad().setIdGenero(Integer.parseInt(request.getParameter("id")));
        try {
            dtoC = daoC.read(dtoC);
            List listaPeli = daoP.readGen(dtoP);
            request.setAttribute("gen", dtoC);
            request.setAttribute("peli", listaPeli);
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(generoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void graficar(HttpServletRequest request, HttpServletResponse response) {
//        GraficaDAO dao = new GraficaDAO();
//        RequestDispatcher rd = request.getRequestDispatcher("graficaGenero.jsp");
//        try {
//            List listaElem = dao.graficaNumCat();
//            List listAvgPrecio = dao.graficaAvgPrecio();
//            List listInventario = dao.graficaInventario();
//            request.setAttribute("elem", listaElem);
//            request.setAttribute("peli", listAvgPrecio);
//            request.setAttribute("inv", listInventario);
//            rd.forward(request, response);
//        } catch (IOException | ServletException | SQLException ex) {
//            Logger.getLogger(generoServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    private void verPDF(HttpServletRequest request, HttpServletResponse response) {
//        GeneroDAO dao = new GeneroDAO();
//        try {
//            ServletOutputStream sos = response.getOutputStream();
//            File reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/Categoria.jasper"));
//            byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(), null, dao.obtenerConexion());
//            response.setContentType("application/pdf");
//            response.setContentLength(bytes.length);
//            sos.write(bytes, 0, bytes.length);
//            sos.flush();
//            sos.close();
//        } catch (IOException | JRException ex) {
//            Logger.getLogger(generoServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
