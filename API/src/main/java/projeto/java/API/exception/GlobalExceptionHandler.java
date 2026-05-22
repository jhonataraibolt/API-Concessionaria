package projeto.java.API.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) //Erro400 para DTOs
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleValidationErrors(MethodArgumentNotValidException e) {
        String messenger = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return new Error(messenger);
    }

    @ExceptionHandler(ArgumentNotValidException.class) // Erro400 para erros do codigo
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleIllegalArgument(ArgumentNotValidException e) {
        return new Error(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class) //Erro404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Error HandleResourceNotFound(ResourceNotFoundException e) {
        return new Error(e.getMessage());
    }

    @ExceptionHandler(DuplicateResourceException.class) //Erro 409
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Error HandleDuplicade(DuplicateResourceException e) {
       return new Error(e.getMessage());
    }

    public static record Error (
            String mensagem
    ){}

}
