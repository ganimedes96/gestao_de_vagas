package br.com.ganimedes.gestao_vagas.modules.candidate.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  
  private String name;
  @Email(message = "O campo email deve conter um email válido")
  private String email;
  
  @NotBlank()
  @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaços")
  private String username;
  
  @Length(min = 6 , max = 100, message = "O campo password deve conter entre 6 e 100 caracteres")
  private String password;
  private String description;
  private String curriculum;

  @CreationTimestamp  
  private LocalDateTime createdAt;
}
