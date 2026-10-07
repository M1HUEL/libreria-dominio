package com.itson.libreria.dominio;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

  private int id;
  private Usuario usuario;
  private List<ItemCarrito> detalle;

  public Carrito() {
    this.detalle = new ArrayList<>();
  }

  public Carrito(int id, Usuario usuario) {
    if (usuario == null || !usuario.puedeComprar()) {
      throw new IllegalArgumentException("El usuario no puede realizar compras.");
    }

    this.id = id;
    this.detalle = new ArrayList<>();
    setUsuario(usuario);
  }

  public void agregarItem(ItemCarrito item) {
    if (item == null) {
      throw new IllegalArgumentException("El detalle del carrito no puede ser nulo.");
    }

    if (!this.detalle.contains(item)) {
      this.detalle.add(item);
    }

    if (item.getCarrito() != this) {
      item.setCarrito(this);
    }
  }

  public void removerItem(ItemCarrito item) {
    if (item == null) {
      return;
    }

    if (this.detalle.remove(item) && item.getCarrito() == this) {
      item.setCarrito(null);
    }
  }

  public double getSubtotal() {
    return detalle.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    if (usuario != null && !usuario.puedeComprar()) {
      throw new IllegalArgumentException("El usuario no puede realizar compras.");
    }

    if (this.usuario != null && this.usuario != usuario) {
      this.usuario.removerCarrito(this);
    }

    this.usuario = usuario;

    if (usuario != null) {
      usuario.agregarCarrito(this);
    }
  }

  public List<ItemCarrito> getDetalle() {
    return detalle;
  }

  public void setDetalle(List<ItemCarrito> detalle) {
    this.detalle = detalle != null ? detalle : new ArrayList<>();
  }
}
