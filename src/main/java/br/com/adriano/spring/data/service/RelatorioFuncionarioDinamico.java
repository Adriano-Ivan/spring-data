package br.com.adriano.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.adriano.spring.data.orm.Funcionario;
import br.com.adriano.spring.data.repository.FuncionarioRepository;
import br.com.adriano.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository=funcionarioRepository;
	}
	
	public void iniciar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite um nome: ");
		String nome =scanner.nextLine();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome=null;
		}

		System.out.println("Digite um CPF: ");
		String cpf =scanner.nextLine();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf=null;
		}

		System.out.println("Digite um salário: ");
		String salarioString =scanner.nextLine();
		BigDecimal salario=null;
		
		if(salarioString.equalsIgnoreCase("NULL")) {
			salario=null;
		}else {
			salario=new BigDecimal(salarioString);;
		}
		
		System.out.println("Digite uma data: ");
		String dataString =scanner.nextLine();
		LocalDate data=null;
		if(dataString.equalsIgnoreCase("NULL")) {
			data=null;
		}else {
			data=LocalDate.parse(dataString,formatter);
		}
		
		
		List<Funcionario> funcionarios=funcionarioRepository.findAll(Specification
				.where(
						SpecificationFuncionario.nome(nome))
				.or(SpecificationFuncionario.cpf(cpf))
				.or(SpecificationFuncionario.dataContratacao(data))
				.or(SpecificationFuncionario.salario(salario))
				);
		
		funcionarios.forEach(System.out::println);
	}
}
