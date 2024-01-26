package br.com.challenge.pet.adapters.in;

import br.com.challenge.pet.domain.model.Request;
import br.com.challenge.pet.domain.model.ResponseData;

import br.com.challenge.pet.application.ports.LocalizadorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocalizadorController {

    private final LocalizadorPort localizadorPort;

    @PostMapping("localizar-pet")
    public ResponseEntity<ResponseData> localizarPet(@RequestBody Request request) {
        LocalizarPetLogger.info("Início da busca pela localidade do pet",request.toString());
        var result = localizadorPort.processarRequisicao(request);
        LocalizarPetLogger.info("Busca finalizada",request.toString());
        return result;
    }
}

