package br.com.ganimedes.gestao_vagas.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ganimedes.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/candidate")
public class ProfileCandidateController {
  
  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;
  @GetMapping("/profile")
  @PreAuthorize("hasRole('CANDIDATE')")
  public ResponseEntity<Object> profile(HttpServletRequest request) {
    var candidateId = request.getAttribute("candidate_id");
    try {
      var profile = profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
      return ResponseEntity.ok(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }    
  }

}
