<%-- 
    Document   : inicioAdmin
    Created on : 11 dic. 2020, 13:28:06
    Author     : Atziri Perez
--%>
<%@page session='true'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <title>Practica 2 WAD</title>
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

<body id="page-top">
    <!-- Barra de navegación-->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
        <div class="container">
            <a class="navbar-brand js-scroll-trigger" href="index.jsp">Producto por Categoría</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto my-2 my-lg-0">
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="categoriaServlet?accion=listaDeCategorias">Categorias</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=usuarios">Usuarios</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=actualizar&id=${sessionScope.user.entidad.idUsuario}"><i
                                class="fas fa-2x fa-user-edit mb-4"></i>${sessionScope.user.entidad.nombreUsuario}</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=salir"><i
                                class="fas fa-2x fa-sign-out-alt mb-4"></i></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Panel principal-->
    <header class="masthead">
        <div class="container h-100">
            <div class="row h-100 align-items-center justify-content-center text-center">
                <div class="col-lg-10 align-self-end">
                    <h1 class="text-uppercase text-white font-weight-bold">Bienvenido ${sessionScope.user.entidad.nombre}</h1>
                    <hr class="divider my-4" />
                </div>
                <div class="col-lg-8 align-self-baseline">
                    <p class="text-white-75 font-weight-light mb-5">Comienza a gestionar Categorías y productos</p>
                    <a class="btn btn-primary btn-xl js-scroll-trigger" href="#about">Conoce más</a>
                </div>
            </div>
        </div>
    </header>
    <!-- Acerca del proyecto-->
    <section class="page-section bg-primary" id="about">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8 text-center">
                    <h2 class="text-white mt-0">Interfaz de administrador</h2>
                    <hr class="divider light my-4" />
                    <p class="text-white-50 mb-4">Hay dos rubros para gestionar, productos y usuarios.</p>
                    <a class="btn btn-light btn-xl js-scroll-trigger" href="#categorias">Productos</a>
                    <a class="btn btn-light btn-xl js-scroll-trigger" href="#usuarios">Usuarios</a>
                </div>
            </div>
        </div>
    </section>
    <!-- Categorias-->
    <section class="page-section" id="categorias">
        <div class="container">
            <h2 class="text-center mt-0">Acciones en productos</h2>
            <hr class="divider my-4" />
            <div class="row">
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="mt-5">
                        <i class="fas fa-4x fa-plus text-primary mb-4"></i>
                        <h3 class="h4 mb-2">Nuevo</h3>
                        <p class="text-muted mb-0">Crear una categoría o un producto nuevo</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="mt-5">
                        <i class="fas fa-4x fa-eye text-primary mb-4"></i>
                        <h3 class="h4 mb-2">Consulta</h3>
                        <p class="text-muted mb-0">Consultar categorias y productos existentes.</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="mt-5">
                        <i class="fas fa-4x fa-pen-alt text-primary mb-4"></i>
                        <h3 class="h4 mb-2">Actualización</h3>
                        <p class="text-muted mb-0">Actualizar información de la base de datos.</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="mt-5">
                        <i class="fas fa-4x fa-trash-alt text-primary mb-4"></i>
                        <h3 class="h4 mb-2">Eliminación</h3>
                        <p class="text-muted mb-0">Eliminar productos y/o categorías</p>
                    </div>
                </div>
            </div>
            <div class="row h-100 align-items-center justify-content-center text-center">
                <a class="btn btn-primary btn-xl js-scroll-trigger" href="categoriaServlet?accion=listaDeCategorias">Ver más</a>
            </div>
        </div>
    </section>
    <!-- usuarios-->
    <section class="page-section bg-dark text-white" id="usuarios">
        <div class="container">
            <h2 class="text-center mt-0">Acciones en usuarios</h2>
            <hr class="divider my-4" />
            <div class="row">
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="mt-5">
                        <i class="fas fa-4x fa-user-plus text-light mb-4"></i>
                        <h3 class="h4 mb-2">Nuevo</h3>
                        <p class="text-muted mb-0">Crear nuevo usuario</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="mt-5">
                        <i class="fas fa-4x fa-address-book text-light mb-4"></i>
                        <h3 class="h4 mb-2">Consulta</h3>
                        <p class="text-muted mb-0">Consultar información de los usuarios</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="mt-5">
                        <i class="fas fa-4x fa-user-edit text-light mb-4"></i>
                        <h3 class="h4 mb-2">Actualización</h3>
                        <p class="text-muted mb-0">Actualizar datos de los usuarios</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="mt-5">
                        <i class="fas fa-4x fa-user-times text-iight mb-4"></i>
                        <h3 class="h4 mb-2">Eliminación</h3>
                        <p class="text-muted mb-0">Borrar cuentas</p>
                    </div>
                </div>
            </div>
            <div class="row h-100 align-items-center justify-content-center text-center">
                <a class="btn btn-light btn-xl js-scroll-trigger" href="usuarioServlet?accion=usuarios">Ver más</a>
            </div>
        </div>
    </section>
    <!-- Contact0o-->
    <section class="page-section" id="contact">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8 text-center">
                    <h2 class="mt-0">Hecho por Atziri Perez García</h2>
                    <hr class="divider my-4" />
                    <p class="text-muted mb-5">Estudiante de la ESCOM en 8vo semestre</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 ml-auto text-center mb-5 mb-lg-0">
                    <i class="fab fa-github fa-3x mb-3 text-muted"></i>
                    <div>atziripe</div>
                </div>
                <div class="col-lg-4 mr-auto text-center">
                    <i class="fas fa-envelope fa-3x mb-3 text-muted"></i>
                    <a class="d-block" href="mailto:aperezg1300@alumno.ipn.mx">aperezg1300@alumno.ipn.mx</a>
                </div>
            </div>
        </div>
    </section>
    <!-- Pie de página-->
    <footer class="bg-light py-5">
        <div class="container">
            <div class="small text-center text-muted">Copyright © 2020 - Start Bootstrap</div>
        </div>
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