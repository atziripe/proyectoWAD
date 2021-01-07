package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.EventoDAO;
import com.ipn.mx.modelo.dto.Evento;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Atziri Perez
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        if (accion.equals("listaDeEventos")) {
            listaDeEventos(request, response);
        } else {
            if (accion.equals("nuevo")) {
                agregarEvento(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarEvento(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarEvento(request, response);
                    }
                    if (accion.equals("guardar")) {
                        almacenarEvento(request, response);
                    }
                    if (accion.equals("ver")) {
                        mostrarEvento(request, response);
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

    private void listaDeEventos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de Eventos</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
            out.println("<nav class='navbar navbar-light bg-light'>");
            out.println("<a class='navbar-brand' href='#'>");
            out.println("<img src='images/evento.png' width='30' height='30' class='d-inline-block align-top' alt=''> Evento</a>");
            out.println("</nav>");
            out.println("<div class='collapse navbar-collapse' id='navbarSupportedContent'>");
            out.println("<ul class='navbar-nav mr-auto'>");
            out.println("<li class='nav-item active'>");
            out.println("<a class='nav-link'>Home <span class='sr-only'>(current)</span></a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='Servlet?accion=listaDeEventos'>Lista de Eventos</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</nav>");
            out.println("<p></p>");
            out.println("<h1>Listado de Eventos </h1>");

            int idEvento;
            String nombreEvento;
            String sede;
            double duracion;
            Date fechaInicio;
            Date fechaTermino;

            EventoDAO dao = new EventoDAO();
            try {
                List lista = dao.readAll();
                out.println("<div class='table-responsive'>");
                out.print("<table class='table table-striped'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th scope='col'>");
                out.print("id del Evento");
                out.print("</th>");
                out.print("<th scope='col'>");
                out.print("nombreEvento");
                out.print("</th>");
                out.print("<th scope='col'>");
                out.print("sede");
                out.print("</th>");
                out.print("<th scope='col'>");
                out.print("duracion");
                out.print("</th>");
                out.print("<th scope='col'>");
                out.print("fechaInicio");
                out.print("</th>");
                out.print("<th scope='col'>");
                out.print("fechaTermino");
                out.print("</th>");
                out.print("<th scope='col'>");
                out.print("Acciones");
                out.print("</th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");
                for (int i = 0; i < lista.size(); i++) {
                    Evento evento = (Evento) lista.get(i);
                    idEvento = evento.getIdEvento();
                    nombreEvento = evento.getNombreEvento();
                    sede = evento.getSede();
                    duracion = evento.getDuracion();
                    fechaInicio = evento.getFechaInicio();
                    fechaTermino = evento.getFechaTermino();

                    out.print("<tr>");
                    out.print("<th scope='row'>" + idEvento + "</th>");
                    out.print("<td>" + nombreEvento + "</td>");
                    out.print("<td>" + sede + "</td>");
                    out.print("<td>" + duracion + "</td>");
                    out.print("<td>" + fechaInicio + "</td>");
                    out.print("<td>" + fechaTermino + "</td>");
                    out.print("<td><a href='Servlet?accion=eliminar&id=" + idEvento + "'> Eliminar </a>");
                    out.println("<a href='Servlet?accion=actualizar&id=" + idEvento + "'> Actualizar </a>");
                    out.println("<a href='Servlet?accion=ver&id=" + idEvento + "'> Ver </a></td>");
                    out.print("</tr>");

                }
                out.print("</tbody>");
                out.print("</table>");
                out.println("</div>");
                out.println("<a href ='Servlet?accion=nuevo' class ='btn btn-primary'> Nuevo Evento </a>");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void agregarEvento(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("eventoForm.html").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarEvento(HttpServletRequest request, HttpServletResponse response) {
        EventoDAO dao = new EventoDAO();
        Evento e = new Evento();
        try {
            e.setIdEvento(Integer.parseInt(request.getParameter("id")));
            e = dao.read(e);
            dao.delete(e);
            listaDeEventos(request, response);
            //response.sendRedirect("Servlet?accion=listaDeEventos");
            //RequestDispatcher vista = request.getRequestDispatcher("Servlet?accion=listaDeEventos");
            //vista.forward(request, response);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarEvento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Datos de Evento</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            
            out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
            out.println("<nav class='navbar navbar-light bg-light'>");
            out.println("<a class='navbar-brand' href='#'>");
            out.println("<img src='images/evento.png' width='30' height='30' class='d-inline-block align-top' alt=''> Evento</a>");
            out.println("</nav>");
            out.println("<div class='collapse navbar-collapse' id='navbarSupportedContent'>");
            out.println("<ul class='navbar-nav mr-auto'>");
            out.println("<li class='nav-item active'>");
            out.println("<a class='nav-link'>Home <span class='sr-only'>(current)</span></a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='Servlet?accion=listaDeEventos'>Lista de Eventos</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</nav>");
            out.println("<p></p>");
           
            EventoDAO dao = new EventoDAO();
            Evento e = new Evento();
            try {
                e.setIdEvento(Integer.parseInt(request.getParameter("id")));
                e = dao.read(e);
                out.println("<h1>Clave del evento " + e.getIdEvento() + " </h1>");
                out.println("<form action='Servlet?accion=guardar&id=" + e.getIdEvento() + "' method='post'>");
                out.println("<table class=\"table table-borderless\">");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th scope='col'>Elemento</th>");
                out.println("<th scope='col'>Valor</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                out.println("<tr>");
                out.println("<th scope='row'>Nombre del evento</th>");
                out.println("<td> <input type=\"text\" value=" + e.getNombreEvento() + " class=\"form-control\" id=\"nomEvento\" name=\"nombreEvento\" placeholder=\"Nombre del Evento\" required=\"required\"></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th scope='row'>Sede</th>");
                out.println("<td><input type=\"text\" value=" + e.getSede() + " class=\"form-control\" id=\"sedeEvento\" name=\"sedeEvento\" placeholder=\"Sede del evento\" required=\"required\"></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th scope='row'>Duracion</th>");
                out.println("<td><input type=\"number\" value = " + e.getDuracion() + " class=\"form-control\" id=\"duracion\" name=\"duracion\" placeholder=\"Duracion\" value=\"3\" step=\"1\" min=\"1\" max=\"6\" required=\"required\"></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th scope='row'>Fecha Inicio</th>");
                out.println("<td><input type=\"date\" value = " + e.getFechaInicio() + " class=\"form-control\" id=\"fechaInicio\" name=\"fechaInicio\" required=\"required\" ></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th scope='row'>Fecha Termino</th>");
                out.println("<td> <input type=\"date\" value=" + e.getFechaTermino() + " class=\"form-control\" id=\"fechaTermino\" name=\"fechaTermino\" required=\"required\"></td>");
                out.println("</tr>");
                out.println("</tbody>");
                out.println("</table>");
                out.println("<button class='btn btn-primary' type='submit'>Actualizar</button>");
                out.println("</form>");

            } catch (SQLException ex) {
                Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void almacenarEvento(HttpServletRequest request, HttpServletResponse response) {
        EventoDAO dao = new EventoDAO();
        Evento e = new Evento();
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            e.setNombreEvento(request.getParameter("nombreEvento"));
            e.setSede(request.getParameter("sedeEvento"));
            e.setDuracion(Double.parseDouble(request.getParameter("duracion")));
            e.setFechaInicio(Date.valueOf(request.getParameter("fechaInicio")));
            e.setFechaTermino(Date.valueOf(request.getParameter("fechaTermino")));
            try {
                dao.create(e);
                response.sendRedirect("Servlet?accion=listaDeEventos");
            } catch (SQLException | IOException ex) {
                Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            e.setIdEvento(Integer.parseInt(request.getParameter("id")));
            e.setNombreEvento(request.getParameter("nombreEvento"));
            e.setSede(request.getParameter("sedeEvento"));
            e.setDuracion(Double.parseDouble(request.getParameter("duracion")));
            e.setFechaInicio(Date.valueOf(request.getParameter("fechaInicio")));
            e.setFechaTermino(Date.valueOf(request.getParameter("fechaTermino")));
            try {
                dao.update(e);
                response.sendRedirect("Servlet?accion=listaDeEventos");
            } catch (SQLException | IOException ex) {
                Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void mostrarEvento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Datos de Evento</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
            out.println("<nav class='navbar navbar-light bg-light'>");
            out.println("<a class='navbar-brand' href='#'>");
            out.println("<img src='images/evento.png' width='30' height='30' class='d-inline-block align-top' alt=''> Evento</a>");
            out.println("</nav>");
            out.println("<div class='collapse navbar-collapse' id='navbarSupportedContent'>");
            out.println("<ul class='navbar-nav mr-auto'>");
            out.println("<li class='nav-item active'>");
            out.println("<a class='nav-link'>Home <span class='sr-only'>(current)</span></a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='Servlet?accion=listaDeEventos'>Lista de Eventos</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</nav>");
            out.println("<p></p>");
            

            EventoDAO dao = new EventoDAO();
            Evento e = new Evento();
            try {
                e.setIdEvento(Integer.parseInt(request.getParameter("id")));
                e = dao.read(e);

                out.println("<div class='list-group'>");
                out.println("<a class='list-group-item list-group-item-action active'>Id del Evento: " + e.getIdEvento() + "</a>");
                out.println("<a class='list-group-item list-group-item-action'>Nombre del evento: " + e.getNombreEvento() + "</a>");
                out.println("<a class='list-group-item list-group-item-action'>Sede: " + e.getSede() + "</a>");
                out.println("<a class='list-group-item list-group-item-action'>Duracion: " + e.getDuracion() + "</a>");
                out.println("<a class='list-group-item list-group-item-action'>Fecha de inicio: " + e.getFechaInicio() + "</a>");
                out.println("<a class='list-group-item list-group-item-action'>Fecha de termino: " + e.getFechaTermino() + "</a>");
                out.println("</div>");

            } catch (SQLException ex) {
                Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
