package com.idat.biblio.dtos;

public class EvaluacionRequestDTO {

	@Override
	public String toString() {
		return "EvaluacionRequestDTO [comentario=" + comentario + ", puntaje=" + puntaje + "]";
	}

	private String comentario;
	private Integer puntaje;

	public EvaluacionRequestDTO() {
	}

	public EvaluacionRequestDTO(String comentario, Integer puntaje) {
		super();
		this.comentario = comentario;
		this.puntaje = puntaje;
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

}