package com.github.sfidencio.demosppgsql.application;

import com.github.sfidencio.demosppgsql.infra.dao.IUsuarioDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final IUsuarioDAO usuarioDAO;

    @GetMapping
    public ResponseEntity<Object> selecionarUsuarios(@RequestParam("nome") String nome) {
        return ResponseEntity.ok(usuarioDAO.selecionarUsuarios(nome));
    }
}
