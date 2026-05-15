package projeto.java.API.exception;

//Erro de não encontrado - 404
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
