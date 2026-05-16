package projeto.java.API.exception;

import org.springframework.http.*;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) //Erro400
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException e) {
        String detail = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
        problema.setTitle("Erro de Validação");
        problema.setProperty("timestamp", Instant.now());
        return problema;
    }

    @ExceptionHandler(ResourceNotFoundException.class) //Erro404
    public ProblemDetail HandleResourceNotFound(ResourceNotFoundException e) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problema.setTitle("Recurso não encontrado");
        problema.setProperty("timestamp", Instant.now());
        return problema;
    }

    @ExceptionHandler(DuplicateResourceException.class) //Erro 409
    public ProblemDetail HandleDuplicade(DuplicateResourceException e) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problema.setTitle("Conflito de dados");
        problema.setProperty("timestamp", Instant.now());
        return problema;
    }
}
