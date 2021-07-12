package com.orangetalents.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orangetalents.projeto.model.Comics;

@Repository
public interface ComicsRepository extends JpaRepository<Comics, Long> {

	List<Comics> findByTitulo(String titulo);
	List<Comics> findByUsuarioId(String usuarioId);
}
