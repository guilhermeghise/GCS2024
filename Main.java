import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parque parque = new Parque(); // Assumindo que você tem uma classe Parque que implementará as funcionalidades

        int opcao;
        do {
            System.out.println("\n### Menu do Parque ###");
            System.out.println("1. Cadastrar novo visitante");
            System.out.println("2. Listar visitantes cadastrados");
            System.out.println("3. Emitir novo ingresso");
            System.out.println("4. Registrar visita à atração");
            System.out.println("5. Localizar visitante");
            System.out.println("6. Consultar faturamento de um mês/ano");
            System.out.println("7. Consultar atrações mais visitadas em uma data");
            System.out.println("8. Atualizar cadastro");
            System.out.println("9. Excluir cadastro");
            System.out.println("0. Sair");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    parque.cadastrarNovoVisitante();
                    break;
                case 2:
                    parque.listarVisitantesCadastrados();
                    break;
                case 3:
                    parque.emitirNovoIngresso();
                    break;
                case 4:
                    parque.registrarVisitaAtracao();
                    break;
                case 5:
                    parque.localizarVisitante();
                    break;
                case 6:
                    parque.consultarFaturamento();
                    break;
                case 7:
                    parque.consultarAtracoesMaisVisitadas();
                    break;
                case 8:
                    parque.atualizarCadastro();
                    break;
                case 9:
                    parque.excluirCadastro();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
