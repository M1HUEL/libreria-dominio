package com.itson.libreria.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Direccion {

	private int id;
	private Usuario usuario;
	private String calle;
	private String numero;
	private String colonia;
	private String ciudad;
	private String estado;
	private String codigoPostal;
	private String pais;
	private boolean predeterminada;
	private List<Envio> envios;

	public Direccion() {
		this.envios = new ArrayList<>();
	}

	public Direccion(int id, Usuario usuario, String calle, String numero, String colonia, String ciudad, String estado, String codigoPostal, String pais) {
		if (usuario == null) {
			throw new IllegalArgumentException("La direccion debe estar asociada a un usuario.");
		}
		if (calle == null || calle.isBlank()) {
			throw new IllegalArgumentException("La calle de la direccion no puede estar vacia.");
		}
		if (ciudad == null || ciudad.isBlank()) {
			throw new IllegalArgumentException("La ciudad de la direccion no puede estar vacia.");
		}
		this.id = id;
		this.calle = calle;
		this.numero = numero;
		this.colonia = colonia;
		this.ciudad = ciudad;
		this.estado = estado;
		this.codigoPostal = codigoPostal;
		this.pais = pais;
		this.envios = new ArrayList<>();
		setUsuario(usuario);
	}

	public void agregarEnvio(Envio envio) {
		if (envio == null) {
			throw new IllegalArgumentException("El envio no puede ser nulo.");
		}
		if (!this.envios.contains(envio)) {
			this.envios.add(envio);
		}
		if (envio.getDireccion() != this) {
			envio.setDireccion(this);
		}
	}

	public void removerEnvio(Envio envio) {
		if (envio == null) {
			return;
		}
		if (this.envios.remove(envio) && envio.getDireccion() == this) {
			envio.setDireccion(null);
		}
	}

	public String getCompleta() {
		return String.join(" ", Stream.of(calle, numero, colonia, ciudad, estado, codigoPostal, pais).filter(p -> p != null && !p.isBlank()).toList());
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
		if (this.usuario != null && this.usuario != usuario) {
			this.usuario.removerDireccion(this);
		}
		this.usuario = usuario;
		if (usuario != null) {
			usuario.agregarDireccion(this);
		}
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		if (calle == null || calle.isBlank()) {
			throw new IllegalArgumentException("La calle de la direccion no puede estar vacia.");
		}
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		if (ciudad == null || ciudad.isBlank()) {
			throw new IllegalArgumentException("La ciudad de la direccion no puede estar vacia.");
		}
		this.ciudad = ciudad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public boolean isPredeterminada() {
		return predeterminada;
	}

	public void setPredeterminada(boolean predeterminada) {
		this.predeterminada = predeterminada;
		if (predeterminada && usuario != null) {
			usuario.desmarcarPredeterminadas(this);
		}
	}

	public List<Envio> getEnvios() {
		return envios;
	}

}
