import java.util.*;

public class Loja {
  private String nome;
  private Double caixa;
  private List<Carro> carros;
  private List<Cliente> clientes;
  private List<Funcionario> funcionarios;
  Scanner input = new Scanner(System.in);

  public Loja() {
  }

  public Loja(String nome, Double caixa) {
    this.nome = nome;
    this.caixa = caixa;
    this.carros = new ArrayList<Carro>();
    this.clientes = new ArrayList<Cliente>();
    this.funcionarios = new ArrayList<Funcionario>();
  }

//setters
  public void abastecerEstoque(Carro carro, int quantidade){
    for(int i = 0; i < quantidade; i++){
      this.carros.add(carro);
      //this.caixa -= carro.getPreco();
    }
  }
  public void demitirFuncionario(){
    if(this.funcionarios.isEmpty()){
      System.out.println("Não há funcionários cadastrados!");
      return;
    } else {
      System.out.println("Por favor digite a matrícula do Funcionário:");
      String matriculaRemover = input.nextLine();
      System.out.println("Demitindo funcionário...");
      for(Funcionario funcionario : this.funcionarios){
        if(funcionario.getMatricula().equals(matriculaRemover)){
          this.funcionarios.remove(funcionario);
          System.out.println("Funcionário demitido com sucesso!");
          return;
        } else {
          System.out.println("Funcionário não encontrado!");
        }
      }
    }

  }

  //GETTERS
  public String getNome() { return nome; }
  public Double getCaixa() { return caixa; }
  public List<Carro> getCarros(){ return this.carros; }
  public List<Cliente> getClientes(){ return this.clientes; }
  public List<Funcionario> getFuncionarios(){ return this.funcionarios; }

  public void venderCarro(){
    System.out.println("Vendendo carro...");
    System.out.println("Digite o CPF do cliente:");
    String cpf = input.nextLine();
    System.out.println("Digite o chassi do carro:");
    String chassi = input.nextLine();
    for(Cliente cliente : this.clientes){
      if(cliente.getCpf().equals(cpf)){
        for(Carro carro : this.carros){
          if(carro.getChassi().equals(chassi)){
            cliente.addCarroComprado(carro);
            this.carros.remove(carro);
            this.caixa += carro.getPreco();
            System.out.println("Carro vendido com sucesso!");
            return;
          } else {
            System.out.println("Carro não encontrado!");
          }
        }
      } else {
        System.out.println("Cliente não encontrado!");
      }
    }
  }

  public void cadastrarCliente(){
    System.out.println("Cadastrando cliente...");
    System.out.println("Digite o nome do cliente:");
    String nome = input.nextLine();
    System.out.println("Digite o CPF do cliente:");
    String cpf = input.nextLine();
    Cliente cliente = new Cliente(nome, cpf);
    this.clientes.add(cliente);
    System.out.println("Cliente cadastrado com sucesso!");
    System.out.println("Número: " + cliente.getCadastro());
  }

  public void cadastrarFuncionario(){
    System.out.println("Cadastrando funcionário...");
    System.out.println("Digite o nome do funcionário:");
    String nome = input.nextLine();
    System.out.println("Digite o CPF do funcionário:");
    String cpf = input.nextLine();
    System.out.println("Digite o cargo do funcionário:");
    String cargo = input.nextLine();
    Funcionario funcionario = new Funcionario(nome, cpf, cargo);
    this.funcionarios.add(funcionario);
    System.out.println("Funcionário cadastrado com sucesso!");
    System.out.println("Matrícula: " + funcionario.getMatricula());
  }

  public void cadastrarCarro(){
    System.out.println("Cadastrando carro...");
    System.out.println("Digite a marca do carro:");
    String marca = input.nextLine();
    System.out.println("Digite o modelo do carro:");
    String modelo = input.nextLine();
    System.out.println("Digite a cor do carro:");
    String cor = input.nextLine();
    System.out.println("Digite o ano do carro:");
    int ano = input.nextInt();
    //input.nextLine();
    System.out.println("Digite o tipo de combustível do carro:");
    String combustivel = input.nextLine();
    System.out.println("Digite o preço do carro:");
    double preco = input.nextDouble();
    //gerar chassi chassi aleatório de 17 numeros ou letras, todos uppercase
    String chassi = geradorDeChassi();
    System.out.println("Chassi gerado: " + chassi);
    Carro carro = new Carro(marca, modelo, cor, ano, chassi, combustivel, preco);
    System.out.println("Digite a quantidade de carros que deseja inserir no Estoque:");
    int quantidade = input.nextInt();
    for(int i = 0; i < quantidade; i++){
      this.carros.add(carro);
    }
  }

  public String geradorDeChassi(){
    String chassi = "";
    Random random = new Random();
    for(int i = 0; i < 17; i++){
      int randomInt = random.nextInt(36);
      if(randomInt < 10){
        chassi += randomInt;
      } else {
        chassi += (char)(randomInt + 55);
      }
    }
    return chassi;
  }

  public void listarCarros(){
    for(Carro carro : this.carros){
      System.out.println("Marca: " + carro.getMarca());
      System.out.println("Modelo: " + carro.getModelo());
      System.out.println("Cor: " + carro.getCor());
      System.out.println("Ano: " + carro.getAno());
      System.out.println("Chassi: " + carro.getChassi());
      System.out.println("Combustível: " + carro.getCombustivel());
      System.out.println("Preço: " + carro.getPreco());
      System.out.println("\n");
    }
  }

  public void listarClientes(){
    for(Cliente cliente : this.clientes){
      System.out.println("Nome: " + cliente.getNome());
      System.out.println("CPF: " + cliente.getCpf());
      System.out.println("Carros comprados: ");
      for(Carro carro : cliente.getCarrosComprados()){
        System.out.println("Marca: " + carro.getMarca());
        System.out.println("Modelo: " + carro.getModelo());
        System.out.println("Cor: " + carro.getCor());
        System.out.println("Ano: " + carro.getAno());
        System.out.println("Chassi: " + carro.getChassi());
        System.out.println("Combustível: " + carro.getCombustivel());
        System.out.println("Preço: " + carro.getPreco());
        System.out.println("\n");
      }
    }
  }

  public void listarFuncionarios(){
    if(this.funcionarios.isEmpty()){
      System.out.println("Não há funcionários cadastrados!");
      return;
    } else {
      System.out.println("Listando funcionários...");
      for(Funcionario funcionario : this.funcionarios){
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("Matrícula: " + funcionario.getMatricula());
        System.out.println("CPF: " + funcionario.getCpf());
        System.out.println("Cargo: " + funcionario.getCargo());
        System.out.println("\n");
      }
    }
  }

  public void consultarFuncionario() {
    System.out.println("Consultando funcionário...");
    if(this.funcionarios.isEmpty()){
      System.out.println("Não há funcionários cadastrados!");
      return;
    } else {
      System.out.println("Digite a matrícula do Funcionário que deseja encontrar.");
      String matriculaConsultar = input.nextLine();
      System.out.println("Listando funcionários...");
      for(Funcionario funcionario : this.funcionarios){
        if(funcionario.getMatricula().equals(matriculaConsultar)){
          System.out.println("Funcionário localizado!");
          System.out.println("Nome: " + funcionario.getNome());
          System.out.println("Matrícula: " + funcionario.getMatricula());
          System.out.println("CPF: " + funcionario.getCpf());
          System.out.println("Cargo: " + funcionario.getCargo());
          System.out.println("\n");
          return;
        } else {
          System.out.println("Funcionário não encontrado!");
        }
      }
    }
  }
}
