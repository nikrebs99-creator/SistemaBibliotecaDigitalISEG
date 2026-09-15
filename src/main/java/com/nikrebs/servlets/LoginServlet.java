package com.nikrebs.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.nikrebs.dao.UsuarioDAO;
import com.nikrebs.modelo.Usuario;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        
        UsuarioDAO dao = new UsuarioDAO();
        Usuario user = dao.autenticar(email, pass);
        
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user);
            response.sendRedirect("LibroServlet"); // Redirige al dashboard de libros
        } else {
            response.sendRedirect("index.jsp?error=1");
        }
    }
}
