import java.util.*;

public abstract class Pessoa {
  private String nome;
  private String cpf;

  //construtor vazio
  public Pessoa() {
  }

  //construtor com alguns atributos obrigatórios
  public Pessoa(String nome, String cpf) {
    this.nome = nome;
    this.cpf = cpf;
  }

  //getters
  public String getNome(){ return nome; }
  public String getCpf(){ return cpf; }

  //setters
  public void setNome(String nome){ this.nome = nome; }
  public void setCpf(String cpf){ this.cpf = cpf; }

  //métodos
  public void cadastrarPessoa(){
    System.out.println("Cadastrando pessoa...");
  }
  public void alterarPessoa(){
    System.out.println("Alterando pessoa...");
  }
  public void excluirPessoa(){
    System.out.println("Excluindo pessoa...");
  }
  public void consultarPessoa(){
    System.out.println("Consultando pessoa...");
  }
  public void listarPessoas(){
    System.out.println("Listando pessoas...");
  }

}
