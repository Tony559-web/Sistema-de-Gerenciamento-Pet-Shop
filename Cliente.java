package Trabalho;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
	  static Scanner scanner = new Scanner(System.in);
	  static  List<Cliente> clientes = new ArrayList<>();
	  private List<Pet> pets = new ArrayList<>();
	
		
	  
	  
	  private String nome;
	  private String telefone;
	  private String email;
		    
	// Construtor correto
	  public Cliente(String nome, String telefone, String email) {
		  this.nome = nome;
		  this.telefone = telefone;
		  this.email = email;
	}
	
	// Cadastro de cliente
	  private void cadastrarCliente() {
		    System.out.print("Nome (ou 'sair' para voltar): ");
		    String nome = scanner.nextLine();
		    if (nome.equalsIgnoreCase("sair")) return; // Retorna ao menu principal

		    System.out.print("Telefone (ou 'sair' para voltar): ");
		    String telefone = scanner.nextLine();
		    if (telefone.equalsIgnoreCase("sair")) return;

		    System.out.print("Email (ou 'sair' para voltar): ");
		    String email = scanner.nextLine();
		    if (email.equalsIgnoreCase("sair")) return;

		    clientes.add(new Cliente(nome, telefone, email));
		    System.out.println("Cliente cadastrado com sucesso!");
		}
	  // Método para obter o nome do cliente
	  public String getNome() {
		  return nome;
	  }
    // Listagem de todos os clientes
    	public static void listarClientes() {
    		if (clientes.isEmpty()) {
    			System.out.println("Nenhum cliente cadastrado.");
    			return;
    		}
    		for (int i = 0; i < clientes.size(); i++) {
    			System.out.println((i + 1) + ". " + clientes.get(i));
    		}
    	}
   
    	


    // Métodos para gerenciar pets
    	    public void adicionarPet(Pet pet) {
    	        pets.add(pet);
    	    }

    	    public List<Pet> getPets() {
    	        return pets;
    	    }
 }
    
  

