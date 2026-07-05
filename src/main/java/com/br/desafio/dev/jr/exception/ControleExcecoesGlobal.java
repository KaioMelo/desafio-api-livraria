package com.br.desafio.dev.jr.exception;

import java.sql.SQLException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.br.desafio.dev.jr.dto.ErroDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControleExcecoesGlobal {
		
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErroDTO> handleNotFoundException(EntityNotFoundException ex) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ErroDTO objetoErroDTO = new ErroDTO(
				ex.getMessage(),
				status.value() + " ==> " + status.getReasonPhrase()
		);
		
		return new ResponseEntity<>(objetoErroDTO, status);
	}

	@ExceptionHandler({DataIntegrityViolationException.class, SQLException.class})
	public ResponseEntity<ErroDTO> handleDatabaseExceptions(Exception ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		String msgDoBanco = (ex.getCause() != null) ? ex.getCause().getMessage() : ex.getMessage();
		String msgFinal = "Erro de consistência de dados no banco: " + msgDoBanco;
		
		if (ex instanceof DataIntegrityViolationException) {
			msgFinal = "Violação de integridade dos dados (Registro duplicado ou restrição violada): " + msgDoBanco;
		}
		
		ErroDTO objetoErroDTO = new ErroDTO(
				msgFinal,
				status.value() + " ==> " + status.getReasonPhrase()
		);
		
		return new ResponseEntity<>(objetoErroDTO, status);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErroDTO> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		String msg = String.format("O parâmetro '%s' recebeu o valor '%s' que é inválido. Esperava-se o tipo %s.", 
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		
		ErroDTO objetoErroDTO = new ErroDTO(msg, status.value() + " ==> " + status.getReasonPhrase());
		return new ResponseEntity<>(objetoErroDTO, status);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErroDTO> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
		
		ErroDTO objetoErroDTO = new ErroDTO(
				"Método HTTP '" + ex.getMethod() + "' não é suportado para esta rota.",
				status.value() + " ==> " + status.getReasonPhrase()
		);
		
		return new ResponseEntity<>(objetoErroDTO, status);
	}


	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
	public ResponseEntity<ErroDTO> handleGenericException(Exception ex) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; 
		
		ErroDTO objetoErroDTO = new ErroDTO(
				"Ocorreu um erro interno inesperado no servidor: " + ex.getMessage(),
				status.value() + " ==> " + status.getReasonPhrase()
		);
		
		return new ResponseEntity<>(objetoErroDTO, status);
	}
}
