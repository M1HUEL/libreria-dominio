package com.itson.libreria.dominio;

public class DetalleOrden {

	private int id;
	private Orden orden;
	private Libro libro;
	private int cantidad;
	private double precioUnitario;

	public DetalleOrden() {
		// ...
	}

	public DetalleOrden(int id, Orden orden, Libro libro, int cantidad, double precioUnitario) {
		if (orden == null) {
			throw new IllegalArgumentException("El detalle de la orden debe estar asociado a una orden.");
		}
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad de la orden debe ser mayor a 0.");
		}
		this.id = id;
		this.libro = libro;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		orden.agregarDetalle(this);
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

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		if (this.orden != null && this.orden != orden) {
			this.orden.removerDetalle(this);
		}
		this.orden = orden;
		if (orden != null) {
			orden.agregarDetalle(this);
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
			throw new IllegalArgumentException("La cantidad de la orden debe ser mayor a 0.");
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
