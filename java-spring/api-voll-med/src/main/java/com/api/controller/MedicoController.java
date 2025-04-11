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

import com.api.domain.Medico.DadosAtualizacaoMedico;
import com.api.domain.Medico.DadosListagemMedicos;
import com.api.domain.Medico.Medico;
import com.api.domain.Medico.MedicoDTO;
import com.api.domain.Medico.MedicoDetalhado;
import com.api.domain.Medico.MedicoRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    private MedicoRepository repositorio;

    @Autowired
    public MedicoController(MedicoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    public ResponseEntity<MedicoDetalhado> cadastrar(@RequestBody MedicoDTO dadosMedico, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dadosMedico);
        repositorio.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoDetalhado(medico));

    }
    
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listar(@PageableDefault(size =10, sort={"nome"}) Pageable pagina){
        var page = repositorio.findAllByAtivoTrue(pagina);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional 
    public ResponseEntity<Void> desativar(@PathVariable Long id){
        var medico = repositorio.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDetalhado> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repositorio.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new MedicoDetalhado(medico));
    }
}
