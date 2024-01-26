package br.com.challenge.pet.application.ports;

import br.com.challenge.pet.domain.model.Request;
import br.com.challenge.pet.domain.model.ResponseData;
import org.springframework.http.ResponseEntity;

public interface LocalizadorPort {
    ResponseEntity<ResponseData> processarRequisicao(Request request);
}
