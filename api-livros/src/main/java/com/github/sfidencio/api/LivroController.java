package com.github.sfidencio.api;

import com.github.sfidencio.api.dto.LivroDTO;
import com.github.sfidencio.domain.model.Livro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Livros", description = "API de Livros")

public interface LivroController {
    /*
     * Exemplo de request via curl:
     * curl -X POST "http://localhost:8080/api/livros" -H "accept: application/json"
     * -H "Content-Type: application/json" -d
     * "{ \"titulo\": \"Meu Livro\", \"autor\": \"Autor\", \"isbn\": \"123\" }"
     *
     * @param dto
     *
     * @return
     *
     * @throws JsonProcessingException
     */
    @Operation(summary = "Cria um livro"
            , description = "Cria um livro com os dados informados"
            , tags = {"Livros"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    LivroDTO criar(@Valid @RequestBody LivroDTO dto);

    @Operation(summary = "Atualiza um livro"
            , description = "Atualiza um livro com os dados informados"
            , tags = {"Livros"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    LivroDTO atualizar(@Valid @PathVariable Long id, @RequestBody LivroDTO dto);

    @Operation(summary = "Busca um livro por id"
            , description = "Busca um livro por id"
            , tags = {"Livros"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    LivroDTO buscarPorId(@PathVariable Long id);

    @Operation(summary = "Exclui um livro por id"
            , description = "Exclui um livro por id"
            , tags = {"Livros"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void excluir(@PathVariable Long id);

    @Operation(summary = "Busca todos os livros"
            , description = "Busca todos os livros"
            , tags = {"Livros"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    //Se ligar hidden = true, o parâmetro não será exibido na documentação(Swagger) se nao ele vai mostrar o objeto Pageable
    //da seguinte forma:
    //http://localhost:8080/api/v1/livros?page=0&size=2&sort=titulo,desc
    /*{
      "page": 0,
      "size": 1,
      "sort": [
        "titulo,asc"
      ]
    }*/

    //https://github.com/sfidencio/my-study-projects/blob/master/dicas-macetes-ferramentas/README.md#paginacao-spring-data
    Page<Livro> buscarTodos(@Parameter(description = "Titulo do livro para filtrar os resultados") @RequestParam(required = false,defaultValue = "") String titulo,
                            @Parameter(hidden = true) @PageableDefault(size = 2, sort = "titulo", direction = Sort.Direction.DESC) Pageable pageable);
}
