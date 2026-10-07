package com.itson.libreria.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Orden {

  private int id;
  private String numeroOrden;
  private Usuario usuario;
  private LocalDateTime fecha;
  private EstadoOrden estado;
  private Envio envio;
  private Pago pago;
  private boolean pagoProcesado;
  private List<DetalleOrden> detalle;

  public Orden() {
    this.fecha = LocalDateTime.now();
    this.estado = EstadoOrden.PENDIENTE;
    this.detalle = new ArrayList<>();
  }

  public Orden(int id, String numeroOrden, Usuario usuario, LocalDateTime fecha, EstadoOrden estado) {
    if (usuario == null || !usuario.puedeComprar()) {
      throw new IllegalArgumentException("El usuario no puede realizar compras.");
    }

    this.id = id;
    this.numeroOrden = numeroOrden;
    this.fecha = fecha;
    this.estado = estado;
    this.detalle = new ArrayList<>();
    setUsuario(usuario);
  }

  public void agregarDetalle(DetalleOrden item) {
    if (item == null) {
      throw new IllegalArgumentException("El detalle de la orden no puede ser nulo.");
    }

    if (!this.detalle.contains(item)) {
      this.detalle.add(item);
    }

    if (item.getOrden() != this) {
      item.setOrden(this);
    }
  }

  public void removerDetalle(DetalleOrden item) {
    if (item == null) {
      return;
    }

    if (this.detalle.remove(item) && item.getOrden() == this) {
      item.setOrden(null);
    }
  }

  public boolean isPagoProcesado() {
    return pagoProcesado;
  }

  public void procesarPago() {
    if (pagoProcesado) {
      throw new IllegalStateException("El pago de la orden ya fue procesado.");
    }

    if (estado == EstadoOrden.CANCELADO) {
      throw new IllegalStateException("No se puede procesar el pago de una orden cancelada.");
    }

    if (pago == null) {
      throw new IllegalStateException("La orden no tiene un pago registrado.");
    }

    for (DetalleOrden item : detalle) {
      if (item.getFormatoLibro() == null) {
        throw new IllegalStateException("La orden tiene un detalle sin formato de libro.");
      }

      if (!item.esDigital()) {
        ItemStock stock = item.getFormatoLibro().getStock();

        if (stock == null || !stock.hayDisponibilidad(item.getCantidad())) {
          throw new IllegalStateException("Stock insuficiente para el formato " + item.getFormatoLibro().getFormato() + " del libro '" + item.getFormatoLibro().getLibro().getTitulo() + "'.");
        }
      }
    }

    for (DetalleOrden item : detalle) {
      if (item.esDigital()) {
        item.generarDescarga();
      } else {
        item.getFormatoLibro().getStock().disminuir(item.getCantidad());
      }
    }

    this.pagoProcesado = true;
  }

  public void avanzarEstado() {
    if (estado == EstadoOrden.ENTREGADO) {
      throw new IllegalStateException("La orden ya fue entregada, no se puede avanzar más.");
    }

    if (estado == EstadoOrden.CANCELADO) {
      throw new IllegalStateException("Una orden cancelada no puede avanzar de estado.");
    }

    EstadoOrden siguiente = getSiguienteEstado();

    if (siguiente == EstadoOrden.ENVIADO && envio == null) {
      throw new IllegalStateException("La orden no puede pasar a ENVIADO sin un Envio registrado.");
    }

    this.estado = siguiente;

    if (siguiente == EstadoOrden.ENTREGADO && envio != null) {
      envio.registrarEntrega(LocalDateTime.now());
    }
  }

  public void cancelar() {
    validarCancelacion();

    this.estado = EstadoOrden.CANCELADO;
  }

  private void validarCancelacion() {
    if (estado == EstadoOrden.ENVIADO || estado == EstadoOrden.ENTREGADO) {
      throw new IllegalStateException("No se puede cancelar una orden enviada o entregada.");
    }

    if (estado == EstadoOrden.CANCELADO) {
      throw new IllegalStateException("La orden ya fue cancelada.");
    }
  }

  private EstadoOrden getSiguienteEstado() {
    return switch (estado) {
      case PENDIENTE ->
        EstadoOrden.PROCESANDO;
      case PROCESANDO ->
        EstadoOrden.ENVIADO;
      case ENVIADO ->
        EstadoOrden.ENTREGADO;
      case ENTREGADO ->
        EstadoOrden.ENTREGADO;
      case CANCELADO ->
        EstadoOrden.CANCELADO;
    };
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNumeroOrden() {
    return numeroOrden;
  }

  public void setNumeroOrden(String numeroOrden) {
    this.numeroOrden = numeroOrden;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    if (usuario != null && !usuario.puedeComprar()) {
      throw new IllegalArgumentException("El usuario no puede realizar compras.");
    }

    if (this.usuario != null && this.usuario != usuario) {
      this.usuario.removerOrden(this);
    }

    this.usuario = usuario;

    if (usuario != null) {
      usuario.agregarOrden(this);
    }
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }

  public EstadoOrden getEstado() {
    return estado;
  }

  public void setEstado(EstadoOrden estado) {
    if (estado == null) {
      throw new IllegalArgumentException("El estado de la orden no puede ser nulo.");
    }

    if (estado == EstadoOrden.CANCELADO) {
      validarCancelacion();
    }

    if (this.estado == EstadoOrden.CANCELADO && estado != EstadoOrden.CANCELADO) {
      throw new IllegalStateException("Una orden cancelada no puede cambiar de estado.");
    }

    this.estado = estado;
  }

  public Envio getEnvio() {
    return envio;
  }

  public void setEnvio(Envio envio) {
    if (envio == null) {
      throw new IllegalArgumentException("El envio no puede ser nulo.");
    }

    if (this.envio != null && this.envio != envio) {
      throw new IllegalStateException("La orden ya tiene un envio registrado.");
    }

    this.envio = envio;
  }

  public Pago getPago() {
    return pago;
  }

  public void setPago(Pago pago) {
    if (pago == null) {
      throw new IllegalArgumentException("El pago no puede ser nulo.");
    }

    if (estado == EstadoOrden.CANCELADO) {
      throw new IllegalStateException("No se puede registrar el pago de una orden cancelada.");
    }

    if (this.pago != null && this.pago != pago) {
      throw new IllegalStateException("La orden ya tiene un pago registrado.");
    }

    this.pago = pago;
  }

  public double getTotal() {
    return detalle.stream().mapToDouble(DetalleOrden::getSubtotal).sum();
  }

  public List<DetalleOrden> getDetalle() {
    return detalle;
  }

  public void setDetalle(List<DetalleOrden> detalle) {
    this.detalle = detalle != null ? detalle : new ArrayList<>();
  }
}
