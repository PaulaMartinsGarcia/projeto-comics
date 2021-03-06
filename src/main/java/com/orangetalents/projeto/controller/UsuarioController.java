package com.orangetalents.projeto.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.projeto.model.Usuario;
import com.orangetalents.projeto.repository.UsuarioRepository;
import com.orangetalents.projeto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long usuarioId) {
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		
		if (usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		
		return ResponseEntity.notFound().build();
	}

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionar(@Valid @RequestBody Usuario usuario) {
		return usuarioService.salvar(usuario);
	}
	
	@PutMapping("/{usuarioId}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long usuarioId,
			@Valid @RequestBody Usuario usuario) {
		if (!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		
		usuario.setId(usuarioId);
		usuario = usuarioService.salvar(usuario);
		
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> remover(@PathVariable Long usuarioId) {
		if (!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		
		usuarioService.excluir(usuarioId);
		
		return ResponseEntity.noContent().build();
	}
}
