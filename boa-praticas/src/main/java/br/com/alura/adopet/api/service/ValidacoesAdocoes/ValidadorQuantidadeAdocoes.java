package br.com.alura.adopet.api.service.ValidacoesAdocoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.exception.ValidacaoExcpetion;

public class ValidadorQuantidadeAdocoes implements ValidadorAdocao {

    @Autowired
    AdocaoRepository repository;

    @Override
    public void validar(Adocao adocao) throws ValidacaoExcpetion {
        List<Adocao> adocoes = repository.findByTutorAndStatus(adocao.getTutor(), StatusAdocao.APROVADO);
        if(adocoes.size() >= 5) {
            throw new ValidacaoExcpetion("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }
    
}
