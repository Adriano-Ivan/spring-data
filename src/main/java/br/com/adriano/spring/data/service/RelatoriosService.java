package br.com.adriano.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.adriano.spring.data.orm.Cargo;
import br.com.adriano.spring.data.orm.Funcionario;
import br.com.adriano.spring.data.repository.CargoRepository;
import br.com.adriano.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private Boolean system =true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository=funcionarioRepository;
	}
	
	public void iniciar() {
		Scanner scanner = new Scanner(System.in);
		while(system) {
			System.out.println("Qual ação de cargo deseja executar ?");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar funcionário por nome");
			System.out.println("2 - Buscar funcionário por nome, data contratação e mínima faixa de salário");
			System.out.println("3 - Buscar por data de contratação mínima ");
			int action = scanner.nextInt();
			switch(action) {
			case 1:
				buscaFuncionarioPorNome();
				break;
			case 2:
				buscaFuncionarioPorNomeSalarioMaiorData();
				break;
			case 3:
				buscaFuncionarioApartirDeDataContratacao();
				break;
			default:
				system=false;
				break;
			}
			
		}
	}
	
	private void buscaFuncionarioPorNome() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Qual nome deseja pesquisar ? ");
		String nome = scanner.nextLine();
		List<Funcionario> lista = funcionarioRepository.findByNome(nome);
		
		System.out.println();
		lista.forEach(System.out::println);
		System.out.println();
	}
	
	private  void buscaFuncionarioPorNomeSalarioMaiorData() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Qual nome ? ");
		String nome = scanner.nextLine();
		
		System.out.println("Qual data de contratação ? ");
		String dataString  = scanner.nextLine();
		LocalDate data=LocalDate.parse(dataString,formatter);
		
		System.out.println("A partir de qual salário ? ");
		String salarioString = scanner.nextLine();
		BigDecimal salario = new BigDecimal(salarioString);
		
		
		List<Funcionario> lista = funcionarioRepository
				.findNomeSalarioMaiorDataContratacao(nome, salario, data);
		
		System.out.println();
		lista.forEach(System.out::println);
		System.out.println();
	}
	
	private void buscaFuncionarioApartirDeDataContratacao() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("A partir de qual data de contratação ? ");
		String dataString  = scanner.nextLine();
		LocalDate data=LocalDate.parse(dataString,formatter);
		
		List<Funcionario> lista= funcionarioRepository
				.findDataContratacaoMaior(data);
		
		System.out.println();
		lista.forEach(System.out::println);
		System.out.println();
	}
}
