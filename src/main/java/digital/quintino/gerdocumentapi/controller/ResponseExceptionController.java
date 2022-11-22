package digital.quintino.gerdocumentapi.controller;

import digital.quintino.gerdocumentapi.dto.ResponseDTO;
import digital.quintino.gerdocumentapi.utility.MensagemUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDTO> exception(Exception exception) {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(exception.getMessage(), HttpStatus.EXPECTATION_FAILED.value()));
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseDTO> maxUploadSizeExceededException(Exception exception) {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(MensagemUtility.MENSAGEM_TAMANHO_MAXIMO_ARQUIVO_UPLOAD, HttpStatus.EXPECTATION_FAILED.value()));
	}
	
}
