package com.itson.libreria.dominio;

public class ItemStock {

	private int id;
	private Libro libro;
	private int cantidad;
	private int stockMinimo;

	public ItemStock() {
		this.cantidad = 0;
	}

	public ItemStock(int id, Libro libro, int cantidadInicial) {
		if (cantidadInicial < 0) {
			throw new IllegalArgumentException("La cantidad inicial de stock no puede ser negativa.");
		}
		this.id = id;
		this.libro = libro;
		this.cantidad = cantidadInicial;
	}

	public void agregar(int cantidad) {
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad a agregar debe ser mayor a 0.");
		}
		this.cantidad += cantidad;
	}

	public void disminuir(int cantidad) {
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad a disminuir debe ser mayor a 0.");
		}
		if (cantidad > this.cantidad) {
			throw new IllegalStateException("Stock insuficiente del libro '" + (libro != null ? libro.getTitulo() : null) + "': disponible " + this.cantidad + ", solicitado " + cantidad + ".");
		}
		this.cantidad -= cantidad;
	}

	public boolean hayDisponibilidad(int cantidad) {
		return cantidad > 0 && this.cantidad >= cantidad;
	}

	public boolean estaEnStockBajo() {
		return cantidad <= stockMinimo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		if (cantidad < 0) {
			throw new IllegalArgumentException("La cantidad de stock no puede ser negativa.");
		}
		this.cantidad = cantidad;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		if (stockMinimo < 0) {
			throw new IllegalArgumentException("El stock mínimo no puede ser negativo.");
		}
		this.stockMinimo = stockMinimo;
	}

}
