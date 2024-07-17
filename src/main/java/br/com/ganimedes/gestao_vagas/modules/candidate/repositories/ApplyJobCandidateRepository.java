package br.com.ganimedes.gestao_vagas.modules.candidate.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ganimedes.gestao_vagas.modules.candidate.entity.ApplyJobCandidateEntity;

public interface ApplyJobCandidateRepository extends JpaRepository<ApplyJobCandidateEntity, UUID> {
  
}
