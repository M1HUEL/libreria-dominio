package com.itson.libreria.dominio;

public class Descarga {

  private int id;
  private DetalleOrden detalle;
  private String url;

  public Descarga() {
    // ...
  }

  public Descarga(DetalleOrden detalle) {
    if (detalle == null) {
      throw new IllegalArgumentException("La descarga debe estar asociada a un detalle de orden.");
    }

    if (!detalle.esDigital()) {
      throw new IllegalStateException("Solo los formatos digitales generan descarga.");
    }

    this.detalle = detalle;

    Orden orden = detalle.getOrden();

    String referencia = orden != null && orden.getNumeroOrden() != null ? orden.getNumeroOrden() : "orden";

    this.url = "https://libreria.example.com/descargas/" + referencia + "/" + detalle.getId();

    if (detalle.getDescarga() != this) {
      detalle.setDescarga(this);
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public DetalleOrden getDetalle() {
    return detalle;
  }

  public String getUrl() {
    return url;
  }
}
