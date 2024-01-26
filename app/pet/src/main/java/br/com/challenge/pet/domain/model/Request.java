package br.com.challenge.pet.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    private String id;
    private double latitude;
    private double longitude;
    private String dateTime;
}