package com.itson.libreria.dominio;

import java.time.LocalDateTime;

public class Pago {

  private int id;
  private Orden orden;
  private double monto;
  private MetodoPago metodo;
  private EstadoPago estado;
  private LocalDateTime fecha;

  public Pago() {
    this.estado = EstadoPago.PENDIENTE;
    this.fecha = LocalDateTime.now();
  }

  public Pago(int id, Orden orden, double monto, MetodoPago metodo) {
    if (orden == null) {
      throw new IllegalArgumentException("El pago debe estar asociado a una orden.");
    }

    if (metodo == null) {
      throw new IllegalArgumentException("El metodo de pago no puede ser nulo.");
    }

    if (monto <= 0) {
      throw new IllegalArgumentException("El monto del pago debe ser mayor a 0.");
    }

    if (Math.abs(monto - orden.getTotal()) > 0.001) {
      throw new IllegalArgumentException("El monto del pago debe ser igual al total de la orden.");
    }

    this.id = id;
    this.monto = monto;
    this.metodo = metodo;
    this.estado = EstadoPago.PENDIENTE;
    this.fecha = LocalDateTime.now();
    setOrden(orden);
  }

  public void completar() {
    if (estado == EstadoPago.COMPLETADO) {
      throw new IllegalStateException("El pago ya fue completado.");
    }

    if (estado == EstadoPago.REEMBOLSADO) {
      throw new IllegalStateException("Un pago reembolsado no puede completarse.");
    }

    if (orden == null) {
      throw new IllegalStateException("El pago debe estar asociado a una orden.");
    }

    orden.procesarPago();

    this.estado = EstadoPago.COMPLETADO;
  }

  public void rechazar() {
    if (estado != EstadoPago.PENDIENTE) {
      throw new IllegalStateException("Solo se puede rechazar un pago pendiente.");
    }

    this.estado = EstadoPago.RECHAZADO;
  }

  public void reembolsar() {
    if (estado != EstadoPago.COMPLETADO) {
      throw new IllegalStateException("Solo se puede reembolsar un pago completado.");
    }

    this.estado = EstadoPago.REEMBOLSADO;
  }

  public boolean estaCompletado() {
    return estado == EstadoPago.COMPLETADO;
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
      throw new IllegalArgumentException("El pago debe estar asociado a una orden.");
    }

    if (this.orden != null && this.orden != orden) {
      throw new IllegalStateException("El pago ya esta asociado a otra orden.");
    }

    this.orden = orden;

    if (orden.getPago() != this) {
      orden.setPago(this);
    }
  }

  public double getMonto() {
    return monto;
  }

  public void setMonto(double monto) {
    if (monto <= 0) {
      throw new IllegalArgumentException("El monto del pago debe ser mayor a 0.");
    }

    this.monto = monto;
  }

  public MetodoPago getMetodo() {
    return metodo;
  }

  public void setMetodo(MetodoPago metodo) {
    if (metodo == null) {
      throw new IllegalArgumentException("El metodo de pago no puede ser nulo.");
    }

    this.metodo = metodo;
  }

  public EstadoPago getEstado() {
    return estado;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public void setFecha(LocalDateTime fecha) {
    if (fecha == null) {
      throw new IllegalArgumentException("La fecha del pago no puede ser nula.");
    }

    this.fecha = fecha;
  }
}
