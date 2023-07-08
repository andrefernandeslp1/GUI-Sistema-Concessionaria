import java.util.*;

public class Cliente extends Pessoa {
  //informações exclusivas para cliente
  private String cadastro;
  private List<Carro> carrosComprados;

  public Cliente() {
  }

  public Cliente(String nome, String cpf) {
    super(nome, cpf);
    this.cadastro = gerarCadastro(nome, cpf);
    this.carrosComprados = new ArrayList<Carro>();
  }

  //getters
  public String getCadastro(){ return cadastro; }
  public List<Carro> getCarrosComprados(){ return carrosComprados; }

  //setters
  public void setCadastro(String nome, String cpf){ this.cadastro = gerarCadastro(nome, cpf); }
  public void addCarroComprado(Carro carro){ this.carrosComprados.add(carro); }

  //metodos
  public String gerarCadastro(String nome, String cpf) {
    String cadastro = nome.substring(0, 3) + cpf.substring(0, 3);
    return cadastro;
  }

}
