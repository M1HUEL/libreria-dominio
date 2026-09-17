package com.itson.libreria.dominio;

import java.time.LocalDateTime;

public class AjusteInventario {

	private int id;
	private Stock stock;
	private int cantidad;
	private String motivo;
	private LocalDateTime fecha;

	public AjusteInventario() {
		this.fecha = LocalDateTime.now();
	}

	public AjusteInventario(int id, Stock stock, int cantidad, String motivo, LocalDateTime fecha) {
		if (cantidad == 0) {
			throw new IllegalArgumentException("La cantidad del ajuste no puede ser 0.");
		}
		this.id = id;
		this.stock = stock;
		this.cantidad = cantidad;
		this.motivo = motivo;
		this.fecha = fecha;
	}

	public void aplicarAjuste() {
		if (stock == null) {
			throw new IllegalStateException("El ajuste debe estar asociado a un stock.");
		}
		if (cantidad > 0) {
			stock.agregar(cantidad);
		} else {
			stock.disminuir(Math.abs(cantidad));
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		if (cantidad == 0) {
			throw new IllegalArgumentException("La cantidad del ajuste no puede ser 0.");
		}

		this.cantidad = cantidad;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "AjusteInventario{" + "id=" + id + ", stock=" + stock + ", cantidad=" + cantidad + ", motivo=" + motivo + ", fecha=" + fecha + '}';
	}

}
