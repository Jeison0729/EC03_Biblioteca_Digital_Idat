package com.idat.biblio.dtos;

import java.time.LocalDateTime;

public class EvaluacionDTO {

	@Override
	public String toString() {
		return "EvaluacionDTO [id=" + id + ", idUsuario=" + idUsuario + ", comentario=" + comentario + ", puntaje="
				+ puntaje + ", fechaRegistro=" + fechaRegistro + "]";
	}

	private Long id;
	private Long idUsuario;
	private String comentario;
	private Integer puntaje;
	private LocalDateTime fechaRegistro;

	public EvaluacionDTO() {
	}

	public EvaluacionDTO(Long id, Long idUsuario, String comentario, Integer puntaje, LocalDateTime fechaRegistro) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.comentario = comentario;
		this.puntaje = puntaje;
		this.fechaRegistro = fechaRegistro;
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}