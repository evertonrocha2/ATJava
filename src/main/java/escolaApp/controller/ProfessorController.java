package escolaApp.controller;

import com.google.gson.Gson;
import escolaApp.model.domain.Endereco;
import escolaApp.model.domain.Professor;
import escolaApp.service.ProfessorService;
import spark.Route;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfessorController {
    public static Route obterLista = (req, res) ->{
        return ProfessorService.obterLista();
    };

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

                return endereco;
            } else {
                System.out.println("Erro ao obter dados de endereço para o CEP " + cep);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Endereco(cep);
    }
    public static Route incluir = (req, res) -> {
        Professor professor = new Gson().fromJson(req.body(), Professor.class);
        String cep = professor.getEndereco().getCep();
        Endereco endereco = obterEndereco(cep);
        if (endereco != null) {
            professor.setEndereco(endereco);
            ProfessorService.incluir(professor);
            return "A inclusão foi realizada com sucesso! " + professor;
        } else {
            return "Erro ao obter dados de endereço para o CEP " + cep;
        }
    };
    public static Route excluir = (req, res) -> {
        Integer index = Integer.valueOf(req.params("id"));
        Professor professor = ProfessorService.obterPorId(index);
        ProfessorService.excluir(index);
        return "A exclusão do professor" + professor + " foi realizada com sucesso!" ;
    };

    public static Route obterPorId = (req, res) -> {
        Integer index = Integer.valueOf(req.params("id"));
        Professor professor = ProfessorService.obterPorId(index);
        return "Recuperação do professor " + professor + " realizada com sucesso!";
    };
}
