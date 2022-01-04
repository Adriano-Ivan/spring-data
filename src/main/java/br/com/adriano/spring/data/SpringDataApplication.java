package br.com.adriano.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.adriano.spring.data.orm.Cargo;
import br.com.adriano.spring.data.repository.CargoRepository;
import br.com.adriano.spring.data.service.CrudCargoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner{

	private final CrudCargoService cargoService;
	private Boolean system = true;
	
	public SpringDataApplication(CrudCargoService cargoService) {
		this.cargoService=cargoService;
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
			
			int action = scanner.nextInt();
			if(action==1) {
				cargoService.iniciar();
			}else {
				system=false;
			}
		}
		
		
		Cargo cargo = new Cargo();
		cargo.setDescricao("DESENVOLVEDOR DE SOFTWARE");
		
		//repository.save(cargo);
	}

}
