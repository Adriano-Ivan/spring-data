package br.com.adriano.spring.data.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.adriano.spring.data.orm.Cargo;
import br.com.adriano.spring.data.orm.Funcionario;
import br.com.adriano.spring.data.orm.UnidadeDeTrabalhoDeFuncionario;
import br.com.adriano.spring.data.orm.UnidadeTrabalho;
import br.com.adriano.spring.data.repository.CargoRepository;
import br.com.adriano.spring.data.repository.FuncionarioRepository;
import br.com.adriano.spring.data.repository.UnidadeDeTrabalhoDeFuncionarioRepository;
import br.com.adriano.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system =true;
	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeTrabalhoRepository utRepository;
	private final UnidadeDeTrabalhoDeFuncionarioRepository unidadeDeTrabalhoDeFuncionarioRepository;
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
			CargoRepository cargoRepository,UnidadeTrabalhoRepository utRepository,UnidadeDeTrabalhoDeFuncionarioRepository unidadeDeTrabalhoDeFuncionarioRepository) {
		this.funcionarioRepository=funcionarioRepository;
		this.cargoRepository=cargoRepository;
		this.utRepository=utRepository;
		this.unidadeDeTrabalhoDeFuncionarioRepository=unidadeDeTrabalhoDeFuncionarioRepository;
	}
	private void mostrarCargos() {
		Iterable<Cargo> cargos= cargoRepository
				.findAll();
		System.out.println("------------------------");
		cargos.forEach(cargo->System.out.println(cargo
				));
		System.out.println("------------------------");
	}
	private void mostrarUnidades() {
		Iterable<UnidadeTrabalho> unidades= utRepository
				.findAll();
		System.out.println("------------------------");
		unidades.forEach(unidade->System.out.println(unidade
				));
		System.out.println("------------------------");
	}
	private void mostrarFuncionariosEsuasUnidades() {
		Iterable<UnidadeDeTrabalhoDeFuncionario> unidadesEfuncionarios= unidadeDeTrabalhoDeFuncionarioRepository.findAll();
		System.out.println("------------------------");
		unidadesEfuncionarios.forEach(uf->System.out.println(uf
				));
		System.out.println("------------------------");
	}
	private void adicionarUnidade(Funcionario f) {
		Scanner scanner = new Scanner(System.in);
		String id = "0";
		System.out.println("Adicione uma unidade de trabalho: (0 para não adicionar)");
		id = scanner.nextLine();
		if(!id.equals("0")) {
			Optional<UnidadeTrabalho> utOpt = utRepository.findById(Long.parseLong(id));
			UnidadeTrabalho ut=utOpt.orElse(null);
			if(ut!=null) {
				UnidadeDeTrabalhoDeFuncionario utf=new UnidadeDeTrabalhoDeFuncionario(ut,f);
				f.adicionarUnidadeDeTrabalho(utf);
			}else {
				System.out.println("Unidade de trabalho não encontrada.");
			}
		}else {
			System.out.println("Por enquanto não será adicionada uma unidade de trabalho.");
		}
	}
	public void iniciar() {
		Scanner scanner = new Scanner(System.in);
		while(system) {
			System.out.println("Qual ação de funcionário deseja executar ?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Excluir");
			System.out.println("5 - Visualizar registro específico");
			System.out.println("6 - Visualizar funcionários e suas unidades");
			System.out.println("7 - Desassociar funcionário e unidade");
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
			case 6:
				mostrarFuncionariosEsuasUnidades();
				break;
			case 7:
				excluirAssociacao();
				break;
			default:
				system=false;
				break;
			}
			
		}
	}

	private void salvar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nome do funcionário: ");
		String nome = scanner.nextLine();
		System.out.println("CPF do funcionário: ");
		String cpf = scanner.nextLine();
		System.out.println("Salário do funcionário: ");
		String stringSalario=scanner.nextLine();
		
		mostrarCargos();
		
		System.out.println("ID do cargo: ");
		String idCargo=scanner.nextLine().trim();
		System.out.println(idCargo);
		Optional<Cargo> cargoOpt = cargoRepository.findById(Long.parseLong(idCargo));
		Cargo cargo= cargoOpt.orElse(null);
		
		mostrarUnidades();
		
		Funcionario funcionario = new Funcionario(nome,
				cpf,stringSalario,cargo);
		
		adicionarUnidade(funcionario);
		
		funcionarioRepository.save(funcionario);
		System.out.println("Salvo.");
	}

	private void atualizar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID do funcionário");
		Long id = scanner.nextLong();
		System.out.println("Nome do funcionário: ");
		scanner.nextLine();
		String nome = scanner.nextLine();
		System.out.println("CPF do funcionário: ");
		String cpf = scanner.nextLine();
		System.out.println("Salário do funcionário: ");
		String stringSalario=scanner.nextLine();
		
		mostrarCargos();
		
		System.out.println("ID do cargo: ");
		long idCargo=scanner.nextLong();
		
		Optional<Cargo> cargoOpt = cargoRepository.findById(idCargo);
		Cargo cargo= cargoOpt.orElse(null);
		
		mostrarUnidades();
		Funcionario funcionario = new Funcionario(nome,
				cpf,stringSalario,cargo);
		
		funcionario.setId(id);
		adicionarUnidade(funcionario);
		
		funcionarioRepository.save(funcionario);
		System.out.println("Atualizado.");
		//scanner.close();

	}
	private void visualizar() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Qual página você deseja visualizar ?");
		Integer page = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(page,1,Sort.by(Sort.Direction.ASC,"salario"));
		
		Page<Funcionario> funcionarios= funcionarioRepository
				.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Página atual: "+funcionarios.getNumber());
		System.out.println("Total de elementos: "+funcionarios.getTotalElements());
		System.out.println("------------------------");
		funcionarios.forEach(funcionario->System.out.println(funcionario
				));
		System.out.println("------------------------");
	}
	private void excluir() {
		visualizar();
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID do funcionário: ");
		long id = scanner.nextLong();
		
		funcionarioRepository.deleteById(id);
		System.out.println("Funcionário excluído.");
		//scanner.close();
	}
	private void excluirAssociacao() {
		mostrarFuncionariosEsuasUnidades();
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID da associação: ");
		long id = scanner.nextLong();
		
		unidadeDeTrabalhoDeFuncionarioRepository.deleteById(id);
		System.out.println("Associação excluída.");
	}
	private void encontrar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID do funcionário: ");
		long id =scanner.nextLong();
		
		Optional<Funcionario> optionalFuncionario=funcionarioRepository.findById(id);
		Funcionario f = optionalFuncionario.orElse(null);
		if(f!=null) {
			System.out.println(f);
		}else {
			System.out.println("Funcionário não encontrado.");
		}
		
		//scanner.close();
	}
}
