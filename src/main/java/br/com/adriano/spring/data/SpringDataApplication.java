package br.com.adriano.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.adriano.spring.data.orm.Cargo;
import br.com.adriano.spring.data.repository.CargoRepository;
import br.com.adriano.spring.data.service.CrudCargoService;
import br.com.adriano.spring.data.service.CrudFuncionarioService;
import br.com.adriano.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.adriano.spring.data.service.RelatorioFuncionarioDinamico;
import br.com.adriano.spring.data.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner{

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final RelatoriosService relatoriosService;
	private final RelatorioFuncionarioDinamico relatorioDinamico;
	
	private Boolean system = true;
	
	public SpringDataApplication(CrudCargoService cargoService,
			CrudFuncionarioService funcionarioService,
			RelatoriosService relatoriosService,
			CrudUnidadeTrabalhoService unidadeTrabalhoService,
			RelatorioFuncionarioDinamico relatorioDinamico) {
		this.cargoService=cargoService;
		this.funcionarioService=funcionarioService;
		this.unidadeTrabalhoService=unidadeTrabalhoService;
		this.relatoriosService=relatoriosService;
		this.relatorioDinamico=relatorioDinamico;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("Que ação quer executar ?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionário");
			System.out.println("3 - Unidade de Trabalho");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatório dinâmico (digitar NULL para dispensar um campo)");
			
			int action = scanner.nextInt();
			if(action==1) {
				cargoService.iniciar();
			}else if(action==2) {
				funcionarioService.iniciar();
			}else if(action==3) {
				unidadeTrabalhoService.iniciar();
			}else if(action==4) {
				relatoriosService.iniciar();
			}else if(action==5) {
				relatorioDinamico.iniciar();
			}
			else {
				system=false;
			}
		}

	}

}
