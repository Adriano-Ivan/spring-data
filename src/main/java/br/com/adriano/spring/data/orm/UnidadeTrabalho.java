package br.com.adriano.spring.data.orm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="unidades_de_trabalho")
public class UnidadeTrabalho {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Endereco endereco;

	public UnidadeTrabalho() {}
	
	public UnidadeTrabalho(String descricao,Endereco endereco) {	
		this.descricao = descricao;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "UnidadeTrabalho [id=" + id + ", descricao=" + descricao + ", endereco=" + endereco + "]";
	}
	
}
