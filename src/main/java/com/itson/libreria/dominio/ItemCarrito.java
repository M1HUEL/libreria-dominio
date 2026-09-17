package com.itson.libreria.dominio;

public class ItemCarrito {

	private int id;
	private CarritoCompra carrito;
	private Libro libro;
	private int cantidad;

	public ItemCarrito() {
	}

	public ItemCarrito(int id, CarritoCompra carrito, Libro libro, int cantidad) {
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
		}
		this.id = id;
		this.carrito = carrito;
		this.libro = libro;
		this.cantidad = cantidad;
	}

	public double calcularSubtotal() {
		if (libro == null) {
			return 0;
		}
		return libro.getPrecio() * cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CarritoCompra getCarrito() {
		return carrito;
	}

	public void setCarrito(CarritoCompra carrito) {
		this.carrito = carrito;
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

	@Override
	public String toString() {
		return "ItemCarrito{" + "id=" + id + ", carrito=" + carrito + ", libro=" + libro + ", cantidad=" + cantidad + '}';
	}

}
