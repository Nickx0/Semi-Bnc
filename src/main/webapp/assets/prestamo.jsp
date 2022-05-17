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
            <div class="text-center mt-4 name"> SOLICITAR PRESTAMO</div>

            <form class="p-3 mt-3" action="" method="post">
                <div class="form-field d-flex align-items-center">
                    <span class="far fa-user"></span>
                    <input type="text" name="cuotas" id="ncuotas" placeholder="Numero de Cuotas">
                </div>
                <div class="form-field d-flex align-items-center">
                    <span class="fas fa-key"></span>
                    <input type="number" name="monto" id="monto" placeholder="Monto a pedir">
                </div>
                <div class="form-field d-flex align-items-center coment">
                    <span class="fas fa-key"></span>
                    <textarea type="text" name="observaciones" id="motivo" placeholder="Motivo del pedido"></textarea>
                </div>
                <button class="btn mt-3">Solicitar</button>
            </form>
        </div>
    </body>
</html>