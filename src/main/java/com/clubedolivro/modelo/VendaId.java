package com.clubedolivro.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VendaId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_PEDIDO")
	private Integer pedidoId;
	
	@Column(name = "ID_VENDEDOR")
	private Integer vendedorId;
	
	public VendaId() {
	}

	public VendaId(Integer pedidoId, Integer vendedorId) {
		this.pedidoId = pedidoId;
		this.vendedorId = vendedorId;
	}

	public Integer getPedidoId() {
		return pedidoId;
	}
	
	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}
	
	public Integer getVendedorId() {
		return vendedorId;
	}
	
	public void setVendedorId(Integer vendedorId) {
		this.vendedorId = vendedorId;
	}
	
}