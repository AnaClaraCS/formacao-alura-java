package br.com.alura.adopet.api.service.ValidacoesAdocoes;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.exception.ValidacaoExcpetion;

public interface ValidadorAdocao {
    public void validar(Adocao adocao) throws ValidacaoExcpetion;
}
