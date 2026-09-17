package com.itson.libreria.dominio;

import java.util.ArrayList;
import java.util.List;

public class Editorial {

	private int id;
	private String nombre;
	private String direccion;
	private List<Libro> libros;

	public Editorial() {
		this.libros = new ArrayList<>();
	}

	public Editorial(int id, String nombre, String direccion) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.libros = new ArrayList<>();
	}

	public void agregarLibro(Libro libro) {
		if (libro != null && !this.libros.contains(libro)) {
			this.libros.add(libro);
		}
	}

	public void removerLibro(Libro libro) {
		this.libros.remove(libro);
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros != null ? libros : new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Editorial{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", libros=" + libros + '}';
	}

}
