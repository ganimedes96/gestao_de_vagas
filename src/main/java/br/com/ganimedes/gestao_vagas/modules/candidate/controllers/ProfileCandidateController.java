package br.com.ganimedes.gestao_vagas.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ganimedes.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.ganimedes.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/candidate")
public class ProfileCandidateController {
  
  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;
  @GetMapping("/profile")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Tag(name = "Candidato", description = "Informações do candidato")
  @Operation(summary = "Perfil do candidato", description = "Essa função é responsável por buscar as informações do perfil do candidato")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
      }),
      @ApiResponse(responseCode = "400", description = "User not found")
  })
  @SecurityRequirement(name = "jwt_auth")
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
