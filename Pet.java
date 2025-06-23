package Trabalho;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Pet extends Cliente {
	

	 static Scanner scanner = new Scanner(System.in);
		    private String nome;
		    private String especie;
		    private String raca;
		    private int idade;
		    private double peso;
		  

		        
		    

	 // Construtor corrigido
		    public Pet(String nome, String especie, String raca, int idade, double peso) {
		        super("Nome ", "Telefone ", "Email "); // Chamada ao construtor de Cliente
		        this.nome = nome;
		        this.especie = especie;
		        this.raca = raca;
		        this.idade = idade;
		        this.peso = peso;
		    }

		    @Override
		    public String toString() {
		        return nome + " (" + especie + ", " + raca + ", " + idade + " anos, " + peso + " kg)";
		    }
		    
	 // Método para obter o nome do pet
		    public String getNome() {
		    	return nome;
		    }
		    
		    // Método para obter a espécie do pet
		    public String getEspecie() {
		    	return especie;
		    }
		    public int getIdade() {
		    	return idade;
		    }
		    
		    public double getPeso() {
		    	return peso;
		    }
		
	 
	// Cadastro de pet associado a um cliente
		    private void cadastrarPet() {
		        listarClientes();
		        if (clientes.isEmpty()) return;

		        System.out.print("Escolha o número do cliente (ou 'sair' para voltar): ");
		        String entrada = scanner.nextLine();
		        if (entrada.equalsIgnoreCase("sair")) return;

		        int idx = Integer.parseInt(entrada) - 1;
		        if (idx < 0 || idx >= clientes.size()) {
		            System.out.println("Cliente inválido.");
		            return;
		        }

		        Cliente cliente = clientes.get(idx);

		        System.out.print("Nome do pet (ou 'sair' para voltar): ");
		        String nome = scanner.nextLine();
		        if (nome.equalsIgnoreCase("sair")) return;

		        System.out.print("Espécie (ou 'sair' para voltar): ");
		        String especie = scanner.nextLine();
		        if (especie.equalsIgnoreCase("sair")) return;

		        System.out.print("Raça (ou 'sair' para voltar): ");
		        String raca = scanner.nextLine();
		        if (raca.equalsIgnoreCase("sair")) return;

		        System.out.print("Idade (ou 'sair' para voltar): ");
		        String entradaIdade = scanner.nextLine();
		        if (entradaIdade.equalsIgnoreCase("sair")) return;
		        int idade = Integer.parseInt(entradaIdade);

		        System.out.print("Peso (ou 'sair' para voltar): ");
		        String entradaPeso = scanner.nextLine();
		        if (entradaPeso.equalsIgnoreCase("sair")) return;
		        double peso = Double.parseDouble(entradaPeso);

		        Pet pet = new Pet(nome, especie, raca, idade, peso);
		        cliente.adicionarPet(pet);
		        System.out.println("Pet cadastrado com sucesso!");
		   }
		
	 // Listagem de pets de um cliente
		    private void listarPets() {
		        listarClientes();
		        if (clientes.isEmpty()) return;

		        System.out.print("Escolha o número do cliente (ou 'sair' para voltar): ");
		        String entrada = scanner.nextLine();
		        if (entrada.equalsIgnoreCase("sair")) {
		            System.out.println("Operação cancelada.");
		            return;
		        }

		        int idx;
		        try {
		            idx = Integer.parseInt(entrada) - 1;
		        } catch (NumberFormatException e) {
		            System.out.println("Entrada inválida! Digite um número.");
		            return;
		        }

		        if (idx < 0 || idx >= clientes.size()) {
		            System.out.println("Cliente inválido.");
		            return;
		        }

		        Cliente cliente = clientes.get(idx);
		        List<Pet> pets = cliente.getPets();

		        if (pets.isEmpty()) {
		            System.out.println("Nenhum pet cadastrado para este cliente.");
		        } else {
		            System.out.println("Pets do cliente " + cliente.getNome() + ":");
		            for (Pet pet : pets) {
		                System.out.println("- " + pet.getNome() + " (Espécie: " + pet.getEspecie() + ", Idade: " + pet.getIdade() + " anos, Peso: " + pet.getPeso() + " kg)");
		            }
		        }
		    }

}
