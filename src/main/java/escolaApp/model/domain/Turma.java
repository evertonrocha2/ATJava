package escolaApp.model.domain;

public class Turma {
    private int codigo;

    public Turma(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "codigo=" + codigo +
                '}';
    }

    public Turma setCodigo(int codigo) {
        this.codigo = codigo;
        return this;
    }
}
