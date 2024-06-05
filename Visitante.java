public abstract class Visitante {
    private String nome;
    private int anoNascimento;

    public Visitante(String nome, int anoNascimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Ano de Nascimento: " + anoNascimento;
    }

    public abstract double calculaIngresso();

}