import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Parque {
    private List<Visitante> visitantes;
    private SimpleDateFormat dateFormat;

    public Parque() {
        this.visitantes = new ArrayList<>();
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.dateFormat.setLenient(false);
    }

    // Método para cadastrar novo visitante (adulto ou criança)
    public void cadastrarNovoVisitante() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cadastrar Novo Visitante:");
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

    // Método para listar visitantes cadastrados
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

    // Métodos adicionais
    public void emitirNovoIngresso() {
        System.out.println("Método para emitir novo ingresso. Implementação futura.");
    }

    public void registrarVisitaAtracao() {
        System.out.println("Método para registrar visita à atração. Implementação futura.");
    }

    public void localizarVisitante() {
        System.out.println("Método para localizar visitante. Implementação futura.");
    }

    public void consultarFaturamento() {
        System.out.println("Método para consultar faturamento. Implementação futura.");
    }

    public void consultarAtracoesMaisVisitadas() {
        System.out.println("Método para consultar atrações mais visitadas. Implementação futura.");
    }

    // Método para atualizar cadastro
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
            System.out.println("Visitante não encontrado.");
        }
    }

    // Método para excluir cadastro
    public void excluirCadastro() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do visitante que deseja excluir: ");
        String nomeVisitante = scanner.nextLine();

        boolean removido = false;
        for (Visitante visitante : visitantes) {
            if (visitante.getNome().equalsIgnoreCase(nomeVisitante)) {
                visitantes.remove(visitante);
                removido = true;
                System.out.println("Visitante removido com sucesso!");
                break;
            }
        }

        if (!removido) {
            System.out.println("Visitante não encontrado.");
        }
    }
}

abstract class Visitante {
    private String nome;
    private int anoNascimento;

    public Visitante(String nome, int anoNascimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Ano de Nascimento: " + anoNascimento;
    }
}

class VisitanteAdulto extends Visitante {
    private String telefone;

    public VisitanteAdulto(String nome, int anoNascimento, String telefone) {
        super(nome, anoNascimento);
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return super.toString() + ", Telefone: " + telefone;
    }
}

class VisitanteCrianca extends Visitante {
    private String nomeResponsavel;
    private String telefoneResponsavel;

    public VisitanteCrianca(String nome, int anoNascimento, String nomeResponsavel, String telefoneResponsavel) {
        super(nome, anoNascimento);
        this.nomeResponsavel = nomeResponsavel;
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    @Override
    public String toString() {
        return super.toString() + ", Nome do Responsável: " + nomeResponsavel + ", Telefone do Responsável: " + telefoneResponsavel;
    }
}
