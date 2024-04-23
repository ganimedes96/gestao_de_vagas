package br.com.ganimedes.gestao_vagas.modules.candidate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ganimedes.gestao_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import br.com.ganimedes.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/candidate")
public class ListAllJobsByFilterController {
  
  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

  @GetMapping("/jobs")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Tag(name = "Candidato", description = "Operações relacionadas ao candidato")
  @Operation(summary = "Vagas disponíveis para o candidato", description = "Essa função e responsável por retorna as vagas disponíveis para o candidato")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
    }),
  })
  @SecurityRequirement(name = "jwt_auth")
  public List<JobEntity> listAllJobsByFilter(@RequestParam String filter) {
    return this.listAllJobsByFilterUseCase.execute(filter);
  }
}
