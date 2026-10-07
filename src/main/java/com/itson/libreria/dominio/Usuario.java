package com.itson.libreria.dominio;

public class Usuario {

	private int id;
	private String nombre;
	private String correo;
	private String contrasena;
	private String telefono;
	private Rol rol;

	public Usuario() {
		// ...
	}

	public Usuario(int id, String nombre, String correo, String contrasena, String telefono, Rol rol) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
		setTelefono(telefono);
		this.rol = rol;
	}

	public boolean esAdministrador() {
		return rol == Rol.ADMINISTRADOR;
	}

	public boolean puedeComprar() {
		return rol == Rol.CLIENTE || rol == Rol.ADMINISTRADOR;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null || telefono.isBlank()) {
			throw new IllegalArgumentException("El telefono del usuario no puede estar vacio.");
		}
		this.telefono = telefono;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
