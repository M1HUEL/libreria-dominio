package com.itson.libreria.dominio;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

  private int id;
  private String nombre;
  private String correo;
  private String contrasena;
  private String telefono;
  private Rol rol;
  private List<Carrito> carritos;
  private List<Orden> ordenes;
  private List<Direccion> direcciones;

  public Usuario() {
    this.carritos = new ArrayList<>();
    this.ordenes = new ArrayList<>();
    this.direcciones = new ArrayList<>();
  }

  public Usuario(int id, String nombre, String correo, String contrasena, String telefono, Rol rol) {
    this.id = id;
    this.nombre = nombre;
    this.correo = correo;
    this.contrasena = contrasena;
    setTelefono(telefono);
    this.rol = rol;
    this.carritos = new ArrayList<>();
    this.ordenes = new ArrayList<>();
    this.direcciones = new ArrayList<>();
  }

  public boolean esAdministrador() {
    return rol == Rol.ADMINISTRADOR;
  }

  public boolean puedeComprar() {
    return rol == Rol.CLIENTE || rol == Rol.ADMINISTRADOR;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getContrasena() {
    return contrasena;
  }

  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    if (telefono == null || telefono.isBlank()) {
      throw new IllegalArgumentException("El telefono del usuario no puede estar vacio.");
    }

    this.telefono = telefono;
  }

  public Rol getRol() {
    return rol;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }

  public List<Carrito> getCarritos() {
    return carritos;
  }

  public void setCarritos(List<Carrito> carritos) {
    this.carritos = carritos != null ? carritos : new ArrayList<>();
  }

  public void agregarCarrito(Carrito carrito) {
    if (carrito == null) {
      throw new IllegalArgumentException("El carrito no puede ser nulo.");
    }

    if (!this.carritos.contains(carrito)) {
      this.carritos.add(carrito);
    }

    if (carrito.getUsuario() != this) {
      carrito.setUsuario(this);
    }
  }

  public void removerCarrito(Carrito carrito) {
    if (carrito == null) {
      return;
    }

    if (this.carritos.remove(carrito) && carrito.getUsuario() == this) {
      carrito.setUsuario(null);
    }
  }

  public List<Orden> getOrdenes() {
    return ordenes;
  }

  public void setOrdenes(List<Orden> ordenes) {
    this.ordenes = ordenes != null ? ordenes : new ArrayList<>();
  }

  public void agregarOrden(Orden orden) {
    if (orden == null) {
      throw new IllegalArgumentException("La orden no puede ser nula.");
    }

    if (!this.ordenes.contains(orden)) {
      this.ordenes.add(orden);
    }

    if (orden.getUsuario() != this) {
      orden.setUsuario(this);
    }
  }

  public void removerOrden(Orden orden) {
    if (orden == null) {
      return;
    }

    if (this.ordenes.remove(orden) && orden.getUsuario() == this) {
      orden.setUsuario(null);
    }
  }

  public List<Direccion> getDirecciones() {
    return direcciones;
  }

  public void setDirecciones(List<Direccion> direcciones) {
    this.direcciones = direcciones != null ? direcciones : new ArrayList<>();
  }

  public void agregarDireccion(Direccion direccion) {
    if (direccion == null) {
      throw new IllegalArgumentException("La direccion no puede ser nula.");
    }

    if (!this.direcciones.contains(direccion)) {
      this.direcciones.add(direccion);
    }

    if (direccion.getUsuario() != this) {
      direccion.setUsuario(this);
    }

    if (direccion.isPredeterminada()) {
      desmarcarPredeterminadas(direccion);
    }
  }

  public void removerDireccion(Direccion direccion) {
    if (direccion == null) {
      return;
    }

    if (this.direcciones.remove(direccion) && direccion.getUsuario() == this) {
      direccion.setUsuario(null);
    }
  }

  public Direccion getDireccionPredeterminada() {
    return direcciones.stream().filter(Direccion::isPredeterminada).findFirst().orElse(direcciones.isEmpty() ? null : direcciones.get(0));
  }

  void desmarcarPredeterminadas(Direccion excepcion) {
    for (Direccion direccion : this.direcciones) {
      if (direccion != excepcion && direccion.isPredeterminada()) {
        direccion.setPredeterminada(false);
      }
    }
  }
}
