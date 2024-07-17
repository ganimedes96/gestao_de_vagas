package br.com.ganimedes.gestao_vagas.modules.candidate.useCase;

import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ganimedes.gestao_vagas.exceptions.JobNotFoundException;
import br.com.ganimedes.gestao_vagas.exceptions.UsernameNotFoundException;
import br.com.ganimedes.gestao_vagas.modules.candidate.entity.ApplyJobCandidateEntity;
import br.com.ganimedes.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.ganimedes.gestao_vagas.modules.candidate.repositories.ApplyJobCandidateRepository;
import br.com.ganimedes.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.ganimedes.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;

import br.com.ganimedes.gestao_vagas.modules.company.entities.JobEntity;
import br.com.ganimedes.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
   
    @InjectMocks
    private ApplyJobCandidateUseCase applyJobUseCase;
    @Mock
    private JobRepository jobRepository;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock 
    private ApplyJobCandidateRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply for a job if candidate does not found")
    public void should_not_be_apply_jon_with_candidate_not_found() {
      try{
        applyJobUseCase.execute(null, null);
      } catch (Exception e){
          assertThat(e).isInstanceOf(UsernameNotFoundException.class);
      }
  }
  @Test
  @DisplayName("Should not be able to apply for a job if job does not found")
    public void should_not_be_able_to_apply_job_with_job_not_found(){
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try{
            applyJobUseCase.execute(idCandidate, null);
        } catch (Exception e){
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }
    @Test
    @DisplayName("Should be able to apply for a job")
    public void should_be_able_to_apply_job() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobCandidateEntity
        .builder()
        .candidateId(idCandidate)
        .jobId(idJob)
        .build();
            
        var applyJobCreated = ApplyJobCandidateEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));
        
        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);
        var result = applyJobUseCase.execute(idCandidate, idJob);
        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
