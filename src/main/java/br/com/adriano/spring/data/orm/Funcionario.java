package br.com.adriano.spring.data.orm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private BigDecimal salario=BigDecimal.ZERO;
	private LocalDate dataContratacao=LocalDate.now();
	
	@ManyToOne
	private Cargo cargo;
	
	@OneToMany(mappedBy="funcionario",cascade=CascadeType.ALL)
	private List<UnidadeDeTrabalhoDeFuncionario> unidadesDeTrabalhoDeFuncionario=new ArrayList<UnidadeDeTrabalhoDeFuncionario>();
	
	public Funcionario() {}
	public Funcionario(String nome, String cpf,
			String stringSalario,Cargo cargo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.salario=new BigDecimal(stringSalario);
		this.cargo=cargo;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public List<UnidadeDeTrabalhoDeFuncionario> getUnidadesDeTrabalhoDeFuncionario(){
		return this.unidadesDeTrabalhoDeFuncionario;
	}
	public void adicionarUnidadeDeTrabalho(UnidadeDeTrabalhoDeFuncionario utf) {
		unidadesDeTrabalhoDeFuncionario.add(utf);
		
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", salario=" + salario
				+ ", dataContratacao=" + dataContratacao + ", cargo=" + cargo + "]";
	}
	
	
}
