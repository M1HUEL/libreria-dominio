package com.itson.libreria.dominio;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

  private int id;
  private Usuario usuario;
  private List<ItemCarrito> items;

  public Carrito() {
    this.items = new ArrayList<>();
  }

  public Carrito(int id, Usuario usuario) {
    if (usuario == null || !usuario.puedeComprar()) {
      throw new IllegalArgumentException("El usuario no puede realizar compras.");
    }

    this.id = id;
    this.items = new ArrayList<>();
    setUsuario(usuario);
  }

  public void agregarItem(ItemCarrito item) {
    if (item == null) {
      throw new IllegalArgumentException("El item del carrito no puede ser nulo.");
    }

    if (!this.items.contains(item)) {
      this.items.add(item);
    }

    if (item.getCarrito() != this) {
      item.setCarrito(this);
    }
  }

  public void removerItem(ItemCarrito item) {
    if (item == null) {
      return;
    }

    if (this.items.remove(item) && item.getCarrito() == this) {
      item.setCarrito(null);
    }
  }

  public double getSubtotal() {
    return items.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
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

  public List<ItemCarrito> getItems() {
    return items;
  }

  public void setItems(List<ItemCarrito> items) {
    this.items = items != null ? items : new ArrayList<>();
  }
}
