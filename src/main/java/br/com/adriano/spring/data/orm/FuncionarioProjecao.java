package br.com.adriano.spring.data.orm;

import java.math.BigDecimal;

public interface FuncionarioProjecao {
	Integer getId();
	String getNome();
	BigDecimal getSalario();
}
