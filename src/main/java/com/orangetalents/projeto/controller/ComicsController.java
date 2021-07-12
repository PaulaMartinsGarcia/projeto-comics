package com.orangetalents.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.projeto.model.Comics;
import com.orangetalents.projeto.model.Usuario;
import com.orangetalents.projeto.repository.ComicsRepository;
import com.orangetalents.projeto.service.ComicsService;

@RestController
@RequestMapping("/comics")
public class ComicsController {
	
	
	@Autowired
	private ComicsRepository comicsRepository;
	
	@Autowired
	private ComicsService comicsService;
	
	@GetMapping
	public List<Comics> listar() {
		return comicsRepository.findAll();
	}

	
	@GetMapping("/{comicsId}")
	public ResponseEntity<Comics> buscar(@PathVariable Long comicsId) {
		Optional<Comics> comics = comicsRepository.findById(comicsId);
		
		if (comics.isPresent()) {
			return ResponseEntity.ok(comics.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Comics> newComics(@PathVariable Long id, @RequestBody Usuario usuario) {
		
		return comicsService.newComics(id, usuario);	
	}
	
}
	
