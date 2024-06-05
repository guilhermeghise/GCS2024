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
        if ((2024 - getAnoNascimento()) <= 18) {
            return 15.00;
        } else if ((2024 - getAnoNascimento()) > 18 && (2024 - getAnoNascimento()) <= 59) {
            return 25.00;
        } else if(((2024 - getAnoNascimento()) >= 60)){
            return 5.00;
        }
        return 0;
    }
}
