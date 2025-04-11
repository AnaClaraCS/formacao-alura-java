package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.Consulta.AgendaDeConsultas;
import com.api.domain.Consulta.ConsultaDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultaDTO> agendar(@RequestBody @Valid ConsultaDTO dados) {
        agenda.agendarConsulta(dados);
        return ResponseEntity.ok(new ConsultaDTO(null, null, null, null, null));
    }

    @DeleteMapping("/{id}")
    @Transactional 
    public ResponseEntity<Void> cancelar(@PathVariable Long id, @RequestBody String motivo) {
        agenda.cancelar(id, motivo);
        return ResponseEntity.noContent().build();
    }

}