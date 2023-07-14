import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class AppGUI extends JFrame {
    private Loja loja;

    private JPanel initialPanel;
    private JPanel resourcesPanel;
    private JPanel clientsPanel;
    private JPanel inventoryPanel;
    private JPanel salesPanel;

    private JPanel novaLojaPanel;

    public AppGUI() {
        super("SYSCAR - Sistema de Informações para Concessionárias de Automóveis");

        loja = new Loja();

        // Carregar objeto loja de arquivo JSON
        try {
            loja = loja.carregarLoja();
        } catch (Exception e) {

            System.out.println("Erro ao carregar arquivo loja.json!");
        }

        createInitialPanel();
        createResourcesPanel();
        createClientsPanel();
        createInventoryPanel();
        createSalesPanel();
        createNovaLojaPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void createInitialPanel() {
        initialPanel = new JPanel();
        initialPanel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Nome: " + loja.getNome());
        JLabel caixaLabel = new JLabel("Saldo de Caixa: " + loja.getCaixa());

        JButton resourcesButton = new JButton("Recursos Humanos");
        resourcesButton.addActionListener(e -> showResourcesPanel());

        JButton clientsButton = new JButton("Clientes");
        clientsButton.addActionListener(e -> showClientsPanel());

        JButton inventoryButton = new JButton("Abastecimento e Venda");
        inventoryButton.addActionListener(e -> showInventoryPanel());

        JButton salesButton = new JButton("Estoque");
        salesButton.addActionListener(e -> showSalesPanel());

        JButton exitButton = new JButton("Sair do Programa");
        exitButton.addActionListener(e -> exitProgram());

        JButton novaLojaButton = new JButton("Criar Nova Loja");
        novaLojaButton.addActionListener(e -> showNovaLojaPanel());

        initialPanel.add(nameLabel);
        initialPanel.add(caixaLabel);
        initialPanel.add(novaLojaButton);
        initialPanel.add(resourcesButton);
        initialPanel.add(clientsButton);
        initialPanel.add(inventoryButton);
        initialPanel.add(salesButton);
        initialPanel.add(exitButton);

        add(initialPanel);
        /*
        if (loja.getNome() == null) {
            showNovaLojaPanel();
        } else {
            //showInitialPanel();
        }
        */
    }

    private void createResourcesPanel() {
        resourcesPanel = new JPanel();
        resourcesPanel.setLayout(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("Recursos Humanos");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        JButton addEmployeeButton = new JButton("Cadastrar Funcionário");
        addEmployeeButton.addActionListener(e -> {
            // Lógica para cadastrar funcionário
            loja.cadastrarFuncionarioGUI();
        });

        JButton fireEmployeeButton = new JButton("Demitir Funcionário");
        fireEmployeeButton.addActionListener(e -> {
            // Lógica para demitir funcionário
            loja.demitirFuncionarioGUI();
        });

        JButton searchEmployeeButton = new JButton("Consultar Funcionário");
        searchEmployeeButton.addActionListener(e -> {
            // Lógica para consultar funcionário
            loja.consultarFuncionarioGUI();
        });

        JButton listEmployeesButton = new JButton("Listar Funcionários");
        listEmployeesButton.addActionListener(e -> {
            // Lógica para listar funcionários
            loja.listarFuncionariosGUI();
        });

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> showInitialPanel());

        resourcesPanel.add(titleLabel);
        resourcesPanel.add(addEmployeeButton);
        resourcesPanel.add(fireEmployeeButton);
        resourcesPanel.add(searchEmployeeButton);
        resourcesPanel.add(listEmployeesButton);
        resourcesPanel.add(backButton);
    }

    private void createClientsPanel() {
        clientsPanel = new JPanel();
        clientsPanel.setLayout(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("Clientes");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        JButton addClientButton = new JButton("Cadastrar Cliente");
        addClientButton.addActionListener(e -> {
            // Lógica para cadastrar cliente
            loja.cadastrarClienteGUI();

        });

        JButton searchClientButton = new JButton("Consultar Cliente");
        searchClientButton.addActionListener(e -> {
            // Lógica para consultar cliente
        });

        JButton listClientsButton = new JButton("Listar Clientes");
        listClientsButton.addActionListener(e -> {
            // Lógica para listar clientes
        });

        JButton listVIPClientsButton = new JButton("Listar Clientes VIP");
        listVIPClientsButton.addActionListener(e -> {
            // Lógica para listar clientes VIP
        });

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> showInitialPanel());

        clientsPanel.add(titleLabel);
        clientsPanel.add(addClientButton);
        clientsPanel.add(searchClientButton);
        clientsPanel.add(listClientsButton);
        clientsPanel.add(listVIPClientsButton);
        clientsPanel.add(backButton);
    }

    private void createInventoryPanel() {
        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("Abastecimento e Venda");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        JButton stockButton = new JButton("Abastecer Estoque (Fábrica)");
        stockButton.addActionListener(e -> {
            // Lógica para abastecer estoque
        });

        JButton sellButton = new JButton("Vender Carro");
        sellButton.addActionListener(e -> {
            // Lógica para vender carro
        });

        JButton returnButton = new JButton("Devolver Carro (Fábrica)");
        returnButton.addActionListener(e -> {
            // Lógica para devolver carro à fábrica
        });

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> showInitialPanel());

        inventoryPanel.add(titleLabel);
        inventoryPanel.add(stockButton);
        inventoryPanel.add(sellButton);
        inventoryPanel.add(returnButton);
        inventoryPanel.add(backButton);
    }

    private void createSalesPanel() {
        salesPanel = new JPanel();
        salesPanel.setLayout(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("Estoque");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> showInitialPanel());

        salesPanel.add(titleLabel);
        salesPanel.add(backButton);
    }

    private void createNovaLojaPanel() {
        novaLojaPanel = new JPanel();
        novaLojaPanel.setLayout(new GridLayout(7, 1));

        JLabel titleLabel = new JLabel("Nova Loja\n");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        JLabel nameLabel = new JLabel("Nome da Loja:");
        JTextField nameField = new JTextField();

        JLabel caixaLabel = new JLabel("Saldo de Caixa:");
        JTextField caixaField = new JTextField();

        JButton confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(e -> {
            // Lógica para criar nova loja
            loja = new Loja(nameField.getText(), Double.parseDouble(caixaField.getText()));
            createInitialPanel();
            showInitialPanel();
        });

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> showInitialPanel());

        novaLojaPanel.add(titleLabel);
        novaLojaPanel.add(nameLabel);
        novaLojaPanel.add(nameField);
        novaLojaPanel.add(caixaLabel);
        novaLojaPanel.add(caixaField);
        novaLojaPanel.add(confirmButton);
        novaLojaPanel.add(backButton);

        //add(novaLojaPanel);
    }

    private void showNovaLojaPanel() {
        setContentPane(novaLojaPanel);
        revalidate();
        repaint();
    }

    private void showResourcesPanel() {
        setContentPane(resourcesPanel);
        revalidate();
        repaint();
    }

    private void showClientsPanel() {
        setContentPane(clientsPanel);
        revalidate();
        repaint();
    }

    private void showInventoryPanel() {
        setContentPane(inventoryPanel);
        revalidate();
        repaint();
    }

    private void showSalesPanel() {
        setContentPane(salesPanel);
        revalidate();
        repaint();
    }

    private void showInitialPanel() {
        setContentPane(initialPanel);
        revalidate();
        repaint();
    }

    private void exitProgram() {
        // Lógica para salvar loja em arquivo JSON
        try {
            loja.salvarLoja(loja);
        } catch (Exception e) {
            System.out.println("Erro ao salvar arquivo loja.json!");
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppGUI();
        });
    }
}
