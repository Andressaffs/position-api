package br.com.challenge.pet.application.service;

import br.com.challenge.pet.adapters.in.LocalizarPetLogger;
import br.com.challenge.pet.domain.model.Request;
import br.com.challenge.pet.domain.model.Response;
import br.com.challenge.pet.domain.model.ResponseData;
import br.com.challenge.pet.application.ports.LocalizadorPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class LocalizadorService implements LocalizadorPort {

    @Value("${endpoint.api.externa}")
    private String endpoint;

    @Value("${access_key}")
    private String key;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<ResponseData> processarRequisicao(Request request) {
        LocalizarPetLogger.info("Início da requisição para API Positionstack", request.toString());

        try {
            var latitude = request.getLatitude();
            var longitude = request.getLongitude();
            String query = latitude + "," + longitude;
            var url = endpoint + "?access_key=" + key + "&query=" + query;

            Response result = restTemplate.getForObject(url, Response.class);

            if (result != null && result.getData() != null && result.getData().length > 0) {
                ResponseData responseData = ResponseData.builder()
                        .country(result.getData()[0].getCountry())
                        .region(result.getData()[0].getRegion())
                        .locality(result.getData()[0].getLocality())
                        .neighbourhood(result.getData()[0].getNeighbourhood())
                        .label(result.getData()[0].getLabel())
                        .build();

                LocalizarPetLogger.info("Resultado da requisição: "+result, request.toString());

                return ResponseEntity.ok(responseData);
            } else {
                LocalizarPetLogger.warn("Response não encontrada para a request", request.toString());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            LocalizarPetLogger.error("Erro HTTP: "+e.getStatusCode()+ "Message: "+e.getMessage(), request.toString());
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            LocalizarPetLogger.error("Erro inesperado: "+e.getMessage(), request.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
