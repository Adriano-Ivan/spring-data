package br.com.adriano.spring.data.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.criteria.CriteriaQuery;

import org.springframework.data.jpa.domain.Specification;

import br.com.adriano.spring.data.orm.Funcionario;

public class SpecificationFuncionario {
	public static Specification<Funcionario> nome(String nome){
		return (root,criteriaQuery,criteriaBuilder) ->
				criteriaBuilder.like(root.get("nome"), "%"+nome+"%");
	}
	public static Specification<Funcionario> cpf(String cpf){
		return (root,criteriaQuery,criteriaBuilder) ->
				criteriaBuilder.equal(root.get("cpf"), cpf);
	}
	public static Specification<Funcionario> salario(BigDecimal salario){
		return (root,criteriaQuery,criteriaBuilder) ->
				criteriaBuilder.greaterThanOrEqualTo(root.get("salario"), salario);
	}
	public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao){
		return (root,criteriaQuery,criteriaBuilder) ->
				criteriaBuilder.greaterThanOrEqualTo(root.get("dataContratacao"), dataContratacao);
	}
}
