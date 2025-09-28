package com.ventas.app.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "SEG_AUTHORITY")
@Entity(name = "AuthorityEntity")
public class AuthorityEntity {

	@Id
	@Column(name = "AUTHORITY_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "STATE")
	private String state;
	
}
