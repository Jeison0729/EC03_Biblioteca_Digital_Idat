package com.idat.biblio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idat.biblio.entity.EvaluacionEntity;

@Repository
public interface EvaluacionRepository extends JpaRepository<EvaluacionEntity, Long> {

	@Query("SELECT e FROM EvaluacionEntity e WHERE e.usuario.codigo = :usuarioId ORDER BY e.fechaRegistro DESC")
	List<EvaluacionEntity> findByUsuarioCodigo(@Param("usuarioId") Long usuarioId);
}