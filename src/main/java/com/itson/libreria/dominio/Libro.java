package com.itson.libreria.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Libro {

	private int id;
	private String titulo;
	private String isbn;
	private Categoria categoria;
	private String sinopsis;
	private String portada;
	private double precio;
	private int paginas;
	private LocalDate fechaPublicacion;
	private Editorial editorial;
	private List<Autor> autores;
	private boolean activo;

	public Libro() {
		this.autores = new ArrayList<>();
		this.activo = true;
	}

	public Libro(int id, String titulo, String isbn, Categoria categoria, String sinopsis, String portada, double precio, int paginas, LocalDate fechaPublicacion, Editorial editorial) {
		this.id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		this.categoria = categoria;
		this.sinopsis = sinopsis;
		this.portada = portada;
		this.precio = precio;
		this.paginas = paginas;
		this.fechaPublicacion = fechaPublicacion;
		this.editorial = editorial;
		this.autores = new ArrayList<>();
		this.activo = true;
	}

	public void agregarAutor(Autor autor) {
		if (autor != null && !this.autores.contains(autor)) {
			this.autores.add(autor);
		}
	}

	public void removerAutor(Autor autor) {
		this.autores.remove(autor);
	}

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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores != null ? autores : new ArrayList<>();
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
