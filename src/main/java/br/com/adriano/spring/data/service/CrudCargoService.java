package br.com.adriano.spring.data.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.adriano.spring.data.orm.Cargo;
import br.com.adriano.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private Boolean system =true;
	private final CargoRepository cargoRepository;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void iniciar() {
		Scanner scanner = new Scanner(System.in);
		while(system) {
			System.out.println("Qual ação de cargo deseja executar ?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Excluir");
			System.out.println("5 - Visualizar registro específico");
			int action = scanner.nextInt();
			switch(action) {
			case 1:
				salvar();
				break;
			case 2:
				atualizar();
				break;
			case 3:
				visualizar();
				break;
			case 4:
				excluir();
				break;
			case 5:
				encontrar();
				break;
			default:
				system=false;
				break;
			}
			
		}
	}

	private void salvar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.nextLine();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Salvo.");
	}

	private void atualizar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID do cargo: ");
		Long id = scanner.nextLong();
		System.out.println("Descrição do cargo: ");
		scanner.nextLine();
		String descricao = scanner.nextLine();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Atualizado.");
		//scanner.close();

	}
	private void visualizar() {
		Iterable<Cargo> cargos= cargoRepository
				.findAll();
		System.out.println("------------------------");
		cargos.forEach(cargo->System.out.println(cargo
				));
		System.out.println("------------------------");
	}
	private void excluir() {
		visualizar();
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID do cargo: ");
		long id = scanner.nextLong();
		
		cargoRepository.deleteById(id);
		System.out.println("Cargo excluído.");
		//scanner.close();
	}
	private void encontrar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID do cargo: ");
		long id =scanner.nextLong();
		
		Optional<Cargo> optionalCargo=cargoRepository.findById(id);
		Cargo cargo = optionalCargo.orElse(null);
		System.out.println(cargo);
		//scanner.close();
	}
}
