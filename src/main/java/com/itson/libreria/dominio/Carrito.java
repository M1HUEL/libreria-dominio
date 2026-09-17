package com.itson.libreria.dominio;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

	private int id;
	private Usuario usuario;
	private List<DetalleCarrito> detalle;

	public Carrito() {
		this.detalle = new ArrayList<>();
	}

	public Carrito(int id, Usuario usuario) {
		this.id = id;
		this.usuario = usuario;
		this.detalle = new ArrayList<>();
	}

	public void agregarItem(DetalleCarrito item) {
		if (item != null) {
			this.detalle.add(item);
		}
	}

	public void removerItem(DetalleCarrito item) {
		this.detalle.remove(item);
	}

	public double getSubtotal() {
		return detalle.stream()
			.mapToDouble(DetalleCarrito::getSubtotal)
			.sum();
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

	public List<DetalleCarrito> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleCarrito> detalle) {
		this.detalle = detalle != null ? detalle : new ArrayList<>();
	}

}
