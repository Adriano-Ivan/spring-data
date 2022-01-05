package br.com.adriano.spring.data.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="unidades_de_trabalho_de_funcionarios")
public class UnidadeDeTrabalhoDeFuncionario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome_do_funcionario")
	private String nomeDoFuncionario;
	
	@ManyToOne
	private UnidadeTrabalho unidadeDeTrabalho;
	@ManyToOne
	private Funcionario funcionario;
	
	public UnidadeDeTrabalhoDeFuncionario() {}
	public UnidadeDeTrabalhoDeFuncionario(UnidadeTrabalho ut,
			Funcionario funcionario) {
		this.unidadeDeTrabalho=ut;
		this.funcionario=funcionario;
		this.nomeDoFuncionario=funcionario.getNome();
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeDoFuncionario() {
		return nomeDoFuncionario;
	}
	public void setNomeDoFuncionario(String nomeDoFuncionario) {
		this.nomeDoFuncionario = nomeDoFuncionario;
	}
	public UnidadeTrabalho getUnidadeDeTrabalho() {
		return unidadeDeTrabalho;
	}
	public void setUnidadeDeTrabalho(UnidadeTrabalho unidadeDeTrabalho) {
		this.unidadeDeTrabalho = unidadeDeTrabalho;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	@Override
	public String toString() {
		return "UnidadeDeTrabalhoDeFuncionario [id=" + id + ", nomeDoFuncionario=" + nomeDoFuncionario
				+ ", unidadeDeTrabalho=" + unidadeDeTrabalho + ", funcionario=" + funcionario + "]";
	}
	
}
