package br.com.alura.adopet.api.service.ValidacoesAdocoes;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.exception.ValidacaoExcpetion;

public class ValidadorPetAdotado implements ValidadorAdocao {

    @Override
    public void validar(Adocao adocao) throws ValidacaoExcpetion {
        if (adocao.getPet().getAdotado() == true) {
            throw new ValidacaoExcpetion("Pet já foi adotado!");
        }
    }
    
}
