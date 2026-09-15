<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login Biblioteca</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
 	<style>
        body {
            background-color: #000000;
            color: #ffffff;
        }
        .login-container {
            background-color: #1a1a1a; /* gris oscuro */
            border: 2px solid #6f42c1; /* Borde morado*/
            border-radius: 1.5rem; /* Bordes redondeados */
            box-shadow: 0 0 20px rgba(111, 66, 193, 0.2); /* brillo bajo*/
        }
        .btn-purple {
            background-color: #6f42c1;
            color: #ffffff;
            border: none;
            transition: 0.3s;
        }
        .btn-purple:hover {
            background-color: #59339d;
            color: #ffffff;
        }
        .form-control {
            background-color: #2a2a2a;
            color: #ffffff;
            border: 1px solid #444;
        }
		.form-control::placeholder {
            color: #ffffff;
            opacity: 0.5; /* mejor opacidad */
        }
        .form-control:focus {
            background-color: #2a2a2a;
            color: #ffffff;
            border-color: #6f42c1;
            box-shadow: 0 0 0 0.25rem rgba(111, 66, 193, 0.25);
        }
    </style>
</head>
<body class="d-flex align-items-center justify-content-center vh-100">
    <div class="login-container p-5 w-100" style="max-width: 420px;">
        <h2 class="text-center mb-4 text-white fw-bold">Biblioteca Digital</h2>
        
        <% if(request.getParameter("error") != null) { %>
            <div class="alert alert-danger p-2 text-center" role="alert">
                Ingrese usuario y contraseña reales
            </div>
        <% } %>
        
        <form action="LoginServlet" method="POST">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="correo@servicio.com" required>
            </div>
            <div class="mb-4">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="******" required>
            </div>
            <button type="submit" class="btn btn-purple w-100 py-2 fw-bold">Entrar</button>
        </form>
        <!-- Añade esto justo debajo de </form> en tu index.jsp -->
        <% if("exito".equals(request.getParameter("registro"))) { %>
            <div class="alert alert-success p-2 mt-3 text-center" role="alert">
                ¡Éxito! ahora puedes iniciar sesión.
            </div>
        <% } %>

        <div class="text-center mt-3">
            <a href="registro.jsp" class="text-decoration-none" style="color: #cda4ff;">¿Usuario nuevo? ¡click aquí!</a>
        </div>
    </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>