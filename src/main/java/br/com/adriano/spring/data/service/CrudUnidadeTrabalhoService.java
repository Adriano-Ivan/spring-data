package br.com.adriano.spring.data.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.adriano.spring.data.orm.Endereco;
import br.com.adriano.spring.data.orm.Funcionario;
import br.com.adriano.spring.data.orm.UnidadeTrabalho;
import br.com.adriano.spring.data.repository.EnderecoRepository;
import br.com.adriano.spring.data.repository.FuncionarioRepository;
import br.com.adriano.spring.data.repository.UnidadeDeTrabalhoDeFuncionarioRepository;
import br.com.adriano.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {

	private Boolean system =true;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	//private final EnderecoRepository enderecoRepository;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository
			) {
		this.unidadeTrabalhoRepository=unidadeTrabalhoRepository;
		
	}

	public void iniciar() {
		Scanner scanner = new Scanner(System.in);
		while(system) {
			System.out.println("Qual ação de unidade de trabalho deseja executar ?");
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
		System.out.println("Descrição da unidade: ");
		String descricao= scanner.nextLine();
		System.out.println("Rua: ");
		String rua = scanner.nextLine();
		System.out.println("Número: ");
		String numero= scanner.nextLine();
		System.out.println("Cidade: ");
		String cidade=scanner.nextLine();
		
		Endereco endereco = new Endereco(cidade,
				rua,Integer.parseInt(numero));
		UnidadeTrabalho ut = new UnidadeTrabalho(descricao,
				endereco);
				
		unidadeTrabalhoRepository.save(ut);
		System.out.println("Salvo.");
	}

	private void atualizar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID da unidade: ");
		Long id = scanner.nextLong();
		System.out.println("Descrição da unidade: ");
		String descricao= scanner.nextLine();
		System.out.println("Rua: ");
		String rua = scanner.nextLine();
		System.out.println("Número: ");
		String numero= scanner.nextLine();
		System.out.println("Cidade: ");
		String cidade=scanner.nextLine();
		
		Endereco endereco = new Endereco(cidade,
				rua,Integer.parseInt(numero));
		UnidadeTrabalho ut = new UnidadeTrabalho(descricao,
				endereco);
		ut.setId(id);
				
		unidadeTrabalhoRepository.save(ut);
		System.out.println("Atualizado.");
		//scanner.close();

	}
	private void visualizar() {
		Iterable<UnidadeTrabalho> unidades= unidadeTrabalhoRepository
				.findAll();
		System.out.println("------------------------");
		unidades.forEach(unidade->System.out.println(unidade
				));
		System.out.println("------------------------");
	}
	private void excluir() {
		visualizar();
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID da unidade: ");
		long id = scanner.nextLong();
		
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Unidade excluída.");
		//scanner.close();
	}
	private void encontrar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID da unidade: ");
		long id =scanner.nextLong();
		
		Optional<UnidadeTrabalho> optionalUnidade=unidadeTrabalhoRepository.findById(id);
		UnidadeTrabalho ut = optionalUnidade.orElse(null);
		if(ut!=null) {
			System.out.println(ut);
		}else {
			System.out.println("Unidade não encontrado.");
		}
		
		//scanner.close();
	}
}
