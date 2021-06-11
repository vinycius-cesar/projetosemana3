package com.projetosemana3.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;

import org.springframework.boot.convert.Delimiter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.stream.Collectors;
import com.projetosemana3.exception.BadRequestException;
import com.projetosemana3.exception.BadRequestExceptionDetails;
import com.projetosemana3.exception.ValidationExceptionDetails;

@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestExceptionDetails>handerRequestExceptionDetails(BadRequestException bre){
		return new ResponseEntity<>(
				BadRequestExceptionDetails.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Bad Request Exception, Deu algum problema.")
				.details(bre.getMessage())
				.developerMessage(bre.getClass().getName())
				.build(), HttpStatus.BAD_REQUEST);
								
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationExceptionDetails>handerMethodArgumentNotValidException(
			MethodArgumentNotValidException exception){
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
		
		return new ResponseEntity<>(
				ValidationExceptionDetails.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.title("Bad Request Exception, Campos invalidos")
					.details("Verifique os campos, Mais informações no fieldsMessage")
					.developerMessage(exception.getClass().getName())
					.fields(fields)
					.fieldsMessage(fieldsMessage)
					.build(),HttpStatus.BAD_REQUEST);
				
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ValidationExceptionDetails>handerDataIntegrityViolationException(
			DataIntegrityViolationException exception){
	//	List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
	//	String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
	//	String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
		
		return new ResponseEntity<>(
				ValidationExceptionDetails.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.title("Bad Request Exception, Campos invalidos")
					.details("Campo com caracteres a mais do que permitido, Verifique:")
					.developerMessage(exception.getClass().getName())
					.build(),HttpStatus.BAD_REQUEST);
				
	}
	
	
	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<ValidationExceptionDetails>handerTransactionSystemException(
			TransactionSystemException exception){
		
		
		return new ResponseEntity<>(
				ValidationExceptionDetails.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.title("Bad Request Exception, Campos invalidos")
					.details("VERIFIQUE se há campo VAZIO")
					.developerMessage(exception.getClass().getName())
					.build(),HttpStatus.BAD_REQUEST);
				
	}
	
	
	
	
	
	

	
}
