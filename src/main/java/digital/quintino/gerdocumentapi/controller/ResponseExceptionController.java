package digital.quintino.gerdocumentapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import digital.quintino.gerdocumentapi.dto.ResponseDTO;

@ControllerAdvice
public class ResponseExceptionController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseDTO> maxUploadSizeExceededException(Exception exception) {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO("O tamanho máximo do arquivo deve ser de até 1GB!", HttpStatus.EXPECTATION_FAILED.value()));
	}

}
