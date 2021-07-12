package com.orangetalents.projeto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orangetalents.projeto.utils.Util;

@Entity
public class Comics implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long comicsId;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private Double preco;

	@Column(length = 2048, nullable = false)
	private String autor;

	@Column(nullable = false)
	private String isbn;

	@Column(length = 1024, nullable = false)
	private String descricao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	public Comics() {
	}
	
	public Comics(Long id, Long comicsId, String titulo, Double preco, String autor, String isbn, String descricao,
			Usuario usuario) {
		super();
		this.id = id;
		this.comicsId = comicsId;
		this.titulo = titulo;
		this.preco = preco;
		this.autor = autor;
		this.isbn = isbn;
		this.descricao = descricao;
		this.usuario = usuario;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getComicsId() {
		return comicsId;
	}

	public void setComicsId(Long comicsId) {
		this.comicsId = comicsId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDiaDesconto() {
		return Util.getDiaDesconto(isbn);
	}
	
	public Boolean getDescontoAtivo() {
		if(Util.getDiaDesconto(isbn).equals(Util.getDiaSemana())){
            return true;
        }else {
        	return false;
        }
	}
	
	public Double getPrecoDesconto() {
		if(Util.getDiaDesconto(isbn).equals(Util.getDiaSemana())){
            return preco - (preco / 10);
        }else {
        	return preco;
        }
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comics other = (Comics) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Comics build() {
		Comics comics = new Comics();

		comics.setId(this.id);
		comics.setComicsId(this.comicsId);
		comics.setTitulo(this.titulo);
		comics.setPreco(this.preco);
		comics.setAutor(this.autor);
		comics.setIsbn(this.isbn);
		comics.setDescricao(this.descricao);
		comics.setUsuario(this.usuario.build());
		
		return comics;
	}

}
