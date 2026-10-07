package com.itson.libreria.dominio;

public class DetalleCarrito {

	private int id;
	private Carrito carrito;
	private Libro libro;
	private int cantidad;
	private double precioUnitario;

	public DetalleCarrito() {
		// ...
	}

	public DetalleCarrito(int id, Carrito carrito, Libro libro, int cantidad, double precioUnitario) {
		if (carrito == null) {
			throw new IllegalArgumentException("El detalle del carrito debe estar asociado a un carrito.");
		}
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad del carrito debe ser mayor a 0.");
		}
		this.id = id;
		this.libro = libro;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		carrito.agregarItem(this);
	}

	public double getSubtotal() {
		return cantidad * precioUnitario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		if (this.carrito != null && this.carrito != carrito) {
			this.carrito.removerItem(this);
		}
		this.carrito = carrito;
		if (carrito != null) {
			carrito.agregarItem(this);
		}
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
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad del carrito debe ser mayor a 0.");
		}
		this.cantidad = cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

}
