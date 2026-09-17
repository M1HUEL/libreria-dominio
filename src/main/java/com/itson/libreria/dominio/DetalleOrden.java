package com.itson.libreria.dominio;

public class DetalleOrden {

	private int id;
	private OrdenCompra orden;
	private Libro libro;
	private int cantidad;
	private double precioUnitario;

	public DetalleOrden() {
	}

	public DetalleOrden(int id, OrdenCompra orden, Libro libro, int cantidad, double precioUnitario) {
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
		}
		if (precioUnitario < 0) {
			throw new IllegalArgumentException("El precio unitario no puede ser negativo.");
		}
		this.id = id;
		this.orden = orden;
		this.libro = libro;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
	}

	public double calcularSubtotal() {
		return precioUnitario * cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrdenCompra getOrden() {
		return orden;
	}

	public void setOrden(OrdenCompra orden) {
		this.orden = orden;
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
			throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
		}
		this.cantidad = cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		if (precioUnitario < 0) {
			throw new IllegalArgumentException("El precio unitario no puede ser negativo.");
		}
		this.precioUnitario = precioUnitario;
	}

	@Override
	public String toString() {
		return "DetalleOrden{" + "id=" + id + ", orden=" + orden + ", libro=" + libro + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + '}';
	}

}
