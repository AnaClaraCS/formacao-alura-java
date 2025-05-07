package br.com.alura.adopet.api.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.adopet.api.DTO.AdocaoDTO;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.ValidacoesAdocoes.ValidadorAdocao;

@Service
public class AdocaoService {
    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    List<ValidadorAdocao> validadores;


    public void solicitar(AdocaoDTO dto) {
        Tutor tutor =  tutorRepository.getReferenceById(dto.idTutor());
        Pet pet = petRepository.getReferenceById(dto.idPet());

        Adocao adocao = new Adocao(tutor, pet);

        validadores.forEach(v -> v.validar(adocao));
        
        adocao.setData(LocalDateTime.now());
        adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        repository.save(adocao);

        String to = adocao.getPet().getAbrigo().getEmail();
        String subject = "Solicitação de adoção";
        String text = "Olá " +adocao.getPet().getAbrigo().getNome() +"!\n\nUma solicitação de adoção foi registrada hoje para o pet: " +adocao.getPet().getNome() +". \nFavor avaliar para aprovação ou reprovação.";

        emailService.enviarEmail(to, subject, text);
         
    }

    public void aprovar(AdocaoDTO dto) {
        Adocao adocao = repository.getReferenceById(dto.id());
        adocao.setStatus(StatusAdocao.APROVADO);
        repository.save(adocao);;

        String to = adocao.getTutor().getEmail();
        String subject = "Adoção aprovada";
        String text = "Parabéns " +adocao.getTutor().getNome() +"!\n\nSua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " +adocao.getPet().getAbrigo().getNome() +" para agendar a busca do seu pet.";

        emailService.enviarEmail(to, subject, text);
    }

    public void reprovar(AdocaoDTO dto) {
        Adocao adocao = repository.getReferenceById(dto.id());
        adocao.setStatus(StatusAdocao.REPROVADO);
        repository.save(adocao);
        
        String to = adocao.getTutor().getEmail();;
        String subject = "Adoção reprovada";
        String text = "Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " +adocao.getPet().getAbrigo().getNome() +" com a seguinte justificativa: " +adocao.getJustificativaStatus();

        emailService.enviarEmail(to, subject, text);
    }
}
