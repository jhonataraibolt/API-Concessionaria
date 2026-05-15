package projeto.java.API.exception;

//Erro de parametro vazio - 400
public class MethodArgumentNotValidException extends RuntimeException {
    public MethodArgumentNotValidException(String message) {
        super(message);
    }
}
