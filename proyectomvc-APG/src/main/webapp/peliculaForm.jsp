<%-- 
    Document   : generoForm
    Created on : 25 nov. 2020, 15:31:40
    Author     : Atziri Perez
--%>
<%@page session='true'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Pelicula</title>
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
                <a class="navbar-brand js-scroll-trigger" href="inicioAdmin.jsp">Pelicula por Género</a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                        data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                        aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto my-2 my-lg-0">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="generoServlet?accion=listaDeCategorias">Categorias</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=usuarios">Usuarios</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=actualizar&id=${sessionScope.user.entidad.idUsuario}"><i
                                    class="fas fa-2x fa-user-edit mb-4"></i>${sessionScope.user.entidad.nombreUsuario}</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=salir"><i
                                    class="fas fa-2x fa-sign-out-alt mb-4"></i></a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Form-->
        <section class="page-section bg-dark text-white">
            <div class="container text-center">
                <h2 class="mb-4">Datos de la película</h2>
                <hr class="divider my-4" />
                <br>
                <form action="peliculaServlet?accion=guardar" method= "POST" name="frmPelicula" id="frmPelicula">
                    <input type="hidden" name="id" id="id" value="<c:out value='${pelicula.entidad.idPelicula}'/>"/>
                    <div class="form-group row">
                        <label for="txtNombrePelicula" class="col-sm-2 col-form-label">Nombre pelicula</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtNombrePelicula" id="txtNombrePelicula" maxlength="50" required="required" placeholder="Nombre del pelicula" class="form-control" value="<c:out value='${pelicula.entidad.nombrePelicula}'/>"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="txtSinopsisPelicula" class="col-sm-2 col-form-label">Sinopsis de la pelicula</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtSinopsisPelicula" id="txtSinopsisPelicula" maxlength="250" required="required" placeholder="Sinopsis del Pelicula" class="form-control" value="<c:out value='${pelicula.entidad.sinopsis}'/>"/>
                        </div>
                    </div>
                    <div class="row g-3">
                        <div class="col">
                            <label for="txtPrecio" class="col-sm-2 col-form-label">Clasificacion</label>
                            <div class="col-sm-10">
                                <div class="input-group">                             
                                    <input type="text" name="txtClasificacion" id="txtClasificacion" required="required" placeholder="Clasificacion" class="form-control" value="<c:out value='${pelicula.entidad.clasificacion}'/>"/>                            
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <label for="txtAnio" class="col-sm-2 col-form-label">Año</label>
                            <input type="number"  max="2021" name="txtAnio" id="txtAnio" required="required" placeholder="Año" class="form-control" value="<c:out value='${pelicula.entidad.anio}'/>">
                        </div>
                        <div class="col">
                            <label for="txtAnio" class="col-sm-2 col-form-label">Duracion</label>
                            <input type="number"  name="txtDuracion" id="txtDuracion" required="required" placeholder="Duracion" class="form-control" value="<c:out value='${pelicula.entidad.duracion}'/>">
                        </div>
                        <div class="col">
                            <label for="Director" class="col-sm-2 col-form-label">Director</label>
                            <input type="text"  name="Director" id="Director" required="required" placeholder="Director" class="form-control" value="<c:out value='${pelicula.entidad.director}'/>">
                        </div>
                    </div>
                        <br>
                    <div class="form-group row">
                        <label for="txtIdGenero" class="col-sm-2 col-form-label">ID genero</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtIdGenero" id="txtIdGenero" maxlength="250" required="required" placeholder="ID Género" class="form-control" value="<c:out value='${pelicula.entidad.idCategoria}'/>"/>
                        </div>
                    </div>
                    <div class="row h-100 align-items-center justify-content-center text-center">
                        <input type="submit" class="btn btn-primary btn-xl js-scroll-trigger" value="Registrar Pelicula"/>
                    </div>
                </form>
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

