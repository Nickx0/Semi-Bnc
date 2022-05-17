<%@page import="modelo.solicitud.Solicitud"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="./resources/structure.css">
    <link rel="stylesheet" href="./resources/profile.css">
</head>
<body>
    <header class="d-flex justify-content-between">
        <a href="profile">
            <img src="https://proactivo.com.pe/wp-content/uploads/2014/04/bcp-logo.jpg" alt="" class="logo">
        </a>
        <div class="py-3 pe-2"><a href="logout" class="btn btn-danger">Logout</a></div>
    </header>
    <main>
        <div class="p-3 profile-grid-container">
            <div>
                <img src="https://www.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png"
                alt="" 
                class="profile-img">
            </div>
            <div class="">
                <h4 ><% out.print(request.getAttribute("user")); %></h4>
                <hr class="m-1">
                <h6>Asociado</h6>
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <div class="p-3 prestamos-container">
                <div class="">   
                    <div class="d-flex justify-content-between">
                        <h2 class="">Prestamos</h2>
                        <div class="pt-2">
                            <a href="prestamo" class="btn btn-success">Solicitar Nuevo Prestamo</a>
                        </div>
                    </div>
                    <hr class="m-2">
                </div>
                <div class=''>
                    <%
                        Solicitud[] solicitudes = (Solicitud[])request.getAttribute("solicitudes");
                        
                        for(Solicitud solicitud: solicitudes) {
                    %>
                
                    <div class='card my-3'>
                        <div class='card-body'>
                            <div class='card-title'>
                                <h5 class='d-inline-block'>S/. <% out.print(solicitud.monto); %></h5>
                                <span class='ps-1'> <% out.print(solicitud.fecha); %> </span>
                            </div>
                            <div class='card-text'><% out.print(solicitud.observaciones); %></div>
                        </div>
                        <div class='btn-container'>
                            <a class='btn btn-outline-success' href="cuotas?codigo=<% out.print(solicitud.cod); %>">Pagar</a>
                        </div>
                    </div>
                
                    <%}%>
                </div>
                <%
                    String class_ = "", message = "";
                    String state = request.getParameter("state") + "";
                    if (state.equals("fail")) {
                        class_ = "danger";
                        message = "No se pudo crear el prestamo";
                    } else if (state.equals("created")) {
                        class_ = "success";
                        message = "Prestamo creado";
                    }

                %>
                <p class="alert alert-<% out.print(class_); if (class_.equals("")) out.print("d-none"); %>" role="alert"><% out.print(message); %></p>
            </div>
        </div> 
    </main>
</body>
</html>