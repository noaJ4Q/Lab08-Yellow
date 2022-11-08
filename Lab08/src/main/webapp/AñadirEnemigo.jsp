<%@ page import="com.example.lab08.MenuEnemigos.Beans.Enemigo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab08.MenuEnemigos.Beans.Clase" %>
<%@ page import="com.example.lab08.MenuEnemigos.Beans.Genero" %>
<%@ page import="com.example.lab08.MenuEnemigos.Beans.Objeto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<Clase> listaClases = (ArrayList<Clase>) request.getAttribute("ListaClases");
    ArrayList<Genero> listaGeneros = (ArrayList<Genero>) request.getAttribute("ListaGeneros");
    ArrayList<Objeto> listaObjetos = (ArrayList<Objeto>) request.getAttribute("ListaObjetos");
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Añadir Enemigo / Wiki Fantástica</title>

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
              rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">
    </head>
    <body style="background-color: #F9E64B;">

        <!-- ======= Header ======= -->
        <header class="header fixed-top d-flex align-items-center" style="background-color: #FAFAFA;">

            <div class="d-flex align-items-center justify-content-between">
                <a href="index.html" class="logo d-flex align-items-center">
                    <span class="d-none d-lg-block">Wiki Fantástica</span>
                </a>
                <i class="bi bi-list toggle-sidebar-btn"></i>
            </div>
            <!-- End Logo -->

            <!-- BARRA DE BÚSQUEDA -->
            <div class="search-bar">
                <form class="search-form d-flex align-items-center" method="POST" action="#">
                    <input type="text" name="query" placeholder="Search" title="Enter search keyword">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                </form>
            </div><!-- End Search Bar -->

            <!-- BOTÓN DE BÚSQUEDA -->
            <nav class="header-nav ms-auto">
                <ul class="d-flex align-items-center">

                    <li class="nav-item d-block d-lg-none">
                        <a class="nav-link nav-icon search-bar-toggle " href="#">
                            <i class="bi bi-search"></i>
                        </a>
                    </li>
                </ul>
            </nav>
            <!-- End Search Icon-->
        </header>
        <!-- End Header -->

        <!-- ======= Sidebar ======= -->
        <aside id="sidebar" class="sidebar">

            <ul class="sidebar-nav" id="sidebar-nav">
                <li class="nav-item">
                    <a class="nav-link collapsed" href="index.html">
                        <i class="bi-grid-fill"></i>
                        <span>MENÚ PRINCIPAL</span>
                    </a>
                </li>
                <!-- End Dashboard Nav -->

                <li class="nav-heading">Entidades</li>

                <li class="nav-item">
                    <a class="nav-link collapsed" href="users-profile.html">
                        <i class="ri-t-shirt-2-fill"></i>
                        <span>Héroes</span>
                    </a>
                </li>
                <!-- End Profile Page Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" href="pages-contact.html">
                        <i class="ri-skull-2-fill"></i>
                        <span>Enemigos</span>
                    </a>
                </li>
                <!-- End Contact Page Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" href="pages-register.html">
                        <i class="ri-ink-bottle-fill"></i>
                        <span>Hechizos</span>
                    </a>
                </li>
                <!-- End Register Page Nav -->
            </ul>

        </aside>
        <!-- End Sidebar-->

        <main id="main" class="main">

            <section class="section dashboard">
                <div class="row">

                    <!-- Left side columns -->
                    <div class="col col-lg-8">
                        <div class="row">

                            <!-- Top Selling -->
                            <div class="col-12">
                                <div class="card top-selling overflow-auto">

                                    <div class="card-body pb-0">
                                        <h5 class="card-title">AÑADIR ENEMIGO</h5>
                                        <form method="post" action="<%=request.getContextPath()%>/MenuEnemigos?action=guardar">

                                            <div class="row mb-3">
                                                <label for="nombre"
                                                       class="col-md-4 col-lg-3 col-form-label">Nombre</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="nombre" type="text" class="form-control" id="nombre">
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="claseEnemigo"
                                                       class="col-md-4 col-lg-3 col-form-label">Clase</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <select name="claseEnemigo" id="claseEnemigo" class="form-control">
                                                        <% for(Clase clase: listaClases){ %>
                                                        <option value="<%=clase.getIdClase()%>"><%=clase.getNombreClase()%></option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="ataque"
                                                       class="col-md-4 col-lg-3 col-form-label">Ataque del Enemigo (Numero Entero)</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="ataque" type="text" class="form-control" id="ataque">
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="experiencia"
                                                       class="col-md-4 col-lg-3 col-form-label">Experiencia al ser Derrotado (Numero Entero)</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="experiencia" type="text" class="form-control" id="experiencia">
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="objetoEnemigo"
                                                       class="col-md-4 col-lg-3 col-form-label">Objeto que deja al ser Derrotado</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <select name="objetoEnemigo" id="objetoEnemigo" class="form-control">
                                                        <% for(Objeto objeto: listaObjetos){ %>
                                                        <option value="<%=objeto.getIdObjeto()%>"><%=objeto.getNombreObjeto()%></option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="probabilidad"
                                                       class="col-md-4 col-lg-3 col-form-label">Probabilidad de Dropear el Objeto (Numero Decimal)</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="probabilidad" type="text" class="form-control" id="probabilidad">
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="generoEnemigo"
                                                       class="col-md-4 col-lg-3 col-form-label">Genero (OPCIONAL)</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <select name="generoEnemigo" id="generoEnemigo" class="form-control">
                                                        <% for(Genero genero: listaGeneros){ %>
                                                        <option value="<%=genero.getIdGenero()%>"><%=genero.getNombreGenero()%></option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <div class="d-grid gap-2 col-6 col-lg-4 col-xl-3 mx-auto">
                                                    <button class="btn btn-secondary" type="submit">Añadir</button>
                                                </div>
                                            </div>

                                        </form>

                                    </div>
                                </div>
                            </div><!-- End Top Selling -->

                        </div>
                    </div><!-- End Left side columns -->

                </div>
            </section>

        </main><!-- End #main -->

        <!-- Vendor JS Files -->
        <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/chart.js/chart.min.js"></script>
        <script src="assets/vendor/echarts/echarts.min.js"></script>
        <script src="assets/vendor/quill/quill.min.js"></script>
        <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="assets/vendor/tinymce/tinymce.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>

    </body>
</html>

