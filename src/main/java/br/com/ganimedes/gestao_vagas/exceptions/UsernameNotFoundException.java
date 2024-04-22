package br.com.ganimedes.gestao_vagas.exceptions;

public class UsernameNotFoundException extends RuntimeException{
  public UsernameNotFoundException(String message) {
    super(message);
  }
}
