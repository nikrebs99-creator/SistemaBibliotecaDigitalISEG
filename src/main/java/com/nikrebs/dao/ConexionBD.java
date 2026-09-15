package com.nikrebs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión a la base de datos usando el Patrón Singleton.
 */
public class ConexionBD {
    
    // biblioteca_db es mi base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_db";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";
    private static Connection conexion = null; //validar conexion
    
    /**
     * Conexion a SQL
     */
    private ConexionBD() {
        try {
          
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Output en consola para saber que está todo correcto en la bd
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("¡Conexión a MySQL establecida con éxito!");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error crítico: No se encontró el Driver JDBC de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
        	// apareció más veces de lo que me esperaba, resulta que tenia mal la pw en los dao xd

            System.out.println("Error crítico: Fallo al intentar conectar con la base de datos.");
            e.printStackTrace();
        }
    }
    
    /** 
     * @return La conexión activa a la base de datos.
     */
    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                new ConexionBD();
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el estado de la conexión.");
            e.printStackTrace();
        }
        return conexion;
    }
}