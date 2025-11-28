package com.idat.biblio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.biblio.entity.RolEntity;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {
	public abstract Optional<RolEntity> findByDescripcion(String descripcion);
}