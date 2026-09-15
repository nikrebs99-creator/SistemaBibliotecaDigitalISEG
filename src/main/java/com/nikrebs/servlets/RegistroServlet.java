package com.nikrebs.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.nikrebs.dao.UsuarioDAO;
import com.nikrebs.modelo.Usuario;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtenemos los datos del formulario
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // usuario temporal, reemplazamos luego con la info de registro.jsp
        Usuario nuevoUsuario = new Usuario(0, nombre, email, password, false);
        UsuarioDAO dao = new UsuarioDAO();
        
        // Intentamos registrar
        if (dao.registrarUsuario(nuevoUsuario)) {
            // Si funciona, lo mandamos al login con un mensaje de éxito
            response.sendRedirect("index.jsp?registro=exito");
        } else {
            // Si falla, lo devolvemos al registro con error
            response.sendRedirect("registro.jsp?error=1");
        }
    }
}