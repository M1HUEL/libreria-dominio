package com.itson.libreria.dominio;

import java.util.ArrayList;
import java.util.List;

public class ItemStock {

  private int id;
  private FormatoLibro formatoLibro;
  private int cantidad;
  private int stockMinimo;
  private List<AjusteInventario> ajustes;

  public ItemStock() {
    this.cantidad = 0;
    this.ajustes = new ArrayList<>();
  }

  public ItemStock(int id, FormatoLibro formatoLibro, int cantidadInicial) {
    if (cantidadInicial < 0) {
      throw new IllegalArgumentException("La cantidad inicial de stock no puede ser negativa.");
    }

    this.id = id;
    this.cantidad = cantidadInicial;
    this.ajustes = new ArrayList<>();
    setFormatoLibro(formatoLibro);
  }

  public void registrarAjuste(AjusteInventario ajuste) {
    if (ajuste == null) {
      throw new IllegalArgumentException("El ajuste de inventario no puede ser nulo.");
    }

    if (!this.ajustes.contains(ajuste)) {
      this.ajustes.add(ajuste);
    }
  }

  public void agregar(int cantidad) {
    if (cantidad <= 0) {
      throw new IllegalArgumentException("La cantidad a agregar debe ser mayor a 0.");
    }

    this.cantidad += cantidad;
  }

  public void disminuir(int cantidad) {
    if (cantidad <= 0) {
      throw new IllegalArgumentException("La cantidad a disminuir debe ser mayor a 0.");
    }

    if (cantidad > this.cantidad) {
      throw new IllegalStateException("Stock insuficiente de '" + getDescripcion() + "': disponible " + this.cantidad + ", solicitado " + cantidad + ".");
    }

    this.cantidad -= cantidad;
  }

  private String getDescripcion() {
    if (formatoLibro == null || formatoLibro.getLibro() == null) {
      return "el producto";
    }

    return formatoLibro.getLibro().getTitulo() + " (" + formatoLibro.getFormato() + ")";
  }

  public boolean hayDisponibilidad(int cantidad) {
    return cantidad > 0 && this.cantidad >= cantidad;
  }

  public boolean estaEnStockBajo() {
    return cantidad <= stockMinimo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public FormatoLibro getFormatoLibro() {
    return formatoLibro;
  }

  public void setFormatoLibro(FormatoLibro formatoLibro) {
    if (this.formatoLibro != null && this.formatoLibro != formatoLibro) {
      this.formatoLibro.removerStock(this);
    }

    this.formatoLibro = formatoLibro;

    if (formatoLibro != null) {
      formatoLibro.agregarStock(this);
    }
  }

  public List<AjusteInventario> getAjustes() {
    return ajustes;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    if (cantidad < 0) {
      throw new IllegalArgumentException("La cantidad de stock no puede ser negativa.");
    }

    this.cantidad = cantidad;
  }

  public int getStockMinimo() {
    return stockMinimo;
  }

  public void setStockMinimo(int stockMinimo) {
    if (stockMinimo < 0) {
      throw new IllegalArgumentException("El stock mínimo no puede ser negativo.");
    }

    this.stockMinimo = stockMinimo;
  }
}
