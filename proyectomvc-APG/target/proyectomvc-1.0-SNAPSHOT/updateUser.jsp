<%@page session='true'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar perfil</title>
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
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="categoriaServlet?accion=listaDeCategorias">Categorias</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=usuarios">Usuarios</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="formImagen.jsp">Cargar imagen</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#page-top"><i
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
                <h2 class="mb-4">Datos de tu cuenta</h2>
                <hr class="divider my-4" />
                <br>
                <form action="usuarioServlet?accion=guardar" method= "POST" name="frmUser" id="frmUser">
                    <input type="hidden" name="id" id="id" value="<c:out value='${usuario.entidad.idUsuario}'/>"/>
                    <div class="form-group row">
                        <label for="name" class="col-sm-2 col-form-label">Nombre</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" id="name" maxlength="50" required="required" placeholder="Nombre" class="form-control" value="<c:out value='${usuario.entidad.nombre}'/>"/>
                        </div>
                    </div>
                    <div class="row g-3">
                        <div class="col">
                            <label for="paterno" class="col-sm-2 col-form-label">Apellido Paterno</label>
                            <div class="col-sm-10">
                                <input type="text" name="paterno" id="paterno" required="required" placeholder="Apellido Paterno" class="form-control" value="<c:out value='${usuario.entidad.paterno}'/>"/>                            
                            </div>
                        </div>
                        <div class="col">
                            <label for="materno" class="col-sm-2 col-form-label">Apellido Materno</label>
                            <input type="text" name="materno" id="materno" required="required" placeholder="Apellido Materno" class="form-control" value="<c:out value='${usuario.entidad.materno}'/>">
                        </div>
                    </div>
                        <br>
                     <div class="form-group row">
                        <label for="imagen" class="col-sm-2 col-form-label">Imagen</label>
                        <div class="col-sm-10">
                            <input type="file" 
                                   accept=".svg, .jpg, .png"
                                   name="imagen" 
                                   id="imagen"  
                                   required="required" 
                                   placeholder="Imagen" 
                                   class="form-control" 
                                   value="<c:out value='${usuario.entidad.imagen}'/>"
                                   />
                        </div>
                    </div>
                        <br>
                    <div class="form-group row">
                        <label for="email" class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" id="email" maxlength="250" required="required" placeholder="Email" class="form-control" value="<c:out value='${usuario.entidad.email}'/>"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="username" class="col-sm-2 col-form-label">Nombre de usuario</label>
                        <div class="col-sm-10">
                            <input type="text" name="username" id="username" maxlength="25" required="required" placeholder="Nombre de usuario" class="form-control" value="<c:out value='${usuario.entidad.nombreUsuario}'/>"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="pass" class="col-sm-2 col-form-label">Contraseña</label>
                        <div class="col-sm-10">
                            <input type="password" name="pass" id="pass" maxlength="25" required="required" placeholder="Contraseña" class="form-control" value="<c:out value='${usuario.entidad.claveUsuario}'/>"/>
                        </div>
                    </div>
                    <input type="hidden" name="tipo" id="tipo" value="<c:out value='${usuario.entidad.tipoUsuario}'/>"/>
                    <div class="row h-100 align-items-center justify-content-center text-center">
                        <input type="submit" class="btn btn-primary btn-xl js-scroll-trigger" value="Actualizar"/>
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

