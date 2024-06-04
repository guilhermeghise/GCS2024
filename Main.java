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
            System.out.println("8. Função adicional 1");
            System.out.println("9. Função adicional 2");
            System.out.println("0. Sair");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    // Método para cadastrar novo visitante
                    break;
                case 2:
                    // Método para listar visitantes cadastrados
                    break;
                case 3:
                    // Método para emitir novo ingresso
                    break;
                case 4:
                    // Método para registrar visita à atração
                    break;
                case 5:
                    // Método para localizar visitante
                    break;
                case 6:
                    // Método para consultar faturamento de um mês/ano
                    break;
                case 7:
                    // Método para consultar atrações mais visitadas em uma data
                    break;
                case 8:
                    // Implemente a primeira funcionalidade adicional
                    break;
                case 9:
                    // Implemente a segunda funcionalidade adicional
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
