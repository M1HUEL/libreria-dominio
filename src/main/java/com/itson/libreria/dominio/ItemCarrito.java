package com.itson.libreria.dominio;

public class ItemCarrito {

  private int id;
  private Carrito carrito;
  private FormatoLibro formatoLibro;
  private int cantidad;
  private double precioUnitario;

  public ItemCarrito() {
    // ...
  }

  public ItemCarrito(int id, Carrito carrito, FormatoLibro formatoLibro, int cantidad, double precioUnitario) {
    if (carrito == null) {
      throw new IllegalArgumentException("El item del carrito debe estar asociado a un carrito.");
    }

    if (formatoLibro == null) {
      throw new IllegalArgumentException("El item del carrito debe indicar el formato del libro.");
    }

    if (cantidad <= 0) {
      throw new IllegalArgumentException("La cantidad del carrito debe ser mayor a 0.");
    }

    this.id = id;
    this.formatoLibro = formatoLibro;
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

  public FormatoLibro getFormatoLibro() {
    return formatoLibro;
  }

  public void setFormatoLibro(FormatoLibro formatoLibro) {
    if (formatoLibro == null) {
      throw new IllegalArgumentException("El item del carrito debe indicar el formato del libro.");
    }

    this.formatoLibro = formatoLibro;
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
    if (precioUnitario < 0) {
      throw new IllegalArgumentException("El precio unitario del carrito no puede ser negativo.");
    }

    this.precioUnitario = precioUnitario;
  }

}
