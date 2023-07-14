import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Funcionario extends Pessoa {
  private String matricula;
  private String cargo;
  private transient Scanner input = new Scanner(System.in);
  private transient JPanel adicionarClientePanel;
  private transient JPanel mensagensPanel;

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

  public void adicionarClienteGUI(ArrayList<Cliente> clientes2){
    adicionarClientePanel = new JPanel();
    adicionarClientePanel.setLayout(new BoxLayout(adicionarClientePanel, BoxLayout.Y_AXIS));
    adicionarClientePanel.add(new JLabel("Digite o nome do cliente:"));
    JTextField nomeField = new JTextField();
    adicionarClientePanel.add(nomeField);
    adicionarClientePanel.add(new JLabel("Digite o CPF do cliente:"));
    JTextField cpfField = new JTextField();
    adicionarClientePanel.add(cpfField);
    int result = JOptionPane.showConfirmDialog(null, adicionarClientePanel, "Adicionar Cliente", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      Cliente cliente = new Cliente(nomeField.getText(), cpfField.getText());
      clientes2.add(cliente);
      JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!\nNúmero de cadastro: " + cliente.getCadastro());
    }
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
