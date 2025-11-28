package com.idat.biblio.dtos;

public class LibroDTO {

	@Override
	public String toString() {
		return "LibroDTO [idLibro=" + idLibro + ", titulo=" + titulo + ", autor=" + autor + ", isbn=" + isbn
				+ ", anioPublicacion=" + anioPublicacion + ", cantidadDisponible=" + cantidadDisponible + "]";
	}

	private Long idLibro;
	private String titulo;
	private String autor;
	private String isbn;
	private Integer anioPublicacion;
	private Integer cantidadDisponible;

	public LibroDTO() {
	}

	public LibroDTO(Long idLibro, String titulo, String autor, String isbn, Integer anioPublicacion,
			Integer cantidadDisponible) {
		super();
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.anioPublicacion = anioPublicacion;
		this.cantidadDisponible = cantidadDisponible;
	}

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(Integer anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(Integer cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

}
