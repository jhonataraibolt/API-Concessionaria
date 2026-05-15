package projeto.java.API.exception;

//Erro de duplicatas - 409
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
