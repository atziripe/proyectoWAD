<%-- 
    Document   : index
    Created on : 19 nov. 2020, 9:40:44
    Author     : Atziri Perez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Practica 2 WAD</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
        <!-- Third party plugin CSS-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
        <!-- Barra de navegación-->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger" href="#page-top">Producto por Categoría</a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto my-2 my-lg-0">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=nuevo">Registrarse</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=iniciar">Ingresar</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Panel principal-->
        <header class="masthead">
            <div class="container h-100">
                <div class="row h-100 align-items-center justify-content-center text-center">
                    <div class="col-lg-10 align-self-end">
                        <h1 class="text-uppercase text-white font-weight-bold">Manera práctica y sencilla de manejar productos</h1>
                        <hr class="divider my-4" />
                    </div>
                    <div class="col-lg-8 align-self-baseline">
                        <p class="text-white-75 font-weight-light mb-5">Este proyecto te ayudará a encontrar el producto que necesitas de una manera muy sencilla y rápida</p>
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
                        <h2 class="text-white mt-0">¡Tenemos lo que necesitas!</h2>
                        <hr class="divider light my-4" />
                        <p class="text-white-50 mb-4">Proyecto de Web Application Development del 2do parcial, el cual tiene la funciones CRUD para productos y organizarlos por categoria, contiene gráficas de los productos, reportes en PDF y envío de emails.</p>
                        <a class="btn btn-light btn-xl js-scroll-trigger" href="#portfolio">Ver categorías</a>
                    </div>
                </div>
            </div>
        </section>
        <!-- Funciones del sistema-->
        <section class="page-section" id="functions">
            <div class="container">
                <h2 class="text-center mt-0">Funciones implementadas</h2>
                <hr class="divider my-4" />
                <div class="row">
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <i class="fas fa-4x fa-users text-primary mb-4"></i>
                            <h3 class="h4 mb-2">Manejo de sesiones</h3>
                            <p class="text-muted mb-0">Solo los usuarios del sistema podrán realizar las acciones de CRUD sobre los productos.</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <i class="fas fa-4x fa-tasks text-primary mb-4"></i>
                            <h3 class="h4 mb-2">CRUD</h3>
                            <p class="text-muted mb-0">Create, Read, Update y Delete en los productos en la base de datos.</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <i class="fas fa-4x fa-chart-bar text-primary mb-4"></i>
                            <h3 class="h4 mb-2">Gráficas</h3>
                            <p class="text-muted mb-0">Resultados de forma gráfica del estado actual de la información.</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <i class="fas fa-4x fa-file-pdf text-primary mb-4"></i>
                            <h3 class="h4 mb-2">Reportes en PDF</h3>
                            <p class="text-muted mb-0">Generación de reportes de la información importante en el proyecto.</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Portfolio-->
        <div id="portfolio">
            <div class="container-fluid p-0">
                <div class="row no-gutters">
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/1.jpg">
                            <img class="img-fluid" src="assets/img/portfolio/thumbnails/1.jpg" alt="" />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">Category</div>
                                <div class="project-name">Videojuegos</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/2.png">
                            <img class="img-fluid" src="assets/img/portfolio/thumbnails/2.png" alt="" />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">Category</div>
                                <div class="project-name">Computación</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/3.jpg">
                            <img class="img-fluid" src="assets/img/portfolio/thumbnails/3.jpg" alt="" />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">Categoria</div>
                                <div class="project-name">Electrónica</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/4.jpg">
                            <img class="img-fluid" src="assets/img/portfolio/thumbnails/4.jpg" alt="" />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">Categoria</div>
                                <div class="project-name">Luces</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/5.jpg">
                            <img class="img-fluid" src="assets/img/portfolio/thumbnails/5.jpg" alt="" />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">Categoria</div>
                                <div class="project-name">Música</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/6.jpg">
                            <img class="img-fluid" src="assets/img/portfolio/thumbnails/6.jpg" alt="" />
                            <div class="portfolio-box-caption p-3">
                                <div class="project-category text-white-50">Categoria</div>
                                <div class="project-name">Libros Electrónicos</div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Call to action-->
        <section class="page-section bg-dark text-white">
            <div class="container text-center">
                <h2 class="mb-4">Hay muchas más categorías</h2>
                <a class="btn btn-light btn-xl" href="usuarioServlet?accion=iniciar">Empieza Ya!</a>
            </div>
        </section>
        <!-- Contacto-->
        <section class="page-section" id="contact">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8 text-center">
                        <h2 class="mt-0">Hecho por Atziri Perez García</h2>
                        <hr class="divider my-4" />
                        <p class="text-muted mb-5">Estudiante del 8vo semestre de la ESCOM</p>
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
            <div class="container"><div class="small text-center text-muted">Copyright © 2020 - Start Bootstrap</div></div>
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
