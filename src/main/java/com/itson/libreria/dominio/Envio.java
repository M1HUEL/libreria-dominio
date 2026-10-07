package com.itson.libreria.dominio;

import java.time.LocalDateTime;

public class Envio {

	private int id;
	private Orden orden;
	private Direccion direccion;
	private String paqueteria;
	private String numeroGuia;
	private LocalDateTime fechaEnvio;
	private LocalDateTime fechaEstimadaEntrega;
	private LocalDateTime fechaEntrega;

	public Envio() {
		// ...
	}

	public Envio(int id, Orden orden, Direccion direccion, String paqueteria, String numeroGuia, LocalDateTime fechaEnvio, LocalDateTime fechaEstimadaEntrega) {
		if (orden == null) {
			throw new IllegalArgumentException("El envio debe estar asociado a una orden.");
		}
		if (direccion == null) {
			throw new IllegalArgumentException("El envio debe tener una direccion de entrega.");
		}
		if (paqueteria == null || paqueteria.isBlank()) {
			throw new IllegalArgumentException("La paqueteria del envio no puede estar vacia.");
		}
		if (numeroGuia == null || numeroGuia.isBlank()) {
			throw new IllegalArgumentException("El numero de guia del envio no puede estar vacio.");
		}
		this.id = id;
		this.paqueteria = paqueteria;
		this.numeroGuia = numeroGuia;
		this.fechaEnvio = fechaEnvio != null ? fechaEnvio : LocalDateTime.now();
		this.fechaEstimadaEntrega = fechaEstimadaEntrega;
		setDireccion(direccion);
		setOrden(orden);
	}

	public void registrarEntrega(LocalDateTime fechaEntrega) {
		if (fechaEntrega == null) {
			throw new IllegalArgumentException("La fecha de entrega no puede ser nula.");
		}
		if (this.fechaEntrega != null) {
			throw new IllegalStateException("El envio ya fue entregado.");
		}
		this.fechaEntrega = fechaEntrega;
	}

	public boolean estaEntregado() {
		return fechaEntrega != null;
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
			throw new IllegalArgumentException("El envio debe estar asociado a una orden.");
		}
		if (this.orden != null && this.orden != orden) {
			throw new IllegalStateException("El envio ya esta asociado a otra orden.");
		}
		this.orden = orden;
		if (orden.getEnvio() != this) {
			orden.setEnvio(this);
		}
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		if (this.direccion != null && this.direccion != direccion) {
			this.direccion.removerEnvio(this);
		}
		this.direccion = direccion;
		if (direccion != null) {
			direccion.agregarEnvio(this);
		}
	}

	public String getPaqueteria() {
		return paqueteria;
	}

	public void setPaqueteria(String paqueteria) {
		if (paqueteria == null || paqueteria.isBlank()) {
			throw new IllegalArgumentException("La paqueteria del envio no puede estar vacia.");
		}
		this.paqueteria = paqueteria;
	}

	public String getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(String numeroGuia) {
		if (numeroGuia == null || numeroGuia.isBlank()) {
			throw new IllegalArgumentException("El numero de guia del envio no puede estar vacio.");
		}
		this.numeroGuia = numeroGuia;
	}

	public LocalDateTime getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(LocalDateTime fechaEnvio) {
		if (fechaEnvio == null) {
			throw new IllegalArgumentException("La fecha de envio no puede ser nula.");
		}
		this.fechaEnvio = fechaEnvio;
	}

	public LocalDateTime getFechaEstimadaEntrega() {
		return fechaEstimadaEntrega;
	}

	public void setFechaEstimadaEntrega(LocalDateTime fechaEstimadaEntrega) {
		this.fechaEstimadaEntrega = fechaEstimadaEntrega;
	}

	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}

}
