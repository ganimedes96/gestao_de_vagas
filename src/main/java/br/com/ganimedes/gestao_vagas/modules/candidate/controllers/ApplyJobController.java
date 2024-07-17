package br.com.ganimedes.gestao_vagas.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import br.com.ganimedes.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/candidate")
public class ApplyJobController {
  @Autowired
  private  ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @PostMapping("/job/apply")
  @PreAuthorize("hasRole('CANDIDATE')")
  @SecurityRequirement(name = "jwt_auth")
    @Operation(summary = "Aplicação de um candidato para uma vaga", description = "Essa função é responsável por cadastrar um candidato para uma vaga")
  public ResponseEntity<Object>  ApplyJobController(HttpServletRequest request, @RequestBody UUID idJob) {
    try {
      var idCandidate = request.getAttribute("candidate_id");
      var result = this.applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()), idJob);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
