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
	private int paginas;
	private LocalDate fechaPublicacion;
	private Editorial editorial;
	private List<Autor> autores;
	private List<FormatoLibro> formatos;
	private boolean activo;

	public Libro() {
		this.autores = new ArrayList<>();
		this.formatos = new ArrayList<>();
		this.activo = true;
	}

	public Libro(int id, String titulo, String isbn, Categoria categoria, String sinopsis, String portada, int paginas, LocalDate fechaPublicacion, Editorial editorial) {
		this.autores = new ArrayList<>();
		this.formatos = new ArrayList<>();
		this.activo = true;
		this.id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		setCategoria(categoria);
		this.sinopsis = sinopsis;
		this.portada = portada;
		this.paginas = paginas;
		this.fechaPublicacion = fechaPublicacion;
		setEditorial(editorial);
	}

	public void agregarAutor(Autor autor) {
		if (autor != null && !this.autores.contains(autor)) {
			this.autores.add(autor);
			autor.agregarLibro(this);
		}
	}

	public void removerAutor(Autor autor) {
		if (autor != null && this.autores.remove(autor)) {
			autor.removerLibro(this);
		}
	}

	public void agregarFormato(FormatoLibro formatoLibro) {
		if (formatoLibro == null) {
			throw new IllegalArgumentException("El formato del libro no puede ser nulo.");
		}
		for (FormatoLibro existente : this.formatos) {
			if (existente != formatoLibro && existente.getFormato() == formatoLibro.getFormato()) {
				throw new IllegalStateException("El libro ya tiene el formato " + formatoLibro.getFormato() + ".");
			}
		}
		if (!this.formatos.contains(formatoLibro)) {
			this.formatos.add(formatoLibro);
		}
		if (formatoLibro.getLibro() != this) {
			formatoLibro.setLibro(this);
		}
	}

	public void removerFormato(FormatoLibro formatoLibro) {
		if (formatoLibro == null) {
			return;
		}
		if (this.formatos.remove(formatoLibro) && formatoLibro.getLibro() == this) {
			formatoLibro.setLibro(null);
		}
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
		if (this.categoria != null && this.categoria != categoria) {
			this.categoria.removerLibro(this);
		}
		this.categoria = categoria;
		if (categoria != null && !categoria.getLibros().contains(this)) {
			categoria.getLibros().add(this);
		}
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
		if (this.editorial != null && this.editorial != editorial) {
			this.editorial.removerLibro(this);
		}
		this.editorial = editorial;
		if (editorial != null && !editorial.getLibros().contains(this)) {
			editorial.getLibros().add(this);
		}
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores != null ? autores : new ArrayList<>();
	}

	public List<FormatoLibro> getFormatos() {
		return formatos;
	}

	public void setFormatos(List<FormatoLibro> formatos) {
		this.formatos = formatos != null ? formatos : new ArrayList<>();
	}

	public FormatoLibro getFormato(Formato formato) {
		return formatos.stream().filter(f -> f.getFormato() == formato).findFirst().orElse(null);
	}

	public List<ItemStock> getStocks() {
		return formatos.stream().map(FormatoLibro::getStock).filter(s -> s != null).toList();
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
