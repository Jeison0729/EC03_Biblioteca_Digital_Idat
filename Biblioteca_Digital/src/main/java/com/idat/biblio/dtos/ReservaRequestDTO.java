package com.idat.biblio.dtos;

public class ReservaRequestDTO {

	private Long libroId;

	public ReservaRequestDTO() {
	}

	@Override
	public String toString() {
		return "ReservaRequestDTO [libroId=" + libroId + "]";
	}

	public ReservaRequestDTO(Long libroId) {
		super();
		this.libroId = libroId;
	}

	public Long getLibroId() {
		return libroId;
	}

	public void setLibroId(Long libroId) {
		this.libroId = libroId;
	}

}
