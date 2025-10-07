package com.ventas.app.business.entity;

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
@Table(name="producto")
@Entity(name="ProductoEntity")
public class ProductoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idproducto", nullable=false)
	private Long idproducto;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idcategoria", nullable = false)
	private CategoriaEntity categoria;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="foto")
	private String foto;
	
	@Column(name="talla")
	private String talla;
	
	@Column(name="material")
	private String material;
	
	@Column(name="taco")
	private String taco;
	
	@Column(name="precio")
	private float precio;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="borrado")
	private String borrado;	
}
