package com.github.sfidencio.api.dto;

import com.github.sfidencio.api.dto.validators.FirstEmptyValidation;
import com.github.sfidencio.api.dto.validators.SecondValidationRegexValidation;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {
    private Long id;
    @SecondValidationRegexValidation(message = "Campo título não pode conter caracteres especiais", regex = "^[a-zA-Z0-9]*$")
    @FirstEmptyValidation(message = "Campo título é obrigatório")
    private String titulo;
    @NotEmpty(message = "Campo autor é obrigatório")
    private String autor;
    @NotEmpty(message = "Campo isbn é obrigatório")
    private String isbn;
}
