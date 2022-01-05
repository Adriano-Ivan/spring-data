package br.com.adriano.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.adriano.spring.data.orm.UnidadeDeTrabalhoDeFuncionario;

@Repository
public interface UnidadeDeTrabalhoDeFuncionarioRepository extends CrudRepository<UnidadeDeTrabalhoDeFuncionario, Long> {

}
