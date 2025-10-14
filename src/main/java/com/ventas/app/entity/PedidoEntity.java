package com.ventas.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pedido")
@Entity(name="PedidoEntity")
public class PedidoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idpedido", nullable=false)
	private Long idpedido;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idcliente", nullable = false)
	private ClienteEntity cliente;
	
	@Column(name="fecha")
	private String fecha;
	
	@Column(name="total")
	private float total;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="borrado")
	private String borrado;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<DetallePedidoEntity> detalles;	
}
