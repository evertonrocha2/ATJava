package escolaApp.service;


import escolaApp.model.domain.Professor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProfessorService {
    private static Map<Integer, Professor> professores = new HashMap<Integer, Professor>();
    private static  Integer id = 0;
    public static void incluir(Professor professor){
        professor.setId(++id);
        professores.put(professor.getId(), professor);
    }
    public static void excluir(Integer id){
        professores.remove(id);
    }
    public static Collection<Professor> obterLista(){
        return professores.values();
    }
    public static Professor obterPorId(Integer id){
        return professores.get(id);
    }

}
