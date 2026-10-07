package com.itson.libreria.dominio;

public class FormatoLibro {

	private int id;
	private Libro libro;
	private Formato formato;
	private double precio;
	private ItemStock stock;

	public FormatoLibro() {
		// ...
	}

	public FormatoLibro(int id, Libro libro, Formato formato, double precio) {
		if (libro == null) {
			throw new IllegalArgumentException("El formato debe estar asociado a un libro.");
		}
		if (formato == null) {
			throw new IllegalArgumentException("El formato del libro no puede ser nulo.");
		}
		if (precio < 0) {
			throw new IllegalArgumentException("El precio del formato no puede ser negativo.");
		}
		this.id = id;
		this.formato = formato;
		this.precio = precio;
		if (!esDigital()) {
			this.stock = new ItemStock(0, this, 0);
		}
		setLibro(libro);
	}

	public boolean esDigital() {
		return formato == Formato.DIGITAL;
	}

	public void agregarStock(ItemStock stock) {
		if (stock == null) {
			throw new IllegalArgumentException("El stock no puede ser nulo.");
		}
		if (esDigital()) {
			throw new IllegalStateException("El formato digital no lleva stock.");
		}
		if (this.stock != null && this.stock != stock) {
			throw new IllegalStateException("El formato ya tiene un stock asignado.");
		}
		this.stock = stock;
	}

	public void removerStock(ItemStock stock) {
		if (stock != null && this.stock == stock) {
			this.stock = null;
		}
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
			this.libro.removerFormato(this);
		}
		this.libro = libro;
		if (libro != null) {
			libro.agregarFormato(this);
		}
	}

	public Formato getFormato() {
		return formato;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		if (precio < 0) {
			throw new IllegalArgumentException("El precio del formato no puede ser negativo.");
		}
		this.precio = precio;
	}

	public ItemStock getStock() {
		return stock;
	}

}
