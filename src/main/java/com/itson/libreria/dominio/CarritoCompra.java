package com.itson.libreria.dominio;

import java.util.ArrayList;
import java.util.List;

public class CarritoCompra {

	private int id;
	private Usuario usuario;
	private List<ItemCarrito> items;

	public CarritoCompra() {
		this.items = new ArrayList<>();
	}

	public CarritoCompra(int id, Usuario usuario) {
		this.id = id;
		this.usuario = usuario;
		this.items = new ArrayList<>();
	}

	public void agregarItem(ItemCarrito item) {
		if (item != null && !this.items.contains(item)) {
			this.items.add(item);
		}
	}

	public void removerItem(ItemCarrito item) {
		this.items.remove(item);
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

	public List<ItemCarrito> getItems() {
		return items;
	}

	public void setItems(List<ItemCarrito> items) {
		this.items = items != null ? items : new ArrayList<>();
	}

	@Override
	public String toString() {
		return "CarritoCompra{" + "id=" + id + ", usuario=" + usuario + ", items=" + items + '}';
	}

}
