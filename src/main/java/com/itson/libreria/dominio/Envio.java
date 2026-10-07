package com.itson.libreria.dominio;

public class Envio {

  private int id;
  private Orden orden;
  private Direccion direccion;
  private String paqueteria;
  private String numeroGuia;

  public Envio() {
    // ...
  }

  public Envio(int id, Orden orden, Direccion direccion, String paqueteria, String numeroGuia) {
    if (orden == null) {
      throw new IllegalArgumentException("El envio debe estar asociado a una orden.");
    }

    if (direccion == null) {
      throw new IllegalArgumentException("El envio debe tener una direccion de entrega.");
    }

    if (paqueteria == null || paqueteria.isBlank()) {
      throw new IllegalArgumentException("La paqueteria del envio no puede estar vacia.");
    }

    if (numeroGuia == null || numeroGuia.isBlank()) {
      throw new IllegalArgumentException("El numero de guia del envio no puede estar vacio.");
    }

    this.id = id;
    this.paqueteria = paqueteria;
    this.numeroGuia = numeroGuia;
    setDireccion(direccion);
    setOrden(orden);
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
    if (orden == null) {
      throw new IllegalArgumentException("El envio debe estar asociado a una orden.");
    }

    if (this.orden != null && this.orden != orden) {
      throw new IllegalStateException("El envio ya esta asociado a otra orden.");
    }

    this.orden = orden;

    if (orden.getEnvio() != this) {
      orden.setEnvio(this);
    }
  }

  public Direccion getDireccion() {
    return direccion;
  }

  public void setDireccion(Direccion direccion) {
    if (this.direccion != null && this.direccion != direccion) {
      this.direccion.removerEnvio(this);
    }

    this.direccion = direccion;

    if (direccion != null) {
      direccion.agregarEnvio(this);
    }
  }

  public String getPaqueteria() {
    return paqueteria;
  }

  public void setPaqueteria(String paqueteria) {
    if (paqueteria == null || paqueteria.isBlank()) {
      throw new IllegalArgumentException("La paqueteria del envio no puede estar vacia.");
    }

    this.paqueteria = paqueteria;
  }

  public String getNumeroGuia() {
    return numeroGuia;
  }

  public void setNumeroGuia(String numeroGuia) {
    if (numeroGuia == null || numeroGuia.isBlank()) {
      throw new IllegalArgumentException("El numero de guia del envio no puede estar vacio.");
    }

    this.numeroGuia = numeroGuia;
  }
}
