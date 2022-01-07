package br.com.adriano.spring.data.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.adriano.spring.data.orm.Funcionario;
import br.com.adriano.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long>,
		JpaSpecificationExecutor<Funcionario>{
	List<Funcionario> findByNome(String nome);
	
	List<Funcionario> findByCargoDescricao(String descricao);
	
	@Query(value="SELECT * FROM funcionarios f WHERE data_contratacao >= :data",
			nativeQuery=true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :dataContratacao")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome,
			BigDecimal salario, LocalDate dataContratacao);
	
	@Query(value="SELECT f.id,f.nome,f.salario FROM funcionarios as f",
			nativeQuery=true)
	List<FuncionarioProjecao> findFuncionarioSalario();
	// Nome extenso
//	List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome,
//			Double salario, LocalDate dataContratacao);
}
