import java.util.*;

public class App {

  public static void main(String[] args) throws Exception {
    Scanner input = new Scanner(System.in);
    Loja loja = new Loja();

    //carregar objeto loja de arquivo json
    try {
      loja = loja.carregarLoja();
    } catch (Exception e) {
      System.out.println("Erro ao carregar arquivo loja.json!");
      System.out.println("Instanciando novo objeto loja...");
      System.out.println("Qual o nome da loja?");
      String nomeLoja = input.nextLine();
      System.out.println("Qual o Saldo de Caixa da loja?");
      double saldoCaixa = input.nextDouble();
      input.nextLine();
      loja = new Loja(nomeLoja, saldoCaixa);
    }

    int opcao1 = 0;

    System.out.println("\nBem vindo ao SYSCAR! Sistema de Informações para Concessionárias de Automóveis");
    System.out.println("Nome: " + loja.getNome());
    System.out.println("Saldo de Caixa: " + loja.getCaixa());

    do {
      opcao1 = 0;
      int opcao2 = 0;

      System.out.println("\nEscolha uma opção:");
      System.out.println("1 - Recursos Humanos");
      System.out.println("2 - Clientes");
      System.out.println("3 - Abastecimento de Estoque e Venda de Veículos");
      System.out.println("4 - Estoque");
      System.out.println("5 - Sair");

      try {
        opcao1 = input.nextInt();
        input.nextLine();
      } catch (InputMismatchException e) {
        opcao1 = 0;
        System.out.println("Opção inválida!");
        input.nextLine();
      }

      switch (opcao1) {
        case 1: // RECURSOS HUMANOS
          do {
            System.out.println("\n  Recursos Humanos");
            System.out.println("  Escolha uma opção:");
            System.out.println("  1 - Cadastrar Funcionário");
            System.out.println("  2 - Demitir Funcionário");
            System.out.println("  3 - Consultar Funcionário");
            System.out.println("  4 - Listar Funcionários");
            System.out.println("  5 - Voltar");

            try {
              opcao2 = input.nextInt();
              input.nextLine();
            } catch (InputMismatchException e) {
              opcao2 = 0;
              System.out.println("Opção inválida!");
              input.nextLine();
            }

            switch (opcao2) {
              case 1: // cadastrar funcionario
                System.out.println("Cadastrar Funcionário");
                loja.cadastrarFuncionario();
                break;

              case 2: // demitir funcionario
                System.out.println("Demitir Funcionário");
                loja.demitirFuncionario();
                break;

              case 3: // consultar funcionario
                System.out.println("Consultar Funcionário");
                loja.consultarFuncionario();
                break;

              case 4: // listar funcionarios
                System.out.println("Listar Funcionários");
                loja.listarFuncionarios();
                break;

              case 5: // sair
                System.out.println("Saindo...");
                break;

              default:
                System.out.println("Opção inválida!");
                break;
            }
          } while (opcao2 != 5);
          break;

        case 2: // CLIENTES
          do {
            System.out.println("\n  Clientes");
            System.out.println("  Escolha uma opção:");
            System.out.println("  1 - Cadastrar Cliente");
            System.out.println("  2 - Consultar Cliente");
            System.out.println("  3 - Listar Clientes");
            System.out.println("  4 - Listar Clientes VIP");
            System.out.println("  5 - Voltar");

            try {
              opcao2 = input.nextInt();
              input.nextLine();
            } catch (InputMismatchException e) {
              opcao2 = 0;
              System.out.println("Opção inválida!");
              input.nextLine();
            }

            switch (opcao2) {
              case 1: // cadastrar cliente
                System.out.println("Cadastrar Cliente");
                loja.cadastrarCliente();
                break;

              case 2: // consultar cliente
                System.out.println("Consultar Cliente");
                loja.consultarCliente();
                break;

              case 3: // listar clientes
                System.out.println("Listar Clientes");
                loja.listarClientes();
                break;

              case 4: // clientes VIP
                System.out.println("Listar Clientes VIP");
                loja.listarClientesVIP();
                break;

              case 5: // sair
                System.out.println("Saindo...");
                break;

              default:
                System.out.println("Opção inválida!");
                break;
            }
          } while (opcao2 != 5);
          break;

        case 3: // ABASTECIMENTO E VENDA

          do{
            System.out.println("\n  Abastecimento e Venda");
            System.out.println("  Escolha uma opção:");
            System.out.println("  1 - Abastecer Estoque (Fábrica)");
            System.out.println("  2 - Vender Carro");
            System.out.println("  ? - Devolver Carro (Fábrica) ***IMPLEMENTAR***");
            System.out.println("  3 - Voltar");

            try {
              opcao2 = input.nextInt();
              input.nextLine();
            } catch (InputMismatchException e) {
              opcao2 = 0;
              System.out.println("Opção inválida!");
              input.nextLine();
            }

            switch (opcao2) {
              case 1: // abastecer estoque
                System.out.println("Abastecer Estoque");
                loja.cadastrarCarro();
                break;

              case 2: // vender carro
                System.out.println("Vender Carro");
                loja.venderCarro();
                break;

              case 3: // sair
                System.out.println("Saindo...");
                break;

              default: // opcao invalida
                System.out.println("Opção inválida!");
                break;
            }
          }while (opcao2 != 3);
          break;
        case 4: // ESTOQUE

        do{
          System.out.println("\n  Estoque");
          System.out.println("  Escolha uma opção:");
          System.out.println("  1 - Consultar Carro");
          System.out.println("  2 - Listar Carros");
          System.out.println("  ? - Zerar Estoque ***IMPLEMENTAR***");
          System.out.println("  3 - Voltar");

          try {
            opcao2 = input.nextInt();
            input.nextLine();
          } catch (InputMismatchException e) {
            opcao2 = 0;
            System.out.println("Opção inválida!");
            input.nextLine();
          }

          switch (opcao2) {
            case 1: // consultar carro
              System.out.println("Consultar Carro");
              loja.consultarCarro();
              break;

            case 2: // listar carros
              System.out.println("Listar Carros");
              loja.listarCarros();
              break;

            case 3: // sair
              System.out.println("Saindo...");
              break;

            default: // opcao invalida
              System.out.println("Opção inválida!");
              break;
          }
        }while(opcao2 != 3);
          break;
        case 5: // SAIR
          System.out.println("Saindo...");
          break;
        default: // OPCAO INVALIDA
          System.out.println("Opção inválida!");
          break;
      }

    } while (opcao1 != 5);

    input.close();

    //salvar objeto loja em arquivo json
    loja.salvarLoja(loja);

    return;
  }
}
