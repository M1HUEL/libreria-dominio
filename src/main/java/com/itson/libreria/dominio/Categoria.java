package com.itson.libreria.dominio;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private int id;
	private String nombre;
	private List<Libro> libros;

	public Categoria() {
		this.libros = new ArrayList<>();
	}

	public Categoria(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
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

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros != null ? libros : new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Categoria{" + "id=" + id + ", nombre=" + nombre + ", libros=" + libros + '}';
	}

}
