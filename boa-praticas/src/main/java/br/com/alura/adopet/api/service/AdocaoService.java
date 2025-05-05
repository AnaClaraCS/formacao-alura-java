package br.com.alura.adopet.api.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.exception.ValidacaoExcpetion;

@Service
public class AdocaoService {
    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private EmailService emailService;


    public String solicitar(Adocao adocao) {
        if (adocao.getPet().getAdotado() == true) {
           throw new ValidacaoExcpetion("Pet já foi adotado!");
        } else {
            List<Adocao> adocoes = repository.findAll();
            for (Adocao a : adocoes) {
                if (a.getTutor() == adocao.getTutor() && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                   throw new ValidacaoExcpetion("Tutor já possui outra adoção aguardando avaliação!");
                }
            }
            for (Adocao a : adocoes) {
                if (a.getPet() == adocao.getPet() && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                   throw new ValidacaoExcpetion("Pet já está aguardando avaliação para ser adotado!");
                }
            }
            for (Adocao a : adocoes) {
                int contador = 0;
                if (a.getTutor() == adocao.getTutor() && a.getStatus() == StatusAdocao.APROVADO) {
                    contador = contador + 1;
                }
                if (contador == 5) {
                   throw new ValidacaoExcpetion("Tutor chegou ao limite máximo de 5 adoções!");
                }
            }
        }
        adocao.setData(LocalDateTime.now());
        adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        repository.save(adocao);

        String to = adocao.getPet().getAbrigo().getEmail();
        
        String subject = "Solicitação de adoção";
        String text = "Olá " +adocao.getPet().getAbrigo().getNome() +"!\n\nUma solicitação de adoção foi registrada hoje para o pet: " +adocao.getPet().getNome() +". \nFavor avaliar para aprovação ou reprovação.";

        emailService.enviarEmail(to, subject, text);

        return null; 
    }

    public void aprovar(Adocao adocao) {
        adocao.setStatus(StatusAdocao.APROVADO);
        repository.save(adocao);;

        String to = adocao.getTutor().getEmail();
        String subject = "Adoção aprovada";
        String text = "Parabéns " +adocao.getTutor().getNome() +"!\n\nSua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " +adocao.getPet().getAbrigo().getNome() +" para agendar a busca do seu pet.";

        emailService.enviarEmail(to, subject, text);
    }

    public void reprovar(Adocao adocao){
        adocao.setStatus(StatusAdocao.REPROVADO);
        repository.save(adocao);
        
        String to = adocao.getTutor().getEmail();;
        String subject = "Adoção reprovada";
        String text = "Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " +adocao.getPet().getAbrigo().getNome() +" com a seguinte justificativa: " +adocao.getJustificativaStatus();

        emailService.enviarEmail(to, subject, text);

    }
}
