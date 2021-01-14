<%-- 
    Document   : listaCategorias
    Created on : 23 nov. 2020, 15:28:45
    Author     : Atziri Perez
--%>
<%@page session='true'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

        <title>Lista de usuarios</title>
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
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=reporte">Reporte PDF</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="generoServlet?accion=listaDeGeneros">Categorias</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=actualizar&id=${sessionScope.user.entidad.idUsuario}"><i
                                    class="fas fa-2x fa-user-edit mb-4"></i>${sessionScope.user.entidad.nombreUsuario}</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=salir"><i
                                    class="fas fa-2x fa-sign-out-alt mb-4"></i></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Tabla-->
        <section class="page-section bg-dark text-white">
            <div class="container text-center">
                <h2 class="mb-4">Lista de Usuarios</h2>

                <hr class="divider my-4" />
                <br>
                <table class="table table-hover text-light">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Nombre</th>
                                <th>Apellido Paterno</th>
                                <th>Apellido Materno</th>
                                <th>Imagen</th>
                                <th>Email</th>
                                <th>Típo de usuario</th>
                                <th>Eliminar</th>
                                <th>ReportePDF</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${listaDeUsuarios}">
                                <tr>
                                    <td>
                                        <c:out value="${dto.entidad.nombreUsuario}"/>
                                    </td>
                                    <td>
                                        <c:out value="${dto.entidad.nombre}"/>
                                    </td>
                                    <td>
                                        <c:out value="${dto.entidad.paterno}"/>
                                    </td>
                                    <td>
                                        <c:out value="${dto.entidad.materno}"/>
                                    </td>
                                    <td>
                                        <img style="max-width:120px;" src="assets/img/<c:out value="${dto.entidad.imagen}" />">
                                    </td>
                                    <td>
                                        <c:out value="${dto.entidad.email}"/>
                                    </td>
                                    <td>
                                        <c:out value="${dto.entidad.tipoUsuario}"/>
                                    </td>
                                    <td>
                                        <a 
                                            href="usuarioServlet?accion=eliminar&id=<c:out value='${dto.entidad.idUsuario}'/>"
                                            >
                                            <i class="fas fa-2x fa-user-times text-primary mb-4"></i>
                                        </a>
                                    </td>
                                    <td>
                                        <a 
                                            href="usuarioServlet?accion=reportesolo&id=<c:out value='${dto.entidad.idUsuario}'/>"
                                            >
                                            <i class="fas fa-2x fa-file-pdf text-secondary mb-4"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                <a class="btn btn-light btn-xl js-scroll-trigger" href="usuarioServlet?accion=reporte">Reporte PDF</a>
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

