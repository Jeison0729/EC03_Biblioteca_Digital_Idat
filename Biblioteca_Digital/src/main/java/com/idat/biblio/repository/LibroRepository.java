package com.idat.biblio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idat.biblio.entity.LibroEntity;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
	@Query("SELECT l FROM LibroEntity l WHERE LOWER(l.titulo) LIKE :texto OR LOWER(l.autor) LIKE :texto")
	List<LibroEntity> buscarPorTituloOAutor(@Param("texto") String texto);
}
