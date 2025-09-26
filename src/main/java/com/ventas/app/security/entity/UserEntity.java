package com.ventas.app.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "SEG_USER")
@Entity(name = "UserEntity")
public class UserEntity {

	@Id
	@Column(name = "USER_ID")
	private Long id;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "STATE")
	private String state;
}