<%-- 
    Document   : EliminarCategoria
    Created on : 23 nov. 2020, 14:57:02
    Author     : Atziri Perez
--%>

<%@page import="com.ipn.mx.modelo.dto.CategoriaDTO"%>
<%@page import="com.ipn.mx.modelo.dao.CategoriaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Almacenar</title>
    </head>
    <body>
        <%
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO(); 
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()){
           dto.getEntidad().setNombreCategoria(request.getParameter("txtNombreCategoria"));
           dto.getEntidad().setDescripcionCategoria(request.getParameter("txtDescripcionCategoria"));
           dao.create(dto);
        }else{
           dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
           dto.getEntidad().setNombreCategoria(request.getParameter("txtNombreCategoria"));
           dto.getEntidad().setDescripcionCategoria(request.getParameter("txtDescripcionCategoria"));
           dao.update(dto);
        }
        %>
    </body>
</html>