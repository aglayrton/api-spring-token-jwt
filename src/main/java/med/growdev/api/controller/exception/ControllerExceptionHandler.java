package med.growdev.api.controller.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    /*Erro 500 é quando não é tratado o erro*/
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFoundExcpetion(){
        return ResponseEntity.notFound().build();
    }

    /*Erro 400 normalmente é o de validação*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequest(MethodArgumentNotValidException e){
        var erros = e.getFieldErrors();
        return ResponseEntity.badRequest()
                .body(erros.stream().map(DadosErroValidação::new).toList());
    }
    public record DadosErroValidação(String field, String message){
        public DadosErroValidação(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
