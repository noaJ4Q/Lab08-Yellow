<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Wiki Fantástica</title>

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

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
            </div><!-- End Logo -->

        </header><!-- End Header -->

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
                    <a class="nav-link collapsed" href="<%=request.getContextPath()%>/MenuHechizos">
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
                    <div class="col-lg-8">
                        <div class="row">

                            <!-- Top Selling -->
                            <div class="col-12">
                                <div class="card top-selling overflow-auto">


                                    <div class="card-body pb-0">
                                        <h5 class="card-title">Wiki Fantastica</h5>

                                        <table class="table table-borderless">
                                            <tbody>
                                                <tr>
                                                    <td><a href="#" class="text-primary ">Agua, tierra, fuego y arie. Desde hace mucho tiempo el mundo se ha mantenido en paz y
                                                        prosperidad gracias al poder de los cristales elementales. Sin embargo, Forond, el rey de la
                                                        oscuridad se ha propuesto destruir estos cristales para ser el amo de la magia. Mold y su
                                                        grupo de jóvenes aventureros parten en una travesía para poder proteger los cristales y
                                                        vencer a Forond en su malvado plan</a></td>
                                                </tr>
                                            </tbody>
                                        </table>

                                    </div>

                                </div>
                            </div><!-- End Top Selling -->

                        </div>
                    </div><!-- End Left side columns -->

                    <!-- Right side columns -->
                    <div class="col-lg-4">

                        <!-- News & Updates Traffic -->
                        <div class="card">
                            <div class="card-body pb-0">
                                <h5 class="card-title">Entidades principales</h5>

                                <div class="news">
                                    <div class="post-item clearfix">
                                        <img src="WikiFantas.JPG" alt="">
                                        <h4><a href="#">Heroes</a></h4>
                                        <p>Los héroes serán el grupo de jóvenes, liderado por Mold y su hermana Erde, que están
                                            dispuestos a arriesgar su vida con el fin de detener los planes de Forond y así mantener la
                                            armonía del mundo. </p>
                                    </div>

                                    <div class="post-item clearfix">
                                        <img src="wkifant1.JPG" alt="">
                                        <h4><a href="#">Enemigos</a></h4>
                                        <p>Los enemigos son seres malignos dispuestos a destruir a los héroes. </p>
                                    </div>

                                    <div class="post-item clearfix">
                                        <img src="WikiFantas.JPG" alt="">
                                        <h4><a href="#">Hechizos</a></h4>
                                        <p>Los hechizos son unas poderosas armas elementales usadas  para poder librar la guerra en este mundo tan hostil. </p>
                                    </div>

                                </div><!-- End sidebar recent posts-->

                            </div>
                        </div><!-- End News & Updates -->

                    </div><!-- End Right side columns -->

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