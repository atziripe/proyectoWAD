package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.GraficaDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.ProductoDTO;
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

/*
 * @author Atziri Perez
 */
@WebServlet(name = "productoServlet", urlPatterns = {"/productoServlet"})
public class productoServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String accion = request.getParameter("accion");
        if(accion.equals("listaDeProductos"))
            listaDeProductos(request,response);
        else{
            if(accion.equals("nuevo")){
                agregarProducto(request, response);
            }else{
               if(accion.equals("eliminar")){
                eliminarProducto(request, response);
               }else{
                   if(accion.equals("actualizar")){
                    actualizarProducto(request, response);
                   }else{
                       if(accion.equals("guardar")){
                        almacenarProducto(request, response);
                       }else{
                           if(accion.equals("ver")){
                            mostrarProducto(request, response);
                           }else{
                               if(accion.equals("grafica")){
                                   graficar(request, response);
                               }else{
                                   if(accion.equals("reporte")){
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
        ProductoDAO dao = new ProductoDAO();
        try {
            List lista = dao.readAll();
            request.setAttribute("listaDeProductos", lista);
            RequestDispatcher rd = request.getRequestDispatcher("listaProductos.jsp");
            rd.forward(request,response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(productoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("productoForm.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(productoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher rd = request.getRequestDispatcher("categoriaServlet?accion=listaDeCategorias");
        try {
            dao.delete(dto);
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(productoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("producto",dto);
            RequestDispatcher vista = request.getRequestDispatcher("productoForm.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(productoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO(); 
        RequestDispatcher rd = request.getRequestDispatcher("categoriaServlet?accion=listaDeCategorias");
         if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            dto.getEntidad().setNombreProducto(request.getParameter("txtNombreProducto"));
            dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcionProducto"));
            dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("txtPrecio")));
            dto.getEntidad().setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
            dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtIdCategoria")));
            try {
                dao.create(dto);
                rd.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
                Logger.getLogger(productoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }else{
            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
            dto.getEntidad().setNombreProducto(request.getParameter("txtNombreProducto"));
            dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcionProducto"));
            dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("txtPrecio")));
            dto.getEntidad().setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
            dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtIdCategoria")));
            try {
                dao.update(dto);
                rd.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
                Logger.getLogger(productoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }

    private void mostrarProducto(HttpServletRequest request, HttpServletResponse response) {    
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        RequestDispatcher rd = request.getRequestDispatcher("verProducto.jsp");
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("product",dto);
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(productoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void graficar(HttpServletRequest request, HttpServletResponse response) {
        GraficaDAO dao = new GraficaDAO();
        CategoriaDAO daoC = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        RequestDispatcher rd = request.getRequestDispatcher("graficaProducto.jsp");
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        try {            
            List listaEx = dao.graficaPExistencia(dto);
            List listPrecio = dao.graficaPPrecio(dto);
            dto = daoC.read(dto);
            request.setAttribute("ex",listaEx);
            request.setAttribute("pr",listPrecio);
            request.setAttribute("category",dto);
            rd.forward(request,response);
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(categoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void verPDF(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        try {
            ServletOutputStream sos = response.getOutputStream();
            File reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/Productos.jasper"));
            byte[] bytes  = JasperRunManager.runReportToPdf(reporte.getPath(),null, dao.obtenerConexion());
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            sos.write(bytes, 0, bytes.length);
            sos.flush();
            sos.close();
        } catch (IOException | JRException ex) {
            Logger.getLogger(categoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
