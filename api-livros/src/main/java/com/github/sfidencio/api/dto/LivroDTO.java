package com.github.sfidencio.api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {
    private Long id;
    @NotEmpty(message = "Campo titulo é obrigatório")
    private String titulo;
    @NotEmpty(message = "Campo autor é obrigatório")
    private String autor;
    @NotEmpty(message = "Campo isbn é obrigatório")
    private String isbn;
}
