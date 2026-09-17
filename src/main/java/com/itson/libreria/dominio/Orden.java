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
	private double total;
	private List<DetalleOrden> detalle;

	public Orden() {
		this.fecha = LocalDateTime.now();
		this.estado = EstadoOrden.PENDIENTE;
		this.detalle = new ArrayList<>();
	}

	public Orden(int id, String numeroOrden, Usuario usuario, LocalDateTime fecha, EstadoOrden estado, double total) {
		this.id = id;
		this.numeroOrden = numeroOrden;
		this.usuario = usuario;
		this.fecha = fecha;
		this.estado = estado;
		this.total = total;
		this.detalle = new ArrayList<>();
	}

	public void agregarDetalle(DetalleOrden item) {
		if (item != null) {
			this.detalle.add(item);
		}
	}

	public void removerDetalle(DetalleOrden item) {
		this.detalle.remove(item);
	}

	public void avanzarEstado() {
		if (estado == EstadoOrden.ENTREGADO) {
			throw new IllegalStateException("La orden ya fue entregada, no se puede avanzar más.");
		}
		this.estado = getSiguienteEstado();
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
		this.usuario = usuario;
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
		this.estado = estado;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<DetalleOrden> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleOrden> detalle) {
		this.detalle = detalle != null ? detalle : new ArrayList<>();
	}

}
