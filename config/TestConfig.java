package com.orangetalents.projetocomics.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.orangetalents.projetocomics.entities.Comics;
import com.orangetalents.projetocomics.entities.Usuario;
import com.orangetalents.projetocomics.repositories.ComicsRepository;
import com.orangetalents.projetocomics.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ComicsRepository comicsRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(null, "Maria da Silva", "maria@gmail.com","111222333-88","10/10/2000");
		Usuario u2 = new Usuario(null, "João da Silva", "joao@gmail.com","111222555-88","10/10/2001");
		
		Comics c1 = new Comics(null, "Marvel Age Spider-Man Vol. 2: Everyday Hero (Digest)", 5.99, "Daniel Quantz","0-7851-1451-3","The Marvel Age of Comics continues! This time around, Spidey meets his match against such classic villains as Electro and the Lizard, and faces the return of one of his first foes: the Vulture! Plus: Spider-Man vs. the Living Brain, Peter Parker's fight with Flash Thompson, and the wall-crawler tackles the high-flying Human Torch!", u2);
		Comics c2 = new Comics(null, "Marvel Age Spider-Man Vol. 3: Everyday Hero (Digest)", 6.99, "Daniel Quantz","0-7851-1451-3","The Marvel Age of Comics continues! This time around, Spidey meets his match against such classic villains as Electro and the Lizard, and faces the return of one of his first foes: the Vulture!", u2);
		Comics c3 = new Comics(null, "Marvel Age Spider-Man Vol. 4: Everyday Hero (Digest)", 7.99, "Daniel Quantz","0-7851-1451-3","The Marvel Age of Comics continues! This time around, Spidey meets his match against such classic villains as Electro and the Lizard, and faces the return of one of his first foes: the Vulture!", u1);
		Comics c4 = new Comics(null, "Marvel Age Spider-Man Vol. 5: Everyday Hero (Digest)", 9.99, "Daniel Quantz","0-7851-1451-3","The Marvel Age of Comics continues! This time around, Spidey meets his match against such classic villains as Electro and the Lizard, and faces the return of one of his first foes: the Vulture!", u1);
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		comicsRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
	}
	
	
}

