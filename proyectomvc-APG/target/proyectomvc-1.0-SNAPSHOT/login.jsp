<%-- 
    Document   : index
    Created on : 19 nov. 2020, 9:40:44
    Author     : Atziri Perez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>

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
        <!-- Sing in  Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="assets/img/signin-image.jpg" alt="sing up image"></figure>
                        <a href="usuarioServlet?accion=nuevo" class="signup-image-link">Crea una nueva cuenta</a>
                    </div>

                    <div class="signin-form">
                        <h2 class="form-title">Inicia Sesion</h2>
                        <form action="usuarioServlet?accion=ingresar" method="POST" class="register-form" id="login-form">
                            <div class="form-group">
                                <label for="username"><i class="fas fa-users text-primary"></i></label>
                                <input type="text" name="username" id="username" placeholder="Username"/>
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="fas fa-key text-primary "></i></label>
                                <input type="password" name="pass" id="pass" placeholder="Contraseña"/>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin" class="btn btn-primary btn-xl" value="Entrar"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>