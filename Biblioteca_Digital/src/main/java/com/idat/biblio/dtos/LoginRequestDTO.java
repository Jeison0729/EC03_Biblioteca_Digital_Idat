package com.idat.biblio.dtos;

public class LoginRequestDTO {

	@Override
	public String toString() {
		return "LoginRequestDTO [correo=" + correo + ", password=" + password + "]";
	}

	private String correo;
	private String password;

	public LoginRequestDTO() {
	}

	public LoginRequestDTO(String correo, String password) {
		super();
		this.correo = correo;
		this.password = password;
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

}
