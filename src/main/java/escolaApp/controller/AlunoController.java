package escolaApp.controller;

import com.google.gson.Gson;
import escolaApp.model.domain.Aluno;
import escolaApp.model.domain.Endereco;
import escolaApp.model.domain.Professor;
import escolaApp.service.AlunoService;
import escolaApp.service.ProfessorService;
import spark.Route;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlunoController {
    public static Route obterLista = (req, res) ->{
        return AlunoService.obterLista();
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
        Aluno aluno = new Gson().fromJson(req.body(), Aluno.class);
        String cep = aluno.getEndereco().getCep();
        Endereco endereco = obterEndereco(cep);
        if (endereco != null) {
            aluno.setEndereco(endereco);
            AlunoService.incluir(aluno);
            return "A inclusão foi realizada com sucesso! " + aluno;
        } else {
            return "Erro ao obter dados de endereço para o CEP " + cep;
        }
    };
    public static Route criar = (req, res) -> {
        String nome = req.queryParams("nome");
        int idade = Integer.parseInt(req.queryParams("idade"));
        String matricula = req.queryParams("matricula");
        double nota = Double.parseDouble(req.queryParams("nota"));
        String cep = req.queryParams("cep");

        Endereco endereco = obterEndereco(cep);

        if (endereco != null) {
            Aluno aluno = new Aluno(nome, idade, matricula, endereco, nota);
            AlunoService.incluir(aluno);
            res.redirect("/aluno/lista");
        } else {
            return "Erro ao obter dados de endereço para o CEP " + cep;
        }
        return null;
    };


    public static Route excluir = (req, res) -> {
        Integer index = Integer.valueOf(req.params("id"));
        Aluno aluno = AlunoService.obterPorId(index);
        AlunoService.excluir(index);
        return "A exclusão do aluno" + aluno + " foi realizada com sucesso!" ;
    };

    public static Route obterPorId = (req, res) -> {
        Integer index = Integer.valueOf(req.params("id"));
        Aluno aluno = AlunoService.obterPorId(index);
        return "Recuperação do aluno " + aluno + " realizada com sucesso!";
    };

}
