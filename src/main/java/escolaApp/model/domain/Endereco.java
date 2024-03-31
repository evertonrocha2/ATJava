package escolaApp.model.domain;

public class Endereco {
    private String cep;
    private String localidade;
    private String uf;

    public Endereco(String cep, String localidade, String uf) {
        this.cep = cep;
        this.localidade = localidade;
        this.uf = uf;
    }
    public Endereco(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cep='" + cep + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
