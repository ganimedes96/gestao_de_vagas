package br.com.ganimedes.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException{
  public UserFoundException(String message) {
    super(message);
  }
}
