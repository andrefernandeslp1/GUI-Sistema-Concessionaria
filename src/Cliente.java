import java.util.*;

public class Cliente extends Pessoa {
  //informações exclusivas para cliente
  private String cadastro;
  private ArrayList<Carro> carrosComprados;

  public Cliente() {
  }

  public Cliente(String nome, String cpf) {
    super(nome, cpf);
    this.cadastro = gerarNumeroDeCadastro(nome, cpf);
    this.carrosComprados = new ArrayList<Carro>();
  }

  //getters
  public String getCadastro(){ return cadastro; }
  public String getNome(){ return super.getNome(); }
  public String getCpf(){ return super.getCpf(); }
  public ArrayList<Carro> getCarrosComprados(){ return carrosComprados; }

  //setters
  public void setCadastro(String nome, String cpf){ this.cadastro = gerarNumeroDeCadastro(nome, cpf); }
  public void addCarroComprado(Carro carro){ this.carrosComprados.add(carro); }
  public void removeCarroComprado(Carro carro){ this.carrosComprados.remove(carro); }
  
  //metodos
  public String gerarNumeroDeCadastro(String nome, String cpf) {
    String cadastro = nome.substring(0, 3) + cpf.substring(0, 3);
    return cadastro;
  }

}
