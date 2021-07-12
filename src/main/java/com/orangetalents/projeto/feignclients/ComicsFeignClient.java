package com.orangetalents.projeto.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;
import feign.Param;


@FeignClient(name = "marvel", url = "https://gateway.marvel.com/v1/public/comics?apikey=cba4e2240e7492f549c3ef5b8d3b863b&ts=1234&hash=f38d3a8f06559fa6044236fbef664810")
public interface ComicsFeignClient {

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	@Headers({"forwardUrl: {}"})
	String getComics(@Param("id")@PathVariable Long id);
		
}
