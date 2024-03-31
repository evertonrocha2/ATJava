package escolaApp;

import com.google.gson.Gson;
import escolaApp.controller.AlunoController;
import escolaApp.controller.ProfessorController;
import escolaApp.controller.ViacepController;
import escolaApp.model.domain.Aluno;
import escolaApp.model.domain.Endereco;
import escolaApp.model.domain.Professor;
import escolaApp.service.AlunoService;
import escolaApp.service.ProfessorService;
import spark.Route;
import spark.Spark;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
        public static void main(String[] args) {
            Spark.port(8080);
            Spark.get("/", (req, res) -> {
                return App.class.getResourceAsStream("/index.html");
            });

            //Aluno
            Spark.get("/aluno/lista", AlunoController.obterLista);

            Spark.get("/aluno/:nome/:cep/incluir", AlunoController.incluir);

            Spark.post("/aluno/incluir", AlunoController.incluir);

            Spark.post("/aluno/criar", AlunoController.criar);

            Spark.delete("/aluno/:id/excluir", AlunoController.excluir);

            Spark.get("/aluno/:id", AlunoController.obterPorId) ;

            //Professor
            Spark.get("/professor/lista", ProfessorController.obterLista);

            Spark.post("/professor/incluir", ProfessorController.incluir);

            Spark.post("/professor/:nome/:cep/incluir", ProfessorController.incluir);

            Spark.delete("/professor/:id/excluir", ProfessorController.excluir);

            Spark.get("/professor/:id", ProfessorController.obterPorId) ;

            //ViaCep
            Spark.get("/viacep/:cep", ViacepController.getEndereco);

            incluirProfessoresIniciais();
            incluirAlunosIniciais();

        }
    private static void incluirProfessoresIniciais() {

        String[] nomes = {"João", "Maria", "Pedro", "Ana"};
        String[] ceps = {"23017390", "02874030", "23017390", "23017390"};


        for (int i = 0; i < nomes.length; i++) {
            String nome = nomes[i];
            String cep = ceps[i];
            Endereco endereco = obterEndereco(cep);
            Professor professor = new Professor(nome, endereco);
            ProfessorService.incluir(professor);
        }
    }

    private static void incluirAlunosIniciais() {

        String[] nomes = {"Everton", "Gabriel", "Pedro", "Ana"};
        String[] ceps = {"23017390", "02874030", "23017390", "23017390"};


        for (int i = 0; i < nomes.length; i++) {
            String nome = nomes[i];
            String cep = ceps[i];
            Endereco endereco = obterEndereco(cep);
            Aluno aluno = new Aluno(nome, endereco);
            AlunoService.incluir(aluno);
        }
    }


    private static Endereco obterEndereco(String cep) {
        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                reader.close();
                String jsonResponse = responseBuilder.toString();

                Gson gson = new Gson();
                Endereco endereco = gson.fromJson(jsonResponse, Endereco.class);
                endereco.setCep(cep);


                if (endereco.getLocalidade() == null || endereco.getLocalidade().isEmpty()) {
                    endereco.setLocalidade("N/A");
                }
                if (endereco.getUf() == null || endereco.getUf().isEmpty()) {
                    endereco.setUf("N/A");
                }

                return endereco;
            } else {
                System.out.println("Erro ao obter dados de endereço para o CEP " + cep);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
