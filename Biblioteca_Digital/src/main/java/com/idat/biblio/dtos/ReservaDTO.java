package com.idat.biblio.dtos;

import java.time.LocalDateTime;

import com.idat.biblio.enums.EstadoReserva;

public class ReservaDTO {
	@Override
	public String toString() {
		return "ReservaDTO [idReserva=" + idReserva + ", libroId=" + libroId + ", tituloLibro=" + tituloLibro
				+ ", autorLibro=" + autorLibro + ", fechaReserva=" + fechaReserva + ", fechaDevolucion="
				+ fechaDevolucion + ", estado=" + estado + "]";
	}
	private Long idReserva;
	private Long libroId;
	private String tituloLibro;
	private String autorLibro;
	private LocalDateTime fechaReserva;
	private LocalDateTime fechaDevolucion;
	private EstadoReserva estado;

	public ReservaDTO() {
	}

	public ReservaDTO(Long idReserva, Long libroId, String tituloLibro, String autorLibro, LocalDateTime fechaReserva,
			LocalDateTime fechaDevolucion, EstadoReserva estado) {
		super();
		this.idReserva = idReserva;
		this.libroId = libroId;
		this.tituloLibro = tituloLibro;
		this.autorLibro = autorLibro;
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

	public Long getLibroId() {
		return libroId;
	}

	public void setLibroId(Long libroId) {
		this.libroId = libroId;
	}

	public String getTituloLibro() {
		return tituloLibro;
	}

	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
	}

	public String getAutorLibro() {
		return autorLibro;
	}

	public void setAutorLibro(String autorLibro) {
		this.autorLibro = autorLibro;
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
}
