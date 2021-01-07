package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.GraficaDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.GraficaDTO;
import com.ipn.mx.modelo.dto.ProductoDTO;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/*
 * @author Atziri Perez
 */
@WebServlet(name = "categoriaServlet", urlPatterns = {"/categoriaServlet"})
public class categoriaServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String accion = request.getParameter("accion");
        if(accion.equals("listaDeCategorias"))
            listaDeCategorias(request,response);
        else{
            if(accion.equals("nuevo")){
                agregarCategoria(request, response);
            }else{
               if(accion.equals("eliminar")){
                eliminarCategoria(request, response);
               }else{
                   if(accion.equals("actualizar")){
                    actualizarCategoria(request, response);
                   }else{
                       if(accion.equals("guardar")){
                        almacenarCategoria(request, response);
                       }else{
                           if(accion.equals("ver")){
                            mostrarCategoria(request, response);
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

    private void listaDeCategorias(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        try {
            List lista = dao.readAll();
            request.setAttribute("listaDeCategorias", lista);
            RequestDispatcher rd = request.getRequestDispatcher("listaCategorias.jsp");
            rd.forward(request,response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(categoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("categoriaForm.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(categoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        try {
            dao.delete(dto);
            listaDeCategorias(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(categoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("categoria",dto);
            RequestDispatcher vista = request.getRequestDispatcher("categoriaForm.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(categoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO(); 
         if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            dto.getEntidad().setNombreCategoria(request.getParameter("txtNombreCategoria"));
            dto.getEntidad().setDescripcionCategoria(request.getParameter("txtDescripcionCategoria"));
            try {
                dao.create(dto);
                listaDeCategorias(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(categoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }else{
            dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
            dto.getEntidad().setNombreCategoria(request.getParameter("txtNombreCategoria"));
            dto.getEntidad().setDescripcionCategoria(request.getParameter("txtDescripcionCategoria"));
            try {
                dao.update(dto);
                listaDeCategorias(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(categoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }

    private void mostrarCategoria(HttpServletRequest request, HttpServletResponse response) {    
        CategoriaDAO daoC = new CategoriaDAO();
        CategoriaDTO dtoC = new CategoriaDTO();
        ProductoDAO daoP = new ProductoDAO();
        ProductoDTO dtoP = new ProductoDTO();
        RequestDispatcher rd = request.getRequestDispatcher("ver.jsp");
        dtoC.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        dtoP.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        try {
            dtoC = daoC.read(dtoC);
            List listaProd = daoP.readCat(dtoP);
            request.setAttribute("cat",dtoC);
            request.setAttribute("prod",listaProd);
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(categoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void graficar(HttpServletRequest request, HttpServletResponse response) {
        GraficaDAO dao = new GraficaDAO();
        RequestDispatcher rd = request.getRequestDispatcher("graficaCategoria.jsp");
        try {
            List listaElem = dao.graficaNumCat();
            List listAvgPrecio = dao.graficaAvgPrecio();
            List listInventario = dao.graficaInventario();
            request.setAttribute("elem",listaElem);
            request.setAttribute("prod",listAvgPrecio);
            request.setAttribute("inv",listInventario);
            rd.forward(request,response);
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(categoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void verPDF(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        try {
            ServletOutputStream sos = response.getOutputStream();
            File reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/Categoria.jasper"));
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
