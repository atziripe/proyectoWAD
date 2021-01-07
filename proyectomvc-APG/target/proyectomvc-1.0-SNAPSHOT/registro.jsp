<%-- 
    Document   : registro
    Created on : 11 dic. 2020, 11:07:27
    Author     : Atziri Perez
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Registro</title>

        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Main css -->    
        <link rel="stylesheet" href="css/styleUser.css">
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>

        <div class="main">
            <!-- Barra de navegación-->
            <nav class="navbar navbar-expand-lg navbar-light fixed-top " id="mainNav">
                <div class="container">
                    <a class="navbar-brand" href="index.jsp">Producto por Categoría</a>
                    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                </div>
            </nav>
            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Registrate</h2>
                            <form action="usuarioServlet?accion=guardar" method= "POST" class="register-form" id="register-form" name="register-form">
                                <input type="hidden" name="id" id="id" value="<c:out value='${usuario.entidad.idUsuario}'/>"/>
                                <div class="form-group">
                                    <label for="name"><i class="fas fa-user text-primary"></i></label>
                                    <input type="text" name="name" id="name" placeholder="Nombre"/>
                                </div>
                                <div class="form-group">
                                    <label for="paterno"><i class="fas fa-user text-primary"></i></label>
                                    <input type="text" name="paterno" id="paterno" placeholder="Apellido Paterno"/>
                                </div>
                                <div class="form-group">
                                    <label for="materno"><i class="fas fa-user text-primary"></i></label>
                                    <input type="text" name="materno" id="materno" placeholder="Apellido Materno"/>
                                </div>
                                <div class="form-group">
                                    <label for="username"><i class="far fa-user text-primary"></i></label>
                                    <input type="text" name="username" id="username" placeholder="Nombre de usuario"/>
                                </div>
                                <div class="custom-file">                                
                                    <label class="custom-file-label" for="imagen">Elegir archivo</label>
                                    <input type="file" 
                                           accept=".svg, .jpg, .png"
                                           name="imagen" 
                                           id="imagen" 
                                           required="required" 
                                           placeholder="Imagen" 
                                           class="form-control" 
                                           />
                                </div>
                                <div class="form-group">
                                    <label for="email"><i class="far fa-envelope text-primary"></i></label>
                                    <input type="email" name="email" id="email" placeholder="Email"/>
                                </div>
                                <div class="form-group">
                                    <label for="pass"><i class="fas fa-key text-primary"></i></label>
                                    <input type="password" name="pass" id="pass" placeholder="Contraseña"/>
                                </div>
                                <div class="form-group">
                                    <label for="re-pass"><i class="fas fa-key text-primary"></i></label>
                                    <input type="password" name="re_pass" id="re_pass" placeholder="Repite tu Contraseña"/>
                                </div>
                                <div class="form-group">                        
                                    <input class="custom-radio" type="radio" name="tipo" id="normal" value ="N" checked>
                                    <label class="custom-control-label" for="flexRadioDefault1">
                                        Usuario normal
                                    </label>

                                    <input class="custom-radio" type="radio" name="tipo" id="admin" value="A" >
                                    <label class="custom-control-label" for="flexRadioDefault2">
                                        Administrador
                                    </label>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signup" id="signup" class="btn btn-primary btn-xl" value="Registrar"/>
                                </div>
                            </form>
                        </div>
                        <div class="signup-image">
                            <figure><img src="assets/img/signup-image.jpg" alt="sing up image"></figure>
                            <a href="usuarioServlet?accion=iniciar" class="signup-image-link">Ya estoy registrado</a>
                        </div>
                    </div>
                </div>
            </section>

        </div>

        <!-- JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
