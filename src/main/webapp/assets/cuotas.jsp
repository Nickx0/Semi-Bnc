<%@page import="modelo.cuota.Cuota"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cuotas</title>
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
        <div class="d-flex justify-content-center">
            <div class="p-3 prestamos-container">
                <div class="">   
                    <div class="d-flex justify-content-between">
                        <h2 class="">Cuotas a pagar</h2>
                        <div class="pt-2">
                            <a href="pagar-cuota?codigo=<% out.print(request.getParameter("codigo")); %>" class="btn btn-success">Pagar</a>
                        </div>
                    </div>
                    <hr class="m-2">
                </div>
                <%  
                    Cuota[] cuotas = (Cuota[])request.getAttribute("cuotas");
                    for(Cuota cuota: cuotas) {
                %>
                <div class=''><div class='card my-3'>
                        <div class='card-body'>
                            <div class='card-title'>
                                <h5 class='d-inline-block'>Cuota numero <% out.print(cuota.ncuota); %></h5>
                                <span class='ps-1'> <% out.print(cuota.plazo); %> </span>
                            </div>
                            <div class='card-text'><% out.print(cuota.neto); %></div>
                        </div>
                        <div class='btn-container'>
                            <% 
                                String class_, text;
                                
                               if (cuota.estado == 0) {
                                    class_ = "warning";
                                    text = "Atrasado";
                               } else if (cuota.estado == 1) {
                                    class_ = "secondary";
                                    text = "En curso";
                               } else {
                                    class_ = "success";
                                    text = "Pagado";
                               }
                            %>
                            <button class='btn btn-outline-<% out.print(class_); %>' style='pointer-events: none'><% out.print(text); %></button>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </main>
</body>
</html>