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

	public void avanzarEstado() {
		if (estado == EstadoOrden.ENTREGADO) {
			throw new IllegalStateException("La orden ya fue entregada, no se puede avanzar más.");
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
