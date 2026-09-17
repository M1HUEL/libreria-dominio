package com.itson.libreria.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Autor {

	private int id;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private List<Libro> libros;

	public Autor() {
		this.libros = new ArrayList<>();
	}

	public Autor(int id, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros != null ? libros : new ArrayList<>();
	}

}
