import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parque parque = new Parque(); 
        int opcao;

        //Instanciados para inicializar com alguns dados prontos.
        VisitanteAdulto adulto1 = new VisitanteAdulto("Gertrudes", 1980, "9090-9090");
        VisitanteAdulto adulto2 = new VisitanteAdulto("Robson", 1995, "4321-8765");
        VisitanteCrianca crianca1 = new VisitanteCrianca("Roberval", 2017, "Madalena", "2222-3333");
        VisitanteCrianca crianca2 = new VisitanteCrianca("Edson", 2015, "Murila", "1234-5678");

        parque.adicionarVisitante(adulto1);
        parque.adicionarVisitante(adulto2);
        parque.adicionarVisitante(crianca1);
        parque.adicionarVisitante(crianca2);


        do {
            System.out.println("\n### Menu do Parque ###");
            System.out.println("1. Cadastrar novo visitante");
            System.out.println("2. Listar visitantes cadastrados");
            System.out.println("3. Emitir novo ingresso");
            System.out.println("4. Registrar visita à atração");
            System.out.println("5. Localizar visitante");
            System.out.println("6. Consultar faturamento de um mês/ano");
            System.out.println("7. Consultar atrações mais visitadas em uma data");
            System.out.println("8. Atualizar Cadastro");
            System.out.println("9. Excluir Cadastro");
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
