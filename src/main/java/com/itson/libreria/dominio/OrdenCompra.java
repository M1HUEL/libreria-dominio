package com.itson.libreria.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdenCompra {

	private int id;
	private Usuario usuario;
	private LocalDateTime fecha;
	private double total;
	private EstadoOrden estado;
	private List<DetalleOrden> detalles;

	public OrdenCompra() {
		this.detalles = new ArrayList<>();
		this.fecha = LocalDateTime.now();
		this.estado = EstadoOrden.PENDIENTE;
	}

	public OrdenCompra(int id, Usuario usuario, LocalDateTime fecha, double total, EstadoOrden estado) {
		this.id = id;
		this.usuario = usuario;
		this.fecha = fecha;
		this.total = total;
		this.estado = estado;
		this.detalles = new ArrayList<>();
	}

	public void agregarDetalle(DetalleOrden detalle) {
		if (detalle != null && !this.detalles.contains(detalle)) {
			this.detalles.add(detalle);
		}
	}

	public void removerDetalle(DetalleOrden detalle) {
		this.detalles.remove(detalle);
	}

	public double calcularTotal() {
		double totalCalculado = 0;

		for (DetalleOrden detalle : detalles) {
			totalCalculado += detalle.calcularSubtotal();
		}

		return totalCalculado;
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
		this.usuario = usuario;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public EstadoOrden getEstado() {
		return estado;
	}

	public void setEstado(EstadoOrden estado) {
		this.estado = estado;
	}

	public List<DetalleOrden> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleOrden> detalles) {
		this.detalles = detalles != null ? detalles : new ArrayList<>();
	}

	@Override
	public String toString() {
		return "OrdenCompra{" + "id=" + id + ", usuario=" + usuario + ", fecha=" + fecha + ", total=" + total + ", estado=" + estado + ", detalles=" + detalles + '}';
	}

}
