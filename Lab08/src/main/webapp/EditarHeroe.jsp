<%@ page import="com.example.lab08.MenuEnemigos.Beans.Heroe" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: noqe2
  Date: 05/11/2022
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Heroe heroe = (Heroe) request.getAttribute("heroeEditar");
    ArrayList<Heroe> listaParejas = (ArrayList<Heroe>) request.getAttribute("listaParejas");
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Editar Héroe / Wiki Fantástica</title>

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
                <a href="<%=request.getContextPath()%>/" class="logo d-flex align-items-center">
                    <span class="d-none d-lg-block">Wiki Fantástica</span>
                </a>
                <i class="bi bi-list toggle-sidebar-btn"></i>
            </div>
            <!-- End Logo -->

            <!-- BARRA DE BÚSQUEDA -->
            <div class="search-bar">
                <form class="search-form d-flex align-items-center" method="POST" action="<%=request.getContextPath()%>/MenuHeroes?action=buscar">
                    <input type="text" name="busqueda" placeholder="Buscar Héroe" value="">
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
                    <a class="nav-link collapsed" href="<%=request.getContextPath()%>/">
                        <i class="bi-grid-fill"></i>
                        <span>MENÚ PRINCIPAL</span>
                    </a>
                </li>
                <!-- End Dashboard Nav -->

                <li class="nav-heading">Entidades</li>

                <li class="nav-item">
                    <a class="nav-link collapsed" href="<%=request.getContextPath()%>/MenuHeroes">
                        <i class="ri-t-shirt-2-fill"></i>
                        <span>Héroes</span>
                    </a>
                </li>
                <!-- End Profile Page Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" href="<%=request.getContextPath()%>/MenuEnemigos">
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

                <li class="nav-item">
                    <a class="nav-link collapsed" href="pages-register.html">
                        <i class="ri-book-fill"></i>
                        <span>Objetos</span>
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
                                        <h5 class="card-title">EDITAR HÉROE</h5>
                                        <form method="post"
                                              action="<%=request.getContextPath()%>/MenuHeroes?action=actualizar">

                                            <input name="idHeroe" type="hidden" class="form-control" id="idHeroe"
                                                   value="<%=heroe.getIdHeroe()%>">

                                            <div class="row mb-3">
                                                <label for="nombre"
                                                       class="col-md-4 col-lg-3 col-form-label">Nombre</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="nombre" type="text" class="form-control" id="nombre"
                                                           value="<%=heroe.getNombre()%>">
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="edad"
                                                       class="col-md-4 col-lg-3 col-form-label">Edad</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="edad" type="text" class="form-control" id="edad"
                                                           value="<%=heroe.getEdad()%>">
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="genero"
                                                       class="col-md-4 col-lg-3 col-form-label">Género</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <select class="form-select" id="genero" name="genero">
                                                        <option value="<%=heroe.getGenero().getIdGenero()%>" selected>Seleccione un género</option>
                                                        <option value="M">Masculino</option>
                                                        <option value="F">Femenino</option>
                                                        <option value="O">Otro</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="clase"
                                                       class="col-md-4 col-lg-3 col-form-label">Clase</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="clase" type="text" class="form-control" id="clase" value="<%=heroe.getClase()%>">
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="nivel"
                                                       class="col-md-4 col-lg-3 col-form-label">Nivel</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="nivel" type="text" class="form-control" id="nivel" value="<%=heroe.getNivel()%>">
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="ataque"
                                                       class="col-md-4 col-lg-3 col-form-label">Ataque</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="ataque" type="text" class="form-control" id="ataque" value="<%=heroe.getAtaque()%>">
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="idPareja"
                                                       class="col-md-4 col-lg-3 col-form-label">Pareja (Opciones
                                                    disponibles)</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <select class="form-select" id="idPareja" name="idPareja">
                                                        <option value="<%=heroe.getPareja().getIdHeroe()%>" selected>Seleccione el ID de la pareja</option>
                                                        <%
                                                            for (Heroe h : listaParejas) {
                                                        %>
                                                        <option value="<%=h.getIdHeroe()%>"><%=h.getIdHeroe()%>
                                                        </option>
                                                        <%}%>
                                                        <option value="0">0 (Sin pareja)</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <div class="d-grid gap-2 col-6 col-lg-4 col-xl-3 mx-auto">
                                                    <button class="btn btn-secondary" type="submit">Guardar</button>
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
