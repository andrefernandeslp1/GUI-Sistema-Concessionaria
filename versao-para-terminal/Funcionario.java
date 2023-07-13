import java.util.*;

public class Funcionario extends Pessoa {
  private String matricula;
  private String cargo;
  private transient Scanner input = new Scanner(System.in);

  public Funcionario() {
  }

  public Funcionario(String nome, String cpf, String cargo) {
    super(nome, cpf);
    this.cargo = cargo;
    this.matricula = gerarMatricula(cpf, cargo);
  }

  public void adicionarCliente(ArrayList<Cliente> clientes2){
    System.out.println("Digite o nome do cliente:");
    String nome = input.nextLine();
    System.out.println("Digite o CPF do cliente:");
    String cpf = input.nextLine();
    Cliente cliente = new Cliente(nome, cpf);
    clientes2.add(cliente);
    System.out.println("Cliente cadastrado com sucesso!");
    System.out.println("Número de cadastro: " + cliente.getCadastro());
  }

  //getters
  public String getMatricula(){ return matricula; }
  public String getCargo(){ return cargo; }
  public String getNome(){ return super.getNome(); }
  public String getCpf(){ return super.getCpf(); }

  //setters
  public void setCargo(String cargo){
    this.cargo = cargo;
    this.matricula = gerarMatricula(super.getCpf(), cargo);
  }

  //metodos
  public String gerarMatricula(String cpf, String cargo) {
    String matricula = cpf.substring(0, 3) + cargo.substring(0, 3);
    return matricula;
  }
}
