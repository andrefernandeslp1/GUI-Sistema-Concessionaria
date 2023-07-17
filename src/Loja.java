import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.swing.*;

public class Loja {
  private String nome;
  private Double caixa;
  private ArrayList<Carro> carros;
  private ArrayList<Cliente> clientes;
  private ArrayList<Funcionario> funcionarios;
  private Map<String, Cliente> clientesVIP = new HashMap<String, Cliente>();

  private transient Scanner input = new Scanner(System.in);

  private transient JPanel Panel;
  private transient JPanel mensagens;

  public Loja() {
    this.nome = "Template";
    this.caixa = 0.0;
    this.carros = new ArrayList<Carro>();
    this.clientes = new ArrayList<Cliente>();
    this.funcionarios = new ArrayList<Funcionario>();
    this.clientesVIP = new HashMap<String, Cliente>();
  }

  public Loja(String nome, Double caixa) {
    this.nome = nome;
    this.caixa = caixa;
    this.carros = new ArrayList<Carro>();
    this.clientes = new ArrayList<Cliente>();
    this.funcionarios = new ArrayList<Funcionario>();
    this.clientesVIP = new HashMap<String, Cliente>();
  }

  // setters
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setCaixa(Double caixa) {
    this.caixa = caixa;
  }

  //! POLIMORFISMO
  public void abastecerEstoque(Carro carro) {
    this.carros.add(carro);
  }
  public void abastecerEstoque(Carro carro, int quantidade) {
    for (int i = 0; i < quantidade; i++) {
      this.carros.add(carro);
      // this.caixa -= carro.getPreco();
    }
  }

  public void demitirFuncionarioGUI(){
    if (this.funcionarios.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há funcionários cadastrados!");
      return;
    } else {
      Panel = new JPanel();
      Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
      Panel.add(new JLabel("Por favor digite a matrícula do Funcionário:"));
      JTextField matriculaRemover = new JTextField();
      Panel.add(matriculaRemover);
      int result = JOptionPane.showConfirmDialog(null, Panel, "Demitir Funcionário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if (result == JOptionPane.OK_OPTION) {
        if(verificarFuncionario(matriculaRemover.getText())){
          for (Funcionario funcionario : this.funcionarios) {
            if (funcionario.getMatricula().equals(matriculaRemover.getText())) {
              this.funcionarios.remove(funcionario);
              mensagens = new JPanel();
              mensagens.setLayout(new BoxLayout(mensagens, BoxLayout.Y_AXIS));
              mensagens.add(new JLabel("Funcionário demitido com sucesso!"));
              JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
              return;
            }
          }
        } else {
            mensagens = new JPanel();
            mensagens.add(new JLabel("Funcionário não encontrado!"));
            JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
            return;
        }
      } else {
        /*
        mensagens = new JPanel();
        mensagens.add(new JLabel("Cancelado!"));
        JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
        */
        return;
      }
    }
  }

  public boolean verificarFuncionario(String matricula) {
    for (Funcionario funcionario : this.funcionarios) {
      if (funcionario.getMatricula().equals(matricula)) {
        return true;
      }
    }
    return false;
  }

  // GETTERS
  public String getNome() {
    return nome;
  }
  public Double getCaixa() {
    return caixa;
  }
  public ArrayList<Carro> getCarros() {
    return this.carros;
  }
  public ArrayList<Cliente> getClientes() {
    return this.clientes;
  }
  public ArrayList<Funcionario> getFuncionarios() {
    return this.funcionarios;
  }

  public void venderCarro() {
    if (this.carros.isEmpty()) {
      System.out.println("\nNão há carros em Estoque!");
      return;
    }
    if (this.clientes.isEmpty()) {
      System.out.println("\nNão há clientes cadastrados!");
      return;
    }
    System.out.println("\nVendendo carro...");
    System.out.println("Digite o CPF do cliente:");
    String cpf = input.nextLine();
    if(verificarCliente(cpf)){
      System.out.println("Cliente localizado!");
    } else {
      System.out.println("Cliente não encontrado!");
      return;
    }
    System.out.println("Digite o chassi do carro:");
    String chassi = input.nextLine();
    if(verificarChassi(chassi)){
      System.out.println("Carro localizado!");
      for (Cliente cliente : this.clientes) {
        if (cliente.getCpf().equals(cpf)) {
          for (Carro carro : this.carros) {
            if (carro.getChassi().equals(chassi)) {
              cliente.addCarroComprado(carro);
              this.carros.remove(carro);
              this.caixa += carro.getPreco();
              System.out.println("Carro vendido com sucesso!");
              adicionarClienteVIP(cliente);
              System.out.println("Cliente adicionado ao programa de fidelidade Clientes VIP!");
              return;
            }
          }
        }
      }
    } else {
      System.out.println("Carro não encontrado!");
      return;
    }
  }
  public void venderCarroGUI(){
    if (this.carros.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há carros em Estoque!");
      return;
    }
    if (this.clientes.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há clientes cadastrados!");
      return;
    }
    Panel = new JPanel();
    Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
    Panel.add(new JLabel("Vendendo carro..."));
    Panel.add(new JLabel("Digite o CPF do cliente:"));
    JTextField cpf = new JTextField();
    Panel.add(cpf);
    Panel.add(new JLabel("Digite o chassi do carro:"));
    JTextField chassi = new JTextField();
    Panel.add(chassi);
    int result = JOptionPane.showConfirmDialog(null, Panel, "Vender Carro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      if(verificarCliente(cpf.getText())){
        for (Cliente cliente : this.clientes) {
          if (cliente.getCpf().equals(cpf.getText())) {
            if(verificarChassi(chassi.getText())){
              for (Carro carro : this.carros) {
                if (carro.getChassi().equals(chassi.getText())) {
                  cliente.addCarroComprado(carro);
                  this.carros.remove(carro);
                  this.caixa += carro.getPreco();
                  JOptionPane.showMessageDialog(null, "Carro vendido com sucesso!");
                  adicionarClienteVIP(cliente);
                  JOptionPane.showMessageDialog(null, "Cliente adicionado ao programa de fidelidade Clientes VIP!");
                  return;
                }
              }
            } else {

              JOptionPane.showMessageDialog(null, "Carro não encontrado!");
              return;
            }
          }
        }
      }
    }
  }

  public void devolverCarro() {
    if (this.clientes.isEmpty()) {
      System.out.println("\nNão há clientes cadastrados!");
      return;
    }
    System.out.println("\nDevolvendo carro...");
    System.out.println("Digite o CPF do cliente:");
    String cpf = input.nextLine();
    if(verificarCliente(cpf)){
      System.out.println("Cliente localizado!");
    } else {
      System.out.println("Cliente não encontrado!");
      return;
    }
    System.out.println("Digite o chassi do carro:");
    String chassi = input.nextLine();
      for (Cliente cliente : this.clientes) {
        if (cliente.getCpf().equals(cpf)) {
          for (Carro carro : cliente.getCarrosComprados()) {
            if (carro.getChassi().equals(chassi)) {
              cliente.removeCarroComprado(carro);
              this.carros.add(carro);
              this.caixa -= carro.getPreco();
              System.out.println("Carro devolvido com sucesso!");
              return;
            }
          }
        }
        else {
         System.out.println("Carro não encontrado!");
         return;
       }
      }
  }

  public void devolverCarroGUI(){
    if (this.clientes.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há clientes cadastrados!");
      return;
    }
    Panel = new JPanel();
    Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
    Panel.add(new JLabel("Devolvendo carro..."));
    Panel.add(new JLabel("Digite o CPF do cliente:"));
    JTextField cpf = new JTextField();
    Panel.add(cpf);
    Panel.add(new JLabel("Digite o chassi do carro:"));
    JTextField chassi = new JTextField();
    Panel.add(chassi);
    int result = JOptionPane.showConfirmDialog(null, Panel, "Devolver Carro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      if(verificarCliente(cpf.getText())){
        for (Cliente cliente : this.clientes) {
          if (cliente.getCpf().equals(cpf.getText())) {
            for (Carro carro : cliente.getCarrosComprados()) {
              if (carro.getChassi().equals(chassi.getText())) {
                cliente.removeCarroComprado(carro);
                this.carros.add(carro);
                this.caixa -= carro.getPreco();
                JOptionPane.showMessageDialog(null, "Carro devolvido com sucesso!");
                if (cliente.getCarrosComprados().isEmpty()) {
                  this.clientesVIP.remove(cliente.getCpf());
                  JOptionPane.showMessageDialog(null, "Cliente removido do programa de fidelidade Clientes VIP!");
                }
                return;
              }
            }
          }
          else {
           JOptionPane.showMessageDialog(null, "Carro não encontrado!");
           return;
         }
        }
      }
    }
  }

  public boolean verificarCliente(String cpf) {
    for (Cliente cliente : this.clientes) {
      if (cliente.getCpf().equals(cpf)) {
        return true;
      }
    }
    return false;
  }

  public boolean verificarChassi(String chassi) {
    for (Carro carro : this.carros) {
      if (carro.getChassi().equals(chassi)) {
        return true;
      }
    }
    return false;
  }

public void adicionarCliente2(Cliente cliente){
    clientes.add(cliente);
  }

  // funcionario que está cadastrando
  public void cadastrarCliente() {
    int count = 0;
    if (this.funcionarios.isEmpty()) {
      System.out.println("\nNão há funcionários cadastrados!");
      return;
    } else {
      System.out.println("\nDigite a matrícula do funcionário que está cadastrando o cliente:");
      String matricula = input.nextLine();
      for (Funcionario funcionario : this.funcionarios) {
        if (funcionario.getMatricula().equals(matricula)) {
          System.out.println("Funcionário localizado!");
          System.out.println("Nome: " + funcionario.getNome());
          System.out.println("Cargo: " + funcionario.getCargo());
          System.out.println("Cadastrando cliente...");
          funcionario.adicionarCliente(clientes);
          count ++;
          return;
        }
      }
      if (count == 0) {
        System.out.println("Funcionário não encontrado!");
      }
    }
  }
  public void cadastrarClienteGUI(){
    if (this.funcionarios.isEmpty()) {
      mensagens = new JPanel();
      mensagens.add(new JLabel("Não há funcionários cadastrados!"));
      JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
      return;
    } else {
      Panel = new JPanel();
      Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
      Panel.add(new JLabel("Digite a matrícula do funcionário que está cadastrando o cliente:"));
      JTextField matricula = new JTextField();
      Panel.add(matricula);
      int result = JOptionPane.showConfirmDialog(null, Panel, "Cadastrar Cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if (result == JOptionPane.OK_OPTION) {
        for (Funcionario funcionario : this.funcionarios) {
          if (funcionario.getMatricula().equals(matricula.getText())) {
            mensagens = new JPanel();
            mensagens.setLayout(new BoxLayout(mensagens, BoxLayout.Y_AXIS));
            mensagens.add(new JLabel("Funcionário localizado!"));
            mensagens.add(new JLabel("\nNome: " + funcionario.getNome()));
            mensagens.add(new JLabel("Cargo: " + funcionario.getCargo()));
            mensagens.add(new JLabel("Cadastrando cliente..."));
            JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
            funcionario.adicionarClienteGUI(clientes);
            return;
          }
        }
        mensagens = new JPanel();
        mensagens.add(new JLabel("Funcionário não encontrado!"));
        JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
        return;
      } else {
        /*
        mensagens = new JPanel();
        mensagens.add(new JLabel("Cancelado!"));
        JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
        */
        return;
      }
    }
  }

  public void adicionarFuncionario(Funcionario funcionario) {
    this.funcionarios.add(funcionario);
  }

  // interface gráfica para cadastrarFuncionario()
  public void cadastrarFuncionarioGUI() {
    Panel = new JPanel();
    Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
    Panel.add(new JLabel("Digite o nome do funcionário:"));
    JTextField nome = new JTextField();
    Panel.add(nome);
    Panel.add(new JLabel("Digite o CPF do funcionário:"));
    JTextField cpf = new JTextField();
    Panel.add(cpf);
    Panel.add(new JLabel("Digite o cargo do funcionário:"));
    JTextField cargo = new JTextField();
    Panel.add(cargo);
    int result = JOptionPane.showConfirmDialog(null, Panel, "Cadastrar Funcionário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      Funcionario funcionario = new Funcionario(nome.getText(), cpf.getText(), cargo.getText());
      this.funcionarios.add(funcionario);
      mensagens = new JPanel();
      mensagens.setLayout(new BoxLayout(mensagens, BoxLayout.Y_AXIS));
      mensagens.add(new JLabel("Funcionário cadastrado com sucesso!"));
      mensagens.add(new JLabel("\nMatrícula: " + funcionario.getMatricula()));
      JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);

    } else {
      /*
      mensagens = new JPanel();
      mensagens.add(new JLabel("Cancelado!"));
      JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
      */
    }
  }


  public void cadastrarCarro() {
    System.out.println("\nCadastrando carro...");
    System.out.println("Digite a marca do carro:");
    String marca = input.nextLine();
    System.out.println("Digite o modelo do carro:");
    String modelo = input.nextLine();
    System.out.println("Digite a cor do carro:");
    String cor = input.nextLine();
    System.out.println("Digite o ano do carro:");
    int ano = input.nextInt();
    input.nextLine();

    System.out.println("Digite o tipo de combustível do carro:");
    String combustivel = input.nextLine();
    System.out.println("Digite o preço do carro:");
    double preco = input.nextDouble();
    input.nextLine();

    System.out.println("Digite a quantidade de carros que deseja inserir no Estoque:");
    int quantidade = input.nextInt();
    input.nextLine();
    for (int i = 0; i < quantidade; i++) {
      String chassi = geradorDeChassi();
      System.out.println("Chassi gerado: " + chassi);
      Carro carro = new Carro(marca, modelo, cor, ano, chassi, combustivel, preco);
      this.carros.add(carro);
    }
    System.out.println("\nCarro(s) cadastrado(s) com sucesso!");
  }
  public void cadastrarCarroGUI(){
    Panel = new JPanel();
    Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
    Panel.add(new JLabel("Digite a marca do carro:"));
    JTextField marca = new JTextField();
    Panel.add(marca);
    Panel.add(new JLabel("Digite o modelo do carro:"));
    JTextField modelo = new JTextField();
    Panel.add(modelo);
    Panel.add(new JLabel("Digite a cor do carro:"));
    JTextField cor = new JTextField();
    Panel.add(cor);
    Panel.add(new JLabel("Digite o ano do carro:"));
    JTextField ano = new JTextField();
    Panel.add(ano);
    Panel.add(new JLabel("Digite o tipo de combustível do carro:"));
    JTextField combustivel = new JTextField();
    Panel.add(combustivel);
    Panel.add(new JLabel("Digite o preço do carro:"));
    JTextField preco = new JTextField();
    Panel.add(preco);
    Panel.add(new JLabel("Digite a quantidade de carros que deseja inserir no Estoque:"));
    JTextField quantidade = new JTextField();
    Panel.add(quantidade);
    int result = JOptionPane.showConfirmDialog(null, Panel, "Cadastrar Carro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      for (int i = 0; i < Integer.parseInt(quantidade.getText()); i++) {
        String chassi = geradorDeChassi();
        System.out.println("Chassi gerado: " + chassi);
        Carro carro = new Carro(marca.getText(), modelo.getText(), cor.getText(), Integer.parseInt(ano.getText()), chassi, combustivel.getText(), Double.parseDouble(preco.getText()));
        this.carros.add(carro);
      }

      JOptionPane.showMessageDialog(null, "Carro(s) cadastrado(s) com sucesso!");
    }
  }

  public String geradorDeChassi() {
    String chassi = "";
    String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String numeros = "0123456789";
    Random random = new Random();
    for (int i = 0; i < 17; i++) {
      if (i < 3) {
        chassi += letras.charAt(random.nextInt(letras.length()));
      } else {
        chassi += numeros.charAt(random.nextInt(numeros.length()));
      }
    }
    return chassi;
  }

  public void _listarClientesVIPGUI(){
    if (this.clientesVIP.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há clientes VIP cadastrados!");
      return;
    } else {
      Panel = new JPanel();
      Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
      Panel.add(new JLabel("Listando clientes VIP..."));
      for (Map.Entry<String, Cliente> entry : this.clientesVIP.entrySet()) {
        Panel.add(new JLabel("\nNome: " + entry.getValue().getNome()));
        Panel.add(new JLabel("CPF: " + entry.getValue().getCpf()));
        Panel.add(new JLabel("Cadastro: " + entry.getValue().getCadastro()));
        Panel.add(new JLabel("Carros comprados: "));
        Panel.add(new JLabel(listarCarrosClienteGUI(entry.getValue())));
        Panel.add(new JLabel(" "));
      }
      JOptionPane.showMessageDialog(null, Panel, "Listar Clientes VIP", JOptionPane.PLAIN_MESSAGE);
    }
  }
  public void listarClientesVIPGUI(){
    if (this.clientesVIP.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há clientes VIP cadastrados!");
      return;
    } else {
      Panel = new JPanel();
      Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
      Panel.add(new JLabel("Listando clientes VIP..."));
      JTextArea textArea = new JTextArea(20, 30);
      textArea.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(textArea);
      for (Map.Entry<String, Cliente> entry : this.clientesVIP.entrySet()) {
        textArea.append("Nome: " + entry.getValue().getNome() + "\n");
        textArea.append("CPF: " + entry.getValue().getCpf() + "\n");
        textArea.append("Cadastro: " + entry.getValue().getCadastro() + "\n");
        //textArea.append("\nCarros comprados: ");
        textArea.append(listarCarrosClienteGUI(entry.getValue()) + "\n");
        textArea.append(" ");
      }
      Panel.add(scrollPane);
      JOptionPane.showMessageDialog(null, Panel, "Listar Clientes VIP", JOptionPane.PLAIN_MESSAGE);
    }
  }

  // consultar cliente vip
  public void verificarClienteVIP(String cpf) {
    if(this.clientesVIP.containsKey(cpf)){
      System.out.println("Cliente VIP!!! *****");
    } else {
      System.out.println("Cliente não VIP!");
    }
  }
  public String verificarClienteVIPGUI(String cpf) {
    if(this.clientesVIP.containsKey(cpf)){
      return "Cliente VIP!!! *****";
    } else {
      return "Cliente não VIP!";
    }
  }

  // usar na classe estoque.
  public void listarCarros() {
    if (this.carros.isEmpty()) {
      System.out.println("\nNão há carros cadastrados!");
      return;
    } else {
      System.out.println("\nListando carros...");
      for (Carro carro : this.carros) {
        System.out.println("\nMarca: " + carro.getMarca());
        System.out.println("Modelo: " + carro.getModelo());
        System.out.println("Cor: " + carro.getCor());
        System.out.println("Ano: " + carro.getAno());
        System.out.println("Chassi: " + carro.getChassi());
        System.out.println("Combustível: " + carro.getCombustivel());
        System.out.println("Preço: " + carro.getPreco());
        //System.out.println("");
      }
      System.out.println("\nTotal de carros em Estoque: " + this.carros.size());
    }

  }

  public void zerarEstoque() {

    this.carros.clear();
    System.out.println("\nEstoque zerado!");
  }

  public void zerarEstoqueGUI() {
    if (this.carros.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há carros cadastrados!");
      return;
    }
    //pedir confirmação antes de zerar o estoque
    Panel = new JPanel();
    Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
    Panel.add(new JLabel("Tem certeza que deseja zerar o estoque?"));
    int result = JOptionPane.showConfirmDialog(null, Panel, "Zerar Estoque", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      this.carros.clear();
      JOptionPane.showMessageDialog(null, "Estoque zerado!");
    }

  }

  public void listarCarrosGUI(){
    if (this.carros.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há carros cadastrados!");
      return;
    } else {
      Panel = new JPanel();
      Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
      Panel.add(new JLabel("Listando carros..."));
      JTextArea textArea = new JTextArea(30,30);
      textArea.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(textArea);
      for (Carro carro : this.carros) {
        textArea.append("Marca: " + carro.getMarca() + "\n");
        textArea.append("Modelo: " + carro.getModelo() + "\n");
        textArea.append("Cor: " + carro.getCor() + "\n");
        textArea.append("Ano: " + carro.getAno() + "\n");
        textArea.append("Chassi: " + carro.getChassi() + "\n");
        textArea.append("Combustível: " + carro.getCombustivel() + "\n");
        textArea.append("Preço: " + carro.getPreco() + "\n");
        textArea.append(" ");
      }
      textArea.append("\nTotal de carros em Estoque: " + this.carros.size());
      textArea.setCaretPosition(0);// seta o scroll pro topo
      Panel.add(scrollPane);
      JOptionPane.showMessageDialog(null, Panel, "Listar Carros", JOptionPane.PLAIN_MESSAGE);
    }
  }

  public void listarClientesGUI(){
    if (this.clientes.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há clientes cadastrados!");
      return;
    } else {
      Panel = new JPanel();
      Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
      Panel.add(new JLabel("Listando clientes..."));
      JTextArea textArea = new JTextArea(30, 30);
      textArea.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(textArea);
      for (Cliente cliente : this.clientes) {
        textArea.append("Nome: " + cliente.getNome() + "\n");
        textArea.append("CPF: " + cliente.getCpf() + "\n");
        textArea.append("Cadastro: " + cliente.getCadastro() + "\n");
        textArea.append(verificarClienteVIPGUI(cliente.getCpf()) + "\n");
        textArea.append(listarCarrosClienteGUI(cliente) + "\n");
        textArea.append(" ");
      }
      textArea.setCaretPosition(0);// seta o scroll pro topo
      Panel.add(scrollPane);
      JOptionPane.showMessageDialog(null, Panel, "Listar Clientes", JOptionPane.PLAIN_MESSAGE);
    }
  }

  public void listarFuncionariosGUI(){
    if (this.funcionarios.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há funcionários cadastrados!");
      return;
    } else {
      Panel = new JPanel();
      Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
      Panel.add(new JLabel("Listando funcionários..."));
      JTextArea textArea = new JTextArea(30, 30);
      textArea.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(textArea);
      for (Funcionario funcionario : this.funcionarios) {
        textArea.append("Nome: " + funcionario.getNome() + "\n");
        textArea.append("Matrícula: " + funcionario.getMatricula() + "\n");
        textArea.append("CPF: " + funcionario.getCpf() + "\n");
        textArea.append("Cargo: " + funcionario.getCargo() + "\n");
        textArea.append(" ");
      }
      textArea.setCaretPosition(0);// seta o scroll pro topo
      Panel.add(scrollPane);
      JOptionPane.showMessageDialog(null, Panel, "Listar Funcionários", JOptionPane.PLAIN_MESSAGE);
    }
  }

  public void consultarClienteGUI(){
    if (this.clientes.isEmpty()) {
      mensagens = new JPanel();
      mensagens.add(new JLabel("Não há clientes cadastrados!"));
      JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
      return;
    } else {
      Panel = new JPanel();
      Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
      Panel.add(new JLabel("Digite o CPF do cliente que deseja encontrar:"));
      JTextField cpfCliente = new JTextField();
      Panel.add(cpfCliente);
      int result = JOptionPane.showConfirmDialog(null, Panel, "Consultar Cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if (result == JOptionPane.OK_OPTION) {
        if(verificarCliente(cpfCliente.getText())){
          for (Cliente cliente : this.clientes) {
            if (cliente.getCpf().equals(cpfCliente.getText())) {
              mensagens = new JPanel();
              mensagens.setLayout(new BoxLayout(mensagens, BoxLayout.Y_AXIS));
              mensagens.add(new JLabel("Cliente localizado!"));
              mensagens.add(new JLabel("\nNome: " + cliente.getNome()));
              mensagens.add(new JLabel("Cadastro: " + cliente.getCadastro()));
              mensagens.add(new JLabel("CPF: " + cliente.getCpf()));
              JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
              verificarClienteVIP(cliente.getCpf());
              listarCarrosCliente(cliente);
              return;
            }
          }
        } else {
          mensagens = new JPanel();
          mensagens.add(new JLabel("Cliente não encontrado!"));
          JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
          return;
        }
      } else {
        /*
        mensagens = new JPanel();
        mensagens.add(new JLabel("Cancelado!"));
        JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
        */
        return;
      }
    }
  }


  public void listarCarrosCliente(Cliente cliente){
    if (cliente.getCarrosComprados().isEmpty()) {
      System.out.println("\nEste cliente não possui carros comprados!");
      //System.out.println("");
    } else {
      System.out.println("\nEste cliente possui os seguintes carros comprados:");
      for (Carro carro : cliente.getCarrosComprados()) {
        System.out.println("\nMarca: " + carro.getMarca());
        System.out.println("Modelo: " + carro.getModelo());
        System.out.println("Cor: " + carro.getCor());
        System.out.println("Ano: " + carro.getAno());
        System.out.println("Chassi: " + carro.getChassi());
        System.out.println("Combustível: " + carro.getCombustivel());
        System.out.println("Preço: " + carro.getPreco());
        //System.out.println("");
      }
    }
  }

  public String listarCarrosClienteGUI(Cliente cliente){
    if (cliente.getCarrosComprados().isEmpty()) {
      return "Este cliente não possui carros comprados!\n";
      //System.out.println("");
    } else {
      int count = 1;
      String carros = "Este cliente possui os seguintes carros comprados:";
      for (Carro carro : cliente.getCarrosComprados()) {
        carros += "\n   " + count + ".";
        carros += "\n   Marca: " + carro.getMarca();
        carros += "\n   Modelo: " + carro.getModelo();
        carros += "\n   Cor: " + carro.getCor();
        carros += "\n   Ano: " + carro.getAno();
        carros += "\n   Chassi: " + carro.getChassi();
        carros += "\n   Combustível: " + carro.getCombustivel();
        carros += "\n   Preço: " + carro.getPreco();
        carros += "\n ";
        count++;
        //System.out.println("");
      }
      return carros;
    }
  }

  public void consultarFuncionario() {
    System.out.println("\nConsultando funcionário...");
    if (this.funcionarios.isEmpty()) {
      System.out.println("\nNão há funcionários cadastrados!");
      return;
    } else {
      System.out.println("\nDigite a matrícula do Funcionário que deseja encontrar.");
      String matriculaConsultar = input.nextLine();
      System.out.println("\nListando funcionários...");
      for (Funcionario funcionario : this.funcionarios) {
        if (funcionario.getMatricula().equals(matriculaConsultar)) {
          System.out.println("\nFuncionário localizado!");
          System.out.println("\nNome: " + funcionario.getNome());
          System.out.println("Matrícula: " + funcionario.getMatricula());
          System.out.println("CPF: " + funcionario.getCpf());
          System.out.println("Cargo: " + funcionario.getCargo());
          //System.out.println("");
          return;
        }
      }
      System.out.println("\nFuncionário não encontrado!");
    }
  }
  public void consultarFuncionarioGUI(){
    if (this.funcionarios.isEmpty()) {
      mensagens = new JPanel();
      mensagens.add(new JLabel("Não há funcionários cadastrados!"));
      JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
      return;
    } else {
      Panel = new JPanel();
      Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
      Panel.add(new JLabel("Digite a matrícula do Funcionário que deseja encontrar:"));
      JTextField matriculaConsultar = new JTextField();
      Panel.add(matriculaConsultar);
      int result = JOptionPane.showConfirmDialog(null, Panel, "Consultar Funcionário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if (result == JOptionPane.OK_OPTION) {
        for (Funcionario funcionario : this.funcionarios) {
          if (funcionario.getMatricula().equals(matriculaConsultar.getText())) {
            mensagens = new JPanel();
            mensagens.setLayout(new BoxLayout(mensagens, BoxLayout.Y_AXIS));
            mensagens.add(new JLabel("Funcionário localizado!"));
            mensagens.add(new JLabel("\nNome: " + funcionario.getNome()));
            mensagens.add(new JLabel("Matrícula: " + funcionario.getMatricula()));
            mensagens.add(new JLabel("CPF: " + funcionario.getCpf()));
            mensagens.add(new JLabel("Cargo: " + funcionario.getCargo()));
            JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
            return;
          }
        }
        mensagens = new JPanel();
        mensagens.add(new JLabel("Funcionário não encontrado!"));
        JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
        return;
      } else {
        /*
        mensagens = new JPanel();
        mensagens.add(new JLabel("Cancelado!"));
        JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
        */
        return;
      }
    }
  }

  public void consultarCarro() {
    int count = 0;
    System.out.println("\nConsultando carro...");
    if (this.carros.isEmpty()) {
      System.out.println("\nNão há carros cadastrados!");
      return;
    } else {
      System.out.println("\nDigite o modelo do carro que deseja encontrar.");
      String modeloConsultar = input.nextLine();
      System.out.println("\nListando carros...");
      for (Carro carro : this.carros) {
        if (carro.getModelo().equals(modeloConsultar)) {
          System.out.println("\nCarro localizado!");
          System.out.println("Marca: " + carro.getMarca());
          System.out.println("Modelo: " + carro.getModelo());
          System.out.println("Cor: " + carro.getCor());
          System.out.println("Ano: " + carro.getAno());
          System.out.println("Chassi: " + carro.getChassi());
          System.out.println("Combustível: " + carro.getCombustivel());
          System.out.println("Preço: " + carro.getPreco());
          //System.out.println("");
          count++;
        }
      }
      if (count > 0) {
        System.out.println("\nTotal de carros deste Modelo encontrados: " + count);
      } else {
        System.out.println("\nModelo não encontrado!");
      }
      //System.out.println("Carro não encontrado!");
    }
  }
  public void consultarCarroGUI(){
    int count = 0;
    if (this.carros.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Não há carros cadastrados!");
      return;
    } else {
      Panel = new JPanel();
      Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
      Panel.add(new JLabel("Digite o modelo do carro que deseja encontrar:"));
      JTextField modeloConsultar = new JTextField();
      Panel.add(modeloConsultar);
      int result = JOptionPane.showConfirmDialog(null, Panel, "Consultar Carro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if (result == JOptionPane.OK_OPTION) {
        for (Carro carro : this.carros) {
          if (carro.getModelo().equals(modeloConsultar.getText())) {
            mensagens = new JPanel();
            mensagens.setLayout(new BoxLayout(mensagens, BoxLayout.Y_AXIS));
            mensagens.add(new JLabel("Carro localizado!"));
            mensagens.add(new JLabel("\nMarca: " + carro.getMarca()));
            mensagens.add(new JLabel("Modelo: " + carro.getModelo()));
            mensagens.add(new JLabel("Cor: " + carro.getCor()));
            mensagens.add(new JLabel("Ano: " + carro.getAno()));
            //mensagens.add(new JLabel("Chassi: " + carro.getChassi()));
            mensagens.add(new JLabel("Combustível: " + carro.getCombustivel()));
            mensagens.add(new JLabel("Preço: " + carro.getPreco()));
            count++;
            mensagens.add(new JLabel("Total de carros deste Modelo encontrados: " + count));
          }
        }

        if (count > 0) {

          JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);

        } else {

          JOptionPane.showMessageDialog(null, "Modelo não encontrado!");
        }
      } else {
        /*
        mensagens = new JPanel();
        mensagens.add(new JLabel("Cancelado!"));
        JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
        */
        return;
      }
    }
  }

  public void adicionarClienteVIP(Cliente cliente) {
    this.clientesVIP.put(cliente.getCpf(), cliente);
  }

  public void removerClienteVIP(String cpf) {
    this.clientesVIP.remove(cpf);
  }

  // função para salvar objeto loja em arquivo json
  public void salvarLoja(Loja loja) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    FileWriter writer = new FileWriter("loja.json");
    gson.toJson(loja, writer);
    writer.close();
  }

  // função para carregar objeto loja de arquivo json
  public Loja carregarLoja() throws IOException {
    Gson gson = new Gson();
    BufferedReader br = new BufferedReader(new FileReader("loja.json"));
    Loja loja = gson.fromJson(br, Loja.class);
    return loja;
  }

  public void novaLojaGUI(){
    Panel = new JPanel();
    Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
    Panel.add(new JLabel("Tem certeza que deseja criar uma nova loja?"));
    int result = JOptionPane.showConfirmDialog(null, Panel, "Nova Loja", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      mensagens = new JPanel();
      mensagens.setLayout(new BoxLayout(mensagens, BoxLayout.Y_AXIS));
      mensagens.add(new JLabel("Nome da loja:"));
      JTextField nome = new JTextField();
      mensagens.add(nome);
      mensagens.add(new JLabel("Saldo inicial:"));
      JTextField saldo = new JTextField();
      mensagens.add(saldo);
      int result2 = JOptionPane.showConfirmDialog(null, mensagens, "Nova Loja", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if (result2 == JOptionPane.OK_OPTION) {
        this.nome = nome.getText();
        this.caixa = Double.parseDouble(saldo.getText());
        this.carros.clear();
        this.clientes.clear();
        this.clientesVIP.clear();
        this.funcionarios.clear();
      }
    }
  }
}
