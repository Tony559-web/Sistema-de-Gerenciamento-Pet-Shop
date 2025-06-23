package Trabalho;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Servicos {
	
    static Scanner scanner = new Scanner(System.in);
    private List<Cliente> clientes;
    private List<ServicoContratado> servicosContratados = new ArrayList<>(); // Lista de serviços contratados

    public Servicos(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("Clientes cadastrados:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNome());
        }
    }

    public void contratarServico() {
        listarClientes();
        if (clientes.isEmpty()) return;

        System.out.print("Escolha o número do cliente (ou 'sair' para voltar): ");
        String entrada = scanner.nextLine();
        if (entrada.equalsIgnoreCase("sair")) return;

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
            System.out.println("Este cliente não possui pets cadastrados.");
            return;
        }

        System.out.println("Escolha o pet:");
        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ". " + pets.get(i).getNome() + " (do cliente " + cliente.getNome() + ")");
        }

        System.out.print("Escolha o número do pet (ou 'sair' para voltar): ");
        String entradaPet = scanner.nextLine();
        if (entradaPet.equalsIgnoreCase("sair")) return;

        int petIdx;
        try {
            petIdx = Integer.parseInt(entradaPet) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
            return;
        }

        if (petIdx < 0 || petIdx >= pets.size()) {
            System.out.println("Pet inválido.");
            return;
        }

        Pet pet = pets.get(petIdx);

        System.out.println("Serviços disponíveis:");
        System.out.println("1. Banho e Tosa");
        System.out.println("2. Consulta Veterinária");
        System.out.println("3. Hospedagem");
        System.out.println("4. Adestramento");
        System.out.print("Escolha o serviço (ou 'sair' para voltar): ");
        String entradaServico = scanner.nextLine();
        if (entradaServico.equalsIgnoreCase("sair")) return;

        int servico;
        try {
            servico = Integer.parseInt(entradaServico);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
            return;
        }

        System.out.print("Data do serviço (AAAA-MM-DD) (ou 'sair' para voltar): ");
        String dataStr = scanner.nextLine();
        if (dataStr.equalsIgnoreCase("sair")) return;

        LocalDate data;
        try {
            data = LocalDate.parse(dataStr);
        } catch (Exception e) {
            System.out.println("Data inválida! Insira no formato AAAA-MM-DD.");
            return;
        }

        Servico s = null;
        switch (servico) {
            case 1 -> s = new BanhoETosa(data);
            case 2 -> s = new ConsultaVeterinaria(data);
            case 3 -> s = new Hospedagem(data);
            case 4 -> s = new Adestramento(data);
            default -> {
                System.out.println("Serviço inválido.");
                return;
            }
        }

        ServicoContratado servicoContratado = new ServicoContratado(cliente, pet, s);
        servicosContratados.add(servicoContratado);

        System.out.println("Serviço contratado com sucesso para " + pet.getNome() + " do cliente " + cliente.getNome());
    }

    public void listarServicosContratados() {
        if (servicosContratados.isEmpty()) {
            System.out.println("Nenhum serviço contratado.");
            return;
        }

        System.out.println("Serviços contratados:");
        for (int i = 0; i < servicosContratados.size(); i++) {
            ServicoContratado servico = servicosContratados.get(i);
            System.out.println((i + 1) + ". Cliente: " + servico.getCliente().getNome() +
                               ", Pet: " + servico.getPet().getNome() +
                               ", Serviço: " + servico.getServico().getClass().getSimpleName() +
                               ", Data: " + servico.getServico().getData()); // Chamada correta do método getData
        }
    }

    public void cancelarServico() {
        listarServicosContratados();
        if (servicosContratados.isEmpty()) return;

        System.out.print("Escolha o número do serviço para cancelar (ou 'sair' para voltar): ");
        String entrada = scanner.nextLine();
        if (entrada.equalsIgnoreCase("sair")) return;

        int idx;
        try {
            idx = Integer.parseInt(entrada) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
            return;
        }

        if (idx < 0 || idx >= servicosContratados.size()) {
            System.out.println("Serviço inválido.");
            return;
        }

        servicosContratados.remove(idx);
        System.out.println("Serviço cancelado com sucesso!");
    }
}