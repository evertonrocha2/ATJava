package escolaApp.model.domain;

public class Aluno extends Pessoa{
//    private Integer id;
//
//    private String nome;
    private int idade;
    private String matricula;
    private Endereco endereco;
    private double nota;
    private Turma turma;
    private Escola escola;

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Aluno: " + id + ", nome: " + nome + ", idade: " + idade + ", matricula: " + matricula + ", endereço: " + endereco + ", nota: " + nota + ", turma: " + turma + ", escola: " + escola + "\n";
    }

    public Aluno(Integer id, String nome, int idade, String matricula, Endereco endereco, double nota) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
        this.matricula = matricula;
        this.endereco = endereco;
        this.nota = nota;
    }
    public Aluno(String nome, int idade, String matricula, Endereco endereco, double nota) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
        this.matricula = matricula;
        this.endereco = endereco;
        this.nota = nota;
    }

    public Aluno(String nome ) {
        this.nome = nome;
        this.setIdade(18);
        this.setMatricula("20304050");
        this.setNota(10);
    }
    public Aluno(String nome, Endereco endereco ) {
        this.nome = nome;
        this.setIdade(18);
        this.setMatricula("20304050");
        this.setNota(10);
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
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
