package escolaApp.model.domain;

public class Escola {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Escola{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
