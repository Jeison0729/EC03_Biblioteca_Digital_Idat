package com.idat.biblio.dtos;

public class LoginResponseDTO {
	
	@Override
	public String toString() {
		return "LoginResponseDTO [token=" + token + ", usuarioId=" + usuarioId + ", nombreCompleto=" + nombreCompleto
				+ ", correo=" + correo + "]";
	}

	private String token;
	private Long usuarioId;
	private String nombreCompleto;
	private String correo;

	public LoginResponseDTO() {
	}

	public LoginResponseDTO(String token, Long usuarioId, String nombreCompleto, String correo) {
		super();
		this.token = token;
		this.usuarioId = usuarioId;
		this.nombreCompleto = nombreCompleto;
		this.correo = correo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
