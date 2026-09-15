package com.nikrebs.dao;
import com.nikrebs.modelo.Usuario;
import java.sql.*;

public class UsuarioDAO {
    private String url = "jdbc:mysql://localhost:3306/biblioteca_db"; // base de datos de prueba	
    private String user = "root"; // user
    private String pass = "1234"; // pw 

    public Usuario autenticar(String email, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("password"), rs.getBoolean("es_admin"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean registrarUsuario(Usuario u) {
        String sql = "INSERT INTO usuarios (nombre, email, password, es_admin) VALUES (?, ?, ?, false)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas > 0; // true si guardado
            
        } catch (Exception e) { // guardado
            e.printStackTrace();
            return false;
        }
    }


}