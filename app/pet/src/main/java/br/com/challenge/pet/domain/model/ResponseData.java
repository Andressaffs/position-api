package br.com.challenge.pet.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {

    private String country;
    private String region;
    private String locality;
    private String neighbourhood;
    private String label;
}
