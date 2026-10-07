package com.itson.libreria.dominio;

public class Descarga {

  private int id;
  private ItemOrden item;
  private String url;

  public Descarga() {
    // ...
  }

  public Descarga(ItemOrden item) {
    if (item == null) {
      throw new IllegalArgumentException("La descarga debe estar asociada a un item de la orden.");
    }

    if (!item.esDigital()) {
      throw new IllegalStateException("Solo los formatos digitales generan descarga.");
    }

    this.item = item;

    Orden orden = item.getOrden();

    String referencia = orden != null && orden.getNumeroOrden() != null ? orden.getNumeroOrden() : "orden";

    this.url = "https://libreria.example.com/descargas/" + referencia + "/" + item.getId();

    if (item.getDescarga() != this) {
      item.setDescarga(this);
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ItemOrden getItem() {
    return item;
  }

  public String getUrl() {
    return url;
  }
}
