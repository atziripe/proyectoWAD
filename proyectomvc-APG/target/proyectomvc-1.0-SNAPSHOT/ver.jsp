<%-- 
    Document   : ver
    Created on : 26 nov. 2020, 15:30:58
    Author     : Atziri Perez
--%>
<%@page session='true'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Categoría</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic"
              rel="stylesheet" type="text/css" />
        <!-- Third party plugin CSS-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css"
              rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Barra de navegación-->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger" href="inicioAdmin.jsp">Producto por Categoría</a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                    data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                    aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto my-2 my-lg-0">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="categoriaServlet?accion=listaDeCategorias">Lista de Categorías</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="productoServlet?accion=grafica&id=${cat.entidad.idCategoria}">Ver gráficas</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="productoServlet?accion=reporte">Reporte PDF</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="productoServlet?accion=nuevo">Nuevo Producto</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=usuarios">Usuarios</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=actualizar&id=${sessionScope.user.entidad.idUsuario}"><i
                                    class="fas fa-2x fa-user-edit mb-4"></i>${sessionScope.user.entidad.nombreUsuario}</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=salir"><i
                                    class="fas fa-2x fa-sign-out-alt mb-4"></i></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Datos Categoria-->
        <section class="page-section bg-primary" id="Category">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8 text-center">
                        <h2 class="text-white mt-0">Categoria <c:out value='${cat.entidad.nombreCategoria}'/></h2>
                        <hr class="divider light my-4" />
                        <div class="card bg-primary">
                            <div class="card-header">
                                <h1>Datos de la categoria</h1>
                            </div>
                            <div class="card-body">
                                <ul class="list-group">
                                    <li class="list-group-item"><c:out value='${cat.entidad.idCategoria}'/></li>
                                    <li class="list-group-item"><c:out value='${cat.entidad.nombreCategoria}'/></li>
                                    <li class="list-group-item"><c:out value='${cat.entidad.descripcionCategoria}'/></li>
                                </ul>
                            </div>
                        </div>
                        <a class="btn btn-light btn-xl js-scroll-trigger" href="productoServlet?accion=grafica&id=${cat.entidad.idCategoria}">Ver gráficas</a>
                        <a class="btn btn-light btn-xl js-scroll-trigger" href="productoServlet?accion=reporte">Reporte PDF</a>
                    </div>
                </div>
            </div>
        </section>

        <!-- Tabla producots-->
        <section class="page-section bg-dark text-white">
            <div class="container text-center">
                <h2 class="mb-4">Productos de esta categoria</h2>

                <hr class="divider my-4" />
                <br>
                <table class="table table-hover text-light">
                        <thead>
                            <tr>
                                <th>ID Producto</th>
                                <th>Nombre</th>
                                <th>Descripcion</th>
                                <th>Precio</th>
                                <th>Existencia</th>
                                <th>Editar</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${prod}">
                                <tr>
                                    <td>
                                        <a class="btn btn-light btn-xs"
                                           href="productoServlet?accion=ver&id=<c:out value='${dto.entidad.idProducto}'/>"
                                           >
                                            <c:out value="${dto.entidad.idProducto}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${dto.entidad.nombreProducto}"/>
                                    </td>
                                    <td>
                                        <c:out value="${dto.entidad.descripcionProducto}"/>
                                    </td>
                                    <td>
                                        <c:out value="${dto.entidad.precio}"/>
                                    </td>
                                    <td>
                                        <c:out value="${dto.entidad.existencia}"/>
                                    </td>
                                    <td>
                                        <a 
                                            href="productoServlet?accion=actualizar&id=<c:out value='${dto.entidad.idProducto}'/>"
                                            >
                                            <i class="fas fa-2x fa-pencil-alt text-success mb-4"></i>
                                        </a>
                                    </td>
                                    <td>
                                        <a 
                                            href="productoServlet?accion=eliminar&id=<c:out value='${dto.entidad.idProducto}'/>"
                                            >
                                            <i class="fas fa-2x fa-trash-alt text-primary mb-4"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
            </div>
        </section>

        <!-- Pie de página-->
        <footer class="bg-light py-5">

                <div class="small text-center text-muted">Copyright © 2020 - Start Bootstrap</div>

        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
