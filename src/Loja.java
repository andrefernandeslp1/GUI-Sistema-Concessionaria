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

  private transient JPanel cadastrarFuncionarioPanel;
  private transient JPanel mensagens;
  private transient JPanel demitirFuncionarioPanel;
  private transient JPanel consultarFuncionarioPanel;


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

  public void demitirFuncionario() {
    if (this.funcionarios.isEmpty()) {
      System.out.println("\nNão há funcionários cadastrados!");
      return;
    } else {
      System.out.println("\nPor favor digite a matrícula do Funcionário:");
      String matriculaRemover = input.nextLine();
      if(verificarFuncionario(matriculaRemover)){
        System.out.println("\nFuncionário localizado!");
        System.out.println("Demitindo funcionário...");
        for (Funcionario funcionario : this.funcionarios) {
          if (funcionario.getMatricula().equals(matriculaRemover)) {
            this.funcionarios.remove(funcionario);
            System.out.println("Funcionário demitido com sucesso!");
            return;
          }
        }
      } else {
          System.out.println("\nFuncionário não encontrado!");
          return;
      }
    }
  }
  public void demitirFuncionarioGUI(){
    if (this.funcionarios.isEmpty()) {
      mensagens = new JPanel();
      mensagens.add(new JLabel("Não há funcionários cadastrados!"));
      JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
      return;
    } else {
      demitirFuncionarioPanel = new JPanel();
      demitirFuncionarioPanel.setLayout(new BoxLayout(demitirFuncionarioPanel, BoxLayout.Y_AXIS));
      demitirFuncionarioPanel.add(new JLabel("Por favor digite a matrícula do Funcionário:"));
      JTextField matriculaRemover = new JTextField();
      demitirFuncionarioPanel.add(matriculaRemover);
      int result = JOptionPane.showConfirmDialog(null, demitirFuncionarioPanel, "Demitir Funcionário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if (result == JOptionPane.OK_OPTION) {
        if(verificarFuncionario(matriculaRemover.getText())){
          System.out.println("\nFuncionário localizado!");
          System.out.println("Demitindo funcionário...");
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
        mensagens = new JPanel();
        mensagens.add(new JLabel("Cancelado!"));
        JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);
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

  public void adicionarFuncionario(Funcionario funcionario) {
    this.funcionarios.add(funcionario);
  }
  /*
  public void cadastrarFuncionario() {
    System.out.println("\nCadastrando funcionário...");
    System.out.println("Digite o nome do funcionário:");
    String nome = input.nextLine();
    System.out.println("Digite o CPF do funcionário:");
    String cpf = input.nextLine();
    System.out.println("Digite o cargo do funcionário:");
    String cargo = input.nextLine();
    Funcionario funcionario = new Funcionario(nome, cpf, cargo);
    this.funcionarios.add(funcionario);
    System.out.println("\nFuncionário cadastrado com sucesso!");
    System.out.println("Matrícula: " + funcionario.getMatricula());
  }
  */
  // interface gráfica para cadastrarFuncionario()
  public void cadastrarFuncionarioGUI() {
    cadastrarFuncionarioPanel = new JPanel();
    cadastrarFuncionarioPanel.setLayout(new BoxLayout(cadastrarFuncionarioPanel, BoxLayout.Y_AXIS));
    cadastrarFuncionarioPanel.add(new JLabel("Digite o nome do funcionário:"));
    JTextField nome = new JTextField();
    cadastrarFuncionarioPanel.add(nome);
    cadastrarFuncionarioPanel.add(new JLabel("Digite o CPF do funcionário:"));
    JTextField cpf = new JTextField();
    cadastrarFuncionarioPanel.add(cpf);
    cadastrarFuncionarioPanel.add(new JLabel("Digite o cargo do funcionário:"));
    JTextField cargo = new JTextField();
    cadastrarFuncionarioPanel.add(cargo);
    int result = JOptionPane.showConfirmDialog(null, cadastrarFuncionarioPanel, "Cadastrar Funcionário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      Funcionario funcionario = new Funcionario(nome.getText(), cpf.getText(), cargo.getText());
      this.funcionarios.add(funcionario);
      mensagens = new JPanel();
      mensagens.setLayout(new BoxLayout(mensagens, BoxLayout.Y_AXIS));
      mensagens.add(new JLabel("Funcionário cadastrado com sucesso!"));
      mensagens.add(new JLabel("\nMatrícula: " + funcionario.getMatricula()));
      JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);

    } else {
      mensagens = new JPanel();
      mensagens.add(new JLabel("Cancelado!"));
      JOptionPane.showMessageDialog(null, mensagens, "Mensagem", JOptionPane.PLAIN_MESSAGE);

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
    // input.nextLine();
    System.out.println("Digite o tipo de combustível do carro:");
    String combustivel = input.nextLine();
    System.out.println("Digite o preço do carro:");
    double preco = input.nextDouble();
    input.nextLine();
    // gerar chassi chassi aleatório de 17 numeros ou letras, todos uppercase
    // String chassi = geradorDeChassi();
    // System.out.println("Chassi gerado: " + chassi);
    // Carro carro = new Carro(marca, modelo, cor, ano, chassi, combustivel, preco);
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

  // listar clientes vip
  public void listarClientesVIP() {
    if (this.clientesVIP.isEmpty()) {
      System.out.println("\nNão há clientes VIP cadastrados!");
      return;
    } else {
      System.out.println("\nListando clientes VIP...");
      for (Map.Entry<String, Cliente> entry : this.clientesVIP.entrySet()) {
        System.out.println("\nNome: " + entry.getValue().getNome());
        System.out.println("CPF: " + entry.getValue().getCpf());
        System.out.println("Cadastro: " + entry.getValue().getCadastro());
        System.out.println("Carros comprados: ");
        listarCarrosCliente(entry.getValue());
      }
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

  public void listarClientes() {
    if (this.clientes.isEmpty()) {
      System.out.println("\nNão há clientes cadastrados!");
      return;
    } else {
      System.out.println("\nListando clientes...");
      for (Cliente cliente : this.clientes) {
        System.out.println("\nNome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Cadastro: " + cliente.getCadastro());
        System.out.println("Carros comprados: ");
        verificarClienteVIP(cliente.getCpf());
        // tem que por uma verificação pra conferir se o cliente possui carro ou não
        listarCarrosCliente(cliente);
      }
    }
  }

  public void listarFuncionarios() {
    if (this.funcionarios.isEmpty()) {
      System.out.println("\nNão há funcionários cadastrados!");
      return;
    } else {
      System.out.println("\nListando funcionários...");
      for (Funcionario funcionario : this.funcionarios) {
        System.out.println("\nNome: " + funcionario.getNome());
        System.out.println("Matrícula: " + funcionario.getMatricula());
        System.out.println("CPF: " + funcionario.getCpf());
        System.out.println("Cargo: " + funcionario.getCargo());
        //System.out.println("");
      }
    }
  }

  public void consultarCliente() {
    System.out.println("\nConsultando cliente...");
    if (this.clientes.isEmpty()) {
      System.out.println("\nNão há clientes cadastrados!");
      return;
    } else {
      System.out.println("\nDigite o CPF do cliente que deseja encontrar.");
      String cpfCliente = input.nextLine();
      if(verificarCliente(cpfCliente)){
        //System.out.println("Cliente localizado!");
        for (Cliente cliente : this.clientes) {
          if (cliente.getCpf().equals(cpfCliente)) {
            System.out.println("\nCliente localizado!");
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Cadastro: " + cliente.getCadastro());
            System.out.println("CPF: " + cliente.getCpf());
            //System.out.println("");
            verificarClienteVIP(cliente.getCpf());
            listarCarrosCliente(cliente);
            return;
          }
        }
      } else {
        System.out.println("\nCliente não encontrado!");
        return;
      }
      //System.out.println("Listando clientes...");

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

  // função para objeto salvar loja em arquivo json
  /*
  public void salvarLoja(Loja loja) {
    Gson gson = new Gson();
    String json = gson.toJson(loja);
    try {
      FileWriter writer = new FileWriter("loja.json");
      writer.write(json);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  */

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
}
