package com.ventas.app.security.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
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
	
	@Column(name = "ENABLED")
	private Boolean enabled;

	@Column(name = "ACCOUNT_NON_EXPIRED")
	private Boolean accountNonExpired;

	@Column(name = "CREDENTIALS_NON_EXPIRED")
	private Boolean credentialsNonExpired;

	@Column(name = "ACCOUNT_NON_LOCKED")
	private Boolean accountNonLocked;	

	@Column(name = "STATE")
	private String state;
	
	// Authorities
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "SEG_USER_AUTHORITY", 
				joinColumns = { 
				@JoinColumn(name = "USER_ID") }, 
				inverseJoinColumns = {
				@JoinColumn(name = "AUTHORITY_ID") })
	@SQLRestriction("state='1'")
	private Set<AuthorityEntity> authorities = new HashSet<>();	
}