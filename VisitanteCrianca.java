public class VisitanteCrianca extends Visitante {
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