<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nikrebs.modelo.Libro" %>
<%@ page import="com.nikrebs.modelo.Usuario" %>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    if(user == null) { response.sendRedirect("index.jsp"); return; }
    List<Libro> libros = (List<Libro>) request.getAttribute("libros");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>¡Biblioteca!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #000000;
            color: #ffffff;
        }
        .admin-panel {
            background-color: #1a1a1a;
            border-left: 4px solid #6f42c1;
        }
        /* Modo oscuro */
        .table-dark {
            --bs-table-bg: #121212;
            --bs-table-striped-bg: #1a1a1a;
            border-color: #333;
        }
        .table-custom-header {
            border-bottom: 2px solid #6f42c1 !important;
        }
        .form-control::placeholder {
            color: #ffffff;
            opacity: 0.5;
        }
        .btn-purple {
            background-color: #6f42c1;
            color: #ffffff;
            border: none;
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
        .form-control:focus {
            background-color: #2a2a2a;
            color: #ffffff;
            border-color: #6f42c1;
            box-shadow: 0 0 0 0.25rem rgba(111, 66, 193, 0.25);
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        
        <!-- header -->
        <div class="d-flex justify-content-between align-items-center mb-5 border-bottom border-secondary pb-3">
            <h2>Hola, <span style="color: #cda4ff;"><%= user.getNombre() %></span> 
                <span class="fs-5 text-muted">(<%= user.isEsAdmin() ? "Administrador/a" : "" %>)</span>
            </h2>
            <!-- cierre sesion boton -->
            <a href="index.jsp" class="btn btn-outline-light btn-sm">Cerrar Sesión</a>
        </div>
        
        <!-- Botones nuevos si el usuario es admin -->
        <% if(user.isEsAdmin()) { %>
            <div class="admin-panel p-4 rounded-4 mb-5 shadow-sm">
                <h4 class="mb-3">Añadir Nuevo Libro</h4>
                <form action="LibroServlet" method="POST" class="row g-3 align-items-center">
                    <input type="hidden" name="accion" value="agregar">
                    <div class="col-md-5">
                        <input type="text" class="form-control" name="titulo" placeholder="Título del libro" required>
                    </div>
                    <div class="col-md-5">
                        <input type="text" class="form-control" name="autor" placeholder="Autor del libro" required>
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-purple w-100 fw-bold">Añadir Libro</button>
                    </div>
                </form>
            </div>
        <% } %>

        <!-- formulario lista libros -->
        <h3 class="mb-4">Catálogo de Libros</h3>
        <div class="table-responsive rounded-4 shadow-lg overflow-hidden">
            <table class="table table-dark table-striped table-hover align-middle mb-0">
                <thead class="table-custom-header text-uppercase text-secondary small">
                    <tr>
                        <th class="ps-4">ID</th>
                        <th>Título</th>
                        <th>Autor</th>
                        <th>Estado</th>
                        <th class="text-center pe-4">Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Libro l : libros) { %>
                    <tr>
                        <td class="ps-4 text-white"><%= l.getId() %></td>
                        <td class="fw-bold"><%= l.getTitulo() %></td>
                        <td><%= l.getAutor() %></td>
                        <td>
                            <% if(l.isDisponible()) { %>
                                <span class="badge bg-success">Disponible</span>
                            <% } else { %>
                                <span class="badge bg-secondary">Prestado</span>
                            <% } %>
                        </td>
                        <td class="text-center pe-4">
	<!-- Contenedor flexible -->
    <div class="d-flex justify-content-center align-items-center gap-2">
        
        <!-- if libro está disponible, que se pueda pedir -->
        <% if(l.isDisponible()) { %>
            <form action="LibroServlet" method="POST" class="m-0">
                <input type="hidden" name="accion" value="prestar">
                <input type="hidden" name="idLibro" value="<%= l.getId() %>">
                <button type="submit" class="btn btn-purple btn-sm">Pedir Prestado</button>
            </form>
        <% } else if(l.getIdUsuario() == user.getId()) { %>
            <form action="LibroServlet" method="POST" class="m-0">
                <input type="hidden" name="accion" value="devolver">
                <input type="hidden" name="idLibro" value="<%= l.getId() %>">
                <button type="submit" class="btn btn-outline-danger btn-sm">Devolver Libro</button>
            </form>
        <% } else { %>
            <% if(user.isEsAdmin()) { %>
                <!-- si es admin, puede ver el nombre -->
                <span class="text-warning small fw-bold">Prestado a: <%= l.getNombreUsuarioPrestamo() %></span>
            <% } else { %>
                <!-- otro usuario no puede ver quien lo tiene prestado -->
                <span class="text-warning small">En manos de otro usuario</span>
            <% } %>	
        <% } %>

        <!-- Boton eliminar si el usuario es admin -->
        <% if(user.isEsAdmin()) { %>
            <!-- onsubmit para doble-check -->
            <form action="LibroServlet" method="POST" class="m-0" onsubmit="return confirm('¿Estás seguro de que deseas eliminar este libro?');">
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="idLibro" value="<%= l.getId() %>">
                <button type="submit" class="btn btn-danger btn-sm" title="Eliminar libro">🗑️</button> <!-- icono chistoso intuitivo -->
            </form>
        <% } %>
        
    </div>
</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>