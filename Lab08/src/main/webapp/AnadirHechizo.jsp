<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab08.WIkiFantastica.Beans.Heroe" %>
<%@ page import="com.example.lab08.WIkiFantastica.Beans.Elemento" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Elemento> listaElemento = (ArrayList<Elemento>) request.getAttribute("listaElemento");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Añadir Hechizo / Wiki Fantástica</title>

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
                    <span class="d-none d-lg-block">Anadir Hechizo</span>
                </a>
                <i class="bi bi-list toggle-sidebar-btn"></i>
            </div>
            <!-- End Logo -->

            <!-- BARRA DE BÚSQUEDA -->
            <div class="search-bar">
                <form class="search-form d-flex align-items-center align-middle" method="POST"
                      action="<%=request.getContextPath()%>/MenuHechizos?action=buscar">
                    <input type="text" name="busqueda" placeholder="Buscar Hechizo">
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
                    <a class="nav-link collapsed" href="<%=request.getContextPath()%>/MenuHechizos">
                        <i class="ri-t-shirt-2-fill"></i>
                        <span>Hechizos</span>
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
                    <a class="nav-link collapsed" href="<%=request.getContextPath()%>/MenuHeroes">
                        <i class="ri-ink-bottle-fill"></i>
                        <span>Heroes</span>
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
                                        <h5 class="card-title">AÑADIR HECHIZO</h5>
                                        <form method="post" action="<%=request.getContextPath()%>/MenuHechizos?action=guardar">

                                            <div class="row mb-3">
                                                <label for="nombre"
                                                       class="col-md-4 col-lg-3 col-form-label">Nombre</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="nombre" type="text" class="form-control" id="nombre" required>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="elementoHechizo"
                                                       class="col-md-4 col-lg-3 col-form-label">Elemento</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <select name="elementoHechizo" id="elementoHechizo" class="form-select">
                                                        <% for(Elemento elemento: listaElemento){ %>
                                                        <option value="<%=elemento.getIdElemento()%>"><%=elemento.getNombreElemento()%></option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                            </div>


                                            <div class="row mb-3">
                                                <label for="potencia"
                                                       class="col-md-4 col-lg-3 col-form-label">Potencia</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="potencia" type="text" class="form-control" id="potencia" required>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="precision"
                                                       class="col-md-4 col-lg-3 col-form-label">Precision</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="precision" type="text" class="form-control" id="precision" required>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="hechizoBase"
                                                       class="col-md-4 col-lg-3 col-form-label">Hechizo Base</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="hechizoBase" type="text" class="form-control" id="potencia" required>
                                                </div>
                                            </div>


                                            <div class="row mb-3">
                                                <label for="hechizoBase"
                                                       class="col-md-4 col-lg-3 col-form-label">Hechizo Base</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <select name="elementoHechizo" id="elementoHechizo" class="form-select">
                                                        <% for(Elemento elemento: listaElemento){ %>
                                                        <option value="<%=elemento.getIdElemento()%>"><%=elemento.getNombreElemento()%></option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="idPareja"
                                                       class="col-md-4 col-lg-3 col-form-label">Pareja (Opciones
                                                    disponibles)</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <select class="form-select" id="idPareja" name="idPareja" required>
                                                        <option value="0" selected>Seleccione el ID de la pareja</option>
                                                        <%
                                                            for (Heroe heroe : listaParejas) {
                                                        %>
                                                        <option value="<%=heroe.getIdHeroe()%>"><%=heroe.getIdHeroe()%></option>
                                                        <%}%>
                                                        <option value="0">Sin pareja</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label for="nivelAprendizaje"
                                                       class="col-md-4 col-lg-3 col-form-label">Nivel de Aprendizaje</label>
                                                <div class="col-md-8 col-lg-9">
                                                    <input name="nivelAprendizaje" type="text" class="form-control" id="nivelAprendizaje" required>
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
