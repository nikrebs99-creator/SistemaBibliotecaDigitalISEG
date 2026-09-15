package com.nikrebs.modelo;

public class Libro {
    private int id; // autoincrement en sql, no su numero en lista
    private String titulo;
    private String autor;
    private boolean disponible;
    private int idUsuario; //tracker de quien tiene el libro
    private String nombreUsuarioPrestamo;

    public Libro(int id, String titulo, String autor, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
    }

    // Getters y Setters zzz
    public int getId() { 
    	return id; 
    	}
    public void setId(int id) { 
    	this.id = id; 
    	}
    public String getTitulo() { 
    	return titulo; 
    	}
    public void setTitulo(String titulo) {
    	this.titulo = titulo; 
    	}
    public String getAutor() { 
    	return autor; 
    	}
    public void setAutor(String autor) { 
    	this.autor = autor; 
    	}
    public boolean isDisponible() { 
    	return disponible; 
    	}
    public void setDisponible(boolean disponible) { 
    	this.disponible = disponible; 
    	}
    public int getIdUsuario() { 
    	return idUsuario; 
    	}
    public void setIdUsuario(int idUsuario) { 
    	this.idUsuario = idUsuario; 
    	}
    public String getNombreUsuarioPrestamo() { 
    	return nombreUsuarioPrestamo; 
    	}
    public void setNombreUsuarioPrestamo(String nombreUsuarioPrestamo) { 
    	this.nombreUsuarioPrestamo = nombreUsuarioPrestamo; 
    	}

}