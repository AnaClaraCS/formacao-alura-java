package br.com.alura.adopet.api.service.ValidacoesAdocoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.exception.ValidacaoExcpetion;

public class ValidadorTutor implements ValidadorAdocao {

    @Autowired
    AdocaoRepository repository;
    
    @Override
    public void validar(Adocao adocao) throws ValidacaoExcpetion {
        if( repository.existsTutorAndStatus(adocao.getTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)){
            throw new ValidacaoExcpetion("Tutor já possui outra adoção aguardando avaliação!");
        }
    }
    
}
