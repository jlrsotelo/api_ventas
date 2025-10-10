package com.ventas.app.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="detalle_pedido")
@Entity(name="DetallePedidoEntity")
public class DetallePedidoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idpedido", nullable = false)
	private PedidoEntity Pedido;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idproducto", nullable = false)
	private ProductoEntity Producto;
	
	@Column(name="precio")
	private float precio;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name="total")
	private float total;
}
