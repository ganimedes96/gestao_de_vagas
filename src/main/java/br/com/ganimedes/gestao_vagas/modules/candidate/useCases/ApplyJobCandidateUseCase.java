package br.com.ganimedes.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ganimedes.gestao_vagas.exceptions.JobNotFoundException;
import br.com.ganimedes.gestao_vagas.exceptions.UsernameNotFoundException;
import br.com.ganimedes.gestao_vagas.modules.candidate.entity.ApplyJobCandidateEntity;
import br.com.ganimedes.gestao_vagas.modules.candidate.repositories.ApplyJobCandidateRepository;
import br.com.ganimedes.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.ganimedes.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobCandidateRepository applyJobRepository;
  public ApplyJobCandidateEntity execute(UUID idCandidate, UUID idJob) {
      this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });

        // Validar se a vaga existe
        this.jobRepository.findById(idJob)
        .orElseThrow(() -> {
            throw new JobNotFoundException("Job not found");
        });

        // Candidato se inscrever na vaga
        var applyJob = ApplyJobCandidateEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob).build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
  }
}
