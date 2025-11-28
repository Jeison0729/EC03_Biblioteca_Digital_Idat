package com.idat.biblio.dtos;

public class RegistroRequestDTO {
	private String correo;
	private String password;
	private String nombreCompleto;

	public RegistroRequestDTO() {
	}

	@Override
	public String toString() {
		return "RegistroRequestDTO [correo=" + correo + ", password=" + password + ", nombreCompleto=" + nombreCompleto
				+ "]";
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

}
