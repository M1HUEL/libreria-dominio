package com.itson.libreria.dominio;

import java.util.ArrayList;
import java.util.List;

public class ItemStock {

	private int id;
	private Libro libro;
	private int cantidad;
	private int stockMinimo;
	private List<AjusteInventario> ajustes;

	public ItemStock() {
		this.cantidad = 0;
		this.ajustes = new ArrayList<>();
	}

	public ItemStock(int id, Libro libro, int cantidadInicial) {
		if (cantidadInicial < 0) {
			throw new IllegalArgumentException("La cantidad inicial de stock no puede ser negativa.");
		}
		this.id = id;
		this.cantidad = cantidadInicial;
		this.ajustes = new ArrayList<>();
		setLibro(libro);
	}

	public void registrarAjuste(AjusteInventario ajuste) {
		if (ajuste == null) {
			throw new IllegalArgumentException("El ajuste de inventario no puede ser nulo.");
		}
		if (!this.ajustes.contains(ajuste)) {
			this.ajustes.add(ajuste);
		}
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
		if (this.libro != null && this.libro != libro) {
			this.libro.removerStock(this);
		}
		this.libro = libro;
		if (libro != null) {
			libro.agregarStock(this);
		}
	}

	public List<AjusteInventario> getAjustes() {
		return ajustes;
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
