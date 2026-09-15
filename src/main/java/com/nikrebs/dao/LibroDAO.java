package com.nikrebs.dao;
import com.nikrebs.modelo.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private String url = "jdbc:mysql://localhost:3306/biblioteca_db";
    private String user = "root";
    private String pass = "1234";

    public List<Libro> listarTodos() {
        List<Libro> lista = new ArrayList<>();
        //CONSULTA SQL CON LEFT JOIN
        String sql = "SELECT l.*, u.nombre AS nombre_usuario FROM libros l LEFT JOIN usuarios u ON l.id_usuario = u.id";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Libro libro = new Libro(
                    rs.getInt("id"), 
                    rs.getString("titulo"), 
                    rs.getString("autor"), 
                    rs.getBoolean("disponible")
                );
                libro.setIdUsuario(rs.getInt("id_usuario")); 
                
                //Guardamos el nombre del usuario (si es nulo, guardará null)
                libro.setNombreUsuarioPrestamo(rs.getString("nombre_usuario")); 
                
                lista.add(libro);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    public void agregarLibro(Libro l) {
        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = con.prepareStatement("INSERT INTO libros (titulo, autor, disponible) VALUES (?, ?, ?)");
            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.setBoolean(3, true);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void prestarLibro(int idLibro, int idUsuario) {
        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = con.prepareStatement("UPDATE libros SET disponible = FALSE, id_usuario = ? WHERE id = ?");
            ps.setInt(1, idUsuario);
            ps.setInt(2, idLibro);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public void devolverLibro(int idLibro, int idUsuario) {
        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            //update libro devuelto
            PreparedStatement ps = con.prepareStatement("UPDATE libros SET disponible = TRUE, id_usuario = NULL WHERE id = ? AND id_usuario = ?");
            ps.setInt(1, idLibro);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public void eliminarLibro(int idLibro) {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idLibro);
            ps.executeUpdate();
            
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}