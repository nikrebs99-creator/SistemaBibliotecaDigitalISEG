package com.nikrebs.modelo;
import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private boolean esAdmin;
    private ArrayList<Libro> librosPrestados = new ArrayList<>();

    public Usuario(int id, String nombre, String email, String password, boolean esAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.esAdmin = esAdmin;
    }

    // Getters y Setters
    public int getId() { 
    	return id; 
    	}
    public String getNombre() { 
    	return nombre; 
    	}
    public String getEmail() { 
    	return email; 
    	}
    public String getPassword() { 
    	return password; 
    	}
    public boolean isEsAdmin() { 
    	return esAdmin; 
    	}
    public ArrayList<Libro> getLibrosPrestados() { 
    	return librosPrestados; 
    	}
    public void addLibroPrestado(Libro libro) { 
    	this.librosPrestados.add(libro); 
    	}
    
}