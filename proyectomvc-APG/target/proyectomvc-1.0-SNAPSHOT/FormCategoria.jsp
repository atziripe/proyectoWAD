<%-- 
    Document   : FormCategoria
    Created on : 26 nov. 2020, 23:36:44
    Author     : Atziri Perez
--%>
<%@page import="com.ipn.mx.modelo.dto.CategoriaDTO"%>
<%@page import="com.ipn.mx.modelo.dao.CategoriaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos de categoria</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    </head>
    <body>
        <%
            CategoriaDAO dao = new CategoriaDAO();
            CategoriaDTO dto = new CategoriaDTO();
            if(request.getParameter("id") == null){ 
                dto = null; 
            } else{
                int id = Integer.parseInt(request.getParameter("id")); 
                dto.getEntidad().setIdCategoria(id);
                dto = dao.read(dto);
            }
        %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <!-- Image and text -->
            <nav class="navbar navbar-light bg-light">
                <a class="navbar-brand" href="#">
                    <img src="images/icono.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
                    Proyecto Categoría
                </a>
            </nav>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="listaDeCategorias.jsp">Lista De Categorías</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="FormCategoria.jsp">Agregar Categoría</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="card">
            <h2 class="card-header">Datos de la categoría</h2>
            <div class="card-body">
                <form action="UpdateCreate.jsp?id=<%= dto.getEntidad().getIdCategoria()%>" 
                      method= "POST" 
                      name="frmCategoria" 
                      id="frmCategoria">
                    <input type="text" name="id" id="id" value="<%= dto.getEntidad().getIdCategoria()%>"/>
                    <div class="form-group row">
                        <label for="txtNombreCategoria" class="col-sm-2 col-form-label">Nombre categoría</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtNombreCategoria" id="txtNombreCategoria" maxlength="50" required="required" placeholder="Nombre de la Categoria" class="form-control" value="<%=dto.getEntidad().getNombreCategoria()%>"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="txtxDescipcionCategoria" class="col-sm-2 col-form-label">Descripcion categoria</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtDescripcionCategoria" id="txtDescripcionCategoria" maxlength="250" required="required" placeholder="Descripcion de la Categoria" class="form-control" value="<%=dto.getEntidad().getDescripcionCategoria()%>"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <input type="submit" class="btn btn-primary" value="Registrar Categoria"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>