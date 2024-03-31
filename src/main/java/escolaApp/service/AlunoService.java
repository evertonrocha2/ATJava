package escolaApp.service;

import escolaApp.model.domain.Aluno;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AlunoService {
    private static Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();
    private static  Integer id = 0;
    public static void incluir(Aluno aluno){
        aluno.setId(++id);
        alunos.put(aluno.getId(), aluno);
    }
    public static void excluir(Integer id){
        alunos.remove(id);
    }
    public static Collection<Aluno> obterLista(){
        return alunos.values();
    }
    public static Aluno obterPorId(Integer id){
        return alunos.get(id);
    }



}
