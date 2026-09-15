package com.nikrebs.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import com.nikrebs.dao.LibroDAO;
import com.nikrebs.modelo.Libro;
import com.nikrebs.modelo.Usuario;

@WebServlet("/LibroServlet")
public class LibroServlet extends HttpServlet {
    private LibroDAO dao = new LibroDAO();
    // protected
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Libro> libros = dao.listarTodos();
        request.setAttribute("libros", libros);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");

        if (user != null) { //osea que si hay usuario
            if ("agregar".equals(accion) && user.isEsAdmin()) { //doblecheck de que sea admin, el primero es en dashboard
                String titulo = request.getParameter("titulo");
                String autor = request.getParameter("autor");
                dao.agregarLibro(new Libro(0, titulo, autor, true));
            } else if ("prestar".equals(accion)) {
                int idLibro = Integer.parseInt(request.getParameter("idLibro"));
                dao.prestarLibro(idLibro, user.getId());
            }
            else if ("devolver".equals(accion)) {
                int idLibro = Integer.parseInt(request.getParameter("idLibro"));
                dao.devolverLibro(idLibro, user.getId());
            }
            else if ("eliminar".equals(accion) && user.isEsAdmin()) {
                int idLibro = Integer.parseInt(request.getParameter("idLibro"));
                dao.eliminarLibro(idLibro);
            }
        }
        
        
        response.sendRedirect("LibroServlet");
    }
}