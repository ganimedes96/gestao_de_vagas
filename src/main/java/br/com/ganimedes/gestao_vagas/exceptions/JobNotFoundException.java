package br.com.ganimedes.gestao_vagas.exceptions;

public class JobNotFoundException extends RuntimeException{
  public JobNotFoundException(String message) {
    super(message);
  }
}
