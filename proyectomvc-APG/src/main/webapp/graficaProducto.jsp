<%-- 
    Document   : graficaCategoria
    Created on : 12 dic. 2020, 21:25:26
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

        <title>Graficas Categoria</title>
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
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="productoServlet?accion=nuevo">Nuevo producto</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=usuarios">Usuarios</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=actualizar&id=${sessionScope.user.entidad.idUsuario}"><i
                                    class="fas fa-2x fa-user-edit mb-4"></i>${sessionScope.user.entidad.nombreUsuario}</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="usuarioServlet?accion=salir"><i
                                    class="fas fa-2x fa-sign-out-alt mb-4"></i></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Grafica elementos por categoria-->
        <section class="page-section bg-dark text-white">
            <div class="container text-center">             
                <h1 class="mb-4">Categoria <c:out value='${category.entidad.nombreCategoria}'/></h1>
                <h2 class="mb-4">Existencia por producto</h2>

                <hr class="divider my-4" />
                <br>
                <canvas id="exxprod" ></canvas>
            </div>
        </section>
        
        <!-- Grafica precio promedio por categoria-->
        <section class="page-section bg-light">
            <div class="container text-center">
                <h2 class="mb-4">Precio promedio por categoría</h2>

                <hr class="divider my-4" />
                <br>
                <canvas id="pricexprod" ></canvas>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js" integrity="sha512-hZf9Qhp3rlDJBvAKvmiG+goaaKRZA6LKUO35oK6EsM0/kjPK32Yw7URqrq3Q+Nvbbt8Usss+IekL7CRn83dYmw==" crossorigin="anonymous"></script>
        <script>
            var dynamicColors = function() {
                var r = Math.floor(Math.random() * 255);
                var g = Math.floor(Math.random() * 255);
                var b = Math.floor(Math.random() * 255);
                return "rgb(" + r + "," + g + "," + b + ")";
            };
            var pxc= document.getElementById("exxprod").getContext("2d");
            var datos = new Array();
            var etiquetas = new Array();
            var coloR = new Array();
            <c:forEach var="item" items="${ex}">
                etiquetas.push('<c:out value='${item.nombre}'/>');
                datos.push('<c:out value='${item.cantidad}'/>'); 
                coloR.push(dynamicColors());
            </c:forEach>  ;      
            var myChart  = new Chart(pxc,{
                type: 'doughnut',
                data:{
                    labels: etiquetas,
                    datasets:[{
                        data:datos,
                        backgroundColor: coloR
                    }]
                }             
            }); 
            
            var prexc= document.getElementById("pricexprod").getContext("2d");
            var datos1 = new Array();
            var etiquetas1 = new Array();
            var coloR1 = new Array();
            <c:forEach var="item" items="${pr}">
                etiquetas1.push('<c:out value='${item.nombre}'/>');
                datos1.push('<c:out value='${item.cantidad}'/>'); 
                coloR1.push(dynamicColors());
            </c:forEach>  ;      
            var myChart  = new Chart(prexc,{
                type: 'bar',
                data:{
                    labels: etiquetas1,
                    datasets:[{
                        label:'Precio promedio',
                        data:datos1,
                        backgroundColor: coloR1
                    }]
                },
                options:{
                 scales:{
                     yAxes:[{
                             ticks:{beginAtZero:true}
                     }]
                 }   
                }                   
            }); 
        </script>
    </body>
</html>