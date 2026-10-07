package com.itson.libreria.dominio;

public class ItemOrden {

  private int id;
  private Orden orden;
  private FormatoLibro formatoLibro;
  private int cantidad;
  private double precioUnitario;
  private Descarga descarga;

  public ItemOrden() {
    // ...
  }

  public ItemOrden(int id, Orden orden, FormatoLibro formatoLibro, int cantidad, double precioUnitario) {
    if (orden == null) {
      throw new IllegalArgumentException("El item de la orden debe estar asociado a una orden.");
    }

    if (formatoLibro == null) {
      throw new IllegalArgumentException("El item de la orden debe indicar el formato del libro.");
    }

    if (cantidad <= 0) {
      throw new IllegalArgumentException("La cantidad de la orden debe ser mayor a 0.");
    }

    this.id = id;
    this.formatoLibro = formatoLibro;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    orden.agregarItem(this);
  }

  public boolean esDigital() {
    return formatoLibro != null && formatoLibro.esDigital();
  }

  public Descarga generarDescarga() {
    if (descarga != null) {
      return descarga;
    }

    if (!esDigital()) {
      throw new IllegalStateException("Solo los formatos digitales generan descarga.");
    }

    setDescarga(new Descarga(this));

    return descarga;
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
      this.orden.removerItem(this);
    }

    this.orden = orden;

    if (orden != null) {
      orden.agregarItem(this);
    }
  }

  public FormatoLibro getFormatoLibro() {
    return formatoLibro;
  }

  public void setFormatoLibro(FormatoLibro formatoLibro) {
    if (formatoLibro == null) {
      throw new IllegalArgumentException("El item de la orden debe indicar el formato del libro.");
    }

    this.formatoLibro = formatoLibro;
  }

  public Descarga getDescarga() {
    return descarga;
  }

  public void setDescarga(Descarga descarga) {
    if (this.descarga != null && this.descarga != descarga) {
      throw new IllegalStateException("El item de la orden ya tiene una descarga.");
    }

    this.descarga = descarga;
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
    if (precioUnitario < 0) {
      throw new IllegalArgumentException("El precio unitario de la orden no puede ser negativo.");
    }

    this.precioUnitario = precioUnitario;
  }
}
