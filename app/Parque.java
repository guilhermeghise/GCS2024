package app;

import dados.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Representa o sistema de gerenciamento de um parque de diversões.

public class Parque {
    private List<Visitante> visitantes;  // Lista de visitantes cadastrados no parque
    private List<Ingresso> ingressos; // Lista de ingressos emitidos
    private int contIngressos; // Contador de ingressos emitidos para controlar a quantidade por dia
    private SimpleDateFormat dateFormat; // Formato de data para análise e formatação
    private Map<String, Map<String, Integer>> visitasPorData; // Registro de visitas por data e atração
    private List<String> atracoesCadastradas; // Lista de atrações disponíveis no parque

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
    }

    public void cadastrarNovoVisitante() {
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
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
                String tipoVisitante = (visitante instanceof VisitanteAdulto) ? "ADULTO" : "CRIANÇA";
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
}