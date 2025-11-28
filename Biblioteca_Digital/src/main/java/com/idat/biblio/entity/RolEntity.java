package com.idat.biblio.entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RolEntity implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(unique = true)
	private String descripcion;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return descripcion;
	}

	public RolEntity() {
	}

	public RolEntity(Long codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return "RolEntity [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
