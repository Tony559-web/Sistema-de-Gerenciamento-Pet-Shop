import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TelaPrincipalPetShop extends JFrame {

    private static ArrayList<String> clientes = new ArrayList<>();
    private static ArrayList<String> pets = new ArrayList<>();

    public TelaPrincipalPetShop() {
        setTitle("Sistema de Gerenciamento - Pet Shop");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnCadastroCliente = new JButton("Cadastro de Clientes");
        JButton btnCadastroPet = new JButton("Cadastro de Pets");
        JButton btnContratarServico = new JButton("Contratar Serviço");
        JButton btnListarClientes = new JButton("Listar Clientes");
        JButton btnListarPets = new JButton("Listar Pets");
        JButton btnSair = new JButton("Sair");

        painel.add(btnCadastroCliente);
        painel.add(btnCadastroPet);
        painel.add(btnContratarServico);
        painel.add(btnListarClientes);
        painel.add(btnListarPets);
        painel.add(btnSair);

        add(painel);

        btnCadastroCliente.addActionListener(e -> abrirTelaCadastroCliente());
        btnCadastroPet.addActionListener(e -> abrirTelaCadastroPet());
        btnContratarServico.addActionListener(e -> abrirTelaContratarServico());
        btnListarClientes.addActionListener(e -> abrirTelaListarClientes());
        btnListarPets.addActionListener(e -> abrirTelaListarPets());
        btnSair.addActionListener(e -> System.exit(0));
    }
    

    private void abrirTelaCadastroCliente() {
        JFrame frame = new JFrame("Cadastro de Cliente");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 5, 5));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();
        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();
        txtTelefone.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) e.consume();
            }
        });
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnVoltar = new JButton("Voltar");

        painel.add(lblNome); painel.add(txtNome);
        painel.add(lblTelefone); painel.add(txtTelefone);
        painel.add(lblEmail); painel.add(txtEmail);
        painel.add(btnSalvar); painel.add(btnVoltar);

        frame.add(painel);
        frame.setVisible(true);

        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String telefone = txtTelefone.getText().trim();
            String email = txtEmail.getText().trim();

            if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(frame, "Email inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                clientes.add("Nome: " + nome + ", Telefone: " + telefone + ", Email: " + email);
                JOptionPane.showMessageDialog(frame, "Cliente salvo com sucesso!");
                frame.dispose();
            }
        });

        btnVoltar.addActionListener(e -> frame.dispose());
    }

    
    
    private void abrirTelaCadastroPet() {
        JFrame frame = new JFrame("Cadastro de Pet");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(7, 2, 5, 5));

        JLabel lblNome = new JLabel("Nome do Pet:");
        JTextField txtNome = new JTextField();
        JLabel lblEspecie = new JLabel("Espécie:");
        JTextField txtEspecie = new JTextField();
        JLabel lblRaca = new JLabel("Raça:");
        JTextField txtRaca = new JTextField();
        JLabel lblIdade = new JLabel("Idade:");
        JTextField txtIdade = new JTextField();
        txtIdade.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) e.consume();
            }
        });
        JLabel lblPeso = new JLabel("Peso (kg):");
        JTextField txtPeso = new JTextField();
        txtPeso.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != ',') e.consume();
            }
        });
        JLabel lblDono = new JLabel("Nome do Dono:");
        JTextField txtDono = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnVoltar = new JButton("Voltar");

        painel.add(lblNome); painel.add(txtNome);
        painel.add(lblEspecie); painel.add(txtEspecie);
        painel.add(lblRaca); painel.add(txtRaca);
        painel.add(lblIdade); painel.add(txtIdade);
        painel.add(lblPeso); painel.add(txtPeso);
        painel.add(lblDono); painel.add(txtDono);
        painel.add(btnSalvar); painel.add(btnVoltar);

        frame.add(painel);
        frame.setVisible(true);

        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String especie = txtEspecie.getText().trim();
            String raca = txtRaca.getText().trim();
            String idade = txtIdade.getText().trim();
            String peso = txtPeso.getText().trim();
            String dono = txtDono.getText().trim();

            if (nome.isEmpty() || especie.isEmpty() || raca.isEmpty() || idade.isEmpty() || peso.isEmpty() || dono.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Integer.parseInt(idade);
                    Double.parseDouble(peso.replace(',', '.'));
                    pets.add("Pet: " + nome + ", Espécie: " + especie + ", Raça: " + raca +
                             ", Idade: " + idade + ", Peso: " + peso + "kg, Dono: " + dono);
                    JOptionPane.showMessageDialog(frame, "Pet cadastrado com sucesso!");
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Idade deve ser inteiro e peso decimal.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnVoltar.addActionListener(e -> frame.dispose());
    }

    

    private void abrirTelaListarClientes() {
        JFrame frame = new JFrame("Lista de Clientes");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String cliente : clientes) model.addElement(cliente);

        JList<String> lista = new JList<>(model);
        JScrollPane scroll = new JScrollPane(lista);

        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnVoltar = new JButton("Voltar");

        btnEditar.addActionListener(e -> {
            int index = lista.getSelectedIndex();
            if (index != -1) {
                String clienteSelecionado = clientes.get(index);
                String[] partes = clienteSelecionado.split(", ");
                String nome = partes[0].substring(6);
                String telefone = partes[1].substring(10);
                String email = partes[2].substring(7);

                JTextField txtNome = new JTextField(nome);
                JTextField txtTelefone = new JTextField(telefone);
                txtTelefone.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        if (!Character.isDigit(e.getKeyChar())) e.consume();
                    }
                });
                JTextField txtEmail = new JTextField(email);

                JPanel painelEditar = new JPanel(new GridLayout(3, 2));
                painelEditar.add(new JLabel("Nome:")); painelEditar.add(txtNome);
                painelEditar.add(new JLabel("Telefone:")); painelEditar.add(txtTelefone);
                painelEditar.add(new JLabel("Email:")); painelEditar.add(txtEmail);

                int resultado = JOptionPane.showConfirmDialog(frame, painelEditar, "Editar Cliente", JOptionPane.OK_CANCEL_OPTION);

                if (resultado == JOptionPane.OK_OPTION) {
                    String novoNome = txtNome.getText().trim();
                    String novoTelefone = txtTelefone.getText().trim();
                    String novoEmail = txtEmail.getText().trim();

                    if (novoNome.isEmpty() || novoTelefone.isEmpty() || novoEmail.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else if (!isValidEmail(novoEmail)) {
                        JOptionPane.showMessageDialog(frame, "Email inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String atualizado = "Nome: " + novoNome + ", Telefone: " + novoTelefone + ", Email: " + novoEmail;
                        clientes.set(index, atualizado);
                        model.set(index, atualizado);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um cliente para editar.");
            }
        });

        btnExcluir.addActionListener(e -> {
            int index = lista.getSelectedIndex();
            if (index != -1) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Deseja excluir este cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    clientes.remove(index);
                    model.remove(index);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um cliente para excluir.");
            }
        });

        btnVoltar.addActionListener(e -> frame.dispose());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnVoltar);

        frame.add(scroll, BorderLayout.CENTER);
        frame.add(painelBotoes, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    

    private void abrirTelaListarPets() {
        JFrame frame = new JFrame("Lista de Pets");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String pet : pets) model.addElement(pet);

        JList<String> lista = new JList<>(model);
        JScrollPane scroll = new JScrollPane(lista);

        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnVoltar = new JButton("Voltar");

        btnEditar.addActionListener(e -> {
            int index = lista.getSelectedIndex();
            if (index != -1) {
                String petSelecionado = pets.get(index);
                String[] partes = petSelecionado.split(", ");
                String nome = partes[0].substring(5);
                String especie = partes[1].substring(9);
                String raca = partes[2].substring(6);
                String idade = partes[3].substring(7);
                String peso = partes[4].substring(6, partes[4].length() - 2);
                String dono = partes[5].substring(6);

                JTextField txtNome = new JTextField(nome);
                JTextField txtEspecie = new JTextField(especie);
                JTextField txtRaca = new JTextField(raca);
                JTextField txtIdade = new JTextField(idade);
                txtIdade.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        if (!Character.isDigit(e.getKeyChar())) e.consume();
                    }
                });
                JTextField txtPeso = new JTextField(peso);
                txtPeso.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c) && c != '.' && c != ',') e.consume();
                    }
                });
                JTextField txtDono = new JTextField(dono);

                JPanel painelEditar = new JPanel(new GridLayout(6, 2));
                painelEditar.add(new JLabel("Nome:")); painelEditar.add(txtNome);
                painelEditar.add(new JLabel("Espécie:")); painelEditar.add(txtEspecie);
                painelEditar.add(new JLabel("Raça:")); painelEditar.add(txtRaca);
                painelEditar.add(new JLabel("Idade:")); painelEditar.add(txtIdade);
                painelEditar.add(new JLabel("Peso:")); painelEditar.add(txtPeso);
                painelEditar.add(new JLabel("Dono:")); painelEditar.add(txtDono);

                int resultado = JOptionPane.showConfirmDialog(frame, painelEditar, "Editar Pet", JOptionPane.OK_CANCEL_OPTION);
                if (resultado == JOptionPane.OK_OPTION) {
                    try {
                        Integer.parseInt(txtIdade.getText().trim());
                        Double.parseDouble(txtPeso.getText().trim().replace(',', '.'));
                        String atualizado = "Pet: " + txtNome.getText().trim() +
                                ", Espécie: " + txtEspecie.getText().trim() +
                                ", Raça: " + txtRaca.getText().trim() +
                                ", Idade: " + txtIdade.getText().trim() +
                                ", Peso: " + txtPeso.getText().trim() + "kg" +
                                ", Dono: " + txtDono.getText().trim();
                        pets.set(index, atualizado);
                        model.set(index, atualizado);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Idade ou Peso inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnExcluir.addActionListener(e -> {
            int index = lista.getSelectedIndex();
            if (index != -1) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Deseja excluir este pet?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    pets.remove(index);
                    model.remove(index);
                }
            }
        });

        btnVoltar.addActionListener(e -> frame.dispose());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnVoltar);

        frame.add(scroll, BorderLayout.CENTER);
        frame.add(painelBotoes, BorderLayout.SOUTH);
        frame.setVisible(true);
    }


    
    
    private void abrirTelaContratarServico() {
        JFrame frame = new JFrame("Contratação de Serviço");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 5, 5));

        JLabel lblDono = new JLabel("Nome do Dono:");
        JTextField txtDono = new JTextField();
        JLabel lblPet = new JLabel("Nome do Pet:");
        JTextField txtPet = new JTextField();
        JLabel lblTipo = new JLabel("Tipo de Serviço:");
        String[] tipos = { "Banho", "Tosa", "Consulta Veterinária", "Pacote Mensal" };
        JComboBox<String> cbTipo = new JComboBox<>(tipos);

        JButton btnContratar = new JButton("Contratar");
        JButton btnVoltar = new JButton("Voltar");

        painel.add(lblDono); painel.add(txtDono);
        painel.add(lblPet); painel.add(txtPet);
        painel.add(lblTipo); painel.add(cbTipo);
        painel.add(btnContratar); painel.add(btnVoltar);

        frame.add(painel);
        frame.setVisible(true);

        btnContratar.addActionListener(e -> {
            String dono = txtDono.getText().trim();
            String pet = txtPet.getText().trim();
            String tipo = (String) cbTipo.getSelectedItem();

            if (dono.isEmpty() || pet.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Serviço de " + tipo + " contratado para " + pet + "!");
                frame.dispose();
            }
        });

        btnVoltar.addActionListener(e -> frame.dispose());
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipalPetShop().setVisible(true));
    }
}
