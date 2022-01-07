package br.com.adriano.spring.data.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.adriano.spring.data.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long>{
	List<Funcionario> findByNome(String nome);
	
	// Nome extenso
//	List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome,
//			Double salario, LocalDate dataContratacao);
	
	List<Funcionario> findByCargoDescricao(String descricao);
	
	@Query(value="SELECT * FROM funcionarios f WHERE data_contratacao >= :data",
			nativeQuery=true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :dataContratacao")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome,
			BigDecimal salario, LocalDate dataContratacao);
	
}
