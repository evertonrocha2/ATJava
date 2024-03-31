package escolaApp.model.domain;

public class Professor{
    private Integer id;
    private String nome;
    private int idade;
    private Endereco endereco;
    private Turma turma;
    private Escola escola;



    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    @Override
    public String toString() {
      return "Professor: " + "id: " + id + ", nome: " + nome + ", idade: " + idade + ", endereço: " + endereco + ", turma: " + turma + ", escola: " + escola + "\n";
    }

    public Professor(Integer id, String nome, int idade, Endereco endereco, Turma turma) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
        this.endereco = endereco;
        this.turma = turma;
    }
    public Professor( String nome) {
       this.nome = nome;

    }
    public Professor( String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
