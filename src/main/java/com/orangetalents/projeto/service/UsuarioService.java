package com.orangetalents.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orangetalents.projeto.exception.NegocioException;
import com.orangetalents.projeto.model.Usuario;
import com.orangetalents.projeto.repository.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		boolean emailEmUso = usuarioRepository.findByEmail(usuario.getEmail())
				.stream()
				.anyMatch(usuarioExistente -> !usuarioExistente.equals(usuario));
		
		if (emailEmUso) {
			throw new NegocioException("Já existe um usuário cadastrado com este e-mail.");
		}
		
		boolean cpfEmUso = usuarioRepository.findByCpf(usuario.getCpf())
				.stream()
				.anyMatch(usuarioExistente -> !usuarioExistente.equals(usuario));
		
		if (cpfEmUso) {
			throw new NegocioException("Já existe um usuário cadastrado com este cpf.");
		}
		return usuarioRepository.save(usuario);
		
	}
		
	
	@Transactional
	public void excluir(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}

}
