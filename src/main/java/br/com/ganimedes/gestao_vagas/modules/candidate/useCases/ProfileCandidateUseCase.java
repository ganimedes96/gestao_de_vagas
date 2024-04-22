package br.com.ganimedes.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ganimedes.gestao_vagas.exceptions.UserFoundException;
import br.com.ganimedes.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.ganimedes.gestao_vagas.modules.candidate.repositories.CandidateRepository;


@Service
public class ProfileCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;  

  public ProfileCandidateResponseDTO execute (UUID idCadidate) {
    var candidate = this.candidateRepository.findById(idCadidate)
    .orElseThrow(() -> {
      throw new UserFoundException("User not found");
    });
    var candidateDTO = ProfileCandidateResponseDTO.builder()
    .desciption(candidate.getDescription())
    .email(candidate.getEmail())
    .name(candidate.getName())
    .username(candidate.getUsername())
    .id(candidate.getId())
    .build();  
    
    return candidateDTO;
  }

}
