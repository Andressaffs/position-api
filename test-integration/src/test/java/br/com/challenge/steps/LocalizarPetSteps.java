package br.com.challenge.steps;


import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class LocalizarPetSteps {

    @Value("${endpoint}")
    private String endpoint;


    private HttpClient httpClient;
    private String payload;
    private HttpResponse response;

    @PostConstruct
    public void init() throws IOException {
        httpClient = HttpClients.createDefault();
        payload = new String(Files.readAllBytes(Paths.get("src/test/resources/request.json")));

    }

    //Falha
    @Dado("que eu receba as informacoes do pet")
    public void que_eu_receba_as_informacoes_do_pet() throws IOException {
        HttpPost request = new HttpPost(endpoint);
        request.setEntity(new StringEntity("\"id\": null"));
        response = httpClient.execute(request);
    }

    @Entao("a API nao consiga retornar a localizacao")
    public void a_API_nao_consiga_retornar_a_localizacao(){
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(415, statusCode);
        System.out.println("Erro inesperado ");
    }

    //Sucesso
    @Dado("que eu receba as informacoes do pet pelo seu dispositivo")
    public void que_eu_receba_as_informacoes_do_pet_pelo_seu_dispositivo() throws IOException {
        HttpPost request = new HttpPost(endpoint);
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(payload));
        response = httpClient.execute(request);
    }

    @Entao("a API deve retornar a posição completa do pet")
    public void a_API_deve_retornar_a_posição_completa_do_pet() {
        if (response != null) {
            int statusCode = response.getStatusLine().getStatusCode();
            //response
            System.out.println("Posição do Pet: " + response);
        } else {
            System.out.println("Response nulo: ");
        }
    }
}