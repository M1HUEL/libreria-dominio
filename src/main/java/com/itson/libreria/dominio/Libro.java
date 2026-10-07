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
	private List<ItemStock> stocks;
	private boolean activo;

	public Libro() {
		this.autores = new ArrayList<>();
		this.stocks = new ArrayList<>();
		this.activo = true;
	}

	public Libro(int id, String titulo, String isbn, Categoria categoria, String sinopsis, String portada, double precio, int paginas, LocalDate fechaPublicacion, Editorial editorial) {
		this.autores = new ArrayList<>();
		this.stocks = new ArrayList<>();
		this.activo = true;
		this.id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		setCategoria(categoria);
		this.sinopsis = sinopsis;
		this.portada = portada;
		this.precio = precio;
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

	public void agregarStock(ItemStock stock) {
		if (stock != null && !this.stocks.contains(stock)) {
			this.stocks.add(stock);
			stock.setLibro(this);
		}
	}

	public void removerStock(ItemStock stock) {
		if (stock == null) {
			return;
		}
		if (this.stocks.remove(stock) && stock.getLibro() == this) {
			stock.setLibro(null);
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

	public List<ItemStock> getStocks() {
		return stocks;
	}

	public void setStocks(List<ItemStock> stocks) {
		this.stocks = stocks != null ? stocks : new ArrayList<>();
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
