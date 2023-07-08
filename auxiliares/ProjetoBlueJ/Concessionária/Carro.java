import java.util.*;

public class Carro {
  //atributos fixos de carro
  private String marca;
  private String modelo;
  private String cor;
  private int ano;
  private String chassi;
  private String combustivel;
  private double preco;
  Scanner input = new Scanner(System.in);

  public Carro() {
  }

  public Carro(String marca, String modelo, String cor, int ano, String chassi, String combustivel, double preco) {
    this.marca = marca;
    this.modelo = modelo;
    this.cor = cor;
    this.ano = ano;
    this.chassi = chassi;
    this.combustivel = combustivel;
    this.preco = preco;
  }

  //getters
  public String getMarca(){ return marca; }
  public String getModelo(){ return modelo; }
  public String getCor(){ return cor; }
  public int getAno(){ return ano; }
  public String getChassi(){ return chassi; }
  public String getCombustivel(){ return combustivel; }
  public Double getPreco(){ return preco; }

  //setters
  public void setMarca(String marca){ this.marca = marca; }
  public void setModelo(String modelo){ this.modelo = modelo; }
  public void setCor(String cor){ this.cor = cor; }
  public void setAno(int ano){ this.ano = ano; }
  public void setChassi(String chassi){ this.chassi = chassi; }
  public void setCombustivel(String combustivel){ this.combustivel = combustivel; }

  //métodos
  /*
  public Carro cadastrarCarro(){
    System.out.println("Cadastrando carro...");
    System.out.println("Digite a marca do carro:");
    String marca = input.nextLine();
    System.out.println("Digite o modelo do carro:");
    String modelo = input.nextLine();
    System.out.println("Digite a cor do carro:");
    String cor = input.nextLine();
    System.out.println("Digite o ano do carro:");
    int ano = input.nextInt();
    input.nextLine();
    //gerar chassi chassi aleatório de 17 numeros ou letras, todos uppercase
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
    System.out.println("Chassi gerado: " + chassi);
    System.out.println("Digite o tipo de combustível do carro:");
    String combustivel = input.nextLine();
    System.out.println("Digite o preço do carro:");
    double preco = input.nextDouble();
    Carro carro = new Carro(marca, modelo, cor, ano, chassi, combustivel, preco);
    return carro;
  }
  */
  public void alterarCarro(){
    System.out.println("Alterando carro...");
  }
  public void excluirCarro(){
    System.out.println("Excluindo carro...");
  }
  public void consultarCarro(){
    System.out.println("Consultando carro...");
  }
  public void listarCarros(){
    System.out.println("Listando carros...");
  }

}
