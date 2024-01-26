package br.com.challenge.pet.controller;

import br.com.challenge.pet.adapters.in.LocalizadorController;
import br.com.challenge.pet.application.ports.LocalizadorPort;
import br.com.challenge.pet.domain.model.Request;
import br.com.challenge.pet.domain.model.ResponseData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalizadorControllerTest {

    @Mock
    private LocalizadorPort localizadorPort;

    @InjectMocks
    private LocalizadorController controller;

    @Test
    void localizarPet_Success() {
        Request request = new Request("1",10.0, 20.0,"2023");
        ResponseData responseData = new ResponseData();
        ResponseEntity<ResponseData> expectedResponse = ResponseEntity.ok(responseData);
        when(localizadorPort.processarRequisicao(request)).thenReturn(expectedResponse);

        ResponseEntity<ResponseData> actualResponse = controller.localizarPet(request);

        assertEquals(expectedResponse, actualResponse);
    }
}