<%-- 
    Document   : ver
    Created on : 26 nov. 2020, 15:30:58
    Author     : Atziri Perez
--%> 
<%@page import="com.ipn.mx.modelo.dto.CategoriaDTO"%>
<%@page import="com.ipn.mx.modelo.dao.CategoriaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Categoría</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    </head>
    <body>
        <%
            CategoriaDAO dao = new CategoriaDAO();
            CategoriaDTO dto = new CategoriaDTO();
            int id = Integer.parseInt(request.getParameter("id")); 
            dto.getEntidad().setIdCategoria(id);
            dto = dao.read(dto);
        %>
        <div  class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="listaDeCategorias.jsp">Lista de Categorias</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="FormCategoria.jsp">Agregar Categoria</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="card bg-primary">
                <div class="card-header">
                    <h1>Datos de la categoria</h1>
                </div>
                <div class="card-body">
                    <ul class="list-group">
                        <li class="list-group-item"><%= dto.getEntidad().getIdCategoria()%></li>
                        <li class="list-group-item"><%= dto.getEntidad().getNombreCategoria()%></li>
                        <li class="list-group-item"><%= dto.getEntidad().getDescripcionCategoria()%></li>
                    </ul>
                </div>
            </div>
           
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
