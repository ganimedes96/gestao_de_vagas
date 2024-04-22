package br.com.ganimedes.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ganimedes.gestao_vagas.exceptions.UserFoundException;
import br.com.ganimedes.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.ganimedes.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository
        .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundException("User already exists");
        });
    var password = passwordEncoder.encode(candidateEntity.getPassword());
    candidateEntity.setPassword(password);
    return this.candidateRepository.save(candidateEntity);
  }
}
