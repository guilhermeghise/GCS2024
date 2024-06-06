package app;

import dados.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parque {
    private List<Visitante> visitantes;
    private List<Ingresso> ingressos;
    private int contIngressos;
    private SimpleDateFormat dateFormat;
    private Map<String, Map<String, Integer>> visitasPorData;


    public Parque() {
        this.visitantes = new ArrayList<>();
        this.ingressos = new ArrayList<>();
        this.contIngressos = 0;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.dateFormat.setLenient(false);
        this.visitasPorData = new HashMap<>();
    }

    public void cadastrarNovoVisitante() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cadastrar Novo dados.Visitante:");
        System.out.print("1. Adulto\n2. Criança\nEscolha o tipo de visitante: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

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

        if (tipo == 1) {
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            VisitanteAdulto adulto = new VisitanteAdulto(nome, anoNascimento, telefone);
            visitantes.add(adulto);
        } else if (tipo == 2) {
            System.out.print("Nome do Responsável: ");
            String nomeResponsavel = scanner.nextLine();
            System.out.print("Telefone do Responsável: ");
            String telefoneResponsavel = scanner.nextLine();
            VisitanteCrianca crianca = new VisitanteCrianca(nome, anoNascimento, nomeResponsavel, telefoneResponsavel);
            visitantes.add(crianca);
        } else {
            System.out.println("Tipo de visitante inválido.");
        }
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

            Scanner in = new Scanner(System.in);
            System.out.print("Data do ingresso (dd/MM/yyyy): ");
            String dataStr = in.nextLine();
            Date data = null;
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

            System.out.print("Nome do visitante: ");
            String nomeVisitante = in.nextLine();
            Visitante visitante = localizarVisitantePorNome(nomeVisitante);
            if (visitante == null) {
                System.out.println("dados.Visitante não encontrado.");
                return;
            }

        contIngressos++;
        String chave = dataFormatada + " seq " + String.format("%03d", contadorIngressos);
        Ingresso ingresso = new Ingresso(chave, data, visitante);
        ingressos.add(ingresso);
        System.out.println("dados.Ingresso emitido: " + ingresso);
    }
    public Visitante localizarVisitantePorNome(String nome) {
        for (Visitante visitante : visitantes) {
            if (visitante.getNome().equalsIgnoreCase(nome)) {
                return visitante;
            }
        }
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
        System.out.print("Digite o nome do visitante: ");
        String nomeVisitante = scanner.nextLine();
        Visitante visitante = localizarVisitantePorNome(nomeVisitante);
        if (visitante == null) {
            System.out.println("Visitante não encontrado.");
            return;
        }

        System.out.print("Digite o nome da atração visitada: ");
        String atracao = scanner.nextLine();

        visitasPorData.putIfAbsent(dataFormatada, new HashMap<>());
        Map<String, Integer> visitasNaData = visitasPorData.get(dataFormatada);
        visitasNaData.put(atracao, visitasNaData.getOrDefault(atracao, 0) + 1);

        System.out.println("Visita registrada com sucesso.");
    }

    public void localizarVisitante() {

        System.out.println("Método para localizar visitante. Implementação futura.");
    }

    public void consultarFaturamento() {
        //6. Consultar faturamento de um mês/ano
        double soma=0;
        for(Visitante v: visitantes){
            soma+=v.calculaIngresso();
        }
        System.out.println("faturamento referente ao total de ingressos: R$" + soma);
        //se quiser, precisa filtrar o valor total referente aos meses ou o ano
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
        Map<String, Integer> atracoesContagem = new HashMap<>();

        for (Map.Entry<Visitante, List<String>> entrada : visitasPorVisitante.entrySet()) {
            List<String> visitas = entrada.getValue();
            for (String visita : visitas) {
                atracoesContagem.put(visita, atracoesContagem.getOrDefault(visita, 0) + 1);
            }
        }

        if (atracoesContagem.isEmpty()) {
            System.out.println("Nenhuma visita registrada na data: " + dataFormatada);
            return;
        }

        int maxVisitas = Collections.max(atracoesContagem.values());
        System.out.println("Atrações mais visitadas na data " + dataFormatada + ":");
        for (Map.Entry<String, Integer> entry : atracoesContagem.entrySet()) {
            if (entry.getValue() == maxVisitas) {
                System.out.println("Atração: " + entry.getKey() + " - Visitas: " + entry.getValue());
            }
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

        if (!encontrado) {
            System.out.println("dados.Visitante não encontrado.");
        }
    }

    public void excluirCadastro() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do visitante que deseja excluir: ");
        String nomeVisitante = scanner.nextLine();

        boolean removido = false;
        for (Visitante visitante : visitantes) {
            if (visitante.getNome().equalsIgnoreCase(nomeVisitante)) {
                visitantes.remove(visitante);
                removido = true;
                System.out.println("dados.Visitante removido com sucesso!");
                break;
            }
        }

        if (!removido) {
            System.out.println("dados.Visitante não encontrado.");
        }
    }






    //método criado para instanciar manualmente dentro do código.
    public void adicionarVisitante(Visitante visitante) {
        visitantes.add(visitante);
    }
}