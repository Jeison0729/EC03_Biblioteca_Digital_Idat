package com.idat.biblio.entity;

import java.time.LocalDateTime;

import com.idat.biblio.enums.EstadoReserva;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ReservaEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReserva;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_usuario", nullable = false)
	private UsuarioEntity usuario;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_libro", nullable = false)
	private LibroEntity libro;

	private LocalDateTime fechaReserva = LocalDateTime.now();
	private LocalDateTime fechaDevolucion;
	private EstadoReserva estado = EstadoReserva.ACTIVA;

	public ReservaEntity() {
	}

	public ReservaEntity(Long idReserva, UsuarioEntity usuario, LibroEntity libro, LocalDateTime fechaReserva,
			LocalDateTime fechaDevolucion, EstadoReserva estado) {
		super();
		this.idReserva = idReserva;
		this.usuario = usuario;
		this.libro = libro;
		this.fechaReserva = fechaReserva;
		this.fechaDevolucion = fechaDevolucion;
		this.estado = estado;
	}

	public Long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public LibroEntity getLibro() {
		return libro;
	}

	public void setLibro(LibroEntity libro) {
		this.libro = libro;
	}

	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDateTime fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public LocalDateTime getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public EstadoReserva getEstado() {
		return estado;
	}

	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
