package app;

import dados.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

// Representa o sistema de gerenciamento de um parque de diversões.

public class Parque {
    private List<Visitante> visitantes;  // Lista de visitantes cadastrados no parque
    private List<Ingresso> ingressos; // Lista de ingressos emitidos
    private int contIngressos; // Contador de ingressos emitidos para controlar a quantidade por dia
    private SimpleDateFormat dateFormat; // Formato de data para análise e formatação
    private Map<String, Map<String, Integer>> visitasPorData; // Registro de visitas por data e atração
    private List<String> atracoesCadastradas; // Lista de atrações disponíveis no parque
    private Scanner scanner; // Scanner para entrada de dados

    // Construtor padrão para inicializar as estruturas de dados e formatador de data.

    public Parque() {
        this.visitantes = new ArrayList<>();
        this.ingressos = new ArrayList<>();
        this.contIngressos = 0;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.dateFormat.setLenient(false);
        this.visitasPorData = new HashMap<>();
        this.atracoesCadastradas = Arrays.asList(
                "Montanha-russa",
                "Roda-gigante",
                "Carrossel",
                "Trem-fantasma",
                "Barca Viking",
                "Carrinho de bate-bate",
                "Simuladores de realidade virtual",
                "Trem de passeio");
        this.scanner = new Scanner(System.in);

        // Inicializar com alguns dados prontos
        inicializarVisitantes();
    }

    private void inicializarVisitantes() {
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

        adicionarVisitante(adulto1);
        adicionarVisitante(adulto2);
        adicionarVisitante(adulto3);
        adicionarVisitante(adulto4);
        adicionarVisitante(adulto5);
        adicionarVisitante(crianca1);
        adicionarVisitante(crianca2);
        adicionarVisitante(crianca3);
        adicionarVisitante(crianca4);
        adicionarVisitante(crianca5);
    }

    public void executar() {
        int opcao;

        do {
            System.out.println("\n### Menu do app.Parque ###");
            System.out.println("1. Cadastrar novo visitante.");
            System.out.println("2. Listar visitantes cadastrados.");
            System.out.println("3. Emitir novo ingresso.");
            System.out.println("4. Registrar visita à atração.");
            System.out.println("5. Localizar visitante.");
            System.out.println("6. Consultar faturamento de um mês/ano.");
            System.out.println("7. Consultar atrações mais visitadas em uma data.");
            System.out.println("8. Atualizar Cadastro.");
            System.out.println("9. Excluir Cadastro.");
            System.out.println("10. Consultar visitantes em um período.");
            System.out.println("11. Consultar quantos ingressos tem disponíveis em uma determinada data.");
            System.out.println("12. Consultar a porcentagem de visitantes no parque.");
            System.out.println("0. Sair.");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarNovoVisitante();
                    break;
                case 2:
                    listarVisitantesCadastrados();
                    break;
                case 3:
                    emitirNovoIngresso();
                    break;
                case 4:
                    registrarVisitaAtracao();
                    break;
                case 5:
                    localizarVisitantePorNome();
                    break;
                case 6:
                    // Método de consultar faturamento (a ser implementado)
                    break;
                case 7:
                    consultarAtracoesMaisVisitadas();
                    break;
                case 8:
                    atualizarCadastro();
                    break;
                case 9:
                    excluirCadastro();
                    break;
                case 10:
                    consultarVisitantesEmUmPeriodo();
                    break;
                case 11:
                    ingressosDisponiveisPorData();
                    break;
                case 12: 
                    calcularPorcentagemVisitantes();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 0);
    }

    public void cadastrarNovoVisitante() {
        int tipo;
        do {
            System.out.println("Cadastrar Novo Visitante:");
            System.out.println("1. Adulto");
            System.out.println("2. Criança");
            System.out.println("0. Voltar para o menu");
            System.out.print("Escolha o tipo de visitante: ");
            tipo = scanner.nextInt();
            scanner.nextLine();

            switch (tipo) {
                case 1:
                    cadastrarNovoAdulto();
                    break;
                case 2:
                    cadastrarNovaCrianca();
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (tipo != 0);
    }

    private void cadastrarNovoAdulto() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        String dataNascimentoStr;
        Date dataNascimento = null;
        while (dataNascimento == null) {
            System.out.print("Data de Nascimento (dd/MM/yyyy): ");
            dataNascimentoStr = scanner.nextLine();
            try {
                dataNascimento = dateFormat.parse(dataNascimentoStr);
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Por favor, use o formato dd/MM/yyyy.");
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataNascimento);
        int anoNascimento = calendar.get(Calendar.YEAR);

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        VisitanteAdulto adulto = new VisitanteAdulto(nome, anoNascimento, telefone);
        visitantes.add(adulto);
        System.out.println("Adulto cadastrado com sucesso!");
    }

    private void cadastrarNovaCrianca() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        String dataNascimentoStr;
        Date dataNascimento = null;
        while (dataNascimento == null) {
            System.out.print("Data de Nascimento (dd/MM/yyyy): ");
            dataNascimentoStr = scanner.nextLine();
            try {
                dataNascimento = dateFormat.parse(dataNascimentoStr);
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Por favor, use o formato dd/MM/yyyy.");
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataNascimento);
        int anoNascimento = calendar.get(Calendar.YEAR);

        System.out.print("Nome do Responsável: ");
        String nomeResponsavel = scanner.nextLine();
        System.out.print("Telefone do Responsável: ");
        String telefoneResponsavel = scanner.nextLine();
        VisitanteCrianca crianca = new VisitanteCrianca(nome, anoNascimento, nomeResponsavel, telefoneResponsavel);
        visitantes.add(crianca);
        System.out.println("Criança cadastrada com sucesso!");
    }

    public void listarVisitantesCadastrados() {
        System.out.println("\n### Lista de Visitantes Cadastrados ###");
        if (visitantes.isEmpty()) {
            System.out.println("Nenhum visitante cadastrado.");
        } else {
            for (Visitante visitante : visitantes) {
                String tipoVisitante = (visitante instanceof VisitanteAdulto) ? "Adulto" : "Criança";
                System.out.println(tipoVisitante + ": " + visitante);
            }
        }
    }

    public void emitirNovoIngresso() {
        Scanner scanner = new Scanner(System.in);
    
        // Verificar se o visitante está cadastrado
        Visitante visitante = localizarVisitantePorNome();
        if (visitante == null) {
            System.out.println("Visitante não cadastrado.");
    
            // Perguntar se deseja cadastrar um novo visitante
            System.out.print("Deseja cadastrar um novo visitante? [S/N]: ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                cadastrarNovoVisitante();
                return; // Volta para o menu principal após cadastrar o novo visitante
            } else {
                System.out.println("Retornando ao menu principal...");
                return; // Volta para o menu principal sem cadastrar novo visitante
            }
        }
    
        // Continuar com o processo de emissão do ingresso
        System.out.print("Data do ingresso (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        Date data;
        
        try {
            data = dateFormat.parse(dataStr);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido.");
            return;
        }
    
        String dataFormatada = dateFormat.format(data);
        int contadorIngressos = contIngressos;
        
        if (contadorIngressos >= 500) {
            System.out.println("Limite de 500 ingressos por dia atingido.");
            return;
        }
    
        contIngressos++;
        String chave = dataFormatada + " seq " + String.format("%03d", contadorIngressos);
        Ingresso ingresso = new Ingresso(chave, data, visitante);
        ingressos.add(ingresso);
        System.out.println("Ingresso emitido com sucesso: " + ingresso);
    }

    public Visitante localizarVisitantePorNome() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do visitante: ");
        String nome = scanner.nextLine();
        
        for (Visitante visitante : visitantes) {
            String nomeVisitante = visitante.getNome();
            if (nomeVisitante != null && nomeVisitante.trim().equalsIgnoreCase(nome.trim())) {
                System.out.println("Visitante encontrado: " + visitante);
                return visitante;
            }
        }
        
        System.out.println("Visitante não encontrado.");
        return null;
    }

    public void registrarVisitaAtracao() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a data da visita (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        Date data;

        try {
            data = dateFormat.parse(dataStr);
        } catch (ParseException e) {
            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        String dataFormatada = dateFormat.format(data);
        Visitante visitante = localizarVisitantePorNome();
        
        if (visitante == null) {
            System.out.println("Visitante não encontrado.");
            return;
        }

        // Verifica se há um ingresso para o visitante na data especificada
        boolean ingressoEncontrado = false;
        
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getVisitante().equals(visitante)
                    && dateFormat.format(ingresso.getData()).equals(dataFormatada)) {
                ingressoEncontrado = true;
                break;
            }
        }

        if (!ingressoEncontrado) {
            System.out.println("Não há ingresso válido para o visitante na data especificada.");
            return;
        }

        System.out.println("Atrações disponíveis:");

        for (int i = 0; i < atracoesCadastradas.size(); i++) {
            System.out.println((i + 1) + ". " + atracoesCadastradas.get(i));
        }

        int opcaoAtracao;

        do {
            System.out.print("Digite o número da atração visitada: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um número válido.");
                scanner.next();
            }
            
            opcaoAtracao = scanner.nextInt();
            scanner.nextLine();

            if (opcaoAtracao < 1 || opcaoAtracao > atracoesCadastradas.size()) System.out.println("Número de atração inválido. Escolha uma das opções listadas.");
        } while (opcaoAtracao < 1 || opcaoAtracao > atracoesCadastradas.size());

        String atracao = atracoesCadastradas.get(opcaoAtracao - 1);
        visitasPorData.putIfAbsent(dataFormatada, new HashMap<>());
        Map<String, Integer> visitasNaData = visitasPorData.get(dataFormatada);
        visitasNaData.put(atracao, visitasNaData.getOrDefault(atracao, 0) + 1);
        System.out.println("Visita registrada com sucesso.");
    }

    public void consultarAtracoesMaisVisitadas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a data para consulta (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        Date data;
        
        try {
            data = dateFormat.parse(dataStr);
        } catch (ParseException e) {
            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        String dataFormatada = dateFormat.format(data);
        Map<String, Integer> atracoesContagem = visitasPorData.get(dataFormatada);

        if (atracoesContagem == null || atracoesContagem.isEmpty()) {
            System.out.println("Nenhuma visita registrada na data: " + dataFormatada);
            return;
        }

        int maxVisitas = Collections.max(atracoesContagem.values());
        System.out.println("Atrações mais visitadas na data " + dataFormatada + ":");
        
        for (Map.Entry<String, Integer> entry : atracoesContagem.entrySet()) {
            if (entry.getValue() == maxVisitas) System.out.println("Atração: " + entry.getKey() + " - Visitas: " + entry.getValue());
        }
    }

    public void atualizarCadastro() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do visitante que deseja atualizar: ");
        String nomeVisitante = scanner.nextLine();
        boolean encontrado = false;

        for (Visitante visitante : visitantes) {
            if (visitante.getNome().equalsIgnoreCase(nomeVisitante)) {
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine();
                if (!novoNome.isEmpty()) {
                    visitante.setNome(novoNome);
                }

                if (visitante instanceof VisitanteAdulto) {
                    System.out.print("Novo telefone: ");
                    String novoTelefone = scanner.nextLine();
                    if (!novoTelefone.isEmpty()) {
                        ((VisitanteAdulto) visitante).setTelefone(novoTelefone);
                    }
                } else if (visitante instanceof VisitanteCrianca) {
                    System.out.print("Novo nome do Responsável: ");
                    String novoNomeResponsavel = scanner.nextLine();
                    if (!novoNomeResponsavel.isEmpty()) {
                        ((VisitanteCrianca) visitante).setNomeResponsavel(novoNomeResponsavel);
                    }

                    System.out.print("Novo telefone do Responsável: ");
                    String novoTelefoneResponsavel = scanner.nextLine();
                    if (!novoTelefoneResponsavel.isEmpty()) {
                        ((VisitanteCrianca) visitante).setTelefoneResponsavel(novoTelefoneResponsavel);
                    }
                }
                encontrado = true;
                System.out.println("Informações atualizadas com sucesso!");
                break;
            }
        }

        if (!encontrado) System.out.println("Visitante não encontrado.");
    }

    public void excluirCadastro() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do visitante que deseja excluir: ");
        String nomeVisitante = scanner.nextLine();
        Iterator<Visitante> iterator = visitantes.iterator();
        boolean removido = false;

        while (iterator.hasNext()) {
            Visitante visitante = iterator.next();
            if (visitante.getNome().equalsIgnoreCase(nomeVisitante)) {
                System.out.print("Tem certeza que deseja excluir " + visitante.getNome() + "? [S/N]: ");
                String confirmacao = scanner.nextLine();
                if (confirmacao.equalsIgnoreCase("s")) {
                    iterator.remove();
                    removido = true;
                    System.out.println("Visitante removido com sucesso!");
                } else {
                    System.out.println("Exclusão cancelada.");
                }
                break;
            }
        }
    
        if (!removido) System.out.println("Visitante não encontrado.");
    }

    public void consultarVisitantesEmUmPeriodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a data inicial do período para consulta (dd/MM/yyyy): ");
        String dataInicialStr = scanner.nextLine();
        System.out.print("Digite a data final do período para consulta (dd/MM/yyyy): ");
        String dataFinalStr = scanner.nextLine();
        Date dataInicial;
        Date dataFinal;

        try {
            dataInicial = dateFormat.parse(dataInicialStr);
            dataFinal = dateFormat.parse(dataFinalStr);
        } catch (ParseException e) {
            System.out.println("Data(s) inválida(s). Use o formato dd/MM/yyyy.");
            return;
        }

        if (dataInicial.after(dataFinal)) {
            System.out.println("A data inicial deve ser anterior ou igual à data final.");
            return;
        }

        Set<Visitante> visitantesNoPeriodo = new HashSet<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataInicial);

        while (!calendar.getTime().after(dataFinal)) {
            String dataFormatada = dateFormat.format(calendar.getTime());
            Map<String, Integer> atracoesContagem = visitasPorData.get(dataFormatada);
            if (atracoesContagem != null) {
                for (Ingresso ingresso : ingressos) {
                    if (dateFormat.format(ingresso.getData()).equals(dataFormatada)) {
                        visitantesNoPeriodo.add(ingresso.getVisitante());
                    }
                }
            }
            calendar.add(Calendar.DATE, 1);
        }

        if (visitantesNoPeriodo.isEmpty()) {
            System.out.println("Nenhum visitante registrado no período especificado.");
        } else {
            System.out.println("Visitantes que visitaram o parque no período especificado:");
            for (Visitante visitante : visitantesNoPeriodo) {
                System.out.println("Visitante: " + visitante.getNome());
            }
        }
    }
    // método criado para instanciar manualmente dentro do código.
    public void adicionarVisitante(Visitante visitante) {
        visitantes.add(visitante);
    }

    //método criado para mostrar quantos ingressos tem dispovíveis em uma determinada data
    public void ingressosDisponiveisPorData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a data que deseja pesquisar a quantidade de ingressos (dd/MM/yyyy): ");
        String dataInicialStr = scanner.nextLine();
        Date dataInicial;

        try {
            dataInicial = dateFormat.parse(dataInicialStr);
        } catch (ParseException e) {
            System.out.println("Data inválida! Digite novamente, no formato dd/MM/yyyy.");
            return;
        }

        // Formatar a data para string no formato "dd/MM/yyyy"
        String dataFormatada = dateFormat.format(dataInicial);

        // Contar o número de ingressos emitidos para a data especificada
        int ingressosEmitidos = 0;
        for (Ingresso ingresso : ingressos) {
            if (dateFormat.format(ingresso.getData()).equals(dataFormatada)) {
                ingressosEmitidos++;
            }
        }

        // Calcular a quantidade de ingressos disponíveis
        int ingressosDisponiveis = 500 - ingressosEmitidos;

        System.out.println("Data: " + dataFormatada);
        System.out.println("Ingressos emitidos: " + ingressosEmitidos);
        System.out.println("Ingressos disponíveis: " + ingressosDisponiveis);
    }

    public void calcularPorcentagemVisitantes(){
        int totalVisitantes = visitantes.size();
        if (totalVisitantes == 0) {
            System.out.println();
            System.out.println("Nenhum visitante registrado.");
            return;
        }

        int totalCriancas = 0;
        int totalAdultos = 0;
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
    
        for (Visitante visitante : visitantes) {
            int idade = anoAtual - visitante.getAnoNascimento();
            if (idade < 12) {
                totalCriancas++;
            } else {
                totalAdultos++;
            }
        }

        double porcentagemCriancas = (totalCriancas / (double) totalVisitantes) * 100;
        double porcentagemAdultos = (totalAdultos / (double) totalVisitantes) * 100;

        System.out.println();
        System.out.printf("Crianças: %.2f%%\n", porcentagemCriancas);
        System.out.printf("Adultos: %.2f%%\n", porcentagemAdultos);
    }
}