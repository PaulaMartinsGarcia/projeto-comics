package com.orangetalents.projeto.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.orangetalents.projeto.feignclients.ComicsFeignClient;
import com.orangetalents.projeto.model.Comics;
import com.orangetalents.projeto.model.Usuario;
import com.orangetalents.projeto.repository.ComicsRepository;

@Service
public class ComicsService {
	
	@Autowired
	ComicsRepository comicsRepository;
	
	@Autowired
	private ComicsFeignClient comicsFeignClient;
	
	public ResponseEntity<Comics> newComics(Long id, Usuario usuario) {
		Comics comics = new Comics();

		
		String jsonStr = comicsFeignClient.getComics(id);
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			/*Leitura do JSON*/
			/*Atribui o objeto data a variavel jsonData*/
			JsonNode jsonData = mapper.readTree(jsonStr);
			ObjectNode JsonStr3 = (ObjectNode) jsonData;
			jsonData = JsonStr3.get("data");
			
			/*Atribui o objeto results da API a variavel results*/
			ArrayNode jsonResults = (ArrayNode) jsonData.get("results");
			Iterator<JsonNode> iteratorResults = jsonResults.elements();
			JsonNode results = iteratorResults.next();
			
			/*Atribui o preco a variavel resultsPreco*/
			ArrayNode ArrayPrices = (ArrayNode) results.get("prices");
			Iterator<JsonNode> iteratorPrices = ArrayPrices.elements();
			JsonNode resultsPreco = iteratorPrices.next();
			
			/*Atribui o array de creators que estao no objeto de results*/
			ObjectNode JsonStr4 = (ObjectNode) results;
			JsonStr4 = (ObjectNode) JsonStr4.get("creators");
			ArrayNode ArrayCreatosItems = (ArrayNode) JsonStr4.get("items");
			Iterator<JsonNode> iteratorCreatorsItems = ArrayCreatosItems.elements();
			JsonNode resultsCreatorsItems = iteratorCreatorsItems.next();
			/*Fim da leitura do JSON*/

			
			/*Inicio da montagem do objeto comic que será persistido em banco*/
			comics.setComicsId(id);
			comics.setTitulo(results.get("title").asText());
			comics.setDescricao(results.get("description").asText());
			comics.setIsbn(results.get("isbn").asText());
			comics.setAutor(resultsCreatorsItems.get("name").asText());
			comics.setPreco(Double.parseDouble(resultsPreco.get("price").asText()));
			comics.setUsuario(usuario);
			/*FIm da montagem do objeto*/
			
			comicsRepository.save(comics.build());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(201).body(comics);
	}
	
}

