package br.com.challenge.pet.service;

import br.com.challenge.pet.adapters.in.LocalizarPetLogger;
import br.com.challenge.pet.application.service.LocalizadorService;
import br.com.challenge.pet.domain.model.Request;
import br.com.challenge.pet.domain.model.Response;
import br.com.challenge.pet.domain.model.ResponseData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocalizadorServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LocalizadorService service;

    @Test
    public void localizarPet_success() {
        Request request = new Request("21", -23.5505, -46.6333, "2024-01-22");
        Response mockResponse = new Response(new ResponseData[]{
                new ResponseData("Brasil", "SP", "São Paulo", "Jardim Paulista", "Rua Haddock Lobo")});
        when(restTemplate.getForObject(anyString(), eq(Response.class))).thenReturn(mockResponse);

        ResponseEntity<ResponseData> responseEntity = service.processarRequisicao(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
    @Test
    public void localizarPet_notFound() {
        Request request = new Request("123",0, 0,"2023-12-12");
        when(restTemplate.getForObject(anyString(), eq(Response.class))).thenReturn(null);

        ResponseEntity<ResponseData> responseEntity = service.processarRequisicao(request);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void localizarPet_httpError() {
        Request request = new Request("123",-23.5505, -46.6333,"2024-02-02");
        when(restTemplate.getForObject(anyString(), eq(Response.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));


        ResponseEntity<ResponseData> responseEntity = service.processarRequisicao(request);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void localizarPet_unexpectedError() {
        Request request = new Request("123",-23.5505, -46.6333,"2024-01-01");
        when(restTemplate.getForObject(anyString(), eq(Response.class)))
                .thenThrow(new RuntimeException("Erro inesperado"));

        ResponseEntity<ResponseData> responseEntity = service.processarRequisicao(request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}