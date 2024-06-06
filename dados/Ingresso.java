package dados;

import java.util.Date;

public class Ingresso {
    private String identificador;
    private Date data;
    private Visitante visitante;

    public Ingresso(String identificador, Date data, Visitante visitante) {
        this.identificador = identificador;
        this.data = data;
        this.visitante = visitante;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public Date getData() {
        return data;
    }

    @Override
    public String toString() {
            return "Chave: '"+identificador+'\''+"Visitante "+visitante;
    }
}
