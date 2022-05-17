<%@page import="modelo.solicitud.Solicitud"%>
<%@page import="modelo.profile.Asociado"%>
<%@page import="modelo.cuota.Cuota"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pagar Cuota</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="./resources/structure.css">
    </head>
    <body> 
        <header>
            <a href="profile">
                <img src="https://proactivo.com.pe/wp-content/uploads/2014/04/bcp-logo.jpg" alt="" class="logo">
            </a>
        </header>
        
        <%  
            Cuota cuota = (Cuota)request.getAttribute("cuota");
            Asociado asociado = (Asociado)request.getAttribute("asociado");
            Solicitud solicitud = (Solicitud)request.getAttribute("solicitud");
        %>
        
       <div class="card border-warning mb-3" style="width: 65%; margin: 3% auto;">
           <div class="card-header">Usuario <% out.print(asociado.nombre + " " + asociado.apellido); %></div>
                <div class="card-body text-warning">
                <h5 class="card-title">Desea proceder con el pago de la siguiente cuota?</h5>
                <p class="card-text">Usted Cuenta con un saldo de <% out.print(asociado.saldo); %></p>
            </div>
        </div>
        
        <div class="card" style="width: 80%; margin: 5% auto;">
            <div class="card-header">
                Cuota numero <% out.print(cuota.ncuota); %><span>     </span><% out.print(cuota.plazo); %>
            </div>
        <div class="card-body">
            <h5 class="card-title">Solicitud Nº<% out.print(solicitud.cod); %></h5>
            <p class="card-text">Al final de la operacion contara con un saldo de <% out.print(asociado.saldo - cuota.neto); %></p>
            <form action="" method="post">
                <input type="hidden" name="codigo" value="<% out.print(request.getParameter("codigo")); %>">
                <button type="submit" class="btn btn-primary">Proceder al Pago</button>    
            </form>
        </div>
        </div>
    </body>
</html>