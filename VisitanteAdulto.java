public class VisitanteAdulto extends Visitante {
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

    @Override
    public double calculaIngresso() {
        return 100.00;
    }
}
