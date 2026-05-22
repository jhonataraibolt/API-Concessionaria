package projeto.java.API.exception;

//Error 400 para tratamento do codigo
public class ArgumentNotValidException extends RuntimeException {
    public ArgumentNotValidException(String message) {
        super(message);
    }
}
