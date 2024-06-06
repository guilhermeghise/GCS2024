package app;

import dados.*;

import java.util.Scanner;

/**
 * Classe principal que contém o método main para executar o aplicativo do
 * Parque.
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parque parque = new Parque(); // Instância da classe Parque para gerenciar os visitantes e atrações do parque.
        int opcao; // Variável para armazenar a opção selecionada pelo usuário no menu.

        // Instanciados para inicializar com alguns dados prontos.
        VisitanteAdulto adulto1 = new VisitanteAdulto("Renato Gaucho", 1980, "9090-9090");
        VisitanteAdulto adulto2 = new VisitanteAdulto("Robson", 1995, "4321-8765");
        VisitanteAdulto adulto3 = new VisitanteAdulto("Cleiton", 1969, "3030-3520");
        VisitanteAdulto adulto4 = new VisitanteAdulto("Roberval", 1914, "2828-3838");
        VisitanteAdulto adulto5 = new VisitanteAdulto("Messi", 1914, "2828-3838");
        VisitanteCrianca crianca1 = new VisitanteCrianca("Juninho", 2017, "Dorival Junior", "2222-3333");
        VisitanteCrianca crianca2 = new VisitanteCrianca("Neymarzinho", 2015, "Renato Gaucho", "1234-5678");
        VisitanteCrianca crianca3 = new VisitanteCrianca("Martinha", 2020, "Jerimundo", "5628-7568");
        VisitanteCrianca crianca4 = new VisitanteCrianca("Ronaldinho", 2007, "Tite", "2755-2758");
        VisitanteCrianca crianca5 = new VisitanteCrianca("Zidaninho", 2009, "Andrea Pirlo", "2858-6675");
        parque.adicionarVisitante(adulto1);
        parque.adicionarVisitante(adulto2);
        parque.adicionarVisitante(adulto3);
        parque.adicionarVisitante(adulto4);
        parque.adicionarVisitante(adulto5);
        parque.adicionarVisitante(crianca1);
        parque.adicionarVisitante(crianca2);
        parque.adicionarVisitante(crianca3);
        parque.adicionarVisitante(crianca4);
        parque.adicionarVisitante(crianca5);

        do {
            System.out.println("\n### Menu do app.Parque ###");
            System.out.println("1. Cadastrar novo visitante");
            System.out.println("2. Listar visitantes cadastrados");
            System.out.println("3. Emitir novo ingresso");
            System.out.println("4. Registrar visita à atração");
            System.out.println("5. Localizar visitante");
            System.out.println("6. Consultar faturamento de um mês/ano");
            System.out.println("7. Consultar atrações mais visitadas em uma data");
            System.out.println("8. Atualizar Cadastro");
            System.out.println("9. Excluir Cadastro");
            System.out.println("10. Consultar visitantes em um período");
            System.out.println("0. Sair");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt(); // Captura a opção selecionada pelo usuário.
            scanner.nextLine(); // Limpar o buffer do scanner

            // Switch case para realizar ações com base na opção selecionada.
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
                    parque.localizarVisitantePorNome();
                    break;
                case 6:
                    // parque.consultarFaturamento();
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
                case 10:
                    parque.consultarVisitantesEmUmPeriodo();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 0);

        scanner.close(); // Fechando o scanner para liberar recursos.
    }
}
