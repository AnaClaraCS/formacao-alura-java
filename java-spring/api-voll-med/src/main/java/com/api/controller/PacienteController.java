package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.domain.Paciente.DadosAtualizacaoPaciente;
import com.api.domain.Paciente.DadosListagemPacientes;
import com.api.domain.Paciente.Paciente;
import com.api.domain.Paciente.PacienteDTO;
import com.api.domain.Paciente.PacienteDetalhado;
import com.api.domain.Paciente.PacienteRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    private PacienteRepository repositorio;

    @Autowired
    public PacienteController(PacienteRepository repositorio){
        this.repositorio = repositorio;
    }

    @PostMapping
    public ResponseEntity<PacienteDetalhado> cadastrar(@RequestBody PacienteDTO dadosPaciente, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(dadosPaciente);
        repositorio.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteDetalhado(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacientes>> listar(@PageableDefault(size=10, sort={"nome"}) Pageable pagina){
        var page = repositorio.findAllByAtivoTrue(pagina);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<PacienteDetalhado> atualizar(@RequestBody DadosAtualizacaoPaciente dados){
        var paciente = repositorio.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok( new PacienteDetalhado(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id){
        var paciente = repositorio.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }
    
    
}
