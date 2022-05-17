<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./resources/styles.css">
    </head>
    <body>
        <div class="wrapper">
            <div class="logo">
                <img src="https://proactivo.com.pe/wp-content/uploads/2014/04/bcp-logo.jpg" alt="">
            </div>
            <div class="text-center mt-4 name">BANCO</div>

            <form class="p-3 mt-3" action="login" method="post">
                <div class="form-field d-flex align-items-center">
                    <span class="far fa-user"></span>
                    <input type="text" name="username" id="username" placeholder="Codigo">
                </div>
                <div class="form-field d-flex align-items-center">
                    <span class="fas fa-key"></span>
                    <input type="password" name="password" id="pwd" placeholder="Contraseña">
                </div>
                <button class="btn mt-3">Login</button>
            </form>
            <% 
                if(request.getAttribute("failed") != null) {
                    out.print("<p style=\"color:red;\">Usuario o Contraseña invalidos</p>");
                }
            %>
        </div>
    </body>
</html>