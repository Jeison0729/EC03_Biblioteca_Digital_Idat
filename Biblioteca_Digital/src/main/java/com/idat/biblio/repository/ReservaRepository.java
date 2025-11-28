package com.idat.biblio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idat.biblio.entity.ReservaEntity;
import com.idat.biblio.enums.EstadoReserva;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

	@Query("SELECT r FROM ReservaEntity r WHERE r.usuario.codigo = :usuarioId AND r.estado = :estado")
	List<ReservaEntity> findByUsuarioCodigoAndEstado(@Param("usuarioId") Long usuarioId,
			@Param("estado") EstadoReserva estado);


	@Query("SELECT r FROM ReservaEntity r WHERE r.idReserva = :reservaId AND r.usuario.codigo = :usuarioId")
	Optional<ReservaEntity> findByIdAndUsuarioCodigo(@Param("reservaId") Long reservaId,
			@Param("usuarioId") Long usuarioId);
}
